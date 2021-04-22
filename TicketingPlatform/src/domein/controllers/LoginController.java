package domein.controllers;

import javax.persistence.EntityNotFoundException;

import domein.models.Gebruiker;
import domein.models.TypeGebruiker;
import persistentie.GebruikerDaoJPA;
import prullenbak.DashboardSchermAdministratorController;
import prullenbak.DashboardSchermSupportManagerController;
import prullenbak.DashboardSchermTechniekerController;

public class LoginController {

	private Gebruiker aangemeldeGebruiker;
	private GebruikerDaoJPA gebruikerRepo;
	

	public LoginController() {
		gebruikerRepo = new GebruikerDaoJPA();
	}
	

	public LoginController(GebruikerDaoJPA gebruikerRepo) {
		this.gebruikerRepo = gebruikerRepo;
	}



	public Gebruiker getAangemeldeGebruiker() {
		return aangemeldeGebruiker;
	}
	
	public GebruikerDaoJPA getGebruikerRepo() {
		return gebruikerRepo;
	}

	
	public void meldAan(String email, String wachtwoord) throws IllegalArgumentException {
		try {
			Gebruiker gevondenGebruiker = gebruikerRepo.getGebruikerByEmail(email);
			if (gevondenGebruiker.getWachtwoord().equals(wachtwoord)) 
		    {
		    	setAangemeldeGebruiker(gevondenGebruiker);
		        //System.out.println("Aangemeld als " + gevondenSpeler.getEmail());
		    } else {
		    	throw new IllegalArgumentException("Foute wachtwoord");
		    }
		} catch(EntityNotFoundException e) {
			throw new IllegalArgumentException("Email adres is niet gekend");
		}
	}
	
	public AangemeldeGebruikerController geefJuisteController() {
		return switch(aangemeldeGebruiker.getClass().getSimpleName()) {
			case "Technieker" -> new TechniekerController(aangemeldeGebruiker);
			case "Administrator" -> new AdministratorController(aangemeldeGebruiker);
			case "SupportManager" -> new SupportManagerController(aangemeldeGebruiker);
			default -> throw new IllegalArgumentException("Unexpected value: " + aangemeldeGebruiker.getClass());
		};
	}
	
	public void meldAf() {
		setAangemeldeGebruiker(null);
	}

	/*public TypeGebruiker geefTypeGebruiker() {
		if(aangemeldeGebruiker == null)
			throw new IllegalArgumentException("Er is geen gebruiker aangemeld");
		return switch(aangemeldeGebruiker.getClass().getSimpleName()) {
			case "Technieker" ->  TypeGebruiker.Technieker;
			case "Administrator" ->  TypeGebruiker.Administrator;
			case "SupportManager" ->  TypeGebruiker.SupportManager;
			default -> throw new IllegalArgumentException("Unexpected value: " + aangemeldeGebruiker.getClass());
		};
	}*/
	
	/*public String geefNaamEnVoornaamAangemeldeGebruiker() {
		if(aangemeldeGebruiker == null)
			throw new IllegalArgumentException("Er is geen gebruiker aangemeld");
		return aangemeldeGebruiker.getVoornaam() + aangemeldeGebruiker.getNaam();
	} */

	private void setAangemeldeGebruiker(Gebruiker gebruiker) {
		this.aangemeldeGebruiker = gebruiker;
	}
	
    public void close() {
        GebruikerDaoJPA.closePersistency();
    }
}