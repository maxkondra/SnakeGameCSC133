package a3.Commands;

import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class EastCommand extends AbstractAction {
    private GameWorld gameWorld;

    public EastCommand(GameWorld gameWorld) {
        super("East");
        this.gameWorld = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameWorld.getLives()>0)
            gameWorld.goEast();
    }
}
