package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.TypeGebruiker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WerknemersBeherenSchermController extends BorderPane implements Initializable {
	@FXML
	private Button btnWerknemerToevoegen;
	@FXML
	private Button btnWerknemerWijzigen;
	@FXML
	private Button btnTerug;
	@FXML
	private Button btnWerknemerDetails;
	@FXML
	private Button btnResetFilters;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private TextField txfNaamVoornaam;
	@FXML
	private TextField txfRol;
	@FXML
	private TextField txfStatus;
	@FXML
	private TableView<GebruikerGegevens> tblView;
	@FXML
	private TableColumn<GebruikerGegevens, String> colGebruikersnaam;
	@FXML
	private TableColumn<GebruikerGegevens, String> colNaam;
	@FXML
	private TableColumn<GebruikerGegevens, String> colVoornaam;
	@FXML
	private TableColumn<GebruikerGegevens, String> colRol;
	@FXML
	private TableColumn<GebruikerGegevens, String> colStatus;

	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;
	private GebruikerGegevens selectedUser;

	public WerknemersBeherenSchermController(DashboardSchermController dashboardSchermController,
			AangemeldeGebruikerController ac) {
		this.parent = dashboardSchermController;
		this.ac = ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WerknemersBeherenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			btnWerknemerDetails.setDisable(true);
			btnWerknemerWijzigen.setDisable(true);
			txfStatus.setPromptText("Schrijf volledig uit");
			ac.changeFilter(null, null);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@FXML
	public void btnWerknemerToevoegenOnAction(ActionEvent event) {
		WerknemerToevoegenSchermController wtsc = new WerknemerToevoegenSchermController(this, ac);
		this.setRight(wtsc);
	}

	@FXML
	public void btnWerknemerWijzigenOnAction(ActionEvent event) {
		this.selectedUser = tblView.getSelectionModel().getSelectedItem();
		WerknemerWijzigenSchermController wwsc = new WerknemerWijzigenSchermController(this, this.selectedUser,
				this.ac);
		this.setRight(wwsc);
	}

	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	// Event Listener on Button[#btnWerknemerDetails].onAction
	@FXML
	public void btnWerknemerDetailsOnAction(ActionEvent event) {
		// Observerrelatie tussen overzicht en detail, breng domein op de hoogte voor
		// wat je hebt geselecteerd. (domein huidige werknemer bijhouden)
		this.selectedUser = tblView.getSelectionModel().getSelectedItem();
		WerknemerDetailsSchermController werknemerDetailsSchermController = new WerknemerDetailsSchermController(this,
				this.selectedUser);

		this.setRight(werknemerDetailsSchermController);
	}

	// Event Listener on Button[#btnResetFilters].onAction
	@FXML
	public void btnResetFiltersOnAction(ActionEvent event) {
		// TODO Autogenerated
	}

	@FXML
	public void filterGebruikersnaam(KeyEvent event) {
		String newValue = txfGebruikersnaam.getText();
		ac.changeFilter(newValue, "Gebruikersnaam");
	}

	// Event Listener on TextField[#txfNaamVoornaam].onKeyReleased
	@FXML
	public void filterNaamVoornaam(KeyEvent event) {
		String newValue = txfNaamVoornaam.getText();
		ac.changeFilter(newValue, "NaamEnVoornaam");
	}

	// Event Listener on TextField[#txfRol].onKeyReleased
	@FXML
	public void filterRol(KeyEvent event) {
		String newValue = txfRol.getText();
		ac.changeFilter(newValue, "Rol");	
		}
	// Event Listener on TextField[#txfRol].onKeyReleased
	@FXML
	public void filterStatus(KeyEvent event) {
		String newValue = txfStatus.getText();
		ac.changeFilter(newValue, "Status");
	}

	@FXML
	public void userClickedOnTable(MouseEvent event) {
		// try catch evt informatie uit event
		btnWerknemerDetails.setDisable(false);
		btnWerknemerWijzigen.setDisable(false);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colGebruikersnaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("emailAdres"));
		colNaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("naam"));
		colVoornaam.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("voornaam"));
		colRol.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("rol"));
		colStatus.setCellValueFactory(new PropertyValueFactory<GebruikerGegevens, String>("status"));
		tblView.setItems(ac.geefWerknemers());

	}
}
