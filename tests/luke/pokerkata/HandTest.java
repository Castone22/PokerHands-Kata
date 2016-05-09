package luke.pokerkata;

import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;
import static luke.pokerkata.HandBuilder.*;

/**
 * Created by Luke on 5/8/2016.
 *
 */
public class HandTest {
    @Test
    public void testGetCardList() throws Exception {

    }

    @Test
    public void testToString() throws Exception {
        ArrayList<String> result;
        result = extractCards("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
        Hand black = new Hand(buildBlackHand(result));
        String message = "Hand contains:\nTWO of HEARTS\nTHREE of DIAMONDS\nFIVE of SPADES\nNINE of CLUBS\n"+
        "KING of DIAMONDS\n";
        assertEquals(message, black.toString());
    }


    @Test
    public void testGetType() throws Exception {
        ArrayList<String> result;
        result = extractCards("Black: TH JH QH KH AH  White: 2D 3H 5C 9S KH");
        Hand black = new Hand(buildBlackHand(result));
        Hand white = new Hand(buildWhiteHand(result));
        System.out.println(black.getType());
        System.out.println(white.getType());
    }

    @Test
    public void testCompareTo() throws Exception {
        int rank;
        ArrayList<String> result;
        result = extractCards("Black: TH JH QH KH AH  White: 2C 3H 4S 8C AH");
        Hand black = new Hand(buildBlackHand(result));
        Hand white = new Hand(buildWhiteHand(result));
        rank = black.compareTo(white);
        System.out.println(rank);

    }

}