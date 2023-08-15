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
        /**
         * Assigning cards to each player
         */
        for (Player player : players) {
            for (int i = 0; i < numCards; i++) {
                Card card = deck.dealCard();
                if (card != null) {
                    player.receiveCard(card);
                }
            }
        }
    }

    public Card gamePlayForAPlayer(Player player){
        System.out.println("\n" + player.getName() + "'s turn.");
        System.out.println("Cards in hand: " + player.getHand());

        if (player.getHand().isEmpty()) {
            System.out.println(player.getName() + " has no more cards. Game over!");
            return null;
        }

        Scanner scanner = new Scanner(System.in);
        int numberOfCards = player.getHand().size();
        String message = "Select a card to play (0 to " + ( numberOfCards - 1) + "):";
        if(numberOfCards == 1){
            message = "Playing last card, Press 0  to complete your round:";
        }

        System.out.print(message);
        int cardIndex = scanner.nextInt();

        Card playedCard = player.playCard(cardIndex);

        if (playedCard == null) {
            System.out.println("Invalid card index. Try again.");
            return gamePlayForAPlayer(player);
        }

        return  playedCard;
    }



    public  boolean checkCurrentWinner(Card  cardA, Card cardB){
        /**
         * If A : return false
         * else : Return true
         */

        int getFirstScore = cardA.getCardScore();
        int getSecondScore = cardB.getCardScore();

        return getFirstScore < getSecondScore;

    }
    public void play(int numCards, int numPlayers) {
        System.out.println("\n ####### Starting the game #######\n");
        System.out.println("Game will be continue for "+ numCards + " rounds.");
        int currentPlayerIndex = 0;

        /**
         * Logic 1 :  The one who win a round will be starting the next round of game.
         * Logic 2 :  Winner will get the score and other players will be compromised with 0 score.
         * Logic 3 :  We are not forcing user with same suite rule.
         */

        int startPlayer = 0;
        Card winningCard = null;
        int winnerIndex = 0;

        for(int i = 0; i < numCards; i++) {

            int roundNumber = i + 1;

            System.out.println("\n ###### Playing round number : "+ roundNumber + " ######\n");

            Player starter = players.get(startPlayer);

            System.out.println("Round will be started by " + starter.getName());

            winningCard = gamePlayForAPlayer(starter);

            if(winningCard == null) {
                break;
            }


            winnerIndex =  startPlayer;

            currentPlayerIndex = startPlayer + 1;

            while (currentPlayerIndex != startPlayer){
                /**
                 * Play for other players.
                 */

                currentPlayerIndex = currentPlayerIndex % numPlayers;
//                System.out.println("Playing for index : " + currentPlayerIndex);
                Player currentPlayer = players.get(currentPlayerIndex);


                Card otherPlayerCard = gamePlayForAPlayer(currentPlayer);

                if(otherPlayerCard != null){
                    /**
                     * Logic behind winner of existing winner and current player
                     */
                    boolean isCurrentWinner =  checkCurrentWinner(winningCard, otherPlayerCard);
                    if(isCurrentWinner) {
                        winnerIndex = currentPlayerIndex % numPlayers;
                        winningCard = otherPlayerCard;
                    }
                }

                currentPlayerIndex = currentPlayerIndex + 1;
                currentPlayerIndex = currentPlayerIndex % numPlayers;

            }

            /**
             * Winner of round
             */
            Player winningPlayer = players.get(winnerIndex);
            winningPlayer.addWinningCard(winningCard);

            System.out.println("\n\nWinner of round number : " + roundNumber + " is " + winningPlayer.getName() +"\n\n");

            startPlayer  = winnerIndex;
        }

        /**
         * Calculating Scores of players.
         */
        calculateScores();
        displayResults();
    }

   private void calculateScores() {
       System.out.println("Calculating scores...");
        for (Player player : players) {
            System.out.println("Player : " + player);
            int playerScore = 0;
            for (Card card : player.getWinningCards()) {
                 int currentCardScore = card.getCardScore();
                 playerScore = playerScore + currentCardScore;

            }
            player.setScore(playerScore);
        }
    }

    private void displayResults() {
        Collections.sort(players, Comparator.comparingInt(Player::getScore).reversed());

        System.out.println("\nGame Results:");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println("Rank " + (i + 1) + ": " + player.getName()  + " + - Score: " + player.getScore());
        }

        System.out.println("\n" + players.get(0).getName() + " wins!");
    }
}
