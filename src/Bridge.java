import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class Bridge extends Application{	
	public static void main(String[] args){
		
		Scanner scnr = new Scanner(System.in);
		
		int playerTurn = 0;
		Deck deck = new Deck();
		Table table = new Table();
		Player[] players = {new Player(), new Player(), new Player(), new Player()};
		deck.deal(players[0], players[1], players[2], players[3]);
		
		launch(args);
		
		while (!(players[0].getCardsInHand() == 0)){
			System.out.print("================== ");
			System.out.println("Turn " + (14 - players[0].getCardsInHand()));
			for (int i = 0; i < 4; i++){
				System.out.println(players[(playerTurn % 4)].toString());
				System.out.print("Choose a card, p" + ((playerTurn % 4) + 1) + ": ");
				int choice = scnr.nextInt();
				table.placeCard(players[(playerTurn % 4)], choice);
				System.out.println(players[(playerTurn % 4)].toString());
				System.out.println(table.toString() + "\n");
				playerTurn++;
			}
			players[((playerTurn % 4) + table.getHighestCard()) % 4].addPoint();
			playerTurn = (playerTurn + table.getHighestCard()) % 4;
			table.clearTable();
		}
		
		for (int i = 0; i < 4; i++){
			System.out.println("Player " + (i + 1) + ": " + players[i].getPoints() + " points");
		}
		if (players[0].getPoints() + players[2].getPoints() == players[1].getPoints() + players[3].getPoints()){
			System.out.println("Tie!");
		} else if (players[0].getPoints() + players[2].getPoints() > players[1].getPoints() + players[3].getPoints()){
			System.out.println("Team 1 wins!");
		} else{
			System.out.println("Team 2 wins!");
		}
	}
	
	@Override
	public void start(Stage primaryStage){
		BorderPane pane = new BorderPane();
		
		pane.setBottom(new PlayerHand());
		pane.setLeft(new AIHand(90));
		pane.setTop(new AIHand(180));
		pane.setRight(new AIHand(-90));
		pane.setCenter(new Pane());
		String s = "src/table_shadow.png";
		pane.setStyle("-fx-background-image: url('file:src/table_shadow.png')");
		
		Scene scene = new Scene(pane, 1280, 800);
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Contract Bridge");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}

class PlayerHand extends HBox{
	public PlayerHand(){
		super(5);
		setAlignment(Pos.CENTER);
		
		Button card1 = new Button();
		card1.setGraphic(new ImageView(new Image("file:src/playing_cards_images/Playing_card_spade_A.jpg", 100, 175, true, false)));
		card1.setStyle("-fx-background-color: transparent");
		
		//ImageView imageView = new ImageView(new Image("file:src/playing_cards_images/Playing_card_spade_A.jpg", 100, 175, true, false));
		ImageView imageView2 = new ImageView(new Image("file:src/playing_cards_images/Playing_card_heart_A.jpg", 100, 175, true, false));
		ImageView imageView3 = new ImageView(new Image("file:src/playing_cards_images/Playing_card_club_J.jpg", 100, 175, true, false));
		
		getChildren().add(card1);
		getChildren().add(imageView2);
		getChildren().add(imageView3);		
	}
}

class AIHand extends HBox{
	public AIHand(int n){
		super(-50);
		setRotate(n);
		setAlignment(Pos.CENTER);
		
		for (int i = 0; i < 5; i++){
			getChildren().add(new ImageView(new Image("file:src/playing_cards_images/Playing_card_back.png", 100, 175, true, false)));
		}
	}
}