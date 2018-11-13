/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.ConsumableItem;
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
            i++;
        }
    }

    //Useless method to check whether the inventory is empty or not. 
    public boolean inventoryCheck() {
        boolean isEmpty = true;

        for (Item item : inventory) {
            isEmpty = false;
        }

        return isEmpty;
    }

    public void useItem(int i) {
        i -= 1;
        try {
            if (inventory[i] instanceof ConsumableItem && inventory[i] != null) {
                ConsumableItem item = (ConsumableItem) inventory[i];
                happinesslevel += item.getHealthIncrease();
            } else { //Temporary solution, more conditions will be added later, as more items are added.
                System.out.println("You stuff a handfull of nothing from pocket " + (i + 1) + " in your mouth, and chew for a few seconds.\n\nYou feel just as empty inside as before.");
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            emptyPockets(i + 1);
        }
    }

    public Item addItem(String name) {
        for (Item item : super.getCurrentRoom().getItemArray()) {
            if (name != null && name.equals(item.getName())) {
                return item;
            } else {
                return null;
            }
        }
        return null;
    }

    public void addItem(String name, String integer) {
        Item item = addItem(name);
        int i = Integer.parseInt(integer) - 1;
        
        if (item != null) {
            if (inventory[i] == null) {
                inventory[i] = item;
                getCurrentRoom().removeItem(inventory[i]);
                System.out.println("You pick up the " + item.getName() + " and stuff it in pocket " + i + ".");
            } else {
                System.out.println("You attempt to pry " + item.getName() + "into pocket " + i + ",\nbut it's already full of " + inventory[i].getName() + ". \n\nMaybe you should empty it first.\n");
            }
        } else {
            noItemFound(item.getName());
        }
    }
    
    public void noItemFound(String name) {
        System.out.println("You grab at what you thought was the " + name + ", but there's nothing there.\n\nIt might be best to see a pshycologist if this issue persists.");
    }

    public void dropItem(int i) {
        i -= 1;
        try {
            if (inventory[i] == null) {
                System.out.println("You attempt to drop an empty inventory slot on the ground. You check around to see \nif anyone saw that. Somehow, someone did. The shadows are laughing at you.");
            } else {

                getCurrentRoom().addItem(inventory[i]);
                System.out.println("You drop the " + inventory[i].getName() + " on the ground before you.\n");
                inventory[i] = null;

            }
        } catch (ArrayIndexOutOfBoundsException error) {
            emptyPockets(i);
        }
    }

    private void emptyPockets(int i) {
        System.out.println("You reach towards pocket " + (i + 1) + ", but for some reason,\nyou can't find it. Perhaps you should check how many pockets you have first.");
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
