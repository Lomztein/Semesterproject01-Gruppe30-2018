package depressionsspillet.worldofzuul.combat;
import depressionsspillet.worldofzuul.Entity;

public interface Attacker extends Entity {
    
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
