package a3.Interfaces;


/**
 * Created by Max on 10/26/2014.
 */
public interface IObservable {
    public void addObserver(IObserver obs);
    public void notifyObservers();
}
