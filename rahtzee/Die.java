/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rahtzee;

import java.util.Random;

/**
 *
 * @author rsersh
 */
public class Die {
    
    private int faceValue;
    Random roll = new Random();
    
    public Die() {
        faceValue = 6;
    }
    
    public int rollDie() {
        do {
            faceValue = roll.nextInt(7);
        } while (faceValue < 1);
        return faceValue;        
    }
    
    public int getValue() {
        return faceValue;
    }
    
    public boolean equals(Die anotherDie) {
        if (this.getValue() == anotherDie.getValue()) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        return "" + faceValue;
    }
    
    public static void main(String args[]) {
        Die die1 = new Die();
        
        System.out.println("Die 1 has been created." 
                + " It's value is: " + die1.getValue());
        
        /*
        for(int i = 0; i < 5; i++) {
            System.out.println("Now testing the rollDie() "
                + "function.  New value on die is: " 
                + die1.rollDie());
        }
        */
        Die die2 = new Die();
        System.out.println("Die 2 has been created." 
                + " It's value is: " + die2.getValue());
        if (die1.equals(die2)) {
                System.out.println("The dice are the same. You have a pair.");
            } else {
                System.out.println("You do not have a pair.");
            }
        
        for(int i = 0; i < 15; i++) {
            die1.rollDie();
            die2.rollDie();
            if (die1.equals(die2)) {
                System.out.println("The dice are the same. You have a pair.");
            } else {
                System.out.println("You do not have a pair.");
            }
        }
    }
    
 }
