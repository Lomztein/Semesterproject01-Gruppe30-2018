/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.interaction;

import depressionsspillet.worldofzuul.Entity;

/**
 *
 * @author Lomztein
 */
public interface Interactable extends Entity {
    
    String getName ();
    
    Interaction[] getInteractions ();
    
    public default Interaction findInteraction (String interactionName) {
        
        Interaction[] interactions = this.getInteractions();
        Interaction correctOne = null;
        for (Interaction interaction : interactions) {
            if (interaction.getName().toLowerCase().equals(interactionName.toLowerCase())) {
                correctOne = interaction;
            }
        }
        
        return correctOne;
        
    }
    
    public default String listInteractions () {
        String list = "";
        for (Interaction interaction : getInteractions()) {
            list += interaction.getName () + " - " + interaction.getDescription() + "\n";
        }
        return list;
    }
    
}
