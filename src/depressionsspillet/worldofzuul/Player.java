/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul;

import java.util.Scanner;

/**
 * @author Joachim
 */
public class Player extends Character {

    //Testing attribute
    Scanner testInput = new Scanner(System.in);

    //Attributes
    Item[] inventory = new Item[4];
    private int happinesslevel = 0;

    public Player(String name, String description, Room startingRoom) {
        super(name, description, startingRoom);

    }

    //Methods
    public void printInventoryList() {
        int i = 1;
        for (Item item : inventory) {

            if (item != null) {
                System.out.println(i + ",  " + item.name);
            } else {
                System.out.println(i + ",  Empty");
            }
        }
    }

    public void addToInventory(Item item) {
        printInventoryList();
        
        //For testing purposes, should run through parser in end-version
        System.out.println("\n\nSelect a slot to insert " + item.name + " into: ");

        int i = testInput.nextInt();

        while (i > 4 || i < 1) {
            
            System.out.print("Wrong input, try again: \n> ");
            i = testInput.nextInt();
        }
        
        inventory[i] = item;
        
    }

}
