/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.interaction.Interaction;
import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.Attacker;
import depressionsspillet.worldofzuul.combat.DamagedEvent;
import depressionsspillet.worldofzuul.combat.HasHealth;
import depressionsspillet.worldofzuul.combat.Health;
import depressionsspillet.worldofzuul.observables.Event;
import java.util.Random;

/**
 *
 * @author Lomztein
 */
public class HostileNPC extends NPC implements HasHealth, Attacker {

    private final Health health;
    private final Attack[] availableAttacks;
    private final boolean retaliate;

    public HostileNPC(String name, String desc, Room startingRoom, boolean retaliate, Health health, Attack... availableAttacks) {
        super(name, desc, startingRoom);
        this.health = health;
        this.availableAttacks = availableAttacks;
        this.retaliate = retaliate;
        health.onTakeDamage.add(e
                -> {
            System.out.println (health.getCurrentHealth());
            if (!health.isDead() && retaliate) {
                if (e.getDamage().getAttacker() instanceof Damagable) {
                    attack((Damagable) e.getDamage().getAttacker());
                }
            }
        });
    }

    private Attack getRandomAttack() {
        Random random = new Random();
        return availableAttacks[random.nextInt(availableAttacks.length)];
    }

    public void attack(Damagable damagable) {
        Attack random = getRandomAttack();
        random.attack(this, damagable);
    }

    public void onTakeDamage(Event event) {
        Damage lastTaken = health.getLastDamage();
        if (health.isDead()) {
            if (lastTaken.getAttacker() instanceof Damagable) {
                attack((Damagable) lastTaken.getAttacker());
            }
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
