package a3.Strategies;

import a3.GameObjects.Weasel;
import a3.Interfaces.IStrategy;

/**
 * Created by Max on 10/28/2014.
 */
public class WeaselFollowStrategy implements IStrategy {
    private Weasel weasel;
    private int strategyID = 1;

    public WeaselFollowStrategy(Weasel weasel) {
        this.weasel = weasel;
    }

    @Override
    public void apply(int timeRate) {
        weasel.setHeading(
                (int)GetAngleOfLineBetweenTwoPoints(
                weasel.getLocation().getLocationX(),
                weasel.getLocation().getLocationY(),
                weasel.getSnakeHead().getLocation().getLocationX(),
                weasel.getSnakeHead().getLocation().getLocationY()));
        weasel.keepMoving(timeRate);
    }

    @Override
    public int strategyID() {
        return strategyID;
    }

    @Override
    public String toString(){
        return "Following Snake Head";
    }

    private double GetAngleOfLineBetweenTwoPoints(double x1, double y1, double x2, double y2) {
        double xDiff = x2 - x1;
        double yDiff = y2 - y1;
        return Math.toDegrees(Math.atan2(yDiff, xDiff));
    }

}
