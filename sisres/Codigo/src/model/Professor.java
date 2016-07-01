package model;

import exception.ClientException;
import exception.PatrimonyException;

public class Professor extends Client {

	private final String EMPTY_ID_PROFESSOR = "Matricula em Branco.";
	private final String NULL_ID_PROFESSOR = "Matricula esta Nula.";

	public Professor(String nameProfessor, String cpfProfessor,
			String idProfessor, String phoneProfessor, String emailProfessor)
			throws ClientException {
		super(nameProfessor, cpfProfessor, idProfessor, phoneProfessor,
				emailProfessor);
	}

	public void setIdPerson(String idProfessor) throws ClientException {
		if ((idProfessor != null) && (!("".equals(idProfessor.trim())))) {
			super.idClient = idProfessor;
		} else if (idProfessor == null) {
			throw new ClientException(NULL_ID_PROFESSOR);
		} else {
			throw new ClientException(EMPTY_ID_PROFESSOR);
		}
	}

}
