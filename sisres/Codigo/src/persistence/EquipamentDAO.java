/*
 * File: EquipamentDAO.java
 * Description: Class to make transactions with the database to crud equipament
 * */

package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Equipamento;
import exception.PatrimonyException;

public class EquipamentDAO {

	// Constants with error messenger
	private static final String EXISTENTEQUIPAMENT = "Equipamento ja cadastrado.";
	private static final String EQUIPAMENTNOTEXIST = "Equipamento nao cadastrado.";
	private static final String NULLEQUIPAMENT = "Equipamento esta nulo.";
	private static final String EQUIPAMENTINUSE = "Equipamento esta sendo utilizado em uma reserva.";
	private static final String EXISTENTCODE = "Equipamento com o mesmo codigo ja cadastrado.";

	// Current instance
	private static EquipamentDAO instance;

	private EquipamentDAO() {
	}

	/** Method to provider current instance or create a new
	 * @return EquipamentDAO - current instance
	 */
	public static EquipamentDAO getInstance() {
		if (instance == null) {
			instance = new EquipamentDAO();
		} 
		else {
			// Nothing to do
		}
		return instance;
	}

	/** Method to add equipament in the database
	 * @param equipament Equipamento - Object to add in database
	 * @throws SQLException happens when sql code is wrong
	 * @throws PatrimonyException happens when equipament is null or his code exist
	 */
	public void add(Equipamento equipament) throws SQLException,
			PatrimonyException {
		
		if (equipament == null) {
			throw new PatrimonyException(NULLEQUIPAMENT);
		}
		else {
			if (this.inDBcode(equipament.getCode())) {
				throw new PatrimonyException(EXISTENTCODE);
			} 
			else {
				if (!this.inDB(equipament)) {
					this.updateQuery("INSERT INTO "
							+ "equipamento (codigo, descricao) VALUES (" + "\""
							+ equipament.getCode() + "\", " + "\""
							+ equipament.getDescription() + "\");");
				}
			}
		}
	}
	
	/**
	 * 
	 * @param old_equipment Equipamento - has the equipment to update
	 * @param new_equipment - has the update equipment
	 * @throws SQLException happens when sql code is wrong
	 * @throws PatrimonyException happens when old or new equipment is null or his code exist
	 */
	public void change(Equipamento old_equipment, Equipamento new_equipment)
			throws SQLException, PatrimonyException {
		
		//Verify if the old equipment and new equipment is valid
		if (old_equipment != null) {
			// Nothing to do
		}
		else{
			throw new PatrimonyException(NULLEQUIPAMENT);	
		}
		if (new_equipment != null) {
			// Nothing to do
		}
		else{
			throw new PatrimonyException(NULLEQUIPAMENT);
		}
		if (this.inDB(old_equipment)) {
			// Nothing to do
		} 
		else{
			throw new PatrimonyException(EQUIPAMENTNOTEXIST);
		}
		if (this.inOtherDB(old_equipment)) {
			// Nothing to do
		} 
		else{
			throw new PatrimonyException(EQUIPAMENTINUSE);
		}
		if (new_equipment.getCode().equals(
				old_equipment.getCode())
				&& this.inDBcode(new_equipment.getCode())) {
			// Nothing to do
		} 
		else {
			throw new PatrimonyException(EXISTENTCODE);
		}
			
		
		//Start a connection
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_database;

		//try update in database
		if (!this.inDB(new_equipment)) {
			String msg = "UPDATEequipament SET " + "codigo = \""
					+ new_equipment.getCode() + "\", "
					+ "descricao = \""
					+ new_equipment.getDescription() + "\""
					+ " WHERE " + "equipamento.codigo = \""
					+ old_equipment.getCode() + "\" and "
					+ "equipamento.descricao = \""
					+ old_equipment.getDescription() + "\";";

			connection.setAutoCommit(false);
			prepare_query_to_database = connection.prepareStatement(msg);
			prepare_query_to_database.executeUpdate();
			connection.commit();

			prepare_query_to_database.close();

		} 
		else {
			throw new PatrimonyException(EXISTENTEQUIPAMENT);
		}
		
		//close connection
		connection.close();
	}

	/** Method to remove equipment from database
	 * @param equipament Equipamento - equipment to remove
	 * @throws SQLException happens when sql code is wrong
	 * @throws PatrimonyException happens when old or new equipment is null, not exist or in use
	 */
	public void delete(Equipamento equipament) throws SQLException,
			PatrimonyException {
		//Verify is valid equipment
		if (equipament != null) {
			//Nothing to do
		} 
		else{
			throw new PatrimonyException(NULLEQUIPAMENT);
		}
		if (!this.inOtherDB(equipament)) {
			//Nothing to do
		}
		else{
			throw new PatrimonyException(EQUIPAMENTINUSE);
		}
		
		//try remove equipment from database
		if (this.inDB(equipament)) {
			this.updateQuery("DELETE FROM equipamento WHERE "
					+ "equipamento.codigo = \"" + equipament.getCode()
					+ "\" and " + "equipamento.descricao = \""
					+ equipament.getDescription() + "\";");
		}
		else {
			throw new PatrimonyException(EQUIPAMENTNOTEXIST);
		}
		
	}

	/**
	 * Method to return all data in database
	 * @return Vector<Equipamento> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws PatrimonyException don't happens
	 */
	public Vector<Equipamento> searchAll() throws SQLException,
			PatrimonyException {
		return this.search("SELECT * FROM equipamento;");
	}


	/**
	 * Method to return all data in database with condition on equipment code
	 * @param code String - Restriction in sql consult
	 * @return Vector<Equipamento> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws PatrimonyException don't happens
	 */
	public Vector<Equipamento> searchByCode(String code) throws SQLException,
			PatrimonyException {
		return this.search("SELECT * FROM equipamento WHERE codigo = " + "\""
				+ code + "\";");
	}

	/**
	 * Method to return all data in database with condition on description
	 * @param description String - Restriction in sql consult
	 * @return Vector<Equipamento> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws PatrimonyException don't happens
	 */
	public Vector<Equipamento> searchByDescription(String description)
			throws SQLException, PatrimonyException {
		return this.search("SELECT * FROM equipamento WHERE descricao = " + "\""
				+ description + "\";");
	}


	/**
	 * Method to execute generic consult
	 * @param query String - Select query 
	 * @return Vector<Equipamento> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws PatrimonyException don't happens
	 */
	private Vector<Equipamento> search(String query) throws SQLException,
			PatrimonyException {

		//Start connection
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		
		//Execute query in database
		ResultSet data_result = prepare_query_to_execute.executeQuery();

		//Save data result in a vector
		Vector<Equipamento> equipment_result = new Vector<Equipamento>();
		while (data_result.next()) {
			equipment_result.add(this.fetchEquipamento(data_result));
		}

		//Close connection.
		prepare_query_to_execute.close();
		data_result.close();
		connection.close();
		
		return equipment_result;
	}
	
	/**
	 * Method to verify if query return any data
	 * @param query - Consult sql to execute, only select statement
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException happens when sql code is wrong
	 */
	private boolean inDBGeneric(String query) throws SQLException {
		//Start connection
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		
		//Execute consult
		ResultSet rs = prepare_query_to_execute.executeQuery();

		//Verify if data exists
		boolean isExists = true;
		if (!rs.next()) {
			rs.close();
			prepare_query_to_execute.close();
			connection.close();
			isExists = false;
		}
		else {
			rs.close();
			prepare_query_to_execute.close();
			connection.close();
			isExists = true;
		}
		
		return isExists;
	}

	/**
	 * Method to verify if equipment exists
	 * @param equipment - equipment to search
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException happens when sql code is wrong
	 */
	private boolean inDB(Equipamento equipament) throws SQLException,
			PatrimonyException {
		return this.inDBGeneric("SELECT * FROM equipamento WHERE "
				+ "equipamento.codigo = \"" + equipament.getCode() + "\" and "
				+ "equipamento.descricao = \"" + equipament.getDescription() + "\";");
	}


	/**
	 * Method to verify if equipment exists using code
	 * @param code - code of equipment
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException happens when sql code is wrong
	 */
	private boolean inDBcode(String code) throws SQLException {
		return this.inDBGeneric("SELECT * FROM equipamento WHERE "
				+ "codigo = \"" + code + "\";");
	}

	/**
	 * Method to verify if equipment exists using subquery in database
	 * @param equipment - equipment to search
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException happens when sql code is wrong
	 */
	private boolean inOtherDB(Equipamento equipament) throws SQLException {
		return this
				.inDBGeneric("SELECT * FROM reserva_equipamento WHERE "
						+ "id_equipamento = (SELECT id_equipamento FROM equipamento WHERE "
						+ "equipamento.codigo = \"" + equipament.getCode() + "\" and "
						+ "equipamento.descricao = \"" + equipament.getDescription()
						+ "\");");
	}

	/**
	 * Method to convert result set in Equipment
	 * @param equipament_data result set contain equipment data
	 * @return new Equipment with data in result set
	 * @throws PatrimonyException  will occur if the code is invalid, can no longer exist and not be null
	 * @throws SQLException happens when sql code is wrong
	 */
	private Equipamento fetchEquipamento(ResultSet equipament_data)
			throws PatrimonyException, SQLException {
		return new Equipamento(equipament_data.getString("codigo"),
				equipament_data.getString("descricao"));
	}

	/**
	 * Method to update equipment
	 * @param query - query with update data in database
	 * @throws SQLException happens when sql code is wrong
	 */
	private void updateQuery(String query) throws SQLException {
		//Start connection
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		
		//Execute query
		prepare_query_to_execute.executeUpdate();
		
		//Close database connection
		prepare_query_to_execute.close();
		connection.close();
	}

}
