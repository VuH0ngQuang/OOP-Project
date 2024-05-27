package CollisionChecker;

import Entity.Entity;
import GamePanel.MyPanel;

public class CollisionChecker {
    MyPanel mp;
    public CollisionChecker(MyPanel mp){
        this.mp = mp;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.get_worldX() + entity.solidArea.x;
        int entityRightWorldX = entity.get_worldX() + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.get_worldY() + entity.solidArea.y;
        int entityBottomWorldY = entity.get_worldY() + entity.solidArea.y + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX/(mp.getOriginalTileSize() * 3);
        int entityRightCol = entityRightWorldX/(mp.getOriginalTileSize() * 3);
        int entityTopRow = entityTopWorldY/(mp.getOriginalTileSize() * 3);
        int entityBottomRow = entityBottomWorldY/(mp.getOriginalTileSize() * 3);
        int tileNum1, tileNum2;

        switch (entity.get_direction()){
            case "up":
                entityTopRow = (entityTopWorldY - entity.get_speed())/(mp.getOriginalTileSize()*3);
                tileNum1 = mp.titleManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = mp.titleManager.mapTileNum[entityRightCol][entityTopRow ];
                if(mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityTopWorldY - entity.get_speed())/(mp.getOriginalTileSize()*3);
                tileNum1 = mp.titleManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = mp.titleManager.mapTileNum[entityRightCol][entityTopRow ];
                if(mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityTopWorldY - entity.get_speed())/(mp.getOriginalTileSize()*3);
                tileNum1 = mp.titleManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = mp.titleManager.mapTileNum[entityRightCol][entityTopRow ];
                if(mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityTopWorldY - entity.get_speed())/(mp.getOriginalTileSize()*3);
                tileNum1 = mp.titleManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = mp.titleManager.mapTileNum[entityRightCol][entityTopRow ];
                if(mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
