package ca.sheridancollege.project;


/**
 * A concrete class that represents any grouping of cards for a Game.
 * The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author Gurvinderbeer Singh
 * @author Harnoor Singh
 */

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {
    private ArrayList<Card> cards; // List of cards
    private int size; // Maximum size of the group

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>(size);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards); // Shuffle the cards
    }

    public void addCard(Card card) {
        if (card != null && cards.size() < size) {
            cards.add(card); // Add card if it is not null and there is space
        }
    }

    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0); // Remove and return the top card
        }
        return null; // Return null if there are no cards to deal
    }
}
