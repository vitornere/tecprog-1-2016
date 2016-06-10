/*
 * File: DAO.java
 * Description: Class to make transactions with database
 * */

package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

<<<<<<< HEAD
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;
=======
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;
>>>>>>> devel

public abstract class DAO {
	
	/** Method to search data in  database
	 * @param query String - query with contains the select clause
	 * @return Vector - vector with the select result
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
<<<<<<< HEAD
	protected Vector buscar(String query) throws SQLException, ClientException, 
													PatrimonyException, ReserveException{
=======

	protected Vector search(String query) throws SQLException, ClienteException, 
													PatrimonyException, ReservaException{
		//Start connection with database
		Connection connection =  FactoryConnection.getInstance().getConnection();
		
>>>>>>> devel
		Vector vet = new Vector();
		
		//Execute consult into database
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		ResultSet database_return = prepare_query_to_execute.executeQuery();
		
		//Save result of the database in the vector
		Vector query_result = new Vector();
		while(database_return.next()){
			query_result.add(this.fetch(database_return));
		}
		
		//Close connection with database
		prepare_query_to_execute.close();
		database_return.close();
		connection.close();
		
		return query_result;
	}
	

	/** Method to verify if the consult return any value
	 * @param query String - query with contains the select clause
	 * @return boolean - true if no exist result else false 
	 */
	protected boolean inDBGeneric(String query) throws SQLException{
		//Start connection with database
		Connection connection =  FactoryConnection.getInstance().getConnection();
		
		//Execute consult into database
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		ResultSet database_return = prepare_query_to_execute.executeQuery();
		
		boolean has_value = database_return.next();
		
		database_return.close();
		prepare_query_to_execute.close();
		connection.close();
		return has_value;
	}

	/**
	 * Funcao utilizada no buscar, por isso precisa ser implementada
	 * Ja foi implementada nas outras classes DAO. A implementacao eh
	 * semelhante.
	 * */
<<<<<<< HEAD
	protected abstract Object fetch(ResultSet rs) throws SQLException, ClientException,
														PatrimonyException, ReserveException;
=======
	protected abstract Object fetch(ResultSet rs) throws SQLException, ClienteException,
														PatrimonyException, ReservaException;
>>>>>>> devel
	
	
	/** Method to execute data manipulate in database, except consults and updates
	 * @param query String - only DML except consult and updates
	 */
	protected void executeQuery(String query) throws SQLException{
		//Prepare statement and start connection with database
		Connection connection =  FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		
		//Execute statement
		prepare_query_to_execute.executeUpdate();		
		
		//close connection
		prepare_query_to_execute.close();
		connection.close();
	}
	
	/** Method to execute update statement
	 * @param query String - only update statement
	 */
	protected void updateQuery(String query) throws SQLException{
		//Prepare statement and start connection with database
		Connection connection =  FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		connection.setAutoCommit(false); //Disable autocommit from the database
		
		//Execute statement
		prepare_query_to_execute.executeUpdate();		
		connection.commit(); //Execute commit in database
		
		//close connection
		prepare_query_to_execute.close();
		connection.close();
	}
}
