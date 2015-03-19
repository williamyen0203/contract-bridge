interface ICard extends Comparable<Card>{
	public static int SPADES = 3;
	public static int HEARTS = 2;
	public static int CLUBS = 1;
	public static int DIAMONDS = 0;

	public int getSuit();

	public int getRank();
	
	public int compareSuit(Card card);
}

public class Card implements ICard{
	private int rank;
	private int suit;

	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}
	
	public int compareTo(Card card){
		if (this.getRank() > card.getRank()){
			return 1;
		} else if (this.getRank() == card.getRank()){
			return 0;
		} else{
			return -1;
		}
	}
	
	public int compareSuit(Card card){
		if (this.getSuit() > card.getSuit()){
			return 1;
		} else if (this.getSuit() == card.getSuit()){
			return 0;
		} else{
			return -1;
		}
	}
}