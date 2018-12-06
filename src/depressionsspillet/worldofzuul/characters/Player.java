/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.combat.HasHealth;
import depressionsspillet.worldofzuul.ConsumableItem;
import depressionsspillet.worldofzuul.Item;
import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.Attacker;
import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.DamageResistance;
import depressionsspillet.worldofzuul.combat.DamageType;
import depressionsspillet.worldofzuul.combat.Health;
import java.util.ArrayList;

/**
 * @author Joachim
 */
public class Player extends Character implements Attacker, HasHealth {

    private Damagable engagedWith;
    private final ArrayList<Attack> availableAttacks = new ArrayList<>();
    private final Health playerHealth;
    private int happiness = 10;

    private final ArrayList<Item> inventory = new ArrayList<>();

    public Player(String name, String description, Room startingRoom) {
        super(name, description, startingRoom);
        playerHealth = new Health (1);
        playerHealth.onTakeDamage.add((x) -> System.out.println (String.format("The player " + x.getResistance().getResponse(), x.getDamageTaken())));
    }

    public void generatePlayerResistances() {
        ArrayList<DamageResistance> playerResistances = new ArrayList<>();
        for (DamageType type : DamageType.values()) {
            if (type != DamageType.ANY) {

                // Player is invulnerable to any damage but mental damage, which they take an unnatural amount of damage from.
                if (type != DamageType.MENTAL) {
                    playerResistances.add(new DamageResistance(type, "takes no damage, since they are already dead inside.", 0d));
                } else {
                    playerResistances.add(new DamageResistance(type, "takes a massive %.2f damage due to their crippling insecurities being exposed.", 2d));
                }
            }

        }

        getHealth().withResistances(playerResistances);
    }
    
    public double getLastAttackDamageValue () {
        if (engagedWith != null && engagedWith instanceof HasHealth) {
            return ((HasHealth)engagedWith).getHealth().getLastDamage().getDamageValue();
        }
        return 0; // Perhaps having different layers of "Damagable" was overcomplicating things. Consider merging Damagable and HasHealth.
    }
    
    public String getLastAttackDamageResponse () {
        if (engagedWith != null && engagedWith instanceof HasHealth) {
            HasHealth engagedWithHealth = ((HasHealth)engagedWith);
            return engagedWithHealth.getHealth ().getResistanceForType (engagedWithHealth.getHealth().getLastDamage().getDamageType()).getResponse();
        }
        return "";
    }
    
    public double getLastAttackedHealth () {
        if (engagedWith != null && engagedWith instanceof HasHealth) {
            return ((HasHealth)engagedWith).getHealth().getCurrentHealth();
        }
        return -1;
    }
    
    public String[] getInventoryItemNames () {
        ArrayList<String> names = new ArrayList<>();
        for (Item item : inventory) {
            names.add(item.getName());
        }
        return names.toArray (new String[0]);
    }
    
    public String[] getInventoryItemDescriptions () {
        ArrayList<String> descriptions = new ArrayList<>();
        for (Item item : inventory) {
            descriptions.add(item.getDescription());
        }
        return descriptions.toArray (new String[0]);
    }
    
    public String[] getAttackNames () {
        ArrayList<String> names = new ArrayList<>();
        for (Attack attack : this.getAttacks()) {
            names.add(attack.getName());
        }
        return names.toArray (new String[0]);
    }
    
    public String[] getAttackDescriptions () {
        ArrayList<String> descriptions = new ArrayList<>();
        for (Attack attack : this.getAttacks()) {
            descriptions.add(attack.getDescription());
        }
        return descriptions.toArray (new String[0]);
    }

    public int getHappiness () {
        return this.happiness;
    }
    
    public void setHappiness (int value) {
        this.happiness = value;
    }
    
    public void addHealth (double value) {
        getHealth ().changeHealth(value);
    }

    @Override
    public Health getHealth() {
        return playerHealth;
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
            attack.attack(this, engagedWith);
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

    //>>>>>> Everything that has to do with player inventory has very high coupling, and should definitely be revised!!!! <<<<<<
    
    //Now a useless method to check whether the inventory is empty or not. 
    public boolean inventoryCheck() {
        
        for (Item item : inventory) {
            return false;
        }
        return true;
    }

    public void useItem(int i) {
        i -= 1;
        try {
            if (inventory.get(i) instanceof ConsumableItem && inventory.get(i) != null) {
                ConsumableItem item = (ConsumableItem) inventory.get(i);
                addHealth (item.getHealthIncrease());
            } else { //Temporary solution, more conditions will be added later, as more items are added.
                System.out.println("You stuff a handfull of nothing from pocket " + (i + 1) + " in your mouth, and chew for a few seconds.\n\nYou feel just as empty inside as before.");
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            emptyPockets(i + 1);
        }
    }

    //Method for determining whether an item with a certain name exists in the room. 
    //Is used by the addItem, and nowhere else, at this point.
    public Item currentRoomHasItem(String name) {
        for (Item item : super.getCurrentRoom().getItemArray()) {
            if (name != null && name.equals(item.getName())) {
                return item;
            } else {
                return null;
            }
        }
        return null;
    }

    public void addItem(String name) {
        Item item = currentRoomHasItem(name);

        if (item != null) {
                inventory.add(item);
                getCurrentRoom().removeItem(item);
                System.out.println("You pick up the " + item.getName() + " and stuff it in your pocket");
           
        } else {
            noItemFound(item.getName());
        }
    }
    
    //
    public void noItemFound(String name) {
        System.out.println("You grab at what you thought was the " + name + ", but there's nothing there.\n\nIt might be best to see a pshycologist if this issue persists.");
    }

    public void dropItem(int i) {
        i -= 1;
        try {
            if (inventory.get(i) == null) {
                System.out.println("You attempt to drop an empty inventory slot on the ground. You check around to see \nif anyone saw that. Somehow, someone did. The shadows are laughing at you.");
            } else {

                getCurrentRoom().addItem(inventory.get(i));
                System.out.println("You drop the " + inventory.get(i).getName() + " on the ground before you.\n");
                inventory.set(i, null);

            }
        } catch (ArrayIndexOutOfBoundsException error) {
            emptyPockets(i);
        }
    }
    
    //This won't work anymore, in the sense that it would never be called, as the inventory is now an infinite arraylist.
    private void emptyPockets(int i) {
        System.out.println("You reach towards pocket " + (i + 1) + ", but for some reason,\nthere's nothing in it. Perhaps you should check your pockets first.");
    }

    @Override
    public Attack[] getAttacks() {
        return availableAttacks.toArray(new Attack[availableAttacks.size()]);
    }
}
