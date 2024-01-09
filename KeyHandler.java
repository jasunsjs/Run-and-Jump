import java.awt.event.*;

public class KeyHandler implements KeyListener{
    private boolean up,down,left,right;
    private boolean reset;
    private boolean pickup;
    private boolean escape;

    @Override
    public void keyPressed(KeyEvent e){

        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            up=true;
        }
        if(keyCode == KeyEvent.VK_A){
            left = true;
        }
        if(keyCode == KeyEvent.VK_S){
            down = true;
        }
        if(keyCode == KeyEvent.VK_D){
            right = true;
        }
        if(keyCode == KeyEvent.VK_R){
            reset=true;
        }
        if(keyCode == KeyEvent.VK_G){
            pickup=true;
        }
        if(keyCode == KeyEvent.VK_ESCAPE){
            System.out.println("escape");
            escape = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W){
            up = false;
        }
        if(keyCode == KeyEvent.VK_A){
            left = false;
        }
        if(keyCode == KeyEvent.VK_S){
            down = false;
        }
        if(keyCode == KeyEvent.VK_D){
            right = false;
        }
        if(keyCode == KeyEvent.VK_R){
            reset=false;
        }
        if(keyCode == KeyEvent.VK_G){
            pickup=false;
        }
        if(keyCode == KeyEvent.VK_ESCAPE){
            escape = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){
        
    }

    //Getters for up, left, down, right
    public boolean getUp(){
        return up;
    }

    public boolean getLeft(){
        return left;
    }

    public boolean getDown(){
        return down;
    }

    public boolean getRight(){
        return right;
    }

    public boolean getReset(){
        return reset;
    }

    public boolean getPickup(){
        return pickup;
    }

    public boolean getEscape(){
        return escape;
    }

}