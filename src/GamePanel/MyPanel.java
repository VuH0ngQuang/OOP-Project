package GamePanel;

import AssetSetter.AssetSetter;
import CollisionChecker.CollisionChecker;
import KeyControl.*;
import Entity.*;
import Title.TitleManager;
import Object.*;
import Sound.*;

import java.awt.*;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable {
     // specifications
     private final int originalTileSize = 16; // 16

     private final int maxScreenCol = 81;
     private final int maxScreenRow = 45;

     public final int tileSize = getOriginalTileSize() * 3;

     private final int screenWidth = getOriginalTileSize() * maxScreenCol; // 1296
     private final int screenHeight = getOriginalTileSize() * maxScreenRow; // 720

     private final int FPS = 120;

     // create object
     public TitleManager titleManager = new TitleManager(this);
     Thread gameThread;
     public CollisionChecker collisionChecker = new CollisionChecker(this);
     KeyMoving keyMoving;
     public Player player; // move value to MyPanel() below
                           // default starting position at 3:2
     public SuperObject[] obj = new SuperObject[10];
     public Entity[] enemy = new Entity[10];
     public AssetSetter assetSetter = new AssetSetter(this);
     public UI ui = new UI(this);

     // Sound
     Sound sound = new Sound();

     // GAME STATE
     public int gameState;
     public final int titleState = 0;
     public final int playState = 1;
     public final int gameOverState = 2;
     public final int gameWinState = 3;

     // Panel
     public MyPanel() {

          this.setPreferredSize(new Dimension(getScreenWidth(), getScreenHeight()));
          this.setBackground(Color.BLACK);
          this.setDoubleBuffered(true);
          this.addKeyListener(keyMoving);
          this.setFocusable(true);
          this.keyMoving = new KeyMoving(this);
          this.player = new Player(this, keyMoving, 41, 7, 4, 3, 3);
          this.addKeyListener(keyMoving);
     }

     public void setupGame() {
          assetSetter.setObject();
          gameState = titleState;
          assetSetter.setEnemy();
     }

     // WORLD SETTINGS
     public final int maxWorldCol = 50;
     public final int maxWorldRow = 50;
     public final int maxWorldWidth = originalTileSize * 3 * maxWorldCol;
     public final int maxWorldHeight = originalTileSize * 3 * maxWorldCol;

     // create to stare game loop
     public void startGameThread() {
          gameThread = new Thread(this);
          gameThread.start();
     }

     @Override
     public void run() {

          double drawInterval = 1000000000.0 / get_FPS(); // 0.0167 seconds 1second into nanosecond
          double delta = 0;
          long lastTime = System.nanoTime();
          long currentTime;

          long timer = 0;
          int drawCount = 0;

          while (gameThread != null) {

               // time control each loop
               currentTime = System.nanoTime();

               delta += (currentTime - lastTime) / drawInterval;
               timer += (currentTime - lastTime);

               lastTime = currentTime;

               // draw graphic ech time 0.0167 seconds
               if (delta >= 1) {
                    // refresh information
                    update();
                    // Represents the elements
                    repaint();
                    delta--;
                    drawCount++;
               }

               // print FPS
               if (timer >= 1000000000) {
                    System.out.println("FPS:" + drawCount);
                    drawCount = 0;
                    timer = 0;
               }

          }
     }

     // update before paint
     public void update() {
          if (gameState == playState) {
               // PLAYER
               player.update();
               // ENEMY
               for (int i = 0; i < enemy.length; i++) {
                    if (enemy[i] != null) {
                         enemy[i].update();
                    }
               }
               for (int i = 0; i < enemy.length; i++) {
                    if (enemy[i] != null) {
                         if (enemy[i].alive == true && enemy[i].dying == false) {
                              enemy[i].update();
                         }
                         if (enemy[i].alive == false) {
                              enemy[i] = null;
                         }
                    }
               }
          }
     }

     // graphics paint
     public void paintComponent(Graphics g) {

          super.paintComponent(g);
          // draw floor
          Graphics2D g2 = (Graphics2D) g;
          // TITLE SCREEN
          if (gameState == titleState) {
               ui.draw(g2);
          } else {
               titleManager.draw(g2);
               // dRaW oBjEcT
               for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null) {
                         obj[i].draw(g2, this);
                    }
               }
               // draw ENinja
               for (Entity e : enemy) {
                    if (e != null) {
                         e.draw(g2);
                    }
               }
               // draw player
               player.draw(g2);
               // UI
               ui.draw(g2);
               g2.dispose();
          }

     }

     // function sound
     public void playMusic(int i) {

          sound.setFile(i);
          sound.play();
          sound.loop();

     }

     public void stopMusic() {
          sound.stop();
     }

     public void playSE(int i) {
          sound.setFile(i);
          sound.play();
     }

     // get and set function
     public int getScreenWidth() {
          return screenWidth;
     }

     public int getScreenHeight() {
          return screenHeight;
     }

     public int getMaxScreenRow() {
          return maxScreenRow / 3;
     }

     public int getMaxScreenCol() {
          return maxScreenCol / 3;
     }

     public int getOriginalTileSize() {
          return originalTileSize;
     }

     public int get_FPS() {
          return FPS;
     }

     public int getMaxWorldCol() {
          return maxWorldCol;
     };

     public int getMaxWorldRow() {
          return maxWorldRow;
     };

     public int getMaxWorldWidth() {
          return maxWorldWidth;
     };

     public int getMaxWorldHeight() {
          return maxWorldHeight;
     }

}
