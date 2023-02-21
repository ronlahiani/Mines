package simpleFx2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleFX2 extends Application {

	    public static void main(String[] args) {
	        launch(args);
	    }

	@Override
	public void start(Stage primaryStage) throws Exception {
    	VBox vbox;
    	try {
    	    FXMLLoader loader = new FXMLLoader();
    	    loader.setLocation(getClass().getResource("scenebuilder.fxml"));
    	    vbox = loader.load();
    	    primaryStage.setTitle("Voting Machine");
    	} catch (IOException e) {
    	    e.printStackTrace();
    	    return;
    	}
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
		

}
