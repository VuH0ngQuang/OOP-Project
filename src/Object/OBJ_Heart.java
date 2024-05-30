package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject {
    public OBJ_Heart(){
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Object/characterheart_empty.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Object/characterheart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/Object/characterheart_full.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
