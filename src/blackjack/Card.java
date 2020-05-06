package blackjack;

/**
 * This class creates a card object with a name and number
 */

public class Card {
    private int number;
    private String name;
    public Card(int number, String name){
        this.number = number;
        this.name = name;
    }

    /**
     * returns the value of the card object
     */
    public int getNumber(){
        return this.number;
    }

    /**
     * sets the value of the card object
     */
    public void setNumber(int number){
        this.number = number;
    }

    /**
     * returns the name (suit and value) of the card
     */
    public String getName(){
        return this.name;
    }


}
