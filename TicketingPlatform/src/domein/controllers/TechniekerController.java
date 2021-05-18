package domein.controllers;

import java.util.stream.Collectors;

import domein.models.KnowledgeBaseGegevens;
import domein.models.Ticket;
import domein.models.TicketGegevens;
import domein.models.WerknemerRol;
import domein.models.Werknemer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class TechniekerController extends AangemeldeGebruikerController {

	public TechniekerController(Werknemer aangemeldeGebruiker) {
		super(aangemeldeGebruiker);
	}

	@Override
	public WerknemerRol geefAangemeldeGebruikerType() {
		// TODO Auto-generated method stub
		return WerknemerRol.Technieker;
	}
	
	// === Beheer tickets ===

	@Override
	public SortedList<TicketGegevens> geefTickets() {
		return actemium.geefTickets(getAangemeldeGebruiker().getId());
	}
	
	// === Beheer knowledgebase ===

	@Override
	public SortedList<KnowledgeBaseGegevens> geefKnowledgebaseItems() {
		return actemium.geefKnowledgebaseItems();
	}
	public ObservableList<GebruikerGegevens> geefTechniekers() {
		return actemium.geefTechniekers();
	}
	@Override
	public void wijzigTicket(String titel, TicketStatus ticketStatus, LocalDate date, String omschrijving,String opmerkingen, int typeTicket, int klantId, int techniekerId, String bijlage) {
		actemium.w(titel, ticketStatus, date, omschrijving,opmerkingen, typeTicket, klantId, techniekerId, bijlage);
	}
}
