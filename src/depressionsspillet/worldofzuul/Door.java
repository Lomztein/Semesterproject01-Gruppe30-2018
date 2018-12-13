package depressionsspillet.worldofzuul;

/**
 *
 * @author emiliekarlsson
 */
public class Door {
    
    private boolean locked;
    private String lockedReason;
    
    private Room room;
    private String direction;
    
    public Door(String direction, Room room){
        this.direction = direction;
        this.room = room;
    }
    
    public Door (String direction, Room room, boolean locked, String lockedReason) {
        this (direction, room);
        this.locked = locked;
        this.lockedReason = lockedReason;
    }

    Door (Room neighbor, boolean locked) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setLocked (boolean value) {
        locked = value;
    }
    
    public boolean isLocked() {
        return locked;
    }
    
    public String getLockedReason () {
        return lockedReason;
    }
    
    public Room getRoom ()  {
        return room;
    }
}
