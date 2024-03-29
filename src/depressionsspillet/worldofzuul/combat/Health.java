package depressionsspillet.worldofzuul.combat;

import depressionsspillet.worldofzuul.observables.Event;
import depressionsspillet.worldofzuul.observables.Observable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Health {

    private double currentHealth;
    private double maxHealth;
    private boolean deadFlag;

    private final List<DamageResistance> resistances = new ArrayList<>();

    public final Observable<DamagedEvent> onTakeDamage = new Observable();
    public final Observable<DamagedEvent> onDeath = new Observable();
    public final Observable onHealthChanged = new Observable();

    public final List<Damage> damageTaken = new ArrayList<>();

    public Health(double health) {
        currentHealth = health;
        maxHealth = health;
    }

    // BUILDER-PATTERN-ESQUE METHODS
    public Health withMaxHealth(double value) {
        setMaxHealth(value);
        return this;
    }

    public Health withResistances(DamageResistance... newResistances) {
        for (DamageResistance resistance : newResistances) {
            resistances.add(resistance);
        }
        return this;
    }

    public Health withResistances(Collection<DamageResistance> newResistances) {
        resistances.addAll(newResistances);
        return this;
    }
    // BUILDER-PATTERN-ESQUE METHODS END

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double newHealth) {
        currentHealth = newHealth;
    }

    public void changeHealth(double value) {
        currentHealth += value;
        onHealthChanged.notifyObservables(new Event(this));
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double newHealth) {
        maxHealth = newHealth;
    }

    public DamageResistance[] getResistances() {
        return resistances.toArray(new DamageResistance[0]);
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    public DamageResistance getResistanceForType(DamageType damageType) {
        for (DamageResistance resistance : resistances) {
            if (resistance.getDamageType() == DamageType.ANY || resistance.getDamageType() == damageType) {
                return resistance;
            }
        }
        return DamageResistance.NULL_RESISTANCE;
    }

    public void takeDamage(Damage damage) {
        DamageResistance resistance = getResistanceForType(damage.getDamageType());

        double resistanceMul = resistance != null ? resistance.getMultiplier() : 1d;
        double damageValue = damage.getDamageValue() * resistanceMul;

        damageTaken.add(damage);
        changeHealth(-damageValue);
        onTakeDamage.notifyObservables(new DamagedEvent(this, damage, resistance, damageValue));

        if (!deadFlag && isDead()) {
            onDeath.notifyObservables(new DamagedEvent(this, damage, resistance, damageValue));
        }
        deadFlag = isDead();
    }

    public Damage getLastDamage() {
        if (damageTaken.size() > 0) {
            return damageTaken.get(damageTaken.size() - 1);
        } else {
            return Damage.NULL_DAMAGE;

        }
    }

    public Damage[] getDamageTaken() {
        return damageTaken.toArray(new Damage[0]);
    }

}
