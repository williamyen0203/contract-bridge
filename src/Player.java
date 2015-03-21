import java.util.*;

public class Player{
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Card> handClone;
	private int points = 0;
	
	public Card getCard(int n){
		return hand.get(n);
	}
	
	public Card getCloneCard(int n){
		return handClone.get(n);
	}
	
	public void cloneHand(ArrayList<Card> hand){
		handClone = (ArrayList<Card>) hand.clone();
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public int getRandomCard(){
		return (int) (Math.random() * hand.size());
	}
	
	public int getHighestCardOfSuit(int suit){
		int highest = 0;
		int index = -1;
		for (int i = 0; i < hand.size(); i++){
			if (hand.get(i).getSuit() == suit && hand.get(i).getRank() > highest){
				highest = hand.get(i).getRank();
				index = i;
			}
		}
		if (index != -1){
			return index;
		} else{
			return (int) (Math.random() * hand.size());
		}
	}
	
	public String getCardRank(int n){
		if (hand.get(n).getRank() == 11){
			return "J";
		} else if (hand.get(n).getRank() == 12){
			return "Q";
		} else if (hand.get(n).getRank() == 13){
			return "K";
		} else if (hand.get(n).getRank() == 14){
			return "A";
		} else{
			return "" + hand.get(n).getRank();
		}
	}
	
	public String getCardSuit(int n){
		if (hand.get(n).getSuit() == 0){
			return "diamond";
		} else if (hand.get(n).getSuit() == 1){
			return "club";
		} else if (hand.get(n).getSuit() == 2){
			return "heart";
		} else return "spade";
	}
	
	public int getRank(int n){
		return hand.get(n).getRank();
	}
	
	public int getCloneRank(int n){
		return handClone.get(n).getRank();
	}
	
	public int getSuit(int n){
		return hand.get(n).getSuit();
	}
	
	public int getCloneSuit(int n){
		return handClone.get(n).getSuit();
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
}
