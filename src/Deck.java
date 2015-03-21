import java.util.ArrayList;
import java.lang.Math;

public class Deck{
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	public Deck(){
		for (int i = 0; i < 52; i++){
			deck.add(new Card((i % 13) + 1, i / 13));
		}
	}
	
	public void deal(Player p1, Player p2, Player p3, Player p4){
		int index1;
		int index2;
		int index3;
		int index4;
		while (deck.size() > 0){
			index1 = (int) (Math.random() * deck.size());
			p1.addCard(deck.get(index1));
			deck.remove(index1);
			index2 = (int) (Math.random() * deck.size());
			p2.addCard(deck.get(index2));
			deck.remove(index2);
			index3 = (int) (Math.random() * deck.size());
			p3.addCard(deck.get(index3));
			deck.remove(index3);
			index4 = (int) (Math.random() * deck.size());
			p4.addCard(deck.get(index4));
			deck.remove(index4);
		}
	}
}