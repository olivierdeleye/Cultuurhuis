package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import be.vdab.entities.MandjeItem;
import be.vdab.entities.Voorstelling;

public class MandjeDAO extends AbstractDAO {

	private static final String FIND_VOORSTELLING_SQL = "select VoorstellingsNr,Titel,Uitvoerders,Datum,"+
                            "GenreNr,Prijs,VrijePlaatsen from voorstellingen where VoorstellingsNr = ?";
	
	public Iterable<MandjeItem>toonMandje(Map<Long,Long>mandje){ 
		//parameter Map mandje is Map die op sessionScope staat met nummer van voorstelling en aantal plaatsen                                                         
		List<MandjeItem> mandjeItems = new ArrayList<>();
		
		for(Map.Entry<Long, Long> entry : mandje.entrySet()){
			long nr = entry.getKey(); //nummer van voorstelling 
			long plaatsen = entry.getValue(); // gekozen aantal plaatsen
			try(Connection connection = getConnection();
			  PreparedStatement prepStat = connection.prepareStatement(FIND_VOORSTELLING_SQL);){
			  prepStat.setLong(1,nr);
			  try(ResultSet resultSet = prepStat.executeQuery()){
			    if(resultSet.first()){
				 Voorstelling voorstelling  = new Voorstelling(resultSet.getLong("VoorstellingsNr"), resultSet.getString("Titel"), resultSet.getString("Uitvoerders"),
			               resultSet.getTimestamp("Datum"), resultSet.getLong("GenreNr"), resultSet.getBigDecimal("Prijs"), resultSet.getLong("VrijePlaatsen"));
				 MandjeItem mandjeItem = new MandjeItem(voorstelling, plaatsen);
				 mandjeItems.add(mandjeItem); // plaats object van MandjeItem in lijst met voorstelling en aantal plaatsen
			    }
			  }
			 
			}
			catch(SQLException ex){
				throw new DAOException("Kan voorstelling niet lezen uit database ",ex.getCause());
			}
		}
		
		return mandjeItems;
		
		
	}
	
	
	
}
