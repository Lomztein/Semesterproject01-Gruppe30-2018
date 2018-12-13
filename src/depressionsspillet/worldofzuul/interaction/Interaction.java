package depressionsspillet.worldofzuul.interaction;

import depressionsspillet.worldofzuul.characters.Player;

public class Interaction {
    
    private final String name;
    private final String description;
    
    private boolean interacted = false;
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
        if (interacted) {
            return "You've already done this.";
        }
        interacted = true;
        return action.execute (player);
    }
    
}
