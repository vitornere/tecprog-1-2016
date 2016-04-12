package entidades;

public class Produto {

	protected String nome;
	protected String fabricante;
	protected String recomendacao; // uso adulto, infantil, todos os públicos etc
	protected String validade;

	public Produto() {
	}

	public Produto(String nomeProduto, String fabricanteProduto, String recomendacaoProduto, String validadeProduto) {
		this.nome = nomeProduto;
		this.fabricante = fabricanteProduto;
		this.recomendacao = recomendacaoProduto;
		this.validade = validadeProduto;
	}

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
