/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.characters;

import depressionsspillet.worldofzuul.Room;
import depressionsspillet.worldofzuul.combat.Attack;
import depressionsspillet.worldofzuul.combat.Damage;
import depressionsspillet.worldofzuul.combat.Damagable;
import depressionsspillet.worldofzuul.combat.DamageType;
import java.util.ArrayList;

/**
 * @author Joachim
 */
public class Player extends Character implements HasHealth, Damagable {

    private double happinesslevel = 0;
    private Damagable engagedWith;
    private final ArrayList<Attack> availableAttacks = new ArrayList<>();
    
    public Player(String name, String description, Room startingRoom) {
        super(name, description, startingRoom);
    }

    @Override
    public double getHealth() {
        return happinesslevel;
    }
    
    public void attackEngaged (String attackName) {
        Attack attack = null;
        for (Attack att : availableAttacks) {
            if (att.getName().toLowerCase().equals(attackName.toLowerCase())) {
                attack = att;
            }
        }
        
        if (attack != null) {
            attack.doDamage(engagedWith);
        }
    }

    @Override
    public void takeDamage(Damage damage) {
        if (damage.getDamageType() == DamageType.MENTAL) {
            happinesslevel -= damage.getDamageValue();
        }
    }
}
