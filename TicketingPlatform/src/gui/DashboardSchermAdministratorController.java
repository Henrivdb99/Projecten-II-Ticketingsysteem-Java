package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class DashboardSchermAdministratorController extends AnchorPane{
	@FXML
	private Button btnAfmelden;
	@FXML
	private Button btnKlantenBeheren;
	@FXML
	private Button btnWerknemersBeheren;

	private LoginSchermController ls;
	
	public DashboardSchermAdministratorController(LoginSchermController loginSchermController) {
		this.ls= loginSchermController;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardSchermAdministrator.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	// Event Listener on Button[#btnAfmelden].onAction
	@FXML
	public void btnAfmeldenOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.ls.getScene());
	}
	// Event Listener on Button[#btnKlantenBeheren].onAction
	@FXML
	public void btnKlantenBeherenOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnWerknemersBeheren].onAction
	@FXML
	public void btnWerknemersBeherenOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
}
