package gui;

import controllers.GebruikerController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardSchermTechniekerController extends DashboardSchermGebruikerController {
	@FXML
	private Button btnOpenstaandeTickets;
	@FXML
	private Button btnAfgehandeldeTickets;
	@FXML
	private Button btnStatistieken;
	@FXML
	private Button btnKnowledgeBaseBeheren;
	
	
	public DashboardSchermTechniekerController(LoginSchermController loginSchermController, GebruikerController gebruikerController) {
		super(loginSchermController, gebruikerController);
		btnOpenstaandeTickets.setDisable(false);
		btnAfgehandeldeTickets.setDisable(false);
		btnStatistieken.setDisable(false);
		btnKnowledgeBaseBeheren.setDisable(false);
		btnOpenstaandeTickets.setVisible(true);
		btnAfgehandeldeTickets.setVisible(true);
		btnStatistieken.setVisible(true);
		btnKnowledgeBaseBeheren.setVisible(true);
	}
	
	

}
