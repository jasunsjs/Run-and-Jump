import java.awt.*;

public class Entity extends Rectangle implements Actions{
    private int touchTop, touchBottom, touchLeft,touchRight;
    private int speedX, speedY;

    public void setSpeedX(int speedX){
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY){
        this.speedY = speedY;
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }

    public void velUp(){
        if(speedY>-5) speedY-=1;
    }
    public void velDown(){
        if(speedY<5) speedY+=1;
    }

    public void velLeft(){
        if(speedX>-5) speedX-=1;
    }
    public void velRight(){
        if(speedX<5) speedX+=1;
    }
    public void velZero(){
        if(speedX>0){
            speedX--;
        }
        if(speedX<0){
            speedX++;
        }
    }
    public void velNeutral(){
        if(speedY>0){
            speedY--;
        }
        if(speedY<0){
            speedY++;
        }
    }

    public int getTop(){
        return touchTop;
    }
    public void setTop(int touchTop){
        this.touchTop=touchTop;
    }

    public int getBottom(){
        return touchBottom;
    }
    public void setBottom(int touchBottom){
        this.touchBottom=touchBottom;
    }

    public int getLeft(){
        return touchLeft;
    }
    public void setLeft(int touchLeft){
        this.touchLeft=touchLeft;
    }

    public int getRight(){
        return touchRight;
    }
    public void setRight(int touchRight){
        this.touchRight=touchRight;
    }

    @Override
    public void draw(Graphics2D g2d) {
        
    }

    @Override
    public void update(){

    }

}
