import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Block extends Entity{

    private Entity push;
    private BufferedImage block;

    public Block(int x, int y){
        setBounds(new Rectangle((int)(Const.SCALED_TILE_SIZE*x), (int)(Const.SCALED_TILE_SIZE*y), Const.SCALED_TILE_SIZE*2, Const.SCALED_TILE_SIZE*2));

        try {
            block = ImageIO.read(getClass().getResourceAsStream("Assets/Entities/PushableBlock.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(){
        if(getBottom() != 1){
            velDown();
        }

        if(getTop() == 1 && getSpeedY()<0){
            setSpeedY(0);
        }
        if(getBottom() == 1 && getSpeedY()>0){
            setSpeedY(0);
        }

        if(getLeft() == 1 && getSpeedX()<0){
            setSpeedX(0);
        }
        if(getRight() == 1 && getSpeedX()>0){
            setSpeedX(0);
        }

        setLocation((int)getX()+getSpeedX(), (int)getY()+getSpeedY());
        velZero();
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(block, (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight(), null);
    }

}