import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileManager {
    GamePanel panel;
    Tile[] tiles;
    BufferedReader br;
    StringTokenizer st;
    int[][] map;

    public TileManager(GamePanel panel){
        this.panel = panel;
        
        tiles = new Tile[10];

        getImages();
    }

    private void getImages(){

        try {

            tiles[0] = new Tile();
            tiles[0].setIcon(ImageIO.read(getClass().getResourceAsStream("Assets/Tiles/Air.png")));

            tiles[1] = new Tile();
            tiles[1].setIcon(ImageIO.read(getClass().getResourceAsStream("Assets/Tiles/Platform.png")));

            tiles[2] = new Tile();
            tiles[2].setIcon(ImageIO.read(getClass().getResourceAsStream("Assets/Tiles/Spikes.png")));

            tiles[3] = new Tile();
            tiles[3].setIcon(ImageIO.read(getClass().getResourceAsStream("Assets/Tiles/Grassless.png")));

            tiles[4] = new Tile();
            tiles[4].setIcon(ImageIO.read(getClass().getResourceAsStream("Assets/Tiles/LeftSpikes.png")));

            tiles[5] = new Tile();
            tiles[5].setIcon(ImageIO.read(getClass().getResourceAsStream("Assets/Tiles/RightSpikes.png")));

            tiles[6] = new Tile();
            tiles[6].setIcon(ImageIO.read(getClass().getResourceAsStream("Assets/Tiles/Flag.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw (Graphics2D g2d){
        for(int x=0; x<map.length; x++){
            for(int y=0; y<map[x].length; y++){
                if(map[x][y]==6){
                    g2d.drawImage(tiles[map[x][y]].getIcon(), x*Const.SCALED_TILE_SIZE, (y-2)*Const.SCALED_TILE_SIZE, Const.SCALED_TILE_SIZE*3, Const.SCALED_TILE_SIZE*3,null);
                }else{
                    g2d.drawImage(tiles[map[x][y]].getIcon(), x*Const.SCALED_TILE_SIZE, y*Const.SCALED_TILE_SIZE, Const.SCALED_TILE_SIZE, Const.SCALED_TILE_SIZE,null);
                }
            }
        }
    }

    public void load(File mapData) throws IOException{
        br = new BufferedReader(new FileReader(mapData));
        st = new StringTokenizer(br.readLine());
        
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        this.map = new int[x][y];
        for(int col=0; col<y; col++){
            st = new StringTokenizer(br.readLine());
            for(int row=0; row<x; row++){
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

    }

    public int getType(int x, int y){
        return map[x][y];
    }

    public void setType(int x, int y, int type){
        map[x][y]=type;
    }

    public Rectangle getHitbox(int x, int y){
        return tiles[map[x][y]].getHitbox();
    }

}
