import javax.swing.Action;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;
import java.awt.*;
import java.util.ArrayList;

public class CollisionManager {
    private GamePanel panel;
    private TileManager map;

    private int entityLeft;
    private int entityRight;
    private int entityTop;
    private int entityBottom;

    public CollisionManager(GamePanel panel, TileManager map){
        this.panel = panel;
        this.map = map;
    }

    public void checkTile(Entity entity, ArrayList<Entity> objects, int skip){
        if(!(entity instanceof Player) && !(entity instanceof Block) && !(entity instanceof Button) && !(entity instanceof ThrowableObject)){
            return;
        }

        int speedX = entity.getSpeedX();
        int speedY = entity.getSpeedY();

        entity.setTop(0);
        entity.setBottom(0);
        entity.setLeft(0);
        entity.setRight(0);

        entityLeft = (int)(entity.getX()+1)/Const.SCALED_TILE_SIZE;
        entityRight =  (int)(entity.getX() + entity.getWidth()-1)/Const.SCALED_TILE_SIZE;
        entityTop = (int)(entity.getY()+speedY)/Const.SCALED_TILE_SIZE;
        entityBottom =  (int)(entity.getY()+speedY + entity.getHeight())/Const.SCALED_TILE_SIZE;

        for(int x=entityLeft; x<=entityRight; x++){

            if(map.getType(x, entityTop)==1 || map.getType(x, entityTop)==3){
                entity.setTop(1);
            }else if((map.getType(x, entityTop)==2 || map.getType(x, entityTop)== 4 || map.getType(x, entityTop)== 5) && entity.getTop()!=1){
                entity.setTop(2);
            }else if(map.getType(x, entityTop)==6 && entity.getTop()!=2){
                entity.setTop(3);
            }

            if(map.getType(x, entityBottom)==1 || map.getType(x, entityBottom)==3){
                entity.setBottom(1);
            }else if((map.getType(x, entityBottom)==2 || map.getType(x, entityBottom)== 4 || map.getType(x, entityBottom)== 5) && entity.getBottom()!=1){
                entity.setBottom(2);
            }else if(map.getType(x, entityBottom)==6 && entity.getBottom()!=2){
                entity.setBottom(3);
            }
            
        }

        entityLeft = (int)(entity.getX()+speedX)/Const.SCALED_TILE_SIZE;
        entityRight =  (int)(entity.getX()+speedX + entity.getWidth())/Const.SCALED_TILE_SIZE;
        entityTop = (int)(entity.getY()+1)/Const.SCALED_TILE_SIZE;
        entityBottom =  (int)(entity.getY() + entity.getHeight()-1)/Const.SCALED_TILE_SIZE;

        for(int y=entityTop; y<=entityBottom; y++){

            if(map.getType(entityLeft, y)==1 || map.getType(entityLeft, y)==3){
                entity.setLeft(1);
            }else if((map.getType(entityLeft, y)==2 || map.getType(entityLeft, y)== 4 || map.getType(entityLeft, y)== 5) && entity.getLeft()!=1){
                entity.setLeft(2);
            }else if(map.getType(entityLeft, y)==6 && entity.getLeft()!=2){
                entity.setLeft(3);
            }

            if(map.getType(entityRight, y)==1 || map.getType(entityRight, y)==3){
                entity.setRight(1);
            }else if((map.getType(entityRight, y)==2 || map.getType(entityRight, y)== 4 || map.getType(entityRight, y)== 5) && entity.getRight()!=1){
                entity.setRight(2);
            }else if(map.getType(entityRight, y)==6 && entity.getRight()!=2){
                entity.setRight(3);
            }
        }

        for(int i=0; i<objects.size(); i++){
            if(i!=skip){
                checkEntity(entity, objects.get(i));
            }
        }

    }
    
    public void checkEntity(Entity firstEntity, Entity secondEntity){
        Rectangle pseudoEntity = new Rectangle();
        pseudoEntity.setBounds((int)(firstEntity.getX()+1), (int)(firstEntity.getY()+firstEntity.getSpeedY()),
            (int)(firstEntity.getWidth()-2), (int)(firstEntity.getHeight()));

        if(pseudoEntity.intersects(secondEntity)){
            if(pseudoEntity.getY()<secondEntity.getY()){
                if(secondEntity instanceof ActionPlatform){
                    ActionPlatform platform = (ActionPlatform)secondEntity;
                    if(platform.getMoving()){
                        firstEntity.setSpeedX(firstEntity.getSpeedX()+platform.getDirectionX());
                        firstEntity.setSpeedY(firstEntity.getSpeedY()+platform.getDirectionY());
                    }
                    firstEntity.setBottom(1);
                }else if(secondEntity instanceof Block){
                    firstEntity.setBottom(1);
                }else if(secondEntity instanceof OnewayPlatform){
                    firstEntity.setBottom(((OnewayPlatform)secondEntity).checkDirection('U'));
                }
            }else{
                if(secondEntity instanceof ActionPlatform){
                    ActionPlatform platform = (ActionPlatform)secondEntity;
                    if(platform.getMoving()){
                        firstEntity.setSpeedX(firstEntity.getSpeedX()+platform.getDirectionX());
                        firstEntity.setSpeedY(firstEntity.getSpeedY()+platform.getDirectionY());
                    }
                    firstEntity.setTop(1);
                }else if(secondEntity instanceof Block){
                    firstEntity.setTop(1);
                }else if(secondEntity instanceof OnewayPlatform){
                    firstEntity.setTop(((OnewayPlatform)secondEntity).checkDirection('D'));
                }
            }
            if(firstEntity instanceof Button){
                firstEntity.setTop(1);
            }else if(secondEntity instanceof ThrowableObject && firstEntity instanceof Player){
                ((ThrowableObject)secondEntity).setTouchingPlayer((Player)firstEntity);
            }
            
        }

        pseudoEntity.setBounds((int)(firstEntity.getX()+firstEntity.getSpeedX()), (int)(firstEntity.getY()+1),
            (int)(firstEntity.getWidth()), (int)(firstEntity.getHeight()-2));

        if(pseudoEntity.intersects(secondEntity)){
            if(pseudoEntity.getX()<secondEntity.getX()){
                if(secondEntity instanceof ActionPlatform){
                    ActionPlatform platform = (ActionPlatform)secondEntity;
                    if(platform.getMoving()){
                        firstEntity.setSpeedX(firstEntity.getSpeedX()+platform.getDirectionX());
                        firstEntity.setSpeedY(firstEntity.getSpeedY()+platform.getDirectionY());
                    }
                    firstEntity.setRight(1);
                }else if(secondEntity instanceof Block){
                    firstEntity.setRight(1);
                    if(firstEntity instanceof Player){
                        secondEntity.setSpeedX(firstEntity.getSpeedX());
                    }
                }else if(secondEntity instanceof OnewayPlatform){
                    firstEntity.setRight(((OnewayPlatform)secondEntity).checkDirection('L'));
                }
            }else{
                if(secondEntity instanceof ActionPlatform){
                    ActionPlatform platform = (ActionPlatform)secondEntity;
                    if(platform.getMoving()){
                        firstEntity.setSpeedX(firstEntity.getSpeedX()+platform.getDirectionX());
                        firstEntity.setSpeedY(firstEntity.getSpeedY()+platform.getDirectionY());
                    }
                    firstEntity.setLeft(1);
                }else if(secondEntity instanceof Block){
                    firstEntity.setLeft(1);
                    if(firstEntity instanceof Player){
                        secondEntity.setSpeedX(firstEntity.getSpeedX());
                    }
                }else if(secondEntity instanceof OnewayPlatform){
                    firstEntity.setLeft(((OnewayPlatform)secondEntity).checkDirection('R'));
                }
            }
            if(firstEntity instanceof Button){
                firstEntity.setTop(1);
            }else if(secondEntity instanceof ThrowableObject && firstEntity instanceof Player){
                ((ThrowableObject)secondEntity).setTouchingPlayer((Player)firstEntity);
            }
        }

    }

}
