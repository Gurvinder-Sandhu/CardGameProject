/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Gurvinderbeer Singh
 * @author Harnoor Singh
 */

import java.util.ArrayList;

public class WarPlayer extends Player {
    private ArrayList<Card> hand; // Cards in the player's hand

    public WarPlayer(String name) {
        super(name);
        hand = new ArrayList<>(); // Initialize an empty hand
    }

    @Override
    public void play() {
        System.out.println(getPlayerName() + " plays a card.");
    }

    public Card playCard() {
        if (hand.isEmpty()) {
            System.out.println(getPlayerName() + " has no more cards.");
            return null; // No cards left to play
        }
        return hand.remove(0); // Removes and returns the top card from hand
    }

    public void receiveCard(Card card) {
        hand.add(card); // Add a single card to the player's hand
    }

    public boolean hasCards() {
        return !hand.isEmpty(); // Check if the player has cards
    }

    public int getHandSize() {
        return hand.size(); // Get the current hand size
    }

    public ArrayList<Card> getCards() {
        return hand; // Get the list of cards in hand
    }

    public void receiveCards(ArrayList<Card> cards) {
        hand.addAll(cards); // Add multiple cards to the player's hand
    }
}
