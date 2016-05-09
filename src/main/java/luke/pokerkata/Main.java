package luke.pokerkata;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Scanner;

import static luke.pokerkata.HandBuilder.buildBlackHand;
import static luke.pokerkata.HandBuilder.buildWhiteHand;
import static luke.pokerkata.HandBuilder.extractCards;

/**
 * Created by Luke on 5/8/2016.
 * A simple program to demo my card parser and hand evaluator.
 */
public class Main {
    public static void main(String args[]) {
        ArrayList<String> result;
        String winner;
        Hand black = null;
        Hand white = null;

        System.out.println("Please input hand using format: Black: #S #S #S #S #S  White: #S #S #S #S #S");
        System.out.println("where # is the face value of the card and S is the suit code.  Suit codes are:");
        System.out.println("H for hearts, S for Spades, D for Diamonds and C for Clubs.  Use T J Q K for numbers"
                + "\nabove nine. \n\nSee http://codingdojo.org/cgi-bin/index.pl?KataPokerHands for more information.\n"
                + "Say exit to quit.");
        Scanner scanner = new Scanner(System.in);
        String handIn = scanner.nextLine();
        System.out.println(handIn);
        if (handIn.contains("exit")) {
            System.exit(0);
        }

        try {
            result = extractCards(handIn);
            black = new Hand(buildBlackHand(result));
            white = new Hand(buildWhiteHand(result));
        } catch (IllegalArgumentException e) {
            System.out.println("Please use the stated formatting.\n");
            main(args);
        }


        int val = black.compareTo(white);
        if (val > 0) {
            if (black.getType() == HandType.HIGH_CARD) {
                winner = "Black wins with " + black.getType() + ":" + black.getHighestCard() +
                        " against " + white.getType() + ":" + white.getHighestCard();
            } else {
                winner = "Black wins with " + black.getType() + " against " + white.getType();
            }
        } else if (val < 0) {
            if (white.getType() == HandType.HIGH_CARD) {
                winner = "White wins with " + white.getType() + ":" + white.getHighestCard() +
                        " against " + black.getType() + ":" + black.getHighestCard();
            } else {
                winner = "White wins with " + white.getType() + " against " + black.getType();
            }
        } else {
            winner = "Tie.";
        }

        System.out.println(winner);


    }
}
