/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul;

/**
 *
 * @author Lomztein
 */
public interface Interactable {
    
    String getName ();
    
    Interaction[] getInteractions ();
    
    public default Interaction findInteraction (String interactionName) {
        
        Interaction[] interactions = this.getInteractions();
        Interaction correctOne = null;
        for (Interaction interaction : interactions) {
            if (interaction.getName().equals(interactionName)) {
                correctOne = interaction;
            }
        }
        
        return correctOne;
        
    }
    
}
