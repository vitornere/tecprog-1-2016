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

import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

public abstract class DAO {

	//Variable to manager the connection with database
	private Connection connection;
	
	/**
	 * Method to try open database connection
	 * @return true if open with success else false
	 * @throws SQLException error with the connection string
	 */
	protected boolean openConnection() throws SQLException
	{
		connection = FactoryConnection.getInstance().getConnection();
		return connection != null;
	}
	
	/**
	 * Method to prepare query string to execute in database
	 * @param query query with the sql statement
	 * @return an object with the query prepared to execute in database
	 * @throws SQLException wrong write sql statement
	 */
	protected PreparedStatement prepareStatement(String query) throws SQLException
	{
		return connection.prepareStatement(query);
	}
	
	/**
	 * Method to set status if database is auto commit or no.
	 * @param isAutoCommit true if is auto commit or false is not
	 * @throws SQLException
	 */
	protected void setAutoCommit(boolean isAutoCommit) throws SQLException
	{
		connection.setAutoCommit(isAutoCommit);
	}

	/**
	 * Method to close connection with application and database
	 * @throws SQLException
	 */
	protected void closeConnection() throws SQLException
	{
		connection.close();
	}

	/**
	 * Method to force database to execute commit
	 * @throws SQLException
	 */
	protected void commit() throws SQLException
	{
		connection.commit();
	}
	
	/** Method to search data in  database
	 * @param query String - query with contains the select clause
	 * @return Vector - vector with the select result
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Vector search(String query) throws SQLException, ClientException, 
													PatrimonyException, ReserveException{
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
	protected abstract Object fetch(ResultSet rs) throws SQLException, ClientException,
														PatrimonyException, ReserveException;
	
	
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
