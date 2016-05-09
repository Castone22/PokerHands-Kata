package luke.pokerkata;

/**
 * Created by Luke on 5/2/2016.
 *
 */
public class Card {
    private final Suit suit;
    private final Face face;

    public Card(Suit suit, Face face){
        this.suit = suit;
        this.face = face;
    }

    public Card(String c){
        this.suit = Suit.compareCode(c.substring(c.length()-2, c.length()-1));
        this.face = Face.compareCode(c.substring(0,c.length()-2));
    }

    public Suit getSuit(){
        return suit;
    }

    public Face getFace(){
        return face;
    }

    public enum Suit{
        CLUBS      ("C"),
        DIAMONDS   ("D"),
        HEARTS     ("H"),
        SPADES     ("S");

        private String code;

        Suit(String c) {
            code = c;
        }

        public static Suit compareCode(String c) throws IllegalArgumentException {
            for (Suit v : values()) {
                if (v.code.equals(c)) return v;
            }
            throw new IllegalArgumentException("Unknown suit: " + c);
        }
    }

    public enum Face{
        TWO       ("2"),
        THREE     ("3"),
        FOUR      ("4"),
        FIVE      ("5"),
        SIX       ("6"),
        SEVEN     ("7"),
        EIGHT     ("8"),
        NINE      ("9"),
        TEN       ("T"),
        JACK      ("J"),
        QUEEN     ("Q"),
        KING      ("K"),
        ACE       ("A");

        private String code;

        Face(String c) {
            code = c;
        }

        public static Face compareCode(String c) throws IllegalArgumentException {
            for (Face v : values()) {
                if (v.code.equals(c)) return v;
            }
            throw new IllegalArgumentException("Unknown face: " + c);

        }
    }
}
