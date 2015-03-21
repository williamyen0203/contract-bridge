public class Card{
	private int rank;
	private int suit;

	public Card(int rank, int suit){
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank(){
		if (rank == 1){
			return 14;
		} else{
			return rank;
		}
	}

	public int getSuit(){
		return suit;
	}
}