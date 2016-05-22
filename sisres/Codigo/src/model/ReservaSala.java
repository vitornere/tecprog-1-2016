package model;

import exception.ReserveException;

public class ReservaSala extends Reserva{

	private Sala sala;
	private String finalidade;
	
	//Mensagens
		private final String SALA_NULO = "A sala esta nula.";
		private final String FINALIDADE_NULA = "A finalidade esta nula.";
		private final String FINALIDADE_BRANCO = "A finalidade esta em branco.";
				
	
	public ReservaSala(String data, String hora, Sala sala, String finalidade) throws ReserveException {
		super(data, hora);
		this.setSala(sala);
		this.setFinalidade(finalidade);
	}

	public Sala getSala() {
		return this.sala;
	}

	public String getFinalidade() {
		return this.finalidade;
	}

	public void setSala(Sala sala) throws ReserveException {
		if(sala == null)
			throw new ReserveException(SALA_NULO);
		this.sala = sala;
	}

	public void setFinalidade(String finalidade) throws ReserveException {
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
