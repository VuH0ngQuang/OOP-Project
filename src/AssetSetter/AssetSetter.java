package AssetSetter;

import Entity.ENinja;
import Object.*;
import GamePanel.MyPanel;

public class AssetSetter {
    MyPanel mp;

    public AssetSetter(MyPanel mp) {
        this.mp = mp;
    }

    public void setObject() {
        // position of key
        mp.obj[0] = new OBJ_Key();
        mp.obj[0].worldX = (int) (48 * mp.getOriginalTileSize() * 3);
        mp.obj[0].worldY = (int) (1 * mp.getOriginalTileSize() * 3);

        mp.obj[1] = new OBJ_Key();
        mp.obj[1].worldX = (int) (48 * mp.getOriginalTileSize() * 3);
        mp.obj[1].worldY = (int) (48 * mp.getOriginalTileSize() * 3);

        // position of door
        mp.obj[2] = new OBJ_Door();
        mp.obj[2].worldX = (int) (30 * mp.getOriginalTileSize() * 3);
        mp.obj[2].worldY = (int) (15 * mp.getOriginalTileSize() * 3);

        mp.obj[3] = new OBJ_Door();
        mp.obj[3].worldX = (int) (40 * mp.getOriginalTileSize() * 3);
        mp.obj[3].worldY = (int) (25 * mp.getOriginalTileSize() * 3);

        // position of the chest
        mp.obj[4] = new OBJ_Chest();
        mp.obj[4].worldX = (int) (39 * mp.getOriginalTileSize() * 3);
        mp.obj[4].worldY = (int) (20 * mp.getOriginalTileSize() * 3);

        // position of the heart
        mp.obj[5] = new OBJ_Healing();
        mp.obj[5].worldX = (int) (48 * mp.getOriginalTileSize() * 3);
        mp.obj[5].worldY = (int) (40 * mp.getOriginalTileSize() * 3);

        mp.obj[6] = new OBJ_Healing();
        mp.obj[6].worldX = (int) (48 * mp.getOriginalTileSize() * 3);
        mp.obj[6].worldY = (int) (13 * mp.getOriginalTileSize() * 3);
    }

    public void setEnemy() {
        // position of the enemy
        mp.enemy[0] = new ENinja(mp);
        mp.enemy[0].set_worldX((int) (43 * mp.getOriginalTileSize() * 3));
        mp.enemy[0].set_worldY((int) (7 * mp.getOriginalTileSize() * 3));


    }
}
