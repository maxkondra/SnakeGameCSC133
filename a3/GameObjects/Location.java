package a3.GameObjects;

import a3.Model.GameWorld;

import java.util.Random;

/**
 * Created by Max on 10/5/2014.
 */
public class Location {
    private String className = "Location";
    private double locationX;
    private double locationY;

    // Generates a specific location on the Game World
    public Location(double locationX, double locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    // Generates a random location on the Game World
    public Location() {
        double worldSize = GameWorld.getGameWorldSize();
        Random random = new Random();
        this.locationX = (double)random.nextInt((((int)worldSize-50)-50)+1)+50;
        this.locationY = (double)random.nextInt((((int)worldSize-50)-50)+1)+50;
    }

    public double getLocationX() {
        return locationX;
    }

    protected void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    protected void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public Location getLocation(){
        return this;
    }

    public String toString(){
        return className+": ("+locationX+","+locationY+")";
    }
}
