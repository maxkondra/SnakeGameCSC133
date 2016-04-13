package a3.GameObjects;

import a3.Interfaces.ICollider;
import a3.Interfaces.IColorChangeable;
import a3.Interfaces.IDrawable;
import a3.Interfaces.ISelectable;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.util.Random;

/**
 * Created by Max on 10/5/2014.
 */
public class Money extends FixedGameObject implements IColorChangeable, IDrawable, ICollider, ISelectable {

    private String className = "Money";
    private int value;
    private int maxValue = 100;
    private int minValue = 1;
    private int size = 20;
    private boolean selected;

    public Money() {
        super.setLocation(new Location());
        this.value = new Random().nextInt((maxValue - minValue)+1)+minValue;
        super.setColor(new Color(237, 210, 0));
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void changeColor(Color c) {
        super.setColor(c);
    }

    public String toString(){
        return className+super.toString()+"   Value: "+value;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        Location l = getLocation();
        Color c = getColor();
        Color newColor;
        if(isSelected()){
            newColor = new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
        }else{
            newColor = c;
        }
        g2D.setColor(newColor);
        g2D.setStroke(new BasicStroke(5F));
        g2D.drawOval((int) l.getLocationX()-size/2, (int) l.getLocationY()-size/2, size, size);
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

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean b) {
        selected = b;
    }
}
