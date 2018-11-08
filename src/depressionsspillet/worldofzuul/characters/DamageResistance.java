/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.combat.DamageType;

/**
 *
 * @author Lomztein
 */
public class DamageResistance {
    
    private final DamageType type;
    private final String response;
    private final double multiplier;
    
    public DamageResistance (DamageType type, String response, double multiplier) {
        this.type = type;
        this.response = response;
        this.multiplier = multiplier;
    }
    
    public DamageType getDamageType () {
        return type;
    }
    
    public String getResponse () {
        return response;
    }
    
    public double getMultiplier () {
        return multiplier;
    }
    
}
