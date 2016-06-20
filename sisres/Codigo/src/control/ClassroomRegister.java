/**
 * Name: ClassroomRegister.java
 * Class of register classroom.
 */
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

	/**
	 * Create for classroom if there is no.
	 */
	public static ClassroomRegister getInstance() {

		if (classroom != null) {
			// Nothing to do.
		} else {

			classroom = new ClassroomRegister();
		}
		return classroom;
	}

	/**
	 * Classroom list.
	 */
	public Vector<Classroom> getVectorClassroom() throws SQLException,
			PatrimonyException {
		this.vectorClassroom = ClassroomDAO.getInstance().searchAll();
		return this.vectorClassroom;
	}

	/**
	 * Insert classroom.
	 * @param idClassroom, descriptionClassroom, capacity
	 */
	public void insert(String idClassroom, String descriptionClassroom,
			String capacity) throws PatrimonyException, SQLException {
		Classroom classroom = new Classroom(idClassroom, descriptionClassroom,
				capacity);
		ClassroomDAO.getInstance().add(classroom);
		this.vectorClassroom.add(classroom);
	}

	/**
	 * Update classroom.
	 * @param idClassroom, descriptionClassroom, capacity
	 */
	public void update(String idClassroom, String descriptionClassroom,
			String capacity, Classroom classroom) throws PatrimonyException,
			SQLException {
		Classroom oldClassroomDate = new Classroom(classroom.getIdEquipment(),
				classroom.getDescriptionEquipment(), classroom.getCapacity());
		classroom.setIdEquipment(idClassroom);
		classroom.setDescriptionEquipment(descriptionClassroom);
		classroom.setCapacity(capacity);
		ClassroomDAO.getInstance().change(oldClassroomDate, classroom);
	}

	/**
	 * Delete classroom.
	 * @param classroom
	 */
	public void delete(Classroom classroom) throws SQLException,
			PatrimonyException {
		ClassroomDAO.getInstance().delete(classroom);
		this.vectorClassroom.remove(classroom);
	}

}
