package a3.Commands;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class UndoCommand extends AbstractAction {
    public UndoCommand() {
        super("Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Undo Clicked.");

    }
}
