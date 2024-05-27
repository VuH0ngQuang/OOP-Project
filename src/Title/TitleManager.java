package Title;

import GamePanel.MyPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TitleManager {

    MyPanel mp;
    public Title[] title;
    public int mapTileNum[][];


    public TitleManager(MyPanel mp){
        this.mp = mp;

        title = new Title[10];
        mapTileNum = new int[mp.getMaxWorldCol()][mp.getMaxWorldRow()];

        getTileImage();
        loadMap("/Maps/map01.txt"); // put map location here
    }

    // Get title image
    public void getTileImage(){ // 0 is wall and 1 is first floor and 2 is second floor
        try {
            title[0] = new Title();
            title[0].image = ImageIO.read(getClass().getResourceAsStream("/Title/wall.png"));
            title[0].collision = true;
            title[1] = new Title();
            title[1].image = ImageIO.read(getClass().getResourceAsStream("/Title/floor_1.png"));
            title[2] = new Title();
            title[2].image = ImageIO.read(getClass().getResourceAsStream("/Title/floor_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String maplocation){
        try{
            InputStream is = getClass().getResourceAsStream(maplocation);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < mp.getMaxWorldCol() && row < mp.getMaxWorldRow()){
                String line = br.readLine();
                while (col <  mp.getMaxWorldCol()){ // get the first row data from txt to create the map
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                } // read next line of the file txt
                if(col == mp. getMaxWorldCol()){
                    col =0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while ((worldCol < mp.getMaxWorldCol() && worldRow < mp.getMaxWorldRow())){
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * mp.getOriginalTileSize()*3;
            int worldY = worldRow * mp.getOriginalTileSize()*3;
            int screenX = worldX - mp.player.get_worldX() + mp.player.getScreenX();
            int screenY = worldY - mp.player.get_worldY() + mp.player.getScreenY();

            if(worldX + mp.getOriginalTileSize() * 3  > mp.player.get_worldX() - mp.player.screenX &&
               worldX - mp.getOriginalTileSize() *3 < mp.player.get_worldX() + mp.player.screenX &&
               worldY + mp.getOriginalTileSize() * 3 > mp.player.get_worldY() - mp.player.screenY &&
               worldY - mp.getOriginalTileSize() * 3 < mp.player.get_worldY() + mp.player.screenY){
                g2.drawImage(title[tileNum].image,screenX ,screenY, mp.getOriginalTileSize()*3, mp.getOriginalTileSize()*3, null);
            }
            worldCol++;
            if(worldCol == mp.getMaxWorldCol()){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
