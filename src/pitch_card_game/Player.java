package pitch_card_game;
import java.util.*;

public class Player {

    private final String name;
    private final List<Card> hand = new ArrayList<>();

    private List<Card> winningCards = new ArrayList<>(); //  maintaining winning history of each round.
    private int score;
   
   public Player(String name) {
        this.name = name;
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public Card playCard(int cardIndex) {
        if (cardIndex < 0 || cardIndex >= hand.size()) {
            return null;
        }
        /**
         * Removing the card from players hand
         */
        return hand.remove(cardIndex);
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getScore() {
            return score;
    }

    public List<Card> getWinningCards() {
        return winningCards;
    }

    public void setWinningCards(List<Card> winningCards) {
        this.winningCards = winningCards;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public  void  addWinningCard(Card card){
       this.winningCards.add(card);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hand=" + hand +
                ", winningCards=" + winningCards +
                ", score=" + score +
                '}';
    }
}