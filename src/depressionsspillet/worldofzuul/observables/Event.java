/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.observables;

/**
 *
 * @author Lomztein
 */
public class Event {
    
    private final Object source;
    private Object target;
    
    public Event (Object source) {
        this.source = source;
    }
    
    protected Event withTarget (Object target) {
        this.target = target;
        return this;
    }
    
    public Object getSource () {
        return source;
    }
    
    public Object getTarget () {
        return target;
    }
    
}
