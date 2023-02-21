package simpleFx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MyController {
	private int count=0;
    @FXML
    private Label label;

    @FXML
    private Button ofra;

    @FXML
    void ofraClick(ActionEvent event) { //while clicking ofra button voting will increase
    	count++;
    	label.setText(count+"");
    }

    @FXML
    void yardenaClick(ActionEvent event) { //while clicking yardena button voting will decrease
    	count--;
    	label.setText(count+"");
    }

}