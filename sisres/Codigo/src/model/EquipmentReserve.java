package model;

import exception.ReserveException;

public class EquipmentReserve extends Reserve {

	private Equipment equipment;

	private final String NULL_EQUIPMENT = "O equipamneto esta nulo.";

	public EquipmentReserve(String date, String hour, Equipment equipment)
			throws ReserveException {
		super(date, hour);
		this.setEquipment(equipment);
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) throws ReserveException {
		if (equipment != null) {
			this.equipment = equipment;
		} else {
			throw new ReserveException(NULL_EQUIPMENT);
		}
	}

	public boolean equals(EquipmentReserve obj) {
		return (super.equals(obj) && this.getEquipment().equals(
				obj.getEquipment()));
	}

	@Override
	public String toString() {
		return "EquipmentReserve [equipment=" + this.getEquipment()
				+ ", toString()=" + super.toString() + "]";
	}

}
