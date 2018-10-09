package depressionsspillet.worldofzuul;

import java.util.Scanner;

public class Parser 
{
    // Declare instance-variables / attribute for the command words available, as well as a scanner for user input.
    private CommandWords commands;
    private Scanner reader;

    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() 
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> "); 

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        // If the scanner has been given an input, then take it and save it as word1.
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            // If there are more words, then save the second word as word2
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next(); 
            }
        }

        // Return the result as a new command.
        return new Command(commands.getCommandWord(word1), word2);
    }

    public void showCommands()
    {
        commands.showAll();
    }
}
