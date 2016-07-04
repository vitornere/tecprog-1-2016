package model;

import exception.ClientException;

public class Student extends Client {

	private final String EMPTY_ID_STUDENT = "Matricula em Branco.";
	private final String NULL_ID_STUDENT = "Matricula esta Nula.";

	public Student(String nameStudent, String cpfStudent, String idStudent,
			String phoneStudent, String emailStudent) throws ClientException {
		super(nameStudent, cpfStudent, idStudent, phoneStudent, emailStudent);
	}

	public void setIdPerson(String idStudent) throws ClientException {
		if (idStudent != null) {
			// Nothing to do
		} else {
			throw new ClientException(NULL_ID_STUDENT);
		}
		if (!"".equals(idStudent)) {
			// Nothing to do
		} else {
			throw new ClientException(EMPTY_ID_STUDENT);
		}
		super.idClient = idStudent;
	}
}
