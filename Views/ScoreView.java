package a3.Views;

import a3.Interfaces.IObservable;
import a3.Interfaces.IObserver;
import a3.Model.GameWorldProxy;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Max on 10/25/2014.
 */
public class ScoreView extends JPanel implements IObserver {
    private int time = 0;
    private int score = 0;
    private int lives = 0;
    private String sound = "OFF";

    private JLabel timeLabel = new JLabel("Time: " +time);
    private JLabel scoreLabel = new JLabel("Score: "+score);
    private JLabel livesLabel = new JLabel("Lives: "+lives);
    private JLabel soundLabel = new JLabel("Sound: "+sound);


    public ScoreView(GameWorldProxy gameWorldProxy) {
        gameWorldProxy.addObserver(this);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        add(timeLabel);
        add(scoreLabel);
        add(livesLabel);
        add(soundLabel);

        setVisible(true);
    }

    @Override
    public void update(IObservable o, Object arg) {
        GameWorldProxy gameWorld = (GameWorldProxy) o;
        int divisor = 1000/((GameWorldProxy) o).getTimeRate();
        time = gameWorld.getGameClock()/divisor;
        score = gameWorld.getScore();
        lives = gameWorld.getLives();
        boolean sound = gameWorld.isSound();
        if(sound){
            this.sound = "ON";
        }else{
            this.sound = "OFF";
        }

        timeLabel.setText("Time: " +time);
        scoreLabel.setText("Score: " +score);
        livesLabel.setText("Lives: " +lives);
        soundLabel.setText("Sound: " +this.sound);

        this.repaint();
    }
}
