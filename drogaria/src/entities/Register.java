package entities;

public class Register {

	protected String codigo;
	protected Medicament medicamento;

	// Construtor Vazio
	public Register() {
	}

	// Construtor utilizando-se heran�a
	public Register(String codigoAnvisa) {
		this.codigo = codigoAnvisa;
	}

	// Agrega��o - Listagem e Adi��o
	public void medicamentList() {
		System.out.println("O medicamento do registro " + this.codigo + " �:" + medicamento.nome);
	}

	public void adicionarMedicamento() {
		Medicament novoMedicamento = new Medicament();
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

	public Medicament getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicament medicamento) {
		this.medicamento = medicamento;
	}

}
