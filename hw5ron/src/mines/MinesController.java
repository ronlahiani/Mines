package mines;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MinesController {
	private Mines bord;
	private Location[][] matrix;
	int width, height, mines;
	@FXML
	private Button ResetButton;

	@FXML
	private Label heightLabel;

	@FXML
	private TextArea heightSize;

	@FXML
	private Label minesLabel;

	@FXML
	private TextArea minesSize;

	@FXML
	private StackPane stackPane;

	@FXML
	private Label widthLabel;

	@FXML
	private TextArea widthSize;

	@FXML
	void resetAll(ActionEvent event) {
		stackPane.getChildren().clear();
		setNewGame();
	}

	public void setNewGame() {
		// setting the sizes
		getHeight();
		getWidth();
		getMines();
		// creating new bord for the logic side.
		bord = new Mines(height, width, mines);
		// the grid will be our play field.
		GridPane gameGrid;
		gameGrid = new GridPane();
		matrix = new Location[getHeight()][getWidth()];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				//settin each button and style
				matrix[i][j] = new Location(i, j);
				matrix[i][j].setFont(Font.font("Calibri", FontWeight.BOLD, null,25));
				matrix[i][j].setText(".");
				matrix[i][j].setMinWidth(50);
				matrix[i][j].setMinHeight(50);
				matrix[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						if (event.getButton() == MouseButton.PRIMARY) {
							int x = ((Location) event.getSource()).getX(); // getting x,y from user
							int y = ((Location) event.getSource()).getY();
							bord.setShowAll(true);
							String check = bord.get(x, y); // checking if its mine.
							bord.open(x, y);
							bord.setShowAll(false);
							if (check == "X") {
								bord.setShowAll(true); // show all the cells/
								stackPane.getChildren().addAll(new Label("OOPS! YOU LOST!!")); // if we steped on mien.
								gameGrid.setDisable(true);
							} else if (check != "X" && check != "F" && bord.isDone()) // check if we are won
							{
								stackPane.getChildren().addAll(new Label("YAYY!! YOU WON!!")); // if we are won.
								bord.setShowAll(true);
								gameGrid.setDisable(true);
							}
							// updating all matrix
							for (int i = 0; i < getHeight(); i++) {
								for (int j = 0; j < getWidth(); j++) {
									matrix[i][j].setText(bord.get(i, j));
								}
							}
						}
						if (event.getButton() == MouseButton.SECONDARY) { // put flag when right mouse clicked
							int x = ((Location) event.getSource()).getX();
							int y = ((Location) event.getSource()).getY();
							bord.toggleFlag(x, y);
							for (int i = 0; i < getHeight(); i++) {
								for (int j = 0; j < getWidth(); j++) {
										matrix[i][j].setText(bord.get(i, j));
								}
							}
						}

					}

				});
				// setting the grid
				gameGrid.add(matrix[i][j], j, i);
			}
		}
		// setting the stackpane
		stackPane.getChildren().addAll(gameGrid); // adding the new grid to stackpane.
		gameGrid.setAlignment(Pos.CENTER); // put the grid in the center.

	}

	public int getHeight() { // getting the height from user
		height = Integer.valueOf(heightSize.getText());
		return height;
	}

	public int getWidth() { // getting the width from user.
		width = Integer.valueOf(widthSize.getText());
		return width;
	}

	public int getMines() { // getting the number of mined from user
		mines = Integer.valueOf(minesSize.getText());
		return mines;
	}
}