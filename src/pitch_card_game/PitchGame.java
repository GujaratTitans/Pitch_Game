/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pitch_card_game;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PitchGame {
    private final List<Player> players;
    private final Deck deck;

    public PitchGame(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
        deck.shuffle();
    }

    public void dealInitialCards(int numCards) {
        for (Player player : players) {
            for (int i = 0; i < numCards; i++) {
                Card card = deck.dealCard();
                if (card != null) {
                    player.receiveCard(card);
                }
            }
        }
    }

    public void play() {
        int currentPlayerIndex = 0;

        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() + "'s turn.");
            System.out.println("Your hand: " + currentPlayer.getHand());

            if (currentPlayer.getHand().isEmpty()) {
                System.out.println(currentPlayer.getName() + " has no more cards. Game over!");
                break;
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Select a card to play (0 to N-1): ");
            int cardIndex = scanner.nextInt();

            Card playedCard = currentPlayer.playCard(cardIndex);
            if (playedCard == null) {
                System.out.println("Invalid card index. Try again.");
                continue;
            }

            System.out.println(currentPlayer.getName() + " played: " + playedCard);

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

            boolean allPlayersFinished = true;
            for (Player player : players) {
                if (!player.getHand().isEmpty()) {
                    allPlayersFinished = false;
                    break;
                }
            }

            if (allPlayersFinished) {
                break;
            }
        }

        calculateScores();
        displayResults();
    }

   private void calculateScores() {
        for (Player player : players) {
            int playerScore = 0;
            for (Card card : player.getHand()) {
                String rank = card.getRank();
                // Assign scores based on rank
                if (rank.equals("Ace")) {
                    playerScore += 11;
                } else if (rank.equals("King")) {
                    playerScore += 10;
                } else {
                    playerScore += Integer.parseInt(rank);
                }
            }
            player.setScore(playerScore);
        }
    }

    private void displayResults() {
        Collections.sort(players, Comparator.comparingInt(Player::getScore).reversed());

        System.out.println("\nGame Results:");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println("Rank " + (i + 1) + ": " + player.getName()  /*" + - Score: " + player.getScore()*/);
        }

        System.out.println("\n" + players.get(0).getName() + " wins!");
    }
}
