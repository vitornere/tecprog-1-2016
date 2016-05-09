package entities;

public class Registro {

	protected String codigo;
	protected Medicament medicament;

	// Construtor Vazio
	public Registro() {
	}

	// Construtor utilizando-se heran�a
	public Registro(String codigoAnvisa) {
		this.codigo = codigoAnvisa;
	}

	// Agrega��o - Listagem e Adi��o
	public void listarMedicamento() {
		System.out.println("O medicamento do registro " + this.codigo + " �:" + medicament.nome);
	}

	public void adicionarMedicamento() {
		Medicament novoMedicamento = new Medicament();
		novoMedicamento = this.medicament;
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
		return medicament;
	}

	public void setMedicamento(Medicament medicament) {
		this.medicament = medicament;
	}

}
