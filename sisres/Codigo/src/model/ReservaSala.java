package model;

import exception.ReservaException;

public class ReservaSala extends Reserva{

	private Classroom sala;
	private String finalidade;
	
	//Mensagens
		private final String SALA_NULO = "A sala esta nula.";
		private final String FINALIDADE_NULA = "A finalidade esta nula.";
		private final String FINALIDADE_BRANCO = "A finalidade esta em branco.";
				
	
	public ReservaSala(String data, String hora, Classroom sala, String finalidade) throws ReservaException {
		super(data, hora);
		this.setSala(sala);
		this.setFinalidade(finalidade);
	}

	public Classroom getSala() {
		return this.sala;
	}

	public String getFinalidade() {
		return this.finalidade;
	}

	public void setSala(Classroom sala) throws ReservaException {
		if(sala == null)
			throw new ReservaException(SALA_NULO);
		this.sala = sala;
	}

	public void setFinalidade(String finalidade) throws ReservaException {
		if(finalidade == null)
			throw new ReservaException(FINALIDADE_NULA);
		
		finalidade = finalidade.trim();
		if(finalidade.equals(""))
			throw new ReservaException(FINALIDADE_BRANCO);
		else
			this.finalidade = finalidade;
	}

	public boolean equals(ReservaSala obj) {
		return (super.equals(obj) && 
			this.getSala().equals(obj.getSala())&&
			this.getFinalidade().equals(obj.getFinalidade()));
	}
	
	@Override
	public String toString() {
		return "\n" + this.getSala().toString() 
			+ "\nFinalidade=" + this.getFinalidade() 
			+ super.toString();
	}

}
