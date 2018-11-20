/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.combat.DamageResistance;
import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.interaction.Interaction;
import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.Attacker;
import depressionsspillet.worldofzuul.combat.HasHealth;
import depressionsspillet.worldofzuul.combat.Health;
import depressionsspillet.worldofzuul.observables.Event;
import java.util.Random;

/**
 *
 * @author Lomztein
 */
public class HostileNPC extends NPC implements HasHealth, Attacker {

    private Health health;
    private final Attack[] availableAttacks;

    public HostileNPC(String name, String desc, Room startingRoom, Health health, Attack... availableAttacks) {
        super(name, desc, startingRoom);
        this.health = health;
        this.availableAttacks = availableAttacks;
    }

    private Attack getRandomAttack() {
        Random random = new Random();
        return availableAttacks[random.nextInt(availableAttacks.length)];
    }

    public void attack(Damagable damagable) {
        Attack random = getRandomAttack();
        random.doDamage(this, damagable);
    }

    public void onTakeDamage(Event event) {
        Damage lastTaken = health.getLastDamage();
        if (health.isDead()) {
            if (lastTaken.getAttacker() instanceof Damagable) {
                attack((Damagable) lastTaken.getAttacker());
            }
        }else {
            System.out.println (getName () + " they would retaliate with glee, but they've already been murdered in cold blood.");
        }

    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public Interaction[] getInteractions() {
        return new Interaction[]{};
    }

    @Override
    public Attack[] getAttacks() {
        return availableAttacks;
    }

}
