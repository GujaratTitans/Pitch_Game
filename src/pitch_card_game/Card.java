/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pitch_card_game;

/**
 *
 * @author 12898
 */
public class Card {

	private String rank;
	private String suit;

	
	public Card(String suit, String rank) {
		
		this.suit = suit;
                this.rank = rank;
	}

	public String getRank() {
		return rank;
	}
        
        public void setRank(String rank){
            this.rank =rank;
        }

	public String getSuit() {
		return suit;
	}
        
        public void setSuit(String suit){
            this.suit =suit;
        }
        
        @Override
	public String toString() {
		
		return rank + " of " + suit;
	}

	public  int getCardScore(){
		String rank = this.getRank();
		int playerScore = 0;
		// Assign scores based on rank
		if (rank.equals("Ace")) {
			playerScore += 4;
		} else if (rank.equals("King")) {
			playerScore += 3;
		}
		else if(rank.equals("Queen")) {
			playerScore += 2;
		} else if (rank.equals("Jack")) {
			playerScore += 1;
		} else {
			/**
			 * Below can be defined to anything, for us, we are making it same value.
			 */
			playerScore += Integer.parseInt(rank);
		}


		return playerScore;
	}
    
}
