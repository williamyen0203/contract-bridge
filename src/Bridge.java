import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Bridge extends Application{
	public static boolean gameFinish = false;
	public static int playerTurn = 0;
	public static int leader = 0;
	public static int playerHighest = 0;
	public static int leftHighest = 0;
	public static int topHighest = 0;
	public static int rightHighest = 0;
	public static int startingSuit = -1;
	public static boolean hasValid = false;
	public static Deck deck = new Deck();
	public static Table table = new Table();
	public static Player[] players = {new Player(), new Player(), new Player(), new Player()};
	
	public static void main(String[] args){
		deck.deal(players[0], players[1], players[2], players[3]);
		players[0].cloneHand(players[0].getHand());
		players[1].cloneHand(players[1].getHand());
		players[2].cloneHand(players[2].getHand());
		players[3].cloneHand(players[3].getHand());
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage){
		Text player1Score = new Text(580, 625, "Your Score: 0");
		player1Score.setFont(Font.font("System", FontWeight.BOLD, 20));
		player1Score.setFill(Color.WHITE);
		player1Score.setStroke(Color.BLACK);
		Text player2Score = new Text(175, 410, "Player 2 Score: 0");
		player2Score.setFont(Font.font("System", FontWeight.BOLD, 20));
		player2Score.setFill(Color.RED);
		player2Score.setStroke(Color.BLACK);
		Text player3Score = new Text(570, 200, "Player 3 Score: 0");
		player3Score.setFont(Font.font("System", FontWeight.BOLD, 20));
		player3Score.setFill(Color.WHITE);
		player3Score.setStroke(Color.BLACK);
		Text player4Score = new Text(950, 410, "Player 4 Score: 0");
		player4Score.setFont(Font.font("System", FontWeight.BOLD, 20));
		player4Score.setFill(Color.RED);
		player4Score.setStroke(Color.BLACK);
		Text cardCheck = new Text(525, 595, "");
		cardCheck.setFont(Font.font("System", FontWeight.BOLD, 18));
		cardCheck.setFill(Color.YELLOW);
		cardCheck.setStroke(Color.BLACK);
		
		BorderPane pane = new BorderPane();
		pane.getChildren().add(player1Score);
		pane.getChildren().add(player2Score);
		pane.getChildren().add(player3Score);
		pane.getChildren().add(player4Score);
		pane.getChildren().add(cardCheck);
		
		BorderPane tablePane = new BorderPane();
		tablePane.setPadding(new Insets(12.5,255,12.5,255));
		HBox playerHBox = new HBox();
		playerHBox.setAlignment(Pos.CENTER);
		playerHBox.setMinHeight(125);
		playerHBox.setMaxHeight(125);
		HBox leftHBox = new HBox();
		leftHBox.setRotate(90);
		leftHBox.setAlignment(Pos.CENTER);
		leftHBox.setMinWidth(125);
		leftHBox.setMaxWidth(125);
		HBox topHBox = new HBox();
		topHBox.setRotate(180);
		topHBox.setAlignment(Pos.CENTER);
		topHBox.setMaxHeight(125);
		topHBox.setMinHeight(125);
		HBox rightHBox = new HBox();
		rightHBox.setRotate(270);
		rightHBox.setAlignment(Pos.CENTER);
		rightHBox.setMinWidth(125);
		rightHBox.setMaxWidth(125);
		VBox buttonPane = new VBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setMinWidth(125);
		buttonPane.setMinHeight(100);
		
		tablePane.setCenter(buttonPane);
		tablePane.setBottom(playerHBox);
		tablePane.setLeft(leftHBox);
		tablePane.setTop(topHBox);
		tablePane.setRight(rightHBox);
		
		HBox aiHandLeft = new HBox(-75);
		aiHandLeft.setMinWidth(200);
		aiHandLeft.setMaxWidth(200);
		aiHandLeft.setRotate(90);
		aiHandLeft.setAlignment(Pos.CENTER);
		for (int i = 0; i < 13; i++){
			ImageView imageView = new ImageView(new Image("file:src/playing_cards_images/Playing_card_back.png", 100, 125, true, false));
			aiHandLeft.getChildren().add(imageView);
		}
		
		HBox aiHandTop = new HBox(-75);
		aiHandTop.setMinHeight(200);
		aiHandTop.setMinHeight(200);
		aiHandTop.setRotate(180);
		aiHandTop.setAlignment(Pos.CENTER);
		for (int i = 0; i < 13; i++){
			ImageView imageView = new ImageView(new Image("file:src/playing_cards_images/Playing_card_back.png", 100, 125, true, false));
			aiHandTop.getChildren().add(imageView);
		}
		
		HBox aiHandRight = new HBox(-75);
		aiHandRight.setMinWidth(200);
		aiHandRight.setMaxWidth(200);
		aiHandRight.setRotate(270);
		aiHandRight.setAlignment(Pos.CENTER);
		for (int i = 0; i < 13; i++){
			ImageView imageView = new ImageView(new Image("file:src/playing_cards_images/Playing_card_back.png", 100, 125, true, false));
			aiHandRight.getChildren().add(imageView);
		}
		
		HBox playerHand = new HBox(-50);
		playerHand.setMinHeight(200);
		playerHand.setMaxHeight(200);
		playerHand.setAlignment(Pos.CENTER);
		for (int i = 0; i < 13; i++){
			String cardInHand = "file:src/playing_cards_images/Playing_card_" + players[0].getCardSuit(i) + "_" + players[0].getCardRank(i) + ".png";
			Button btn = new Button();
			btn.setId("" + i);
			btn.setStyle("-fx-background-color: transparent");
			btn.setOnAction(e -> {
				if ((playerTurn % 4 == 0) && (playerTurn != leader + 4)){
					for (int k = 0; k < players[0].getCardsInHand(); k++){
						if (players[0].getCard(k).getSuit() == startingSuit){
							hasValid = true;
						}
					}
					if (!hasValid || ((players[0].getCloneSuit(Integer.parseInt(btn.getId()))) == startingSuit) || (leader == 0)){
					((HBox) btn.getParent()).getChildren().remove(btn);
					for (int j = 0; j < players[0].getCardsInHand(); j++){
						if (players[0].getCloneRank(Integer.parseInt(btn.getId())) == players[0].getRank(j) && players[0].getCloneSuit(Integer.parseInt(btn.getId())) == players[0].getSuit(j)){
							players[0].removeCard(j);
						}
					} // Move from player hand to table ArrayList
					table.putCard(players[0], Integer.parseInt(btn.getId()));
					cardCheck.setText("");
					playerHBox.getChildren().add(btn);
					playerTurn++;
					} else{
						cardCheck.setText("Choose a card of the same suit");
					}
					if (leader == 0){
						startingSuit = players[0].getCloneSuit(Integer.parseInt(btn.getId()));
					}
				}
			});
			btn.setGraphic(new ImageView(new Image(cardInHand, 100, 125, true, false)));
			playerHand.getChildren().add(btn);
		}
		
		Button nextButton = new Button("Next");
		nextButton.setOnAction(t -> {
			int index, rank, suit;
			String cardInHand;
			if (playerTurn != leader + 4 && !gameFinish){
				switch (playerTurn % 4){
					case 0:
						break;
					case 1:
						aiHandLeft.getChildren().remove(0);
						if (leader == 1){
							index = players[1].getRandomCard();
							startingSuit = players[1].getSuit(index);
						} else{
							index = players[1].getHighestCardOfSuit(startingSuit);
						}
						rank = players[1].getRank(index);
						suit = players[1].getSuit(index);
						cardInHand = "file:src/playing_cards_images/Playing_card_" + players[1].getCardSuit(index) + "_" + players[1].getCardRank(index) + ".png";
						leftHBox.getChildren().add(new ImageView(new Image(cardInHand, 100, 125, true, false)));
						table.placeCard(players[1], index);
						playerTurn++;
						break;
					case 2:
						aiHandTop.getChildren().remove(0);
						if (leader == 2){
							index = players[2].getRandomCard();
							startingSuit = players[2].getSuit(index);
						} else{
							index = players[2].getHighestCardOfSuit(startingSuit);
						}
						rank = players[2].getRank(index);
						suit = players[2].getSuit(index);
						cardInHand = "file:src/playing_cards_images/Playing_card_" + players[2].getCardSuit(index) + "_" + players[2].getCardRank(index) + ".png";
						topHBox.getChildren().add(new ImageView(new Image(cardInHand, 100, 125, true, false)));
						table.placeCard(players[2], index);
						playerTurn++;
						break;
					case 3:
						aiHandRight.getChildren().remove(0);
						if (leader == 3){
							index = players[3].getRandomCard();
							startingSuit = players[3].getSuit(index);
						} else{
							index = players[3].getHighestCardOfSuit(startingSuit);
						}
						rank = players[3].getRank(index);
						suit = players[3].getSuit(index);
						cardInHand = "file:src/playing_cards_images/Playing_card_" + players[3].getCardSuit(index) + "_" + players[3].getCardRank(index) + ".png";
						rightHBox.getChildren().add(new ImageView(new Image(cardInHand, 100, 125, true, false)));
						table.placeCard(players[3], index);
						playerTurn++;
						break;
					default:
						break;
				}
			} else{
				leader = (leader + table.getHighestCard()) % 4;
				playerTurn = leader;
				players[leader].addPoint();
				player1Score.setText("Your Score: " + players[0].getPoints());
				player2Score.setText("Player 2 Score: " + players[1].getPoints());
				player3Score.setText("Player 3 Score: " + players[2].getPoints());
				player4Score.setText("Player 4 Score: " + players[3].getPoints());
				table.clearTable();
				playerHBox.getChildren().remove(0);
				leftHBox.getChildren().remove(0);
				topHBox.getChildren().remove(0);
				rightHBox.getChildren().remove(0);
				if (players[0].getCardsInHand() + players[1].getCardsInHand() + players[2].getCardsInHand() + players[3].getCardsInHand() == 0){
					gameFinish = true;
					buttonPane.getChildren().remove(nextButton);
					pane.getChildren().remove(player1Score);
					pane.getChildren().remove(player2Score);
					pane.getChildren().remove(player3Score);
					pane.getChildren().remove(player4Score);
					endGameScreen(pane);
				} else{
					nextButton.fire();
				}
			}
		});
		
		buttonPane.getChildren().add(nextButton);
		
		pane.setBottom(playerHand);
		pane.setLeft(aiHandLeft);
		pane.setTop(aiHandTop);
		pane.setRight(aiHandRight);
		pane.setCenter(tablePane);
		pane.setStyle("-fx-background-image: url('file:src/table.png')");
		
		Scene scene = new Scene(pane, 1280, 800);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Contract Bridge");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void endGameScreen(BorderPane pane){
		Text scoreBoard1 = new Text(500, 300, "Team 1 Score: " + (players[0].getPoints() + players[2].getPoints()));
		scoreBoard1.setFont(Font.font("System", FontWeight.BOLD, 40));
		scoreBoard1.setFill(Color.WHITE);
		scoreBoard1.setStroke(Color.BLACK);
		Text scoreBoard2 = new Text(500, 400, "Team 2 Score: " + (players[1].getPoints() + players[3].getPoints()));
		scoreBoard2.setFont(Font.font("System", FontWeight.BOLD, 40));
		scoreBoard2.setFill(Color.RED);
		scoreBoard2.setStroke(Color.BLACK);
		Text scoreBoard3;
		if ((players[0].getPoints() + players[2].getPoints()) > players[1].getPoints() + players[3].getPoints()){
			scoreBoard3 = new Text(500, 500, "You win!");
			scoreBoard3.setFill(Color.WHITE);
		} else if ((players[0].getPoints() + (players[2].getPoints()) < players[1].getPoints() + players[3].getPoints())){
			scoreBoard3 = new Text(500, 500, "You lose!");
			scoreBoard3.setFill(Color.RED);
		} else{
			scoreBoard3 = new Text(500, 500, "Tie!");
			scoreBoard3.setFill(Color.WHITE);
		}
		scoreBoard3.setFont(Font.font("System", FontWeight.BOLD, 80));
		scoreBoard3.setStroke(Color.BLACK);
		pane.getChildren().addAll(scoreBoard1, scoreBoard2, scoreBoard3);
	}
}