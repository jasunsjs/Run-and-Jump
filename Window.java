/**
 * [Window.java]
 * Window where graphical components are shown
 * Manages current shown JPanel
 * @author Evan Tao
 * @version 1.0 Jun 2022
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame{
    //JPanel classes
    private GamePanel game;
    private MainMenu mainMenu;
    private LevelSelector levelSelector;

    //JComponent classes
    private JButton startButton;
    private JButton joinButton;
    private JButton[] levels;

    //Control Flags
    private boolean[] levelComplete;

    //Threads
    private Thread gameThread;

    /**
     * Window Constructor
     */
    public Window(){
        //JFrame properties
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Run and Jump");

        //Initiate Flags
        levelComplete = new boolean[5];
        
        //Initiate Compoenents
        startButton = new JButton();
        startButton.addActionListener(new Eavesdropper());
        joinButton = new JButton();
        joinButton.addActionListener(new Eavesdropper());

        levels = new JButton[5];
        for(int i=0; i<5; i++){
            levels[i] = new JButton();
            levels[i].addActionListener(new Eavesdropper());
        }

        //Initiate JPanels
        mainMenu = new MainMenu(startButton, joinButton);
        
        //JFrame settings
        setContentPane(mainMenu);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * launchGame
     * This method takes levelNumber as a parameter. It will initialize and set the necessary settings
     * Start gameThread and set game as current Displayed JPanel
     * Sets other JPanels to null
     * @param levelNumber
     */
    public void launchGame(int levelNumber){

        game = new GamePanel(new KeyHandler(), levelNumber);
        game.addAncestorListener(new Eavesdropper());

        gameThread = new Thread(game);
        gameThread.start();
        setContentPane(game);
        game.requestFocusInWindow();
        pack();
        this.mainMenu = null;
        this.levelSelector = null;
    }

    /**
     * launchLevelSelector
     * This method will initalize levelSelector,
     * set level selector as main content
     * set other JPanels to null
     */
    public void launchLevelSelector(){

        levelSelector = new LevelSelector(levels, levelComplete);

        setContentPane(levelSelector);
        levelSelector.requestFocusInWindow();
        pack();
        this.game = null;
        this.mainMenu = null;
    }

    /**
     * launchLevelSelector
     * This method will initalize levelSelector,
     * set level selector as main content
     * set other JPanels to null
     */
    public void launchJoinMenu(){

        levelSelector = new LevelSelector(levels, levelComplete);

        setContentPane(levelSelector);
        levelSelector.requestFocusInWindow();
        pack();
        this.game = null;
        this.mainMenu = null;
    }

    /**
     * Eavesdropper Private Class
     * listens to AncestorEvents and ActionEvents
     * Used to send listeners to other classes
     */
    private class Eavesdropper implements ActionListener, AncestorListener{
        /**
         * actionPerformed
         * @param ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==startButton){
                launchLevelSelector();
            }
            if(e.getSource()==joinButton){
                launchJoinMenu();
            }
            if(e.getSource()==levels[0]){
                launchGame(1);
            }
            if(e.getSource()==levels[1]){
                launchGame(2);
            }
            if(e.getSource()==levels[2]){
                launchGame(3);
            }
            if(e.getSource()==levels[3]){
                launchGame(4);
            }
            if(e.getSource()==levels[4]){
                launchGame(5);
            }
        }

        @Override
        public void ancestorRemoved(AncestorEvent event) {
            if(event.getSource() == game){
                if(game.getComplete()){
                    levelComplete[game.getlevelNumber()-1] = true;
                }
                game.stop();
                launchLevelSelector();
            }
        }

        @Override
        public void ancestorAdded(AncestorEvent event) {}

        @Override
        public void ancestorMoved(AncestorEvent event) {}

    }

    public static void main(String[] args){
        Window window = new Window();
    }

}
