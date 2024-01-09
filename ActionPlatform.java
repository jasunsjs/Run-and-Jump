import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ActionPlatform extends Entity{
    private int directionX;
    private int directionY;
    private int distance;
    private BufferedImage platform;
    private boolean running;
    private boolean moving;

    int moved = 0;

    public ActionPlatform(int x, int y, int width, int height, String direction, int distance){
        setBounds(new Rectangle(x*Const.SCALED_TILE_SIZE, y*Const.SCALED_TILE_SIZE, width*Const.SCALED_TILE_SIZE, height*Const.SCALED_TILE_SIZE));
        this.distance = distance*Const.SCALED_TILE_SIZE;

        if(direction.equals("Left")){
            this.directionX=-1;
        }else if(direction.equals("Right")){
            this.directionX=1;
        }else if(direction.equals("Up")){
            this.directionY=-1;
        }else if(direction.equals("Down")){
            this.directionY=1;
        }

        try {
            platform = ImageIO.read(getClass().getResourceAsStream("Assets/Tiles/Grassless.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(){
        moving=false;
        if(running){
            if(moved<distance){
                setLocation((int)getX()+directionX, (int)getY()+directionY);
                moving=true;
                moved++;
            }
        }else if (moved!=0){
            setLocation((int)getX()-directionX, (int)getY()-directionY);
            moved--;
        }
    }

    public void start(){
        running = true;
    }

    public void stop(){
        running = false;
    }

    public int getDirectionX(){
        return directionX;
    }

    public int getDirectionY(){
        return directionY;
    }

    public boolean getMoving(){
        return moving;
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(platform, (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight(), null);
    }


}
