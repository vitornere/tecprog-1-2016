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

public class TeacherDAO {

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
		}
		else {
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
		if (teacher == null) {
			throw new ClientException(NULLTEACHER);
		}
		else {
			if (this.inDBCpf(teacher.getCpfPerson())) {
				throw new ClientException(EXISTENTCPF);
			}
			else {
				if (this.inDBMatricula(teacher.getIdRegister())) {
					throw new ClientException(EXISTENTREGISTER);
				}
			}
		}
		this.updateQuery("INSERT INTO "
				+ "professor (nome, cpf, telefone, email, matricula) VALUES ("
				+ "\"" + teacher.getNamePerson() + "\", " + "\"" + teacher.getCpfPerson()
				+ "\", " + "\"" + teacher.getPhonePerson() + "\", " + "\""
				+ teacher.getEmailPerson() + "\", " + "\"" + teacher.getIdRegister()
				+ "\"); ");
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
		if (old_teacher == null) {
			throw new ClientException(NULLTEACHER);
		} 
		else {
			// Nothing to do.
		}
		if (new_teacher == null) {
			throw new ClientException(NULLTEACHER);
		}
		else {
			// Nothing to do.

		}
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;

		if (!this.inDB(old_teacher)) {
			throw new ClientException(TEACHERNOTEXISTENT);
		} 
		else {
			if (this.inOtherDB(old_teacher)) {
				throw new ClientException(ROOMINUSE);
			}
			else {
				if (!old_teacher.getCpfPerson().equals(new_teacher.getCpfPerson())
						&& this.inDBCpf(new_teacher.getCpfPerson())) {
					throw new ClientException(EXISTENTCPF);
				}
				else {
					if (!old_teacher.getIdRegister().equals(
							new_teacher.getIdRegister())
							&& this.inDBMatricula(new_teacher.getIdRegister())) {
						throw new ClientException(EXISTENTREGISTER);
					}
					else {
						if (!this.inDB(new_teacher)) {
							String msg = "UPDATE professor SET " + "nome = \""
									+ new_teacher.getNamePerson() + "\", "
									+ "cpf = \"" + new_teacher.getCpfPerson()
									+ "\", " + "telefone = \""
									+ new_teacher.getPhonePerson() + "\", "
									+ "email = \"" + new_teacher.getEmailPerson()
									+ "\", " + "matricula = \""
									+ new_teacher.getIdRegister() + "\""
									+ " WHERE " + "professor.nome = \""
									+ old_teacher.getNamePerson() + "\" and "
									+ "professor.cpf = \""
									+ old_teacher.getCpfPerson() + "\" and "
									+ "professor.telefone = \""
									+ old_teacher.getPhonePerson() + "\" and "
									+ "professor.email = \""
									+ old_teacher.getEmailPerson() + "\" and "
									+ "professor.matricula = \""
									+ old_teacher.getIdRegister() + "\";";
							con.setAutoCommit(false);
							pst = con.prepareStatement(msg);
							pst.executeUpdate();
							con.commit();
						} 
						else {
							throw new ClientException(EXISTENTTEACHER);
						}
					}
				}
			}
		}
		pst.close();
		con.close();
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
		} 
		else {
			if (this.inDB(teacher)) {
				this.updateQuery("DELETE FROM professor WHERE "
						+ "professor.nome = \"" + teacher.getNamePerson() + "\" and "
						+ "professor.cpf = \"" + teacher.getCpfPerson() + "\" and "
						+ "professor.telefone = \"" + teacher.getPhonePerson()
						+ "\" and " + "professor.email = \"" + teacher.getEmailPerson()
						+ "\" and " + "professor.matricula = \""
						+ teacher.getIdRegister() + "\";");
			}
			else {
				throw new ClientException(TEACHERNOTEXISTENT);
			}
		}
	}

	public Vector<Professor> searchAll() throws SQLException,
			ClientException {
		return this.search("SELECT * FROM professor;");
	}

	public Vector<Professor> searchByName(String name) throws SQLException,
			ClientException {
		return this.search("SELECT * FROM professor WHERE nome = " + "\""
				+ name + "\";");
	}

	public Vector<Professor> searchByCpf(String cpf) throws SQLException,
			ClientException {
		return this.search("SELECT * FROM professor WHERE cpf = " + "\""
				+ cpf + "\";");
	}

	public Vector<Professor> searchByRegister(String register) throws SQLException,
			ClientException {
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

	private Vector<Professor> search(String query) throws SQLException,
			ClientException {
		Vector<Professor> vet = new Vector<Professor>();

		Connection con = FactoryConnection.getInstance().getConnection();

		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			vet.add(this.fetchProfessor(rs));
		}
		pst.close();
		rs.close();
		con.close();
		return vet;
	}

	private boolean inDBGeneric(String query) throws SQLException {
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		if (!rs.next()) {
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

	private boolean inDB(Professor teacher) throws SQLException {
		return this.inDBGeneric("SELECT * FROM professor WHERE "
				+ "professor.nome = \"" + teacher.getNamePerson() + "\" and "
				+ "professor.cpf = \"" + teacher.getCpfPerson() + "\" and "
				+ "professor.telefone = \"" + teacher.getPhonePerson() + "\" and "
				+ "professor.email = \"" + teacher.getEmailPerson() + "\" and "
				+ "professor.matricula = \"" + teacher.getIdRegister() + "\";");
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
				+ "professor.telefone = \"" + teacher.getPhonePerson() + "\" and "
				+ "professor.email = \"" + teacher.getEmailPerson() + "\" and "
				+ "professor.matricula = \"" + teacher.getIdRegister() + "\");") == false) {
			if (this.inDBGeneric("SELECT * FROM reserva_equipamento WHERE "
					+ "id_professor = (SELECT id_professor FROM professor WHERE "
					+ "professor.nome = \"" + teacher.getNamePerson() + "\" and "
					+ "professor.cpf = \"" + teacher.getCpfPerson() + "\" and "
					+ "professor.telefone = \"" + teacher.getPhonePerson()
					+ "\" and " + "professor.email = \"" + teacher.getEmailPerson()
					+ "\" and " + "professor.matricula = \""
					+ teacher.getIdRegister() + "\");") == false) {
				return false;
			}
		}

		return true;
	}

	private Professor fetchProfessor(ResultSet teacher_data) throws ClientException,
			SQLException {
		return new Professor(teacher_data.getString("nome"), teacher_data.getString("cpf"),
				teacher_data.getString("matricula"), teacher_data.getString("telefone"),
				teacher_data.getString("email"));
	}

	private void updateQuery(String messenger) throws SQLException {
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(messenger);
		pst.executeUpdate();
		pst.close();
		con.close();
	}

}
