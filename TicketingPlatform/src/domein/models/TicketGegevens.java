package domein.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TicketGegevens {
	public int getId();

	public String getTitel();

	public TicketStatus getStatus();

	public LocalDateTime getDatumAanmaken();

	public String getOmschrijving();

	public int getTypeTicket();

	public Klant getKlant();

	public Werknemer getTechnieker();

	public String getNaamVoornaam();

	public String getOpmerkingen();
	public String getDatumUur();
}
