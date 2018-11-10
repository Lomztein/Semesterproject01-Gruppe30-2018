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
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void useItem() {
        System.out.println("This is the main item class, and can't be used... If you see this, the developer fucked something up.");
    }

}
