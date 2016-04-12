package a3.Commands;

import a3.Controllers.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Max on 10/28/2014.
 */
public class AboutCommand extends AbstractAction {

    private Game game;
    public AboutCommand(Game game) {
        super("About");
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(game,
                "Made by: Max Kondrashov.\nCSC 133\nVersion 3",
                "About",
                JOptionPane.PLAIN_MESSAGE);
    }
}
