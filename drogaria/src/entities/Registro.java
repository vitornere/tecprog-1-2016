package entities;

public class Registro {

	protected String codigo;
	protected Medicamento medicamento;

	// Construtor Vazio
	public Registro() {
	}

	// Construtor utilizando-se heran�a
	public Registro(String codigoAnvisa) {
		this.codigo = codigoAnvisa;
	}

	// Agrega��o - Listagem e Adi��o
	public void listarMedicamento() {
		System.out.println("O medicamento do registro " + this.codigo + " �:" + medicamento.nome);
	}

	public void adicionarMedicamento() {
		Medicamento novoMedicamento = new Medicamento();
		novoMedicamento = this.medicamento;
		this.setMedicamento(novoMedicamento);
	}// Fim Agregacao

	// Getters & Setters

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

}
