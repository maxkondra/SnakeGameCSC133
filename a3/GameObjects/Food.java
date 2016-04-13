package a3.GameObjects;

import a3.Interfaces.ICollider;
import a3.Interfaces.IDrawable;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.util.Random;

/**
 * Created by Max on 10/5/2014.
 */
public class Food extends FixedGameObject implements IDrawable, ICollider {

    private String className = "Food";
    private int value;
    private int maxValue = 10;
    private int minValue = 1;
    private int size = 20;

    public Food() {
        super.setLocation(new Location());
        this.value = new Random().nextInt((maxValue - minValue)+1)+minValue;
        super.setColor(new Color(4, 112, 0));
    }

    @Override
    public void setLocation(Location l){
        //Empty
    }
    @Override
    public void setColor(Color c){
        //Empty
    }

    public int getValue() {
        return value;
    }

    public String toString(){
        return className+super.toString()+"   Value: "+value;
    }

    @Override
    public void draw(Graphics g) {
        Location l = getLocation();
        g.setColor(getColor());
        g.fillOval((int) l.getLocationX()-size/2, (int) l.getLocationY()-size/2, size, size);
    }

    public RectangularShape getBounds(){
        Ellipse2D.Double e = new Ellipse2D.Double(getLocation().getLocationX()-size/2,getLocation().getLocationY()-size/2,size, size);
        return e;
    }

    @Override
    public boolean collidesWith(ICollider c) {
        return false;
    }

    @Override
    public void handleCollision(ICollider c) {

    }
}
