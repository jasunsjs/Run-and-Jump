import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OnewayPlatform extends Entity{
    private char direction;
    private BufferedImage platform;

    int moved = 0;

    public OnewayPlatform(int x, int y, int width, int height, String direction){
        setBounds(new Rectangle(x*Const.SCALED_TILE_SIZE, y*Const.SCALED_TILE_SIZE, width*Const.SCALED_TILE_SIZE, height*Const.SCALED_TILE_SIZE));

        if(direction.equals("Left")){
            this.direction='L';
        }else if(direction.equals("Right")){
            this.direction='R';
        }else if(direction.equals("Up")){
            this.direction='U';
        }else if(direction.equals("Down")){
            this.direction='D';
        }

        try {
            platform = ImageIO.read(getClass().getResourceAsStream("Assets/Tiles/Oneway.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int checkDirection(char check){
        if(check==direction){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(platform, (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight(), null);
    }


}
