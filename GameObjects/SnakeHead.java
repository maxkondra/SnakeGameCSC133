package a3.GameObjects;

import a3.Commands.*;
import a3.Interfaces.ICollider;
import a3.Interfaces.IDrawable;
import a3.Interfaces.ISteerable;
import a3.Model.GameWorld;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * Created by Max on 10/5/2014.
 */
public class SnakeHead extends MoveableGameObject implements ISteerable, IDrawable, ICollider {

    private String className = "SnakeHead";
    private int size;
    private boolean justTurned = false;
    private boolean canTurn = false;
    private int newHeading;
    private boolean headingSet = false;
    private Polygon head;
    private GameWorld gameWorld;

    public SnakeHead(double x, double y, int s, int h, Color c, int size, GameWorld gameWorld) {
        super.setLocation(new Location(x, y));
        super.setSpeed(s);
        super.setHeading(h);
        super.setColor(c);
        this.size = size;
        this.gameWorld = gameWorld;
    }

    @Override
    public void steer(int heading) {
        if(heading == 0 || heading == 90 || heading == 180 || heading == 270) {
            newHeading = heading;
            headingSet = true;
        }
    }

    @Override
    public void move(int timeRate){
        if(canTurn && headingSet){
                super.setHeading(newHeading);
                setJustTurned(true);
                headingSet = false;
        }
        super.move(timeRate);
    }
    public void setCanTurn(boolean canTurn) {
        this.canTurn = canTurn;
    }

    public boolean isJustTurned() {
        return justTurned;
    }

    public void setJustTurned(boolean justTurned) {
        this.justTurned = justTurned;
        this.canTurn = false;
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
        return "\t"+className+super.toString();
    }

    @Override
    public boolean collidesWith(ICollider c) {
        return c.getBounds().intersects((Rectangle2D)getBounds());
    }

    @Override
    public void handleCollision(ICollider c) {
        if(c instanceof Snake){
            new BodyCollisionCommand(gameWorld).execute();
        }else if(c instanceof Bird){
            new BirdCollisionCommand(gameWorld).execute();
        }else if(c instanceof Money){
            new MoneyCollisionCommand(gameWorld).execute((Money) c);
        }else if(c instanceof Food){
            new FoodCollisionCommand(gameWorld).execute((Food) c);
        }else if(c instanceof Wall){
            new WallCollisionCommand(gameWorld).execute();
        }else if(c instanceof Weasel){
            new WeaselCollisionCommand(gameWorld).execute();
        }
    }

    public RectangularShape getBounds(){
        return head.getBounds2D();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        int x = (int)getLocation().getLocationX();
        int y = (int)getLocation().getLocationY();
        int height = size;

        int xpoints90and270[] = {x,x-size/2,x+size/2};
        int xpoints180[] = {x+size/2,x+size/2,x-size/2};
        int xpoints0[] = {x-size/2,x-size/2,x+size/2};

        int ypoints90[] = {y+height/2,y-height/2,y-height/2};
        int ypoints0and180[] = {y+height/2,y-height/2,y};
        int ypoints270[] = {y-height/2,y+height/2,y+height/2};

        int xpoints[] = new int[3];
        int ypoints[] = new int[3];
        int npoints = 3;
        switch (getHeading()){
            case 270:
                xpoints = xpoints90and270;
                ypoints = ypoints270;
                break;
            case 180:
                xpoints = xpoints180;
                ypoints = ypoints0and180;
                break;
            case 0:
                xpoints = xpoints0;
                ypoints = ypoints0and180;
                break;
            case 90:
                xpoints = xpoints90and270;
                ypoints = ypoints90;
                break;
        }
        g2D.setColor(getColor());
        Polygon p = new Polygon(xpoints, ypoints, npoints);
        g2D.fillPolygon(p);
        head = p;


    }
}
