import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TicTacToe extends Application {
	
	private char currentPlayer = 'X';
	private Cell[][] cell = new Cell[3][3];
	private Label statusMsg = new Label("It is " + currentPlayer + "'s turn");
	private Button btnNewGame = new Button("Restart Game");
	private Image ximg = new Image("file:src/x.jpg");
	private Image oimg = new Image("file:src/o.jpg");

	void cleanup() {
		currentPlayer = 'X';
		statusMsg = new Label("It is " + currentPlayer + "'s turn");
	}

	void startGame(Stage stage) {
		GridPane pane = new GridPane();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cell[i][j] = new Cell();
				pane.add(cell[i][j], j, i);
			}
		}
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(statusMsg);
		
		btnNewGame.setOnAction(e -> { restart(stage); });
		btnNewGame.setMaxWidth(450);
		btnNewGame.setVisible(false);
		borderPane.setTop(btnNewGame);
		BorderPane.setAlignment(btnNewGame, Pos.CENTER);
		BorderPane.setAlignment(statusMsg, Pos.CENTER);
	    
		Scene scene = new Scene(borderPane, 450, 300);
		stage.setTitle("JavaFX Tic Tac Toe Game");
		stage.setScene(scene);
		stage.show();
	}

	void restart(Stage stage) {
		cleanup();
	    startGame(stage);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		startGame(primaryStage);
	}
	
	public boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (cell[i][j].getPlayer() == ' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean hasWon(char player) {
		for (int i = 0; i < 3; i++) {
			if (cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player && cell[i][2].getPlayer() == player) {
				return true;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (cell[0][i].getPlayer() == player && cell[1][i].getPlayer() == player && cell[2][i].getPlayer() == player) {
				return true;
			}
		}
		if (cell[0][0].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer() == player) {
			return true;
		}
		if (cell[0][2].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][0].getPlayer() == player) {
			return true;
		}
		
		return false;
	}
	
	private class Cell extends Pane {
		private char player = ' ';
		public Cell() {
			setStyle("-fx-border-color : black");
			this.setPrefSize(300, 300);
			this.setOnMouseClicked(e -> handleClick());
		}
		private void handleClick() {
			if (player == ' ' && currentPlayer != ' ') {
				setPlayer(currentPlayer);
				if (hasWon(currentPlayer)) {
					statusMsg.setText("Congratulations, " + currentPlayer + " has won!");
					currentPlayer= ' ';
					btnNewGame.setVisible(true);
				} else if (isBoardFull()) {
					statusMsg.setText("Draw");
					currentPlayer= ' ';
					btnNewGame.setVisible(true);
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
					statusMsg.setText("It is " + currentPlayer + "'s turn");
				}
			}
		}
		public char getPlayer() {
			return player;
		}
		public void clearPlayer() {
			this.player = ' ';
		}
		public void setPlayer(char player) {
			this.player = player;
			if (this.player == 'X') {
				ImageView imgV = new ImageView(ximg);
				imgV.fitWidthProperty().bind(this.widthProperty());
				imgV.fitHeightProperty().bind(this.heightProperty());
				getChildren().add(imgV);

			} else if (this.player == 'O') {
				ImageView imgV = new ImageView(oimg);
				imgV.fitWidthProperty().bind(this.widthProperty());
				imgV.fitHeightProperty().bind(this.heightProperty());
				getChildren().add(imgV);
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

