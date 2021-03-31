package gui;

import domein.DomeinController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartGui extends Application {

	public static DomeinController dc = new DomeinController();
	
    @Override
    public void start(Stage stage) throws Exception {
    	AnchorPane root = (AnchorPane) FXMLLoader.load(StartGui.class.getResource("/gui/LoginScherm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}