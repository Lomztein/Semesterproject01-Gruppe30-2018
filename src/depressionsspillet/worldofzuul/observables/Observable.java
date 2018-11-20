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
 */
public class Observable {
    
    private ArrayList<Observer> observers = new ArrayList<>();
        
    public void add (Observer observer) {
        observers.add (observer);
    }
    
    public void remove (Observer observer) {
        observers.remove (observer);
    }
    
    
    public void notify (Object source) {
        for (Observer observer : observers) {
            observer.onNotify (new Event (source, this));
        }
    }
    
}
