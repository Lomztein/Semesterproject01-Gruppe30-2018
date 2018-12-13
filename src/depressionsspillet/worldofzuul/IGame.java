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
    String[] getAvailableCommands ();
    
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
    String getPlayerName ();
    String getPlayerDescription ();
    
    double getPlayerHealth();
    boolean isPlayerDead ();
    int getCurrentHappiness();
    
    String[] getPlayerInventoryNames ();
    String[] getPlayerInventoryDescriptions ();
    
    // TODO: See if it's possible to improve how locked rooms are handled across layers.
    boolean getPlayerTriedEnteringLockedDoor ();
    String getPlayerTriedEnteringLockedDoorResponse ();
    // PLAYER STUFFINGS END
    
    // INTERACTION STUFFINGS
    String[] getInteractableNames ();
    String[] getInteractableDescriptions ();
    
    String [][] getInteractionNames ();
    String [][] getInteractionDescriptions ();
    // INTERACTION STUFFINGS END
    
    // COMBAT STUFFINGS
    String[] getAvailableAttackNames ();
    String[] getAvailableAttackDescriptions ();
    
    String getEngagedName ();
    
    boolean getLastAttackDidHit ();
    double getLastAttackDamage ();
    String getLastAttackName ();
    String getLastAttackDescription ();
    String getLastAttackType ();
    String getLastAttackResponse ();
    
    boolean getLastAttackHadRetaliation ();
    double getRetaliationAttackDamage ();
    String getRetaliationAttackName ();
    String getRetaliationAttackDescription ();
    String getRetaliationAttackType ();
    String getRetaliationAttackResponse ();
    
    double getLastAttackedHealth ();
    // COMBAT STUFFINGS DONE
  
}
