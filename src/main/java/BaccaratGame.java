import java.util.HashMap;
import java.util.Iterator;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import java.lang.String;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

public class BaccaratGame extends Application {
	//members
	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	BaccaratGameLogic gameLogic;
	double currentBet;
	double totalWinnings;
	String betWinner;				//holds "Banker" if user bets on dealer or "Player" otherwise

	String heart = "\u2764";
	String diamond = "\u2662";
	String spade = "\u2660";
	String club = "\u2663";

	String roundWinner;
	TextField amountBet;
	VBox paneLeft, cards, cards2, paneCenter, holder;
	Button start, betPlayer, betBanker, betDraw, pc1, pc2, pc3, bc1, bc2, bc3;
	HashMap<String, Scene> sceneMap;
	Label lab, space, space2, bLab, pLab, extra;
	MenuBar menu;
	PauseTransition pause = new PauseTransition(Duration.seconds(3));
	GenericStack<String> myQueue;
	ListView<String> displayQueueItems;
	//use this to add and delete from ListView
	ObservableList<String> storeQueueItemsInListView;

	//method will determine if the user won or lost their bet and return the amount won or
	//lost based on the value in currentBet
	public double evaluateWinnings(){
		//if user bet on the winner
		if(betWinner.equals(gameLogic.whoWon(playerHand, bankerHand))){
			//return positive value (user wins money)
			return currentBet;
		}
		//else return negative value (user loses money)
		return currentBet * -1;
	}

	//method to create our first scene with controls
	public Scene createControlScene() {
		BorderPane main = new BorderPane();
		main.setPadding(new Insets(10));
		paneCenter = new VBox(20,lab,amountBet,betBanker,betPlayer,betDraw,start,displayQueueItems);
		paneCenter.setAlignment(Pos.CENTER);
		paneCenter.setStyle("-fx-background-color: aliceblue;");
		holder = new VBox(50, extra, space, space2);
		holder.setAlignment(Pos.CENTER);
		holder.setStyle("-fx-background-color: green;");
		main.setTop(menu);
		main.setLeft(paneCenter);
		main.setCenter(holder);
		return new Scene(main,750,750);
	}

	public Scene createCardScene() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		paneLeft = new VBox(20,lab,amountBet,betBanker,betPlayer,betDraw,start,displayQueueItems);
		paneLeft.setAlignment(Pos.CENTER);
		paneLeft.setStyle("-fx-background-color: aliceblue;");
		cards = new VBox(30, extra, pLab, pc1, pc2, bLab, bc1, bc2);
		cards.setStyle("-fx-background-color: green;");
		cards.setAlignment(Pos.CENTER);
		pane.setTop(menu);
		pane.setCenter(cards);
		pane.setLeft(paneLeft);
		return new Scene(pane,750,750);
	}

	public Scene createCard2Scene() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		paneLeft = new VBox(20,lab,amountBet,betBanker,betPlayer,betDraw,start,displayQueueItems);
		paneLeft.setAlignment(Pos.CENTER);
		paneLeft.setStyle("-fx-background-color: aliceblue;");
		pane.setTop(menu);
		pane.setCenter(cards2);
		pane.setLeft(paneLeft);
		return new Scene(pane,750,750);
	}

	public String getCardText(Card c){
		if(c.value == 1)
			return c.symbol + " Ace of " + c.suite + " " + c.symbol;
		else if(c.value == 11)
			return c.symbol + " Jack of " + c.suite + " " + c.symbol;
		else if(c.value == 12)
			return c.symbol + " Queen of " + c.suite + " " + c.symbol;
		else if(c.value == 13)
			return c.symbol + " King of " + c.suite + " " + c.symbol;
		else
			return c.symbol + " " + c.value + " of " + c.suite + " " + c.symbol;
	}

	public void createButtons(){
		pc1.setText(getCardText(playerHand.get(0)));
		pc2.setText(getCardText(playerHand.get(1)));
		bc1.setText(getCardText(bankerHand.get(0)));
		bc2.setText(getCardText(bankerHand.get(1)));
		if ((playerHand.get(0).suite.equals("hearts")) || (playerHand.get(0).suite.equals("diamonds")))
			 { pc1.setStyle("-fx-border-color: red; -fx-border-width: 5px;-fx-font-size: 2em;");}
		else {pc1.setStyle("-fx-border-color: black; -fx-border-width: 5px;-fx-font-size: 2em;");}
		if ((playerHand.get(1).suite.equals("hearts")) || (playerHand.get(1).suite.equals("diamonds")))
			{ pc2.setStyle("-fx-border-color: red; -fx-border-width: 5px;-fx-font-size: 2em;");}
		else{pc2.setStyle("-fx-border-color: black; -fx-border-width: 5px;-fx-font-size: 2em;");}
		if ((bankerHand.get(0).suite.equals("hearts")) || (bankerHand.get(0).suite.equals("diamonds")))
			{ bc1.setStyle("-fx-border-color: red; -fx-border-width: 5px;-fx-font-size: 2em;");}
		else {bc1.setStyle("-fx-border-color: black; -fx-border-width: 5px;-fx-font-size: 2em;");}
		if ((bankerHand.get(1).suite.equals("hearts")) || (bankerHand.get(1).suite.equals("diamonds")))
			{ bc2.setStyle("-fx-border-color: red; -fx-border-width: 5px;-fx-font-size: 2em;");}
		else {bc2.setStyle("-fx-border-color: black; -fx-border-width: 5px;-fx-font-size: 2em;");}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//creates a new menu
		menu = new MenuBar();
		Menu mOne = new Menu("Options");
		menu.getMenus().addAll(mOne);
		// create menu items
		MenuItem m1 = new MenuItem("Exit");
		MenuItem m2 = new MenuItem("Fresh Start");
		// add menu items to menu
		mOne.getItems().add(m1);
		mOne.getItems().add(m2);
		//add action to menu items
		m1.setOnAction(e -> {
			System.exit(0) ;
		});
		m2.setOnAction(e -> {
			totalWinnings = 0;
			currentBet = 0;
			theDealer.deck.clear();
			betDraw.setDisable(true);
			betBanker.setDisable(true);
			betPlayer.setDisable(true);
			start.setDisable(false);
			amountBet.setEditable(false);
			amountBet.setText("");
			extra.setText("Press START");
			totalWinnings = 0;
			lab.setText("\nTotal Winnings: $" + String.format("%.2f",totalWinnings));
			displayQueueItems.getItems().clear();
			int len = myQueue.getLength();
			for(int i=0; i<len; i++)
				myQueue.pop();
			primaryStage.setScene(createControlScene());
		});


		theDealer = new BaccaratDealer();
		gameLogic = new BaccaratGameLogic();
		playerHand = new ArrayList<Card>();
		bankerHand = new ArrayList<Card>();
		amountBet = new TextField();
		amountBet.setAlignment(Pos.BASELINE_LEFT);
		start = new Button("START");
		betPlayer = new Button("Player");
		betBanker = new Button("Banker");
		betDraw = new Button("Draw");
		Menu mOptions = new Menu("Options");
		Menu mExit = new Menu("Exit");
		Menu mStart = new Menu("Fresh Start");
		lab = new Label("\nTotal Winnings: $" + String.format("%.2f",totalWinnings));
		lab.setFont(new Font("Comic Sans MS", 16));
		lab.setStyle("-fx-font-weight: bold");
		extra = new Label("");
		extra.setFont(new Font("Comic Sans MS", 30));
		extra.setStyle("-fx-font-weight: bold");
		extra.setTextFill(Color.GHOSTWHITE);
		space = new Label("\n");
		space2 = new Label("\n");
		bLab = new Label("Banker's Hand");
		pLab = new Label("Player's Hand");
		bLab.setTextFill(Color.GHOSTWHITE);
		bLab.setFont(new Font("Comic Sans MS", 22));
		pLab.setTextFill(Color.GHOSTWHITE);
		pLab.setFont(new Font("Comic Sans MS", 22));
		extra.setText("Press START");
		amountBet.setEditable(false);

		//stores strings: from your project #1
		myQueue = new GenericStack<String>(" ");
		displayQueueItems = new ListView<String>();
		//initialize to an observable list
		storeQueueItemsInListView = FXCollections.observableArrayList();

		betDraw.setDisable(true);
		betBanker.setDisable(true);
		betPlayer.setDisable(true);

		//create buttons that will represent cards
		pc1 = new Button("null");
		pc2 = new Button("null");
		bc1 = new Button("null");
		bc2 = new Button("null");
		pc3 = new Button("null");
		bc3 = new Button("null");

		EventHandler<ActionEvent> veryEnd = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				start.setDisable(false);
				amountBet.setText("");
				extra.setText("        Press START\nto play another round");
				primaryStage.setScene(createControlScene());
				primaryStage.show();
			}
		};

		EventHandler<ActionEvent> endRound = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				extra.setText("");
				String holder;
				totalWinnings += evaluateWinnings();
				lab.setText("\nTotal Winnings: $" + String.format("%.2f",totalWinnings));
				holder = "Player Total: " + gameLogic.handTotal(playerHand) + "  Banker Total: " + gameLogic.handTotal(bankerHand);
				if(roundWinner.equals("Draw")) {
					extra.setText("It's a Draw!");
					holder += "\nIt's a Draw\n";
				}
				else {
					extra.setText(roundWinner + " wins!");
					holder += "\n" + roundWinner + " wins\n";
				}
				if(roundWinner.equals(betWinner))
					holder += "Congrats, you bet " + betWinner + "! You win!\n";
				else
					holder += "Sorry, you bet " + betWinner + "! You lost your bet!\n\n";

				myQueue.push(holder);
				displayQueueItems.getItems().removeAll(storeQueueItemsInListView);storeQueueItemsInListView.clear();
				Iterator<String> i = myQueue.createIterator();
				while(i.hasNext()) {
					storeQueueItemsInListView.add(i.next());
				}
				displayQueueItems.setItems(storeQueueItemsInListView);
				pause.setOnFinished(veryEnd);
				pause.play(); //calls setOnFinished
			}
		};

		EventHandler<ActionEvent> betPlaced = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//check for natural win
				roundWinner = gameLogic.naturalWin(playerHand, bankerHand);

				if(roundWinner.equals("None")) {
					cards2 = new VBox(30, extra, pLab, pc1, pc2);
					Card c = new Card("null", -1);
					//see if player should get third card. if so, deal one
					if (gameLogic.evaluatePlayerDraw(playerHand)) {
						c = theDealer.drawOne();
						playerHand.add(c);
						pc3.setText(getCardText(playerHand.get(2)));
						if ((playerHand.get(2).suite.equals("hearts")) || (playerHand.get(2).suite.equals("diamonds"))) {
							pc3.setStyle("-fx-border-color: red; -fx-border-width: 5px;-fx-font-size: 2em;");
						} else {
							pc3.setStyle("-fx-border-color: black; -fx-border-width: 5px;-fx-font-size: 2em;");
						}
						cards2.getChildren().add(pc3);
					}
					cards2.getChildren().add(bLab);
					cards2.getChildren().add(bc1);
					cards2.getChildren().add(bc2);
					//see if banker should get third card. if so, deal one
					if (gameLogic.evaluateBankerDraw(bankerHand, c)) {
						bankerHand.add(theDealer.drawOne());
						bc3.setText(getCardText(bankerHand.get(2)));
						if ((bankerHand.get(2).suite.equals("hearts")) || (bankerHand.get(2).suite.equals("diamonds"))) {
							bc3.setStyle("-fx-border-color: red; -fx-border-width: 5px;-fx-font-size: 2em;");
						} else {
							bc3.setStyle("-fx-border-color: black; -fx-border-width: 5px;-fx-font-size: 2em;");
						}
						cards2.getChildren().add(bc3);
					}
					roundWinner = gameLogic.whoWon(playerHand, bankerHand);
					cards2.setStyle("-fx-background-color: green;");
					cards2.setAlignment(Pos.CENTER);
					primaryStage.setScene(createCard2Scene());
				}
				else
					extra.setText("NATURAL WIN!");
				pause.setOnFinished(endRound);
				pause.play(); //calls setOnFinished
			}
		};

		EventHandler<ActionEvent> startHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				extra.setText("Please enter an amount to bet\n   and choose who to bet on");
				amountBet.setText("");
				start.setDisable(true);
				betDraw.setDisable(false);
				betBanker.setDisable(false);
				betPlayer.setDisable(false);
				amountBet.setEditable(true);
				roundWinner = "None";
				theDealer.shuffleDeck();
				playerHand.clear();
				bankerHand.clear();
				playerHand = theDealer.dealHand();
				bankerHand = theDealer.dealHand();
				createButtons();
			}
		};

		EventHandler<ActionEvent> betPlayerHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				extra.setText("");
				String checkDoub = amountBet.getText();
				char[] array=new  char[checkDoub.length()];
				for (int i = 0; i < checkDoub.length(); i ++) {
					if ((!Character.isDigit(checkDoub.charAt(i))) && ((checkDoub.charAt(i))) != ('.') )
					{extra.setText("Please enter a valid amount");
					 amountBet.setText("");}
				}
				currentBet = Double.parseDouble(amountBet.getText());
				betDraw.setDisable(true);
				betBanker.setDisable(true);
				betPlayer.setDisable(true);
				betWinner = "Player";
				amountBet.setText("$" + String.format("%.2f",currentBet) + " on Player");
				amountBet.setEditable(false);
				extra.setText("");
				primaryStage.setScene(createCardScene());
				pause.setOnFinished(betPlaced);
				pause.play(); //calls setOnFinished
			}
		};

		EventHandler<ActionEvent> betBankerHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				extra.setText("");
				String checkDoub = amountBet.getText();
				char[] array=new  char[checkDoub.length()];
				for (int i = 0; i < checkDoub.length(); i ++) {
					if ((!Character.isDigit(checkDoub.charAt(i))) && ((checkDoub.charAt(i))) != ('.') )
					{extra.setText("Please enter a valid amount");
						amountBet.setText("");}
				}
				currentBet = Double.parseDouble(amountBet.getText());
				betDraw.setDisable(true);
				betBanker.setDisable(true);
				betPlayer.setDisable(true);
				betWinner = "Banker";
				amountBet.setText("$" + String.format("%.2f",currentBet) + " on Banker");
				amountBet.setEditable(false);
				extra.setText("");
				primaryStage.setScene(createCardScene());
				pause.setOnFinished(betPlaced);
				pause.play(); //calls setOnFinished
			}
		};

		EventHandler<ActionEvent> betDrawHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				extra.setText("");
				String checkDoub = amountBet.getText();
				char[] array=new  char[checkDoub.length()];
				for (int i = 0; i < checkDoub.length(); i ++) {
					if ((!Character.isDigit(checkDoub.charAt(i))) && ((checkDoub.charAt(i))) != ('.') )
					{extra.setText("Please enter a valid amount");
						amountBet.setText("");}
				}
				currentBet = Double.parseDouble(amountBet.getText());
				betDraw.setDisable(true);
				betBanker.setDisable(true);
				betPlayer.setDisable(true);
				betWinner = "Draw";
				amountBet.setText("$" + String.format("%.2f",currentBet) + " on Draw");
				amountBet.setEditable(false);
				extra.setText("");
				primaryStage.setScene(createCardScene());
				pause.setOnFinished(betPlaced);
				pause.play(); //calls setOnFinished
			}
		};

		primaryStage.setTitle("Let's Play Baccarat!!!");

		start.setOnAction(startHandler);
		betBanker.setOnAction(betBankerHandler);
		betPlayer.setOnAction(betPlayerHandler);
		betDraw.setOnAction(betDrawHandler);

		primaryStage.setScene(createControlScene());
		primaryStage.show();
	}
}

