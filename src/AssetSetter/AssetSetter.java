package AssetSetter;

import Object.*;
import GamePanel.MyPanel;

public class AssetSetter {
    MyPanel mp;
    public AssetSetter(MyPanel mp){
        this.mp = mp;
    }
    public void setObject(){
        // position of key 1
        mp.obj[0] = new OBJ_Key();
        mp.obj[0].worldX = 48 * mp.getOriginalTileSize()*3;
        mp.obj[0].worldY = 48 * mp.getOriginalTileSize()*3;

        //position of key 2
        mp.obj[1] = new OBJ_Key();
        mp.obj[1].worldX = 2 * mp.getOriginalTileSize()*3;
        mp.obj[1].worldY = 48 * mp.getOriginalTileSize()*3;
    }
}
