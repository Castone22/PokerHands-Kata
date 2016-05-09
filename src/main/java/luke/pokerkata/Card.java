package luke.pokerkata;

/**
 * @author Lucas Ridge lzridge.04@gmail.com
 * @version 1.0
 * @since 2010-03-31
 */

public class Card {
    private final Suit suit;
    private final Face face;

    /**
     * Manual card constructor.
     *
     * @param suit a suit enum reference
     * @param face a face enum reference
     */

    public Card(Suit suit, Face face){
        this.suit = suit;
        this.face = face;
    }

    /**
     * Card constructor that parses a formatted input string to create a card object.
     *
     * @param c a string in the format of Black: #S #S #S #S #S  White: #S #S #S #S #S per the project spec.
     */

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

        /**
         * enum comparator to create a suit object which is used to define a card.
         *
         * @param c a character code corresponding to a given suit.
         */
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

        /**
         * enum comparator to create a face object which is used to define a card.
         *
         * @param c a character code corresponding to a given face.
         */
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
