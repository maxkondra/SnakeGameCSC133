package a3.Commands;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class SaveGameCommand extends AbstractAction {
    public SaveGameCommand() {
        super("Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Save Clicked.");
    }
}
