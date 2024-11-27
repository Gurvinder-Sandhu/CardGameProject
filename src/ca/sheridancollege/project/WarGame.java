package ca.sheridancollege.project;

import java.util.ArrayList;

public class WarGame extends Game {
    private GroupOfCards deck; 

    public WarGame(String name) {
        super(name);
        deck = new GroupOfCards(52); 
        initializeDeck(); 
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String suit : suits) {
            for (int value = 2; value <= 14; value++) { 
                deck.addCard(new PlayingCard(suit, value));
            }
        }
    }

    @Override
    public void play() {
        deck.shuffle(); 
        dealCards(); 

        while (playersStillInGame()) {
            System.out.println("Current card counts: " +
                getPlayers().get(0).getPlayerName() + ": " + ((WarPlayer) getPlayers().get(0)).getHandSize() +
                ", " + getPlayers().get(1).getPlayerName() + ": " + ((WarPlayer) getPlayers().get(1)).getHandSize());

            playRound(); 
        }
        declareWinner(); 
    }

    protected void dealCards() {
        int playerCount = getPlayers().size();
        for (int i = 0; i < 52; i++) {
            WarPlayer player = (WarPlayer) getPlayers().get(i % playerCount);
            player.receiveCard(deck.dealCard());
        }
    }

    private boolean playersStillInGame() {
        WarPlayer player1 = (WarPlayer) getPlayers().get(0);
        WarPlayer player2 = (WarPlayer) getPlayers().get(1);
        return player1.hasCards() && player2.hasCards();
    }

    private void playRound() {
        WarPlayer player1 = (WarPlayer) getPlayers().get(0);
        WarPlayer player2 = (WarPlayer) getPlayers().get(1);

        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        if (card1 == null || card2 == null) {
            return; 
        }

        System.out.println(player1.getPlayerName() + " plays: " + card1);
        System.out.println(player2.getPlayerName() + " plays: " + card2);

        if (card1.getValue() > card2.getValue()) {
            System.out.println(player1.getPlayerName() + " wins the round.");
            player1.receiveCard(card1);
            player1.receiveCard(card2);
        } else if (card2.getValue() > card1.getValue()) {
            System.out.println(player2.getPlayerName() + " wins the round.");
            player2.receiveCard(card1);
            player2.receiveCard(card2);
        } else {
            System.out.println("It's a tie! Entering War...");
            handleWar(player1, player2, card1, card2);
        }
    }

    public void handleWar(WarPlayer player1, WarPlayer player2, Card card1, Card card2) {
    ArrayList<Card> warPile = new ArrayList<>();
    warPile.add(card1);
    warPile.add(card2);

    while (true) {
        for (int i = 0; i < 3; i++) {
            if (player1.hasCards()) {
                warPile.add(player1.playCard());
            }
            if (player2.hasCards()) {
                warPile.add(player2.playCard());
            }
        }

        Card warCard1 = player1.playCard();
        Card warCard2 = player2.playCard();

        if (warCard1 == null || warCard2 == null) {
            break;
        }

        System.out.println(player1.getPlayerName() + " plays: " + warCard1);
        System.out.println(player2.getPlayerName() + " plays: " + warCard2);

        if (warCard1.getValue() > warCard2.getValue()) {
            System.out.println(player1.getPlayerName() + " wins the War.");
            player1.receiveCard(warCard1);
            player1.receiveCard(warCard2);
            for (Card c : warPile) { 
                player1.receiveCard(c);
            }
            break;
        } else if (warCard2.getValue() > warCard1.getValue()) {
            System.out.println(player2.getPlayerName() + " wins the War.");
            player2.receiveCard(warCard1);
            player2.receiveCard(warCard2);
            for (Card c : warPile) { 
                player2.receiveCard(c);
            }
            break;
        } else {
            System.out.println("The War continues...");
            warPile.add(warCard1);
            warPile.add(warCard2);
        }
    }
}


    @Override
    public void declareWinner() {
        WarPlayer player1 = (WarPlayer) getPlayers().get(0);
        WarPlayer player2 = (WarPlayer) getPlayers().get(1);

        if (player1.getHandSize() > player2.getHandSize()) {
            System.out.println(player1.getPlayerName() + " wins the game!");
        } else if (player2.getHandSize() > player1.getHandSize()) {
            System.out.println(player2.getPlayerName() + " wins the game!");
        } else {
            System.out.println("The game ends in a draw!");
        }
    }
}
