package a3.GameObjects;

import a3.Interfaces.ICollider;
import a3.Interfaces.IDrawable;
import a3.Model.GameWorld;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.Iterator;

/**
 * Created by Max on 10/5/2014.
 */
public class Snake extends MoveableGameObject implements IDrawable, ICollider {

    private String className = "Snake";
    private SnakeHead snakeHead;
    private SnakeBody snakeBody;
    private int size = 20;
    private int growAmount = 0;
    private double addingLocationX;
    private double addingLocationY;
    private int speed = 2;

    public Snake(GameWorld gameWorld) {
        super.setColor(new Color(0, 172, 57));
        super.setLocation(new Location());
        super.setHeading(90);
        super.setSpeed(speed);
        snakeHead = new SnakeHead(getLocation().getLocationX(), getLocation().getLocationY(), getSpeed(), getHeading(), getColor(), size, gameWorld);
        snakeBody = new SnakeBody(getLocation().getLocationX(), getLocation().getLocationY(), getSpeed(), getHeading(), getColor(), size);

    }


    //I want the whole snake to move together and to ensure BodySegments don't move when food is eaten.
    @Override
    public void move(int timeRate){
        snakeHead.move(timeRate);
        if(growAmount <= 0){
            if(hasSpaceForSegment()){
                snakeBody.move(addingLocationX, addingLocationY);
                snakeHead.setJustTurned(false);
                snakeHead.setCanTurn(true);
            }else{
                snakeHead.setCanTurn(false);
            }
        }else{
            if(hasSpaceForSegment()) {
                snakeBody.add(0, new BodySegment(addingLocationX, addingLocationY, snakeHead.getSpeed(), snakeHead.getHeading(), getColor(), size));
                growAmount--;
                snakeHead.setJustTurned(false);
                snakeHead.setCanTurn(true);
            }else{
                snakeHead.setCanTurn(false);
            }
        }

    }

    private boolean hasSpaceForSegment() {
        double neededDistance;
        int distanceOffset = this.getSpeed()*2;
        if(snakeHead.isJustTurned()){
            neededDistance = size+this.getSpeed();
        }else{
            neededDistance = size*2+distanceOffset;
        }
        BodySegment frontOfSnake = snakeBody.getFrontOfSnakeBody();
        double x1 = frontOfSnake.getLocation().getLocationX();
        double y1 = frontOfSnake.getLocation().getLocationY();
        double x2 = snakeHead.getLocation().getLocationX();
        double y2 = snakeHead.getLocation().getLocationY();
        double xdistance = x2 - x1;
        double ydistance = y2 - y1;
        switch (snakeHead.getHeading()){
            case 0:
                if(xdistance > neededDistance){
                    addingLocationY = y2;
                    addingLocationX = x2-size-distanceOffset;
                    return true;
                }
                break;
            case 180:
                if(Math.abs(xdistance) > neededDistance){
                    addingLocationY = y2;
                    addingLocationX = x2+size+distanceOffset;
                    return true;
                }
                break;
            case 270:
                if(Math.abs(ydistance) > neededDistance){
                    addingLocationY = y2+size+distanceOffset;
                    addingLocationX = x2;
                    return true;
                }
                break;
            case 90:
                if(ydistance > neededDistance){
                    addingLocationY = y2-size-distanceOffset;
                    addingLocationX = x2;
                    return true;
                }
                break;
        }
        return false;
    }

    public void ateFood(int value){
        growAmount += value;
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    @Override
    public void setLocation(Location l){
        //Empty
    }
    @Override
    public void setColor(Color c){
        //Empty
    }
    @Override
    public void setHeading(int i){
        //Empty
    }
    @Override
    public void setSpeed(int i){
        //Empty
    }

    public String toString(){
        return className+":\n"+snakeHead.toString()+"\n"+snakeBody.toString();
    }

    @Override
    public void draw(Graphics g) {
        snakeHead.draw(g);
        snakeBody.draw(g);
    }

    @Override
    public boolean collidesWith(ICollider c) {
        if(c instanceof Snake){
            Iterator snakeBodyIterator = ((Snake) c).snakeBody.iterator();
            while(snakeBodyIterator.hasNext()){
                if(snakeHead.collidesWith((ICollider)snakeBodyIterator.next())){
                    return true;
                }
            }
            return false;
        }
        return snakeHead.collidesWith(c);
    }

    @Override
    public void handleCollision(ICollider c) {
        snakeHead.handleCollision(c);
    }

    @Override
    public RectangularShape getBounds() {
        return null;
    }
}
