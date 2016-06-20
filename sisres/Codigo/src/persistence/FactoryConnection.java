/*
 * File: FactoryConnectionDAO.java
 * Description: Class to configure connection with application and database
 * */

package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnection {
	private final String LOCAL = "jdbc:mysql://localhost/sisres_db";
	private final String USER = "testuser";
	private final String PASSWORD = "password";
	private static FactoryConnection instance;

	private FactoryConnection() {
	}

	
	/** Method to provider current instance or create a new
	 * @return FactoryConnection - current instance
	 */
	public static FactoryConnection getInstance() {
		//Verify if instance exist
		if (instance != null) {
			// Nothing to do
		} 
		else {
			instance = new FactoryConnection();
		}
		return instance;
	}


	/** Method to provider database connection
	 * @return Connection - Current connection
	 */
	public Connection getConnection() throws SQLException {
		//Create a instance to connection
		Connection connection = null;
		connection = DriverManager.getConnection(LOCAL, USER, PASSWORD);

		//Verify connect is successful
		assert connection != null;

		return connection;
	}

}
