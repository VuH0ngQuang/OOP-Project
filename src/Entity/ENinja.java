package Entity;

import GamePanel.MyPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
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
        try {
            set_up1(ImageIO.read(getClass().getResourceAsStream("/Enemy/ninja2_up_1.png")));
            set_up2(ImageIO.read(getClass().getResourceAsStream("/Enemy/ninja2_up_2.png")));
            set_down1(ImageIO.read(getClass().getResourceAsStream("/Enemy/ninja2_down_1.png")));
            set_down2(ImageIO.read(getClass().getResourceAsStream("/Enemy/ninja2_down_2.png")));
            set_left1(ImageIO.read(getClass().getResourceAsStream("/Enemy/ninja2_left_1.png")));
            set_left2(ImageIO.read(getClass().getResourceAsStream("/Enemy/ninja2_left_2.png")));
            set_right1(ImageIO.read(getClass().getResourceAsStream("/Enemy/ninja2_right_1.png")));
            set_right2(ImageIO.read(getClass().getResourceAsStream("/Enemy/ninja2_right_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 120){
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
            actionLockCounter = 0;
        }
    }
}
