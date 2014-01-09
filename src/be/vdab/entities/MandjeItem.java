package be.vdab.entities;

import java.io.Serializable;

public class MandjeItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Voorstelling voorstelling;
	private long plaatsen;
	
	public MandjeItem(Voorstelling voorstelling, long plaatsen) {
		setVoorstelling(voorstelling);
		setPlaatsen(plaatsen);
	}
	public Voorstelling getVoorstelling() {
		return voorstelling;
	}
	public void setVoorstelling(Voorstelling voorstelling) {
		this.voorstelling = voorstelling;
	}
	public long getPlaatsen() {
		return plaatsen;
	}
	public void setPlaatsen(long plaatsen) {
		this.plaatsen = plaatsen;
	}
	
	

	
}
