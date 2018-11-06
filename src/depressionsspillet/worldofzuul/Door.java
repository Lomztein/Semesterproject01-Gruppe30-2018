package depressionsspillet.worldofzuul;

/**
 *
 * @author emiliekarlsson
 */
public class Door {
    
    boolean locked;
    private Room room;
    private String direction;
    
    public Door(String direction, Room room, boolean locked){
        this.direction = direction;
        this.locked = locked;
        this.room = room;
    }

    Door (Room neighbor, boolean locked) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean isLocked() {
        return locked;
    }
    
    public Room getRoom ()  {
        return room;
    }
}
