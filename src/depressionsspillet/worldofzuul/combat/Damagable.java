/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.combat;

import depressionsspillet.worldofzuul.Entity;
import depressionsspillet.worldofzuul.Named;

/**
 *
 * @author Lomztein
 */
public interface Damagable extends Entity {
    
    void takeDamage (Damage damage);
    
}
