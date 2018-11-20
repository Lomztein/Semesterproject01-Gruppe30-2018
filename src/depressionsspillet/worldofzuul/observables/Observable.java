/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.observables;

import java.util.ArrayList;

/**
 *
 * @author Lomztein
 * @param <T>
 */
public class Observable<T extends Event> {
    
    private final ArrayList<Observer<T>> observers = new ArrayList<>();
        
    public void add (Observer<T> observer) {
        observers.add (observer);
    }
    
    public void remove (Observer<T> observer) {
        observers.remove (observer);
    }
    
    public void notifyObservables (T event) {
        for (Observer observer : observers) {
            observer.onNotify (event.withTarget(this));
        }
    }
    
}
