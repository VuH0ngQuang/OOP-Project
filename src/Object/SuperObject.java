package Object;

import GamePanel.MyPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, MyPanel mp){
        int screenX = worldX - mp.player.get_worldX() + mp.player.getScreenX();
        int screenY = worldY - mp.player.get_worldY() + mp.player.getScreenY();

        if (worldX + mp.getOriginalTileSize() * 3 > mp.player.get_worldX() - mp.player.screenX &&
                worldX - mp.getOriginalTileSize() * 3 < mp.player.get_worldX() + mp.player.screenX &&
                worldY + mp.getOriginalTileSize() * 3 > mp.player.get_worldY() - mp.player.screenY &&
                worldY - mp.getOriginalTileSize() * 3 < mp.player.get_worldY() + mp.player.screenY) {
            g2.drawImage(image, screenX, screenY, mp.getOriginalTileSize() * 3,  mp.getOriginalTileSize() * 3, null);
        }
    }
}
