package entidades;

//Super classe de balconista,cliente e caixa
public class People extends Administrativo {

	protected String rg;
	protected String cpf;
	protected int digitCpf;
	protected String name;
	protected String lastname;
	protected String address;
	protected String phone;
	private static double paymentConfirmation;// Polimorfismo
	protected double salary;// Classe Abstrata

	public People() {
	}

	// Documentos
	public People(String rgPeople, String cpfPeople, int digitCpfPeople) {
		this.rg = rgPeople;
		this.cpf = cpfPeople;
		this.digitCpf = digitCpfPeople;
	}

	// Nomes
	public People(String nomePeople, String lastnamePeople, String addressPeople, String phonePeople) {
		this.name = nomePeople;
		this.lastname = lastnamePeople;
		this.address = addressPeople;
		this.phone = phonePeople;
	}

	// Todos os parametros
	public People(String rgPeople, String cpfPeople, int digitCpfPeople, String nomePeople,
			String lastnamePeople, String addressPeople, String phonePeople) {
		this.rg = rgPeople;
		this.cpf = cpfPeople;
		this.digitCpf = digitCpfPeople;
		this.name = nomePeople;
		this.lastname = lastnamePeople;
		this.address = addressPeople;
		this.phone = phonePeople;
	}

	// Validacao digito
	private boolean validateCpf(int digitCpfPeople) {
		boolean first_validate;
		if (digitCpfPeople > 99) {
			first_validate = false;
		}
		else {
			first_validate = true;
		}

		return first_validate;
	}

	public void registerPeople(String rgPeople, String cpfPeople, int digitCpfPeople, String nomePeople,
			String lastnamePeople, String addressPeople, String phonePeople) {
		// Atributos
		this.name = nomePeople;
		this.lastname = lastnamePeople;
		this.address = addressPeople;
		this.phone = phonePeople;
		this.rg = rgPeople;
		this.cpf = cpfPeople;
		this.digitCpf = digitCpfPeople;

		boolean validationDigitCpf = this.validateCpf(digitCpfPeople);
		if (validationDigitCpf == true) {
			System.out.println("Funcionário cadastrado com sucesso!");
		}
		else {
			System.out.println("Funcionario no cadastrado!");
		}
	}

	public double paymentConfirmation() {
		if (this.PaymentConfirmation() == 1) {
			return 1; // Confirma que o pagamento foi aceito.
		}
		else {
			return 0; // Confirma que o pagamento n�o foi aceito
		}
	}

	// Metodo de calculo do salary do funcionario
	public double calculateSalary() {
		return this.salary;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getDigitoCpf() {
		return digitCpf;
	}

	public void setDigitoCpf(int digitCpf) {
		this.digitCpf = digitCpf;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String name) {
		this.name = name;
	}

	public String getSobrenome() {
		return lastname;
	}

	public void setSobrenome(String lastname) {
		this.lastname = lastname;
	}

	public String getEndereco() {
		return address;
	}

	public void setEndereco(String address) {
		this.address = address;
	}

	public String getTelefone() {
		return phone;
	}

	public void setTelefone(String phone) {
		this.phone = phone;
	}

	public double PaymentConfirmation() {
		return paymentConfirmation;
	}

	public static void setConfirmacaoPagamento(double paymentConfirmation) {
		People.paymentConfirmation = paymentConfirmation;
	}

	@Override
	public double calcularSalario() {
		// TODO Auto-generated method stub
		return 0;
	}

}
