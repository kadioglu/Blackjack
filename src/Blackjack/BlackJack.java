package Blackjack;
import comp127graphics.CanvasWindow;
import comp127graphics.Line;

import java.util.Scanner;

public class BlackJack {
    int runningtotal;
    Scanner scanner;

    public static void main(String[]args){
        int runningtotal = 0;
        Deck mydeck = new Deck();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Here are your first 2 cards:");
        Card card1 = mydeck.drawRandomCard();
        Card card2 = mydeck.drawRandomCard();
        runningtotal += card1.getNumber();
        runningtotal+=card2.getNumber();


        System.out.println("Your first card is the " + card1.getName());
        System.out.println("Your next card is the " + card2.getName());
        System.out.println("Your current total is " + runningtotal + ". Would you like to hit or stay? (1 to hit, 2 to stay)");
        int response = scanner.nextInt();

        if (response==1){
           Card card3 = mydeck.drawRandomCard();
        System.out.println("Your next card is the " + card3.getName());
        System.out.println("Your current total is " + runningtotal + ". Would you like to hit or stay? (1 to hit, 2 to stay)");
    }
        else if (response==2){
        System.out.println("Your final total is " + runningtotal + ".  Now it's the dealer's turn!");
    }
        else{
        System.out.println("Not a valid response!  Would you like to hit or stay? (1 to hit, 2 to stay)");
        scanner.nextInt();
    }
}




        }




