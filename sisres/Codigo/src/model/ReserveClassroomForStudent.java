package model;

import exception.ReserveException;

public class ReserveClassroomForStudent extends ReservaSala{
	
	private Student aluno;
	private String cadeiras_reservadas;
	
	//Mensagens
		private final String ALUNO_NULO = "O aluno esta nulo.";
		private final String CADEIRAS_NULA = "O numero de cadeiras esta nulo.";
		private final String CADEIRAS_BRANCO = "O numero de cadeiras esta em branco.";
		private final String CADEIRAS_INVALIDA = "O numero de cadeira eh invalido.";
		private final String CADEIRAS_ACIMA_DO_LIMITE = "A sala nao possui este numero de cadeiras para reservar.";
		private final String CADEIRAS_PATTERN = "^[\\d]+$";

	public ReserveClassroomForStudent(String data, String hora, Classroom sala,
			String finalidade, String cadeiras_reservadas, Student aluno) throws ReserveException {
		super(data, hora, sala, finalidade);
		this.setAluno(aluno);
		this.setReservedChairs(cadeiras_reservadas);
	}
	
	public Student getStudent() {
		return this.aluno;
	}

	public String getReservedChairs() {
		return this.cadeiras_reservadas;
	}

	public void setAluno(Student aluno) throws ReserveException {
		if(aluno == null)
			throw new ReserveException(ALUNO_NULO);
		this.aluno = aluno;
	}

	public void setReservedChairs(String cadeiras_reservadas) throws ReserveException {
		String c = cadeiras_reservadas;
		if(c == null)
			throw new ReserveException(CADEIRAS_NULA);
		c = c.trim();
		if(c.equals(""))
			throw new ReserveException(CADEIRAS_BRANCO);
		else if(c.matches(CADEIRAS_PATTERN)){
			if(Integer.parseInt(super.getClassroom().getCapacity()) < Integer.parseInt(cadeiras_reservadas))
				throw new ReserveException(CADEIRAS_ACIMA_DO_LIMITE);
			else
				this.cadeiras_reservadas = cadeiras_reservadas;
		}
		else
			throw new ReserveException(CADEIRAS_INVALIDA);
	}


	public boolean equals(ReserveClassroomForStudent obj) {
		return (super.equals(obj) &&
				this.getStudent().equals(obj.getStudent()) &&
				this.getReservedChairs().equals(obj.getReservedChairs())
				);
	}

	@Override
	public String toString() {
		return "Aluno: " + this.getStudent().toString()
			+ "\nCadeiras Reservadas: " + this.getReservedChairs() 
			+ super.toString();
	}

}
