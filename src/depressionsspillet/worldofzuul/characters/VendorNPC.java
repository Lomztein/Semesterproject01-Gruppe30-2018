/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.interaction.Interaction;
import depressionsspillet.worldofzuul.*;

/**
 *
 * @author Lomztein
 */
public class VendorNPC extends NPC {

    public VendorNPC(String name, String desc, Room startingRoom, boolean hostile) {
        super(name, desc, startingRoom);
    }

    @Override
    public Interaction[] getInteractions() {
        return new Interaction [] {
            new Interaction ("annoy", "", () -> System.out.println ("You have been murdered.")),
            new Interaction ("kick", "", () -> System.out.println ("You kick " + getName () + " square in the balls, he is visibly upset."))
        };
    }
    
    
    
}