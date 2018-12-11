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
    
    public static Damage NULL_DAMAGE = new Damage (null, null, Attack.NULL_ATTACK);
    
    private final Attacker attacker;
    private final Damagable reciever;
    private final Attack attack;
    
    private final DamageType damageType;
    private final double damageValue;
    
    public Damage (Attacker attacker, Damagable reciever, Attack attack) {
        this.attacker = attacker;
        this.reciever = reciever;
        this.attack = attack;
        this.damageType = attack.getDamageType();
        this.damageValue = attack.getDamageValue ();
    }

    public void doDamage () {
        reciever.takeDamage(this);
    }
    
    public Attacker getAttacker () {
        return attacker;
    }
    
    public Attack getAttack () {
        return attack;
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
