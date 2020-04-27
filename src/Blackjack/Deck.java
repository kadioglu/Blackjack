package Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private Random rand = new Random();

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

    public Card drawRandomCard(){
        Card card = cards.get(rand.nextInt(cards.size()));
        cards.remove(card);
        return card;
    }
}

