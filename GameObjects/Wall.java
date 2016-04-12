package a3.GameObjects;

import a3.Interfaces.ICollider;
import a3.Interfaces.IDrawable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Max on 10/5/2014.
 */
public class Wall extends FixedGameObject implements IDrawable, ICollider{

    private String className = "Wall";
    private int width;
    private int height;
    private char side;

    public Wall(int height, int width, Location location, char side) {
        this.height = height;
        this.width = width;
        super.setLocation(location);
        super.setColor(new Color(0,0,0));
        this.side = side;
    }

    public char getSide() {
        return side;
    }

    @Override
    public void setLocation(Location l){
        //Empty
    }
    @Override
    public void setColor(Color c){
        //Empty
    }

    public String toString(){
        return className+super.toString()+"   Width: "+width+"   Height: "+height;
    }

    @Override
    public void draw(Graphics g) {
        Location l = getLocation();
        g.setColor(this.getColor());
        g.fillRect((int)l.getLocationX()-width/2,(int)l.getLocationY()-height/2,width, height);
    }

    public Rectangle2D.Double getBounds(){
        Rectangle2D.Double e = new Rectangle2D.Double(getLocation().getLocationX()-width/2,getLocation().getLocationY()-height/2,width, height);
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
