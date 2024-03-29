package depressionsspillet.worldofzuul;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    // Declare instance-variables / attribute for the command words available, as well as a scanner for user input.
    private final CommandWords commands;

    public Parser() {
        commands = new CommandWords();
    }

    public Command getCommand(String input) {
        String word1 = null;
        String word2 = null;
        String word3 = null;
        String word4 = null;

        Scanner tokenizer = new Scanner(input);
        // If the scanner has been given an input, then take it and save it as word1.
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            // If there are more words, then save the second word as word2, and so-on.
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
                if (tokenizer.hasNext()) {
                    word3 = tokenizer.next();
                }
            }
        }

        // Return the result as a new command.
        return new Command(commands.getCommandWord(word1), word2, word3, word4);
    }

    public ArrayList<String> showCommands() {
        return commands.showAll();
    }
    
    public String[] getCommands () {
        return commands.getAll();
    }
}
