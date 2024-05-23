package graphic;

import java.awt.*;

import javax.swing.JPanel;

public class Character extends JPanel {

     Character() {
          this.setPreferredSize(new Dimension(100, 30));
     }

     public void paintComponent(Graphics g) {

          g.drawLine(0, 0, 500, 500);

     }

}
