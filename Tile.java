import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;

public class Tile{
    private BufferedImage icon;
    private int state; //Stores state of tile,2 death,1 solid, 0 is air
    private Rectangle hitbox;

    public Tile(){
    }

    public BufferedImage getIcon(){
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state = state; 
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public void setHitbox(int x, int y, int width, int height){
        this.hitbox = new Rectangle(x, y, width, height);
    }
}