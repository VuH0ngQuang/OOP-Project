package GamePanel;

import javax.swing.*;

public class MyFrame extends JFrame {

     // create frame
     public MyFrame() {

          MyPanel myPanel = new MyPanel();

          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setResizable(false);
          this.setTitle("NinjaDie");

          this.add(myPanel);
          this.pack();

          this.setLocationRelativeTo(null);
          this.setVisible(true);

          myPanel.startGameThread();
     }

}