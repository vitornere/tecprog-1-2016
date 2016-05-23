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
import exception.ClienteException;

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

	public static TeacherDAO getInstance() {
		if (instance == null) {
			instance = new TeacherDAO();
		}
		else {
			// Nothing to do.
		}

		return instance;
	}

	//

	public void add(Professor teacher) throws SQLException, ClienteException {
		if (teacher == null) {
			throw new ClienteException(NULLTEACHER);
		}
		else {
			if (this.inDBCpf(teacher.getCpf())) {
				throw new ClienteException(EXISTENTCPF);
			}
			else {
				if (this.inDBMatricula(teacher.getMatricula())) {
					throw new ClienteException(EXISTENTREGISTER);
				}
			}
		}
		this.updateQuery("INSERT INTO "
				+ "professor (nome, cpf, telefone, email, matricula) VALUES ("
				+ "\"" + teacher.getNome() + "\", " + "\"" + teacher.getCpf()
				+ "\", " + "\"" + teacher.getTelefone() + "\", " + "\""
				+ teacher.getEmail() + "\", " + "\"" + teacher.getMatricula()
				+ "\"); ");
	}

	public void change(Professor old_teacher, Professor new_teacher)
			throws SQLException, ClienteException {
		if (old_teacher == null) {
			throw new ClienteException(NULLTEACHER);
		} 
		else {
			// Nothing to do.
		}
		if (new_teacher == null) {
			throw new ClienteException(NULLTEACHER);
		}
		else {
			// Nothing to do.

		}
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;

		if (!this.inDB(old_teacher)) {
			throw new ClienteException(TEACHERNOTEXISTENT);
		} 
		else {
			if (this.inOtherDB(old_teacher)) {
				throw new ClienteException(ROOMINUSE);
			}
			else {
				if (!old_teacher.getCpf().equals(new_teacher.getCpf())
						&& this.inDBCpf(new_teacher.getCpf())) {
					throw new ClienteException(EXISTENTCPF);
				}
				else {
					if (!old_teacher.getMatricula().equals(
							new_teacher.getMatricula())
							&& this.inDBMatricula(new_teacher.getMatricula())) {
						throw new ClienteException(EXISTENTREGISTER);
					}
					else {
						if (!this.inDB(new_teacher)) {
							String msg = "UPDATE professor SET " + "nome = \""
									+ new_teacher.getNome() + "\", "
									+ "cpf = \"" + new_teacher.getCpf()
									+ "\", " + "telefone = \""
									+ new_teacher.getTelefone() + "\", "
									+ "email = \"" + new_teacher.getEmail()
									+ "\", " + "matricula = \""
									+ new_teacher.getMatricula() + "\""
									+ " WHERE " + "professor.nome = \""
									+ old_teacher.getNome() + "\" and "
									+ "professor.cpf = \""
									+ old_teacher.getCpf() + "\" and "
									+ "professor.telefone = \""
									+ old_teacher.getTelefone() + "\" and "
									+ "professor.email = \""
									+ old_teacher.getEmail() + "\" and "
									+ "professor.matricula = \""
									+ old_teacher.getMatricula() + "\";";
							con.setAutoCommit(false);
							pst = con.prepareStatement(msg);
							pst.executeUpdate();
							con.commit();
						} 
						else {
							throw new ClienteException(EXISTENTTEACHER);
						}
					}
				}
			}
		}
		pst.close();
		con.close();
	}

	public void delete(Professor teacher) throws SQLException, ClienteException {
		if (teacher == null) {
			throw new ClienteException(NULLTEACHER);
		}
		if (this.inOtherDB(teacher)) {
			throw new ClienteException(ROOMINUSE);
		} 
		else {
			if (this.inDB(teacher)) {
				this.updateQuery("DELETE FROM professor WHERE "
						+ "professor.nome = \"" + teacher.getNome() + "\" and "
						+ "professor.cpf = \"" + teacher.getCpf() + "\" and "
						+ "professor.telefone = \"" + teacher.getTelefone()
						+ "\" and " + "professor.email = \"" + teacher.getEmail()
						+ "\" and " + "professor.matricula = \""
						+ teacher.getMatricula() + "\";");
			}
			else {
				throw new ClienteException(TEACHERNOTEXISTENT);
			}
		}
	}

	public Vector<Professor> searchAll() throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM professor;");
	}

	public Vector<Professor> searchByName(String name) throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM professor WHERE nome = " + "\""
				+ name + "\";");
	}

	public Vector<Professor> searchByCpf(String cpf) throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM professor WHERE cpf = " + "\""
				+ cpf + "\";");
	}

	public Vector<Professor> searchByRegister(String register) throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM professor WHERE matricula = " + "\""
				+ register + "\";");
	}

	public Vector<Professor> searchByEmail(String email) throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM professor WHERE email = " + "\""
				+ email + "\";");
	}

	public Vector<Professor> searchByPhone(String phone) throws SQLException,
			ClienteException {
		return this.search("SELECT * FROM professor WHERE telefone = " + "\""
				+ phone + "\";");
	}

	/**
	 * Metodos Privados
	 * */

	private Vector<Professor> search(String query) throws SQLException,
			ClienteException {
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
				+ "professor.nome = \"" + teacher.getNome() + "\" and "
				+ "professor.cpf = \"" + teacher.getCpf() + "\" and "
				+ "professor.telefone = \"" + teacher.getTelefone() + "\" and "
				+ "professor.email = \"" + teacher.getEmail() + "\" and "
				+ "professor.matricula = \"" + teacher.getMatricula() + "\";");
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
				+ "professor.nome = \"" + teacher.getNome() + "\" and "
				+ "professor.cpf = \"" + teacher.getCpf() + "\" and "
				+ "professor.telefone = \"" + teacher.getTelefone() + "\" and "
				+ "professor.email = \"" + teacher.getEmail() + "\" and "
				+ "professor.matricula = \"" + teacher.getMatricula() + "\");") == false) {
			if (this.inDBGeneric("SELECT * FROM reserva_equipamento WHERE "
					+ "id_professor = (SELECT id_professor FROM professor WHERE "
					+ "professor.nome = \"" + teacher.getNome() + "\" and "
					+ "professor.cpf = \"" + teacher.getCpf() + "\" and "
					+ "professor.telefone = \"" + teacher.getTelefone()
					+ "\" and " + "professor.email = \"" + teacher.getEmail()
					+ "\" and " + "professor.matricula = \""
					+ teacher.getMatricula() + "\");") == false) {
				return false;
			}
		}

		return true;
	}

	private Professor fetchProfessor(ResultSet teacher_data) throws ClienteException,
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
