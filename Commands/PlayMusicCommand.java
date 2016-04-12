package a3.Commands;

import a3.GameObjects.Sound;
import a3.Model.GameWorld;

/**
 * Created by Max on 11/30/2014.
 */
public class PlayMusicCommand {

    private GameWorld gameWorld;
    private Sound music;

    public PlayMusicCommand(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        music = new Sound("loop.wav");
    }

    public void loop() {
        music.loop();
    }

    public void stop(){
        music.stop();
    }
}
