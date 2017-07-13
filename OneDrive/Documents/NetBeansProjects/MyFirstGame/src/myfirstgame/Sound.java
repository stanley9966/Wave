
package myfirstgame;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Sound {
  
    // doesnt set the clipRunning boolean to true, have to do manually
    public static Clip play(String path) {
        Clip clip = null;
        try{
            clip = AudioSystem.getClip();
            File file;
            file = new File(path);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {}
        return clip;
    }
}

