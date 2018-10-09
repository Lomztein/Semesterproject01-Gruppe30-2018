package depressionsspillet.worldofzuul;

import java.util.Set;
import java.util.HashMap;


public class Room 
{
    // Declare instance-variables / attributes for the desciption of the room as well as the connections to other rooms.
    private String description;
    
    // This object is a HashMap, which functions much like an array by containing a list of elements.
    // Differently from an array however, these elements are indexed with a key instead of a number.
    // The types inside <> are known as Type arguments, and declare what types this HashMap deals with.
    // In this case, the "keys" for this hashmap is of type String, and the "values" is of type Room.
    // Types that take these "type arguments" are called generic types, meaining they can work with anything, provided there are no constraints.
    // Contraints in this sense are declarations on the generic that limit what types can be used.
    private HashMap<String, Room> exits;

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    // Wrapper function for exits.put, since exits is private.
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    // Return the description + the possible exit routes.
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        // Declare a variable for containing the combined result of the upcoming loop.
        String returnString = "Exits:";
        
        // Get every single key in the HashMap, for looping through them.
        // This generic type given a string type argument is much like an array of Strings.
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

