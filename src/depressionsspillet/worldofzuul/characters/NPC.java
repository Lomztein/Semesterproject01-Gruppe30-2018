/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.Interactable;
import depressionsspillet.worldofzuul.Room;

/**
 * @author Joachim
 */
public abstract class NPC extends Character implements Interactable {

    private boolean hostile;

    public NPC (String name, String desc, Room startingRoom, boolean hostile) {
        super (name, desc, startingRoom);
        this.hostile = hostile;
    }
    
    public boolean isHostile () {
        return hostile;
    }

}
