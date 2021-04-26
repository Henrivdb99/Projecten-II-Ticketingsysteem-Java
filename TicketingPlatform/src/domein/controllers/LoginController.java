package domein.controllers;

import javax.persistence.EntityNotFoundException;

import domein.models.Gebruiker;
import domein.models.TypeGebruiker;
import persistentie.GebruikerDaoJPA;

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
	
	public GebruikerDaoJPA getgebruikerRepo() {
		return gebruikerRepo;
	}

	
	public void meldAan(String email, String wachtwoord) throws IllegalArgumentException {
		try {
			Gebruiker gevondenGebruiker = gebruikerRepo.getGebruikerByEmail(email);
			if (gevondenGebruiker.checkPassword(wachtwoord))
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
	
	
	public AangemeldeGebruikerController geefJuisteController() throws IllegalArgumentException {
		return switch(TypeGebruiker.valueOf(aangemeldeGebruiker.getClass().getSimpleName())) {
			case Technieker -> new TechniekerController();
			case Administrator -> new AdministratorController();
			case SupportManager -> new SupportManagerController();
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
	
	public String geefNaamEnVoornaamAangemeldeGebruiker() throws IllegalArgumentException {
		if(aangemeldeGebruiker == null)
			throw new IllegalArgumentException("Er is geen gebruiker aangemeld");
		return aangemeldeGebruiker.getVoornaam() + " " + aangemeldeGebruiker.getNaam();
	} 

	private void setAangemeldeGebruiker(Gebruiker gebruiker) {
		this.aangemeldeGebruiker = gebruiker;
	}
	
    public void close() {
        GebruikerDaoJPA.closePersistency();
    }


	public GebruikerDaoJPA getGebruikerRepo() {
		return this.gebruikerRepo;
	}
}
