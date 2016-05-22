package model;

import exception.PatrimonyException;

public class Patrimonio {

	private String codigo;
	private String descricao;
	//Mensagens de Erro e Alertas
	//private final String CODIGO_INVALIDO = "Codigo Invalido.";
	private final String CODIGO_BRANCO = "Codigo em Branco.";
	private final String CODIGO_NULO = "Codigo esta Nulo.";
	//private final String DESCRICAO_INVALIDO = "Descricao Invalido.";
	private final String DESCRICAO_BRANCO = "Descricao em Branco.";
	private final String DESCRICAO_NULO = "Descricao esta Nula.";

	public Patrimonio(String codigo, String descricao) throws PatrimonyException {
		this.setIdEquipment(codigo);
		this.setDescriptionEquipment(descricao);
	}

	public String getIdEquipment() {
		return codigo;
	}

	public String getDescriptionEquipment() {
		return descricao;
	}

	public void setIdEquipment(String codigo) throws PatrimonyException {
		if(codigo == null)
			throw new PatrimonyException(CODIGO_NULO);
		else if ("".equals(codigo.trim())) 
			throw new PatrimonyException(CODIGO_BRANCO);
		//else if(codigo.matches("PATTERN"))
			//this.codigo = codigo;
		//else
			//throw new PatrimonioException(CODIGO_INVALIDO);
		this.codigo = codigo;//
	}

	public void setDescriptionEquipment(String descricao) throws PatrimonyException {
		if(descricao == null)
			throw new PatrimonyException(DESCRICAO_NULO);
		else if ("".equals(descricao.trim())) 
			throw new PatrimonyException(DESCRICAO_BRANCO);
		this.descricao = descricao;
	}

	public boolean equals(Patrimonio e){
		if( this.getIdEquipment().equals(e.getIdEquipment()) && 
			this.getDescriptionEquipment().equals(e.getDescriptionEquipment()))
			return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Codigo=" + codigo +
			"\nDescricao=" + descricao;
	}
}
