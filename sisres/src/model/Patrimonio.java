package model;

import exception.PatrimonyException;

public class Patrimonio {

	private String codigo;
	private String descricao;
	//Mensagens de Erro e Alertas
	private final String CODIGO_BRANCO = "Codigo em Branco.";
	private final String CODIGO_NULO = "Codigo esta Nulo.";
	private final String DESCRICAO_BRANCO = "Descricao em Branco.";
	private final String DESCRICAO_NULO = "Descricao esta Nula.";

	public Patrimonio(String codigo, String descricao) throws PatrimonyException {
		this.setCodigo(codigo);
		this.setDescricao(descricao);
	}

	public String getCode() {
		return codigo;
	}

	public String getDescription() {
		return descricao;
	}

<<<<<<< HEAD:sisres/src/model/Patrimonio.java
	public void setCodigo(String codigo) throws PatrimonioException {
		try{
=======
	public void setCodigo(String codigo) throws PatrimonyException {
>>>>>>> devel:sisres/src/model/Patrimonio.java
		if(codigo == null)
			throw new PatrimonyException(CODIGO_NULO);
		else if ("".equals(codigo.trim())) 
<<<<<<< HEAD:sisres/src/model/Patrimonio.java
			throw new PatrimonioException(CODIGO_BRANCO);
		this.codigo = codigo;
		}catch(PatrimonioException exPatrimonio){
			System.out.println("erro" + exPatrimonio.getMessage());
		}
=======
			throw new PatrimonyException(CODIGO_BRANCO);
		//else if(codigo.matches("PATTERN"))
			//this.codigo = codigo;
		//else
			//throw new PatrimonioException(CODIGO_INVALIDO);
		this.codigo = codigo;//
>>>>>>> devel:sisres/src/model/Patrimonio.java
	}

	public void setDescricao(String descricao) throws PatrimonyException {
		if(descricao == null)
			throw new PatrimonyException(DESCRICAO_NULO);
		else if ("".equals(descricao.trim())) 
			throw new PatrimonyException(DESCRICAO_BRANCO);
		this.descricao = descricao;
	}

	public boolean equals(Patrimonio e){
<<<<<<< HEAD:sisres/src/model/Patrimonio.java
		if( this.getCodigo().equals(e.getCodigo()) && 
			this.getDescricao().equals(e.getDescricao())){
=======
		if( this.getCode().equals(e.getCode()) && 
			this.getDescription().equals(e.getDescription()))
>>>>>>> devel:sisres/src/model/Patrimonio.java
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
