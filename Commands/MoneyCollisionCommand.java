package a3.Commands;

import a3.GameObjects.Money;
import a3.GameObjects.Sound;
import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class MoneyCollisionCommand extends AbstractAction {

    private GameWorld gameWorld;
    private Sound getMoney;


    public MoneyCollisionCommand(GameWorld gameWorld) {
        super("Snake hit Money");
        this.gameWorld = gameWorld;
        getMoney = new Sound("coin.wav");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void execute(Money m) {
        gameWorld.moneyCollision(m);
        if(gameWorld.isSound())
            getMoney.play();
    }
}
