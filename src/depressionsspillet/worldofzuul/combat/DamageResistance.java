package depressionsspillet.worldofzuul.combat;

public class DamageResistance {
    
    public static DamageResistance NULL_RESISTANCE = new DamageResistance (DamageType.ANY, "takes a full %.2f damage.", 1);
    
    private final DamageType type;
    private final String response;
    private final double multiplier;
    
    public DamageResistance (DamageType type, String response, double multiplier) {
        this.type = type;
        this.response = response; 
        this.multiplier = multiplier;
    }
    
    public DamageType getDamageType () {
        return type;
    }
    
    public String getResponse () {
        return response;
    }
    
    public double getMultiplier () {
        return multiplier;
    }
    
}
