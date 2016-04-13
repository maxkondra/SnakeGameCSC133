package a3.Views;

import a3.GameObjects.*;
import a3.Interfaces.*;
import a3.Model.GameWorldProxy;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import static a3.Model.GameWorld.getGameWorldSize;

/**
 * Created by Max on 10/25/2014.
 */
public class MapView extends JPanel implements IObserver, ActionListener, MouseListener {
    GameWorldProxy gameWorldProxy;

    public MapView(GameWorldProxy gameWorldProxy) {
        this.gameWorldProxy = gameWorldProxy;
        this.gameWorldProxy.addObserver(this);
        setBorder(new EtchedBorder());
        setSize((int)getGameWorldSize(),(int)getGameWorldSize());
        setVisible(true);
        addMouseListener(this);
    }

    public void update (IObservable o, Object arg) {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int lives = gameWorldProxy.getLives();
        Iterator gameObjectsIterator = gameWorldProxy.getGameObjects().iterator();
        if(lives > 0) {
            while (gameObjectsIterator.hasNext()) {
                GameObject next = (GameObject) gameObjectsIterator.next();
                if (next instanceof IDrawable)
                    ((IDrawable) next).draw(g);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(gameWorldProxy.isOnPause()){
            boolean clickedObject = false;
            Iterator gameObjectsIterator = gameWorldProxy.getGameObjects().iterator();
            while (gameObjectsIterator.hasNext()) {
                GameObject next = (GameObject) gameObjectsIterator.next();
                if (next instanceof ISelectable) {
                    if (next instanceof ICollider) {
                        ISelectable t = (ISelectable) next;
                        if (((ICollider) next).getBounds().contains(e.getPoint())) {
                            clickedObject = true;
                        }
                    }
                }
            }
            gameObjectsIterator = gameWorldProxy.getGameObjects().iterator();
            while (gameObjectsIterator.hasNext()) {
                GameObject next = (GameObject) gameObjectsIterator.next();
                if (next instanceof ISelectable){
                    if (next instanceof ICollider) {
                        ISelectable t = (ISelectable) next;
                        if(clickedObject){
                            if (((ICollider) next).getBounds().contains(e.getPoint())) {
                                if (!t.isSelected()) {
                                    t.setSelected(true);
                                } else {
                                    t.setSelected(false);
                                }
                                clickedObject = true;
                                repaint();
                            } else {
                                if (!e.isControlDown()) {
                                    t.setSelected(false);
                                }

                                repaint();
                            }
                        }else{
                            t.setSelected(false);
                            repaint();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
