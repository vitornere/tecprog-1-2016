/**
 * This class keeps medicaments
 */

package entities;

public class Registro {

	/*
	 * Medicament's information attributes
	 */
	protected String codigo;
	protected Medicament medicament;

	/**
	 * Creates an empty object
	 */
	public Registro() {
	}

	/**
	 * Creates an object with Anvisa code
	 * 
	 * @param codigoAnvisa
	 */
	public Registro(String codigoAnvisa) {
		this.codigo = codigoAnvisa;
	}

	public void listarMedicamento() {
		System.out.println("O medicamento do registro " + this.codigo + " �:" + medicament.nome);
	}

	public void adicionarMedicamento() {
		Medicament novoMedicamento = new Medicament();
		novoMedicamento = this.medicament;
		this.setMedicamento(novoMedicamento);
	}

	/*
	 * Getters and Setters
	 */

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
