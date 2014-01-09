package be.vdab.entities;

import java.io.Serializable;

public class Reservatie implements Serializable{

	private static final long serialVersionUID = 1L;
	private long reservatieNr;
	private long klantNr;
	private long voorstellingsNr;
	private long plaaten;
	
	
	public Reservatie(long klantnr, long voorstellingsNr, long plaatsen) {
		setKlantNr(klantnr);
		setVoorstellingsNr(voorstellingsNr);
		setPlaaten(plaatsen);
	}
	
	public Reservatie(long reservatieNr, long klantnr, long voorstellingsNr, long plaatsen) {
		setReservatieNr(reservatieNr);
		setKlantNr(klantnr);
		setVoorstellingsNr(voorstellingsNr);
		setPlaaten(plaatsen);
	}

	public long getReservatieNr() {
		return reservatieNr;
	}

	public void setReservatieNr(long reservatieNr) {
		this.reservatieNr = reservatieNr;
	}

	public long getKlantNr() {
		return klantNr;
	}

	public void setKlantNr(long klantNr) {
		this.klantNr = klantNr;
	}

	public long getVoorstellingsNr() {
		return voorstellingsNr;
	}

	public void setVoorstellingsNr(long voorstellingsNr) {
		this.voorstellingsNr = voorstellingsNr;
	}

	public long getPlaaten() {
		return plaaten;
	}

	public void setPlaaten(long plaaten) {
		this.plaaten = plaaten;
	}

	
	
	
	
}
