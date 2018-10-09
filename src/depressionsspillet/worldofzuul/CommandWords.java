package depressionsspillet.worldofzuul;
import java.util.HashMap;


public class CommandWords
{
    // Declare a HashMap that contains the valid commands. Go to Room.java for an explananation of HashMaps.
    private HashMap<String, CommandWord> validCommands;

    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        // For each in the CommandWord enumerable, add it to the validCommands hashmap for easy indexing using the command identifier.
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    public CommandWord getCommandWord(String commandWord)
    {
        // Get the correct CommandWord by indexing the validCommands HashMap using the input.
        CommandWord command = validCommands.get(commandWord);
        
        // If it exists, return it.
        if(command != null) {
            return command;
        }
        else {
            // Otherwise, return UNKNOWN.
            return CommandWord.UNKNOWN;
        }
    }
    
    // Helper function for checking if a command is valid in the first place.
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    public void showAll() 
    {
        // Loop through and print each key in the validCommand HashMap.
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
