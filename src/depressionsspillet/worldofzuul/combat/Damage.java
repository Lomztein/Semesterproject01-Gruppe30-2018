/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.combat;

import depressionsspillet.worldofzuul.combat.DamageType;

/**
 *
 * @author Lomztein
 */
public class Damage {
    
    private DamageType damageType;
    private double damageValue;
    
    public DamageType getDamageType () {
        return damageType;
    }
    
    public double getDamageValue () {
        return damageValue;
    }
    
}
