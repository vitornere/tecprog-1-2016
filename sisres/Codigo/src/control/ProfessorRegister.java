package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.TeacherDAO;
import exception.ClientException;
import model.Professor;

public class ProfessorRegister {

	private Vector<Professor> vectorProfessors = new Vector<Professor>();

	private static ProfessorRegister newProfessor;

	private ProfessorRegister() {
	}

	public static ProfessorRegister getInstance() {
		if (newProfessor != null) {

			// Nothing to do.
		} else {

			newProfessor = new ProfessorRegister();
		}
		return newProfessor;
	}

	public Vector<Professor> searchNameProfessor(String value)
			throws SQLException, ClientException {
		return TeacherDAO.getInstance().searchByName(value);
	}

	public Vector<Professor> searchCpfProfessor(String value)
			throws SQLException, ClientException {
		return TeacherDAO.getInstance().searchByCpf(value);
	}

	public Vector<Professor> searchIdProfessor(String value)
			throws SQLException, ClientException {
		return TeacherDAO.getInstance().searchByRegister(value);
	}

	public Vector<Professor> searchEmailProfessor(String value)
			throws SQLException, ClientException {
		return TeacherDAO.getInstance().searchByEmail(value);
	}

	public Vector<Professor> searchPhoneProfessor(String value)
			throws SQLException, ClientException {
		return TeacherDAO.getInstance().searchByPhone(value);
	}

	public Vector<Professor> getVectorProfessors() throws SQLException,
			ClientException {
		this.vectorProfessors = TeacherDAO.getInstance().searchAll();
		return this.vectorProfessors;
	}

	public void insert(String nameProfessor, String cpfProfessor,
			String idProfessor, String phoneProfessor, String emailProfessor)
			throws ClientException, SQLException {
		Professor professor = new Professor(nameProfessor, cpfProfessor,
				idProfessor, phoneProfessor, emailProfessor);
		TeacherDAO.getInstance().add(professor);
		this.vectorProfessors.add(professor);
	}

	public void change(String nameProfessor, String cpfProfessor,
			String idProfessor, String phoneProfessor, String emailProfessor,
			Professor professor) throws ClientException, SQLException {
		Professor oldTeacherData = new Professor(professor.getNamePerson(),
				professor.getCpfPerson(), professor.getIdRegister(),
				professor.getPhonePerson(), professor.getEmailPerson());
		professor.setNamePerson(nameProfessor);
		professor.setCpfPerson(cpfProfessor);
		professor.setIdPerson(idProfessor);
		professor.setPhonePerson(phoneProfessor);
		professor.setEmailPerson(emailProfessor);
		TeacherDAO.getInstance().change(oldTeacherData, professor);
	}

	public void delete(Professor professor) throws SQLException,
			ClientException {
		TeacherDAO.getInstance().delete(professor);
		this.vectorProfessors.remove(professor);
	}

}
