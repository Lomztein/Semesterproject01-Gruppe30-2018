package depressionsspillet.worldofzuul.combat;

public interface HasHealth extends Damagable {
    
    public Health getHealth ();
    
    @Override
    public default void takeDamage (Damage damage) {
        getHealth ().takeDamage(damage);
    }
    
}
