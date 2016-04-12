package a3.GameObjects;

import java.awt.*;

/**
 * Created by Max on 10/5/2014.
 */
public abstract class GameObject {

    private String className = "GameObject";
    private Location location;
    private Color color;


    public Location getLocation() {
        return location;
    }

    public Color getColor() {
        return color;
    }

    protected void setLocation(Location location) {
        this.location = location;
    }

    protected void setColor(Color color) {
        this.color = color;
    }

    public String toString(){
        return ":   "+location.toString()+"   Color: ["+color.getRed()+", "+color.getGreen()+", "+color.getBlue()+"]";
}
}
