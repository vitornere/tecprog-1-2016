/**
 * Name: ReserveClassroomForProfessor.java
 * Class of model reserve classroom for professor.
 */
package model;

import exception.ReserveException;

public class ReserveClassroomForProfessor extends ClassroomReserve {

	private Professor professor;

	private final String NULL_PROFESSOR = "O professor esta nulo.";
	/**
	 * Constructor method of reserve classroom for professor.
	 * @param date, hour, classroom, finality, professor
	 */
	public ReserveClassroomForProfessor(String date, String hour,
			Classroom classroom, String finality, Professor professor)
			throws ReserveException {
		super(date, hour, classroom, finality);
		this.setProfessor(professor);
	}

	/**
	 * Empty Constructor method of reserve classroom for professor.
	 */
	public Professor getProfessor() {
		return this.professor;
	}

	/**
	 * Create professor if there is no.
	 * @param professor
	 */
	public void setProfessor(Professor professor) throws ReserveException {
		if (professor != null) {
			this.professor = professor;
		} else {
			throw new ReserveException(NULL_PROFESSOR);
		}
	}

	/**
	 * Get reservation made by the professor.
	 * @param obj
	 */
	public boolean equals(ReserveClassroomForProfessor obj) {
		return (super.equals(obj) && this.getProfessor().equals(
				obj.getProfessor()));
	}

	/**
	 * Return reservation made by the professor.
	 */
	@Override
	public String toString() {
		return "ReserveClassroomForProdessor [professor="
				+ this.getProfessor().toString() + ", toString()="
				+ super.toString() + "]";
	}

}
