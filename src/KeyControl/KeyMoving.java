package KeyControl;

import GamePanel.MyPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyMoving implements KeyListener {

     private boolean up, down, left, right, enter;
     private MyPanel gp;

     public KeyMoving(MyPanel gp) {
          this.gp = gp;
     }

     // moving listening event
     @Override
     public void keyTyped(KeyEvent e) {

     }

     @Override
     public void keyPressed(KeyEvent e) {
          // if (gp == null)
          // return;
          int code = e.getKeyCode();

          // TITLE STATE
          if (gp.gameState == gp.titleState || gp.gameState == gp.gameOverState || gp.gameState == gp.gameWinState) {
               switch (code) {
                    case KeyEvent.VK_W:
                         gp.ui.commandNum--;
                         if (gp.ui.commandNum < 0) {
                              gp.ui.commandNum = 1;
                         }
                         break;
                    case KeyEvent.VK_S:
                         gp.ui.commandNum++;
                         if (gp.ui.commandNum > 1) {
                              gp.ui.commandNum = 0;
                         }
                         break;
                    case KeyEvent.VK_ENTER:
                         if (gp.ui.commandNum == 0) {
                              if (gp.gameState == gp.titleState)
                                   gp.gameState = gp.playState;

                              if (gp.gameState == gp.gameOverState)
                                   gp.gameState = gp.titleState;

                              if (gp.gameState == gp.gameWinState)
                                   gp.gameState = gp.titleState;
                              // begin play music
                              // gp.playMusic(0);
                         }
                         if (gp.ui.commandNum == 1) {
                              System.exit(0);
                         }
                         break;
                    default:
                         break;
               }
          }

          // TITLE STATE
          // else if (gp.gameState == gp.gameOverState) {
          // if (code == KeyEvent.VK_W) {
          // gp.ui.commandNum--;
          // if (gp.ui.commandNum < 0) {
          // gp.ui.commandNum = 1;
          // }
          // }
          // if (code == KeyEvent.VK_S) {
          // gp.ui.commandNum++;
          // if (gp.ui.commandNum > 1) {
          // gp.ui.commandNum = 0;
          // }
          // }
          // if (code == KeyEvent.VK_ENTER) {
          // if (gp.ui.commandNum == 0) {
          // gp.gameState = gp.titleState;
          // // gp.player.life = 6;
          // }
          // if (gp.ui.commandNum == 1) {
          // System.exit(0);
          // }
          // }
          // }

          if (gp.gameState == gp.playState) {
               switch (code) {
                    case KeyEvent.VK_W:
                         setUp(true);
                         break;

                    case KeyEvent.VK_S:
                         setDown(true);
                         break;

                    case KeyEvent.VK_A:
                         setLeft(true);
                         break;

                    case KeyEvent.VK_D:
                         setRight(true);
                         break;

                    case KeyEvent.VK_ENTER:
                         setEnter(true);
                         break;

                    default:
                         break;
               }
          }
     }

     @Override
     public void keyReleased(KeyEvent e) {
          int code = e.getKeyCode();
          if (gp.gameState == gp.playState) {
               switch (code) {
                    case KeyEvent.VK_W:
                         setUp(false);
                         break;

                    case KeyEvent.VK_S:
                         setDown(false);
                         break;

                    case KeyEvent.VK_A:
                         setLeft(false);
                         break;

                    case KeyEvent.VK_D:
                         setRight(false);
                         break;

                    case KeyEvent.VK_ENTER:
                         setEnter(false);
                         break;

                    default:
                         break;
               }
          }
     }

     // get and set
     public void setUp(boolean up) {
          this.up = up;
     }

     public boolean getUp() {
          return up;
     }

     public void setDown(boolean down) {
          this.down = down;
     }

     public boolean getDown() {
          return down;
     }

     public void setLeft(boolean left) {
          this.left = left;
     }

     public boolean getLeft() {
          return left;
     }

     public void setRight(boolean right) {
          this.right = right;
     }

     public boolean getRight() {
          return right;
     }

     public void setEnter(boolean enter) {
          this.enter = enter;
     }

     public boolean getEnter() {
          return enter;
     }

}
