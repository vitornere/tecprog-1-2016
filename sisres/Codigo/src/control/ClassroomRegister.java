package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.ClassroomDAO;
import exception.PatrimonyException;
import model.Classroom;

public class ClassroomRegister {

	private Vector<Classroom> vectorClassroom = new Vector<Classroom>();

	private static ClassroomRegister classroom;

	private ClassroomRegister() {
	}

	public static ClassroomRegister getClassroom() {

		if (classroom != null) {
			// Nothing to do.
		} else {

			classroom = new ClassroomRegister();
		}
		return classroom;
	}

	public Vector<Classroom> getVectorClassroom() throws SQLException,
			PatrimonyException {
		this.vectorClassroom = ClassroomDAO.getClassroom().searchAll();
		return this.vectorClassroom;
	}

	public void insert(String idClassroom, String descriptionClassroom,
			String capacity) throws PatrimonyException, SQLException {
		Classroom classroom = new Classroom(idClassroom, descriptionClassroom,
				capacity);
		ClassroomDAO.getClassroom().include(classroom);
		this.vectorClassroom.add(classroom);
	}

	public void update(String idClassroom, String descriptionClassroom,
			String capacity, Classroom classroom) throws PatrimonyException,
			SQLException {
		Classroom oldClassroomDate = new Classroom(classroom.getIdEquipment(),
				classroom.getDescriptionEquipment(), classroom.getCapacity());
		classroom.setIdEquipment(idClassroom);
		classroom.setDescriptionEquipment(descriptionClassroom);
		classroom.setCapacity(capacity);
		ClassroomDAO.getClassroom().update(oldClassroomDate, classroom);
	}

	public void delete(Classroom classroom) throws SQLException,
			PatrimonyException {
		ClassroomDAO.getClassroom().delete(classroom);
		this.vectorClassroom.remove(classroom);
	}

}
