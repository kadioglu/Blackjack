package Blackjack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The game Blackjack
 *
 * By James, Mia, and Katelyn
 *
 * test
 */

public class BlackJack {
    private Scanner scanner;

    ArrayList<Card> playersCards;
    ArrayList<Card> dealerCards;
    Deck deck;
    int response;

    /**
     * creates the list of cards that is the player's hand, and the list of cards that is the dealer's hand
     */
    public BlackJack() {
        playersCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
        deck = new Deck();
        response = 1;
    }

    /**
     * sets up the game, plays through the player's turn and the dealer's turn
     *
     *
     */
    private void runGame(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Here are your first 2 cards:");

        addToHand(playersCards, deck);
        addToHand(playersCards, deck);

        addToHand(dealerCards, deck);
        addToHand(dealerCards, deck);

        initialHandMessage("Your", playersCards);

        System.out.println("Your current total is " + getTotal(playersCards) + ". Would you like to hit or stay? (1 to hit, 2 to stay)");
        playersTurn(deck,scanner.nextInt(), scanner);
        dealersTurn(deck);
    }

    public void playersTurn (Deck deck, int response, Scanner scanner){
        while(response == 1){
            hit(deck, playersCards, "Player");

            if(bust(playersCards, "Player")){
               playAgainMessage(deck);
            }
            else {
                System.out.println("Your current total is " + getTotal(playersCards) + ". Would you like to hit or stay? (1 to hit, 2 to stay)");
                    response = scanner.nextInt();
                }
            }

        if (response==2){
            System.out.println("Your final total is " + getTotal(playersCards) + ".  Now it's the dealer's turn!");
        }
        else{
            System.out.println("Not a valid response!  Would you like to hit or stay? (1 to hit, 2 to stay)");
            scanner.nextInt();
        }

    }
    public void dealersTurn(Deck deck){
        initialHandMessage("Dealer's", dealerCards);
        while (getTotal(dealerCards)<16){
            hit(deck, dealerCards, "Dealer's");
            if (bust(dealerCards, "Dealer")){
                System.out.println("Dealer has busted. You win!");

            }
        }
        System.out.println("Your total is " + getTotal(playersCards) + ". The dealer's total is " + getTotal(dealerCards) + ".");
        if (getTotal(dealerCards)>= getTotal(playersCards)){
            System.out.println("The dealer wins!");
        }
        else{
            System.out.println("You win!");
        }
        playAgainMessage(deck);
    }

    /** Prompts the user if they would like to play the game again, and responds accordingly - exits the program if needed,
     * deals another hand with the same deck if possible, and reshuffles the deck if it is approaching empty
     *
     */
    public void playAgainMessage(Deck deck){
        playersCards.removeAll(playersCards);
        dealerCards.removeAll(dealerCards);
        System.out.println("Press 1 to play again, or any other key to exit");
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
     * prints the first and second card values and suits of a hand
     */
    public void initialHandMessage(String who, ArrayList<Card> list){
        System.out.println(who + " first card is the " + list.get(0).getName());
        System.out.println(who + " next card is the " + list.get(1).getName());
    }

    /**
     * adds a random card from the deck to a hand (a list of cards)
     */
    public void addToHand(ArrayList<Card> hand, Deck deck){
        Card card = deck.drawRandomCard();
        hand.add(card);
    }

    /**
     * returns the sum of all the values of the cards in the given list
     * also changes the value of the Ace card from 11 to one to prevent busting
     *
     * TODO fix bug with 2 aces both being turned to 1 when 1 should be 11 and the other 1
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
//            if (list.contains(card.))
        }
        return runningTotal;
    }

    /**
     * draws a random card from the deck and adds it
     */
    private void hit(Deck mydeck, ArrayList<Card> list, String who){
            Card card = mydeck.drawRandomCard();
            System.out.println(who + " next card is the " + card.getName());
            list.add(card);
    }

    /**
     * returns true if the sum of all the cards in the given hand (a list of cards) is greater than 21, meaning the
     * hand's owner has busted
     * if not, returns false
     */
    private boolean bust(ArrayList<Card> list, String who){
        if(getTotal(list) >21){
            System.out.println(who + "'s " + "final total is " + getTotal(list) + ". " + who + " loses!");
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





