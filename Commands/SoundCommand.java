package a3.Commands;

import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class SoundCommand extends AbstractAction {

    private GameWorld gameWorld;

    public SoundCommand(GameWorld gameWorld) {
        super("Sound");
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.changeSound();
    }

}
