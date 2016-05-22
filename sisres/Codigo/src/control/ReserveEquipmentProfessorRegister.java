package control;

import java.sql.SQLException;
import java.util.Vector;

import model.Equipment;
import model.Professor;
import model.ReserveEquipmentProfessor;
import persistence.ReserveEquipmentProfessorDAO;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

public class ReserveEquipmentProfessorRegister {
	private Vector<Object> vertorReserveEquipmentsProfessor = new Vector<Object>();

	private static ReserveEquipmentProfessorRegister newReserveEquipmentProfessor;

	private ReserveEquipmentProfessorRegister() {
	}

	public static ReserveEquipmentProfessorRegister getReserveEquipmentProfessor() {
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
		return ReserveEquipmentProfessorDAO.getReserve().searchForHour(hour);

	}

	public Vector<ReserveEquipmentProfessor> getMonthReservations(int month)
			throws SQLException, PatrimonyException, ClientException,
			ReserveException {
		return ReserveEquipmentProfessorDAO.getReserve().searchForMonth(month);
	}

	public Vector<Object> getVectorReserveEquipmentProfessor()
			throws SQLException, ClientException, PatrimonyException,
			ReserveException {
		this.vertorReserveEquipmentsProfessor = ReserveEquipmentProfessorDAO
				.getReserve().searchAll();
		return this.vertorReserveEquipmentsProfessor;
	}

	public void insert(Equipment equipment, Professor professor, String date,
			String hour) throws SQLException, ReserveException {
		ReserveEquipmentProfessor reserve = new ReserveEquipmentProfessor(date,
				hour, equipment, professor);
		ReserveEquipmentProfessorDAO.getReserve().include(reserve);
		this.vertorReserveEquipmentsProfessor.add(reserve);
	}

	public void update(String finality, ReserveEquipmentProfessor reserve)
			throws SQLException, ReserveException {

		ReserveEquipmentProfessor oldReserveData = new ReserveEquipmentProfessor(
				reserve.getDate(), reserve.getHour(), reserve.getEquipment(),
				reserve.getProfessor());
		ReserveEquipmentProfessorDAO.getReserve().updateReserve(oldReserveData,
				reserve);

	}

	public void delete(ReserveEquipmentProfessor reserve) throws SQLException,
			ReserveException {
		ReserveEquipmentProfessorDAO.getReserve().delete(reserve);
		this.vertorReserveEquipmentsProfessor.remove(reserve);
	}
}
