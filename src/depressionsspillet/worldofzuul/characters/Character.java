/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.combat.DamageResistance;
import depressionsspillet.worldofzuul.Entity;
import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.combat.DamageType;

/**
 * @author Joachim
 */
public abstract class Character implements Entity, Damagable, HasHealth {

    private final String name;
    private final String description;
    private boolean dead;

    DamageResistance[] resistances;

    //private Inventory inventory;
    private Room currentRoom;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean isDead() {
        return dead;
    }

    public void kill() {
        dead = true;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    private DamageResistance getResistanceForType(DamageType damageType) {
        for (DamageResistance resistance : resistances) {
            if (resistance.getDamageType() == DamageType.ANY || resistance.getDamageType() == damageType) {
                return resistance;
            }
        }
        return null;
    }

    @Override
    public void takeDamage(Damage damage) {
        DamageResistance resistance = getResistanceForType(damage.getDamageType());

        double resistanceMul = resistance != null ? resistance.getMultiplier() : 1d;
        double damageValue = damage.getDamageValue() * resistanceMul;

        if (isDead()) {
            System.out.printf ("Stop! Stop! " + name + " is already dead!");
        } else {
            if (resistance == null) {
                System.out.println(String.format (name + " takes a full %.1f damage from attack.", damageValue));
            } else {
                System.out.println(String.format (name + " " + resistance.getResponse(), damageValue));
            }
        }

        changeHealth(-damageValue);
        System.out.println (String.format (getName () + " has %.1f health left.", getHealth ()));
        if (this.getHealth() <= 0) {
            kill();
            System.out.println(name + " dies in agony.");
        }
    }

    public Character(String name, String description, Room currentRoom, DamageResistance... resistances) {
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;
        this.resistances = resistances;
    }

    //GGWP
}
