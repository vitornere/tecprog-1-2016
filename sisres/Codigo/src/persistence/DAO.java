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

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public abstract class DAO {
	
	/** Method to search data in  database
	 * @param query String - query with contains the select clause
	 * @return Vector - vector with the select result
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Vector search(String query) throws SQLException, ClienteException, 
													PatrimonioException, ReservaException{
		//Start connection with database
		Connection connection =  FactoryConnection.getInstance().getConnection();
		
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

	/** Method abstract to receive a result set and convert to other object
	 * @param data ResultSet - result set with the result with the object data
	 * @return boolean - object with your value
	 */
	protected abstract Object fetch(ResultSet data) throws SQLException, ClienteException,
														PatrimonioException, ReservaException;
	
	
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
