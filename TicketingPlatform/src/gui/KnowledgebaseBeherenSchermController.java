package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import domein.controllers.AangemeldeGebruikerController;
import domein.models.KnowledgebaseGegevens;
import domein.models.TicketGegevens;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class KnowledgebaseBeherenSchermController extends BorderPane implements Initializable {
	@FXML
	private Button btnItemToevoegen;
	@FXML
	private Button btnItemWijzigen;
	@FXML
	private Button btnTerug;
	@FXML
	private Button btnToonOmschrijving;
	@FXML
	private TextField txfFilterTitel;
	@FXML
	private TableView<KnowledgebaseGegevens> tblView;
	@FXML
	private TableColumn<KnowledgebaseGegevens, Integer> colItemId;
	@FXML
	private TableColumn<KnowledgebaseGegevens, String> colItemTitel;
	@FXML
	private TableColumn<KnowledgebaseGegevens, LocalDate> colItemDatum;

	private DashboardSchermController parent;
	private AangemeldeGebruikerController ac;
	private KnowledgebaseGegevens selectedItem;

	public KnowledgebaseBeherenSchermController(DashboardSchermController dashboardSchermController,
			AangemeldeGebruikerController ac) {
		this.parent = dashboardSchermController;
		this.ac = ac;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KnowledgebaseBeherenScherm.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();

			if (ac.geefAangemeldeGebruikerType().toString() == "Technieker") {
				btnItemToevoegen.setVisible(false);
				btnItemWijzigen.setVisible(false);
			}

			btnToonOmschrijving.setDisable(true);
			btnItemWijzigen.setDisable(true);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@FXML
	public void btnItemToevoegenOnAction(ActionEvent event) {
		KnowledgebaseItemToevoegenSchermController ktvsc = new KnowledgebaseItemToevoegenSchermController(this, ac);
		this.setRight(ktvsc);
	}

	@FXML
	public void btnItemWijzigenOnAction(ActionEvent event) {
		this.selectedItem = tblView.getSelectionModel().getSelectedItem();
		KnowledgebaseItemWijzigenSchermController ktwsc = new KnowledgebaseItemWijzigenSchermController(this,
				selectedItem, ac);
		this.setRight(ktwsc);
	}

	@FXML
	public void btnTerugOnAction(ActionEvent event) {
		Stage stage = (Stage) (getScene().getWindow());
		stage.setScene(this.parent.getScene());
	}

	@FXML
	public void btnToonOmschrijvingOnAction(ActionEvent event) {
		this.selectedItem = tblView.getSelectionModel().getSelectedItem();
		KnowledgebaseItemOmschrijvingSchermController tdsc = new KnowledgebaseItemOmschrijvingSchermController(this,
				this.selectedItem);

		this.setRight(tdsc);
	}

	@FXML
	public void filterTitel(KeyEvent event) {
		String newValue = txfFilterTitel.getText();
		ac.changeFilterKnowledgebase(newValue, "knowledgebaseTitel");
	}

	@FXML
	public void userClickedOnTable(MouseEvent event) {
		btnItemWijzigen.setDisable(false);
		btnToonOmschrijving.setDisable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colItemId.setCellValueFactory(new PropertyValueFactory<KnowledgebaseGegevens, Integer>("knowledgeBaseId"));
		colItemDatum.setCellValueFactory(new PropertyValueFactory<KnowledgebaseGegevens, LocalDate>("datumToevoegen"));
		colItemTitel.setCellValueFactory(new PropertyValueFactory<KnowledgebaseGegevens, String>("titel"));
		tblView.setItems(ac.geefKnowledgebaseItems());
		ac.geefKnowledgebaseItems().comparatorProperty().bind(tblView.comparatorProperty());
	}
}
