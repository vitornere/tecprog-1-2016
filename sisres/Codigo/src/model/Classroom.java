package model;

import exception.PatrimonyException;

public class Classroom extends Patrimonio {

	private String capacidade;
	

	//Mensagens de Erro e Alertas
		private final String CAPACIDADE_INVALIDO = "Capacidade Invalida.";
		private final String CAPACIDADE_BRANCO = "Capacidade em Branco.";
		private final String CAPACIDADE_NULA = "Capacidade esta nula.";
		//private final String CAPACIDADE_NEGATIVA = "Capacidade negativa.";
			
		
	public Classroom(String codigo, String descricao, String capacidade) throws PatrimonyException {
		super(codigo, descricao);
		this.setCapacidade(capacidade);
	}

	public String getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(String capacidade) throws PatrimonyException {
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
			this.getCapacidade().equals(b.getCapacidade())){
			return true;
		}
		
		return false;
	}
}
