import javax.swing.*;
import java.awt.*;
import java.io.File;

public final class Const{
    public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public static final Color OFF_WHITE = Color.decode("#e6e4d7");

    public static final int TILE_SIZE = 16; //a tile is 16 x 16 pixels
    public static final int SCALING = 1;

    public static final int SCALED_TILE_SIZE = TILE_SIZE * SCALING; //Scale tiles to high resolution displays 80 x 80 tile

    public static final int DISPLAY_WIDTH = 64;
    public static final int DISPLAY_HEIGHT = 48;

    public static final Dimension SCALED_DISPLAY_SIZE = //Scale display to hgih resolution displays
        new Dimension((int)(SCALED_TILE_SIZE*DISPLAY_WIDTH), (int)(SCALED_TILE_SIZE*DISPLAY_HEIGHT)); 
    
    public static final double Gravity = 9.81;

    public static final int TICKS = 60;

    //Images
}