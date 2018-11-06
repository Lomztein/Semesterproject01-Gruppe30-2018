/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul;

/**
 * @author Joachim
 */
public abstract class Character {

    private String name;
    private String description;
    //private Inventory inventory;
    private Room currentRoom;
    
    
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
