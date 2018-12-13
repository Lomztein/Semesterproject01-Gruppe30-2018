package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.Entity;
import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.observables.Event;
import depressionsspillet.worldofzuul.observables.Observable;

public abstract class Character implements Entity {

    private final String name;
    private final String description;

    public Observable onRoomChanged = new Observable();

    //private Inventory inventory;
    private Room currentRoom;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        onRoomChanged.notifyObservables(new Event (this));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public Character(String name, String description, Room currentRoom) {
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;
    }
    
    //GGWP
}
