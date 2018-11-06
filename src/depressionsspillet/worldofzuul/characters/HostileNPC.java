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
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.Attacker;
import java.util.Random;

/**
 *
 * @author Lomztein
 */
public class HostileNPC extends NPC implements Attacker {

    private double health;
    private final Attack[] availableAttacks;

    public HostileNPC(String name, String desc, Room startingRoom, double health, DamageResistance[] resistances, Attack[] availableAttacks) {
        super(name, desc, startingRoom, resistances);
        this.health = health;
        this.availableAttacks = availableAttacks;
    }
    
    private Attack getRandomAttack () {
        Random random = new Random ();
        return availableAttacks [random.nextInt(availableAttacks.length)];
    }
    
    public void attack (Damagable damagable) {
        Attack random = getRandomAttack ();
        random.doDamage(this, damagable);
    }
    
    @Override
    public void takeDamage (Damage damage) {
        super.takeDamage(damage);
        if (damage.getAttacker() instanceof Damagable) {
            attack ((Damagable)damage.getAttacker());
        }
    }

    @Override
    public double getHealth() {
        return health;
    }
    
    @Override
    public void setHealth (double value) {
        health = value;
    }
    
    @Override
    public void changeHealth (double value) {
        health += value;
    }

    @Override
    public Interaction[] getInteractions() {
        return new Interaction[]{
            new Interaction("Engage", "You attack " + this.getName() + " with spirit and vigor!", (x) -> {x.engage(this);}),
        };
    }

    @Override
    public Attack[] getAttacks() {
        return availableAttacks;
    }

}
