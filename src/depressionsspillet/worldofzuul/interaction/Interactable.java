package depressionsspillet.worldofzuul.interaction;

import depressionsspillet.worldofzuul.Entity;

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
