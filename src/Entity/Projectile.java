package Entity;

import GamePanel.MyPanel;

public class Projectile extends Entity{
    Entity user;
    public Projectile(MyPanel mp){
        super(mp);
    }
    public void set(int worldX, int worldY, String dir, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        set_direction(dir);
        this.alive = alive;
        this.user = user;
        this.life = maxLife;
    }
    public void update(){
        switch (get_direction()){
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
        life --;
        if (life <= 0){
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
