/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.combat.DamageType;
import depressionsspillet.worldofzuul.interaction.Interaction;
import depressionsspillet.worldofzuul.Room;

/**
 *
 * @author Lomztein
 */
public class HostileNPC extends NPC implements Damagable, HasHealth {

    private double health;
    private DamageType[] effectiveDamageTypes;
    private static final double INEFFECTIVE_DAMAGE_MULTIPLIER = 0.2d;

    public HostileNPC(String name, String desc, Room startingRoom, double health, DamageType... effectiveDamageTypes) {
        super(name, desc, startingRoom, true);
        this.health = health;
        this.effectiveDamageTypes = effectiveDamageTypes;
    }
    
    private boolean isEffectiveDamageType (DamageType damageType) {
        for (DamageType type : effectiveDamageTypes) {
            if (type == damageType)
                return true;
        }
        return false;
    }

    @Override
    public void takeDamage(Damage damage) {
        if (isEffectiveDamageType (damage.getDamageType ())) {
            health -= damage.getDamageValue();
        }else{
            health -= damage.getDamageValue () * INEFFECTIVE_DAMAGE_MULTIPLIER;
        }
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public Interaction[] getInteractions() {
        return new Interaction[]{
            new Interaction("Engage", "Attack " + this.getName() + " with spirit and vigor!", () -> {}),
        };
    }

}
