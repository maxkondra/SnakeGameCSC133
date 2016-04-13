package a3.GameObjects;

/**
 * Created by Max on 10/5/2014.
 */
public abstract class MoveableGameObject extends GameObject {

    private String className = "MoveableGameObject";
    private int speed;
    private int heading;


    public void move(int timeRate){
        changeLocation(Math.cos(Math.toRadians((double) heading)) * speed * timeRate/10, Math.sin(Math.toRadians((double) heading)) * speed * timeRate/10);
    }

    private void changeLocation(double x, double y){
        double currentX = getLocation().getLocationX();
        double currentY = getLocation().getLocationY();
        double newX = currentX+x;
        double newY = currentY+y;

        setLocation(newX,newY);
    }

    public void setLocation(double x, double y){
        getLocation().setLocationX(x);
        getLocation().setLocationY(y);
    }

    public int getHeading() {
        return heading;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHeading(int heading) {
        while(heading<0)
            heading+= 360;
        while (heading>360)
            heading -= 360;
        this.heading = heading;
    }

    public String toString(){
        return super.toString() + "   Speed: "+speed+"   Heading: "+heading;
    }
}
