package be.vdab.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends AbstractDAO{

	private static final String FIND_ALL_GENRES_SQL = "SELECT Naam FROM genres";
	
	public Iterable<String> findAllGenres(){
		List<String> genres = new ArrayList<>();
		  try ( Connection connection = getConnection();
			     Statement statement = connection.createStatement();
				 ResultSet resultSet = statement.executeQuery(FIND_ALL_GENRES_SQL)){
					  while(resultSet.next()) {
					       genres.add(resultSet.getString("Naam"));
				       }
		                return genres;
		   }
		  catch(SQLException ex) {
			  throw new DAOException("Kan genres niet lezen uit databank",ex.getCause());
		  }
	}
}
