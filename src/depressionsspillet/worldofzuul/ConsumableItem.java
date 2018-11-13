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
public class ConsumableItem extends Item {
    
    int healthIncrease; 
    
    public ConsumableItem (String name, String description, int rarity, int healthIncrease) {
        super(name, description, rarity);
        
        this.healthIncrease = healthIncrease;
        
    }
    
    @Override
    public void useItem(Player player) {
        //This applies whatever stats the item may have to the player.
        
        
        
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
    
    
}
