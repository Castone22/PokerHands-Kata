package luke.pokerkata;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Luke on 5/2/2016.
 */
public class CardTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetSuit() throws Exception {
        Card card1 = new Card(Card.Suit.SPADES, Card.Face.ACE);
        Card card2 = new Card("AS");
        assertEquals(Card.Suit.SPADES, card1.getSuit());
        assertEquals(Card.Suit.SPADES, card2.getSuit());
    }

    @Test
    public void testGetFace() throws Exception {
        Card card1 = new Card(Card.Suit.SPADES, Card.Face.ACE);
        Card card2 = new Card("AS");
        assertEquals(Card.Face.ACE, card1.getFace());
        assertEquals(Card.Face.ACE, card2.getFace());
    }

}