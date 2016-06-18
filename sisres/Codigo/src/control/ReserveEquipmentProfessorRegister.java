package control;

import java.sql.SQLException;
import java.util.Vector;

import model.Equipment;
import model.Professor;
import model.ReserveEquipmentProfessor;
import persistence.EquipamentReservationForTeacherDAO;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

public class ReserveEquipmentProfessorRegister {
	private Vector<Object> vertorReserveEquipmentsProfessor = new Vector<Object>();

	private static ReserveEquipmentProfessorRegister newReserveEquipmentProfessor;

	private ReserveEquipmentProfessorRegister() {
	}

	public static ReserveEquipmentProfessorRegister getInstanceEquipmentProfessor() {
		if (newReserveEquipmentProfessor != null) {

			// Nothing to do.
		} else {

			newReserveEquipmentProfessor = new ReserveEquipmentProfessorRegister();
		}

		return newReserveEquipmentProfessor;
	}

	public Vector<ReserveEquipmentProfessor> getHoursReserved(String hour)
			throws SQLException, PatrimonyException, ClientException,
			ReserveException {
		return EquipamentReservationForTeacherDAO.getInstance().searchForHour(hour);

	}

	public Vector<ReserveEquipmentProfessor> getMonthReservations(int month)
			throws SQLException, PatrimonyException, ClientException,
			ReserveException {
		return EquipamentReservationForTeacherDAO.getInstance().searchForMonth(month);
	}

	public Vector<Object> getVectorReserveEquipmentProfessor()
			throws SQLException, ClientException, PatrimonyException,
			ReserveException {
		this.vertorReserveEquipmentsProfessor = EquipamentReservationForTeacherDAO
				.getInstance().searchAll();
		return this.vertorReserveEquipmentsProfessor;
	}

	public void insert(Equipment equipment, Professor professor, String date,
			String hour) throws SQLException, ReserveException {
		ReserveEquipmentProfessor reserve = new ReserveEquipmentProfessor(date,
				hour, equipment, professor);
		EquipamentReservationForTeacherDAO.getInstance().add(reserve);
		this.vertorReserveEquipmentsProfessor.add(reserve);
	}

	public void update(String finality, ReserveEquipmentProfessor reserve)
			throws SQLException, ReserveException {

		ReserveEquipmentProfessor oldReserveData = new ReserveEquipmentProfessor(
				reserve.getDate(), reserve.getHour(), reserve.getEquipment(),
				reserve.getProfessor());
		EquipamentReservationForTeacherDAO.getInstance().change(oldReserveData,
				reserve);

	}

	public void delete(ReserveEquipmentProfessor reserve) throws SQLException,
			ReserveException {
		EquipamentReservationForTeacherDAO.getInstance().delete(reserve);
		this.vertorReserveEquipmentsProfessor.remove(reserve);
	}
}
