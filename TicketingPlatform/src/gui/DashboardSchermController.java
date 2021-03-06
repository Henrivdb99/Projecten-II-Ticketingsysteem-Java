package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.controllers.AangemeldeGebruikerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DashboardSchermController extends BorderPane implements Initializable  {
	@FXML
	private Button btnAfmelden;
	@FXML
	private Label lblWelkom;
	@FXML
	private Button btnTickets;
	@FXML
	private Button btnKnowledgebase;
	@FXML
	private Button btnStatistieken;
	@FXML
	private Button btnWerknemersBeheren;
	@FXML
	private Button btnKlanten;

	private LoginSchermController parent;
	
	private AangemeldeGebruikerController ac;

	public DashboardSchermController(LoginSchermController loginSchermController, AangemeldeGebruikerController ac) {
		this.parent= loginSchermController;
		this.ac = ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@FXML
	public void btnAfmeldenOnAction(ActionEvent event) {
		parent.lg.meldAf();
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	@FXML
	public void btnTicketsOnAction(ActionEvent event) {
TicketsBeherenSchermController tbsc = new TicketsBeherenSchermController(this, this.ac);
		
		Scene scene = new Scene(tbsc);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();	}
	// Event Listener on Button[#btnKnowledgebase].onAction
	@FXML
	public void btnKnowledgebaseOnAction(ActionEvent event) {
		KnowledgebaseBeherenSchermController kbsc = new KnowledgebaseBeherenSchermController(this, ac);
		Scene scene = new Scene(kbsc);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();		}
	// Event Listener on Button[#btnStatistieken].onAction
	@FXML
	public void btnStatistiekenOnAction(ActionEvent event) {
		StatistiekenSchermController ssc = new StatistiekenSchermController(this, ac);
		Scene scene = new Scene(ssc);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();		
	}
	// Event Listener on Button[#btnWerknemersBeheren].onAction
	@FXML
	public void btnWerknemersBeherenOnAction(ActionEvent event) {
WerknemersBeherenSchermController wbsc = new WerknemersBeherenSchermController(this, this.ac);
		
		Scene scene = new Scene(wbsc);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();		}
	// Event Listener on Button[#btnKlanten].onAction
	@FXML
	public void btnKlantenOnAction(ActionEvent event) {
		KlantenBeherenSchermController kbsc = new KlantenBeherenSchermController(this, this.ac);
		
		Scene scene = new Scene(kbsc);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();		}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		welcomeMessage();
		managePermissions();		
	}
	private void welcomeMessage()  {
		this.lblWelkom.setText(String.format("Welkom %s %s!", ac.geefAangemeldeGebruikerType(), parent.lg.geefNaamEnVoornaamAangemeldeGebruiker()));
	}
	
	private void managePermissions() {
		switch(ac.geefAangemeldeGebruikerType()) {
			case Administrator -> {
				btnKlanten.setVisible(true);
				btnWerknemersBeheren.setVisible(true);
			}
			case Technieker -> {
				btnTickets.setVisible(true);
				btnStatistieken.setVisible(true);
				btnKnowledgebase.setVisible(true);		
						}
			case SupportManager -> {
				btnTickets.setVisible(true);
				btnStatistieken.setVisible(true);
				btnKnowledgebase.setVisible(true);
			}
			default -> {
				
			}
		}
		
	}
}
