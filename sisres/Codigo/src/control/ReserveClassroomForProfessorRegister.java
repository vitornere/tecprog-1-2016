package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.ReservationRoomForTeacherDAO;

import model.Professor;
import model.ReserveClassroomForProfessor;
import model.Classroom;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

public class ReserveClassroomForProfessorRegister {
	private Vector<ReserveClassroomForProfessor> vectorReserveClassroomForProfessor = new Vector<ReserveClassroomForProfessor>();

	private static ReserveClassroomForProfessorRegister ReserveClassroomForProfessor;

	private ReserveClassroomForProfessorRegister() {
	}

	public static ReserveClassroomForProfessorRegister getInstance() {
		if (ReserveClassroomForProfessor != null) {

			// Nothing to do.
		} else {

			ReserveClassroomForProfessor = new ReserveClassroomForProfessorRegister();
		}

		return ReserveClassroomForProfessor;
	}

	public Vector<ReserveClassroomForProfessor> searchForDate(String date)
			throws SQLException, ClientException, PatrimonyException,
			ReserveException {
		return ReservationRoomForTeacherDAO
				.getInstance().searchByDate(date);
	}

	public Vector<ReserveClassroomForProfessor> getVectorReserveClassroomForProfessor()
			throws SQLException, ClientException, PatrimonyException,
			ReserveException {
		this.vectorReserveClassroomForProfessor = ReservationRoomForTeacherDAO
				.getInstance().searchAll();
		return this.vectorReserveClassroomForProfessor;
	}

	public void insert(Classroom classroom, Professor professor, String date,
			String hour, String finality) throws SQLException, ReserveException {

		ReserveClassroomForProfessor reserve = new ReserveClassroomForProfessor(
				date, hour, classroom, finality, professor);
		ReservationRoomForTeacherDAO.getInstance()
				.add(reserve);
		this.vectorReserveClassroomForProfessor.add(reserve);
	}

	public void update(String finality, ReserveClassroomForProfessor reserve)
			throws SQLException, ReserveException {

		ReserveClassroomForProfessor oldReserveClassroomForProfessorData = new ReserveClassroomForProfessor(
				reserve.getDate(), reserve.getHour(), reserve.getClassroom(),
				reserve.getFinality(), reserve.getProfessor());

		reserve.setFinality(finality);
		ReservationRoomForTeacherDAO.getInstance()
				.change(
						oldReserveClassroomForProfessorData, reserve);

	}

	public void delete(ReserveClassroomForProfessor reserve)
			throws SQLException, ReserveException {
		ReservationRoomForTeacherDAO.getInstance()
				.delete(reserve);
		this.vectorReserveClassroomForProfessor.remove(reserve);
	}
}