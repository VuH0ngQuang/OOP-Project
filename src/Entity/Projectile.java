package Entity;

import GamePanel.MyPanel;

import java.awt.*;

public class Projectile extends Entity {
    Entity user;

    public Projectile(MyPanel mp) {
        super(mp);
    }

    public void set(int worldX, int worldY, String dir, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        set_direction(dir);
        this.alive = alive;
        this.user = user;
        this.life = maxLife;
        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 15;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 21;
        solidArea.height = 21;
    }

    public void update() {
        if (user == mp.player) {
            int enemyIndex = mp.collisionChecker.checkEntity(this, mp.enemy);
            if (enemyIndex != 999) {
                mp.player.damageMonster(enemyIndex);
                alive = false;
            }
        }
        //check collision dart with tile
        mp.collisionChecker.tileCollision(mp.projectileList);
        //check collision dart with obj
        mp.collisionChecker.objCollision(mp.projectileList);
        if (user != mp.player) {

        }
        switch (get_direction()) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }
        life--;
        if (life <= 0) {
            alive = false;
        }
        set_spriteCounter(get_spriteCounter() + 1);
        if (get_spriteCounter() > 12) {
            if (get_spriteNum() == 1) {
                set_spriteNum(2);
            } else if (get_spriteNum() == 2) {
                set_spriteNum(1);
            }
            set_spriteCounter(0);
        }
    }
}
