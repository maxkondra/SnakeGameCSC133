package a3.Interfaces;

import java.awt.geom.RectangularShape;

/**
 * Created by Max on 11/21/2014.
 */
public interface ICollider {
    public boolean collidesWith(ICollider c);
    public void handleCollision(ICollider c);
    public RectangularShape getBounds();
}
