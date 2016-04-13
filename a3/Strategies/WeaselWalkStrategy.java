package a3.Strategies;

import a3.GameObjects.Weasel;
import a3.Interfaces.IStrategy;

/**
 * Created by Max on 10/28/2014.
 */
public class WeaselWalkStrategy implements IStrategy {

    private Weasel weasel;
    private int strategyID = 0;

    public WeaselWalkStrategy(Weasel weasel) {
        this.weasel = weasel;
    }

    @Override
    public void apply(int timeRate) {
        weasel.keepMoving(timeRate);

    }

    @Override
    public int strategyID() {
        return strategyID;
    }

    @Override
    public String toString(){
        return "Just Walking";
    }
}
