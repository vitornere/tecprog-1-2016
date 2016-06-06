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

	public ReserveClassroomForStudent(String date, String hour,
			Classroom classroom, String finality, String reservedChairs,
			Student student) throws ReserveException {
		super(date, hour, classroom, finality);
		this.setStudent(student);
		this.setReservedChairs(reservedChairs);
	}

	public Student getStudent() {
		return this.student;
	}

	public String getReservedChairs() {
		return this.reservedChairs;
	}

	public void setStudent(Student student) throws ReserveException {
		if (student != null) {
			this.student = student;
		} else {
			throw new ReserveException(NULL_STUDENT);
		}
	}

	public void setReservedChairs(String reservedChairs)
			throws ReserveException {
		String chairs = reservedChairs;
		chairs = chairs.trim();
		if ((chairs != null) && (chairs.equals("") == false)
				&& (chairs.matches(PATTERN_CHAIRS) == false)) {
			this.reservedChairs = reservedChairs;
		} else if (chairs == null) {
			throw new ReserveException(NULL_CHAIRS);
		} else if (chairs.equals("")) {
			throw new ReserveException(EMPTY_CHAIRS);
		}

		else if (chairs.matches(PATTERN_CHAIRS)) {
			if (Integer.parseInt(super.getClassroom().getCapacity()) < Integer
					.parseInt(reservedChairs)) {
				throw new ReserveException(ABOVE_THE_LIMIT_CHAIRS);
			} else {
				// Nothing to do.
			}
		} else {
			throw new ReserveException(INVALID_CHAIR);
		}

	}

	public boolean equals(ReserveClassroomForStudent obj) {
		return (super.equals(obj) && this.getStudent().equals(obj.getStudent()) && this
				.getReservedChairs().equals(obj.getReservedChairs()));
	}

	@Override
	public String toString() {
		return "Student: " + this.getStudent().toString()
				+ "\nReserved Chairs: " + this.getReservedChairs()
				+ super.toString();
	}

}
