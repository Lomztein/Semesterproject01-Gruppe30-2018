package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.Room;

public abstract class NPC extends Character {

    public NPC (String name, String desc, Room startingRoom) {
        super (name, desc, startingRoom);
    }
    
    public abstract boolean isHostile ();
    
}
