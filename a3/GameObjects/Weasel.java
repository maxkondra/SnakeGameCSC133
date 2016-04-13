package a3.GameObjects;

import a3.Interfaces.ICollider;
import a3.Interfaces.IDrawable;
import a3.Interfaces.ISelectable;
import a3.Interfaces.IStrategy;
import a3.Strategies.WeaselFollowStrategy;
import a3.Strategies.WeaselWalkStrategy;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Created by Max on 10/25/2014.
 */
public class Weasel extends MoveableGameObject implements IDrawable, ICollider, ISelectable {

    private String className = "Weasel";
    private Random random = new Random();
    private IStrategy strategy;
    private SnakeHead snakeHead;
    private int size = 15;
    private int switchStrategyCounter = 0;
    private int switchEveryXSeconds = 3;
    private boolean selected;

    public Weasel(SnakeHead snakeHead) {
        super.setHeading(random.nextInt(360));
        super.setColor(new Color(100, 69, 39));
        super.setLocation(new Location());
        this.snakeHead = snakeHead;
        super.setSpeed(random.nextInt(snakeHead.getSpeed()-1)+1);
        strategy = newStrategy();
        selected = false;
    }

    private IStrategy newStrategy() {
        int select = random.nextInt(2);
        switch (select){
            case 0:
                return new WeaselWalkStrategy(this);
            case 1:
                return new WeaselFollowStrategy(this);
        }
        return new WeaselWalkStrategy(this);
    }

    @Override
    public void move(int timeRate){
        int switchAt = switchEveryXSeconds*1000/timeRate;
        if(switchStrategyCounter<switchAt){
            switchStrategyCounter++;
        }else{
            switchStrategyCounter=0;
            changeStrategy();
        }
        strategy.apply(timeRate);
    }

    public void keepMoving(int timeRate){
        super.move(timeRate);
    }

    //Will create a more elegant strategy switcher once I get more strategies.
    public void changeStrategy(){
        if(strategy.strategyID() == 0){
            strategy = new WeaselFollowStrategy(this);
        }else{
            strategy = new WeaselWalkStrategy(this);
        }
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
        super.setHeading(i);
    }
    @Override
    public void setSpeed(int i){
        //Empty
    }

    public String toString(){
        return className+super.toString()+"   Strategy: "+strategy.toString();
    }

    @Override
    public void draw(Graphics g) {
        int newX = (int)getLocation().getLocationX() - size;
        int newY = (int)getLocation().getLocationY() - size/2;
        Graphics2D g2D = (Graphics2D) g;
        Color c = getColor();
        Color newColor;
        if(isSelected()){
            newColor = new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
        }else{
            newColor = c;
        }
        g2D.setColor(newColor);
        g2D.setStroke(new BasicStroke(5F));
        g2D.drawRect(newX, newY, size * 2, size);
    }

    public Rectangle2D.Double getBounds(){
        Rectangle2D.Double e = new Rectangle2D.Double(getLocation().getLocationX()-size,getLocation().getLocationY()-size/2,size*2, size);
        return e;
    }

    @Override
    public boolean collidesWith(ICollider c) {
        if(c instanceof Wall)
            return c.getBounds().intersects(getBounds());
        return false;
    }

    @Override
    public void handleCollision(ICollider c) {
        if(c instanceof Wall){
            switch (((Wall) c).getSide()){
                case 'N':
                    hitNorthWall();
                    break;
                case 'S':
                    hitSouthWall();
                    break;
                case 'E':
                    hitEastWall();
                    break;
                case 'W':
                    hitWestWall();
                    break;
            }
        }

    }

    private void hitNorthWall(){
        setHeading(360-getHeading());
    }

    private void hitSouthWall(){
        setHeading(360 - getHeading());
    }

    private void hitEastWall(){
        if(getHeading() > 90 && getHeading() < 180) {
            setHeading(getHeading()-90);
        }else if(getHeading() > 180 && getHeading() < 270){
            setHeading(getHeading()+90);
        }
    }

    private void hitWestWall(){
        if(getHeading() < 90 && getHeading() > 0) {
            setHeading(getHeading()+90);
        }else if(getHeading() > 270 && getHeading() < 360){
            setHeading(getHeading()-90);
        }
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean b) {
        selected = b;
    }

}
