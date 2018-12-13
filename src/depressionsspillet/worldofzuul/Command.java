package depressionsspillet.worldofzuul;

public class Command
{
    // Declare instance-variables / attribues for this commands CommandWord, as well as a variable for containing the "argument" for this command.
    private final CommandWord commandWord;
    
    private final String secondWord;
    private final String thirdWord; 
    private final String fourthWord;
    
    public Command(CommandWord commandWord, String secondWord, String thirdWord, String fourthWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
        this.fourthWord = fourthWord;
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
   
    public boolean hasThirdWord () {
        return (thirdWord != null);
    }
    
    public String getThirdWord () {
        return thirdWord;
    }
    
     public boolean hasFourthWord () {
        return (fourthWord != null);
    }
    
    public String getFourthWord () {
        return fourthWord;
    }
    
}

