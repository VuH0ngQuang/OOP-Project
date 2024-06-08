package CollisionChecker;

import Entity.Entity;
import GamePanel.MyPanel;

import java.util.ArrayList;

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
                }
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

            default:
                break;
            // case "stand":
            // break;
        }
    }
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i <  mp.obj.length; i++){
            if(mp.obj[i] != null){
                //get entity's solid area position
                entity.solidArea.x = entity.get_worldX() + entity.solidArea.x;
                entity.solidArea.y = entity.get_worldY() + entity.solidArea.y;

                // get the object's solid area position
                mp.obj[i].solidArea.x = mp.obj[i].worldX + mp.obj[i].solidArea.x;
                mp.obj[i].solidArea.y = mp.obj[i].worldY + mp.obj[i].solidArea.y;

                switch (entity.get_direction()) {
                    case "up":
                        entity.solidArea.y -= entity.get_speed();
                        if (entity.solidArea.intersects(mp.obj[i].solidArea)) {
                            if (mp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player)
                                index = i;
                        }
                        break;

                    case "down":
                        entity.solidArea.y += entity.get_speed();
                        if (entity.solidArea.intersects(mp.obj[i].solidArea)) {
                            if (mp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player)
                                index = i;
                        }
                        break;

                    case "left":
                        entity.solidArea.x -= entity.get_speed();
                        if (entity.solidArea.intersects(mp.obj[i].solidArea)) {
                            if (mp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player)
                                index = i;
                        }
                        break;

                    case "right":
                        entity.solidArea.x += entity.get_speed();
                        if (entity.solidArea.intersects(mp.obj[i].solidArea)) {
                            if (mp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player)
                                index = i;
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
    //ENEMY
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        for(int i = 0; i <  target.length; i++){
            if(target[i] != null){
                //get entity's solid area position
                entity.solidArea.x = entity.get_worldX() + entity.solidArea.x;
                entity.solidArea.y = entity.get_worldY() + entity.solidArea.y;

                // get the object's solid area position
                target[i].solidArea.x = target[i].get_worldX() + target[i].solidArea.x;
                target[i].solidArea.y = target[i].get_worldY() + target[i].solidArea.y;

                if (entity.get_direction() != null) {
                    switch (entity.get_direction()) {
                        case "up":
                            entity.solidArea.y -= entity.get_speed();
                            break;
                        case "down":
                            entity.solidArea.y += entity.get_speed();
                            break;
                        case "left":
                            entity.solidArea.x -= entity.get_speed();
                            break;
                        case "right":
                            entity.solidArea.x += entity.get_speed();
                            break;
                    }
                }
                if (entity.solidArea.intersects(target[i].solidArea)) {
                    if (target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;
        // get entity's solid area position
        entity.solidArea.x = entity.get_worldX() + entity.solidArea.x;
        entity.solidArea.y = entity.get_worldY() + entity.solidArea.y;

        // get the object's solid area position
        mp.player.solidArea.x = mp.player.get_worldX() + mp.player.solidArea.x;
        mp.player.solidArea.y = mp.player.get_worldY() + mp.player.solidArea.y;

        if (entity.get_direction() != null) {
            switch (entity.get_direction()) {
                case "up":
                    entity.solidArea.y -= entity.get_speed();
                    break;
                case "down":
                    entity.solidArea.y += entity.get_speed();
                    break;
                case "left":
                    entity.solidArea.x -= entity.get_speed();
                    break;
                case "right":
                    entity.solidArea.x += entity.get_speed();
                    break;
            }
        }
        if (entity.solidArea.intersects(mp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        mp.player.solidArea.x = mp.player.solidAreaDefaultX;
        mp.player.solidArea.y = mp.player.solidAreaDefaultY;

        return contactPlayer;
    }
    public void objCollision(ArrayList<Entity> projectileList) {
        for (int i = 0; i < projectileList.size(); i++) {
            if (projectileList.get(i) != null) {
                int entityLeftWorldX = projectileList.get(i).get_worldX() + projectileList.get(i).solidArea.x;
                int entityRightWorldX = projectileList.get(i).get_worldX() + projectileList.get(i).solidArea.x + projectileList.get(i).solidArea.width;
                int entityTopWorldY = projectileList.get(i).get_worldY() + projectileList.get(i).solidArea.y;
                int entityBottomWorldY = projectileList.get(i).get_worldY() + projectileList.get(i).solidArea.y + projectileList.get(i).solidArea.height;
                int entityLeftCol = entityLeftWorldX / (mp.getOriginalTileSize() * 3);
                int entityRightCol = entityRightWorldX / (mp.getOriginalTileSize() * 3);
                int entityTopRow = entityTopWorldY / (mp.getOriginalTileSize() * 3);
                int entityBottomRow = entityBottomWorldY / (mp.getOriginalTileSize() * 3);
                int tileNum1, tileNum2;

                switch (projectileList.get(i).get_direction()) {
                    case "up":
                        entityTopRow = (entityTopWorldY - projectileList.get(i).get_speed()) / (mp.getOriginalTileSize() * 3);
                        tileNum1 = mp.titleManager.mapTileNum[entityLeftCol][entityTopRow];
                        tileNum2 = mp.titleManager.mapTileNum[entityRightCol][entityTopRow];
                        if (mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision) {
                            projectileList.get(i).alive = false;
                        }
                    case "down":
                        entityBottomRow = (entityBottomWorldY + projectileList.get(i).get_speed()) / (mp.getOriginalTileSize() * 3);
                        tileNum1 = mp.titleManager.mapTileNum[entityLeftCol][entityBottomRow];
                        tileNum2 = mp.titleManager.mapTileNum[entityRightCol][entityBottomRow];
                        if (mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision) {
                            projectileList.get(i).alive = false;
                        }
                        break;
                    case "left":
                        entityLeftCol = (entityLeftWorldX - projectileList.get(i).get_speed()) / (mp.getOriginalTileSize() * 3);
                        tileNum1 = mp.titleManager.mapTileNum[entityLeftCol][entityTopRow];
                        tileNum2 = mp.titleManager.mapTileNum[entityLeftCol][entityBottomRow];
                        if (mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision) {
                            projectileList.get(i).alive = false;
                        }
                        break;
                    case "right":
                        entityRightCol = (entityRightWorldX + projectileList.get(i).get_speed()) / (mp.getOriginalTileSize() * 3);
                        tileNum1 = mp.titleManager.mapTileNum[entityRightCol][entityTopRow];
                        tileNum2 = mp.titleManager.mapTileNum[entityRightCol][entityBottomRow];
                        if (mp.titleManager.title[tileNum1].collision || mp.titleManager.title[tileNum2].collision) {
                            projectileList.get(i).alive = false;
                        }
                        break;
                }
            }
        }
    }
}
