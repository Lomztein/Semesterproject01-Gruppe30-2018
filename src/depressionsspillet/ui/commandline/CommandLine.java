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
        new CommandLine ().init();
    }

    private void init() {
        game = new Game();

        scanner = new Scanner(System.in);
        boolean wantToQuit = true;

        printWelcome();

        while (!wantToQuit) {
            String input = scanner.nextLine();
            wantToQuit = game.enterCommand(input);
        }

        System.out.println("You walk away to cry in the corner. Spilmester Martin will not forget this.");
        System.out.println("Thank you for playing.  Good bye.");
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
        System.out.println(game.getCurrentRoomName());
        System.out.println(singlify(game.getCurrentExits(), ", "));
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
