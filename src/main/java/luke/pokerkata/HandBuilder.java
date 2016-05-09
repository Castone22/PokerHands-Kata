package luke.pokerkata;

import java.util.ArrayList;
import java.util.List;

/**
 * A string parser helper method that generates hands using a formatted input string.
 *
 * @author Lucas Ridge lzridge.04@gmail.com
 * @version 1.0
 * @since 2010-03-31
 */

public class HandBuilder {

    /**
     * Helper function that parses black's hand
     *
     * @param cardCodes the full parsed list of card codes usually built by extractCards.
     */

    public static List<Card> buildBlackHand(ArrayList<String> cardCodes) {
        List<Card> output = new ArrayList<Card>();
        for (String item : cardCodes.subList(0,5)) {
            output.add(new Card(item));
        }
        return output;
    }

    /**
     * Helper function that parses white's hand
     *
     * @param cardCodes the full parsed list of card codes usually built by extractCards.
     */
    public static List<Card> buildWhiteHand(ArrayList<String> cardCodes) {
        List<Card> output = new ArrayList<Card>();
        for (String item : cardCodes.subList(5,10)) {
            output.add(new Card(item));
        }
        return output;
    }

    /**
     * Function that generates an arraylist of card codes used by the hand builder functions. Throws an illegal argument
     * exception should the string not follow expected input.
     *
     * @param c a formatted input following Black: #S #S #S #S #S  White: #S #S #S #S #S".
     */
    public static ArrayList<String> extractCards(String c) throws IllegalArgumentException{
        c = c + " "; // Adding a space to the end of the string to make things consistent for the parser.
        ArrayList<String> cardCodes = new ArrayList<String>();
        //Iterating through the length of the string, parsing out every 3rd set of letters to create an output
        //that can be parsed easily.  Once for white, and once for black.  The first through fifth codes are black's
        //hand and the last 5 are white's hand.
        for (int i = c.indexOf(':') + 2; i < c.indexOf("Whi")-2; i += 3) {
            cardCodes.add(c.substring(i, Math.min(c.indexOf("Whi"), i + 3)));
        }
        for (int i = c.indexOf("e:") + 3; i < c.length(); i += 3) {
            cardCodes.add(c.substring(i, Math.min(c.length(), i + 3)));
        }
        //checking to make sure we have 10 cards since this is 5 card poker.
        if(cardCodes.size()==10) {
            return cardCodes;
        }
        //if not throw an error!
        throw new IllegalArgumentException("Invalid input string.  Please try again.");
    }
}