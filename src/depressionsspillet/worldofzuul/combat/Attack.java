package depressionsspillet.worldofzuul.combat;

import depressionsspillet.worldofzuul.Named;

public class Attack {
    
    public static Attack NULL_ATTACK = new Attack (DamageType.ANY, 0, "null", "null");
    
    private final DamageType damageType;
    private final double damageValue;
    private final String name;
    private final String description;
    
    public Damage attack (Attacker attacker, Damagable damagable) {
        Damage damage = new Damage (attacker, damagable, this);
        damage.doDamage();
        return damage;
    }
    
    public Attack (DamageType damageType, double damageValue, String name, String description) {
        this.damageType = damageType;
        this.damageValue = damageValue;
        this.name = name;
        this.description = description;
    }
    
    public String getName () {
        return name;
    }
    
    public String getDescription () {
        return description;
    }
    
    public DamageType getDamageType () {
        return damageType;
    }
    
    public double getDamageValue () {
        return damageValue;
    }
    
}
