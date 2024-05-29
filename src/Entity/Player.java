package Entity;

import GamePanel.*;
import KeyControl.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {
     // object create
     MyPanel myPanel;
     KeyMoving keyMoving;

     public final int screenX;
     public final int screenY;

     int hasKey = 0;

     // contructol
     public Player(MyPanel myPanel, KeyMoving keyMoving, int x, int y, int speed, int height, int width) {
          super(myPanel.getOriginalTileSize() * 3 * x, myPanel.getOriginalTileSize() * 3 * y, speed,
                    myPanel.getOriginalTileSize() * height, myPanel.getOriginalTileSize() * width);
          this.myPanel = myPanel;
          this.keyMoving = keyMoving;
          // set camera at the center of the screen
          screenX = myPanel.getScreenWidth() / 2 - (myPanel.getOriginalTileSize() * 3 / 2);
          screenY = myPanel.getScreenHeight() / 2 - (myPanel.getOriginalTileSize() * 3 / 2);
          solidArea = new Rectangle();
          solidArea.x = 12;
          solidArea.y = 20;
          solidAreaDefaultX = solidArea.x;
          solidAreaDefaultY = solidArea.y;
          solidArea.width = 24;
          solidArea.height = 28;
          getPlayerImage();
          set_direction("down");
     }

     // update value
     public void update() {
          if (keyMoving.getRight() == true || keyMoving.getLeft() == true ||
                    keyMoving.getUp() == true
                    || keyMoving.getDown() == true) {
               if (keyMoving.getUp() == true) {
                    set_direction("up");
               }

               if (keyMoving.getDown() == true) {
                    set_direction("down");
               }

               if (keyMoving.getLeft() == true) {
                    set_direction("left");
               }

               if (keyMoving.getRight() == true) {
                    set_direction("right");
               }

               // Make it always face out of the screen when the button is not pressed
               // if (keyMoving.getRight() == false && keyMoving.getLeft() == false &&
               // keyMoving.getUp() == false
               // && keyMoving.getDown() == false) {
               // set_direction("stand");
               // }
               // //CHECK TILE COLLISION
               collisionOn = false;
               myPanel.collisionChecker.checkTile(this);

               // check object collision
               int objIndex = myPanel.collisionChecker.checkObject(this, true);
               pickUpObject(objIndex);

               // IF COLLISION IS FALSE, PLAYER CAN MOVE
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

     }

     public void pickUpObject(int i) {
          if (i != 999) {
               String objectName = myPanel.obj[i].name;

               switch (objectName) {
                    case "Key":
                         hasKey++;
                         myPanel.obj[i] = null;
                         System.out.println("Key:" + hasKey);
                         break;

                    case "Door":
                         if (hasKey > 0) {
                              myPanel.obj[i] = null;
                              hasKey--;
                              System.out.println("Key:" + hasKey);
                         } else
                              System.out.println("Key:" + hasKey);
                         break;

                    case "Chest":
                         myPanel.obj[i] = null;
                         break;

                    case "Hear":
                         myPanel.obj[i] = null;
                         break;
               }
          }
     }

     // draw graphics
     public void draw(Graphics2D g2) {
          // g.setColor(Color.white);
          // g.fillRect(get_worldX(), get_worldY(), get_width(), get_height());

          BufferedImage image = null;

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

          g2.drawImage(image, screenX, screenY, get_width(), get_height(), myPanel);
     }

     // image input
     public void getPlayerImage() {
          try {
               set_up1(ImageIO.read(getClass().getResourceAsStream("/Player/ninja_up_1.png")));
               set_up2(ImageIO.read(getClass().getResourceAsStream("/Player/ninja_up_2.png")));
               set_down1(ImageIO.read(getClass().getResourceAsStream("/Player/ninja_down_1.png")));
               set_down2(ImageIO.read(getClass().getResourceAsStream("/Player/ninja_down_2.png")));
               set_left1(ImageIO.read(getClass().getResourceAsStream("/Player/ninja_left_1.png")));
               set_left2(ImageIO.read(getClass().getResourceAsStream("/Player/ninja_left_2.png")));
               set_right1(ImageIO.read(getClass().getResourceAsStream("/Player/ninja_right_1.png")));
               set_right2(ImageIO.read(getClass().getResourceAsStream("/Player/ninja_right_2.png")));

          } catch (IOException e) {
               e.printStackTrace();
          }
     }

     public int getScreenX() {
          return screenX;
     }

     public int getScreenY() {
          return screenY;
     }
}
