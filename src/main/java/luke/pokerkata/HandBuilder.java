package luke.pokerkata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke on 5/3/2016.
 *
 */
public class HandBuilder {


    public static List<Card> buildBlackHand(ArrayList<String> cardCodes) {
        List<Card> output = new ArrayList<Card>();
        for (String item : cardCodes.subList(0,5)) {
            output.add(new Card(item));
        }
        return output;
    }
    public static List<Card> buildWhiteHand(ArrayList<String> cardCodes) {
        List<Card> output = new ArrayList<Card>();
        for (String item : cardCodes.subList(5,10)) {
            output.add(new Card(item));
        }
        return output;
    }

    public static ArrayList<String> extractCards(String c) throws IllegalArgumentException{
        c = c + " ";
        ArrayList<String> cardCodes = new ArrayList<String>();
        for (int i = c.indexOf(':') + 2; i < c.indexOf("Whi")-2; i += 3) {
            cardCodes.add(c.substring(i, Math.min(c.indexOf("Whi"), i + 3)));
        }
        for (int i = c.indexOf("e:") + 3; i < c.length(); i += 3) {
            cardCodes.add(c.substring(i, Math.min(c.length(), i + 3)));
        }
        if(cardCodes.size()==10) {
            return cardCodes;
        }
        throw new IllegalArgumentException("Invalid input string.  Please try again.");
    }
}