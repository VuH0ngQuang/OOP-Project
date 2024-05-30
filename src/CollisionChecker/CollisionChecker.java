package CollisionChecker;

import Entity.Entity;
import GamePanel.MyPanel;

public class CollisionChecker {
    MyPanel mp;

    public CollisionChecker(MyPanel mp) {
        this.mp = mp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.get_worldX() + entity.solidArea.x;
        int entityRightWorldX = entity.get_worldX() + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.get_worldY() + entity.solidArea.y;
        int entityBottomWorldY = entity.get_worldY() + entity.solidArea.y + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX / (mp.getOriginalTileSize() * 3);
        int entityRightCol = entityRightWorldX / (mp.getOriginalTileSize() * 3);
        int entityTopRow = entityTopWorldY / (mp.getOriginalTileSize() * 3);
        int entityBottomRow = entityBottomWorldY / (mp.getOriginalTileSize() * 3);
        int tileNum1, tileNum2;

        switch (entity.get_direction()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.get_speed()) / (mp.getOriginalTileSize() * 3);
                tileNum1 = mp.titleManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = mp.titleManager.mapTileNum[entityRightCol][entityTopRow];
                if (mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision) {
                    entity.collisionOn = true;
                } else
                    entity.collisionOn = false;
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.get_speed()) / (mp.getOriginalTileSize() * 3);
                tileNum1 = mp.titleManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = mp.titleManager.mapTileNum[entityRightCol][entityBottomRow];
                if (mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.get_speed()) / (mp.getOriginalTileSize() * 3);
                tileNum1 = mp.titleManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = mp.titleManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.get_speed()) / (mp.getOriginalTileSize() * 3);
                tileNum1 = mp.titleManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = mp.titleManager.mapTileNum[entityRightCol][entityBottomRow];
                if (mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            // case "stand":
            // break;
        }
    }
    public int checkObject(Entity entity, boolean player){
        int index = 999; // index of the object is unique
        for(int i = 0; i <  mp.obj.length; i++){
            if(mp.obj[i] != null){
                //get entity's solid area position
                entity.solidArea.x = entity.get_worldX() + entity.solidArea.x;
                entity.solidArea.y = entity.get_worldY() + entity.solidArea.y;

                //get the object's solid area position
                mp.obj[i].solidArea.x = mp.obj[i].worldX + mp.obj[i].solidArea.x;
                mp.obj[i].solidArea.y = mp.obj[i].worldY + mp.obj[i].solidArea.y;

                switch (entity.get_direction()){
                    case "up":
                        entity.solidArea.y -= entity.get_speed();
                        if(entity.solidArea.intersects(mp.obj[i].solidArea)){
                            if(mp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;

                    case "down":
                        entity.solidArea.y += entity.get_speed();
                        if(entity.solidArea.intersects(mp.obj[i].solidArea)){
                            if(mp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;

                    case "left":
                        entity.solidArea.x -= entity.get_speed();
                        if(entity.solidArea.intersects(mp.obj[i].solidArea)){
                            if(mp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;

                    case "right":
                        entity.solidArea.x += entity.get_speed();
                        if(entity.solidArea.intersects(mp.obj[i].solidArea)){
                            if(mp.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                mp.obj[i].solidArea.x = mp.obj[i].solidAreaDefaultX;
                mp.obj[i].solidArea.y = mp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
