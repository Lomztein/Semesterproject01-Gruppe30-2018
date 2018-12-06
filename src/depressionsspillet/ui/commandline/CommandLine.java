/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.ui.commandline;

import depressionsspillet.worldofzuul.CommandWord;
import depressionsspillet.worldofzuul.Game;
import depressionsspillet.worldofzuul.IGame;
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
        game = new Game();

        scanner = new Scanner(System.in);
        boolean wantToQuit = false;

        printWelcome();

        while (!wantToQuit) {
            String input = scanner.nextLine();
            wantToQuit = game.enterCommand(input);

            String last = game.getLastCommand();
            switch (last) {
                case "GO":
                    printRoom();
                    break;
                    
                case "HELP":
                    printHelp ();
                    break;
                    
                case "?":
                    System.out.println ("Command \"" + input + "\" not recognized. Type \"help\" for help.");
                    break;

                default:
                    System.out.println("Warning: This command hasn't been implemented properly into the CLI. Please scream at the developers.");
                    break;
            }
        }

        System.out.println("You walk away to cry in the corner. Spilmester Martin will not forget this.");
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printRoom() {
        if (game.getCommandWords()[0] == null) {
            System.out.println("Go? Go where..?");
            // If this happens, then exit out of this function using a return statement.
            return;
        } else if (game.getPlayerTriedEnteringLockedDoor()) {
            System.out.println(game.getPlayerTriedEnteringLockedDoorResponse());
        }

        System.out.println("-------------------------");
        System.out.println("You are now in " + game.getCurrentRoomLongDesc());
        //The following is printing the room's items and NPC's to tell the user what they can do.
        //Adds the rooms happiness to yours and sets the room happiness to 0.
        System.out.println("You feel your happiness rising to: " + game.getPlayerHealth());

        System.out.println("In this place, you find the following items to be of potential significance: ");
        String[] itemNames = game.getItemNames();
        String[] itemDescription = game.getItemDescriptions();

        if (itemNames.length != 0) {
            for (int i = 0; i < itemNames.length; i++) {
                System.out.println(itemNames[i] + " - " + itemDescription[i]);
            }
        } else {
            System.out.println("Nothing.");
        }

        String[] npcNames = game.getNPCNames();
        String[] npcDescriptions = game.getItemDescriptions();
        System.out.println("The following NPCs are present: ");
        if (npcNames.length != 0) {
            for (int i = 0; i < npcNames.length; i++) {
                System.out.println(npcNames[i] + " - " + npcDescriptions[i]);
            }
        } else {
            System.out.println("Nothing.");
        }

        System.out.println("Type HELP for help.");
        System.out.println(singlify(game.getCurrentExits(), ", "));
    }

    private void printWelcome() {
        // A simple, warm welcome.
        System.out.println();
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();

        // An introduction to our current room.
        System.out.println("Welcome to Depressionsspillet!");
        System.out.println("Depressionsspillet is a positive and uplifting game, designed to make the player remember the positives of a student's life!");
        System.out.println();
        // A basic guide on how to play this game.
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();

        //Trolling the player
        System.out.println("Your adventure starts near the barn of the famous Spilmester Martin.");
        System.out.println();
        System.out.println("- Greetings, youngling!");
        System.out.println("- My name is Spilmester Martin, and I am the leader of the Warriors against Erikthulu!");
        System.out.println("- Please state your desired character style!");
        System.out.println("Options include: Wizard, warrior, monk, witch hunter and berserker.");
        System.out.print(">");
        Scanner input = new Scanner(System.in);
        String someStyle = input.next();
        System.out.println("- Please state your desired name!");
        System.out.print(">");
        String someName = input.next();
        System.out.println("- Alright! You are now Janus the Magic Midget.");
        System.out.println("");
        System.out.println(game.getCurrentRoomShortDesc());
        System.out.println(singlify(game.getCurrentExits(), ", "));
    }
    
        private void printHelp() {
        // A desturbingly omnious function for printing out a short guide.
        System.out.println("You are lost. You are alone. Again... - Really? For Gods sake...");
        System.out.println("You're currently this happy: " + game.getCurrentHappiness());
        System.out.println("Right. Your options are:");
        for (String str : game.getCommandWords()) {
            System.out.println (str + ", ");
        }
    }

    private String singlify(String[] strings, String seperator) {

        String result = "";
        for (int i = 0; i < strings.length; i++) {
            result += strings[i];
            if (i != strings.length - 1) {
                result += seperator;
            }
        }

        return result;

    }
}
