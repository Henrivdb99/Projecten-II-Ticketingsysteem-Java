package gui;

import java.io.IOException;

import controllers.GebruikerController;
import domein.Gebruiker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class DashboardSchermGebruikerController extends AnchorPane {
	@FXML
	private Button btnAfmelden;
	@FXML
	private Label lblWelkom;
	
	protected LoginSchermController parent;
	protected GebruikerController gc;
	
	public DashboardSchermGebruikerController(LoginSchermController loginSchermController, GebruikerController gebruikerController) {
		this.parent= loginSchermController;
		this.gc = gebruikerController;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardSchermGebruiker.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			welcomeMessage();
			
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	// Event Listener on Button[#btnAfmelden].onAction
	@FXML
	public void btnAfmeldenOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}
	// Event Listener on Button[#btnOpenstaandeTickets].onAction
	@FXML
	public void btnOpenstaandeTicketsOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnAfgehandeldeTickets].onAction
	@FXML
	public void btnAfgehandeldeTicketsOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnStatistieken].onAction
	@FXML
	public void btnStatistiekenOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnKnowledgeBaseBeheren].onAction
	@FXML
	public void btnKnowledgeBaseBeherenOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnNieuwTicketAanmaken].onAction
	@FXML
	public void btnNieuwTicketAanmakenOnAction(ActionEvent event) {
		// TODO Autogenerated
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
	
	private void welcomeMessage()  {
		Gebruiker g = gc.getGebruiker();
		this.lblWelkom.setText(String.format("Welkom %s %s!", g.getClass().getSimpleName(), g.getEmailAdres()));
	}
}
