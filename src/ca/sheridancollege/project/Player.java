/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author Gurvinderbeer Singh
 * @author Harnoor Singh
 */
public abstract class Player {
    private String playerName;

    public Player(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public abstract void play(); // Abstract method to be implemented by subclasses
}
