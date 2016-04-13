package a3.Commands;

import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class ChangeStrategyCommand extends AbstractAction {

    private GameWorld gameWorld;

    public ChangeStrategyCommand(GameWorld gameWorld) {
        super("Change Strategy");
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.changeStrategy();
    }
}
