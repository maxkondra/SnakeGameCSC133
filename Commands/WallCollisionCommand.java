package a3.Commands;

import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class WallCollisionCommand extends AbstractAction {

    private GameWorld gameWorld;
    //private Sound snakeDie;

    public WallCollisionCommand(GameWorld gameWorld) {
        super("Snake hit Wall");
        this.gameWorld = gameWorld;
        //snakeDie = new Sound("weasel.wav");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.wallCollision();
    }

    public void execute() {
        gameWorld.wallCollision();
        /*if(gameWorld.isSound())
            snakeDie.play();*/
    }
}
