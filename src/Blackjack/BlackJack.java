package Blackjack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The game Blackjack
 *
 * By James, Mia, and Katelyn
 *
 */

public class BlackJack {

    private ArrayList<Card> playersCards;
    private ArrayList<Card> dealerCards;
    private Deck deck;


    /**
     * Constructor for the BlackJack class; creates the deck of cards, the list of cards that is the player's hand,
     * and the list of cards that is the dealer's hand
     */
    public BlackJack() {
        playersCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
        deck = new Deck();
    }

    /**
     * Gives the player their first two cards and the dealer their first two cards
     * plays through the player's turn and the dealer's turn
     */
    private void runGame(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Here are your first 2 cards:");

        addInitialCards(playersCards, deck);
        addInitialCards(playersCards, deck);

        addInitialCards(dealerCards, deck);
        addInitialCards(dealerCards, deck);

        initialHandMessage("Your", playersCards);

        System.out.println("Your current total is " + getTotal(playersCards) + ". Would you like to hit or stay? (1 to hit, 2 to stay)");
        playersTurn(deck, scanner.nextInt(), scanner);
        dealersTurn(deck);
    }

    /**
     * Prints the first and second card values and suits of a hand
     */
    public void initialHandMessage(String who, ArrayList<Card> list){
        System.out.println(who + " first card is the " + list.get(0).getName());
        System.out.println(who + " next card is the " + list.get(1).getName());
    }

    /**
     * Takes in user input (int response). While the response is 1, the program gives the player another card, as long as
     * the player has not busted. If the response is 2 the player's turn has ended, and the dealer takes its turn. If the
     * response is any other number, the system will prompt the player to reenter their number
     */
    public void playersTurn (Deck deck, int response, Scanner scanner){
        while(response == 1){
            hit(deck, playersCards, "Your");

            if(bust(playersCards)){
                System.out.println("Your final total is " + getTotal(playersCards) + ". You lose!");
                playAgainMessage(deck);
            }
            else {
                System.out.println("Your current total is " + getTotal(playersCards) + ". Would you like to hit or stay? (1 to hit, 2 to stay)");
                    response = scanner.nextInt();
            }
        }
        if (response==2){
            System.out.println("Your final total is " + getTotal(playersCards) + ". Now it's the dealer's turn!");
        }
        else{
            System.out.println("Not a valid response!  Would you like to hit or stay? (1 to hit, 2 to stay)");
            int altResponse = scanner.nextInt();
            playersTurn(deck, altResponse, scanner);
        }
    }

    /**
     * Prints dealer's first two cards. As long as their hand adds up to 16 or below, the dealer will hit. If the
     * dealer busts, the game will end and the player wins. If they do not bust, the dealer and player's hand will be
     * compared, and whichever is higher will win. The player will then be prompted to play again.
     */
    public void dealersTurn(Deck deck){
        initialHandMessage("Dealer's", dealerCards);
        while (getTotal(dealerCards)<17){
            hit(deck, dealerCards, "Dealer's");
            if (bust(dealerCards)){
                System.out.println("Dealer's final total is " + getTotal(dealerCards) + ". ");
                System.out.println("Dealer has busted. You win!");
            }
        }
        if (!bust(dealerCards)) {
            System.out.println("Your total is " + getTotal(playersCards) + ". The dealer's total is " + getTotal(dealerCards) + ".");
            if (getTotal(dealerCards) >= getTotal(playersCards)) {
                System.out.println("The dealer wins!");
            } else {
                System.out.println("You win!");
            }
        }
        playAgainMessage(deck);
    }

    /**
     * Prompts the user if they would like to play the game again. If the player selects to play again, rungame is called.
     * If the player wants to play again and the deck is empty, a new deck is created and then rungame is called.
     * If the player does not want to play again, the program exits.
     */
    public void playAgainMessage(Deck deck){
        playersCards.removeAll(playersCards);
        dealerCards.removeAll(dealerCards);

        System.out.println("Press 1 to play again, or any other number to exit");
        Scanner scanner = new Scanner(System.in);
        int response = scanner.nextInt();
        if(response==1){
            if (deck.isEmpty()) {
                System.out.println("Reshuffling deck so there are enough remaining cards to play");
                Deck newdeck = new Deck();
                this.deck = newdeck;
            }
            runGame();
        }
        else{
            System.exit(0);
        }
    }

    /**
     * Adds a random card from the deck to a hand (a list of cards)
     */
    public void addInitialCards(ArrayList<Card> hand, Deck deck){
        Card card = deck.drawRandomCard();
        hand.add(card);
    }

    /**
     * Draws a random card from the deck and adds it to that person's hand, printing out what that card is
     */
    private void hit(Deck deck, ArrayList<Card> hand, String who){
        Card card = deck.drawRandomCard();
        System.out.println(who + " next card is the " + card.getName());
        hand.add(card);
    }

    /**
     * Returns the sum of all the values of the cards in the given list
     * Also changes the value of the Ace card from 11 to 1 to prevent busting
     */
    public int getTotal(ArrayList<Card> list){
        int runningTotal = 0;
        for (Card card:list){
            runningTotal += card.getNumber();
        }

        if (runningTotal > 21){
            runningTotal =0;
            for (Card card: list){
                if (card.getNumber()==11){
                    card.setNumber(1);
                    System.out.println("Ace's value has changed to 1 to prevent going over 21.");
                }
                runningTotal += card.getNumber();
            }
        }
        return runningTotal;
    }

    /**
     * Returns true if the sum of all the cards in the given hand (a list of cards) is greater than 21, meaning the
     * hand's owner has busted
     * if not, returns false
     */
    private boolean bust(ArrayList<Card> list){
        if(getTotal(list) >21){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Creates the game BlackJack and calls runGame method
     */
    public static void main(String[]args) {
        BlackJack newgame = new BlackJack();
        newgame.runGame();
    }
}





