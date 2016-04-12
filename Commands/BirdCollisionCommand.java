package a3.Commands;

import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class BirdCollisionCommand extends AbstractAction {

    private GameWorld gameWorld;
    //private Sound snakeDie;


    public BirdCollisionCommand(GameWorld gameWorld) {
        super("Bird hit Snake");
        this.gameWorld = gameWorld;
        //snakeDie = new Sound("weasel.wav");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.birdCollision();
    }

    public void execute() {
        gameWorld.birdCollision();
        /*if(gameWorld.isSound())
            snakeDie.play();*/
    }
}
