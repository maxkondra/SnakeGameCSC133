package a3.Interfaces;

import java.util.Iterator;

/**
 * Created by Max on 10/5/2014.
 */
public interface ICollection {

    public int size();

    public boolean isEmpty();


    public Iterator iterator();


    public boolean add(Object o);


    public boolean remove(Object o);
}
