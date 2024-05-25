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
    Title[] title;
    int mapTileNum[][];


    public TitleManager(MyPanel mp){
        this.mp = mp;

        title = new Title[10];
        mapTileNum = new int[mp.getMaxScreenCol()][mp.getMaxScreenCol()];

        getTileImage();
        loadMap("/Maps/map01.txt"); // put map location here
    }

    // Get title image
    public void getTileImage(){ // 0 is wall and 1 is first floor and 2 is second floor
        try {
            title[0] = new Title();
            title[0].image = ImageIO.read(getClass().getResourceAsStream("/Title/wall.png"));
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

            while (col < mp.getMaxScreenCol() && row < mp.getMaxScreenRow()){
                String line = br.readLine();
                while (col <  mp.getMaxScreenCol()){ // get the first row data from txt to create the map
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                } // read next line of the file txt
                if(col == mp. getMaxScreenCol()){
                    col =0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0 ;

        while ((col < mp.getMaxScreenCol() && row < mp.getMaxScreenRow())){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(title[tileNum].image,x ,y, mp.getOriginalTileSize()*3, mp.getOriginalTileSize()*3, null);
            col++;
            x += mp.getOriginalTileSize()*3;

            if(col == mp.getMaxScreenCol()){
                col = 0;
                x = 0;
                row++;
                y += mp.getOriginalTileSize()*3;
            }
        }
    }
}
