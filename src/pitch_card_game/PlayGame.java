package pitch_card_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pitch_card_game.PitchGame;
import pitch_card_game.Player;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 12898
 */
public class PlayGame {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();

        System.out.println("Number of cards with each player :");

        int numCards = scanner.nextInt();

        if(numPlayers < 2) {
            System.out.println("Minimum of 2 players needed to start the game");
        }
        else if(numCards<1) {
            System.out.println("Minimum of 1 card needed to start the game");
        }
        else{
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < numPlayers; i++) {
                System.out.print("Enter player " + (i + 1) + "'s name: ");
                String playerName = scanner.next();
                players.add(new Player(playerName));
            }


            PitchGame pitchGame = new PitchGame(players);
            pitchGame.dealInitialCards(numCards);
            /*
             * Play for number of rounds.
             * */

            System.out.println("Displaying players information before starting...");
            for(Player player : players){
                System.out.println("\n");
                System.out.println(player);
            }
            pitchGame.play(numCards, numPlayers);
        }


    
    }
    
}
