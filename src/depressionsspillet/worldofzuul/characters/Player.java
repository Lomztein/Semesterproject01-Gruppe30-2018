/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.Item;
import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.Attacker;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.DamageType;
import depressionsspillet.worldofzuul.observables.Observable;
import java.util.ArrayList;

/**
 * @author Joachim
 */
public class Player extends Character implements Attacker, HasHealth, Damagable {

    private double happinesslevel = 0;
    private Damagable engagedWith;
    private final ArrayList<Attack> availableAttacks = new ArrayList<>();

    Item[] inventory = new Item[4];

    public Player(String name, String description, Room startingRoom) {
        super(name, description, startingRoom);

        ArrayList<DamageResistance> playerResistances = new ArrayList<>();
        for (DamageType type : DamageType.values()) {
            if (type != DamageType.MENTAL || type != DamageType.ANY) {
                playerResistances.add(new DamageResistance(type, "takes no damage, since they are already dead inside.", 0d));
            } else {
                playerResistances.add(new DamageResistance(type, "takes a massive %.2f damage due to their crippling insecurities being exposed.", 2d));
            }
        }
        
        this.resistances = playerResistances.toArray(new DamageResistance[0]);
    }

    @Override
    public double getHealth() {
        return happinesslevel;
    }

    public void engage(Damagable damagable) {
        engagedWith = damagable;
    }

    public void disengage() {
        engagedWith = null;
    }

    public Damagable getEngaged() {
        return engagedWith;
    }

    public boolean isEngaged() {
        return engagedWith != null;
    }

    public void addAttack(Attack newAttack) {
        availableAttacks.add(newAttack);
    }

    public Attack getAttack(String attackName) {
        Attack attack = null;
        for (Attack att : availableAttacks) {
            if (att.getName().toLowerCase().equals(attackName.toLowerCase())) {
                attack = att;
            }
        }
        return attack;
    }

    public void attackEngaged(Attack attack) {
        if (attack != null) {
            attack.doDamage(this, engagedWith);
        }
    }

    /*@Override
    public void takeDamage(Damage damage) {
        onDamaged.execute(damage);
        if (damage.getDamageType() == DamageType.MENTAL) {
            happinesslevel -= damage.getDamageValue();
        }
    }*/
    //Methods
    public void printInventoryList() {
        int i = 1;
        for (Item item : inventory) {

            if (item != null) {
                System.out.println(i + ",  " + item.getName());
            } else {
                System.out.println(i + ",  Empty");
            }
        }
    }

    public void addToInventory(Item item, int index) {
        printInventoryList();

        //For testing purposes, should run through parser in end-version
        System.out.println("\n\nSelect a slot to insert " + item.getName() + " into: ");

        while (index > 4 || index < 1) {
            System.out.print("Wrong input, try again: \n> ");
        }

        inventory[index] = item;

    }

    public void dropItem(int i) {
        printInventoryList();

        System.out.println("\n\nSelect an item to drop: ");

    }

    @Override
    public void setHealth(double value) {
        happinesslevel = value;
    }

    @Override
    public void changeHealth(double value) {
        happinesslevel += value;
    }

    @Override
    public Attack[] getAttacks() {
        return availableAttacks.toArray(new Attack[availableAttacks.size()]);
    }
}
