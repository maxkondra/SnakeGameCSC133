package a3.Commands;

import a3.Model.GameWorld;
import a3.Views.MapView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 11/30/2014.
 */
public class DeleteCommand extends AbstractAction {

    private GameWorld gameWorld;
    private MapView m;

    public DeleteCommand(GameWorld gameWorld, MapView m) {
        super("Delete");
        this.gameWorld = gameWorld;
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.removeSelectedObjects();
        m.repaint();
    }
}
