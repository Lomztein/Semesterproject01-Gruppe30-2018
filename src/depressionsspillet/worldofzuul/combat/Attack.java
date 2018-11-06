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
public class Attack {
    
    private final Damage damage;
    private final String name;
    private final String description;
    
    public void doDamage (Damagable damagable) {
        damagable.takeDamage(damage);
    }
    
    public Attack (Damage damage, String name, String description) {
        this.damage = damage;
        this.name = name;
        this.description = description;
    }
    
}
