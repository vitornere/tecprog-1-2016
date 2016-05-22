package model;

import exception.ReserveException;

public class ReserveClassroomForProfessor extends ReservaSala{

	private Professor professor;
	
	//Mensagens
		private final String PROFESSOR_NULO = "O professor esta nulo.";
	
	public ReserveClassroomForProfessor(String data, String hora, Classroom sala,
			String finalidade, Professor professor) throws ReserveException {
		super(data, hora, sala, finalidade);
		this.setProfessor(professor);
	}
	
	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) throws ReserveException {
		if(professor == null)
			throw new ReserveException(PROFESSOR_NULO);
		this.professor = professor;
	}

	public boolean equals(ReserveClassroomForProfessor obj) {
		return (super.equals(obj) &&
				this.getProfessor().equals(obj.getProfessor())
				);
	}

	@Override
	public String toString() {
		return "ReservaSalaProfessor [professor=" + this.getProfessor().toString() + ", toString()="
				+ super.toString() + "]";
	}

}
