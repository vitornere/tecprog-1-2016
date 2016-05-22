package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.ProfessorDAO;
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
		return ProfessorDAO.getNewProfessor().searchNameProfessor(value);
	}

	public Vector<Professor> searchCpfProfessor(String value)
			throws SQLException, ClientException {
		return ProfessorDAO.getNewProfessor().searchCpfProfessor(value);
	}

	public Vector<Professor> searchIdProfessor(String value)
			throws SQLException, ClientException {
		return ProfessorDAO.getNewProfessor().searchIdProfessor(value);
	}

	public Vector<Professor> searchEmailProfessor(String value)
			throws SQLException, ClientException {
		return ProfessorDAO.getNewProfessor().searchEmailProfessor(value);
	}

	public Vector<Professor> searchPhoneProfessor(String value)
			throws SQLException, ClientException {
		return ProfessorDAO.getNewProfessor().searchPhoneProfessor(value);
	}

	public Vector<Professor> getVectorProfessors() throws SQLException,
			ClientException {
		this.vectorProfessors = ProfessorDAO.getNewProfessor().seachAll();
		return this.vectorProfessors;
	}

	public void insert(String nameProfessor, String cpfProfessor,
			String idProfessor, String phoneProfessor, String emailProfessor)
			throws ClientException, SQLException {
		Professor professor = new Professor(nameProfessor, cpfProfessor,
				idProfessor, phoneProfessor, emailProfessor);
		ProfessorDAO.getNewProfessor().include(professor);
		this.vectorProfessors.add(professor);
	}

	public void update(String nameProfessor, String cpfProfessor,
			String idProfessor, String phoneProfessor, String emailProfessor,
			Professor professor) throws ClientException, SQLException {
		Professor oldTeacherData = new Professor(professor.getName(),
				professor.getCpfProfessor(), professor.getIdProfessor(),
				professor.getPhoneProfessor(), professor.getEmailProfessor());
		professor.setName(nameProfessor);
		professor.setCpfProfessor(cpfProfessor);
		professor.setIdProfessor(idProfessor);
		professor.setPhoneProfessor(phoneProfessor);
		professor.setEmailProfessor(emailProfessor);
		ProfessorDAO.getNewProfessor().update(oldTeacherData, professor);
	}

	public void delete(Professor professor) throws SQLException,
			ClientException {
		ProfessorDAO.getNewProfessor().delete(professor);
		this.vectorProfessors.remove(professor);
	}

}
