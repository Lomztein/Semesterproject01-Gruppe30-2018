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
public class Damage {
    
    private final Attacker attacker;
    private final Damagable reciever;
    
    private final DamageType damageType;
    private final double damageValue;
    
    public Damage (Attacker attacker, Damagable reciever, DamageType damageType, double damageValue) {
        this.attacker = attacker;
        this.reciever = reciever;
        this.damageType = damageType;
        this.damageValue = damageValue;
    }

    public void doDamage () {
        reciever.takeDamage(this);
    }
    
    public Attacker getAttacker () {
        return attacker;
    }
    
    public DamageType getDamageType () {
        return damageType;
    }
    
    public double getDamageValue () {
        return damageValue;
    }
    
    public Damagable getReciever () {
        return reciever;
    }
    
}
