/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author Gurvinderbeer Singh
 * @author Harnoor Singh
 */

import java.util.ArrayList;

public abstract class Game {
    private ArrayList<Player> players; // List of players in the game
    private String gameName;

    public Game(String name) {
        this.gameName = name;
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player); // Add a player to the game
    }

    public ArrayList<Player> getPlayers() {
        return players; // Get the list of players
    }

    public abstract void play(); // Abstract method for the game logic

    public abstract void declareWinner(); // Abstract method for declaring the winner
}
