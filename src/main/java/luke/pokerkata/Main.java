package luke.pokerkata;

import java.util.ArrayList;
import java.util.Scanner;

import static luke.pokerkata.HandBuilder.buildBlackHand;
import static luke.pokerkata.HandBuilder.buildWhiteHand;
import static luke.pokerkata.HandBuilder.extractCards;

/**
 * Created by Luke on 5/8/2016.
 */
public class Main {
    public static void main(String args[]){
        ArrayList<String> result;
        String winner;
        Card.Face blackHigh;
        Card.Face whiteHigh;

        System.out.println("Please input hand using format: Black: #S #S #S #S #S  White: #S #S #S #S #S");
        System.out.println("where # is the face value of the card and S is the suit code.  Suit codes are:");
        System.out.println("H for hearts, S for Spades, D for Diamonds and C for Clubs.  Use T J Q K for numbers"
        +"\nabove nine. See http://codingdojo.org/cgi-bin/index.pl?KataPokerHands for more information.\n");
        Scanner scanner = new Scanner(System.in);
        String handIn = scanner.nextLine();
        System.out.println(handIn);
        result = extractCards(handIn);
        Hand black = new Hand(buildBlackHand(result));
        Hand white = new Hand(buildWhiteHand(result));



        winner = black.compareTo(white) >=0
                ? "Black wins with "+black.getType() + " against " + white.getType()
                : "White wins with "+white.getType() + " against " + black.getType();
        System.out.println(winner);

    }
}
