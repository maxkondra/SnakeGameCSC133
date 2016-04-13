package a3.GameObjects;

import a3.Interfaces.ICollection;
import a3.Interfaces.ICollider;
import a3.Interfaces.IDrawable;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Max on 10/5/2014.
 */
public class SnakeBody implements ICollection, IDrawable, ICollider{

    private String className = "SnakeBody";
    private ArrayList<BodySegment> snakeBody;
    private int size;


    public SnakeBody(double x, double y, int s, int h, Color c, int size) {
        this.size = size;
        snakeBody = new ArrayList<BodySegment>();
        createNewBody(x, y, s, h, c);
    }

    private void createNewBody(double x, double y, int s, int h, Color c) {
        snakeBody.add(new BodySegment(x, y-size-2, s, h, c, size));
        snakeBody.add(new BodySegment(x, y-size*2-4, s, h, c, size));
        snakeBody.add(new BodySegment(x, y-size*3-6, s, h, c, size));
    }

    public int size() {
        return snakeBody.size();
    }

    public boolean isEmpty() {
        return snakeBody.isEmpty();
    }

    public Iterator iterator() {
        return new SnakeBodyIterator();
    }

    public BodySegment getFrontOfSnakeBody(){
        return snakeBody.get(0);
    }

    public boolean add(Object o) {
        if(o instanceof BodySegment)
            return snakeBody.add((BodySegment) o);
        else
            return false;
    }

    public boolean add(int position, Object o) {
        if(o instanceof BodySegment){
            snakeBody.add(position, (BodySegment) o);
            return true;
        }
        else {
            return false;
        }
    }


    public boolean remove(Object o) {
        return snakeBody.remove(o);
    }

    public void move(double x, double y){
        double oldX;
        double oldY;
        for (BodySegment b : snakeBody){
            Location l = b.getLocation();
            oldX = l.getLocationX();
            oldY = l.getLocationY();
            b.setLocation(x, y);
            x = oldX;
            y = oldY;
        }
    }

    @Override
    public void draw(Graphics g) {
        for(BodySegment b : snakeBody){
            b.draw(g);
        }
    }

    @Override
    public boolean collidesWith(ICollider c) {
        return false;
    }

    @Override
    public void handleCollision(ICollider c) {

    }

    @Override
    public RectangularShape getBounds() {
        return null;
    }


    class SnakeBodyIterator implements Iterator{

        private int currElementIndex;

        public SnakeBodyIterator() {
            currElementIndex = -1;
        }
        public boolean hasNext() {
            if (snakeBody.size ( ) <= 0)
                return false;
            if (currElementIndex == (snakeBody.size() - 1))
                return false;
            return true;
        }
        public Object next ( ) {
            currElementIndex ++ ;
            return(snakeBody.get(currElementIndex));
        }

        public void remove() {
            snakeBody.remove(currElementIndex);
        }
    }

    public String toString(){
        String printOut = "";
        for(BodySegment b: snakeBody){
            printOut+="\t"+b.toString()+"\n";
        }
        return printOut;
    }

}
