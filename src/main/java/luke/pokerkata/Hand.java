package luke.pokerkata;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.Ordering;

import java.util.*;
import java.util.function.Function;

import static com.google.common.collect.Ordering.from;
import static com.google.common.collect.Ordering.natural;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static luke.pokerkata.HandType.*;

/**
 *
 * @author Lucas Ridge  lzridge.04@gmail.com
 * @version 1.0
 * @since 2010-03-31
 */

public class Hand implements Comparable<Hand> {
    //places cards in order by their ranking.
    private static final Ordering<Entry<Card.Face>> byCountThenRank;
    //compares hands their typing, in the event that they are the same type, then compares them by each face until a
    //winner is found, or there is a tie.
    private static final Comparator<Hand> byTypeThenFaces;

    static {
        Comparator<Entry<Card.Face>> byCount = comparingInt(Entry::getCount);
        Comparator<Entry<Card.Face>> byRank = comparing(Entry::getElement);
        byCountThenRank = from(byCount.thenComparing(byRank));
    }

    static {
        Comparator<Hand> byType = comparing((Hand hand) -> hand.type);
        Function<Hand, Iterable<Card.Face>> getFaces = (Hand hand) -> hand.uniqueFaces;
        Comparator<Hand> byFaces = comparing(getFaces, natural().lexicographical());
        byTypeThenFaces = byType.thenComparing(byFaces);
    }

    private final List<Card> cardList;
    private final LinkedList<Card.Face> uniqueFaces = new LinkedList<>();
    private final HandType type;

    /**
     * Manual card constructor.
     *
     * @param cardList uses a list of cards typically generated using HandBuilder.
     */
    public Hand(List<Card> cardList) {
        this.cardList = cardList;
        Set<Card.Suit> suits = EnumSet.noneOf(Card.Suit.class);
        Multiset<Card.Face> faces = EnumMultiset.create(Card.Face.class);
        for (Card card : cardList) {
            suits.add(card.getSuit());
            faces.add(card.getFace());
        }

        //Putting the cards in order by rank to make some of the comparators easier to use.
        for (Entry<Card.Face> entry : byCountThenRank.immutableSortedCopy(faces.entrySet())) {
            uniqueFaces.addFirst(entry.getElement());
        }

        //Since the cards are now in order, it's safe to assume that the highest card is now the first one.
        Card.Face first = uniqueFaces.getFirst();
        int numUnique = uniqueFaces.size();

        //If the entire hand is unique, it's worth checking for a flush or a straight.  Guava libraries make this
        //fairly simple.
        if (numUnique == 5) {
            boolean flush = suits.size() == 1;
            if (first.ordinal() - uniqueFaces.getLast().ordinal() == 4) {
                type = flush ? STRAIGHT_FLUSH : STRAIGHT;
            } else {
                type = flush ? FLUSH : HIGH_CARD;
            }
            //After this point it's tuple based logic.  The order of the checks does not matter due to the enum sets.
        } else if (numUnique == 4) {
            type = ONE_PAIR;
        } else if (numUnique == 3) {
            type = faces.count(first) == 2 ? TWO_PAIR : THREE_OF_A_KIND;
        } else {
            type = faces.count(first) == 3 ? FULL_HOUSE : FOUR_OF_A_KIND;
        }

    }

    public HandType getType(){
        return type;
    }

    /**
     * Returns the first and highest card in the ordered list of cards.
     *
     * @return the Face of the highest card in a hand.
     */
    public Card.Face getHighestCard(){
        return uniqueFaces.getFirst();
    }

    /**
     * Lists each card in the hand using it's face and suit.
     *
     * @return the entire contents of a given hand in string form.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Hand contains:\n");
        for (Card c : cardList) {
            stringBuilder.append(c.getFace().toString());
            stringBuilder.append(" of ");
            stringBuilder.append(c.getSuit().toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Comparator used to compare two hands and output the difference in rankings.
     *
     * @return a signed int showing the difference in ranking between two hands.
     */
    @Override
    public final int compareTo(Hand otherHand) {
        return byTypeThenFaces.compare(this, otherHand);
    }

}

