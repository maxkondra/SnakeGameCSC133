package a3.Commands;

import a3.GameObjects.Food;
import a3.GameObjects.Sound;
import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class FoodCollisionCommand extends AbstractAction {

    private GameWorld gameWorld;
    private Sound eatFood;

    public FoodCollisionCommand(GameWorld gameWorld) {
        super("Snake eats Food");
        this.gameWorld = gameWorld;
        eatFood = new Sound("food.wav");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //gameWorld.FoodCollision();
    }

    public void execute(Food f) {
        gameWorld.foodCollision(f);
        if(gameWorld.isSound())
            eatFood.play();
    }
}
