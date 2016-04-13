package a3.GameObjects;

import java.applet.Applet ;
import java.applet.AudioClip ;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Max on 11/30/2014.
 */

public class Sound {
    AudioClip myClip ;
    public Sound(String fileName) {
        fileName = "."+File.separator+"a3"+File.separator+"Sounds"+File.separator+fileName;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                myClip = Applet.newAudioClip(file.toURI().toURL());
            } else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Sound: malformed URL: " + e);
        }
    }
    public void play() {
        myClip.play();
    }

    public void stop(){
        myClip.stop();
    }

    public void loop(){
        myClip.loop();
    }
}
