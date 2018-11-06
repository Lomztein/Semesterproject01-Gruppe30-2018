/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.combat;

/**
 *
 * @author Lomztein
 */
public enum DamageType {

    BLUNT("blunt force"), SLASH ("sharp slash"), FIRE ("burning fire"), WATER ("moist water"), MENTAL ("hurtful insult"), DAB("dab"), SUNONASTICK ("Sun on a Stick");

    private final String description;

    DamageType(String description) {
        this.description = description;
    }

}
