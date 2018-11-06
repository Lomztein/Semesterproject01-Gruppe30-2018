/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.interaction.Interactable;
import depressionsspillet.worldofzuul.Room;

/**
 * @author Joachim
 */
public abstract class NPC extends Character implements Interactable {

    public NPC (String name, String desc, Room startingRoom) {
        super (name, desc, startingRoom);
    }

}
