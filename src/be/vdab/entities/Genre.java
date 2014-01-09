package be.vdab.entities;


 
public class Genre {
	
	 private long nummer;
	 private String naam;
	 
	 public Genre(String naam) {
		setNaam(naam);
	 }
	 
	 public Genre(long nummer, String naam) {
		setNummer(nummer);
		setNaam(naam);
	 }
	 
	 
    public long getNummer() {
		return nummer;
	}

	public void setNummer(long nummer) {
		this.nummer = nummer;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	 

	 
}
