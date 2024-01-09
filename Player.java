import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{

    private GamePanel panel;
    private KeyHandler keyHandler;
    private BufferedImage[] leftSprites;
    private BufferedImage[] rightSprites;
    private int spritePointer;
    private char facing;

    private int jumpCooldown;

    public Player(GamePanel panel, KeyHandler keyHandler){
        this.panel=panel;
        this.keyHandler=keyHandler;

        setBounds(new Rectangle(0, 0, Const.SCALED_TILE_SIZE, Const.SCALED_TILE_SIZE*2-5));
        loadSprites();
    }

    public void setDefaultValues(int x, int y, int speedX, int speedY){
        setLocation(x*Const.SCALED_TILE_SIZE, y*Const.SCALED_TILE_SIZE);
        setSpeedX(speedX);
        setSpeedY(speedY);
    }

    public void update(){
        updateSpeed();

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

        if(getTop()==2||getBottom()==2||getLeft()==2||getRight()==2){
            panel.setFailed(true);
        }

        if(getTop()==3||getBottom()==3||getLeft()==3||getRight()==3){
            panel.setComplete(true);
            panel.stop();
        }

        if(getSpeedX()<0){
            facing = 'L';
        }else if(getSpeedX()>0){
            facing = 'R';
        }else{
            spritePointer = 0;
        }
        setLocation((int)getX()+getSpeedX(), (int)getY()+getSpeedY());

    }

    public void updateSpeed(){
        if(keyHandler.getLeft()){
            velLeft();
        }else if(keyHandler.getRight()){
            velRight();
        }else{
            velZero();
        }

        if(keyHandler.getUp() && getBottom() == 1 && jumpCooldown == 0){
            jumpCooldown=25;
            setSpeedY(-13);
        }else if(jumpCooldown>0){
            jumpCooldown--;
        }

        if(getBottom() != 1){
            velDown();
        }
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.BLACK);

        if(facing=='L'){
            g2d.drawImage(leftSprites[spritePointer/2], (int)getX(),(int)getY()-5,Const.SCALED_TILE_SIZE, Const.SCALED_TILE_SIZE*2,null);
        }else{
            g2d.drawImage(rightSprites[spritePointer/2], (int)getX(),(int)getY()-5,Const.SCALED_TILE_SIZE, Const.SCALED_TILE_SIZE*2,null);
        }

        spritePointer++;
        if(spritePointer==8){
            spritePointer=0;
        }
    }

    public char getFacing(){
        return facing;
    }

    public void loadSprites(){
        leftSprites = new BufferedImage[4];
        rightSprites = new BufferedImage[4];

        try {
            leftSprites[0] = ImageIO.read(getClass().getResourceAsStream("Assets/PlayerSprites/Player1/1L.png"));
            leftSprites[1] = ImageIO.read(getClass().getResourceAsStream("Assets/PlayerSprites/Player1/2L.png"));
            leftSprites[2] = ImageIO.read(getClass().getResourceAsStream("Assets/PlayerSprites/Player1/3L.png"));
            leftSprites[3] = ImageIO.read(getClass().getResourceAsStream("Assets/PlayerSprites/Player1/4L.png"));
            rightSprites[0] = ImageIO.read(getClass().getResourceAsStream("Assets/PlayerSprites/Player1/1R.png"));
            rightSprites[1] = ImageIO.read(getClass().getResourceAsStream("Assets/PlayerSprites/Player1/2R.png"));
            rightSprites[2] = ImageIO.read(getClass().getResourceAsStream("Assets/PlayerSprites/Player1/3R.png"));
            rightSprites[3] = ImageIO.read(getClass().getResourceAsStream("Assets/PlayerSprites/Player1/4R.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}