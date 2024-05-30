package Object;

import GamePanel.MyPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48 ,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, MyPanel mp){
        int screenX = worldX - mp.player.get_worldX() + mp.player.getScreenX();
        int screenY = worldY - mp.player.get_worldY() + mp.player.getScreenY();
        g2.drawImage(image, screenX, screenY, mp.getOriginalTileSize() * 3,  mp.getOriginalTileSize() * 3, null);
    }
}
