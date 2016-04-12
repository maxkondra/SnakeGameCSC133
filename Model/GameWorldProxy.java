package a3.Model;

import a3.GameObjects.Food;
import a3.GameObjects.GameObjectCollection;
import a3.GameObjects.Money;
import a3.Interfaces.IGameWorld;
import a3.Interfaces.IObservable;
import a3.Interfaces.IObserver;

/**
 * Created by Max on 10/26/2014.
 */
public class GameWorldProxy implements IObservable, IGameWorld {

    private GameWorld gameWorld;

    public GameWorldProxy(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public GameObjectCollection getGameObjects() {
        return gameWorld.getGameObjects();
    }

    @Override
    public void pause() {
        gameWorld.pause();
    }

    @Override
    public void resume() {
        gameWorld.resume();
    }

    @Override
    public void stopMusic() {
        gameWorld.stopMusic();
    }

    @Override
    public void startMusic() {
        gameWorld.startMusic();
    }

    @Override
    public boolean isOnPause() {
        return gameWorld.isOnPause();
    }

    @Override
    public int getTimeRate() {
        return gameWorld.getTimeRate();
    }

    @Override
    public int getGameClock() {
        return gameWorld.getGameClock();
    }

    @Override
    public int getLives() {
        return gameWorld.getLives();
    }

    @Override
    public int getScore() {
        return gameWorld.getScore();
    }

    @Override
    public boolean isSound() {
        return gameWorld.isSound();
    }

    @Override
    public void initLayout() {
        return;
    }

    @Override
    public void goNorth() {
        return;
    }

    @Override
    public void goSouth() {
        return;
    }

    @Override
    public void goEast() {
        return;
    }

    @Override
    public void goWest() {
        return;
    }

    @Override
    public void bodySegmentCollision() {
        return;
    }

    @Override
    public void birdCollision() {
        return;
    }

    @Override
    public void moneyCollision(Money m) {
        return;
    }

    @Override
    public void foodCollision(Food f) {
        return;
    }

    @Override
    public void wallCollision() {
        return;
    }

    @Override
    public void tickTock() {
        return;
    }

    @Override
    public void displayMap() {
        gameWorld.displayMap();
    }

    @Override
    public void quit() {
        gameWorld.quit();
    }

    @Override
    public void addObserver(IObserver obs) {
        gameWorld.addObserver(obs);
    }

    @Override
    public void notifyObservers() {
        return;
    }

    @Override
    public void weaselCollision() {
        return;
    }

    @Override
    public void newGame() {
        return;
    }



}
