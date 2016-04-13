package a3.Commands;

import a3.Controllers.Game;
import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class NewGameCommand extends AbstractAction {

    private GameWorld gameWorld;
    private Game g;

    public NewGameCommand(GameWorld gameWorld, Game g) {
        super("New");
        this.gameWorld = gameWorld;
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.newGame();
        g.startGame();
    }
}
