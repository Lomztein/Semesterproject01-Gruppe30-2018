package depressionsspillet.worldofzuul.observables;

import java.util.ArrayList;

public class Observable<T extends Event> {
    
    private final ArrayList<Observer<T>> observers = new ArrayList<>();
        
    public void add (Observer<T> observer) {
        observers.add (observer);
    }
    
    public void remove (Observer<T> observer) {
        observers.remove (observer);
    }
    
    public void remove (int index) {
        observers.remove(index);
    }
    
    public void clear () {
        observers.clear();
    }
    
    public void notifyObservables (T event) {
        for (Observer observer : observers) {
            observer.onNotify (event.withTarget(this));
        }
    }
    
}
