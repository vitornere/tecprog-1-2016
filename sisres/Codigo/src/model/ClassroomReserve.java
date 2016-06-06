package model;

import exception.ReserveException;

public class ClassroomReserve extends Reserve {

	private Classroom classroom;
	private String finality;

	private final String NULL_CLASSROOM = "A sala esta nula.";
	private final String NULL_FINALITY = "A finalidade esta nula.";
	private final String EMPTY_FINALITY = "A finalidade esta em branco.";

	public ClassroomReserve(String date, String hour, Classroom classroom,
			String finality) throws ReserveException {
		super(date, hour);
		this.setClassroom(classroom);
		this.setFinality(finality);
	}

	public Classroom getClassroom() {
		return this.classroom;
	}

	public String getFinality() {
		return this.finality;
	}

	public void setClassroom(Classroom classroom) throws ReserveException {
		if (classroom != null) {
			this.classroom = classroom;
		} else {
			throw new ReserveException(NULL_CLASSROOM);
		}
	}

	public void setFinality(String finality) throws ReserveException {
		finality = finality.trim();
		if ((finality != null) && (finality.equals("") == false)) {
			this.finality = finality;
		} else if ((finality == null)) {
			throw new ReserveException(NULL_FINALITY);
		} else {
			throw new ReserveException(EMPTY_FINALITY);
		}
	}

	public boolean equals(ClassroomReserve obj) {
		return (super.equals(obj)
				&& this.getClassroom().equals(obj.getClassroom()) && this
				.getFinality().equals(obj.getFinality()));
	}

	@Override
	public String toString() {
		return "\n" + this.getClassroom().toString() + "\nFinality="
				+ this.getFinality() + super.toString();
	}

}
