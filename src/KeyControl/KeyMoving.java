package KeyControl;

import GamePanel.MyPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyMoving implements KeyListener {

     private boolean up, down, left, right;
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
          if (gp == null) return;

          int code = e.getKeyCode();
          //TITLE STATE
          if(gp.gameState == gp.titleState){
               if(code == KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                         gp.ui.commandNum = 1;
                    }
               }
               if(code == KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 1){
                         gp.ui.commandNum = 0;
                    }
               }
               if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                         gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 1){
                         System.exit(0);
                    }
               }
          }

          switch (e.getKeyCode()) {
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

               default:
                    break;
          }
     }

     @Override
     public void keyReleased(KeyEvent e) {
          switch (e.getKeyCode()) {
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

               default:
                    break;
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

}
