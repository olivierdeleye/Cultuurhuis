package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservatieDAO extends AbstractDAO {
	
	private static final String FIND_VRIJE_PLAATSEN_SQL = "select VrijePlaatsen from voorstellingen where VoorstellingsNr = ?";
	private static final String CREATE_RESERVATIE_SQL = "insert into reservaties (KlantNr,VoorstellingsNr,Plaatsen) values(?,?,?)";
	private static final String VERMINDER_PLAATSEN_SQL = "update voorstellingen set VrijePlaatsen = ? where voorstellingsNr = ?";
	
	
	// methode die alles in een keer verwerkt : controle of vrije plaatsen voldoende is , reservatie in database schrijven en
	// aantal plaatsen in DB verminderen
	public boolean boekReservaties(long klantNr,long voorstellingsNr, long plaatsen){
		
		 try(Connection connection = getConnection()){
			  connection.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
		      connection.setAutoCommit(false);
		      long vrijePlaatsen = getVrijPlaatsen(voorstellingsNr);
				 if((vrijePlaatsen - plaatsen) < 0){
					 return false;
				 }
				 else {
			       try(PreparedStatement prepStat1 = connection.prepareStatement(CREATE_RESERVATIE_SQL);
				       PreparedStatement prepStat2 = connection.prepareStatement(VERMINDER_PLAATSEN_SQL)){
				 
			          prepStat1.setLong(1,klantNr); //voeg reservatie toe aan database
				      prepStat1.setLong(2,voorstellingsNr);
				      prepStat1.setLong(3,plaatsen);
				      prepStat1.executeUpdate();
				 
				      long resultPlaatsen = vrijePlaatsen - plaatsen;//verminder aantal plaatsen in database
				      prepStat2.setLong(1,resultPlaatsen);
				      prepStat2.setLong(2,voorstellingsNr);
				      prepStat2.executeUpdate();
				 
				     connection.commit();
				     }
				     catch(SQLException ex){
					   connection.rollback();
				      }
				 }
		 }
		catch(SQLException e) {
	       throw new DAOException("Kan reservatie niet toevoegen in database ",e.getCause());
	    }
	
		return true;
	}

	private long getVrijPlaatsen(long voorstellingsNr){
		long vrijePlaatsen = 0;
		try(Connection connection = getConnection();
			 PreparedStatement prepStat = connection.prepareStatement(FIND_VRIJE_PLAATSEN_SQL);){
			 prepStat.setLong(1, voorstellingsNr);
			 try(ResultSet resultSet = prepStat.executeQuery()){
				if(resultSet.first()){
					  vrijePlaatsen = resultSet.getLong(1);
					}
		     }
			 return vrijePlaatsen;
		 }
		catch(SQLException ex){
			throw new DAOException("Kan vrije plaatsen niet lezen uit database ",ex.getCause());
		}
	}
}
