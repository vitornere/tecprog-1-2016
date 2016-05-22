package model;

import exception.ReserveException;

public class ReservaSalaProfessor extends ReservaSala{

	private Professor professor;
	
	//Mensagens
		private final String PROFESSOR_NULO = "O professor esta nulo.";
	
	public ReservaSalaProfessor(String data, String hora, Sala sala,
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

	public boolean equals(ReservaSalaProfessor obj) {
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
