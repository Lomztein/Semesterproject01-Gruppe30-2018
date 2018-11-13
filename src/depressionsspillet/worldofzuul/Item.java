/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul;

import depressionsspillet.worldofzuul.characters.Player;

/**
 *
 * @author Ryge
 */
public abstract class Item implements Entity {

    //Attributes
    private String name;
    private String description;

    //Rarity determines the spawnrate.
    private int rarity;

    //constructor
    public Item(String name, String description, int rarity) {
        this.name = name;
        this.description = description;
        
        //Rarity is currently not used, but will be used to determine spawnn rates of certain items.
        this.rarity = rarity;
    }
    
    @Override
    public String getName () {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void useItem(Player player) {
    }

}
