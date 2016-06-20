package control;

import java.sql.SQLException;
import java.util.Vector;

import model.Student;
import model.ReserveClassroomForStudent;
import model.Classroom;
import persistence.ReservationRoomForStudentDAO;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

public class ReserveClassroomForStudentRegister {

	private Vector<ReserveClassroomForStudent> vectorReserveClassroomForStudent = new Vector<ReserveClassroomForStudent>();

	private static ReserveClassroomForStudentRegister reserveClassroomForStudent;

	private ReserveClassroomForStudentRegister() {
	}

	public static ReserveClassroomForStudentRegister getInstance() {
		if (reserveClassroomForStudent != null) {

			// Nothing to do.
		} else {
			reserveClassroomForStudent = new ReserveClassroomForStudentRegister();
		}

		return reserveClassroomForStudent;
	}

	/**
	 * Reserve Classroom list for hour.
	 */
	public Vector<ReserveClassroomForStudent> getHoursReserved(String hour)
			throws SQLException, PatrimonyException, ClientException,
			ReserveException {
		return ReservationRoomForStudentDAO.getInstance()
				.searchByHour(hour);

	}

	/**
	 * Reserve Classroom list for date.
	 */
	public Vector<ReserveClassroomForStudent> getMonthReservations(String date)
			throws SQLException, PatrimonyException, ClientException,
			ReserveException {
		return ReservationRoomForStudentDAO.getInstance()
				.searchbyDay(date);
	}
	
	/**
	 * Reserve Classroom list.
	 */
	public Vector<ReserveClassroomForStudent> getVectorReserveClassroomForStudent()
			throws SQLException, PatrimonyException, ClientException,
			ReserveException {
		this.vectorReserveClassroomForStudent = ReservationRoomForStudentDAO
				.getInstance().searchAll();
		return this.vectorReserveClassroomForStudent;
	}

	/**
	 * Verify if exist chairs.
	 */
	public int chairsAvailable(Classroom classroom, String date,
			String hourReserveClassroomForStudent) throws SQLException,
			PatrimonyException, ClientException, ReserveException {
		return ReservationRoomForStudentDAO.getInstance()
				.availableChair(classroom, date,
						hourReserveClassroomForStudent);
	}

	public void insert(Classroom classroom, Student student, String date,
			String hour, String finality, String reservedChairs)
			throws SQLException, ReserveException, ClientException,
			PatrimonyException {

		ReserveClassroomForStudent reserveClassroomForStudent = new ReserveClassroomForStudent(
				date, hour, classroom, finality, reservedChairs, student);
		ReservationRoomForStudentDAO.getInstance().add(
				reserveClassroomForStudent);
		this.vectorReserveClassroomForStudent.add(reserveClassroomForStudent);
	}

	public void update(String finality, String reservedChairs,
			ReserveClassroomForStudent reserveClassroomForStudent)
			throws SQLException, ReserveException, ClientException,
			PatrimonyException {

		ReserveClassroomForStudent oldReserveClassroomForStudentData = new ReserveClassroomForStudent(
				reserveClassroomForStudent.getDate(),
				reserveClassroomForStudent.getHour(),
				reserveClassroomForStudent.getClassroom(),
				reserveClassroomForStudent.getFinality(),
				reserveClassroomForStudent.getReservedChairs(),
				reserveClassroomForStudent.getStudent());
		reserveClassroomForStudent.setFinality(finality);
		reserveClassroomForStudent.setReservedChairs(reservedChairs);
		ReservationRoomForStudentDAO.getInstance()
				.change(
						oldReserveClassroomForStudentData,
						reserveClassroomForStudent);
	}

	public void delete(ReserveClassroomForStudent reserveClassroomForStudent)
			throws SQLException, ReserveException {
		ReservationRoomForStudentDAO.getInstance().delete(
				reserveClassroomForStudent);
		this.vectorReserveClassroomForStudent
				.remove(reserveClassroomForStudent);
	}
}
