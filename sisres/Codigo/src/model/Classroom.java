package model;

import exception.PatrimonyException;

public class Classroom extends Patrimony {

	private String capacidade;
	

	//Mensagens de Erro e Alertas
		private final String CAPACIDADE_INVALIDO = "Capacidade Invalida.";
		private final String CAPACIDADE_BRANCO = "Capacidade em Branco.";
		private final String CAPACIDADE_NULA = "Capacidade esta nula.";
		//private final String CAPACIDADE_NEGATIVA = "Capacidade negativa.";
			
		
	public Classroom(String codigo, String descricao, String capacidade) throws PatrimonyException {
		super(codigo, descricao);
		this.setCapacity(capacidade);
	}

	public String getCapacity() {
		return capacidade;
	}

	public void setCapacity(String capacidade) throws PatrimonyException {
		if(capacidade == null)
			throw new PatrimonyException(CAPACIDADE_NULA);
		else if("".equals(capacidade.trim()))
			throw new PatrimonyException(CAPACIDADE_BRANCO);
		else if(capacidade.matches("[\\d]+")){
				this.capacidade = capacidade;
		}
		else
		{
			throw new PatrimonyException(CAPACIDADE_INVALIDO);
		}
	}

	public boolean equals(Classroom b){
		if( super.equals(b) &&
			this.getCapacity().equals(b.getCapacity())){
			return true;
		}
		
		return false;
	}
}
