package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Healing extends SuperObject {
    public OBJ_Healing() {
        name = "Hear";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Object/spin_heart_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

    }
}