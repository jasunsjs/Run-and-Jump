import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.*;

public class levelManager {
    private GamePanel panel;
    private Player player;
    private TileManager map;
    private ArrayList<Entity> entities;
    private KeyHandler keyHandler;

    public levelManager(GamePanel panel, KeyHandler keyHandler, TileManager map, int levelNumber){
        this.panel=panel;
        this.map=map;
        this.keyHandler = keyHandler;

        this.player = new Player(panel, keyHandler);

        this.entities = new ArrayList<Entity>();

        File mapData = new File("LevelData/Map"+levelNumber);
        File entityData = new File("LevelData/Entity"+levelNumber);
        try {
            initialize(mapData, entityData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(File mapData, File entityData) throws IOException{
        map.load(mapData);
        BufferedReader br = new BufferedReader(new FileReader(entityData));
        StringTokenizer st;

        String line;
        while((line = br.readLine()) != null){
            st = new StringTokenizer(line);

            String type = st.nextToken();
            if(type.equals("Player")){
                player.setDefaultValues(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                entities.add(player);
            }
            if(type.equals("Button")){
                Button button = new Button(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                entities.add(button);
                type = st.nextToken();
                if(type.equals("Action")){
                    ActionPlatform action = new ActionPlatform(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()), st.nextToken(), Integer.parseInt(st.nextToken()));
                    entities.add(action);
                    button.addAction(action);
                }else if(type.equals("Previous")){
                    button.addAction((ActionPlatform)entities.get(entities.size()-2));
                }
            }
            if(type.equals("Block")){
                entities.add(new Block(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            if(type.equals("Oneway")){
                entities.add(new OnewayPlatform(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), st.nextToken()));
            }
            if(type.equals("Ball")){
                entities.add(new ThrowableObject(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), keyHandler));
            }
        }

    }

    public void draw(Graphics2D g2d){
        for(int i=entities.size()-1; i>=0; i--){
            entities.get(i).draw(g2d);
        }
    }

    public void checkEntityCollision(CollisionManager collider){
        for(int i=0; i<entities.size(); i++){
            collider.checkTile(entities.get(i), entities, i);

        }
    }

    public void update(){
        for(int i=0; i<entities.size(); i++){
            entities.get(i).update();
        }
    }

}
