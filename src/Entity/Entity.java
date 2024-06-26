package Entity;

import GamePanel.MyPanel;
import UtilityTool.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Entity {
     public int worldX, worldY, speed, height, width, spriteNum = 1, spriteCounter = 0, con = 1;
     private BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
     public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackDown3, attackDown4, attackLeft1,
               attackLeft2, attackLeft3, attackLeft4, attackRight1, attackRight2, attackRight3, attackRight4, image;
     private String direction;
     public Rectangle solidArea;
     public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
     public int solidAreaDefaultX, solidAreaDefaultY, actionLockCounter = 0;
     public boolean collisionOn = false;
     public boolean invincible = false;
     public boolean alive = true;
     public boolean dying = false;
     boolean attacking = false;
     public boolean contactPlayer = false;
     public int invincibleCounter = 0;
     int dyingCounter = 0;
     public int attack = 0;
     public int shotAvailableCounter = 0;
     public int maxMana;
     public int mana;
     public Projectile projectile;
     public int useCost;
     public int type; // 0 = player 1 = enemy
     public boolean onPath = false;

     public int getXdistance(Entity target) {
          int xDistance = Math.abs(worldX - target.worldX);
          return xDistance;
     }

     public int getYdistance(Entity target) {
          int yDistance = Math.abs(worldY - target.worldY);
          return yDistance;
     }

     public int getTileDistance(Entity target) {
          int tileDistance = (getXdistance(target) + getYdistance(target) / mp.tileSize);
          return tileDistance;
     }

     public int getGoalCol(Entity target) {
          int goalCol = (target.worldX + target.solidArea.x) / mp.tileSize;
          return goalCol;
     }

     public int getGoalRow(Entity target) {
          int goalRow = (target.worldY + target.solidArea.y) / mp.tileSize;
          return goalRow;
     }

     public BufferedImage setup(String pathImage) {
          UtilityTool utilityTool = new UtilityTool();
          BufferedImage image = null;
          try {
               // System.out.println(pathImage);
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

     public void checkCollision() {
          collisionOn = false;
          mp.collisionChecker.checkTile(this);
          mp.collisionChecker.checkObject(this, false);
          mp.collisionChecker.checkEntity(this, mp.enemy);
          contactPlayer = mp.collisionChecker.checkPlayer(this);
     }

     public void draw(Graphics2D g2) {
          BufferedImage image = null;
          int screenX = worldX - mp.player.get_worldX() + mp.player.getScreenX();
          int screenY = worldY - mp.player.get_worldY() + mp.player.getScreenY();
          if (worldX > mp.player.get_worldX() - mp.player.screenX &&
                    worldX < mp.player.get_worldX() + mp.player.screenX &&
                    worldY > mp.player.get_worldY() - mp.player.screenY &&
                    worldY < mp.player.get_worldY() + mp.player.screenY) {
               switch (get_direction()) {
                    case "up":
                         if (get_spriteNum() == 1) {
                              image = get_up1();
                         }
                         if (get_spriteNum() == 2) {
                              image = get_up2();
                         }
                         break;

                    case "down":
                         if (get_spriteNum() == 1) {
                              image = get_down1();
                         }
                         if (get_spriteNum() == 2) {
                              image = get_down2();
                         }
                         break;

                    case "left":
                         if (get_spriteNum() == 1) {
                              image = get_left1();
                         }
                         if (get_spriteNum() == 2) {
                              image = get_left2();
                         }
                         break;

                    case "right":
                         if (get_spriteNum() == 1) {
                              image = get_right1();
                         }
                         if (get_spriteNum() == 2) {
                              image = get_right2();
                         }
                         break;

                    // case "stand":
                    // image = get_down1();
                    // break;

                    default:
                         break;
               }
               if (invincible == true) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
               }
               if (dying == true) {
                    dyingAnimation(g2);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
               }
               g2.drawImage(image, screenX, screenY, mp.getOriginalTileSize() * 3,  mp.getOriginalTileSize() * 3, null);

               g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
          }
     }

     public void dyingAnimation(Graphics2D g2) {
          dyingCounter++;
          if (dyingCounter <= 5) {
               changeAlpha(g2, 0f);
          }
          if (dyingCounter > 5 && dyingCounter <= 10) {
               changeAlpha(g2, 1f);
          }
          if (dyingCounter > 10 && dyingCounter <= 15) {
               changeAlpha(g2, 0f);
          }
          if (dyingCounter > 15 && dyingCounter <= 20) {
               changeAlpha(g2, 1f);
          }
          if (dyingCounter > 20 && dyingCounter <= 25) {
               changeAlpha(g2, 0f);
          }
          if (dyingCounter > 25 && dyingCounter <= 30) {
               changeAlpha(g2, 1f);
          }
          if (dyingCounter > 30 && dyingCounter <= 35) {
               changeAlpha(g2, 0f);
          }
          if (dyingCounter > 35 && dyingCounter <= 40) {
               changeAlpha(g2, 1f);
          }
          if (dyingCounter > 40) {
               dying = false;
               alive = false;
          }
     }

     public void changeAlpha(Graphics2D g2, float alphaValue) {
          g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
     }

     public void setAction() {
     };

     public void update() {
          checkCollision();
          setAction();
          boolean contactPlayer = mp.collisionChecker.checkPlayer(this);
         //check collision dart with obj
         if (projectile != null){
             mp.collisionChecker.checkObject(projectile, false);
         }
          if (this.type == 1 && contactPlayer) {
               if (mp.player.invincible == false) {
                    mp.player.life--;
                    mp.player.invincible = true;
               }
          }
          if (get_direction() != null) {
               mp.collisionChecker.checkTile(this);
               // IF COLLISION IS FALSE, ENEMY CAN MOVE
               // System.out.println(collisionOn);
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
               if (invincible == true) {
                    invincibleCounter++;
                    if (invincibleCounter > 40) {
                         invincible = false;
                         invincibleCounter = 0;
                    }
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

     public void checkStartChasingOrNot(Entity target, int distance) {
          if (getTileDistance(target) < (distance * mp.tileSize)) {

               onPath = true;

          }
     }

     public void checkStopChasingOrNot(Entity target, int distance) {
          if (getTileDistance(target) > (distance * mp.tileSize)) {

               onPath = false;

          }
     }

    public void drawOBJ(Graphics2D g2, MyPanel mp) {
        int screenX = worldX - mp.player.get_worldX() + mp.player.getScreenX();
        int screenY = worldY - mp.player.get_worldY() + mp.player.getScreenY();
        if (get_spriteNum() == 1 || get_spriteNum() == 2) {
            if (con == 3)
                con = 1;
            else
                con++;
        }
        switch (get_direction()) {
            case "up":
                if (con == 1) {
                    image = get_up1();
                    // System.out.println("1");
                }
                if (con == 2) {
                    image = get_up2();
                    // System.out.println("2");
                }
                if (con == 3) {
                    image = get_up3();
                    // System.out.println("3");
                }
                break;

            case "down":
                if (con == 1) {
                    image = get_down1();
                }
                if (con == 2) {
                    image = get_down2();
                }
                if (con == 3) {
                    image = get_down3();
                }
                break;

            case "left":
                if (con == 1) {
                    image = get_left1();
                }
                if (con == 2) {
                    image = get_left2();
                }
                if (con == 3) {
                    image = get_left3();
                }
                break;

            case "right":
                if (con == 1) {
                    image = get_right1();
                }
                if (con == 2) {
                    image = get_right2();
                }
                if (con == 3) {
                    image = get_right3();
                }
                break;

            // case "stand":
            // image = get_down1();
            // break;

            default:
                break;
        }
        g2.drawImage(image, screenX, screenY, mp.getOriginalTileSize() * 3, mp.getOriginalTileSize() * 3, null);
    }

    public void getRandomDirection() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            // System.out.println("actionLockCounter: " + actionLockCounter); // Debug print
            // statement
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                set_direction("up");
            } else if (i <= 50) {
                set_direction("down");
            } else if (i <= 75) {
                set_direction("left");
            } else {
                set_direction("right");
            }
            // System.out.println("Direction set to: " + get_direction()); // Debug print
            // statement
            actionLockCounter = 0;
        }
    }

    // public void checkAttackOrNot(int rate, int straight, int horizontal) {
    // boolean targetInRange = false;
    // int xDis = getXdistance(mp.player);
    // int yDis = getYdistance(mp.player);

    // switch (direction) {
    // case "up":
    // if (mp.player.worldY < worldY && yDis < straight && xDis < horizontal){
    // targetInRange = true;
    // }
    // break;
    // case "down":
    // if (mp.player.worldY > worldY && yDis < straight && xDis < horizontal){
    // targetInRange = true;
    // }
    // break;
    // case "left":
    // if (mp.player.worldX < worldX && xDis < straight && yDis < horizontal){
    // targetInRange = true;
    // }
    // break;
    // case "right":
    // if (mp.player.worldX > worldX && xDis < straight && yDis < horizontal){
    // targetInRange = true;
    // }
    // break;
    // }
    // if(targetInRange == true){

    // int i = new Random().nextInt(rate);
    // }
    // }

     public void searchPath(int goalCol, int goalRow) {
          int startCol = (worldX + solidArea.x) / mp.tileSize;
          int startRow = (worldY + solidArea.y) / mp.tileSize;

          mp.pathFinder.setNodes(startCol, startRow, goalCol, goalRow);

          if (mp.pathFinder.search() == true) {
               // Next worldX & worldY
               int nextX = mp.pathFinder.pathList.get(0).col * mp.tileSize;
               int nextY = mp.pathFinder.pathList.get(0).row * mp.tileSize;

               // Entity's solidArea position
               int enLeftX = worldX + solidArea.x;
               int enRightX = worldX + solidArea.x + solidArea.width;
               int enTopY = worldY + solidArea.y;
               int enBottomY = worldY + solidArea.y + solidArea.height;

               if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + mp.tileSize) {
                    direction = "up";
               } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + mp.tileSize) {
                    direction = "down";
               } else if (enTopY >= nextY && enBottomY < nextY + mp.tileSize) {
                    // left or right
                    if (enLeftX > nextX) {
                         direction = "left";
                    }
                    if (enLeftX < nextX) {
                         direction = "right";
                    }
               } else if (enTopY > nextY && enLeftX > nextX) {
                    // up or left
                    direction = "up";
                    checkCollision();
                    if (collisionOn == true) {
                         direction = "left";
                    }
               } else if (enTopY > nextY && enLeftX < nextX) {
                    // up or right
                    direction = "up";
                    checkCollision();
                    if (collisionOn == true) {
                         direction = "right";
                    }

               } else if (enTopY < nextY && enLeftX > nextX) {
                    // down or left
                    direction = "down";
                    checkCollision();
                    if (collisionOn == true) {
                         direction = "left";
                    }
               } else if (enTopY < nextY && enLeftX < nextX) {
                    // down or right
                    direction = "down";
                    checkCollision();
                    if (collisionOn == true) {
                         direction = "right";
                    }

               }

               // If reaches the goal, stop the search
               // int nextCol = mp.pathFinder.pathList.get(0).col;
               // int nextRow = mp.pathFinder.pathList.get(0).row;
               // if (nextCol == goalCol && nextRow == goalRow) {
               // onPath = false;
               // }
          }
     }

     public MyPanel mp;

     public Entity(MyPanel mp) {
          this.mp = mp;
     }

     public String name;
     // CHARACTER STATUS
     public int maxLife;
     public int life;

     // set and get
     public void set_worldX(int x) {
          this.worldX = x;
     }

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

     public BufferedImage get_up3() {
          return up3;
     }

     public void set_up3(BufferedImage up3) {
          this.up3 = up3;
     }

     public BufferedImage get_down3() {
          return down3;
     }

     public void set_down3(BufferedImage down3) {
          this.down3 = down3;
     }

     public BufferedImage get_left3() {
          return left3;
     }

     public void set_left3(BufferedImage left3) {
          this.left3 = left3;
     }

     public BufferedImage get_right3() {
          return right3;
     }

     public void set_right3(BufferedImage right3) {
          this.right3 = right3;
     }
}
