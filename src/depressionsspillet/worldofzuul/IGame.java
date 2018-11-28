/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul;

import depressionsspillet.worldofzuul.characters.Player;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Lomztein
 */
public interface IGame {
    
    // COMMAND STUFFINGS
    boolean enterCommand (String input);
    
    String getLastCommand ();
    String[] getCommandWords ();
    // COMMAND STUFFINGS END
    
    // ROOM STUFFINGS
    String[] getCurrentExits ();
    boolean[] getLockedExitsFlags ();
    
    String getCurrentRoomShortDesc ();
    String getCurrentRoomLongDesc ();
    
    // TODO: See if it's possible to improve how locked rooms are handled across layers.
    boolean getPlayerTriedEnteringLockedDoor ();
    String getPlayerTriedEnteringLockedDoorResponse ();
    
    String[] getItemNames ();
    String[] getItemDescriptions ();
    
    String[] getNPCNames ();
    String[] getNPCDescriptions ();
    double[] getNPCHealth ();
    // ROOM STUFFINGS END
    
    // PLAYER STUFFINGS
    double getPlayerHappiness ();
    
    String[] getPlayerInventoryNames ();
    String[] getPlayerInventoryDescriptions ();
    // PLAYER STUFFINGS END
    
    // COMBAT STUFFINGS
    String[] getAvailableAttackNames ();
    String[] getAvailableAttackDescriptions ();
    
    double getLastAttackDamage ();
    String getLastAttackResponse ();
    
    double getLastAttackedHealth ();
    // COMBAT STUFFINGS DONE
    
    // PLAYER STUFFINGS
    double getPlayerHealth ();
    // PLAYER STUFFINGS END
    
}
