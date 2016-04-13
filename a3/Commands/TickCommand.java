package a3.Commands;

import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class TickCommand extends AbstractAction {

    private GameWorld gameWorld;

    public TickCommand(GameWorld gameWorld) {
        super("Tick");
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.tickTock();
    }
}
