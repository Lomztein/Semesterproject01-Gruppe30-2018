/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

package depressionsspillet.worldofzuul;

public class Command
{
    // Declare instance-variables / attribues for this commands CommandWord, as well as a variable for containing the "argument" for this command.
    private CommandWord commandWord;
    private String secondWord;
    
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    public String getSecondWord()
    {
        return secondWord;
    }

    // Helper function to know if the input command was invalid.
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }
    
    // Also helper function to know if the command was given an argument.
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

