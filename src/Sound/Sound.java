package Sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
     Clip clip;
     URL soundURL[] = new URL[30];

     public Sound() {
          System.out.println("Sound constructor is running.");
          soundURL[0] = getClass().getResource("/Sound/sound_background.wav");
          soundURL[1] = getClass().getResource("/Sound/sound_bangbang.wav");
          soundURL[2] = getClass().getResource("/Sound/sound_wining.wav");
     }

     public void setFile(int i) {
          System.out.println("setFile method is running with index: " + i);
          try {
               AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
               clip = AudioSystem.getClip();
               clip.open(ais);
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     public void play() {
          clip.start();
     }

     public void loop() {
          clip.loop(Clip.LOOP_CONTINUOUSLY);
     }

     public void stop() {
          clip.stop();
     }
}
