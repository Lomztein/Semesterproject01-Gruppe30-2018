  package depressionsspillet.worldofzuul;

// An enum is a funky one. This is a speciala type of class that can be used to define an amount of constants.
// Each constant in this case, such as GO or HELP is declared using a constructor just like any other class.
// These can then be used elsewhere as constants by referencing them using CommandWord.GO for example, and their attributes and methods can then be accessed.
public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), INTERACT ("interact"), ENGAGE ("engage"), DISENGAGE ("disengage"), ATTACK ("attack"), INVENTORY("inventory"), UNKNOWN("?");
    
    private final String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    @Override
    public String toString()
    {
        return commandString;
    }
}
