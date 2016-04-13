package a3.Commands;

import a3.GameObjects.Sound;
import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class WeaselCollisionCommand extends AbstractAction {

    private GameWorld gameWorld;
    private Sound snakeDie;

    public WeaselCollisionCommand(GameWorld gameWorld) {
        super("Weasel hit Snake");
        this.gameWorld = gameWorld;
        snakeDie = new Sound("weasel.wav");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.weaselCollision();
    }

    public void execute() {
        gameWorld.weaselCollision();
        if(gameWorld.isSound())
            snakeDie.play();
    }
}
