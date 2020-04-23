package Blackjack;

public class Card {
    private int number;
    private String name;
    public Card(int number, String name){
        this.number = number;
        this.name = name;
    }

    public int getNumber(){
        return this.number;
    }

    public void setNumber(int number){
        this.number = number;
    }
    public String getName(){
        return this.name;
    }
}
