package a3.GameObjects;

import a3.Interfaces.ICollection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Max on 10/28/2014.
 */
public class GameObjectCollection implements ICollection{
    private String className = "GameObjectCollection";
    private ArrayList<GameObject> gameObjects;

    public GameObjectCollection() {
        gameObjects = new ArrayList<GameObject>();
    }

    public int size() {
        return gameObjects.size();
    }

    public boolean isEmpty() {
        return gameObjects.isEmpty();
    }


    public Iterator iterator() {
        return new GameObjectIterator();
    }

    public boolean add(Object o) {
        return gameObjects.add((GameObject) o);
    }

    public void clear(){
        gameObjects.clear();
    }

    public boolean remove(Object o) {
        return gameObjects.remove(o);
    }

    class GameObjectIterator implements Iterator{

        private int currElementIndex;

        public GameObjectIterator() {
            currElementIndex = -1;
        }
        public boolean hasNext() {
            if (gameObjects.size ( ) <= 0)
                return false;
            if (currElementIndex == (gameObjects.size() - 1))
                return false;
            return true;
        }
        public Object next ( ) {
            currElementIndex ++ ;
            return(gameObjects.get(currElementIndex));
        }

        public void remove() {
            gameObjects.remove(currElementIndex);
        }
    }

}
