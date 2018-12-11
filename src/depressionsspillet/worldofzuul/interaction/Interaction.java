/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.interaction;

import depressionsspillet.worldofzuul.characters.Player;

/**
 *
 * @author Lomztein
 */
public class Interaction {
    
    private final String name;
    private final String description;
    
    private final Action action;
    
    public Interaction (String name, String description, Action action) {
        this.name = name;
        this.description = description;
        this.action = action;
    }
    
    public String getName () {
        return this.name;
    }
    
    public String getDescription () {
        return this.description;
    }

    public String execute (Player player) {
        return action.execute (player);
    }
    
}
