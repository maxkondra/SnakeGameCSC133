package a3.Commands;

import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class SouthCommand extends AbstractAction {
    private GameWorld gameWorld;

    public SouthCommand(GameWorld gameWorld) {
        super("South");
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameWorld.getLives()>0)
            gameWorld.goSouth();
    }
}
