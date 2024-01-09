import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class LevelSelector extends JPanel{
    private BufferedImage background;
    private BufferedImage selectLevel;

    public LevelSelector(JButton[] levels, boolean[] levelComplete){
        setPreferredSize(Const.SCALED_DISPLAY_SIZE);
        setDoubleBuffered(true);
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        try {
            background = ImageIO.read(getClass().getResourceAsStream("Assets/Background.png"));
            selectLevel = ImageIO.read(getClass().getResourceAsStream("Assets/Selectlevel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(Box.createRigidArea(new Dimension((int)Const.SCALED_TILE_SIZE*14,0)));

        for(int i=0; i<levels.length; i++){
            initiateLevelStarter(levels[i], i+1, levelComplete[i]);
        }

        //startButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        //add(Box.createRigidArea(new Dimension(0,(int)Const.SCALED_TILE_SIZE*30)));
        //add(startButton);
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D  g2d = (Graphics2D)g;
        setBackground(Color.BLACK);

        g2d.drawImage(background, 0, 0, (int)Const.SCALED_DISPLAY_SIZE.getWidth(), (int)Const.SCALED_DISPLAY_SIZE.getHeight(), null);

        g2d.drawImage(selectLevel, Const.SCALED_TILE_SIZE*4, Const.SCALED_TILE_SIZE*8, (int)Const.SCALED_TILE_SIZE*56, (int)Const.SCALED_TILE_SIZE*8, null);

        revalidate();
        repaint();
    }

    public void initiateLevelStarter(JButton button, int levelNum, boolean complete){
        BufferedImage icon = null;
        try {
            if(complete){
                icon = ImageIO.read(getClass().getResourceAsStream("Assets/Buttons/Check.png"));
            }else{
                icon = ImageIO.read(getClass().getResourceAsStream("Assets/Buttons/Level"+levelNum+".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        button.setIcon(new ImageIcon(icon.getScaledInstance(Const.SCALED_TILE_SIZE*5, Const.SCALED_TILE_SIZE*5, Image.SCALE_AREA_AVERAGING)));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(Const.SCALED_TILE_SIZE*5, Const.SCALED_TILE_SIZE*5));
        add(button);
    }
}
