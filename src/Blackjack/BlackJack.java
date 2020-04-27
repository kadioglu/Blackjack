package Blackjack;
import comp127graphics.CanvasWindow;
import comp127graphics.Line;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    Scanner scanner;

    ArrayList<Card> playersCards;
    ArrayList<Card> dealerCards;

    public BlackJack() {
        playersCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
    }

    private void runGame(){
        Deck mydeck = new Deck();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Here are your first 2 cards:");

        addToHand(playersCards, mydeck);
        addToHand(playersCards, mydeck);

        addToHand(dealerCards, mydeck);
        addToHand(dealerCards, mydeck);

//        Card card1 = mydeck.drawRandomCard();
//        Card card2 = mydeck.drawRandomCard();
//        runningtotal += card1.getNumber();
//        runningtotal+=card2.getNumber();

//        playersCards.add(card1);
//        playersCards.add(card2);

//        dealerCards.add

        System.out.println("Your first card is the " + playersCards.get(0).getName());
        System.out.println("Your next card is the " + playersCards.get(1).getName());
        System.out.println("Your current total is " + getTotal(playersCards) + ". Would you like to hit or stay? (1 to hit, 2 to stay)");
        int response = scanner.nextInt();

        while(response == 1){
            hit(mydeck, playersCards);
            if(bust(playersCards)){
                System.exit(0);
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

    public void addToHand(ArrayList<Card> hand, Deck deck){
        Card card = deck.drawRandomCard();
//        runningTotal += card.getNumber();
        hand.add(card);
//        return runningTotal;
    }

    public int getTotal(ArrayList<Card> list){
        int runningTotal = 0;
        for (Card card:list){
            runningTotal += card.getNumber();
        }
        return runningTotal;
    }

    public static void main(String[]args) {
        BlackJack newgame = new BlackJack();
        newgame.runGame();
    }

    private void hit(Deck mydeck, ArrayList<Card> list){
            Card card = mydeck.drawRandomCard();
            System.out.println("Your next card is the " + card.getName());
            list.add(card);
//            getTotal(list)+=card.getNumber();

    }

    private boolean bust(ArrayList<Card> list){
        if(getTotal(list) >21){
            System.out.println("Your final total is " + getTotal(list) + ". You lose :(");
            return true;
        }
        else {
            return false;
        }
    }

}






