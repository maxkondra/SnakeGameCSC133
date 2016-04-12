package a3.GameObjects;

import a3.Interfaces.ICollider;
import a3.Interfaces.IDrawable;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * Created by Max on 10/5/2014.
 */
public class BodySegment extends MoveableGameObject implements IDrawable, ICollider {

    private String className = "BodySegment";
    private int size;

    public BodySegment(double x, double y, int speed, int heading, Color c, int size) {
        super.setLocation(new Location(x, y));
        super.setSpeed(speed);
        super.setHeading(heading);
        super.setColor(c);
        this.size = size;
    }

    @Override
    public void move(int timeRate){
        //Empty
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
        return className+super.toString();
    }

    @Override
    public boolean collidesWith(ICollider c) {
        return false;
    }

    @Override
    public void handleCollision(ICollider c) {

    }

    public RectangularShape getBounds(){
        Rectangle2D.Double e = new Rectangle2D.Double(getLocation().getLocationX()-size/2,getLocation().getLocationY()-size/2,size, size);
        return e;
    }

    @Override
    public void draw(Graphics g) {
        int newX = (int)getLocation().getLocationX() - size/2;
        int newY = (int)getLocation().getLocationY() - size/2;
        g.setColor(getColor());
        g.fillRect(newX, newY, size, size);
    }
}
