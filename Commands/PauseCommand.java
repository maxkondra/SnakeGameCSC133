package a3.Commands;

/**
 * Created by Max on 11/30/2014.
 */

import a3.Controllers.Game;
import a3.GameObjects.GameObject;
import a3.Interfaces.ICollider;
import a3.Interfaces.ISelectable;
import a3.Model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;


/**
 * Created by Max on 10/28/2014.
 */
public class PauseCommand extends AbstractAction {

    private GameWorld gameWorld;
    private Game game;


    public PauseCommand(GameWorld gameWorld, Game game) {
        super("Pause");
        this.gameWorld = gameWorld;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(!gameWorld.isOnPause()){
            disableCommands();
            gameWorld.pause();
            b.setText("Play");
        }else{
            Iterator gameObjectsIterator = gameWorld.getGameObjects().iterator();
            while (gameObjectsIterator.hasNext()) {
                GameObject next = (GameObject) gameObjectsIterator.next();
                if (next instanceof ISelectable)
                    if(next instanceof ICollider){
                        ISelectable t = (ISelectable) next;
                        t.setSelected(false);
                    }
            }
            enableCommands();
            gameWorld.resume();
            b.setText("Pause");
        }
    }

    private void disableCommands() {
        game.disableCommands();
    }

    private void enableCommands() {
        game.enableCommands();
    }

}

