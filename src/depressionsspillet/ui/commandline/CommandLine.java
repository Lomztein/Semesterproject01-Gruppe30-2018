/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.ui.commandline;

import depressionsspillet.worldofzuul.Command;
import depressionsspillet.worldofzuul.CommandWord;
import depressionsspillet.worldofzuul.Game;
import depressionsspillet.worldofzuul.IGame;
import depressionsspillet.worldofzuul.combat.Damagable;
import java.util.Scanner;

/**
 *
 * @author Lomztein
 */
public class CommandLine {

    private IGame game;
    private Scanner scanner;

    public static void main(String[] args) {
        new CommandLine().init();
    }

    private void init() {
        //Creating a 'Game attribute and a scanner for inputs.
        game = new Game();
        scanner = new Scanner(System.in);
        boolean wantToQuit = false;

        //Calling the welcome-method.
        printWelcome();

        while (!wantToQuit) {
            //Print the input-arrow, and check if player wants to quit.
            System.out.print("> ");
            String input = scanner.nextLine();
            wantToQuit = game.enterCommand(input);

            //Gets the last command from Game.java.
            String last = game.getLastCommand();

            //This might seem very redundant, and it probably is, but this is neccessary to properly print all the actions of the player, as the game class must handle both the GUI and the CLI. 
            //Without this, you wouldn't know which room you were standing in and so on. - This switch-case is supposed to handle all the CLI prints, whereas the switch-case in the game-class handles all the logic within.
            switch (last) {
                case "GO":
                    printRoom();
                    break;

                case "HELP":
                    printHelp();
                    break;

                case "QUIT":
                    wantToQuit = quit();
                    break;

                case "ATTACK":
                    attack();
                    break;

                case "ENGAGE":
                    break;

                case "DISENGAGE":
                    break;
                    
                case "NO":
                    break;
                    
                case "INVENTORY":
                    inventory();
                    break;

                case "?":
                    System.out.println("Command \"" + input + "\" not recognized. Type \"help\" for help.");
                    break;

                default:
                    System.out.println("\n\u001B[33mWarning: This command hasn't been implemented properly into the CLI. Please scream at the developers for not doing their job.\u001B[0m \n");
                    break;
            }

            if (game.getCommandResponse() != null) {
                System.out.println(game.getCommandResponse());
            }
        }

        //The quitting-response.
        System.out.println("You walk away to cry in the corner. Spilmester Martin will not forget this.");
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printRoom() {
        //Checks the CommandWords array for values.
        if (game.getCommandWords()[0] == null) {
            System.out.println("Go? Go where..?");
            // If this happens, then exit out of this function using a return statement.
            return;
        } else if (game.getPlayerTriedEnteringLockedDoor()) {
            System.out.println(game.getPlayerTriedEnteringLockedDoorResponse());
        }

        System.out.println(
                "-------------------------\n"
                + game.getCurrentRoomLongDesc());

        //The following is printing the room's items and NPC's to tell the user what they can do.
        //Adds the rooms happiness to yours and sets the room happiness to 0.
        System.out.println("You feel your happiness rising to: " + game.getPlayerHealth() + "\n");

        //Printing items from array
        String[] itemNames = game.getItemNames();
        String[] itemDescription = game.getItemDescriptions();

        if (itemNames.length != 0) {
            System.out.println("In this place, you find the following items to be of potential significance: \n");
            for (int i = 0; i < itemNames.length; i++) {
                System.out.println(itemNames[i] + " - " + itemDescription[i]);
            }
        } else {
            System.out.println("There are no items in here to interact with.");
        }

        //Printing NPCs from array
        String[] npcNames = game.getNPCNames();
        String[] npcDescriptions = game.getNPCDescriptions();
        if (npcNames.length != 0) {
            System.out.println("The following NPCs are present: ");
            for (int i = 0; i < npcNames.length; i++) {
                System.out.println(npcNames[i] + " - " + npcDescriptions[i]);
            }
        } else {
            System.out.println("\nThere are no NPCs to interact with here.\n");
        }
        System.out.println("Type HELP for help.\nYou can now go the following directions: \n" + singlify(game.getCurrentExits(), ",  "));
    }

    private void printWelcome() {
        // A tiny, not-so-welcoming method.
        System.out.print(
                "Welcome to Depressionsspillet!\nDepressionsspillet is a positive and uplifting game, "
                + "designed to make the player remember the positives of a student's life!\n(Please sign the non-disclosure agreement before playing the game.)\n\n"
                + "Type '" + CommandWord.HELP + "' if you need help.\n\nYour adventure starts near the barn of the famous Spilmester Martin.\n- Greetings, youngling!\n"
                + "- My name is Spilmester Martin, and I am the leader of the Warriors against Erikthulu!\n"
                + "- Please state your desired character style\nOptions include: Wizard, warrior, monk, witch hunter and berserker.\n> ");

        //Prompts the player to enter a name and a class, wheras it's used for nothing but to troll the player.
        Scanner input = new Scanner(System.in);
        String someStyle = input.next();
        System.out.print("- Please state your desired name\n> ");
        String someName = input.next();
        System.out.println("\n\u001B[32m- Alright! You are now Janus the Magic Midget.\u001B[0m\n");
        printHelp();
    }

    private void printHelp() {
        //A very short method that prints out some help.
        System.out.println(
                "You wimper softly as your check your surroundings:\n"
                + "Your stats currently are: " + game.getCurrentHappiness() + " happiness, and " + game.getPlayerHealth() + " health.\n"
                + "Your commands are:");

        for (String str : game.getAvailableCommands()) {
            System.out.print(str + "   ");
        }
        System.out.println("");
    }

    private String singlify(String[] strings, String seperator) {
        //A method to handle arrays and print each value individually with a seperator.
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            result += strings[i];
            if (i != strings.length - 1) {
                result += seperator;
            }
        }
        return result;
    }

    private boolean quit() {
        // If the command has a second word, become confused.
        if (game.getCommandWords()[0] != null) {
            System.out.println("Quit what?");
            return false;
        } else {
            // If not, return true, which then quits the game through the previously mentioned "wantToQuit" boolean variable on line 87.
            return true;
        }
    }

    private void attack() {
        if (game.getCommandWords()[0] == null) {
            System.out.println("You have the option of the following attacks: ");
            String[] names = game.getAvailableAttackNames();
            String[] descriptions = game.getAvailableAttackDescriptions();
            for (int i = 0; i < names.length; i++) {
                System.out.println(names[i] + " - " + descriptions[i]);
            }
        }else if (game.getCommandWords()[0] != null) {
            // An attack was performed.
            if (game.getIsCurrentlyAttacking()) {
                
                double lastAttackDamage = game.getLastAttackDamage();
                String lastAttackResponse = game.getLastAttackResponse();
                System.out.println (String.format("You attack " + game.getEngagedName() + " using " + game.getCommandWords()[0] + " - " + lastAttackResponse, lastAttackDamage));
                System.out.println ("They have " + game.getLastAttackedHealth() + " health left.");
                
                double lastRetaliationDamage = game.getRetaliationAttackDamage();
                String lastRetaliationResponse = game.getRetaliationAttackResponse();
                System.out.println (String.format(game.getEngagedName() + " attacks you with " + lastRetaliationResponse, lastRetaliationDamage));
                System.out.println ("You have " + game.getPlayerHealth() + " health left.");
                
            }else {
                System.out.println ("Your attack failed, just as the world expected from you.");
            }
        }
    }

    private void inventory() {
        if (game.getCommandWords()[0] == null) {
            System.out.println("You are carrying the following: ");
            String[] names = game.getPlayerInventoryNames();
            String[] descriptions = game.getPlayerInventoryDescriptions();

            for (int i = 0; i < names.length; i++) {
                System.out.println(i + " - " + names[i] + " - " + descriptions[i]);
            }
        }
    }

}
