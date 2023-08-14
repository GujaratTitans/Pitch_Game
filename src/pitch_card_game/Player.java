package pitch_card_game;
import java.util.*;

public class Player {

    private final String name;
    private final List<Card> hand = new ArrayList<>();
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

    public void setScore(int score) {
        this.score = score;
    }

}