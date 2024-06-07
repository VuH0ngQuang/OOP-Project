package GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import Object.*;

public class UI {
    MyPanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB, arial_24;
    BufferedImage heart_full, heart_half, heart_empty;
    public String currentDialogue = "";
    public int commandNum = 0;
    public OBJ_Key Obj_key;

    // set up String massage
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    // finish
    public boolean gameFinished = false;

    // count time
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(MyPanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/Purisa_Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);

            arial_24 = new Font("Arial", Font.PLAIN, gp.tileSize / 2);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // CREATE HUD OBJECT
        SuperObject heart = new OBJ_Heart();
        heart_empty = heart.image;
        heart_half = heart.image2;
        heart_full = heart.image3;

        // CREATE key OBJECT
        Obj_key = new OBJ_Key();
        // Check if the images are loaded correctly
        // System.out.println("heart_empty: " + heart_empty);
        // System.out.println("heart_half: " + heart_half);
        // System.out.println("heart_full: " + heart_full);
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawPlayerKey();

            // Time
            playTime += (double) 1 / 60;
            g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize * 11, gp.tileSize);

            // draw message
            if (messageOn) {
                g2.drawString(message, 10, gp.tileSize * 4);
                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

        // game win
        if (gp.gameState == gp.gameWinState) {
            drawGameWinScreen();
        }

        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

    }

    public void drawPlayerLife() {
        // Graphics2D g2d = (Graphics2D) gp.getGraphics();
        // // Check if this method is being called
        // System.out.println("drawPlayerLife is being called");
        // // Test if g2 can draw anything
        // g2d.setColor(Color.RED);
        // g2d.fillRect(144, 48, 50, 50);
        // g2d.dispose();
        int x = 10;
        int y = 10;
        int i = 0;

        // DRAW MAX HEART
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_empty, x, y, gp.tileSize, gp.tileSize, gp);
            i++;
            x += gp.tileSize + 10;
        }

        // RESET 1
        x = 10;
        y = 10;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, gp.tileSize, gp.tileSize, gp);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, gp.tileSize, gp.tileSize, gp);
            }
            i++;
            x += gp.tileSize + 10;
            // // Check the values of gp.player.life and gp.player.maxLife
            // System.out.println("gp.player.life: " + gp.player.life);
            // System.out.println("gp.player.maxLife: " + gp.player.maxLife);
            // // Check the values of gp.tileSize, x, and y
            // System.out.println("gp.tileSize: " + gp.tileSize);
            // System.out.println("x: " + x);
            // System.out.println("y: " + y);
        }
    }

    // draw title menu
    public void drawTitleScreen() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());
        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Ninja DIE ♡";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        // SHADOW COLOR
        g2.setColor(Color.pink);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // NINJA IMAGE
        x = gp.getScreenWidth() / 2 - ((gp.tileSize / 2) + 30);
        y = gp.tileSize * 5;
        g2.drawImage(gp.player.get_down1(), x, y, gp.tileSize * 3, gp.tileSize * 3, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 6;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString("☠", x - gp.tileSize * 2, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString("☠", x - gp.tileSize * 2, y);
        }

    }

    // get place centered text
    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.getScreenWidth() / 2 - length / 2;
        return x;
    }

    // draw key
    public void drawPlayerKey() {
        g2.setFont(arial_24);
        g2.setColor(Color.white);
        g2.drawString(" x " + gp.player.hasKey, gp.tileSize, gp.tileSize * 2 - 10);
        g2.drawImage(Obj_key.image, 0, gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
    }

    // message show
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(107, 0, 119, 231));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        text = "You Die, Loser <3";
        // Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "BACK TO MENU ♡";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString("❤", x - 40, y);
        }
        // Back to title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString("❤", x - 40, y);
        }
    }

    public void drawGameWinScreen() {

        g2.setFont(arial_24);
        g2.setColor(Color.white);

        String text;
        int textLength;
        int x, y;

        text = "You found the treasure";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.getScreenWidth() / 2 - textLength / 2;
        y = gp.getScreenHeight() / 2 - (gp.tileSize * 3);
        g2.drawString(text, x, y);

        text = "You time is :" + dFormat.format(playTime);
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.getScreenWidth() / 2 - textLength / 2;
        y = gp.getScreenHeight() / 2 + (gp.tileSize * 4);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, gp.tileSize * 3));
        g2.setColor(Color.yellow);
        text = "Congratulations";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.getScreenWidth() / 2 - textLength / 2;
        y = gp.getScreenHeight() / 2 + (gp.tileSize * 2);
        g2.drawString(text, x, y);

        gp.gameThread = null;
    }

}
