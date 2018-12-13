/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.interaction.Interactable;
import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.interaction.Interaction;

/**
 * @author Joachim
 */
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
