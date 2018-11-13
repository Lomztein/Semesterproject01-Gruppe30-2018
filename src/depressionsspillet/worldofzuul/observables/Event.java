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
    
    private Object source;
    private Object target;
    
    public Event (Object source, Object target) {
        this.source = source;
        this.target = target;
    }
    
    public Object getSource () {
        return source;
    }
    
    public Object getTarget () {
        return target;
    }
    
}
