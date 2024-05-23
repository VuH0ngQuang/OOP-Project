package graphic;

import java.awt.*;
import javax.swing.*;

public class Map extends JFrame {

     Character character;

     public Map() {

          character = new Character();

          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setSize(1280, 720);

          this.add(character);

          this.setLocationRelativeTo(null);
          this.setVisible(true);
     }

}