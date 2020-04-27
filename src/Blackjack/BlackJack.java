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

        initialHandMessage("Your", playersCards);
        System.out.println("Your current total is " + getTotal(playersCards) + ". Would you like to hit or stay? (1 to hit, 2 to stay)");
        int response = scanner.nextInt();

        while(response == 1){
            hit(mydeck, playersCards, "Player's");
//            System.out.println("Your next card is the " + card.getName());
            if(bust(playersCards, "Player")){
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

//        Dealer's turn
        initialHandMessage("Dealer's", dealerCards);
        while (getTotal(dealerCards)<16){
            hit(mydeck, dealerCards, "Dealer's");
            if (bust(dealerCards, "Dealer")){
                System.out.println("Dealer has busted. You win!");
                System.exit(0);
            }

        }

        System.out.println("Your total is " + getTotal(playersCards) + ". The dealer's total is " + getTotal(dealerCards) + ".");
        if (getTotal(dealerCards)>= getTotal(playersCards)){
             System.out.println("The dealer wins!");
        }
        else{
            System.out.println("You win!");
        }
        System.exit(0);
    }

    public void initialHandMessage(String who, ArrayList<Card> list){
        System.out.println(who + " first card is the " + list.get(0).getName());
        System.out.println(who + " next card is the " + list.get(1).getName());
    }

    public void addToHand(ArrayList<Card> hand, Deck deck){
        Card card = deck.drawRandomCard();
        hand.add(card);
    }

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

    public static void main(String[]args) {
        BlackJack newgame = new BlackJack();
        newgame.runGame();
    }

    private void hit(Deck mydeck, ArrayList<Card> list, String who){
            Card card = mydeck.drawRandomCard();
            System.out.println(who + " next card is the " + card.getName());
            list.add(card);
    }

    private boolean bust(ArrayList<Card> list, String who){
        if(getTotal(list) >21){
            System.out.println(who + "'s " + "final total is " + getTotal(list) + ". " + who + " loses!");
            return true;
        }
        else {
            return false;
        }
    }

}






