package domein.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KnowledgeBase implements KnowledgeBaseGegevens{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int knowledgeBaseId;
    private String titel;
    private String omschrijving;
    private LocalDate datumToevoegen = LocalDate.now();
    
	public KnowledgeBase() {
	}

	public KnowledgeBase(String titel, String omschrijving, LocalDate datumToevoegen) {
		setTitel(titel);
		setDatumToevoegen(datumToevoegen);
		setOmschrijving(omschrijving);
	}

	public int getKnowledgeBaseId() {
		return knowledgeBaseId;
	}

	public void setKnowledgeBaseId(int knowledgeBaseId) {
		this.knowledgeBaseId = knowledgeBaseId;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		if(titel == null || titel.isBlank())
			throw new IllegalArgumentException("Titel is verplicht.");
		this.titel=titel;
		
	}
	public LocalDate getDatumToevoegen() {
		return datumToevoegen;
	}

	public void setDatumToevoegen(LocalDate datumToevoegen) {
			this.datumToevoegen = datumToevoegen;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		if(omschrijving == null || omschrijving.isBlank())
			throw new IllegalArgumentException("Omschrijving is verplicht.");
		this.omschrijving=omschrijving;
	}
    
	
    
}
