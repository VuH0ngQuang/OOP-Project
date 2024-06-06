package Entity;

import GamePanel.MyPanel;


import java.awt.*;

import java.util.Random;

public class ENinja extends Entity {
    public ENinja(MyPanel mp) {
        super(mp);
        type = 1;
        name = "ENinja";
        set_speed(1);
        maxLife = 1;
        life = maxLife;
        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 28;

        getImage();
    }

    public void getImage() {
        set_up1(setup("/Enemy/ninja2_up_1.png"));
        set_up2(setup("/Enemy/ninja2_up_2.png"));
        set_down1(setup("/Enemy/ninja2_down_1.png"));
        set_down2(setup("/Enemy/ninja2_down_2.png"));
        set_left1(setup("/Enemy/ninja2_left_1.png"));
        set_left2(setup("/Enemy/ninja2_left_2.png"));
        set_right1(setup("/Enemy/ninja2_right_1.png"));
        set_right2(setup("/Enemy/ninja2_right_2.png"));
    }


    @Override
    public void setAction() {
//        int xDistance = Math.abs(worldX - mp.player.worldX);
//        int yDistance = Math.abs(worldY - mp.player.worldY);
//        int tileDistance = (xDistance + yDistance) / mp.tileSize;
        if (onPath == true) {
            //check if it stops chasing
            checkStopChasingOrNot(mp.player, 15, 100);
//            if (tileDistance > 20) {
//                onPath = false;
//            }
            //search the direction to go
//            int goalCol = (mp.player.worldX + mp.player.solidArea.x)/mp.tileSize;
//            int goalRow = (mp.player.worldY + mp.player.solidArea.y)/mp.tileSize;
            searchPath(getGoalCol(mp.player), getGoalRow(mp.player));

        } else {
            //check if it starts chasing
            checkStartChasingOrNot(mp.player, 5, 100);
//            if(onPath == true && tileDistance > 20){
//                onPath = false;
            // Get a random direction
            getRandomDirection();
        }
    }
}
