/**
 * This class keep product
 */

package entities;

public class Produto {

	/*
	 * Product's information attributess
	 */
	protected String nome;
	protected String fabricante;
	protected String recomendacao; // uso adulto, infantil, todos os p�blicos etc
	protected String validade;

	/**
	 * Creates an empty object
	 */
	public Produto() {
	}

	/**
	 * Creates an object with all params
	 * 
	 * @param nomeProduto
	 * @param fabricanteProduto
	 * @param recomendacaoProduto
	 * @param validadeProduto
	 */
	public Produto(String nomeProduto, String fabricanteProduto, String recomendacaoProduto, String validadeProduto) {
		this.nome = nomeProduto;
		this.fabricante = fabricanteProduto;
		this.recomendacao = recomendacaoProduto;
		this.validade = validadeProduto;
	}

	/*
	 * Getters and Setters
	 */
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public void setRecomendacao(String recomendacao) {
		this.recomendacao = recomendacao;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

}
