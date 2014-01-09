package be.vdab.entities;

import java.io.Serializable;

public class Klant implements Serializable{

	private static final long serialVersionUID = 1L;
	private long nummer;
	private String voorNaam;
	private String familieNaam;
	private String straat;
	private String huisNr;
	private String postCode;
	private String Gemeente;
	private String gebruikersNaam;
	private String paswoord;
	
	public Klant(String voorNaam, String familieNaam, String straat, String huisNr, String postCode, String gemeente,
			     String gebruikersNaam, String paswoord) {
		setVoorNaam(voorNaam);
		setFamilieNaam(familieNaam);
		setStraat(straat);
		setHuisNr(huisNr);
		setPostCode(postCode);
		setGemeente(gemeente);
		setGebruikersNaam(gebruikersNaam);
		setPaswoord(paswoord);
		
		
	}
	
	public Klant(long nummmer,String voorNaam, String familieNaam, String straat, String huisNr, String postCode, String gemeente,
		     String gebruikersNaam, String paswoord) {
		setNummer(nummmer);
		setVoorNaam(voorNaam);
		setFamilieNaam(familieNaam);
		setStraat(straat);
		setHuisNr(huisNr);
		setPostCode(postCode);
		setGemeente(gemeente);
		setGebruikersNaam(gebruikersNaam);
		setPaswoord(paswoord);
		
		
	}

	public String getStraat() {
		
		return straat;
	}

	public void setStraat(String straat) {
		if ( (straat == null) || straat.isEmpty()){
			 throw new IllegalArgumentException();
			}
		
		this.straat = straat;
	}

	public void setHuisNr(String huisNr) {
		if ( (huisNr == null) || huisNr.isEmpty()){
			 throw new IllegalArgumentException();
			}
		this.huisNr = huisNr;
	}
	
	public String getHuisNr() {
		
		return huisNr;
	}

	public void setPostCode(String postCode) {
		if ( (postCode == null) || postCode.isEmpty()){
			 throw new IllegalArgumentException();
			}
		this.postCode = postCode;
	}
	
	public String getPostCode() {
		
		return postCode;
	}

	public void setGemeente(String gemeente) {
		if ( (gemeente == null) || gemeente.isEmpty()){
			 throw new IllegalArgumentException();
			}
		Gemeente = gemeente;
	}

	public String getGemeente() {
		
		return Gemeente;
	}

    public long getNummer() {
    	
		return nummer;
	}

	public void setNummer(long nummer) {
		this.nummer = nummer;
	}

	public String getVoorNaam() {
		return voorNaam;
	}

	public void setVoorNaam(String voorNaam) {
		if ( (voorNaam == null) || voorNaam.isEmpty()){
			 throw new IllegalArgumentException();
			}
		this.voorNaam = voorNaam;
	}

	public String getFamilieNaam() {
		
		return familieNaam;
	}

	public void setFamilieNaam(String familieNaam) {
		if ( (familieNaam == null) || familieNaam.isEmpty()){
			 throw new IllegalArgumentException();
			}
		this.familieNaam = familieNaam;
	}

	public String getGebruikersNaam() {
		return gebruikersNaam;
	}

	public void setGebruikersNaam(String gebruikersNaam) {
		if ( (gebruikersNaam == null) || gebruikersNaam.isEmpty()){
			 throw new IllegalArgumentException();
			}
		this.gebruikersNaam = gebruikersNaam;
	}

	public String getPaswoord() {
		return paswoord;
	}

	public void setPaswoord(String paswoord) {
		if ( (paswoord == null) || paswoord.isEmpty()){
			 throw new IllegalArgumentException();
			}
		this.paswoord = paswoord;
	}


	
	

	
}
