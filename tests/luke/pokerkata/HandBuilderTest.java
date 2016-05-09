package luke.pokerkata;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static luke.pokerkata.HandBuilder.*;
import static org.junit.Assert.*;

/**
 * Created by Luke on 5/3/2016.
 *
 */
public class HandBuilderTest {
    @Test
    public void testExtractCards() throws Exception {
        ArrayList<String> result;
        result = extractCards("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
        for(String item : result) {
            System.out.println(item);
        }
        assertTrue(result.contains("2H "));
        assertTrue(result.contains("3D "));
        assertTrue(result.contains("5S "));
        assertTrue(result.contains("9C "));
        assertTrue(result.contains("KD "));
        assertTrue(result.contains("2C "));
        assertTrue(result.contains("3H "));
        assertTrue(result.contains("4S "));
        assertTrue(result.contains("8C "));
        assertTrue(result.contains("AH "));

    }
    @Test
    public void testBuildHandsAndToString() throws Exception {
        ArrayList<String> result;
        result = extractCards("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
        Hand black = new Hand(buildBlackHand(result));
        Hand white = new Hand(buildWhiteHand(result));
        String message =
                "Hand contains:\n"+
                "TWO of HEARTS\n"+
                "THREE of DIAMONDS\n"+
                "FIVE of SPADES\n"+
                "NINE of CLUBS\n"+
                "KING of DIAMONDS\n";
        String message2 =
                "Hand contains:\n" +
                "TWO of CLUBS\n" +
                "THREE of HEARTS\n" +
                "FOUR of SPADES\n" +
                "EIGHT of CLUBS\n" +
                "ACE of HEARTS\n";
        assertEquals(message, black.toString());
        assertEquals(message2 ,white.toString());


    }
}

