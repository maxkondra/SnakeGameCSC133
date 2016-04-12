package a3.Views;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Max on 10/27/2014.
 */
public class CommandView extends JPanel {

    public CommandView() {
        setLayout(new GridLayout(10,1));
        setBorder(new TitledBorder("Commands: "));

        setVisible(true);
    }
}
