package GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import Object.*;


public class UI{
    MyPanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    BufferedImage heart_full, heart_half, heart_empty;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(MyPanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //CREATE HUD OBJECT
        SuperObject heart = new OBJ_Heart();
        heart_empty = heart.image;
        heart_half = heart.image2;
        heart_full = heart.image3;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.white);
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
    }
    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        //DRAW MAX HEART
        while (i < gp.player.maxLife/2){
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        //DRAW CURRENT LIFE
        while (i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full,x , y, null);
            }
            i++;
            x += gp.tileSize;

        }
    }
    public void drawTitleScreen(){
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());
    //TITLE NAME
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
    String text = "Ninja DIE ♡";
    int x = getXforCenteredText(text);
    int y = gp.tileSize*3;

    //SHADOW COLOR
    g2.setColor(Color.pink);
    g2.drawString(text, x+5, y+5);

    //MAIN COLOR
    g2.setColor(Color.white);
    g2.drawString(text, x, y);

    //NINJA IMAGE
    x = gp.getScreenWidth()/2  - ((gp.tileSize/2)+30);
    y = gp.tileSize*5;
    g2.drawImage(gp.player.get_down1(), x, y, gp.tileSize*3, gp.tileSize*3, null);

    //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*6;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString("✧", x - gp.tileSize*2, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString("✧", x - gp.tileSize*2, y);
        }

    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.getScreenWidth() /2 - length/2;
        return x;
    }
}



