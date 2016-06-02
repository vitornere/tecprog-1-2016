package entidades;

public class Register {

	protected String code;
	protected Medicamento medicine;

	// Construtor Vazio
	public Register() {
	}

	// Construtor utilizando-se herança
	public Register(String codeAnvisa) {
		this.code = codeAnvisa;
	}

	// Agregaçãoo - Listagem e Adição
	public void listMedicine() {
		System.out.println("O medicine do registro " + this.code + " �:" + medicine.name);
	}

	public void adicionarMedicine() {
		Medicamento newMedicine = new Medicamento();
		newMedicine = this.medicine;
		this.setMedicamento(newMedicine);
	}// Fim Agregacao

	// Getters & Setters

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Medicamento getMedicine() {
		return medicine;
	}

	public void setMedicamento(Medicamento medicine) {
		this.medicine = medicine;
	}

}
