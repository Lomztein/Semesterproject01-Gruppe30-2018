/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul.combat;
import depressionsspillet.worldofzuul.Named;

/**
 *
 * @author Lomztein
 */
public interface Attacker extends Named {
    
    Attack[] getAttacks ();
    
    public default String getAttackList () {
        Attack[] attacks = getAttacks ();
        String list = "";
        for (Attack att : attacks) {
            list += att.getName() + " - " + att.getDescription() + "\n";
        }
        return list;
    }
    
}
