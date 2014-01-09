package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


public class Voorstelling implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long nummer;
	private String titel;
	private String uitvoerders;
	private Date datum; 
	private long genreNr;
	private BigDecimal prijs;
	private long vrijePlaatsen;
	
	public Voorstelling(String titel,String uitvoerders, Timestamp datum, long genreNr, BigDecimal prijs, long VrijePlaatsen) {
		setTitel(titel);
		setUitvoerders(uitvoerders);
		setDatum(datum);
		setGenreNr(genreNr);
		setPrijs(prijs);
		setVrijePlaatsen(VrijePlaatsen);
	}
	
    public Voorstelling(long nummer, String titel, String uitvoerders, Timestamp datum, long genreNr, BigDecimal prijs, long VrijePlaatsen) {
		setNummer(nummer);
    	setTitel(titel);
		setUitvoerders(uitvoerders);
		setDatum(datum);
		setGenreNr(genreNr);
		setPrijs(prijs);
		setVrijePlaatsen(VrijePlaatsen);
	}

	public long getNummer() {
		return nummer;
	}

	public void setNummer(long nummer) {
		this.nummer = nummer;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getUitvoerders() {
		return uitvoerders;
	}

	public void setUitvoerders(String uitvoerders) {
		this.uitvoerders = uitvoerders;
	}

	public Date getDatum() {
		return (Date)datum.clone();
	}

	public void setDatum(Timestamp datum) {
		
		this.datum = (Date)datum.clone(); 
	
	}
	public long getGenreNr() {
		return genreNr;
	}

	public void setGenreNr(long genreNr) {
		this.genreNr = genreNr;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public long getVrijePlaatsen() {
		return vrijePlaatsen;
	}

	public void setVrijePlaatsen(long vrijePlaatsen) {
		this.vrijePlaatsen = vrijePlaatsen;
	}
    
    
	
    

}
