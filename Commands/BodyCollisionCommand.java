package a3.Commands;

import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class BodyCollisionCommand extends AbstractAction {

    private GameWorld gameWorld;
    //private Sound snakeDie;


    public BodyCollisionCommand(GameWorld gameWorld) {
        super("Snake hit Body");
        this.gameWorld = gameWorld;
        //snakeDie = new Sound("weasel.wav");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.bodySegmentCollision();
    }

    public void execute() {
        gameWorld.bodySegmentCollision();
        /*if(gameWorld.isSound())
            snakeDie.play();*/
    }
}
