package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FactoryConnection {
	static String statusConnection = "";
	
	private final String LOCAL = "jdbc:mysql://localhost/sisres_db";
	private final String USER = "testuser";
	private final String PASSWORD = "password";
	
	//Singleton
		private static FactoryConnection instance;
		private FactoryConnection(){
		}
		public static FactoryConnection getInstance(){
			if(instance == null)
				instance = new FactoryConnection();
			return instance;
		}
	//
		
		
	public Connection getConnection() throws SQLException{
		Connection con = null;
		con = DriverManager.getConnection(LOCAL, USER, PASSWORD);
		assert con != null;
		
		return con;
	}

}
