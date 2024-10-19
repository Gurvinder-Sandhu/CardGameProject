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
public class Main {
    public static void main(String[] args) {
        // Create and start the game
        WarGame warGame = new WarGame("War Card Game");
        
        // Add two players to the game
        WarPlayer player1 = new WarPlayer("Player 1");
        WarPlayer player2 = new WarPlayer("Player 2");

        warGame.addPlayer(player1);
        warGame.addPlayer(player2);
        
        // Start the game
        warGame.play();
    }
}