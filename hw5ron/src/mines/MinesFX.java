package mines;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MinesFX extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		MinesController controller;
		GridPane grid = new GridPane();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Game.fxml"));
			grid = loader.load();
			controller = loader.getController();

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		controller.setNewGame();
		Scene scene = new Scene(grid);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("The Amazing Mine Sweeper");
		primaryStage.show();
	}

}
