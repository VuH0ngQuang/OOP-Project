package Entity;

import GamePanel.MyPanel;
import UtilityTool.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Entity {
     private int worldX, worldY, speed, height, width, spriteNum = 1, spriteCounter = 0;
     private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
     private String direction;
     public Rectangle solidArea;
     public int solidAreaDefaultX, solidAreaDefaultY, actionLockCounter;
     public boolean collisionOn = false;
     public BufferedImage setup (String pathImage) {
          UtilityTool utilityTool = new UtilityTool();
          BufferedImage image = null;
          try {
//               System.out.println(pathImage);
               image = ImageIO.read(getClass().getResourceAsStream(pathImage));
               image = utilityTool.scaleImage(image, 48, 48);
          } catch (IOException e) {
               e.printStackTrace();
          }
          return image;
     }
     public Entity(int x, int y, int speed, int height, int width) {
          this.worldX = x;
          this.worldY = y;
          this.speed = speed;
          this.width = width;
          this.height = height;
     }
     public void setAction(){};
     public void update(){
          setAction();
          collisionOn = false;
          if (get_direction() == null) {
               // Handle the case where direction is null
               System.out.println("Direction is null");
          }
          mp.collisionChecker.checkTile(this);
               // IF COLLISION IS FALSE, ENEMY CAN MOVE
          if (collisionOn == false) {
               switch (get_direction()) {
                    case "up":
                         set_worldY(get_worldY() - get_speed());
                         break;
                    case "down":
                         set_worldY(get_worldY() + get_speed());
                         break;
                    case "left":
                         set_worldX(get_worldX() - get_speed());
                         break;
                    case "right":
                         set_worldX(get_worldX() + get_speed());
                         break;
                    // case "stand":
                    // break;
               }
          }
          // Changes the displayed image every 12 frames
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
     MyPanel mp;
     public Entity(MyPanel mp){
          this.mp = mp;
     }
     public String name;
     //CHARACTER STATUS
     public int maxLife;
     public int life;

     // set and get
     public void set_worldX(int x) { this.worldX = x; }

     public int get_worldX() {
          return worldX;
     }

     public void set_worldY(int y) {
          this.worldY = y;
     }

     public int get_worldY() {
          return worldY;
     }

     public void set_speed(int speed) {
          this.speed = speed;
     }

     public int get_speed() {
          return speed;
     }

     public void set_height(int height) {
          this.height = height;
     }

     public int get_height() {
          return height;
     }

     public void set_width(int width) {
          this.width = width;
     }

     public int get_width() {
          return width;
     }

     public void set_direction(String direction) {
          this.direction = direction;
     }

     public String get_direction() {
          return direction;
     }

     public void set_up1(BufferedImage up1) {
          this.up1 = up1;
     }

     public BufferedImage get_up1() {
          return up1;
     }

     public void set_up2(BufferedImage up2) {
          this.up2 = up2;
     }

     public BufferedImage get_up2() {
          return up2;
     }

     public void set_down1(BufferedImage down1) {
          this.down1 = down1;
     }

     public BufferedImage get_down1() {
          return down1;
     }

     public void set_down2(BufferedImage down2) {
          this.down2 = down2;
     }

     public BufferedImage get_down2() {
          return down2;
     }

     public void set_left1(BufferedImage left1) {
          this.left1 = left1;
     }

     public BufferedImage get_left1() {
          return left1;
     }

     public void set_left2(BufferedImage left2) {
          this.left2 = left2;
     }

     public BufferedImage get_left2() {
          return left2;
     }

     public void set_right1(BufferedImage right1) {
          this.right1 = right1;
     }

     public BufferedImage get_right1() {
          return right1;
     }

     public void set_right2(BufferedImage right2) {
          this.right2 = right2;
     }

     public BufferedImage get_right2() {
          return right2;
     }

     public void set_spriteNum(int spriteNum) {
          this.spriteNum = spriteNum;
     }

     public int get_spriteNum() {
          return spriteNum;
     }

     public void set_spriteCounter(int spriteCounter) {
          this.spriteCounter = spriteCounter;
     }

     public int get_spriteCounter() {
          return spriteCounter;
     }

}
