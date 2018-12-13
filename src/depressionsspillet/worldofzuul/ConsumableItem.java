package depressionsspillet.worldofzuul;

import depressionsspillet.worldofzuul.characters.Player;

public class ConsumableItem extends Item {
    
    int healthIncrease; 
    int happinessIncrease;
    
    public ConsumableItem (String name, String description, int rarity, int healthIncrease, int happinessIncrease) {
        super(name, description, rarity);
        
        this.healthIncrease = healthIncrease;
        this.happinessIncrease = happinessIncrease;
        
    }
    
    //Useless????
    @Override
    public void useItem(Player player) {
        //This applies whatever stats the item may have to the player.
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
    
    public int getHappinessIncrease() {
        return happinessIncrease;
    }
    
}
