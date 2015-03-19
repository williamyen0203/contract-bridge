import java.util.*;

public class Player{
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int cardsInHand = 0;
	private int points = 0;
	
	public Card getCard(int n){
		return hand.get(n);
	}
	
	public void addCard(Card card){
		hand.add(card);
	}
	
	public void removeCard(int n){
		hand.remove(n);
	}
	
	public int getCardsInHand(){
		return hand.size();
	}
	
	public void addPoint(){
		points++;
	}
	
	public int getPoints(){
		return points;
	}
	
	public String toString(){
		String s = "";
		if (hand.size() == 0){
			return "Hand is empty";
		}
		for(int i = 0; i < hand.size(); i++){
			s += "[";
			s += hand.get(i).getRank();
			s += ", ";
			s += hand.get(i).getSuit();
			s += "]";
		}
		return "Hand: " + s + "\nNumber of cards in hand: " + hand.size();
	}
}
