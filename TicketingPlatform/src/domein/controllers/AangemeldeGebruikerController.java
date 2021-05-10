package domein.controllers;

import domein.models.Gebruiker;
import domein.models.GebruikerGegevens;
import domein.models.GebruikerStatus;
import domein.models.TypeGebruiker;
import javafx.collections.ObservableList;
import persistentie.GebruikerDaoJPA;

public abstract class AangemeldeGebruikerController {
	
	protected GebruikerDaoJPA gebruikerRepo;
	
	public AangemeldeGebruikerController() {
		this(new GebruikerDaoJPA());
	}
	
	public AangemeldeGebruikerController(GebruikerDaoJPA gebruikerRepo) {
		this.gebruikerRepo = gebruikerRepo;
	}

	public GebruikerDaoJPA getGebruikerRepo() {
		return gebruikerRepo;
	}

	public void setGebruikerRepo(GebruikerDaoJPA gebruikerRepo) {
		this.gebruikerRepo = gebruikerRepo;
	}

	public abstract TypeGebruiker geefAangemeldeGebruikerType();

	public Gebruiker geefGebruiker(int id) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public ObservableList<GebruikerGegevens> geefWerknemers() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public ObservableList<GebruikerGegevens> geefKlanten() {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public void verwijderGebruiker(int id) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}

	public void wijzigWerknemer(int id, String naam, String voornaam, String email, String[] telefoonnummers, TypeGebruiker rol, GebruikerStatus status ,String wachtwoord, String[] adres) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public void voegWerknemerToe(String naam, String voornaam, String email, String[] telefoonnummers, TypeGebruiker rol, String wachtwoord, String[] adres) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public void wijzigKlant(int id, String naam, String voornaam, String email, String[] telefoonnummers, GebruikerStatus status ,String wachtwoord, String[] adres, String bedrijfsnaam) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	public void voegKlantToe(String naam, String voornaam, String email, String[] telefoonnummers, String wachtwoord, String[] adres, String bedrijfsnaam) {
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
		
	}

	public void changeFilter(String newValue, String veld)
	{
		throw new UnsupportedOperationException("U heeft niet de nodige toestemming om deze opdracht uit te voeren");
	}
	
	
}
