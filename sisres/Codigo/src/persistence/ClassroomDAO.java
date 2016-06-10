<<<<<<< HEAD
=======
/**
 * Name:ClassroomDAO.java
 * Class for persistence in the database of classrooms information.
 */

>>>>>>> devel
package persistence;

import model.Classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import exception.PatrimonyException;

public class ClassroomDAO {
<<<<<<< HEAD

	//Mensagens
		private static final String SALA_JA_EXISTENTE = "Sala ja cadastrada.";
		private static final String SALA_NAO_EXISTENTE = "Sala nao cadastrada.";
		private static final String SALA_EM_USO = "Sala esta sendo utilizada em uma reserva.";
		private static final String SALA_NULA = "Sala esta nula.";
		private static final String CODIGO_JA_EXISTENTE = "Sala com o mesmo codigo ja cadastrada.";
	
	//Singleton
		private static ClassroomDAO instance;
		private ClassroomDAO(){
		}
		public static ClassroomDAO getClassroom(){
			if(instance == null)
				instance = new ClassroomDAO();
			return instance;
		}
	//

		
	public void include(Classroom sala) throws SQLException, PatrimonyException {	
		if(sala == null)
			throw new PatrimonyException(SALA_NULA);
		else if(this.inDBCodigo(sala.getIdEquipment()))
			throw new PatrimonyException(CODIGO_JA_EXISTENTE);
		this.updateQuery("INSERT INTO " +
					"sala (codigo, descricao, capacidade) VALUES (" +
					"\"" + sala.getIdEquipment() + "\", " +
					"\"" + sala.getDescriptionEquipment() + "\", " +
					sala.getCapacity() + ");");
	}

	public void update(Classroom old_sala, Classroom new_sala) throws SQLException, PatrimonyException {
		if(new_sala == null)
			throw new PatrimonyException(SALA_NULA);
		if(old_sala == null)
			throw new PatrimonyException(SALA_NULA);
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;
		
		if(!this.inDB(old_sala))
			throw new PatrimonyException(SALA_NAO_EXISTENTE);
		else if(this.inOtherDB(old_sala))
			throw new PatrimonyException(SALA_EM_USO);
		else if(!old_sala.getIdEquipment().equals(new_sala.getIdEquipment()) && this.inDBCodigo(new_sala.getIdEquipment()))
			throw new PatrimonyException(CODIGO_JA_EXISTENTE);
		if(!this.inDB(new_sala)){
			String msg = "UPDATE sala SET " +				
				"codigo = \"" + new_sala.getIdEquipment() + "\", " +
				"descricao = \"" + new_sala.getDescriptionEquipment() + "\", " +
				"capacidade = " + new_sala.getCapacity() +
				" WHERE " +
				"sala.codigo = \"" + old_sala.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + old_sala.getDescriptionEquipment() +  "\" and " +
				"sala.capacidade = " + old_sala.getCapacity() +";";
			con.setAutoCommit(false);
			pst = con.prepareStatement(msg);
			pst.executeUpdate();
			con.commit();
		}
		else
			throw new PatrimonyException(SALA_JA_EXISTENTE);
		
		pst.close();
		con.close();
	}

	public void delete(Classroom sala) throws SQLException, PatrimonyException {
		if(sala == null)
			throw new PatrimonyException(SALA_NULA);
		else if(this.inOtherDB(sala))
			throw new PatrimonyException(SALA_EM_USO);
		else if(this.inDB(sala)){
			this.updateQuery("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala.getDescriptionEquipment() +  "\" and " +
				"sala.capacidade = " + sala.getCapacity() + ";"				
				);
		}
		else
			throw new PatrimonyException(SALA_NAO_EXISTENTE);
	}

	
	
	public Vector<Classroom> searchAll() throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala;");
	}
	public Vector<Classroom> buscarPorCodigo(String valor) throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala WHERE codigo = " + "\"" + valor + "\";");
	}
	public Vector<Classroom> buscarPorDescricao(String valor) throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala WHERE descricao = " + "\"" + valor + "\";");
	}
	public Vector<Classroom> buscarPorCapacidade(String valor) throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala WHERE capacidade = " + valor + ";");
	}
	
	
	/**
	 * Metodos Privados
	 * */
	
	private Vector<Classroom> buscar(String query) throws SQLException, PatrimonyException {
		Vector<Classroom> vet = new Vector<Classroom>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
			vet.add(this.fetchSala(rs));
		
		pst.close();
		rs.close();
		con.close();
		return vet;
	}
	
	
	private boolean inDBGeneric(String query) throws SQLException{
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
		{
			rs.close();
			pst.close();
			con.close();
			return false;
		}
		else {
			rs.close();
			pst.close();
			con.close();
			return true;
		}
	}
	private boolean inDB(Classroom sala) throws SQLException{
		return this.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala.getDescriptionEquipment() + "\" and " +
				"sala.capacidade = " + sala.getCapacity() +
				";");
	}
	private boolean inDBCodigo(String codigo) throws SQLException{
		return this.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + codigo + "\";");
	}
	private boolean inOtherDB(Classroom sala) throws SQLException{
		if( this.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
				"id_sala = (SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala.getDescriptionEquipment() +  "\" and " +
				"sala.capacidade = " + sala.getCapacity() +" );") == false)
		{
			if(this.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
							"id_sala = (SELECT id_sala FROM sala WHERE " +
							"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
							"sala.descricao = \"" + sala.getDescriptionEquipment() +  "\" and " +
							"sala.capacidade = " + sala.getCapacity() +" );") == false)
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	private Classroom fetchSala(ResultSet rs) throws PatrimonyException, SQLException{
		return new Classroom(rs.getString("codigo"), rs.getString("descricao"), rs.getString("capacidade"));
	}
	
	private void updateQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
=======
	
	// Constants with error message
	private static final String EXISTING_CLASSROOM = "Sala ja cadastrada.";
	private static final String NOT_EXISTING_CLASSROOM = "Sala nao cadastrada.";
	private static final String CLASSROOM_IN_USE = "Sala esta sendo utilizada em uma reserva.";
	private static final String NULL_CLASSROOM = "Sala esta nula.";
	private static final String EXISTING_CODE = "Sala com o mesmo codigo ja cadastrada.";

	/**
	 * Current instance
	 */
	private static ClassroomDAO instance;

	private ClassroomDAO() {
	}
	
	/**
	 * Method to provider current instance or create a new.
	 * 
	 * @return instance
	 */

	public static ClassroomDAO getInstance() {
		if (instance != null) {
			// Nothing to do.
		} else {
			instance = new ClassroomDAO();
		}
		return instance;
	}
	
	/**
	 * Method to add classroom in the database
	 * 
	 * @param classroom
	 * @throws SQLException
	 * @throws PatrimonyException
	 */

	public void add(Classroom classroom) throws SQLException, PatrimonyException {
		//Verify if is a valid student.
		if (classroom != null) {
			//Try to execute statement.
			if (!this.inDBCode(classroom.getCode())) {
				this.updateQuery(
						"INSERT INTO " + "sala (codigo, descricao, capacidade) VALUES (" + "\"" + classroom.getCode()
								+ "\", " + "\"" + classroom.getDescription() + "\", " + classroom.getCapacity() + ");");
			} else {
				throw new PatrimonyException(EXISTING_CODE);
			}
		} else {
			throw new PatrimonyException(NULL_CLASSROOM);
		}
	}
	
	/**
	 * Method to Update data from classroom
	 * 
	 * @param old_classroom
	 * @param new_classroom
	 * @throws SQLException
	 * @throws PatrimonyException
	 */

	public void change(Classroom old_classroom, Classroom new_classroom) throws SQLException, PatrimonyException {
		if (new_classroom != null) {
			if (old_classroom != null) {
				//Start connection with database.
				Connection connection = FactoryConnection.getInstance().getConnection();
				PreparedStatement preopare_query_to_execute;

				if (this.inDB(old_classroom)) {
					if (this.inOtherDB(old_classroom)) {

						if (old_classroom.getCode().equals(new_classroom.getCode())
								&& !this.inDBCode(new_classroom.getCode())) {
							//Try update database.
							if (!this.inDB(new_classroom)) {
								String msg = "UPDATE sala SET " + "codigo = \"" + new_classroom.getCode() + "\", "
										+ "descricao = \"" + new_classroom.getDescription() + "\", " + "capacidade = "
										+ new_classroom.getCapacity() + " WHERE " + "sala.codigo = \""
										+ old_classroom.getCode() + "\" and " + "sala.descricao = \""
										+ old_classroom.getDescription() + "\" and " + "sala.capacidade = "
										+ old_classroom.getCapacity() + ";";
								connection.setAutoCommit(false);
								preopare_query_to_execute = connection.prepareStatement(msg);
								preopare_query_to_execute.executeUpdate();
								connection.commit();
							} else {
								throw new PatrimonyException(EXISTING_CLASSROOM);
							}
						} else {
							throw new PatrimonyException(EXISTING_CODE);
						}
					} else {
						throw new PatrimonyException(CLASSROOM_IN_USE);
					}
				} else {
					throw new PatrimonyException(NOT_EXISTING_CLASSROOM);
				}
				//Close connection.
				preopare_query_to_execute.close();
				connection.close();
			} else {
				throw new PatrimonyException(NULL_CLASSROOM);
			}
		} else {
			throw new PatrimonyException(NULL_CLASSROOM);
		}

	}
	
	/**
	 * Method to delete data from classroom.
	 * 
	 * @param classroom
	 * @throws SQLException
	 * @throws PatrimonyException
	 */

	public void delete(Classroom classroom) throws SQLException, PatrimonyException {
		//Verify if it is a valid student.
		if (classroom != null) {
			if (!this.inOtherDB(classroom)) {
				//Try to remove student from database.
				if (this.inDB(classroom)) {
					this.updateQuery("DELETE FROM sala WHERE " + "sala.codigo = \"" + classroom.getCode() + "\" and "
							+ "sala.descricao = \"" + classroom.getDescription() + "\" and " + "sala.capacidade = "
							+ classroom.getCapacity() + ";");
				} else {
					throw new PatrimonyException(NOT_EXISTING_CLASSROOM);
				}
			} else {
				throw new PatrimonyException(CLASSROOM_IN_USE);
			}
		} else {
			throw new PatrimonyException(NULL_CLASSROOM);
		}
	}
	
	/**
	 * Method to return all data in database about classroom.
	 * 
	 * @return Vector<Classroom> with all registers
	 * @throws SQLException
	 * @throws PatrimonyException
	 */

	public Vector<Classroom> searchAll() throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala;");
	}
	
	/**
	 * Method to return all data in database using code.
	 * 
	 * @param code
	 * @return Vector<Classroom> with all registers
	 * @throws SQLException
	 * @throws PatrimonyException
	 */

	public Vector<Classroom> searchCode(String code) throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala WHERE codigo = " + "\"" + code + "\";");
	}
	
	/**
	 * Method to return all data in database using description.
	 * 
	 * @param description
	 * @return Vector<Classroom> with all registers
	 * @throws SQLException
	 * @throws PatrimonyException
	 */

	public Vector<Classroom> searchDescription(String description) throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala WHERE descricao = " + "\"" + description + "\";");
	}
	
	/**
	 * Method to return all data in database using the capacity.
	 * 
	 * @param value
	 * @return Vector<Classroom> with all registers
	 * @throws SQLException
	 * @throws PatrimonyException
	 */

	public Vector<Classroom> searchCapacity(String value) throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala WHERE capacidade = " + value + ";");
	}
	
	/**
	 * Method to return all data in database.
	 * 
	 * @param query
	 * @return Vector<Aluno> data when all registers
	 * @throws SQLException
	 * @throws PatrimonyException
	 */

	private Vector<Classroom> buscar(String query) throws SQLException, PatrimonyException {
		//Start connection
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		
		//Execute consult
		ResultSet database_result = prepare_query_to_execute.executeQuery();

		//Fill vector with the data query result.
		Vector<Classroom> data_result = new Vector<Classroom>();
		while (database_result.next())
			data_result.add(this.fetchSala(database_result));

		//Close connection
		prepare_query_to_execute.close();
		database_result.close();
		connection.close();
		
		return data_result;
	}
	
	/**
	 * Method to execute generic consult
	 * 
	 * @param query
	 * @return isExists
	 * @throws SQLException
	 */

	private boolean inDBGeneric(String query) throws SQLException {
		//Start connection
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		
		//Data return by query
		ResultSet data_result = prepare_query_to_execute.executeQuery();

		//verify if data exists
		boolean has_result = data_result.next();
	
		//Close connection.
			data_result.close();
			prepare_query_to_execute.close();
			connection.close();
		
		return has_result;
	}
	
	/**
	 * Method to verify if classroom exists
	 * 
	 * @param classroom
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException
	 */

	private boolean inDB(Classroom classroom) throws SQLException {
		return this.inDBGeneric("SELECT * FROM sala WHERE " + "sala.codigo = \"" + classroom.getCode() + "\" and "
				+ "sala.descricao = \"" + classroom.getDescription() + "\" and " + "sala.capacidade = "
				+ classroom.getCapacity() + ";");
	}
	
	/**
	 * Method to verify if equipment exists using code
	 * 
	 * @param code
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException
	 */

	private boolean inDBCode(String code) throws SQLException {
		return this.inDBGeneric("SELECT * FROM sala WHERE " + "sala.codigo = \"" + code + "\";");
	}
	
	/**
	 * Method to verify if classroom exists using subquery in database
	 * 
	 * @param classroom
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException
	 */

	private boolean inOtherDB(Classroom classroom) throws SQLException {
		boolean verification = true;
		if (this.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE "
				+ "id_sala = (SELECT id_sala FROM sala WHERE " + "sala.codigo = \"" + classroom.getCode() + "\" and "
				+ "sala.descricao = \"" + classroom.getDescription() + "\" and " + "sala.capacidade = "
				+ classroom.getCapacity() + " );") == true) {
			if (this.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE "
					+ "id_sala = (SELECT id_sala FROM sala WHERE " + "sala.codigo = \"" + classroom.getCode()
					+ "\" and " + "sala.descricao = \"" + classroom.getDescription() + "\" and " + "sala.capacidade = "
					+ classroom.getCapacity() + " );") == false) {
				verification = false;
			} else {
				// Nothing to do.
			}
		} else {
			// Nothing to do.
		}
		return verification;
	}
	
	/**
	 * Method to convert result set in classroom
	 * 
	 * @param data_from_classroom
	 * @return new classroom with data in result set
	 * @throws PatrimonyException
	 * @throws SQLException
	 */

	private Classroom fetchSala(ResultSet data_from_classroom) throws PatrimonyException, SQLException {
		return new Classroom(data_from_classroom.getString("codigo"), data_from_classroom.getString("descricao"), data_from_classroom.getString("capacidade"));
	}
	
	/**
	 * Method to update classroom
	 * 
	 * @param menssage
	 * @throws SQLException
	 */

	private void updateQuery(String menssage) throws SQLException {
		//Start connection.
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(menssage);
		
		//Execute update query.
		prepare_query_to_execute.executeUpdate();
		prepare_query_to_execute.close();
		
		//close connection.
		connection.close();
>>>>>>> devel
	}

}
