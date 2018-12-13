package depressionsspillet.worldofzuul;

import depressionsspillet.worldofzuul.characters.NPC;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Room {

    // This object is a HashMap, which functions much like an array by containing a list of elements.
    // Differently from an array however, these elements are indexed with a key instead of a number.
    // The types inside <> are known as Type arguments, and declare what types this HashMap deals with.
    // In this case, the "keys" for this hashmap is of type String, and the "values" is of type Room.
    // Types that take these "type arguments" are called generic types, meaining they can work with anything, provided there are no constraints.
    // Contraints in this sense are declarations on the generic that limit what types can be used.
    private HashMap<String, Door> exits;
    private ArrayList<Entity> allEntities;
    private ArrayList<Item> currentItems;

    // Declare instance-variables / attributes for the desciption of the room as well as the connections to other rooms.
    private String name;
    private String description;
    private int happiness;
    ArrayList<Item> itemsInRoom;

    //Constructor
    public Room(String description, String name) {
        currentItems = new ArrayList<>();
        this.description = description;
        this.name = name;
        exits = new HashMap<>();
        allEntities = new ArrayList<>();
    }

    public void printCurrentItems() {
        Item[] itemsInRoom = getEntities(Item.class);
        int itemCounter = 0;
        for (Item item : itemsInRoom) {
            if (itemCounter == 0) {
                System.out.print("On the ground before you lies: ");
            } else {
                System.out.println(", ");
            }
            System.out.print(item.getName());
            itemCounter++;
        }
        if (itemCounter == 0) {
            System.out.println("The room contains no visible items.");
        }

    }

    public ArrayList<Item> getItemArray() {
        return currentItems;
    }

    public String[] getItemNames() {
        ArrayList<Item> items = getItemArray();
        String[] names = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            names[i] = items.get(i).getName();
        }
        return names;
    }

    public String[] getItemDescriptions() {
        ArrayList<Item> items = getItemArray();
        String[] descriptions = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            descriptions[i] = items.get(i).getDescription();
        }
        return descriptions;
    }

    public void addItem(Item item) {
        currentItems.add(item);
    }

    public void addRandomItem() {

    }

    public void removeItem(Item item) {
        currentItems.remove(item);
    }

    public String listItemNames() {
        String names = null;

        for (Item item : currentItems) {

            names += (" " + item.getName() + ", ");
        }
        return names;
    }

    public void setExit(String direction, Room neighbor) {
        setExit(direction, neighbor, false, null);
    }

    // Wrapper function for exits.put, since exits is private.
    public void setExit(String direction, Room neighbor, boolean locked, String lockedReason) {
        Door door = new Door(direction, neighbor, locked, lockedReason);
        if (exits.containsKey(direction)) {
            exits.remove(direction); // Remove it if it already exists, in order to reset it.
        }
        exits.put(direction, door);
    }

    public String getShortDescription() {
        return name;
    }

    //It is preffered for this method to be used instead of instanciating an item, and THEN adding it.
    //This ensures that you don't have floating items that don't exist in any list. 
    public void createItem(String name) {

    }

    public void addEntityToRoom(Entity entity) {
        allEntities.add(entity);
    }

    public void leaveRoom(Entity entity) {
        allEntities.remove(entity);
    }

    public <T extends Entity> T[] getEntities(Class<T> clazz) {
        return allEntities.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList()).toArray((T[]) Array.newInstance(clazz, 0));
    }

    public <T extends Entity> String listEntities(Class<T> clazz) {
        String list = "";
        T[] entities = getEntities(clazz);
        for (T entity : entities) {
            list += entity.getName() + "\n";
        }
        return list;
    }

    public <T extends Entity> String[] getEntityNames(Class<T> clazz) {
        T[] entities = getEntities(clazz);
        String[] names = new String[entities.length];
        for (int i = 0; i < entities.length; i++) {
            names[i] = entities[i].getName();
        }
        return names;
    }

    public <T extends Entity> String[] getEntityDescriptions(Class<T> clazz) {
        T[] entities = getEntities(clazz);
        String[] descriptions = new String[entities.length];
        for (int i = 0; i < entities.length; i++) {
            descriptions[i] = entities[i].getDescription();
        }
        return descriptions;
    }

    public <T extends Entity> T getEntity(Class<T> clazz, String name) {
        for (T entity : getEntities(clazz)) {
            if (entity.getName().toLowerCase().equals(name.toLowerCase())) {
                return entity;
            }
        }
        return null;
    }

    // Return the description + the possible exit routes.
    public String getLongDescription() {

        return description;
    }

    public String getExitString() {
        // Declare a variable for containing the combined result of the upcoming loop.
        String returnString = "You can now go these ways:";

        // Get every single key in the HashMap, for-looping through them.
        // This generic type given a string type argument is much like an array of Strings.
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public boolean[] getIsExitLockedFlags() {
        boolean[] result = new boolean[exits.size()];
        Door[] doors = exits.values().toArray(new Door[0]);
        for (int i = 0; i < exits.size(); i++) {
            result[i] = doors[i].isLocked();
        }
        return result;
    }
    
    public boolean[] getHostileFlags () {
        NPC[] entities = getEntities (NPC.class);
        boolean[] isHostile = new boolean[entities.length];
        for (int i = 0; i < entities.length; i++) {
            isHostile[i] = entities[i].isHostile();
        }
        return isHostile;
    }

    public Door getExit(String direction) {
        return exits.get(direction);
    }

    public String[] getExitNames() {
        return exits.keySet().toArray(new String[0]);
    }

    //Happiness getter and setters
    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

}
