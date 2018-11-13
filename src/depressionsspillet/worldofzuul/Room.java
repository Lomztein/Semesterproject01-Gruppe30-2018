package depressionsspillet.worldofzuul;

import depressionsspillet.worldofzuul.interaction.Interactable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
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

    // Declare instance-variables / attributes for the desciption of the room as well as the connections to other rooms.
    private String description;
    ArrayList<Item> itemsInRoom;

    //Constructor
    public Room(String description) {
        this.itemsInRoom = new ArrayList<>();
        this.description = description;
        exits = new HashMap<>();
        allEntities = new ArrayList<>();
    }

    public void setExit(String direction, Room neighbor) {
        setExit(direction, neighbor, false);
    }

    // Wrapper function for exits.put, since exits is private.
    public void setExit(String direction, Room neighbor, boolean locked) {
        Door door = new Door(direction, neighbor, locked);
        exits.put(direction, door);
    }

    public String getShortDescription() {
        return description;
    }

    public void addToRoom(Entity entity) {
        allEntities.add(entity);
    }

    public void leaveRoom(Entity entity) {
        allEntities.remove(entity);
    }

    public <T extends Entity> T[] getEntities(Class<T> clazz) {
        return allEntities.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList()).toArray((T[])Array.newInstance(clazz, 0));
    }
    
    public <T extends Entity> String listEntities (Class<T> clazz) {
        String list = "";
        T[] entities = getEntities (clazz);
        for (T entity : entities) {
            list += entity.getName() + "\n";
        }
        return list;
    }
    
    public <T extends Entity> T getEntity (Class<T> clazz, String name) {
        for (T entity : getEntities(clazz)) {
            if (entity.getName ().toLowerCase().equals(name.toLowerCase()))
                return entity;
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

        // Get every single key in the HashMap, for looping through them.
        // This generic type given a string type argument is much like an array of Strings.
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += "  " + exit;
        }
        return returnString;
    }

    public Door getExit(String direction) {
        return exits.get(direction);
    }

}
