package Entity;

import GamePanel.*;
import KeyControl.*;
import Sound.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {
     // object create
     MyPanel myPanel;
     KeyMoving keyMoving;
     private boolean attacking = false;
     public final int screenX;
     public final int screenY;

     // sound SE
     Sound soundE = new Sound();

     public int hasKey = 0;

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
          attackArea.width = 36;
          attackArea.height = 36;
          projectile = new OBJ_Dart(myPanel);
          getPlayerImage();
          getPlayerAttackImage();
          set_direction("down");
          // PLAYER STATUS
          maxLife = 6;
          life = maxLife;

     }

     // update value
     public void update() {
          if (attacking == true) {
               attacking();
          } else if (keyMoving.getRight() == true || keyMoving.getLeft() == true ||
                    keyMoving.getUp() == true
                    || keyMoving.getDown() == true || keyMoving.getEnter() == true) {
               if (keyMoving.getEnter() == true) {
                    attacking = true;
               }

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

               // CHECK ENEMY COLLISION
               int enemyIndex = myPanel.collisionChecker.checkEntity(this, myPanel.enemy);
               contactEnemy(enemyIndex);

               // check object collision
               int objIndex = myPanel.collisionChecker.checkObject(this, true);
               pickUpObject(objIndex);

               // IF COLLISION IS FALSE, PLAYER CAN MOVE
               if (collisionOn == false && keyMoving.getEnter() == false) {

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
          if (keyMoving.getSpace() == true && projectile.alive == false && shotAvailableCounter == 120) {
               // SET DEFAULT COORDINATES, DIRECTION AND USE
               projectile.set(get_worldX(), get_worldY(), get_direction(), true, this);
               // ADD IT TO THE LIST
               myPanel.projectileList.add(projectile);
               // play SE
               soundE.setFile(1);
               soundE.play();

               shotAvailableCounter = 0;
          }
          // this shit needs to be outside of key if statement !
          if (invincible == true) {
               invincibleCounter++;
               if (invincibleCounter > 60) {
                    invincible = false;
                    invincibleCounter = 0;
               }
          }
          if (shotAvailableCounter < 120) {
               shotAvailableCounter++;
          }
          if (life <= 0) {
               // stop background music
               myPanel.stopMusic();

               keyMoving.setSpace(false);
               myPanel.gameState = myPanel.gameOverState;
          }
     }

     public void attacking() {
          spriteCounter++;
          if (spriteCounter <= 5) {
               spriteNum = 1;
          }
          if (spriteCounter > 5 && spriteCounter <= 25) {
               spriteNum = 2;
               // Save the current worldX, Y, solidArea
               int currentWorldX = worldX;
               int currentWorldY = worldY;
               int solidAreaWidth = solidArea.width;
               int solidAreaHeight = solidArea.height;

               // Adjust player worldX,Y for attackArea
               switch (get_direction()) {
                    case "up":
                         worldY -= attackArea.height;
                         break;
                    case "down":
                         worldY += attackArea.height;
                         break;
                    case "left":
                         worldX -= attackArea.width;
                         break;
                    case "right":
                         worldX += attackArea.width;
                         break;
               }
               solidArea.width = attackArea.width;
               solidArea.height = attackArea.height;

               int monsterIndex = myPanel.collisionChecker.checkEntity(this, myPanel.enemy);
               damageMonster(monsterIndex);

               worldX = currentWorldX;
               worldY = currentWorldY;
               solidArea.width = solidAreaWidth;
               solidArea.height = solidAreaHeight;
          }
          if (spriteCounter > 25) {
               spriteNum = 1;
               spriteCounter = 0;
               attacking = false;
          }
     }

     public void pickUpObject(int i) {
          if (i != 999) {
               String objectName = myPanel.obj[i].name;

               switch (objectName) {
                    case "Key":
                         hasKey++;
                         myPanel.obj[i] = null;
                         myPanel.ui.showMessage("a Key");
                         break;

                    case "Door":
                         if (hasKey > 0) {
                              myPanel.obj[i] = null;
                              hasKey--;
                              myPanel.ui.showMessage("open door");
                         } else
                              myPanel.ui.showMessage("need a Key");
                         break;

                    case "Chest":
                         // stop background music
                         myPanel.stopMusic();

                         // wining sound
                         myPanel.playMusic(2);

                         keyMoving.setSpace(false);
                         myPanel.gameState = myPanel.gameWinState;
                         break;

                    case "Hear":
                         myPanel.obj[i] = null;
                         myPanel.ui.showMessage("Healing");
                         life += 2;
                         if (life > maxLife) {
                              life = maxLife;
                         }
                         break;
               }
          }
     }

     public void contactEnemy(int i) {
          if (i != 999) {
               if (invincible == false) {
                    life -= 1;
                    invincible = true;
               }

          }
     }

     public void damageMonster(int i) {
          if (i != 999) {
               if (myPanel.enemy[i].invincible == false) {
                    myPanel.enemy[i].life -= 1;
                    myPanel.enemy[i].invincible = true;
                    if (myPanel.enemy[i].life <= 0) {
                         myPanel.enemy[i].dying = true;
                    }
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
                    if (attacking == false) {
                         if (get_spriteNum() == 1) {
                              image = get_up1();
                         }
                         if (get_spriteNum() == 2) {
                              image = get_up2();
                         }
                    }
                    if (attacking == true) {
                         if (spriteNum == 1) {
                              image = attackUp1;
                         }
                         if (spriteNum == 2) {
                              image = attackUp2;
                         }
                    }
                    break;

               case "down":
                    if (attacking == false) {
                         if (get_spriteNum() == 1) {
                              image = get_down1();
                         }
                         if (get_spriteNum() == 2) {
                              image = get_down2();
                         }
                    }
                    if (attacking == true) {
                         if (spriteNum == 1) {
                              image = attackDown1;
                         }
                         if (spriteNum == 2) {
                              image = attackDown2;
                         }
                    }

                    break;

               case "left":
                    if (attacking == false) {
                         if (get_spriteNum() == 1) {
                              image = get_left1();
                         }
                         if (get_spriteNum() == 2) {
                              image = get_left2();
                         }
                    }
                    if (attacking == true) {
                         if (spriteNum == 1) {
                              image = attackLeft1;
                         }
                         if (spriteNum == 2) {
                              image = attackLeft2;
                         }
                    }
                    break;

               case "right":
                    if (attacking == false) {
                         if (get_spriteNum() == 1) {
                              image = get_right1();
                         }
                         if (get_spriteNum() == 2) {
                              image = get_right2();
                         }
                    }
                    if (attacking == true) {
                         if (spriteNum == 1) {
                              image = attackRight1;
                         }
                         if (spriteNum == 2) {
                              image = attackRight2;
                         }
                    }
                    break;

               // case "stand":
               // image = get_down1();
               // break;

               default:
                    break;
          }
          if (invincible == true) {
               g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
          }
          g2.drawImage(image, screenX, screenY, get_width(), get_height(), myPanel);
          // reset alpha
          g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
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

     public void getPlayerAttackImage() {
          attackUp1 = setup("/Player/ninja_up_attack_1.png");
          attackUp2 = setup("/Player/ninja_up_attack_2.png");
          attackDown1 = setup("/Player/ninja_down_attack_1.png");
          attackDown2 = setup("/Player/ninja_down_attack_2.png");
          attackDown3 = setup("/Player/ninja_down_attack_3.png");
          attackDown4 = setup("/Player/ninja_down_attack_4.png");
          attackLeft1 = setup("/Player/ninja_left_attack_1.png");
          attackLeft2 = setup("/Player/ninja_left_attack_2.png");
          attackLeft3 = setup("/Player/ninja_left_attack_3.png");
          attackLeft4 = setup("/Player/ninja_left_attack_4.png");
          attackRight1 = setup("/Player/ninja_right_attack_1.png");
          attackRight2 = setup("/Player/ninja_right_attack_2.png");
          attackRight3 = setup("/Player/ninja_right_attack_3.png");
          attackRight4 = setup("/Player/ninja_right_attack_4.png");
     }

     public int getScreenX() {
          return screenX;
     }

     public int getScreenY() {
          return screenY;
     }
}
