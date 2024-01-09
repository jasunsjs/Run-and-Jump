import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class MainMenu extends JPanel{
    private final Dimension buttonSize = new Dimension((int)Const.SCALED_TILE_SIZE*20, (int)Const.SCALED_TILE_SIZE*7);

    private BufferedImage background;
    private BufferedImage newGameIcon;
    private BufferedImage joinGameIcon;
    private BufferedImage title;
    private boolean started;

    public MainMenu(JButton startButton, JButton joinButton){
        setPreferredSize(Const.SCALED_DISPLAY_SIZE);
        setDoubleBuffered(true);
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        try {
            background = ImageIO.read(getClass().getResourceAsStream("Assets/Background.png"));
            newGameIcon = ImageIO.read(getClass().getResourceAsStream("Assets/Buttons/New_Game.png"));
            joinGameIcon = ImageIO.read(getClass().getResourceAsStream("Assets/Buttons/Join_Game.png"));
            title = ImageIO.read(getClass().getResourceAsStream("Assets/Title.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        startButton.setIcon(new ImageIcon(newGameIcon.getScaledInstance((int)buttonSize.getWidth(), 
            (int)buttonSize.getHeight(), Image.SCALE_AREA_AVERAGING)));
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setPreferredSize(buttonSize);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        joinButton.setIcon(new ImageIcon(joinGameIcon.getScaledInstance((int)buttonSize.getWidth(), 
            (int)buttonSize.getHeight(), Image.SCALE_AREA_AVERAGING)));
            joinButton.setBorderPainted(false);
            joinButton.setFocusPainted(false);
            joinButton.setContentAreaFilled(false);
        joinButton.setPreferredSize(buttonSize);
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        add(Box.createRigidArea(new Dimension(0,(int)Const.SCALED_TILE_SIZE*27)));
        add(startButton);
        add(joinButton);
        setFocusable(true);
        setVisible(true);
    }

    public boolean getStarted(){
        return started;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D  g2d = (Graphics2D)g;
        setBackground(Color.BLACK);

        g2d.drawImage(background, 0, 0, (int)Const.SCALED_DISPLAY_SIZE.getWidth(), (int)Const.SCALED_DISPLAY_SIZE.getHeight(), null);

        g2d.drawImage(title, Const.SCALED_TILE_SIZE*12, Const.SCALED_TILE_SIZE*8, (int)Const.SCALED_TILE_SIZE*40, (int)Const.SCALED_TILE_SIZE*16, null);

        revalidate();
        repaint();
    }
    
}