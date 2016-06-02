package entities;

import java.util.ArrayList;
import java.util.Scanner;

<<<<<<< HEAD:drogaria/src/entidades/Medicamento.java
import entidades.Register;

public class Medicamento extends Product {

	protected String tipo; // Comprimidos, liquido, etc
	protected String posologia;
	protected Register register;
=======
import entities.Registro;

public class Medicament extends Produto {

	protected String medicamentType; // Comprimidos, liquido, etc
	protected String dosage;
	protected Registro registration;
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c:drogaria/src/entities/Medicament.java

	// Console
	Medicament[] medicament = {};
	Scanner scanner = new Scanner(System.in);
	private int deletingCode = 0, medicamentDeletingConfirmation = 0;

	public Medicament() {
		super();
	}

	public Medicament(String nomeProduto, String fabricanteProduto, String recomendacaoProduto,
					  String validadeProduto, String tipoMedicamento, String posologiaMedicamento) {
		super(nomeProduto, fabricanteProduto, recomendacaoProduto, validadeProduto);
		this.medicamentType = tipoMedicamento;
		this.dosage = posologiaMedicamento;
	}

	// Agregacao - Listagem e adicao
	public void listarRegistro() {
<<<<<<< HEAD:drogaria/src/entidades/Medicamento.java
		System.out.println("O registro do medicamento " + this.name + " �:" + register.code);
	}

	public void adicionarRegistro() {
		Register novoRegistro = new Register();
		novoRegistro = this.register;
=======
		System.out.println("O registro do medicamento " + this.nome + " é:" + registration.codigo);
	}

	public void adicionarRegistro() {
		Registro novoRegistro = new Registro();
		novoRegistro = this.registration;
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c:drogaria/src/entities/Medicament.java
		this.setRegistro(novoRegistro);
	}// Fim Agregacao

	public void menuMedicamento() {
<<<<<<< HEAD:drogaria/src/entidades/Medicamento.java
		System.out.println("\nInsira o que deseja fazer de acordo com as op��es seguintes:" + "\n(0) - Sair\n"
				+ "(1) - Cadastrar novo Medicamento\n" + "(2) - Listar Medicamentos\n"
				+ "(3) - Excluir Medicamento\n");
=======
		System.out.println("\nInsira o que deseja fazer de acordo com as opções seguintes:" + "\n(0) - Sair\n" +
						   "(1) - Cadastrar novo Medicamento\n" + "(2) - Listar Medicamentos\n" +
						   "(3) - Excluir Medicamento\n");
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c:drogaria/src/entities/Medicament.java
	}

	public void cadastrarMedicamento(ArrayList<Medicament> listaDeMedicamentos) {

		System.out.println("Digite o nome do Medicamento: ");
		String nomeProduto = ConsoleMenu.readString();

		System.out.println("Digite o fabricante do Medicamento: ");
		String fabricanteProduto = ConsoleMenu.readString();

<<<<<<< HEAD:drogaria/src/entidades/Medicamento.java
		System.out.println("Digite a recomenda��o de uso deste Medicamento: ");
		String recomendacaoProduto = Complementar.readString();
=======
		System.out.println("Digite a recomendação de uso deste Medicamento: ");
		String recomendacaoProduto = ConsoleMenu.readString();
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c:drogaria/src/entities/Medicament.java

		System.out.println("Digite a validade do Medicamento: ");
		String validadeProduto = ConsoleMenu.readString();

		System.out.println("Digite o tipo do Medicamento: ");
		String tipoMedicamento = ConsoleMenu.readString();

		System.out.println("Digite posologia recomendada do Medicamento: ");
		String posologiaMedicamento = ConsoleMenu.readString();

		Medicament medicament = new Medicament(nomeProduto, fabricanteProduto, recomendacaoProduto,
				validadeProduto, tipoMedicamento, posologiaMedicamento);

		listaDeMedicamentos.add(medicament);

<<<<<<< HEAD:drogaria/src/entidades/Medicamento.java
		System.out.println("O Medicamento " + medicamento.getName() + " foi cadastrado com sucesso!");
=======
		System.out.println("O Medicamento " + medicament.getNome() + " foi cadastrado com sucesso!");
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c:drogaria/src/entities/Medicament.java
	}

	public void listarMedicamentos(ArrayList<Medicament> listaDeMedicamentos) {
		if (listaDeMedicamentos.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("\nLista de cadastros de Medicamentos\n");
			for (int b = 0; b < listaDeMedicamentos.size(); b++) {
<<<<<<< HEAD:drogaria/src/entidades/Medicamento.java
				Medicamento t = listaDeMedicamentos.get(b);
				System.out.println("\nCadastro de n�mero:" + (b + 1));
				System.out.println("\nNome: " + t.getName());
				System.out.println("\nFabricante: " + t.getMaker());
				System.out.println("\nRecomenda��o: " + t.getRecommendation());
				System.out.println("\nValidade: " + t.getValidity().substring(0, 2) + "/"
						+ t.getValidity().substring(2, 4) + "/" + t.getValidity().substring(4, 8));
=======
				Medicament t = listaDeMedicamentos.get(b);
				System.out.println("\nCadastro de n�mero:" + (b + 1));
				System.out.println("\nNome: " + t.getNome());
				System.out.println("\nFabricante: " + t.getFabricante());
				System.out.println("\nRecomenda��o: " + t.getRecomendacao());
				System.out.println("\nValidade: " + t.getValidade().substring(0, 2) + "/"
						+ t.getValidade().substring(2, 4) + "/" + t.getValidade().substring(4, 8));
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c:drogaria/src/entities/Medicament.java
				System.out.println("\nTipo: " + t.getTipo());
				System.out.println("\nPosologia: " + t.getPosologia());
			}
			System.out.println("Fim da lista de cadastro.\n");
		}
	}

	public void excluirMedicamento(ArrayList<Medicament> listaDeMedicamentos) {

		if (listaDeMedicamentos.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("Digite o numero do cadastro de Medicamento que deseja excluir: ");
			this.setCodigoExclusao(scanner.nextInt());
<<<<<<< HEAD:drogaria/src/entidades/Medicamento.java
			System.out.println("Voc� deseja realmente excluir o cadastro de numero: " + this.codigoExclusao + "?"
=======
			System.out.println("Voc� deseja realmente excluir o cadastro de numero: " + this.deletingCode + "?"
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c:drogaria/src/entities/Medicament.java
					+ "\n(0) - N�o" + "\n(1) - Sim");
			this.setConfirmacaoExclusaoMedicamento(scanner.nextInt());
			if (medicamentDeletingConfirmation == 1) {
				this.setCodigoExclusao(deletingCode - 1);
				listaDeMedicamentos.remove(deletingCode);

				System.out.println("A lista foi alterada");
				listarMedicamentos(listaDeMedicamentos);
			}
			else if (medicamentDeletingConfirmation == 0) {
				this.setCodigoExclusao(0);
			}
			else {
				// Nothing to do
			}
		}

	}

	// Getters & Setters

	public String getTipo() {
		return medicamentType;
	}

	public void setTipo(String tipo) {
		this.medicamentType = tipo;
	}

	public String getPosologia() {
		return dosage;
	}

	public void setPosologia(String posologia) {
		this.dosage = posologia;
	}

<<<<<<< HEAD:drogaria/src/entidades/Medicamento.java
	public Register getRegistro() {
		return register;
	}

	public void setRegistro(Register register) {
		this.register = register;
=======
	public Registro getRegistro() {
		return registration;
	}

	public void setRegistro(Registro registro) {
		this.registration = registro;
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c:drogaria/src/entities/Medicament.java
	}

	public Medicament[] getMedicamento() {
		return medicament;
	}

	public void setMedicamento(Medicament[] medicamento) {
		this.medicament = medicamento;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public int getCodigoExclusao() {
		return deletingCode;
	}

	public void setCodigoExclusao(int codigoExclusao) {
		this.deletingCode = codigoExclusao;
	}

	public int getConfirmacaoExclusaoMedicamento() {
		return medicamentDeletingConfirmation;
	}

	public void setConfirmacaoExclusaoMedicamento(int confirmacaoExclusaoMedicamento) {
		this.medicamentDeletingConfirmation = confirmacaoExclusaoMedicamento;
	}

}