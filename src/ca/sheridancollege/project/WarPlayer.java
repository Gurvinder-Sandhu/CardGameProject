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


import java.util.LinkedList;

public class WarPlayer extends Player {
    private LinkedList<Card> hand;

    public WarPlayer(String name) {
        super(name);
        hand = new LinkedList<>();
    }

    public void receiveCard(Card card) {
        if (card != null) {
            hand.addLast(card);
        }
    }

    public void receiveCard(Card[] cards) {
        for (Card card : cards) {
            receiveCard(card);
        }
    }

    public Card playCard() {
        return hand.isEmpty() ? null : hand.removeFirst();
    }

    public int getHandSize() {
        return hand.size();
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    @Override
    public void play() {
        System.out.println(getPlayerName() + " is playing.");
    }
}
