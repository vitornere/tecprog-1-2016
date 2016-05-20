package model;

import exception.PatrimonioException;

public class Patrimonio {

	private String codigo;
	private String descricao;
	//Mensagens de Erro e Alertas
	private final String CODIGO_BRANCO = "Codigo em Branco.";
	private final String CODIGO_NULO = "Codigo esta Nulo.";
	private final String DESCRICAO_BRANCO = "Descricao em Branco.";
	private final String DESCRICAO_NULO = "Descricao esta Nula.";

	public Patrimonio(String codigo, String descricao) throws PatrimonioException {
		this.setCodigo(codigo);
		this.setDescricao(descricao);
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setCodigo(String codigo) throws PatrimonioException {
		try{
		if(codigo == null)
			throw new PatrimonioException(CODIGO_NULO);
		else if ("".equals(codigo.trim())) 
			throw new PatrimonioException(CODIGO_BRANCO);
		this.codigo = codigo;
		}catch(PatrimonioException exPatrimonio){
			System.out.println("erro" + exPatrimonio.getMessage());
		}
	}

	public void setDescricao(String descricao) throws PatrimonioException {
		if(descricao == null)
			throw new PatrimonioException(DESCRICAO_NULO);
		else if ("".equals(descricao.trim())) 
			throw new PatrimonioException(DESCRICAO_BRANCO);
		this.descricao = descricao;
	}

	public boolean equals(Patrimonio e){
		if( this.getCodigo().equals(e.getCodigo()) && 
			this.getDescricao().equals(e.getDescricao())){
			return true;
		}else{
		return false;
		}
	}
	
	@Override
	public String toString() {
		return "Codigo=" + codigo +
			"\nDescricao=" + descricao;
	}
}
