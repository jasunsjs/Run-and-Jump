import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.awt.image.*;
import javax.imageio.*;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    private final Dimension startSize = new Dimension((int)Const.SIZE.getWidth()/10, (int)Const.SIZE.getWidth()/20);
    private final Dimension optionSize = new Dimension((int)Const.SIZE.getWidth()/8, (int)Const.SIZE.getWidth()/20);

    private long frameTime = System.nanoTime();
    private boolean failed = false;
    private boolean complete = false;
    
    private boolean running;

    private KeyHandler keyHandler;
    private Player player;
    private TileManager map;
    private CollisionManager collider;
    private levelManager level;
    private int levelNumber;

    private BufferedImage background;


    public GamePanel(KeyHandler keyHandler, int levelNumber){
        setPreferredSize(Const.SCALED_DISPLAY_SIZE);
        setDoubleBuffered(true);
        setBackground(Color.BLACK);

        this.levelNumber = levelNumber;
        this.keyHandler = keyHandler;
        addKeyListener(keyHandler);

        map = new TileManager(this);

        collider = new CollisionManager(this, map);

        level = new levelManager(this, keyHandler, map, levelNumber);

        try {
            background = ImageIO.read(getClass().getResourceAsStream("Assets/Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("load");

        setFocusable(true);
        setVisible(true);
        
    }

    @Override
    public void run(){
        double drawDelay = 1000000000/Const.TICKS;
        double delay = 0;
        long prevTime = System.nanoTime();
        long currentTime;

        running = true;
        while(running){

            currentTime = System.nanoTime();

            delay += (currentTime-prevTime)/drawDelay;

            prevTime = currentTime;

            if(delay>=1){
                update();
                delay=0;
            }

        }
    }

    public void update(){
        level.checkEntityCollision(collider);
        level.update();
        if(failed||keyHandler.getReset()){
            level = new levelManager(this, keyHandler, map, levelNumber);
            failed = false;
        }
        if(keyHandler.getEscape()){
            System.out.println("gogogo");
            stop();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        long currentTime = System.nanoTime();
        //System.out.println("FPS:"+1000000000/(currentTime-frameTime));
        frameTime = currentTime;

        Graphics2D  g2d = (Graphics2D)g;

        setBackground(Color.BLACK);

        g2d.drawImage(background, 0, 0, (int)Const.SCALED_DISPLAY_SIZE.getWidth(), (int)Const.SCALED_DISPLAY_SIZE.getHeight(), null);

        map.draw(g2d);

        level.draw(g2d);

        g2d.dispose();

        revalidate();
        repaint();
    }

    public void setFailed(boolean failed){
        this.failed=failed;
    }

    public void setComplete(boolean complete){
        this.complete=complete;
    }

    public int getlevelNumber(){
        return levelNumber;
    }

    public void stop(){
        setVisible(false);
        running = false;
    }

    public boolean getComplete(){
        return complete;
    }
    
}