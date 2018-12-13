package depressionsspillet.worldofzuul.combat;

public enum DamageType {

    ANY ("any"), BLUNT("blunt force"), SLASH ("sharp slash"), FIRE ("burning fire"), WATER ("moist water"), MENTAL ("hurtful insult"), DAB("dab"), SUNONASTICK ("Sun on a Stick");

    private final String description;

    DamageType(String description) {
        this.description = description;
    }

}
