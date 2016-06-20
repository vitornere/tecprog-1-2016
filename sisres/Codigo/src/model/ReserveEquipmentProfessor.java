/**
 * Name: ReserveEquipmentProfessor.java
 * Class of model reserve equipment for professor.
 */
package model;

import exception.ReserveException;

public class ReserveEquipmentProfessor extends EquipmentReserve {

	private Professor professor;

	private final String NULL_PROFESSOR = "O professor esta nulo.";

	/**
	 * Constructor method of reserve equipment for professor.
	 * @param date, hour, equipment, professor
	 */
	public ReserveEquipmentProfessor(String date, String hour,
			Equipment equipment, Professor professor) throws ReserveException {
		super(date, hour, equipment);
		this.setProfessor(professor);
	}

	/**
	 * Getters and Setters
	 */	
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

	/**
	 * Get reservation equipment.
	 * @param obj
	 */
	public boolean equals(ReserveEquipmentProfessor obj) {
		return (super.equals(obj) && this.getEquipment().equals(
				obj.getEquipment()));
	}
	
	/**
	 * Return reservation equipment.
	 */
	@Override
	public String toString() {
		return "ReserveEquipmentProfessor [professor="
				+ this.getEquipment().toString() + ", toString()="
				+ super.toString() + "]";
	}

}
