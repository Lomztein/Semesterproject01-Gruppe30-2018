package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.interaction.Interactable;
import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.interaction.Interaction;

public class InteractableNPC extends NPC implements Interactable {
    
    private Interaction[] interactions;

    public InteractableNPC (String name, String desc, Room startingRoom, Interaction... interactions) { // new NPC ("John Hitler", "Hvad hed Hitler til fornavn", startingRoom, new Interaction (), new Interaction (), new Interaction ())
        super (name, desc, startingRoom);
        this.interactions = interactions;
    }

    @Override
    public Interaction[] getInteractions() {
        return interactions;
    }
    
    @Override
    public String toString () {
        return this.getName ();
    }

    @Override
    public boolean isHostile() {
        return false;
    }

}
