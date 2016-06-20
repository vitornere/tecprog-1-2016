package model;

import exception.ReserveException;

public class ReserveClassroomForProfessor extends ClassroomReserve {

	private Professor professor;

	private final String NULL_PROFESSOR = "O professor esta nulo.";

	public ReserveClassroomForProfessor(String date, String hour,
			Classroom classroom, String finality, Professor professor)
			throws ReserveException {
		super(date, hour, classroom, finality);
		this.setProfessor(professor);
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) throws ReserveException {
		if (professor != null) {
			this.professor = professor;
		} else {
			throw new ReserveException(NULL_PROFESSOR);
		}
	}

	public boolean equals(ReserveClassroomForProfessor obj) {
		return (super.equals(obj) && this.getProfessor().equals(
				obj.getProfessor()));
	}

	@Override
	public String toString() {
		return "ReserveClassroomForProdessor [professor="
				+ this.getProfessor().toString() + ", toString()="
				+ super.toString() + "]";
	}

}
