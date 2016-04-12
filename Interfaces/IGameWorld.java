package a3.Interfaces;

import a3.GameObjects.Food;
import a3.GameObjects.GameObjectCollection;
import a3.GameObjects.Money;

/**
 * Created by Max on 10/26/2014.
 */
public interface IGameWorld {

    public int getGameClock();

    public int getLives();

    public int getScore();

    public boolean isSound();

    public void initLayout();

    public void goNorth();

    public void goSouth();

    public void goEast();

    public int getTimeRate();

    public void goWest();

    public void bodySegmentCollision();

    public void birdCollision();

    public void moneyCollision(Money m);

    public void foodCollision(Food f);

    public void wallCollision();

    public void tickTock();

    public void displayMap();

    public void quit();

    public void addObserver(IObserver obs);

    public void notifyObservers();

    public void weaselCollision();

    public void newGame();

    public GameObjectCollection getGameObjects();

    public void pause();

    public void resume();

    public void stopMusic();

    public void startMusic();

    public boolean isOnPause();
}
