package Object;

import Entity.Projectile;
import GamePanel.MyPanel;

public class OBJ_Dart extends Projectile {
    public OBJ_Dart(MyPanel mp) {
        super(mp);
        this.mp = mp;
        name = "Dart";
        speed = 20;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        set_up1(setup("/Dart/bluedart_left_1.png"));
        set_up2(setup("/Dart/bluedart_left_2.png"));
        set_up3(setup("/Dart/bluedart_left_3.png"));
        set_down1(setup("/Dart/bluedart_right_1.png"));
        set_down2(setup("/Dart/bluedart_right_2.png"));
        set_down3(setup("/Dart/bluedart_right_3.png"));
        set_left1(setup("/Dart/bluedart_left_1.png"));
        set_left2(setup("/Dart/bluedart_left_2.png"));
        set_left3(setup("/Dart/bluedart_left_3.png"));
        set_right1(setup("/Dart/bluedart_right_1.png"));
        set_right2(setup("/Dart/bluedart_right_2.png"));
        set_right3(setup("/Dart/bluedart_right_3.png"));
    }
}
