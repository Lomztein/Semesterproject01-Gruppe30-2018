package depressionsspillet.worldofzuul.characters;

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

    public Damage attack(Damagable damagable) {
        Attack random = getRandomAttack();
        return random.attack(this, damagable);
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
    public String getName () {
        if (getHealth ().isDead()) {
            return super.getName () + " (dead)";
        }
        return super.getName();
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public Attack[] getAttacks() {
        return availableAttacks;
    }

    @Override
    public boolean isHostile() {
        return true;
    }

}
