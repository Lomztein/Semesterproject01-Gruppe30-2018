/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul;

/**
 *
 * @author Ryge
 */
public abstract class Item {
    
    //Attributes
    String name;
    String description;
    
    //Rarity determines the spawnrate.
    int rarity;
    
    
    //constructor
    public Item (String name, String description, int rarity) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        
    }
    
    
    
    
}
