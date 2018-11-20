/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.combat;

/**
 *
 * @author Lomztein
 */
public interface HasHealth extends Damagable {
    
    public Health getHealth ();
    
    @Override
    public default void takeDamage (Damage damage) {
        getHealth ().takeDamage(damage);
    }
    
}
