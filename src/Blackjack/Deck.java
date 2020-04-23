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
        for (String suit:suitlist) {
            for (int z = 1; z < 14; z++) {
                Card card = new Card(z, z + suit);
                cards.add(card);
            }
        }
    }
    public List<Card> getCards(){
        return cards;
    }

    public Card drawRandomCard(){
        return cards.get(rand.nextInt(cards.size()));
    }
}

