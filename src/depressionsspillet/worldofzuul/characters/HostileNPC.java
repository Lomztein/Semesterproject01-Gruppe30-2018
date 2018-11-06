/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.Damagable;
import depressionsspillet.worldofzuul.HasHealth;
import depressionsspillet.worldofzuul.Interaction;
import depressionsspillet.worldofzuul.NPC;
import depressionsspillet.worldofzuul.Room;

/**
 *
 * @author Lomztein
 */
public class HostileNPC extends NPC implements Damagable, HasHealth {

    private double health;

    public HostileNPC(String name, String desc, Room startingRoom, double health) {
        super(name, desc, startingRoom, true);
        this.health = health;
    }

    @Override
    public void takeDamage(double damage) {
        health -= damage;
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
