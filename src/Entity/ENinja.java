package Entity;

import GamePanel.MyPanel;


import java.awt.*;

import java.util.Random;

public class ENinja extends Entity{
public ENinja(MyPanel mp) {
        super(mp);
        name = "ENinja";
        set_speed(1);
        maxLife = 4;
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
    public void getImage(){
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
    public void setAction(){
        System.out.println("setAction called"); // Debug print statement
        actionLockCounter++;
        System.out.println(actionLockCounter);
        if(actionLockCounter == 120){
            System.out.println("actionLockCounter: " + actionLockCounter); // Debug print statement
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if(i <= 25){
                set_direction("up");
            } else if(i <= 50){
                set_direction("down");
            } else if(i <= 75){
                set_direction("left");
            } else {
                set_direction("right");
            }
            System.out.println("Direction set to: " + get_direction()); // Debug print statement
            actionLockCounter = 0;
        }
    }
}
