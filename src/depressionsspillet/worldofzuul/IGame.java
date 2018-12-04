/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul;

/**
 *
 * @author Lomztein
 */
public interface IGame {
    //To start the game we need the rooms created
    void playGame();
    
    // COMMAND STUFFINGS
    boolean enterCommand(String input);
    String getLastCommand();
    String[] getCommandWords();
    String getCommandResponse ();
    // COMMAND STUFFINGS END

    // ROOM STUFFINGS
    String[] getCurrentExits ();
    boolean[] getLockedExitsFlags ();

    String getCurrentRoomName();
    String getCurrentRoomShortDesc ();
    String getCurrentRoomLongDesc();

    String[] getItemNames();
    String[] getItemDescriptions();
    String[] getNPCNames();
    String[] getNPCDescriptions();

    int getRoomHappiness();
    // ROOM STUFFINGS END

    // PLAYER STUFFINGS
    double getPlayerHealth();
    int getCurrentHappiness();
    
    String[] getPlayerInventoryNames ();
    String[] getPlayerInventoryDescriptions ();
    
    // TODO: See if it's possible to improve how locked rooms are handled across layers.
    boolean getPlayerTriedEnteringLockedDoor ();
    String getPlayerTriedEnteringLockedDoorResponse ();
    // PLAYER STUFFINGS END
    
    // COMBAT STUFFINGS
    String[] getAvailableAttackNames ();
    String[] getAvailableAttackDescriptions ();
    
    double getLastAttackDamage ();
    String getLastAttackResponse ();
    
    double getRetaliationAttackDamage ();
    String getRetaliationAttackResponse ();
    
    double getLastAttackedHealth ();
    // COMBAT STUFFINGS DONE
  
}
