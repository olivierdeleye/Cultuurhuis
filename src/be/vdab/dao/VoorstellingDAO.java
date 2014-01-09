package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Voorstelling;

public class VoorstellingDAO extends AbstractDAO{
	
	
	private static final String FIND_ALL_BY_GENRE_SQL = "select a.VoorstellingsNr,a.Titel,a.Uitvoerders,"+
			                             "a.Datum,a.GenreNr,a.Prijs,a.VrijePlaatsen from voorstellingen "+
			                             "as a inner join genres as b on a.GenreNr = b.GenreNr "+
			                             "and a.Datum > {fn curdate()} and b.Naam = ? ";
	private static final String FIND_VOORSTELLING_SQL = "select VoorstellingsNr,Titel,Uitvoerders,Datum,"+
			                        "GenreNr,Prijs,VrijePlaatsen from voorstellingen where VoorstellingsNr = ?"; 
	
	
	public Voorstelling findVoorstelling(long nummer){
		Voorstelling voorstelling = null;
		try(Connection connection = getConnection();
		    PreparedStatement prepStat = connection.prepareStatement(FIND_VOORSTELLING_SQL);){
			prepStat.setLong(1, nummer);
			
			try(ResultSet resultSet = prepStat.executeQuery()){
				if(resultSet.next()){
				voorstelling = new Voorstelling(resultSet.getLong("VoorstellingsNr"), resultSet.getString("Titel"), resultSet.getString("Uitvoerders"),
				               resultSet.getTimestamp("Datum"), resultSet.getLong("GenreNr"), resultSet.getBigDecimal("Prijs"), resultSet.getLong("VrijePlaatsen"));
				}
	         }
		  }
		  catch(SQLException ex){
			throw new DAOException("Kan voorstelling niet lezen uit database ",ex.getCause());
		  }
		return voorstelling;
	}
	
	 public List<Voorstelling> findAllByGenre(String genre){
		 List<Voorstelling> voorstellingen = new ArrayList<>();
		  try ( Connection connection = getConnection();
			  PreparedStatement prepStat = connection.prepareStatement(FIND_ALL_BY_GENRE_SQL);
			  ){
			    prepStat.setString(1,genre);
			   try(ResultSet resultSet = prepStat.executeQuery()){ 
			     
			      while ( resultSet.next()) {
				    voorstellingen.add(resultSetRijNaarVoorstelling(resultSet));
			      }
			   }
			  return voorstellingen;
		  }
		  catch(SQLException ex) {
			  throw new DAOException("Kan voorstellingen niet lezen uit database", ex );
		  }
		 
	  }
	 
	 private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet) 
			  throws SQLException {
				  return new Voorstelling(resultSet.getLong("VoorstellingsNr"), resultSet.getString("Titel"), resultSet.getString("Uitvoerders"),
			               resultSet.getTimestamp("Datum"), resultSet.getLong("GenreNr"), resultSet.getBigDecimal("Prijs"), resultSet.getLong("VrijePlaatsen"));
	          }
   
}
