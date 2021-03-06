/*
 * File: TeacherDAO.java
 * Description: Class to make transactions with the database to crud teacher
 * */

package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Professor;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

public class TeacherDAO extends DAO {

	// Mensagens
	private static final String EXISTENTTEACHER = "O Professor ja esta cadastrado.";
	private static final String TEACHERNOTEXISTENT = "O Professor nao esta cadastrado.";
	private static final String NULLTEACHER = "O Professor esta nulo.";
	private static final String ROOMINUSE = "Sala esta sendo utilizada em uma reserva.";
	private static final String EXISTENTCPF = "Ja existe um professor cadastrado com esse CPF.";
	private static final String EXISTENTREGISTER = "Ja existe um professor cadastrado com essa matricula.";

	// Singleton
	private static TeacherDAO instance;

	private TeacherDAO() {
	}

	/**
	 * Method to return a current instance
	 * 
	 * @return current instance or a new instance
	 */
	public static TeacherDAO getInstance() {
		if (instance == null) {
			instance = new TeacherDAO();
		} else {
			// Nothing to do.
		}

		return instance;
	}

	/**
	 * Method to add professor in the database
	 * 
	 * @param teacher
	 *            - Object to add in database
	 * @throws SQLException
	 *             happens when sql code is wrong
	 * @throws ClientException
	 *             happens when teacher is null or his code exist
	 */
	public void add(Professor teacher) throws SQLException, ClientException {
		verifyIsValidProfessor(teacher);

		this.updateQuery("INSERT INTO "
				+ "professor (nome, cpf, telefone, email, matricula) VALUES ("
				+ "\"" + teacher.getNamePerson() + "\", " + "\""
				+ teacher.getCpfPerson() + "\", " + "\""
				+ teacher.getPhonePerson() + "\", " + "\""
				+ teacher.getEmailPerson() + "\", " + "\""
				+ teacher.getIdRegister() + "\"); ");
	}

	/**
	 * Method to Update data from student
	 * 
	 * @param old_teacher
	 *            - Professor to update data
	 * @param new_teacher
	 *            - Professor with updated data
	 * @throws SQLException
	 *             happens when sql code is wrong
	 * @throws ClientException
	 *             happens when teacher is null or his code exist
	 */
	public void change(Professor old_teacher, Professor new_teacher)
			throws SQLException, ClientException {

		verifyIfIsNullTeacher(old_teacher);
		verifyIfIsNullTeacher(new_teacher);

		if (openConnection()) {
			PreparedStatement prepare_query;

			if (!this.inDB(old_teacher)) {
				throw new ClientException(TEACHERNOTEXISTENT);
			} else {
				if (this.inOtherDB(old_teacher)) {
					throw new ClientException(ROOMINUSE);
				} else {
					if (!old_teacher.getCpfPerson().equals(
							new_teacher.getCpfPerson())
							&& this.inDBCpf(new_teacher.getCpfPerson())) {
						throw new ClientException(EXISTENTCPF);
					} else {
						if (!old_teacher.getIdRegister().equals(
								new_teacher.getIdRegister())
								&& this.inDBMatricula(new_teacher
										.getIdRegister())) {
							throw new ClientException(EXISTENTREGISTER);
						} else {
							if (!this.inDB(new_teacher)) {
								String msg = "UPDATE professor SET "
										+ "nome = \""
										+ new_teacher.getNamePerson()
										+ "\", "
										+ "cpf = \""
										+ new_teacher.getCpfPerson()
										+ "\", "
										+ "telefone = \""
										+ new_teacher.getPhonePerson()
										+ "\", "
										+ "email = \""
										+ new_teacher.getEmailPerson()
										+ "\", "
										+ "matricula = \""
										+ new_teacher.getIdRegister()
										+ "\""
										+ " WHERE "
										+ "professor.nome = \""
										+ old_teacher.getNamePerson()
										+ "\" and "
										+ "professor.cpf = \""
										+ old_teacher.getCpfPerson()
										+ "\" and "
										+ "professor.telefone = \""
										+ old_teacher.getPhonePerson()
										+ "\" and "
										+ "professor.email = \""
										+ old_teacher.getEmailPerson()
										+ "\" and "
										+ "professor.matricula = \""
										+ old_teacher.getIdRegister() + "\";";
								setAutoCommit(false);
								prepare_query = prepareStatement(msg);
								prepare_query.executeUpdate();
								commit();
							} else {
								throw new ClientException(EXISTENTTEACHER);
							}
						}
					}
				}
			}
			prepare_query.close();
			closeConnection();
		}
	}

	/**
	 * Method to delete data from teacher
	 * 
	 * @param teacher
	 *            - teacher to remove
	 * @throws SQLException
	 *             happens when sql code is wrong
	 * @throws ClientException
	 *             happens when teacher is null or his code exist
	 */
	public void delete(Professor teacher) throws SQLException, ClientException {
		if (teacher == null) {
			throw new ClientException(NULLTEACHER);
		}
		if (this.inOtherDB(teacher)) {
			throw new ClientException(ROOMINUSE);
		} else {
			if (this.inDB(teacher)) {
				this.updateQuery("DELETE FROM professor WHERE "
						+ "professor.nome = \"" + teacher.getNamePerson()
						+ "\" and " + "professor.cpf = \""
						+ teacher.getCpfPerson() + "\" and "
						+ "professor.telefone = \"" + teacher.getPhonePerson()
						+ "\" and " + "professor.email = \""
						+ teacher.getEmailPerson() + "\" and "
						+ "professor.matricula = \"" + teacher.getIdRegister()
						+ "\";");
			} else {
				throw new ClientException(TEACHERNOTEXISTENT);
			}
		}
	}

	public Vector<Professor> searchAll() throws SQLException, ClientException {
		return this.search("SELECT * FROM professor;");
	}

	public Vector<Professor> searchByName(String name) throws SQLException,
			ClientException {
		return this.search("SELECT * FROM professor WHERE nome = " + "\""
				+ name + "\";");
	}

	public Vector<Professor> searchByCpf(String cpf) throws SQLException,
			ClientException {
		return this.search("SELECT * FROM professor WHERE cpf = " + "\"" + cpf
				+ "\";");
	}

	public Vector<Professor> searchByRegister(String register)
			throws SQLException, ClientException {
		return this.search("SELECT * FROM professor WHERE matricula = " + "\""
				+ register + "\";");
	}

	public Vector<Professor> searchByEmail(String email) throws SQLException,
			ClientException {
		return this.search("SELECT * FROM professor WHERE email = " + "\""
				+ email + "\";");
	}

	public Vector<Professor> searchByPhone(String phone) throws SQLException,
			ClientException {
		return this.search("SELECT * FROM professor WHERE telefone = " + "\""
				+ phone + "\";");
	}

	/**
	 * Metodos Privados
	 * */

	protected Vector<Professor> search(String query) throws SQLException,
			ClientException {
		Vector<Professor> vet = new Vector<Professor>();

		if (openConnection()) {

			PreparedStatement prepare_query = prepareStatement(query);
			ResultSet rs = prepare_query.executeQuery();

			while (rs.next()) {
				vet.add(this.fetchProfessor(rs));
			}
			prepare_query.close();
			rs.close();
			closeConnection();
		} else {
			System.exit(1);
		}
		return vet;
	}

	protected boolean inDBGeneric(String query) throws SQLException {
		boolean inDb = false;
		if (openConnection()) {
			PreparedStatement prepare_query = prepareStatement(query);
			ResultSet query_result = prepare_query.executeQuery();

			inDb = query_result.next();
			query_result.close();
			prepare_query.close();
			closeConnection();
		}
		return inDb;
	}

	private boolean inDB(Professor teacher) throws SQLException {
		return this.inDBGeneric("SELECT * FROM professor WHERE "
				+ "professor.nome = \"" + teacher.getNamePerson() + "\" and "
				+ "professor.cpf = \"" + teacher.getCpfPerson() + "\" and "
				+ "professor.telefone = \"" + teacher.getPhonePerson()
				+ "\" and " + "professor.email = \"" + teacher.getEmailPerson()
				+ "\" and " + "professor.matricula = \""
				+ teacher.getIdRegister() + "\";");
	}

	private boolean inDBCpf(String code) throws SQLException {
		return this.inDBGeneric("SELECT * FROM professor WHERE " + "cpf = \""
				+ code + "\";");
	}

	private boolean inDBMatricula(String code) throws SQLException {
		return this.inDBGeneric("SELECT * FROM professor WHERE "
				+ "matricula = \"" + code + "\";");
	}

	private boolean inOtherDB(Professor teacher) throws SQLException {
		if (this.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE "
				+ "id_professor = (SELECT id_professor FROM professor WHERE "
				+ "professor.nome = \"" + teacher.getNamePerson() + "\" and "
				+ "professor.cpf = \"" + teacher.getCpfPerson() + "\" and "
				+ "professor.telefone = \"" + teacher.getPhonePerson()
				+ "\" and " + "professor.email = \"" + teacher.getEmailPerson()
				+ "\" and " + "professor.matricula = \""
				+ teacher.getIdRegister() + "\");") == false) {
			if (this.inDBGeneric("SELECT * FROM reserva_equipamento WHERE "
					+ "id_professor = (SELECT id_professor FROM professor WHERE "
					+ "professor.nome = \"" + teacher.getNamePerson()
					+ "\" and " + "professor.cpf = \"" + teacher.getCpfPerson()
					+ "\" and " + "professor.telefone = \""
					+ teacher.getPhonePerson() + "\" and "
					+ "professor.email = \"" + teacher.getEmailPerson()
					+ "\" and " + "professor.matricula = \""
					+ teacher.getIdRegister() + "\");") == false) {
				return false;
			}
		}

		return true;
	}

	private Professor fetchProfessor(ResultSet teacher_data)
			throws ClientException, SQLException {
		return new Professor(teacher_data.getString("nome"),
				teacher_data.getString("cpf"),
				teacher_data.getString("matricula"),
				teacher_data.getString("telefone"),
				teacher_data.getString("email"));
	}

	protected void updateQuery(String messenger) throws SQLException {
		if(openConnection())
		{
		PreparedStatement prepare_query = prepareStatement(messenger);
		prepare_query.executeUpdate();
		prepare_query.close();
		closeConnection();
		}
		else
		{
			System.exit(1);
		}
	}

	/**
	 * Verify if Professor object is valid
	 * 
	 * @param teacher
	 * @throws ClientException
	 *             occurs if professor is null or this cpf has in database
	 * @throws SQLException
	 *             query error
	 */
	private void verifyIsValidProfessor(Professor teacher)
			throws ClientException, SQLException {
		verifyIfIsNullTeacher(teacher);

		if (this.inDBCpf(teacher.getCpfPerson())) {
			throw new ClientException(EXISTENTCPF);
		} else {
			if (this.inDBMatricula(teacher.getIdRegister())) {
				throw new ClientException(EXISTENTREGISTER);
			}
		}
	}

	/**
	 * Verify if Professor is a null object
	 * 
	 * @param teacher
	 * @throws ClientException
	 *             occurs if professor is null or this cpf has in database
	 * @throws SQLException
	 *             query error
	 */
	private void verifyIfIsNullTeacher(Professor teacher)
			throws ClientException {
		if (teacher == null) {
			throw new ClientException(NULLTEACHER);
		}

	}

	@Override
	protected Object fetch(ResultSet rs) throws SQLException, ClientException,
			PatrimonyException, ReserveException {
		// TODO Auto-generated method stub
		return null;
	}

}
