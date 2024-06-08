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
        int[] x = new int[]{26, 27, 26, 27, 29, 29, 29, 29, 29, 29, 20, 21, 22, 20, 21, 22, 20, 21, 22, 20, 21, 22, 20, 21, 22, 20, 21, 22, 45, 45, 45, 45, 35, 35, 35, 35, 35, 35};
        int[] y = new int[]{14, 14, 12, 12, 8, 9, 10, 11, 12, 13, 20, 20, 20, 21, 21, 21, 22, 22, 22, 24, 24, 24, 25, 25, 25, 26, 26, 26, 15, 16, 29, 28, 40, 41, 42, 43, 44, 45};
        // position of the enemy
        for (int i = 0; i < x.length; i++) {
            mp.enemy[i] = new ENinja(mp);
            mp.enemy[i].set_worldX((int) (x[i] * mp.getOriginalTileSize() * 3));
            mp.enemy[i].set_worldY((int) (y[i] * mp.getOriginalTileSize() * 3));
        }
    }
}
