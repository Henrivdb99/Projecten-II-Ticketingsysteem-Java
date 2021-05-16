package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.Gebruiker;
import domein.models.Ticket;
import domein.models.TicketGegevens;
import domein.models.TicketStatus;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class TicketsBeherenSchermController extends BorderPane implements Initializable{
	@FXML
	private Button btnTicketToevoegen;
	@FXML
	private Button btnTicketWijzigen;
	@FXML
	private Button btnTerug;
	@FXML
	private Button btnTicketDetails;
	@FXML
	private TextField txfStatus;
	@FXML
	private TextField txfKalnt;
	@FXML
	private TextField txfType;
	@FXML
	private TextField txfTitel;
	@FXML
	private Button btnResetFilters;
	@FXML
	private TableView<TicketGegevens> tblView;
	@FXML
	private TableColumn<TicketGegevens, Integer> colTicketId;
	@FXML
	private TableColumn<TicketGegevens, String> colTicketTitel;
	@FXML
	private TableColumn<TicketGegevens, String> colTicketType;
	@FXML
	private TableColumn<TicketGegevens, String> colKlant;
	@FXML
	private TableColumn<TicketGegevens, String> colStatus;
	@FXML
	private ChoiceBox<TicketStatus> cboStatus;
	
	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;
	private TicketGegevens selectedTicket;

	public TicketsBeherenSchermController(DashboardSchermController dashboardSchermController,
			AangemeldeGebruikerController ac) {
		this.parent = dashboardSchermController;
		this.ac = ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketsBeherenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
			
			if (ac.getClass().getSimpleName().equals("TechniekerController")) {
				btnTicketToevoegen.setVisible(false);
			}
			cboStatus.setItems(FXCollections.observableArrayList(TicketStatus.values()));
			
			cboStatus.setOnAction(event -> {
				ac.changeFilter(cboStatus.getValue().toString(), "ticketStatus");
			});
			btnTicketDetails.setDisable(true);
			btnTicketWijzigen.setDisable(true);

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@FXML
	public void btnTicketToevoegenOnAction(ActionEvent event) {
		TicketToevoegenSchermController ttsc = new TicketToevoegenSchermController(this, ac);
		this.setRight(ttsc);
	}

	@FXML
	public void btnTicketWijzigenOnAction(ActionEvent event) {
		this.selectedTicket = tblView.getSelectionModel().getSelectedItem();
		TicketWijzigenSchermController twsc = new TicketWijzigenSchermController(this, selectedTicket, ac);
		this.setRight(twsc);
	}

	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	@FXML
	public void btnTicketDetailsOnAction(ActionEvent event) {
		this.selectedTicket = tblView.getSelectionModel().getSelectedItem();
		TicketDetailsSchermController tdsc = new TicketDetailsSchermController(this, this.selectedTicket);

		this.setRight(tdsc);
	}
	

	// Event Listener on TableView[#tblView].onMouseClicked
	@FXML
	public void userClickedOnTable(MouseEvent event) {
		btnTicketDetails.setDisable(false);
		btnTicketWijzigen.setDisable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colTicketId.setCellValueFactory(new PropertyValueFactory<TicketGegevens, Integer>("id"));
		colTicketType.setCellValueFactory(new PropertyValueFactory<TicketGegevens, String>("typeTicket"));
		colTicketTitel.setCellValueFactory(new PropertyValueFactory<TicketGegevens, String>("titel"));
		colKlant.setCellValueFactory(new PropertyValueFactory<TicketGegevens, String>("naamVoornaam"));
		colStatus.setCellValueFactory(new PropertyValueFactory<TicketGegevens, String>("status"));
		tblView.setItems(ac.geefTickets()); //Geeft voor technieker enkel de tickets die voor hem zijn bedoeld, Supportmanager krijgt alles
	}
}
