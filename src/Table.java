import java.util.*;

public class Table{
	private ArrayList<Card> table = new ArrayList<Card>();
	
	public Table(){
	}
	
	public void placeCard(Player player, int n){
		table.add(player.getCard(n));
		player.removeCard(n);
	}
	
	public int getHighestCard(){
		int highestCard = 0;
		for (int i = 0; i < 4; i++){
			if (table.get(highestCard).getRank() < table.get(i).getRank() && table.get(highestCard).getSuit() == table.get(i).getSuit()){
				highestCard = i;
			}
		}		
		return highestCard;
	}
	
	public void clearTable(){
		table.clear();
	}
	
	public String toString(){
		String s = "";
		if (table.size() == 0){
			return "No cards on table";
		}
		for(int i = 0; i < table.size(); i++){
			s += "[";
			s += table.get(i).getRank();
			s += ", ";
			s += table.get(i).getSuit();
			s += "]";
		}
		return "Table: " + s + "\nCards on table: " + table.size();
	}
}
