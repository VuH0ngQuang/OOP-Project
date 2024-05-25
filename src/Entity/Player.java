package Entity;

import GamePanel.*;
import KeyControl.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {
     // object create
     MyPanel myPanel;
     KeyMoving keyMoving;

     // contructol
     public Player(MyPanel myPanel, KeyMoving keyMoving, int x, int y, int speed, int height, int width) {
          super(myPanel.getOriginalTileSize() * x, myPanel.getOriginalTileSize() * y,
                    speed,
                    myPanel.getOriginalTileSize() * height, myPanel.getOriginalTileSize() * width);
          this.myPanel = myPanel;
          this.keyMoving = keyMoving;
          getPlayerImage();
          set_direction("down");
     }

     // update value
     public void update() {

          if (keyMoving.getUp() == true) {
               set_direction("up");
               set_y(get_y() - get_speed());
          }

          if (keyMoving.getDown() == true) {
               set_direction("down");
               set_y(get_y() + get_speed());
          }

          if (keyMoving.getLeft() == true) {
               set_direction("left");
               set_x(get_x() - get_speed());
          }

          if (keyMoving.getRight() == true) {
               set_direction("right");
               set_x(get_x() + get_speed());
          }

          // Make it always face out of the screen when the button is not pressed
          if (keyMoving.getRight() == false && keyMoving.getLeft() == false &&
                    keyMoving.getUp() == false
                    && keyMoving.getDown() == false) {
               set_direction("down");
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

     // draw graphics
     public void draw(Graphics g) {
          // g.setColor(Color.white);
          // g.fillRect(get_x(), get_y(), get_width(), get_height());

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
                    image = get_down1();
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

               default:
                    break;
          }

          g.drawImage(image, get_x(), get_y(), get_width(), get_height(), myPanel);
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
}
