/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul;

/**
 *
 * @author Lomztein
 */
public enum DamageType {

    BLUNT("a punch"), SLASH ("a strangely shaped sword"), FIRE ("an almost-out-of-fuel lighter"), WATER ("a weak spray of water vapor"), MENTAL ("an insult"), DAB("a particularily dank dab"), SUNONASTICK ("the Sun on a Stick");

    private final String description;

    DamageType(String description) {
        this.description = description;
    }

}
