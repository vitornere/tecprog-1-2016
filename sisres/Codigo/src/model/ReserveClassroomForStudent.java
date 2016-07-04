/**
 * Name: ReserveClassroomForStudent.java
 * Class of model reserve classroom for student.
 */
package model;

import exception.ReserveException;

public class ReserveClassroomForStudent extends ClassroomReserve {

	private Student student;
	private String reservedChairs;

	private final String NULL_STUDENT = "O aluno esta nulo.";
	private final String NULL_CHAIRS = "O numero de cadeiras esta nulo.";
	private final String EMPTY_CHAIRS = "O numero de cadeiras esta em branco.";
	private final String INVALID_CHAIR = "O numero de cadeira eh invalido.";
	private final String ABOVE_THE_LIMIT_CHAIRS = "A sala nao possui este numero de cadeiras para reservar.";
	private final String PATTERN_CHAIRS = "^[\\d]+$";

	/**
	 * Constructor method of reserve classroom for student.
	 * 
	 * @param date
	 *            , hour, classroom, finality, reservedChairs, student
	 */
	public ReserveClassroomForStudent(String date, String hour,
			Classroom classroom, String finality, String reservedChairs,
			Student student) throws ReserveException {
		super(date, hour, classroom, finality);
		this.setStudent(student);
		this.setReservedChairs(reservedChairs);
	}

	/**
	 * Getters and Setters.
	 */
	public Student getStudent() {
		return this.student;
	}

	public String getReservedChairs() {
		return this.reservedChairs;
	}

	/**
	 * Create student if there is no.
	 * 
	 * @param student
	 */
	public void setStudent(Student student) throws ReserveException {
		if (student != null) {
			this.student = student;
		} else {
			throw new ReserveException(NULL_STUDENT);
		}
	}

	/**
	 * Get reserved chairs.
	 * 
	 * @param reservedChairs
	 */
	public void setReservedChairs(String reservedChairs)
			throws ReserveException {
		if (reservedChairs != null) {
			// Nothing to do.
		} else {
			throw new ReserveException(NULL_CHAIRS);
		}
		if (!reservedChairs.equals("")) {
			// Nothing to do.
		} else {
			throw new ReserveException(EMPTY_CHAIRS);
		}
		if (!reservedChairs.matches(PATTERN_CHAIRS)) {
			// Nothing to do.
		} else {
			if (Integer.parseInt(super.getClassroom().getCapacity()) < Integer
					.parseInt(reservedChairs)) {
				throw new ReserveException(ABOVE_THE_LIMIT_CHAIRS);
			} else {
				// Nothing to do.
			}
		}

		if ((reservedChairs != null) && (!reservedChairs.equals(""))
				&& (reservedChairs.matches(PATTERN_CHAIRS))) {
			reservedChairs = reservedChairs.trim();
			this.reservedChairs = reservedChairs;
		} else {
			throw new ReserveException(INVALID_CHAIR);
		}
	}

	/**
	 * Get reservation made by the student.
	 * 
	 * @param obj
	 */
	public boolean equals(ReserveClassroomForStudent obj) {
		return (super.equals(obj) && this.getStudent().equals(obj.getStudent()) && this
				.getReservedChairs().equals(obj.getReservedChairs()));
	}

	/**
	 * Return reservation made by the student.
	 */
	@Override
	public String toString() {
		return "Student: " + this.getStudent().toString()
				+ "\nReserved Chairs: " + this.getReservedChairs()
				+ super.toString();
	}

}
