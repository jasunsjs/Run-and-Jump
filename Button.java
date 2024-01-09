import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Button extends Entity{

    private boolean pressed;
    private BufferedImage pressedButton;
    private BufferedImage unpressedButton;
    private Rectangle pressedHitBox;
    private Rectangle unpressedHitBox;
    private ActionPlatform action;

    public Button(int x, int y){
        unpressedHitBox = new Rectangle((int)(Const.SCALED_TILE_SIZE*(x+0.25)), (int)(Const.SCALED_TILE_SIZE*(y)), (int)(Const.SCALED_TILE_SIZE*1.5), (int)(Const.SCALED_TILE_SIZE*1));
        pressedHitBox = new Rectangle((int)(Const.SCALED_TILE_SIZE*(x+0.25)), (int)(Const.SCALED_TILE_SIZE*(y+0.25)), (int)(Const.SCALED_TILE_SIZE*1.5), (int)(Const.SCALED_TILE_SIZE*0.75));
        setBounds(unpressedHitBox);

        try {
            pressedButton = ImageIO.read(getClass().getResourceAsStream("Assets/Entities/PushedButton.png"));
            unpressedButton = ImageIO.read(getClass().getResourceAsStream("Assets/Entities/UnpushedButton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addAction(ActionPlatform action){
        this.action = action;
    }



    @Override
    public void update(){
        if(getTop()==1){
            action.start();
            setBounds(pressedHitBox);
        }else{
            action.stop();
            setBounds(unpressedHitBox);
        }
    }

    @Override
    public void draw(Graphics2D g2d){
        if(getTop()==1){
            g2d.drawImage(pressedButton, (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight(), null);
        }else{
            g2d.drawImage(unpressedButton, (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight(), null);
        }
    }

}