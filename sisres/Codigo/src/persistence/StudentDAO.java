/*
 * File: AlunoDAO.java
 * Description: Class to make transactions with the database to crud student
 * */

package persistence;

import model.Aluno;

import java.sql.*;
import java.util.Vector;

import exception.ClienteException;

public class StudentDAO {

	// Constants with error messenger
	private static final String EXISTENTSTUDENT = "O Aluno ja esta cadastrado.";
	private static final String NULLSTUDENT = "O Aluno esta nulo.";
	private static final String STUDENTNOTEXIST = "O Aluno nao esta cadastrado.";
	private static final String STUDENTINUSE = "Sala esta sendo utilizada em uma reserva.";
	private static final String EXISTENTCPF = "Ja existe um aluno cadastrado com esse CPF.";
	private static final String EXISTENTREGISTER = "Ja existe um aluno cadastrado com essa matricula.";

	// Current instance
	private static StudentDAO instance;

	private StudentDAO() {
	}

	/** Method to provider current instance or create a new
	 * @return StudentDAO - current instance
	 */
	public static StudentDAO getInstance() {
		if (instance == null) {
			instance = new StudentDAO();
		} 
		else {
			// Nothing to do.
		}
		return instance;
	}

	/** Method to add student in the database
	 * @param student - Object to add in database
	 * @throws SQLException happens when sql code is wrong
	 * @throws ClienteException happens when student is null or his code exist
	 */
	public void add(Aluno student) throws SQLException, ClienteException {
		//Verify if is valid student
		if (student != null) {
			// Nothing to do.
		}
		else{
			throw new ClienteException(NULLSTUDENT);
		}
		if (!this.inDBCpf(student.getCpf())) {
			// Nothing to do.
		}
		else{
			throw new ClienteException(EXISTENTCPF);
		}
		if (!this.inDBRegister(student.getMatricula())) {
			// Nothing to do
		}
		else{
			throw new ClienteException(EXISTENTREGISTER);
		}

		// Try execute statement
		if (!this.inDB(student)) {
			this.updateQuery("INSERT INTO "
					+ "aluno (nome, cpf, telefone, email, matricula) VALUES ("
					+ "\"" + student.getNome() + "\", " + "\""
					+ student.getCpf() + "\", " + "\""
					+ student.getTelefone() + "\", " + "\""
					+ student.getEmail() + "\", " + "\""
					+ student.getMatricula() + "\"); ");
		} 
		else {
			throw new ClienteException(EXISTENTSTUDENT);
		}
	}

	/**
	 * Method to Update data from student
	 * @param old_student - Student to update data
	 * @param new_student - Student with updated data
	 * @throws SQLException happens when sql code is wrong
	 * @throws ClienteException happens when student is null or his code exist
	 */
	public void change(Aluno old_student, Aluno new_student)
			throws SQLException, ClienteException {
		// Verify if is valid old_student and new student
		if (old_student != null) {
			// Nothing to do.
		}
		else{
			throw new ClienteException(NULLSTUDENT);			
		}
		if (new_student != null) {
			// Nothing to do.
		}
		else{
			throw new ClienteException(NULLSTUDENT);			
		}
		if (this.inDB(old_student)) {
			// Nothing to do.
		} 
		else{
			throw new ClienteException(STUDENTNOTEXIST);			
		}
		if (!this.inOtherDB(old_student)) {
			// Nothing to do.
		} 
		else{
			throw new ClienteException(STUDENTINUSE);			
		}
		
		if (old_student.getCpf().equals(new_student.getCpf())
				&& this.inDBCpf(new_student.getCpf())) {
			// Nothing to do.
		}
		else{
			throw new ClienteException(EXISTENTCPF);			
		}
		
		if (old_student.getMatricula().equals(
				new_student.getMatricula())
				&& this.inDBRegister(new_student.getMatricula())) {
			// Nothing to do.
		}
		else{
			throw new ClienteException(EXISTENTREGISTER);			
		}

		// Start connection with database
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preopare_query_to_execute;
		
		//try update database
		if (!this.inDB(new_student)) {
			String msg = "UPDATE aluno SET " + "nome = \""
					+ new_student.getNome() + "\", "
					+ "cpf = \"" + new_student.getCpf()
					+ "\", " + "telefone = \""
					+ new_student.getTelefone() + "\", "
					+ "email = \"" + new_student.getEmail()
					+ "\", " + "matricula = \""
					+ new_student.getMatricula() + "\""
					+ " WHERE " + "aluno.nome = \""
					+ old_student.getNome() + "\" and "
					+ "aluno.cpf = \"" + old_student.getCpf()
					+ "\" and " + "aluno.telefone = \""
					+ old_student.getTelefone() + "\" and "
					+ "aluno.email = \""
					+ old_student.getEmail() + "\" and "
					+ "aluno.matricula = \""
					+ old_student.getMatricula() + "\";";
			connection.setAutoCommit(false);
			preopare_query_to_execute = connection.prepareStatement(msg);
			preopare_query_to_execute.executeUpdate();
			connection.commit();
		}
		else {
			throw new ClienteException(EXISTENTSTUDENT);
		}
		
		// Close connection
		preopare_query_to_execute.close();
		connection.close();
	}


	/**
	 * Method to delete data from student
	 * @param student - Student to remove
	 * @throws SQLException happens when sql code is wrong
	 * @throws ClienteException happens when student is null or his code exist
	 */
	public void delete(final Aluno student) throws SQLException, ClienteException {
		// Verify if is valid student.
		if (student != null) {
			//Nothing to do.
		} 
		else {
			throw new ClienteException(NULLSTUDENT);	
		}
		if (!this.inOtherDB(student)) {
			//Nothing to do.
		} 
		else {
			throw new ClienteException(STUDENTINUSE);	
		}
		
		// Try remove student from database
		if (this.inDB(student)) {
			this.updateQuery("DELETE FROM aluno WHERE "
					+ "aluno.nome = \"" + student.getNome() + "\" and "
					+ "aluno.cpf = \"" + student.getCpf() + "\" and "
					+ "aluno.telefone = \"" + student.getTelefone()
					+ "\" and " + "aluno.email = \""
					+ student.getEmail() + "\" and "
					+ "aluno.matricula = \"" + student.getMatricula()
					+ "\";");
		} 
		else {
			throw new ClienteException(STUDENTNOTEXIST);
		}
	}
	
	/**
	 * Method to return all data in database
	 * @return Vector<Aluno> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws ClienteException don't happens
	 */
	public Vector<Aluno> searchAll() throws SQLException, ClienteException {
		return this.search("SELECT * FROM aluno;");
	}

	/**
	 * Method to return all data in database with condition on Student name
	 * @param name String - Restriction in sql consult
	 * @return Vector<Aluno> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws ClienteException don't happens
	 */
	public Vector<Aluno> searchByName(String name) throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM aluno WHERE nome = " + "\"" + name
				+ "\";");
	}
	
	/**
	 * Method to return all data in database with condition on Student cpf
	 * @param cpf String - Restriction in sql consult
	 * @return Vector<Aluno> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws ClienteException don't happens
	 */
	public Vector<Aluno> searchByCpf(String cpf) throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM aluno WHERE cpf = " + "\"" + cpf
				+ "\";");
	}


	/**
	 * Method to return all data in database with condition on Student register
	 * @param register String - Restriction in sql consult
	 * @return Vector<Aluno> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws ClienteException don't happens
	 */
	public Vector<Aluno> searchByRegister(String register) throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM aluno WHERE matricula = " + "\""
				+ register + "\";");
	}


	/**
	 * Method to return all data in database with condition on Student email
	 * @param email String - Restriction in sql consult
	 * @return Vector<Aluno> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws ClienteException don't happens
	 */
	public Vector<Aluno> searcByEmail(String email) throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM aluno WHERE email = " + "\"" + email
				+ "\";");
	}

	/**
	 * Method to return all data in database with condition on Student phone
	 * @param phone String - Restriction in sql consult
	 * @return Vector<Aluno> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws ClienteException don't happens
	 */
	public Vector<Aluno> searchByPhone(String phone) throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM aluno WHERE telefone = " + "\""
				+ phone + "\";");
	}


	/**
	 * Method to return all data in database
	 * @return Vector<Aluno> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 * @throws ClienteException don't happens
	 */
	private Vector<Aluno> search(String query) throws SQLException,
			ClienteException {

		//Start connection
		Connection connnection = FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connnection.prepareStatement(query);
		
		//Query result
		ResultSet database_result = prepare_query_to_execute.executeQuery();

		// Fill vector with the data query result
		Vector<Aluno> data_result = new Vector<Aluno>();
		while (database_result.next()){
			data_result.add(this.fetchAluno(database_result));
		}
	
		//Close connection
		prepare_query_to_execute.close();
		database_result.close();
		connnection.close();
		
		return data_result;
	}

	/**
	 * Method to execute generic consult
	 * @param query String - Select query 
	 * @return Vector<Equipamento> data when all registers
	 * @throws SQLException happens when sql code is wrong
	 */
	private boolean inDBGeneric(String query) throws SQLException {
		
		//Start connection
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		
		// Data return by query
		ResultSet data_result = prepare_query_to_execute.executeQuery();

		// Save if has result
		boolean has_result = data_result.next();
		
		//close connection
		data_result.close();
		prepare_query_to_execute.close();
		connection.close();
		
		return has_result;
	}

	/**
	 * Method to verify if equipment exists
	 * @param student - student to search
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException happens when sql code is wrong
	 */
	private boolean inDB(Aluno student) throws SQLException {
		return this.inDBGeneric("SELECT * FROM aluno WHERE "
				+ "aluno.nome = \"" + student.getNome() + "\" and "
				+ "aluno.cpf = \"" + student.getCpf() + "\" and "
				+ "aluno.telefone = \"" + student.getTelefone() + "\" and "
				+ "aluno.email = \"" + student.getEmail() + "\" and "
				+ "aluno.matricula = \"" + student.getMatricula() + "\";");
	}

	/**
	 * Method to verify if equipment exists using code
	 * @param code - code of equipment
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException happens when sql code is wrong
	 */
	private boolean inDBCpf(String code) throws SQLException {
		return this.inDBGeneric("SELECT * FROM aluno WHERE " + "aluno.cpf = \""
				+ code + "\";");
	}

	/**
	 * Method to verify if equipment exists using register
	 * @param register - code of equipment
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException happens when sql code is wrong
	 */
	private boolean inDBRegister(String register) throws SQLException {
		return this.inDBGeneric("SELECT * FROM aluno WHERE "
				+ "aluno.matricula = \"" + register + "\";");
	}
	
	/**
	 * Method to verify if equipment exists using subquery in database
	 * @param student - student to search
	 * @return boolean - true if exist data in consult or false if not exist
	 * @throws SQLException happens when sql code is wrong
	 */
	private boolean inOtherDB(Aluno student) throws SQLException,
			ClienteException {
		return this.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE "
				+ "id_aluno = (SELECT id_aluno FROM aluno WHERE "
				+ "aluno.nome = \"" + student.getNome() + "\" and "
				+ "aluno.cpf = \"" + student.getCpf() + "\" and "
				+ "aluno.telefone = \"" + student.getTelefone() + "\" and "
				+ "aluno.email = \"" + student.getEmail() + "\" and "
				+ "aluno.matricula = \"" + student.getMatricula() + "\");");
	}

	/**
	 * Method to convert result set in Student
	 * @param equipament_data result set contain equipment data
	 * @return new student with data in result set
	 * @throws ClienteException  will occur if the code is invalid, can no longer exist and not be null
	 * @throws SQLException happens when sql code is wrong
	 */
	private Aluno fetchAluno(ResultSet data_from_student) throws ClienteException,
			SQLException {
		return new Aluno(data_from_student.getString("nome"), data_from_student.getString("cpf"),
				data_from_student.getString("matricula"), data_from_student.getString("telefone"),
				data_from_student.getString("email"));
	}

	/**
	 * Method to update student
	 * @param query - query with update data in database
	 * @throws SQLException happens when sql code is wrong
	 */
	private void updateQuery(String query) throws SQLException {
		// Start connection
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement prepare_query_to_execute = connection.prepareStatement(query);
		
		// Execute uodate query
		prepare_query_to_execute.executeUpdate();
		prepare_query_to_execute.close();
		
		// Close connection
		connection.close();
	}

}
