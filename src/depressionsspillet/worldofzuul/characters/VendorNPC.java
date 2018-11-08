/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.interaction.Interaction;
import depressionsspillet.worldofzuul.*;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.DamageType;

/**
 *
 * @author Lomztein
 */
public class VendorNPC extends HostileNPC {

    public VendorNPC(String name, String desc, Room startingRoom, DamageResistance... resistances) {
        super(name, desc, startingRoom, 100, resistances, new Attack[] { new Attack (DamageType.MENTAL, 20, "Russian National Anthem", "") });
    }

    @Override
    public Interaction[] getInteractions() {
        return new Interaction [] {
            new Interaction ("annoy", "You have been murdered.", (x) -> x.setHealth(0)),
            new Interaction ("kick", "You kick " + getName () + " square in the balls, he is visibly upset.", (x) -> this.changeHealth(-10)),
            super.getInteractions()[0]
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
