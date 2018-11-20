/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.combat.DamageResistance;
import depressionsspillet.worldofzuul.interaction.Interaction;
import depressionsspillet.worldofzuul.*;
import depressionsspillet.worldofzuul.combat.DamageType;

/**
 *
 * @author Lomztein
 */
public class VendorNPC extends NPC {

    public VendorNPC(String name, String desc, Room startingRoom) {
        super(name, desc, startingRoom, new DamageResistance (DamageType.ANY, "It just bounces off their fleshy exterior as they grin.", 0f));
    }

    @Override
    public Interaction[] getInteractions() {
        return new Interaction [] {
            new Interaction ("kick", "You kick " + getName () + " square in the balls, they visibly upset.", (x) -> {}),
        };
    }

    @Override
    public double getHealth() {
        return 100;
    }

    @Override
    public void setHealth(double value) { }

    @Override
    public void changeHealth(double value) { }
    
}
