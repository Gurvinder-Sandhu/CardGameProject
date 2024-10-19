package ca.sheridancollege.project;

import java.util.ArrayList;

public class WarGame extends Game {
    private GroupOfCards deck; // The deck of cards used in the game

    public WarGame(String name) {
        super(name);
        deck = new GroupOfCards(52); // Standard deck of 52 cards
        initializeDeck(); // Initialize the deck
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String suit : suits) {
            for (int value = 2; value <= 14; value++) { // Ace high (14)
                deck.addCard(new PlayingCard(suit, value));
            }
        }
    }

    @Override
    public void play() {
        deck.shuffle(); // Shuffle the deck before playing
        dealCards(); // Distribute the cards equally to players

        while (playersStillInGame()) {
            System.out.println("Current card counts: " +
                getPlayers().get(0).getPlayerName() + ": " + ((WarPlayer) getPlayers().get(0)).getHandSize() +
                ", " + getPlayers().get(1).getPlayerName() + ": " + ((WarPlayer) getPlayers().get(1)).getHandSize());

            playRound(); // Play one round
        }
        declareWinner(); // Declare the winner
    }

    private void dealCards() {
        int playerCount = getPlayers().size();
        for (int i = 0; i < 52; i++) { // 52 cards in total
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

        // If either player has no more cards, the game ends
        if (card1 == null || card2 == null) {
            return; // Stops the game if any player runs out of cards
        }

        System.out.println(player1.getPlayerName() + " plays: " + card1);
        System.out.println(player2.getPlayerName() + " plays: " + card2);

        // Compare card values to determine the round's winner
        if (card1.getValue() > card2.getValue()) {
            System.out.println(player1.getPlayerName() + " wins the round.");
            player1.receiveCard(card1); // Winner takes both cards
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

    private void handleWar(WarPlayer player1, WarPlayer player2, Card card1, Card card2) {
        ArrayList<Card> warPile = new ArrayList<>(); // Collect cards in the war pile
        warPile.add(card1);
        warPile.add(card2);

        // Check if both players have enough cards to continue the war
        if (player1.getHandSize() < 4) {
            System.out.println(player1.getPlayerName() + " doesn't have enough cards to continue the war. " + player2.getPlayerName() + " wins!");
            player2.receiveCards(warPile); // Player 2 takes all war cards
            return;
        }
        if (player2.getHandSize() < 4) {
            System.out.println(player2.getPlayerName() + " doesn't have enough cards to continue the war. " + player1.getPlayerName() + " wins!");
            player1.receiveCards(warPile); // Player 1 takes all war cards
            return;
        }

        // Players place 3 cards face down and play 1 card face up
        for (int i = 0; i < 3; i++) {
            if (player1.hasCards()) warPile.add(player1.playCard()); // Adds 3 cards face down
            if (player2.hasCards()) warPile.add(player2.playCard());
        }

        Card warCard1 = player1.playCard();
        Card warCard2 = player2.playCard();

        System.out.println(player1.getPlayerName() + " plays: " + warCard1 + " (War)");
        System.out.println(player2.getPlayerName() + " plays: " + warCard2 + " (War)");

        // Compare the war cards to determine the winner
        if (warCard1.getValue() > warCard2.getValue()) {
            System.out.println(player1.getPlayerName() + " wins the War.");
            player1.receiveCards(warPile); // Winner takes all war cards
            player1.receiveCard(warCard1); // Give the card played in war
            player1.receiveCard(warCard2); // Give the losing card in war as well
        } else if (warCard2.getValue() > warCard1.getValue()) {
            System.out.println(player2.getPlayerName() + " wins the War.");
            player2.receiveCards(warPile); // Winner takes all war cards
            player2.receiveCard(warCard1); // Give the card played in war
            player2.receiveCard(warCard2); // Give the losing card in war as well
        } else {
            System.out.println("It's a tie again! Another war should occur.");
            // Add further handling if you want to keep playing in case of a tie
        }
    }

    @Override
    public void declareWinner() {
        WarPlayer player1 = (WarPlayer) getPlayers().get(0);
        WarPlayer player2 = (WarPlayer) getPlayers().get(1);
        
        if (player1.getHandSize() > player2.getHandSize()) {
            System.out.println(player1.getPlayerName() + " wins the game with " + player1.getHandSize() + " cards.");
        } else if (player2.getHandSize() > player1.getHandSize()) {
            System.out.println(player2.getPlayerName() + " wins the game with " + player2.getHandSize() + " cards.");
        } else {
            System.out.println("The game ends in a tie!");
        }
    }
}
