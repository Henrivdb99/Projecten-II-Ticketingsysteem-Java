package domein;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import domein.models.Contract;
import domein.models.ContractEnContractTypeStatus;
import domein.models.ContractType;
import domein.models.GebruikerStatus;
import domein.models.Klant;
import domein.models.KnowledgeBase;
import domein.models.ManierVanAanmakenTicket;
import domein.models.Ticket;
import domein.models.TicketStatus;
import domein.models.TijdstipTicketAanmaken;
import domein.models.WerknemerRol;
import domein.models.Werknemer;
import persistentie.GebruikerDaoJPA;
import persistentie.GenericDaoJPA;

public class PopuleerDB {
	
    public static int userId = 1; //"210771fc - 21f2 - 47e4 - a902 - 986e2d199105";
    
    public static void run() {
        GebruikerDaoJPA gebruikerdao = new GebruikerDaoJPA();
        GebruikerDaoJPA.startTransaction();
        
       
        Klant klant1 = new Klant("johan@actemium.be", "wachtwoord1", GebruikerStatus.ACTIEF, "Jorissen", "Johan", new String[] {"Jorisstraat", "46", "9000","Gent"}, new String[] {"049952754", "092214365"}, "HoGent");
        Klant klant2 = new Klant("stijn@vincke.be", "wachtwoord1", GebruikerStatus.ACTIEF, "Vincke", "Stijn", new String[] {"Borisstraat", "46", "9000","Gent"}, new String[] {"049352754", "092214365"}, "UGent");
        Klant klant3 = new Klant("moris@artevelde.be", "wachtwoord1", GebruikerStatus.NIETACTIEF, "Morissen", "Moris", new String[] {"Borisstraat", "46", "9000","Gent"}, new String[] {"049352754", "092214365"}, "Artevelde");
        
        Werknemer supportManager1 = new Werknemer("supportmanager@hogent.be", "wachtwoord1",GebruikerStatus.ACTIEF, "Tomssen", "Tom", new String[]{"Tomstraat", "46", "9000", "Gent"}, new String[] {"04991754", "099914365"}, WerknemerRol.SupportManager);
        Werknemer administrator1 = new Werknemer("administrator@hogent.be", "wachtwoord1", GebruikerStatus.ACTIEF, "Odinson", "Thor", new String[] {"Michaelstraat", "46", "9000", "Gent"}, new String[] {"049952804", "092214185"}, WerknemerRol.Administrator);
        Werknemer technieker1 = new Werknemer("technieker@hogent.be", "wachtwoord1", GebruikerStatus.ACTIEF, "Pieterssen", "Pieter", new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, WerknemerRol.Technieker);
        Werknemer technieker2 = new Werknemer("technieker2@hogent.be", "wachtwoord1", GebruikerStatus.ACTIEF, "Jacobus", "Jacob", new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, WerknemerRol.Technieker);

        Werknemer gebruiker1 = new Werknemer("techniekerOFF1@gmail.com", "wachtwoord1", GebruikerStatus.NIETACTIEF, "Morgan", "Arthur", new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, WerknemerRol.Technieker);
        Werknemer gebruiker2 = new Werknemer("supportmanagerOFF1@gmail.com", "wachtwoord1", GebruikerStatus.NIETACTIEF, "Hoed", "Jan", new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, WerknemerRol.SupportManager);
        Werknemer gebruiker3 = new Werknemer("supportmanagerOFF2@gmail.com", "wachtwoord1", GebruikerStatus.NIETACTIEF, "Lebowski", "Jef", new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, WerknemerRol.SupportManager);
        Werknemer gebruiker4 = new Werknemer("techniekerOFF2@gmail.com", "wachtwoord1", GebruikerStatus.NIETACTIEF, "Kennedy","Leon" ,new String[] {"Pieterstraat", "46", "9000", "Gent"}, new String[] {"049192754", "092217665"}, WerknemerRol.SupportManager);
        
        LocalDateTime date1= LocalDateTime.now().minusDays(2); 
        LocalDateTime date2 =LocalDateTime.now().minusHours(1);
        LocalDateTime date3 = LocalDateTime.now().minusHours(3);
        
        
        Ticket ticket1 = new Ticket("2020-Error 109271", TicketStatus.Afgehandeld, date1, "loremIpsum","Geen opmerkingen", 3, klant1, technieker1, "Geen bijlage" );
        Ticket ticket2 = new Ticket("2020-Error 2980", TicketStatus.InBehandeling, date2, "loremIpsum","Geen opmerkingen", 1,klant1, technieker1, "Geen bijlage" );
        Ticket ticket3 = new Ticket("2020-Authorisatie Probleem", TicketStatus.Geannuleerd, LocalDateTime.now(), "loremIpsum","Geen opmerkingen", 1, klant1, technieker1, "Geen bijlage" );
        Ticket ticket4 = new Ticket("2020-Error 1212", TicketStatus.InBehandeling, date3, "loremIpsum","Geen opmerkingen", 2, klant1, technieker1, "Geen bijlage" );
        Ticket ticket5 = new Ticket("2020-Error 1940", TicketStatus.Aangemaakt, date3, "loremIpsum","Geen opmerkingen", 2, klant1, technieker1, "Geen bijlage" );
        Ticket ticket6 = new Ticket("2020-Error 1522", TicketStatus.Aangemaakt, date2, "loremIpsum","Geen opmerkingen", 3, klant1, technieker1, "Geen bijlage" );

        ContractType contract24_7Email = new ContractType("Email Contract 24/7", ContractEnContractTypeStatus.Actief, ManierVanAanmakenTicket.Email, TijdstipTicketAanmaken.Altijd, 3, 10, 100);
        ContractType contract24_7Email2 = new ContractType("Email Contract 24/7 2 jaar", ContractEnContractTypeStatus.Actief, ManierVanAanmakenTicket.Email, TijdstipTicketAanmaken.Altijd, 2, 10, 100);
        ContractType contract24_7 = new ContractType("Applicatie Contract 24/7", ContractEnContractTypeStatus.Actief, ManierVanAanmakenTicket.EmailEnTelefonischEnApplicatie, TijdstipTicketAanmaken.Altijd, 1, 10, 100);
        ContractType contractWerkuren = new ContractType("Applicatie Contract Werkuren", ContractEnContractTypeStatus.Actief, ManierVanAanmakenTicket.EmailEnTelefonischEnApplicatie, TijdstipTicketAanmaken.TijdensWerkdagen, 1, 10, 100);
       
        Contract contract1 = new Contract(LocalDate.now().plusMonths(-13), contract24_7, 1, klant1, ContractEnContractTypeStatus.Afgelopen); 
        Contract contract2 = new Contract(LocalDate.now().plusMonths(-8), contract24_7, 1, klant1, ContractEnContractTypeStatus.InBehandeling);
        Contract contract3 = new Contract(LocalDate.now().plusMonths(-23).plusDays(-11), contractWerkuren, 2, klant1, ContractEnContractTypeStatus.Actief);
        Contract contract4 = new Contract(LocalDate.now().plusMonths(-22).plusDays(-10), contractWerkuren, 2, klant2, ContractEnContractTypeStatus.Actief);

        
        List<Contract> contracts1 = new ArrayList<>();
        contracts1.add(contract1); contracts1.add(contract2); contracts1.add(contract3); //bidirectioneel in domein, databank blijft gelijk
        klant1.setContracten(contracts1);
        
        List<Contract> contracts2 = new ArrayList<>();
        contracts2.add(contract4); //bidirectioneel in domein, databank blijft gelijk
        klant2.setContracten(contracts2);
        
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1); tickets.add(ticket2); tickets.add(ticket3); tickets.add(ticket4); tickets.add(ticket5); tickets.add(ticket6);//bidirectioneel in domein, databank blijft gelijk
        klant1.setTickets(tickets);
        
        GenericDaoJPA<Ticket> ticketdao = new GenericDaoJPA<>(Ticket.class); 
        ticketdao.insert(ticket1);
        ticketdao.insert(ticket2);
        ticketdao.insert(ticket3);
        ticketdao.insert(ticket4);
        ticketdao.insert(ticket5);
        ticketdao.insert(ticket6);

        
        GenericDaoJPA<ContractType> contractTypedao = new GenericDaoJPA<>(ContractType.class);
        contractTypedao.insert(contract24_7);
        contractTypedao.insert(contractWerkuren);
        contractTypedao.insert(contract24_7Email);
        contractTypedao.insert(contract24_7Email2);
        
        GenericDaoJPA<Contract> contractdao = new GenericDaoJPA<>(Contract.class);
        contractdao.insert(contract1);
        contractdao.insert(contract2);
        contractdao.insert(contract3);
        contractdao.insert(contract4);
        
        GenericDaoJPA<KnowledgeBase> knowledgebasedao = new GenericDaoJPA<>(KnowledgeBase.class);
        knowledgebasedao.insert(new KnowledgeBase("Hoe kan ik me aanmelden?", "loremIpsum", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Hoe kan ik mijn wachtwoord aanpassen?","loremIpsum", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Wat doe ik bij Error 5038","loremIpsum", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Hoe annuleer ik een ticket?","Oplossing 4", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Mijn nieuwe contract staat niet op actief","Oplossing 5", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Ik kan mijn bijlage niet uploaden","Oplossing 6", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Waar vind ik  mijn oude contracten terug?","Oplossing 7", LocalDate.now()));
        knowledgebasedao.insert(new KnowledgeBase("Ik kan de naam van mijn ticket niet meer aanpassen","Oplossing 8", LocalDate.now()));
        
		gebruikerdao.insert(klant1);
		gebruikerdao.insert(klant2);
		gebruikerdao.insert(klant3);
		gebruikerdao.insert(supportManager1);
		gebruikerdao.insert(administrator1);
		gebruikerdao.insert(technieker1);
		gebruikerdao.insert(technieker2);

		gebruikerdao.insert(gebruiker1);
		gebruikerdao.insert(gebruiker2);
		gebruikerdao.insert(gebruiker3);
		gebruikerdao.insert(gebruiker4);

	
        GebruikerDaoJPA.commitTransaction();
          
    }

}
