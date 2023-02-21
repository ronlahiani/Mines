package simpleFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SimpleFx extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private Button makeButton(String msg) {
		Button btn = new Button(msg);
		return btn;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(makeVBox());
		stage.setTitle("Voting Machine");
		stage.setScene(scene);
		stage.show();
	}

	private int total = 0;

	private VBox makeVBox() {
		Button ofra = makeButton("Ofra Haza"); //create button for ofra
		Button yardena = makeButton("Yardena Arazi"); // create button for yardena
		Label label = new Label("0"); //setting the voting label to zero
		label.setFont(Font.font(null, FontWeight.BOLD, 15));
		VBox box = new VBox();
		class OfraIncreaser implements EventHandler<ActionEvent> { //while clicking ofra it will increase
			@Override
			public void handle(ActionEvent event) {

				total++;
				label.setText(total + "");

			}
		}
		class YardenaIncreaser implements EventHandler<ActionEvent> { //when clicking yardena it will decrease
			@Override
			public void handle(ActionEvent event) {
				total--;
				label.setText(total + "");
			}
		}
		label.setStyle("-fx-background-color:red");
		label.setAlignment(Pos.CENTER);
		label.setMaxWidth(Double.MAX_VALUE);
		label.setPrefHeight(40);
		ofra.setMaxWidth(Double.MAX_VALUE);
		yardena.setMaxWidth(Double.MAX_VALUE);
		ofra.setOnAction(new OfraIncreaser());//setting the button to action
		yardena.setOnAction(new YardenaIncreaser()); //setting the button to action
		GridPane root = new GridPane();
		root.add(ofra, 0, 0); //setting in the location
		root.add(yardena, 2, 0);
		root.setHgap(10);
		root.setPrefHeight(40);
		root.setAlignment(Pos.CENTER); //marking the grid in center.
		root.setPrefWidth(360);
		box.setPadding(new Insets(10));
		box.getChildren().addAll(root, label);
		box.setAlignment(Pos.CENTER);

		return box;
	}

}
