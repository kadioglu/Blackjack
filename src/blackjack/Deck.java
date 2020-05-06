package Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class creates a deck of cards, represented by a list of card objects
 */

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private Random rand = new Random();

    /**
     * Constructor for the Deck class; assigns each card in the deck to a number/face card and a suit
     */
    public Deck() {
        List<String> suitlist = new ArrayList<>();
        suitlist.add(" of Hearts");
        suitlist.add(" of Spades");
        suitlist.add(" of Diamonds");
        suitlist.add(" of Clubs");

        List<String> facecards = new ArrayList<>();
        facecards.add("Jack");
        facecards.add("Queen");
        facecards.add("King");

        for (String suit:suitlist) {
            for (int z = 2; z < 11; z++) {
                Card card = new Card(z, z + suit);
                cards.add(card);
            }
            for (String face:facecards){
                Card card = new Card(10,face + suit);
                cards.add(card);
            }
            Card card = new Card(11, "Ace" + suit);
            cards.add(card);
        }

    }
    public List<Card> getCards(){
        return cards;
    }

    /**
     * returns a random card object from the list cards, and removes it from the list
     */
    public Card drawRandomCard(){
        Card card = cards.get(rand.nextInt(cards.size()));
        cards.remove(card);
        return card;
    }

    /**
     *  returns false if there are not enough cards remaining in the deck for both the player and the dealer to play a full hand
     */
    public boolean isEmpty(){
        if(cards.size() >= 20){
            return false;
        }
        return true;
    }
}

