/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.DamageType;

/**
 * @author Joachim
 */
public class Player extends Character implements HasHealth, Damagable {

    private double happinesslevel = 0;
    private Damagable engagedWith;
    
    public Player(String name, String description, Room startingRoom) {
        super(name, description, startingRoom);
    }

    @Override
    public double getHealth() {
        return happinesslevel;
    }

    @Override
    public void takeDamage(Damage damage) {
        if (damage.getDamageType() == DamageType.MENTAL) {
            happinesslevel -= damage.getDamageValue();
        }
    }
}
