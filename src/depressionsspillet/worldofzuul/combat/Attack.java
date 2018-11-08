/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.combat;

import depressionsspillet.worldofzuul.Named;

/**
 *
 * @author Lomztein
 */
public class Attack {
    
    private final DamageType damageType;
    private final double damageValue;
    private final String name;
    private final String description;
    
    public void doDamage (Attacker attacker, Damagable damagable) {
        System.out.println (attacker.getName() + " attacks " + damagable.getName() + " using " + getName () + ", doing " + damageType.name() + " damage.");
        damagable.takeDamage(new Damage (attacker, damageType, damageValue));
    }
    
    public Attack (DamageType damageType, double damageValue, String name, String description) {
        this.damageType = damageType;
        this.damageValue = damageValue;
        this.name = name;
        this.description = description;
    }
    
    public String getName () {
        return name;
    }
    
    public String getDescription () {
        return description;
    }
    
}
