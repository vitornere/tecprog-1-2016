package model;

import exception.ReserveException;

public class ReserveEquipmentProfessor extends EquipmentReserve {

	private Professor professor;

	private final String NULL_PROFESSOR = "O professor esta nulo.";

	public ReserveEquipmentProfessor(String date, String hour,
			Equipment equipment, Professor professor) throws ReserveException {
		super(date, hour, equipment);
		this.setProfessor(professor);
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) throws ReserveException {
		if (professor != null) {
			this.professor = professor;
		} else {
			throw new ReserveException(NULL_PROFESSOR);
		}
	}

	public boolean equals(ReserveEquipmentProfessor obj) {
		return (super.equals(obj) && this.getEquipment().equals(
				obj.getEquipment()));
	}

	@Override
	public String toString() {
		return "ReserveEquipmentProfessor [professor="
				+ this.getEquipment().toString() + ", toString()="
				+ super.toString() + "]";
	}

}
