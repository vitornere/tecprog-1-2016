package entities;

public class Person extends Administrative {
	protected String rg; 
	protected String cpf;
	protected int digitoCpf;
	protected String nome;
	protected String sobrenome;
	protected String endereco;
	protected String telefone;
	private static double confirmacaoPagamento; // Used to verify if payment it is released
	protected double salario;

	/**
	 * Creates an empty object
	 */
	public Person() {
	}

	/**
	 * Creates an object only with documents' params
	 * 
	 * @param rgPessoa
	 * @param cpfPessoa
	 * @param digitoCpfPessoa
	 */
	public Person(String rgPessoa, String cpfPessoa, int digitoCpfPessoa) {
		this.rg = rgPessoa;
		this.cpf = cpfPessoa;
		this.digitoCpf = digitoCpfPessoa;
	}

	/**
	 * Creates an object only with personal information's params
	 * 
	 * @param nomePessoa
	 * @param sobrenomePessoa
	 * @param enderecoPessoa
	 * @param telefonePessoa
	 */

	public Person(String nomePessoa, String sobrenomePessoa, String enderecoPessoa, String telefonePessoa) {
		this.nome = nomePessoa;
		this.sobrenome = sobrenomePessoa;
		this.endereco = enderecoPessoa;
		this.telefone = telefonePessoa;
	}

	/**
	 * Creates an object with all params
	 * 
	 * @param rgPessoa
	 * @param cpfPessoa
	 * @param digitoCpfPessoa
	 * @param nomePessoa
	 * @param sobrenomePessoa
	 * @param enderecoPessoa
	 * @param telefonePessoa
	 */
	public Person(String rgPessoa, String cpfPessoa, int digitoCpfPessoa, String nomePessoa,
			String sobrenomePessoa, String enderecoPessoa, String telefonePessoa) {
		this.rg = rgPessoa;
		this.cpf = cpfPessoa;
		this.digitoCpf = digitoCpfPessoa;
		this.nome = nomePessoa;
		this.sobrenome = sobrenomePessoa;
		this.endereco = enderecoPessoa;
		this.telefone = telefonePessoa;
	}

	/**
	 * Verify if digits of CPF are valid
	 * 
	 * @param digitoCpfPessoa
	 * @return valid or invalid
	 */
	private boolean validarCpf(int digitoCpfPessoa) {
		boolean validar1;
		if (digitoCpfPessoa > 99) {
			validar1 = false;
		}
		else {
			validar1 = true;
		}

		return validar1;
	}

	/**
	 * Performs the registration of a new person with all your information
	 * 
	 * @param rgPessoa
	 * @param cpfPessoa
	 * @param digitoCpfPessoa
	 * @param nomePessoa
	 * @param sobrenomePessoa
	 * @param enderecoPessoa
	 * @param telefonePessoa
	 */
	public void cadastrarPessoa(String rgPessoa, String cpfPessoa, int digitoCpfPessoa, String nomePessoa,
			String sobrenomePessoa, String enderecoPessoa, String telefonePessoa) {
		this.nome = nomePessoa;
		this.sobrenome = sobrenomePessoa;
		this.endereco = enderecoPessoa;
		this.telefone = telefonePessoa;
		this.rg = rgPessoa;
		this.cpf = cpfPessoa;
		this.digitoCpf = digitoCpfPessoa;

		boolean validacaoDigitoCpf = this.validarCpf(digitoCpfPessoa);
		if (validacaoDigitoCpf == true) {
			System.out.println("Funcion�rio cadastrado com sucesso!");
		}
		else {
			System.out.println("Funcion�rio n�o cadastrado!");
		}
	}

	/**
	 *  Confirms whether the payment was accepted or not
	 * 
	 * @return 1 or 0 to accepted or rejected
	 */
	public double paymentConfirmation() {
		if (this.getPaymentConfirmation() == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}

	/*
	 * Getters and Setters
	 * 
	 * @see entities.Administrativo#calculateSalary()
	 */
	
	public double calculateSalary() {
		return this.salario;
	}

	public String getIdentity() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpfPerson() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getDigitCpfPerson() {
		return digitoCpf;
	}

	public void setDigitoCpf(int digitoCpf) {
		this.digitoCpf = digitoCpf;
	}

	public String getName() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPastName() {

		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getAddress() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getPhone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public static double getPaymentConfirmation() {
		return confirmacaoPagamento;
	}

	public static void setPaymentConfirmation(double confirmacaoPagamento) {
		Person.confirmacaoPagamento = confirmacaoPagamento;
	}


}
