/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.combat;

import depressionsspillet.worldofzuul.observables.Event;

/**
 *
 * @author Lomztein
 */
public class DamagedEvent extends Event {

    private final Damage damage;
    private final DamageResistance resistance;
    private final double damageTaken;

    public DamagedEvent(Object source, Damage damage, DamageResistance resistance, double damageTaken) {
        super(source);
        this.damage = damage;
        this.resistance = resistance;
        this.damageTaken = damageTaken;
    }

    public Damage getDamage() {
        return damage;
    }

    public DamageResistance getResistance() {
        if (resistance != null) {
            return resistance;
        }else {
            return DamageResistance.NULL_RESISTANCE;
        }
    }

    public double getDamageTaken() {
        return damageTaken;
    }

}