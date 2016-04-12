package a3.Controllers;

import a3.Commands.*;
import a3.Model.GameWorld;
import a3.Views.CommandView;
import a3.Views.MapView;
import a3.Views.ScoreView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Max on 10/5/2014.
 */
public class Game extends JFrame{
    private GameWorld gameWorld;
    private MapView mapView;
    private ScoreView scoreView;

    //So I can use them in the MenuBar and the Command Panel
    private BodyCollisionCommand bodyCollisionCommand;
    private BirdCollisionCommand birdCollisionCommand;
    private MoneyCollisionCommand moneyCollisionCommand;
    private FoodCollisionCommand foodCollisionCommand;
    private WallCollisionCommand wallCollisionCommand;
    private WeaselCollisionCommand weaselCollisionCommand;
    private ChangeStrategyCommand changeStrategyCommand;
    private PauseCommand pauseCommand;
    private DeleteCommand deleteCommand;
    private QuitCommand quitCommand;
    private NorthCommand northCommand;
    private SouthCommand southCommand;
    private WestCommand westCommand;
    private EastCommand eastCommand;


    public Game() {
        gameWorld = new GameWorld(this);
        mapView = new MapView(gameWorld.getGameWorldProxy());
        scoreView = new ScoreView(gameWorld.getGameWorldProxy());

        bodyCollisionCommand = new BodyCollisionCommand(gameWorld);
        birdCollisionCommand = new BirdCollisionCommand(gameWorld);
        moneyCollisionCommand = new MoneyCollisionCommand(gameWorld);
        foodCollisionCommand = new FoodCollisionCommand(gameWorld);
        wallCollisionCommand = new WallCollisionCommand(gameWorld);
        weaselCollisionCommand = new WeaselCollisionCommand(gameWorld);
        changeStrategyCommand = new ChangeStrategyCommand(gameWorld);
        pauseCommand = new PauseCommand(gameWorld, this);
        deleteCommand = new DeleteCommand(gameWorld, mapView);
        quitCommand = new QuitCommand();

        deleteCommand.setEnabled(false);
        bodyCollisionCommand.setEnabled(false);
        birdCollisionCommand.setEnabled(false);
        moneyCollisionCommand.setEnabled(false);
        foodCollisionCommand.setEnabled(false);
        wallCollisionCommand.setEnabled(false);
        weaselCollisionCommand.setEnabled(false);
        changeStrategyCommand.setEnabled(false);
        pauseCommand.setEnabled(false);
        //UI
        //Basic Setup
        setSize(1000, 900);
        setName("Snake Game!");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Quit requested from X"
                        + " " + e.getSource().getClass());
                // verify intent before exiting by displaying a Yes/No dialog
                int result = JOptionPane.showConfirmDialog
                        (null, // source of event
                                "Are you sure you want to exit ?", // display message
                                "Confirm Exit", // Title bar text
                                JOptionPane.YES_NO_OPTION, // button choices
                                JOptionPane.QUESTION_MESSAGE); // prompt icon
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        };
        addWindowListener(exitListener);

        //Menu
        JMenuBar bar = createMenuBar();
        setJMenuBar(bar);

        //Layout
        setLayout(new BorderLayout());
        add(scoreView, BorderLayout.NORTH);
        add(mapView, BorderLayout.CENTER);

        JPanel commandView = createCommandPanel();
        commandView.setPreferredSize(new Dimension(120,300));
        add(commandView, BorderLayout.WEST);



        //KeyStrokes
        int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap imap = mapView.getInputMap(mapName);
        ActionMap amap = mapView.getActionMap();
        northCommand = new NorthCommand(gameWorld);
        southCommand = new SouthCommand(gameWorld);
        westCommand = new WestCommand(gameWorld);
        eastCommand = new EastCommand(gameWorld);
        ChangeStrategyCommand changeStrategyCommand = new ChangeStrategyCommand(gameWorld);

        KeyStroke upKey = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke downKey = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        KeyStroke leftKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        KeyStroke rightKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        KeyStroke spaceKey = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
        KeyStroke deleteKey = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);

        imap.put(upKey, "north");
        imap.put(downKey, "south");
        imap.put(leftKey, "west");
        imap.put(rightKey, "east");
        imap.put(spaceKey, "space");
        imap.put(deleteKey, "delete");

        amap.put("north", northCommand);
        amap.put("south", southCommand);
        amap.put("west", westCommand);
        amap.put("east", eastCommand);
        amap.put("space", changeStrategyCommand);
        amap.put("delete", deleteCommand);

        this.requestFocus();

        setVisible(true);

    }

    //Creating the menu bar
    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        //File Menu
        bar.add(fileMenu);

        JMenuItem newGame = new JMenuItem("New");
        JMenuItem saveGame = new JMenuItem("Save");
        JMenuItem undoGame = new JMenuItem("Undo");
        JCheckBoxMenuItem sound = new JCheckBoxMenuItem("Sound");
        JMenuItem about = new JMenuItem("About");
        JMenuItem quit = new JMenuItem("Quit");

        fileMenu.add(newGame);
        fileMenu.add(saveGame);
        fileMenu.add(undoGame);
        fileMenu.add(sound);
        fileMenu.add(about);
        fileMenu.addSeparator();
        fileMenu.add(quit);


        NewGameCommand newGameCommand = new NewGameCommand(gameWorld, this);
        SaveGameCommand saveGameCommand = new SaveGameCommand();
        UndoCommand undoCommand = new UndoCommand();
        SoundCommand soundCommand = new SoundCommand(gameWorld);
        AboutCommand aboutCommand = new AboutCommand(this);

        newGame.setAction(newGameCommand);
        saveGame.setAction(saveGameCommand);
        undoGame.setAction(undoCommand);
        sound.setAction(soundCommand);
        about.setAction(aboutCommand);
        quit.setAction(quitCommand);

        //Commands Menu
        JMenu commandMenu = new JMenu("Commands");
        bar.add(commandMenu);
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem bodyCollision = new JMenuItem("Snake hit Body");
        JMenuItem birdCollision = new JMenuItem("Bird hit Snake");
        JMenuItem moneyCollision = new JMenuItem("Snake hit Money");
        JMenuItem foodCollision = new JMenuItem("Snake eats Food");
        JMenuItem wallCollision = new JMenuItem("Snake hit Wall");
        JMenuItem weaselCollision = new JMenuItem("Weasel hit Snake");
        JMenuItem changeStrategy = new JMenuItem("Change Strategy");

        commandMenu.add(delete);
        commandMenu.add(bodyCollision);
        commandMenu.add(birdCollision);
        commandMenu.add(moneyCollision);
        commandMenu.add(foodCollision);
        commandMenu.add(wallCollision);
        commandMenu.add(weaselCollision);
        commandMenu.add(changeStrategy);

        delete.setAction(deleteCommand);
        bodyCollision.setAction(bodyCollisionCommand);
        birdCollision.setAction(birdCollisionCommand);
        moneyCollision.setAction(moneyCollisionCommand);
        foodCollision.setAction(foodCollisionCommand);
        wallCollision.setAction(wallCollisionCommand);
        weaselCollision.setAction(weaselCollisionCommand);
        changeStrategy.setAction(changeStrategyCommand);

        return bar;
    }

    //Creating Command Panel
    private JPanel createCommandPanel() {
        JPanel commandPanel = new CommandView();

        JButton pauseButton = new JButton();
        JButton deleteButton = new JButton();
        JButton quitButton = new JButton();

        //Removing space keystroke from buttons
        pauseButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
        deleteButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
        quitButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");

        //Adding buttons to panel
        commandPanel.add(pauseButton);
        commandPanel.add(deleteButton);
        commandPanel.add(quitButton);

        //Adding actions to buttons
        pauseButton.setAction(pauseCommand);
        deleteButton.setAction(deleteCommand);
        quitButton.setAction(quitCommand);

        return commandPanel;
    }

    public void disableCommands(){
        northCommand.setEnabled(false);
        southCommand.setEnabled(false);
        westCommand.setEnabled(false);
        eastCommand.setEnabled(false);
        bodyCollisionCommand.setEnabled(false);
        birdCollisionCommand.setEnabled(false);
        moneyCollisionCommand.setEnabled(false);
        foodCollisionCommand.setEnabled(false);
        wallCollisionCommand.setEnabled(false);
        weaselCollisionCommand.setEnabled(false);
        changeStrategyCommand.setEnabled(false);
        deleteCommand.setEnabled(true);
    }

    public void enableCommands(){
        northCommand.setEnabled(true);
        southCommand.setEnabled(true);
        westCommand.setEnabled(true);
        eastCommand.setEnabled(true);
        bodyCollisionCommand.setEnabled(true);
        birdCollisionCommand.setEnabled(true);
        moneyCollisionCommand.setEnabled(true);
        foodCollisionCommand.setEnabled(true);
        wallCollisionCommand.setEnabled(true);
        weaselCollisionCommand.setEnabled(true);
        changeStrategyCommand.setEnabled(true);
        deleteCommand.setEnabled(false);
    }

    public void startGame(){
        pauseCommand.setEnabled(true);
        bodyCollisionCommand.setEnabled(true);
        birdCollisionCommand.setEnabled(true);
        moneyCollisionCommand.setEnabled(true);
        foodCollisionCommand.setEnabled(true);
        wallCollisionCommand.setEnabled(true);
        weaselCollisionCommand.setEnabled(true);
        changeStrategyCommand.setEnabled(true);
    }

    public void stopGame(){
        pauseCommand.setEnabled(false);
        bodyCollisionCommand.setEnabled(false);
        birdCollisionCommand.setEnabled(false);
        moneyCollisionCommand.setEnabled(false);
        foodCollisionCommand.setEnabled(false);
        wallCollisionCommand.setEnabled(false);
        weaselCollisionCommand.setEnabled(false);
        changeStrategyCommand.setEnabled(false);
    }



}
