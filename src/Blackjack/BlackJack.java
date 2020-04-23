package Blackjack;
import comp127graphics.CanvasWindow;
import comp127graphics.Line;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    int runningtotal;
    Scanner scanner;

    ArrayList<Card> playersCards;
    ArrayList<Card> dealerCards;

    public BlackJack() {
        playersCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
        runningtotal = 0;
    }

    private void runGame(){
        Deck mydeck = new Deck();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Here are your first 2 cards:");
        Card card1 = mydeck.drawRandomCard();
        Card card2 = mydeck.drawRandomCard();
        runningtotal += card1.getNumber();
        runningtotal+=card2.getNumber();

        playersCards.add(card1);
        playersCards.add(card2);

        System.out.println("Your first card is the " + card1.getName());
        System.out.println("Your next card is the " + card2.getName());
        System.out.println("Your current total is " + runningtotal + ". Would you like to hit or stay? (1 to hit, 2 to stay)");
        int response = scanner.nextInt();

        while(response == 1){
            hit(mydeck, playersCards);
            if(bust()){
                System.exit(0);
            }
            else {
                System.out.println("Your current total is " + runningtotal + ". Would you like to hit or stay? (1 to hit, 2 to stay)");
                response = scanner.nextInt();
            }
        }
        if (response==2){
            System.out.println("Your final total is " + runningtotal + ".  Now it's the dealer's turn!");
        }
        else{
            System.out.println("Not a valid response!  Would you like to hit or stay? (1 to hit, 2 to stay)");
            scanner.nextInt();
        }
    }

    public static void main(String[]args) {
        BlackJack newgame = new BlackJack();
        newgame.runGame();
    }

    private void hit(Deck mydeck, ArrayList<Card> list){
            Card card = mydeck.drawRandomCard();
            System.out.println("Your next card is the " + card.getName());
            list.add(card);
            int n = 0;
            for(Card cardd: list){
                n += cardd.getNumber();
            }
            runningtotal+=card.getNumber();

    }

    private boolean bust(){
        if(runningtotal >21){
            System.out.println("Your final total is " + runningtotal + ". You lose :(");
            return true;
        }
        else {
            return false;
        }
    }

}






