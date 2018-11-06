/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package depressionsspillet.worldofzuul;

/**
 * @author Joachim
 */
public class Player extends Character implements HasHealth {

    private double happinesslevel = 0;
    
    
    public Player(String name, String description, Room startingRoom) {
        super(name, description, startingRoom);
    }

    @Override
    public double getHealth() {
        return happinesslevel;
    }
    
}
