import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ThrowableObject extends Entity {
    private KeyHandler keyHandler;
    private BufferedImage ball;
    private Player touchingPlayer;
    private boolean attatched;
    private int pickupCooldown;

    public ThrowableObject(int x, int y, KeyHandler keyHandler){
        setBounds(x*Const.SCALED_TILE_SIZE, y*Const.SCALED_TILE_SIZE, (int)(Const.SCALED_TILE_SIZE*0.75), (int)(Const.SCALED_TILE_SIZE*0.75));

        this.keyHandler=keyHandler;

        try {
            ball = ImageIO.read(getClass().getResourceAsStream("Assets/Entities/Throwable.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(attatched){
            if(touchingPlayer.getFacing()=='L'){
                setLocation((int)(touchingPlayer.getX()-getWidth()), (int)(touchingPlayer.getY()+Const.SCALED_TILE_SIZE*0.50));
            }else{
                setLocation((int)(touchingPlayer.getX()+touchingPlayer.getWidth()), (int)(touchingPlayer.getY()+Const.SCALED_TILE_SIZE*0.50));
            }
            if(keyHandler.getPickup() && pickupCooldown == 0){
                pickupCooldown = 30;

                if(touchingPlayer.getFacing()=='L'){
                    setSpeedX(-8);
                }else{
                    setSpeedX(8);
                }

                setSpeedY(-13);
                setLocation((int)touchingPlayer.getX(), (int)touchingPlayer.getY());
                attatch(false);
            }
        }else{
            if(getBottom() != 1){
                velDown();
            }
    
            if(getTop() == 1 && getSpeedY()<0){
                setSpeedY(-getSpeedY()-1);
            }
            if(getBottom() == 1 && getSpeedY()>0){
                setSpeedY(-getSpeedY()+1);
            }
    
            if(getLeft() == 1 && getSpeedX()<0){
                setSpeedX(-getSpeedX()-1);
            }
            if(getRight() == 1 && getSpeedX()>0){
                setSpeedX(-getSpeedX()+1);
            }
    
            setLocation((int)getX()+getSpeedX(), (int)getY()+getSpeedY());
    
            if(touchingPlayer!=null && keyHandler.getPickup() && pickupCooldown == 0){
                pickupCooldown = 30;
                attatch(true);
            }
            if(!attatched){
                touchingPlayer = null;
            }
        }
        if(pickupCooldown>0){
            pickupCooldown--;
        }
    }

    public void attatch(boolean attatched){
        this.attatched = attatched;
    }

    public void setTouchingPlayer(Player player){
        this.touchingPlayer = player;
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawImage(ball, (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight(), null);
    }
}
