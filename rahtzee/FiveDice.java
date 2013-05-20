/*
 * To roll 5 dice at once.
 */
package rahtzee;

import rahtzee.Die;

/**
 *
 * @author Rachel
 */
public class FiveDice {
    
    private Die die1, die2, die3, die4, die5;   
    private static boolean foundTriple = false;
        
    public FiveDice() {
        die1 = new Die();
        die2 = new Die();
        die3 = new Die();
        die4 = new Die();
        die5 = new Die();
    }
    
    /**
     * @return the total of the five dice
     */
    public int sum() {
        return die1.getValue() + die2.getValue() + die3.getValue()
                + die4.getValue() + die5.getValue();
    }
    
    @Override
    public String toString() {
        return "" + die1.toString() + "," + die2.toString() + "," + die3.toString()
                + "," + die4.toString() + "," + die5.toString();
    }
    
    public int getHand() {
        //can pass all dice as an int and then pick off the digits 
        //by dividing by factors of 10. smaller than arrays... 
        return 0;
    }
    
    
    /**
     * Rolls all five dice and prints the results, with the sum.
     */
    public void roll() {
        die1.rollDie();
        die2.rollDie();
        die3.rollDie();
        die4.rollDie();
        die5.rollDie();
        
        System.out.println("Your roll: " + this.toString());// + "   = " + sum());
    }
    
    /**
     * The following methods are for player decisions as to which dice to roll.
     */
    public void selectRoll(int one, int two, int three, int four, int five) {
        roll();
    }
    
    public void selectRoll(int one, int two, int three, int four) {
        die1.rollDie();
        die2.rollDie();
        die3.rollDie();
        die4.rollDie();
    }
    
    public void selectRoll(int one, int two, int three) {
        die1.rollDie();
        die2.rollDie();
        die3.rollDie();
    }
    
    public void selectRoll(int one, int two) {
        die1.rollDie();
        die2.rollDie();
    }
    
    public void selectRoll(int one) {
        die1.rollDie();
    } 
    
    public boolean checkFiveOfKind() {
        if ( die1.equals(die2) && die1.equals(die3) && die1.equals(die4)
                && die1.equals(die5)) {
            return true;
        }
        return false;
    }
    
    /**
     * @return true if 4 out of 5 dice equal each other in faceValue
     */
    public boolean checkFourOfKind() {
        //(1,2,3,4) (1,2,3,5) (1,2,4,5) (1,3,4,5) (2,3,4,5)
        if ( (die1.equals(die2) && die1.equals(die3) && die1.equals(die4))
          || (die1.equals(die2) && die1.equals(die3) && die1.equals(die5))
          || (die1.equals(die2) && die1.equals(die4) && die1.equals(die5)) 
          || (die2.equals(die3) && die2.equals(die4) && die2.equals(die5)) ){
            return true;
        }
        return false;
    }
    
    /**
     * @return true if 3 out of 5 dice are equal in faceValue
     */
    public boolean checkThreeOfKind() {
        //(1,2,3) (1,2,4) (1,2,5) (1,3,4) (1,3,5) (1,4,5)
        //(2,3,4) (2,3,5) (2,4,5)
        //(3,4,5)
        if ( (die1.equals(die2) && die1.equals(die3)) 
          || (die1.equals(die2) && die1.equals(die4))
          || (die1.equals(die2) && die1.equals(die5)) 
          || (die1.equals(die3) && die1.equals(die4))
          || (die1.equals(die3) && die1.equals(die5))
          || (die1.equals(die4) && die1.equals(die5))
          || (die2.equals(die3) && die2.equals(die4))
          || (die2.equals(die3) && die2.equals(die5))
          || (die2.equals(die4) && die2.equals(die5))
          || (die3.equals(die4) && die3.equals(die5)) ) {
            foundTriple = true;  //for checkFullHouse() later
            return true;
        }
        return false;
    }
    
       /**
     * @return true, if there is a pair
     */
    public boolean checkPair() {
        int pairs = 0;
        int match = 0, match1 = 0, match2 = 0;
        
        if ( !checkFiveOfKind() && !checkFourOfKind() && !checkThreeOfKind() ) {
        
           if ( die1.equals(die2) || die1.equals(die3) || die1.equals(die4)
                || die1.equals(die5)) {  //die1 matched one die
             match1 = die1.getValue();
             pairs++;
           }
                
           if ( die2.equals(die3) || die2.equals(die4) || die2.equals(die5) ) {
             if (match1 == 0) {// die2.getValue()) { //found a second pair
                match1 = die2.getValue();
                pairs++;
                } else {  //didn't find a pair with first die but did with 2nd
                 match2 = die2.getValue();
                 pairs++;
             }
           }
            if ( die3.equals(die4) || die3.equals(die5)) {
                if (match1 == 0 && match2 == 0) {  //found a pair
                    match1 = die3.getValue();
                    pairs++;
                } else if (match1 != 0) {  //found first pair
                    match2 = die3.getValue();
                    pairs++;
                }
            }
            if ( die4.equals(die5)) {  
                if (match1 == 0 && match2 == 0) { //die4.getValue() && match2 != die4.getValue()) { //found second pair
                    match1 = die4.getValue();
                    pairs++;
                } else if (match1 != 0) {
                    match2 = die4.getValue();
                    pairs++;
                }
            }
        
            if (pairs == 1) {
                int score = (match1*2) + (match2*2);
                System.out.println("You have a pair! Your score is " + score);
                return true;
            } else if (pairs == 2){
               int score = (match1*2) + (match2*2);
               System.out.println("You have 2 pair! Your score is " + score);
               return true;
            }
        }
        return false;            
    }
    
    //add to a ScoreCard class later
    public void scoreIt() {
        if (checkFiveOfKind()) {
            System.out.println("Yahtzee!! 50 points!");
        } else if (checkFourOfKind()) {
            System.out.println("4 of a Kind for " + sum() + "!");
        } else if (checkThreeOfKind()) {
            System.out.println("3 of a Kind for " + sum() + "!");
        } else if (checkPair()) {
            
        } else {
            System.out.println("Your chance is worth: " + sum());
        }
    }
    
     public static void main(String args[]) {
        
        FiveDice myDice = new FiveDice();
        System.out.println("Your dice: " + myDice.toString());
        for (int i=0; i<20; i++) {
            System.out.println("Rolling dice...");
            myDice.roll();
            myDice.scoreIt();
           
        }
     }
}
