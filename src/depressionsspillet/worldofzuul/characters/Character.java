/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.Room;

/**
 * @author Joachim
 */
public abstract class Character {

    private final String name;
    private final String description;
    
    //private Inventory inventory;
    private Room currentRoom;

    public Room getCurrentRoom () {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public Character (String name, String description, Room currentRoom){
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;
    }
   
    //GGWP
    
    
   
}
