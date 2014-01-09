package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.vdab.entities.Klant;


public class KlantDAO extends AbstractDAO{

	private static final String FIND_KLANT_SQL = "select KlantNr,Voornaam,Familienaam,Straat,HuisNr,Postcode,Gemeente,"+
	                       "GebruikersNaam,Paswoord from klanten where GebruikersNaam = ? and Paswoord = ?";
	private static final String NIEUWE_KLANT_SQL = "insert into klanten (Voornaam,Familienaam,Straat,HuisNr,Postcode,Gemeente,GebruikersNaam,Paswoord)"+
                                  " values(?,?,?,?,?,?,?,?)";
	private static final String CHECK_GEBRUIKERSNAAM_SQL = "select GebruikersNaam from klanten where GebruikersNaam = ?";
	
	public Klant findKlant(String gebruikersNaam, String paswoord){
		Klant klant = null;
		try(Connection connection = getConnection();
		    PreparedStatement prepStat = connection.prepareStatement(FIND_KLANT_SQL);){
			prepStat.setString(1, gebruikersNaam);
			prepStat.setString(2, paswoord);
			try(ResultSet resultSet = prepStat.executeQuery()){
				if(resultSet.first()){
				  
				 klant = new Klant(resultSet.getLong("KlantNr"), resultSet.getString("Voornaam"), resultSet.getString("Familienaam"), 
					               resultSet.getString("Straat"), resultSet.getString("HuisNr"),resultSet.getString("Postcode"), 
					               resultSet.getString("Gemeente"),resultSet.getString("GebruikersNaam"),resultSet.getString("Paswoord"));
				 }
			}
		}
		catch(SQLException ex){
			throw new DAOException("Kan klant niet lezen uit database ",ex.getCause());
		}
		return klant;
	}
	
	public void createKlant(Klant klant){
		try(Connection connection = getConnection();
		    PreparedStatement prepStat = connection.prepareStatement(NIEUWE_KLANT_SQL, Statement.RETURN_GENERATED_KEYS);){
			prepStat.setString(1, klant.getVoorNaam());
			prepStat.setString(2, klant.getFamilieNaam());
			prepStat.setString(3, klant.getStraat());
			prepStat.setString(4, klant.getHuisNr());
			prepStat.setString(5, klant.getPostCode());
			prepStat.setString(6, klant.getGemeente());
			prepStat.setString(7, klant.getGebruikersNaam());
			prepStat.setString(8, klant.getPaswoord());
			prepStat.executeUpdate();
		    try (ResultSet resultSet = prepStat.getGeneratedKeys()){
			  if (!resultSet.next()) {
				  throw new DAOException("Kan nummer van toegevoegde klant niet lezen uit database");
			  }
			      klant.setNummer(resultSet.getLong(1));
		    }
		}
		catch(SQLException ex){
			throw new DAOException("Kan klant niet toevoegen in database ",ex.getCause());
		}
    }
	// checken of gebruikersnaam reeds in gebruik is - true indien reeds in gebruik
	public boolean checkGebruikersnaamUniciteit(String gebruikersNaam){ 
		try(Connection connection = getConnection();
		  PreparedStatement prepStat = connection.prepareStatement(CHECK_GEBRUIKERSNAAM_SQL);){
		  prepStat.setString(1, gebruikersNaam);
		  try(ResultSet resultSet = prepStat.executeQuery()){ 
			    return resultSet.next();
	       }
		}
		catch(SQLException ex){
			throw new DAOException("Kan gebruikersnaam niet lezen uit database ",ex.getCause());
		}
	}
	
	
}
