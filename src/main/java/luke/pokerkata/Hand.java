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
 * Created by Luke on 5/2/2016.
 *
 */
public class Hand implements Comparable<Hand> {
    private final List<Card> cardList;
    private final LinkedList<Card.Face> uniqueFaces = new LinkedList<>();
    private final HandType type;

    public Hand(List<Card> cardList) {
        this.cardList = cardList;
        Set<Card.Suit> suits = EnumSet.noneOf(Card.Suit.class);
        Multiset<Card.Face> faces = EnumMultiset.create(Card.Face.class);
        for (Card card : cardList) {
            suits.add(card.getSuit());
            faces.add(card.getFace());
        }

        for (Entry<Card.Face> entry : byCountThenRank.immutableSortedCopy(faces.entrySet())) {
            uniqueFaces.addFirst(entry.getElement());
        }

        Card.Face first = uniqueFaces.getFirst();
        int numUnique = uniqueFaces.size();

        if (numUnique == 5) {
            boolean flush = suits.size() == 1;
            if (first.ordinal() - uniqueFaces.getLast().ordinal() == 4) {
                type = flush ? STRAIGHT_FLUSH : STRAIGHT;
            } else {
                type = flush ? FLUSH : HIGH_CARD;
            }

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

    @Override
    public final int compareTo(Hand otherHand) {
        return byTypeThenFaces.compare(this, otherHand);
    }

    private static final Ordering<Entry<Card.Face>> byCountThenRank;

    static {
        Comparator<Entry<Card.Face>> byCount = comparingInt(Entry::getCount);
        Comparator<Entry<Card.Face>> byRank = comparing(Entry::getElement);
        byCountThenRank = from(byCount.thenComparing(byRank));
    }

    private static final Comparator<Hand> byTypeThenFaces;

    static {
        Comparator<Hand> byType = comparing((Hand hand) -> hand.type);
        Function<Hand, Iterable<Card.Face>> getFaces = (Hand hand) -> hand.uniqueFaces;
        Comparator<Hand> byFaces = comparing(getFaces, natural().lexicographical());
        byTypeThenFaces = byType.thenComparing(byFaces);
    }

}

