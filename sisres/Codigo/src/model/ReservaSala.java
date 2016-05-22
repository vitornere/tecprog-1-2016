package model;

import exception.ReserveException;

public class ReservaSala extends Reserva{

	private Classroom sala;
	private String finalidade;
	
	//Mensagens
		private final String SALA_NULO = "A sala esta nula.";
		private final String FINALIDADE_NULA = "A finalidade esta nula.";
		private final String FINALIDADE_BRANCO = "A finalidade esta em branco.";
				
	
	public ReservaSala(String data, String hora, Classroom sala, String finalidade) throws ReserveException {
		super(data, hora);
		this.setSala(sala);
		this.setFinality(finalidade);
	}

	public Classroom getClassroom() {
		return this.sala;
	}

	public String getFinality() {
		return this.finalidade;
	}

	public void setSala(Classroom sala) throws ReserveException {
		if(sala == null)
			throw new ReserveException(SALA_NULO);
		this.sala = sala;
	}

	public void setFinality(String finalidade) throws ReserveException {
		if(finalidade == null)
			throw new ReserveException(FINALIDADE_NULA);
		
		finalidade = finalidade.trim();
		if(finalidade.equals(""))
			throw new ReserveException(FINALIDADE_BRANCO);
		else
			this.finalidade = finalidade;
	}

	public boolean equals(ReservaSala obj) {
		return (super.equals(obj) && 
			this.getClassroom().equals(obj.getClassroom())&&
			this.getFinality().equals(obj.getFinality()));
	}
	
	@Override
	public String toString() {
		return "\n" + this.getClassroom().toString() 
			+ "\nFinalidade=" + this.getFinality() 
			+ super.toString();
	}

}
