package a3.Model;

import a3.Commands.PlayMusicCommand;
import a3.Controllers.Game;
import a3.GameObjects.*;
import a3.Interfaces.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Max on 10/5/2014.
 */
public class GameWorld implements IObservable, IGameWorld, ActionListener {


    private GameObjectCollection gameObjects;
    private Game game;
    private Iterator gameObjectsIterator;
    private static double gameWorldSize = 800;
    private GameWorldProxy gwProxy;
    private int gameClock;
    private Snake snake;
    private int lives;
    private int score;
    private ArrayList<IObserver> observers;
    private boolean sound = false;
    private int timeRate = 20;
    private ArrayList<GameObject> objectsToRemove;
    private PlayMusicCommand gameMusic;
    private boolean onPause = false;

    private Timer myTimer = new Timer(timeRate, this);

    public GameWorld(Game game) {
        this.gameObjects = new GameObjectCollection();
        observers = new ArrayList<IObserver>();
        objectsToRemove = new ArrayList<GameObject>();
        gwProxy = new GameWorldProxy(this);
        gameMusic = new PlayMusicCommand(this);
        this.game = game;

    }

    public boolean isOnPause() {
        return onPause;
    }

    public void stopMusic(){
        gameMusic.stop();
    }

    public void startMusic(){
        gameMusic.loop();
    }

    public void playMusic(){
        gameMusic.loop();
    }

    public int getGameClock() {
        return gameClock;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public boolean isSound() {
        return sound;
    }

    public static double getGameWorldSize() {
        return gameWorldSize;
    }

    //Factory Methods
    public Snake makeSnake() {
        return new Snake(this);
    }

    public Food makeFood() {
        return new Food();
    }

    public Bird makeBird() {
        return new Bird();
    }

    public Money makeMoney() {
        return new Money();
    }

    public Wall makeWall(int height, int width, Location location, char side) {
        return new Wall(height, width, location, side);
    }

    public int getTimeRate() {
        return timeRate;
    }

    public Weasel makeWeasel(SnakeHead snakeHead){ return new Weasel(snakeHead);}

    public void initLayout() {
        gameClock = 0;
        snake = makeSnake();
        gameObjects.add(snake);
        gameObjects.add(makeFood());
        gameObjects.add(makeBird());
        gameObjects.add(makeBird());
        gameObjects.add(makeBird());
        gameObjects.add(makeMoney());
        gameObjects.add(makeWeasel(snake.getSnakeHead()));
        gameObjects.add(makeWeasel(snake.getSnakeHead()));
        gameObjects.add(makeWeasel(snake.getSnakeHead()));
        gameObjects.add(makeWeasel(snake.getSnakeHead()));
        addFourWalls();
        notifyObservers();
        myTimer.start();
    }

    private void addFourWalls() {
        gameObjects.add(makeWall(6, (int) gameWorldSize, new Location(gameWorldSize/2, 6), 'S'));
        gameObjects.add(makeWall(6, (int) gameWorldSize, new Location(gameWorldSize/2, gameWorldSize - 3), 'N'));
        gameObjects.add(makeWall((int) gameWorldSize, 6, new Location(6, gameWorldSize/2), 'E'));
        gameObjects.add(makeWall((int) gameWorldSize, 6, new Location(gameWorldSize - 3, gameWorldSize/2), 'W'));
    }

    private void resetWorld() {
        gameObjects.clear();
        myTimer.stop();
        if (lives > 0) {
            initLayout();
        } else {
            // verify intent before starting new game by displaying a Yes/No dialog
            int result = JOptionPane.showConfirmDialog
                    (null, // source of event
                            "Out of lives. Game Over! Start a new game?", // display message
                            "New Game", // Title bar text
                            JOptionPane.YES_NO_OPTION, // button choices
                            JOptionPane.QUESTION_MESSAGE); // prompt icon
            if (result == JOptionPane.YES_OPTION) {
                newGame();
            }else{
                lives = 0;
                game.stopGame();
            }
        }
        notifyObservers();
    }

    public void goNorth() {
        snake.getSnakeHead().steer(270);
        System.out.println("Snake is moving North!");
        notifyObservers();
    }

    public void goSouth() {
        snake.getSnakeHead().steer(90);
        System.out.println("Snake is moving South!");
        notifyObservers();
    }

    public void goEast() {
        snake.getSnakeHead().steer(0);
        System.out.println("Snake is moving East!");
        notifyObservers();
    }

    public void goWest() {
        snake.getSnakeHead().steer(180);
        System.out.println("Snake is moving West!");
        notifyObservers();
    }

    public void bodySegmentCollision() {
        if(lives > 0) {
            System.out.println("Snake Collided with a body segment.");
            lives--;
        }
        resetWorld();
    }

    public void birdCollision() {
        if(lives > 0){
            System.out.println("Snake Collided with a Bird.");
            lives--;
        }
        resetWorld();
    }

    public void moneyCollision(Money m) {
        if(lives > 0) {
            System.out.println("Snake Collided with Money.");
            gameObjectsIterator = gameObjects.iterator();
            while (gameObjectsIterator.hasNext()) {
                GameObject next = (GameObject) gameObjectsIterator.next();
                if (next.equals(m)) {
                    score += ((Money) next).getValue();
                    objectsToRemove.add(next);
                    break;
                }
            }
        }else{
            resetWorld();
        }
    }

    public void foodCollision(Food f) {
        if(lives > 0) {

            System.out.println("Snake Collided with Food.");
            gameObjectsIterator = gameObjects.iterator();
            while (gameObjectsIterator.hasNext()) {
                GameObject next = (GameObject) gameObjectsIterator.next();
                if (next.equals(f)) {
                    snake.ateFood(((Food) next).getValue());
                    objectsToRemove.add(next);
                    gameObjects.add(new Food());

                    Random rand = new Random();
                    int numberOfMoneyToAdd = rand.nextInt((4-1)+1)+1;
                    for(int i=0; i<numberOfMoneyToAdd; i++){
                        gameObjects.add(new Money());
                    }
                    break;
                }
            }
        }else{
            resetWorld();
        }
    }

    public void wallCollision() {
        if(lives > 0) {
            System.out.println("Snake Collided with a Wall.");
            lives--;
        }
        resetWorld();
    }

    public void tickTock() {
        if(lives > 0) {
            gameObjectsIterator = gameObjects.iterator();
            while (gameObjectsIterator.hasNext()) {
                GameObject next = (GameObject) gameObjectsIterator.next();
                if (next instanceof FixedGameObject) {
                    ((FixedGameObject) next).incrementAge();
                } else if (next instanceof MoveableGameObject) {
                        ((MoveableGameObject) next).move(timeRate);
                }
            }
            gameClock++;
            Iterator collisiionIterator = gameObjects.iterator();
            while (collisiionIterator.hasNext()) {
                ICollider firstObject = (ICollider) collisiionIterator.next();
                Iterator collisionIterator2 = gameObjects.iterator();
                while (collisionIterator2.hasNext()){
                    ICollider secondObject = (ICollider)collisionIterator2.next();
                    if(firstObject != secondObject || firstObject instanceof Snake){
                        if(firstObject.collidesWith(secondObject)){
                            firstObject.handleCollision(secondObject);
                        }
                    }
                }
            }
            removeObjects();
            notifyObservers();
        }else{
            resetWorld();
        }
    }

    private void removeObjects() {
        for(GameObject g: objectsToRemove){
            gameObjects.remove(g);
        }
    }

    public void displayMap() {
        if(lives > 0) {
            gameObjectsIterator = gameObjects.iterator();
            while (gameObjectsIterator.hasNext()) {
                GameObject next = (GameObject) gameObjectsIterator.next();
                if (!(next instanceof SnakeHead || next instanceof BodySegment))
                    System.out.println(next.toString());
            }
        }
    }

    public void quit() {
        // verify intent before exiting by displaying a Yes/No dialog
        int result = JOptionPane.showConfirmDialog
                (null, // source of event
                        "Are you sure you want to exit ?", // display message
                        "Confirm Exit", // Title bar text
                        JOptionPane.YES_NO_OPTION, // button choices
                        JOptionPane.QUESTION_MESSAGE); // prompt icon
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void changeSound(){
        if(sound){
            sound = false;
            System.out.println("Sound set to OFF.");
            stopMusic();
        }else{
            sound = true;
            System.out.println("Sound set to ON.");
            if(!onPause)
                playMusic();
        }
        notifyObservers();
    }

    public void changeStrategy(){
        if(lives > 0) {
            gameObjectsIterator = gameObjects.iterator();
            while (gameObjectsIterator.hasNext()) {
                GameObject next = (GameObject) gameObjectsIterator.next();
                if (next instanceof Weasel)
                    ((Weasel) next).changeStrategy();
            }
            notifyObservers();
        }else{
            resetWorld();
        }
    }

    @Override
    public void addObserver(IObserver obs) {
        observers.add(obs);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.update(gwProxy, null);
        }
    }

    @Override
    public void weaselCollision() {
        if(lives > 0) {
            System.out.println("Snake Collided with a Weasel.");
            lives--;
        }
        resetWorld();
    }

    @Override
    public void newGame() {
        lives = 3;
        score = 0;
        initLayout();
    }

    public GameObjectCollection getGameObjects() {
        return gameObjects;
    }

    public GameWorldProxy getGameWorldProxy() {
        return gwProxy;
    }

    public void pause(){
        myTimer.stop();
        onPause = true;
        stopMusic();
    }

    public void resume(){
        myTimer.start();
        onPause = false;
        if(isSound()){
            startMusic();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tickTock();
    }

    public void removeSelectedObjects(){
        gameObjectsIterator = gameObjects.iterator();
        if(!gameObjects.isEmpty()){
            while (gameObjectsIterator.hasNext()) {
                GameObject next = (GameObject) gameObjectsIterator.next();
                if (next instanceof ISelectable) {
                    if(((ISelectable) next).isSelected()){
                        objectsToRemove.add(next);
                    }
                }
            }
            removeObjects();
        }
    }
}
