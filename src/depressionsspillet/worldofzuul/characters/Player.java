package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.combat.HasHealth;
import depressionsspillet.worldofzuul.ConsumableItem;
import depressionsspillet.worldofzuul.Item;
import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.Attacker;
import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.combat.DamageResistance;
import depressionsspillet.worldofzuul.combat.DamageType;
import depressionsspillet.worldofzuul.combat.Health;
import depressionsspillet.worldofzuul.observables.Observable;
import java.util.ArrayList;

public class Player extends Character implements Attacker, HasHealth {

    private Damagable engagedWith;
    private final ArrayList<Attack> availableAttacks = new ArrayList<>();
    private final Health playerHealth;
    private int happiness = 0;

    private final ArrayList<Item> inventory = new ArrayList<>();

    public Player(String name, String description, Room startingRoom) {
        super(name, description, startingRoom);
        playerHealth = new Health(69);

        generatePlayerResistances();

        addAttack(new Attack(DamageType.DAB, 5, "dab", "a profound dab"));
        addAttack(new Attack(DamageType.BLUNT, 20, "punch", "a rather weak, yet beautifully spirited punch"));
    }

    private void generatePlayerResistances() {
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

    private Damage getLastAttack() {
        if (engagedWith != null && engagedWith instanceof HasHealth) {
            return ((HasHealth) engagedWith).getHealth().getLastDamage();
        }
        return Damage.NULL_DAMAGE;
    }

    public double getLastAttackDamageValue() {
        if (getEngagedAsHasHealth() == null) {
            return getLastAttack().getDamageValue();
        } else {
            return getLastAttack().getDamageValue() * getEngagedAsHasHealth().getHealth().getResistanceForType(getLastAttack().getDamageType()).getMultiplier();
        }
    }

    public String getLastAttackName() {
        return getLastAttack().getAttack().getName();
    }

    public String getLastAttackDescription() {
        return getLastAttack().getAttack().getDescription();
    }

    public String getLastAttackType() {
        return getLastAttack().getDamageType().name();
    }

    public HasHealth getEngagedAsHasHealth() {
        if (engagedWith != null && engagedWith instanceof HasHealth) {
            return (HasHealth) engagedWith;
        }
        return null;
    }

    public String getLastAttackDamageResponse() {
        if (engagedWith != null && engagedWith instanceof HasHealth) {
            HasHealth engagedWithHealth = ((HasHealth) engagedWith);
            return engagedWithHealth.getHealth().getResistanceForType(engagedWithHealth.getHealth().getLastDamage().getDamageType()).getResponse();
        }
        return "";
    }

    public double getLastAttackedHealth() {
        if (engagedWith != null && engagedWith instanceof HasHealth) {
            return ((HasHealth) engagedWith).getHealth().getCurrentHealth();
        }
        return -1;
    }

    public String[] getInventoryItemNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Item item : inventory) {
            names.add(item.getName());
        }
        return names.toArray(new String[0]);
    }

    //Returns the item descriptions in the player inventory.
    public String[] getInventoryItemDescriptions() {
        ArrayList<String> descriptions = new ArrayList<>();
        for (Item item : inventory) {
            descriptions.add(item.getDescription());
        }
        return descriptions.toArray(new String[0]);
    }

    public String[] getAttackNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Attack attack : this.getAttacks()) {
            names.add(attack.getName());
        }
        return names.toArray(new String[0]);
    }

    public String[] getAttackDescriptions() {
        ArrayList<String> descriptions = new ArrayList<>();
        for (Attack attack : this.getAttacks()) {
            descriptions.add(attack.getDescription());
        }
        return descriptions.toArray(new String[0]);
    }

    public int getHappiness() {
        return this.happiness;
    }

    public void addHappiness(int value) {
        this.happiness += value;
    }

    public void addHealth(double value) {
        getHealth().changeHealth(value);
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
            if (attackName != null && att.getName().toLowerCase().equals(attackName.toLowerCase())) {
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

    //Prints the full list of the inventory for the CLI
    public String printInventoryList() {
        int i = 1;
        String printOut = "";

        //Checks if the inventory is empty, and exits if it is.
        if (inventoryCheck()) {
            return ("It's all empty, like your soul.");
        }

        //Continues with checking every item in the inventory.
        for (Item item : inventory) {

            if (item != null) {
                printOut += (i + ",  " + item.getName());
            }
            i++;
        }
        return printOut;
    }

    //>>>>>> Everything that has to do with player inventory has very high coupling, and should definitely be revised!!!! <<<<<< - Not coupling but cohesion mate.
    //Checks whether the inventory is empty or not.
    public boolean inventoryCheck() {

        for (Item item : inventory) {
            return false;
        }
        return true;
    }

    //Uses the item, adds buffs and removes it from inventory.
    public String useItem(int i) {
        i -= 1;
        String printOut;
        try {
            if (inventory.get(i) instanceof ConsumableItem) {
                ConsumableItem item = (ConsumableItem) inventory.get(i);
                addHealth(item.getHealthIncrease());
                addHappiness(item.getHappinessIncrease());
                printOut = ("You quickly consume the " + item.getName() + ".\nYou gain " + item.getHealthIncrease() + " health, and " + item.getHappinessIncrease() + " happiness.");
                inventory.remove(i); //At this point the item will be removed from all lists, and should not be accessibe, but it would still exist. It's not very effective.

            } else { //Temporary solution, more conditions will be added later, as more items are added.
                printOut = ("You stuff a handfull of nothing from pocket " + (i + 1) + " in your mouth, and chew for a few seconds.\n\nYou feel just as empty inside as before.");
            }
        } catch (IndexOutOfBoundsException error) {
            return emptyPockets(i + 1);
        }
        return printOut;
    }

    //Method for determining whether an item with a certain name exists in the room. 
    //Is used by the addItem, and nowhere else, at this point.
    public Item currentRoomHasItem(String name) {
        for (Item item : super.getCurrentRoom().getItemArray()) {
            if (name != null && name.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    //Adds item to inventory, and removes it from room's item-array.
    public String addItem(String name) {
        Item item = currentRoomHasItem(name);

        if (item != null) {
            inventory.add(item);
            getCurrentRoom().removeItem(item);
            return ("You pick up the " + item.getName() + " and stuff it in your pocket");

        } else {
            return noItemFound(name);
        }
    }

    //When no item by the name of 'item' was found in the room.
    public String noItemFound(String name) {
        return ("You grab at what you thought was the " + name + ", but there's nothing there.\n\nIt might be best to see a pshycologist if this issue persists.");
    }

    //Drops the item in slot i into the room's item array. 
    public String dropItem(int i) {
        i -= 1;
        String printOut = emptyPockets(i);

        try {

            if (inventory.get(i) != null) {
                getCurrentRoom().addItem(inventory.get(i));
                printOut = ("You drop the " + inventory.get(i).getName() + " on the ground before you.\n");
                inventory.remove(i);
            }

            //Gets called whenever a player attemps to drop something from a slot that is not occupied.
        } catch (IndexOutOfBoundsException error) {
            return printOut;
        }
        return printOut;
    }

    //
    private String emptyPockets(int i) {
        return ("You reach towards pocket " + (i) + ", but for some reason,\nthere's nothing in it. Perhaps you should check your pockets first. Perhaps you're an idiot. Who knows?");
    }

    @Override
    public Attack[] getAttacks() {
        return availableAttacks.toArray(new Attack[availableAttacks.size()]);
    }
}
