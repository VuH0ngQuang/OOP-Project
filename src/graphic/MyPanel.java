package Graphic;

import KeyControl.*;
import Entity.*;
import java.awt.*;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable {

     // specifications
     private final int originaltileSize = 16; // 16

     private final int width_character = getOriginaltileSize() * 5; // 80
     private final int height_character = getOriginaltileSize() * 5; // 80

     private final int maxScreenCol = 80;
     private final int maxScreenRow = 45;

     private final int screenWidth = getOriginaltileSize() * maxScreenCol; // 1280
     private final int screenHeight = getOriginaltileSize() * maxScreenRow; // 720

     private final int FPS = 60;

     // create object
     Thread gameThread;
     KeyMoving keyMoving = new KeyMoving();
     Player player = new Player(this, keyMoving, 5, 5, 5, 5, 5);

     // Panel
     public MyPanel() {

          this.setPreferredSize(new Dimension(getScreenWidth(), getScreenHeight()));
          this.setBackground(Color.BLACK);
          this.setDoubleBuffered(true);
          this.addKeyListener(keyMoving);
          this.setFocusable(true);

     }

     // create to stare game loop
     public void startGameThread() {
          gameThread = new Thread(this);
          gameThread.start();
     }

     @Override
     public void run() {

          double drawInterval = 1000000000 / get_FPS(); // 0.0167 seconds 1second into nanosecond
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

          player.update();

     }

     // graphics paint
     public void paintComponent(Graphics g) {

          super.paintComponent(g);

          // draw player
          player.draw(g);

          g.dispose();

     }

     // get and set function
     public int getScreenWidth() {
          return screenWidth;
     }

     public int getScreenHeight() {
          return screenHeight;
     }

     public int getMaxScreenRow() {
          return maxScreenRow;
     }

     public int getMaxScreenCol() {
          return maxScreenCol;
     }

     public int get_height_character() {
          return height_character;
     }

     public int get_width_character() {
          return width_character;
     }

     public int getOriginaltileSize() {
          return originaltileSize;
     }

     public int get_FPS() {
          return FPS;
     }
}
