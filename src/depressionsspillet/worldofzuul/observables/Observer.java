/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.observables;

/**
 *
 * @author Lomztein
 * @param <T>
 */
public interface Observer<T extends Event> {
    
    void onNotify (T event);
    
}
