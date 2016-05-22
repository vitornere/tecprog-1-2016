package control;

import java.sql.SQLException;
import java.util.Vector;
import persistence.EquipmentDAO;
import exception.PatrimonyException;
import model.Equipment;

public class EquipmentRegister {

	private static final String EXCEPTION_OF_EQUIPMENT_NULL = "Equipamento em branco";

	private Vector<Equipment> vectorEquipments = new Vector<Equipment>();

	private static EquipmentRegister newEquipment;

	private EquipmentRegister() {

	}

	public static EquipmentRegister getNewEquipment() {
		if (newEquipment != null) {

			// Nothing to do.
		} else {
			newEquipment = new EquipmentRegister();
		}
		return newEquipment;
	}

	public Vector<Equipment> getVectorEquipments() throws SQLException,
			PatrimonyException {
		this.vectorEquipments = EquipmentDAO.getNewEquipment().searchAll();
		return this.vectorEquipments;
	}

	public void insert(String idEquipment, String descriptionEquipment)
			throws PatrimonyException, SQLException {
		Equipment equipment = new Equipment(idEquipment, descriptionEquipment);
		EquipmentDAO.getNewEquipment().include(equipment);
		getVectorEquipments();
	}

	public void update(String idEquipment, String descriptionEquipment,
			Equipment equipment) throws PatrimonyException, SQLException {
		if (equipment != null) {
			Equipment oldEquipmentData = new Equipment(
					equipment.getIdEquipment(),
					equipment.getDescriptionEquipment());
			equipment.setIdEquipment(idEquipment);
			equipment.setDescriptionEquipment(descriptionEquipment);
			EquipmentDAO.getNewEquipment().update(oldEquipmentData, equipment);
			getVectorEquipments();
		} else {

			throw new PatrimonyException(EXCEPTION_OF_EQUIPMENT_NULL);
		}

	}

	public void delete(Equipment equipment) throws SQLException,
			PatrimonyException {
		if (equipment != null) {

			EquipmentDAO.getNewEquipment().delete(equipment);
			getVectorEquipments();
		} else {

			throw new PatrimonyException(EXCEPTION_OF_EQUIPMENT_NULL);
		}

	}
}
