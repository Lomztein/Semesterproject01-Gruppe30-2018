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
    
    String getCurrentRoomName ();
    String getCurrentRoomLongDesc ();
    
    boolean triedEnteringLockedRooom ();
    
    String[] getItemNames ();
    String[] getItemDescriptions ();
    
    String[] getNPCNames ();
    String[] getNPCDescriptions ();
    // ROOM STUFFINGS END
    
    // PLAYER STUFFINGS
    double getPlayerHealth ();
    // PLAYER STUFFINGS END
    
}
