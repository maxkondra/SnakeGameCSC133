package a3.GameObjects;

import a3.Interfaces.ICollider;
import a3.Interfaces.IDrawable;
import a3.Interfaces.ISelectable;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.util.Random;

/**
 * Created by Max on 10/5/2014.
 */
public class Bird extends MoveableGameObject implements IDrawable, ICollider, ISelectable {

    private String className = "Bird";
    private int size;
    private int maxSpeed = 2;
    private Random random = new Random();
    private boolean selected;

    public Bird() {
        super.setHeading(random.nextInt(360));
        super.setSpeed(random.nextInt(maxSpeed)+1);
        size = random.nextInt(20)+10;
        super.setColor(new Color(100, 100, 100));
        super.setLocation(new Location());
        selected = false;
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
        return className+super.toString()+"   Size: "+size;
    }

    @Override
    public void draw(Graphics g) {
        Color c = getColor();
        Color newColor;
        if(isSelected()){
            newColor = new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
        }else{
            newColor = c;
        }
        g.setColor(newColor);
        g.fillOval((int)this.getLocation().getLocationX()-size, (int)this.getLocation().getLocationY()-size/2, size*2, size);
    }

    public RectangularShape getBounds(){
        Ellipse2D.Double e = new Ellipse2D.Double(getLocation().getLocationX()-size,getLocation().getLocationY()-size/2,size*2, size);
        return e;
    }

    @Override
    public boolean collidesWith(ICollider c) {
        //TODO
        return false;
    }

    @Override
    public void handleCollision(ICollider c) {
        //TODO
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
