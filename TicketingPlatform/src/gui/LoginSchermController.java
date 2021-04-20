package gui;

import java.io.IOException;

import controllers.GebruikerController;
import domein.Technieker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginSchermController extends AnchorPane {
	@FXML
	private Label lblLogIn;
	@FXML
	private Label lblGebruikersnaam;
	@FXML
	private Label lblWachtwoord;
	@FXML
	private PasswordField txfWachtwoord;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private Button btnInloggen;
	@FXML
	private Label lblFout;

	private GebruikerController gc;

	public LoginSchermController(GebruikerController gebruikerController) {
		super();
		this.gc = gebruikerController;				
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			setPlaceholders();

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public void btnInloggenOnAction(ActionEvent event) throws IOException {
		String username = txfGebruikersnaam.getText();
		String wachtwoord = txfWachtwoord.getText();

		try {
			gc.meldAan(username, wachtwoord);
			DashboardSchermGebruikerController child;
			
			child = switch(gc.getGebruiker().getClass().getSimpleName()) {
				case "Technieker" ->  new DashboardSchermTechniekerController(this, gc);
				case "Administrator" ->  new DashboardSchermAdministratorController(this, gc);
				case "SupportManager" ->  new DashboardSchermSupportManagerController(this, gc);
				default -> throw new IllegalArgumentException("Unexpected value: " + gc.getGebruiker().getClass());
			};
			Scene scene = new Scene(child);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			

		} catch (IllegalArgumentException e) {		
			System.out.println(e.getMessage());
			this.lblFout.setText(e.getMessage());
		}

	}
	
	private void setPlaceholders() {
		this.txfGebruikersnaam.setText("supportmanager@gmail.com");
		this.txfWachtwoord.setText("wachtwoord2");
	}
}
