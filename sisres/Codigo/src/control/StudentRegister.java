package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.StudentDAO;
import exception.ClientException;
import model.Student;

public class StudentRegister {

	private Vector<Student> vectorStudents = new Vector<Student>();

	private static StudentRegister newStudent;

	private StudentRegister() {
	}

	public static StudentRegister getInstance() {
		if (newStudent != null) {

			// Nothing to do.
		} else {

			newStudent = new StudentRegister();
		}

		return newStudent;
	}

	public Vector<Student> searchNameStudent(String value) throws SQLException,
			ClientException {
		return StudentDAO.getInstance().searchByName(value);
	}

	public Vector<Student> searchCpfStudent(String value) throws SQLException,
			ClientException {
		return StudentDAO.getInstance().searchByCpf(value);
	}

	public Vector<Student> searchIdStudent(String value) throws SQLException,
			ClientException {
		return StudentDAO.getInstance().searchByRegister(value);
	}

	public Vector<Student> searchEmailStudent(String value)
			throws SQLException, ClientException {
		return StudentDAO.getInstance().searcByEmail(value);
	}

	public Vector<Student> searchPhoneStudent(String value)
			throws SQLException, ClientException {
		return StudentDAO.getInstance().searchByPhone(value);
	}

	public Vector<Student> getVectorStudents() throws SQLException,
			ClientException {
		this.vectorStudents = StudentDAO.getInstance().searchAll();
		return this.vectorStudents;
	}

	public void insert(String nameStudent, String cpfStudent, String idStudent,
			String phoneStudent, String emailStudent) throws ClientException,
			SQLException {
		Student student = new Student(nameStudent, cpfStudent, idStudent,
				phoneStudent, emailStudent);
		StudentDAO.getInstance().add(student);
		this.vectorStudents.add(student);
	}

	public void update(String nameStudent, String cpfStudent, String idStudent,
			String phoneStudent, String emailStudent, Student student)
			throws ClientException, SQLException {
		Student oldStudentData = new Student(student.getNamePerson(),
				student.getCpfPerson(), student.getIdRegister(),
				student.getPhonePerson(), student.getEmailPerson());
		student.setNamePerson(nameStudent);
		student.setCpfPerson(cpfStudent);
		student.setIdPerson(idStudent);
		student.setPhonePerson(phoneStudent);
		student.setEmailPerson(emailStudent);
		StudentDAO.getInstance().change(oldStudentData, student);
	}

	public void delete(Student student) throws SQLException, ClientException {
		StudentDAO.getInstance().delete(student);
		this.vectorStudents.remove(student);
	}

}
