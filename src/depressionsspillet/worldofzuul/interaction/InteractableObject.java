/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.interaction;

/**
 *
 * @author Lomztein
 */
public class InteractableObject implements Interactable {
    
    private final String name;
    private final String description;
    private final Interaction[] interactions;
    
    public InteractableObject (String name, String description, Interaction... interactions) {
        this.name = name;
        this.description = description;
        this.interactions = interactions;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public Interaction[] getInteractions() {
        return interactions;
    }
    
}
