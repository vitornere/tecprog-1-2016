package entidades;

import java.util.ArrayList;
import java.util.Scanner;

import entidades.Register;

public class Medicamento extends Product {

	protected String tipo; // Comprimidos, liquido, etc
	protected String posologia;
	protected Register register;

	// Console
	Medicamento[] medicamento = {};
	Scanner scanner = new Scanner(System.in);
	private int codigoExclusao = 0, confirmacaoExclusaoMedicamento = 0;

	public Medicamento() {
		super();
	}

	public Medicamento(String nomeProduto, String fabricanteProduto, String recomendacaoProduto,
			String validadeProduto, String tipoMedicamento, String posologiaMedicamento) {
		super(nomeProduto, fabricanteProduto, recomendacaoProduto, validadeProduto);
		this.tipo = tipoMedicamento;
		this.posologia = posologiaMedicamento;
	}

	// Agregacao - Listagem e adicao
	public void listarRegistro() {
		System.out.println("O registro do medicamento " + this.name + " �:" + register.code);
	}

	public void adicionarRegistro() {
		Register novoRegistro = new Register();
		novoRegistro = this.register;
		this.setRegistro(novoRegistro);
	}// Fim Agregacao

	public void menuMedicamento() {
		System.out.println("\nInsira o que deseja fazer de acordo com as op��es seguintes:" + "\n(0) - Sair\n"
				+ "(1) - Cadastrar novo Medicamento\n" + "(2) - Listar Medicamentos\n"
				+ "(3) - Excluir Medicamento\n");
	}

	public void cadastrarMedicamento(ArrayList<Medicamento> listaDeMedicamentos) {

		System.out.println("Digite o nome do Medicamento: ");
		String nomeProduto = Complementar.readString();

		System.out.println("Digite o fabricante do Medicamento: ");
		String fabricanteProduto = Complementar.readString();

		System.out.println("Digite a recomenda��o de uso deste Medicamento: ");
		String recomendacaoProduto = Complementar.readString();

		System.out.println("Digite a validade do Medicamento: ");
		String validadeProduto = Complementar.readString();

		System.out.println("Digite o tipo do Medicamento: ");
		String tipoMedicamento = Complementar.readString();

		System.out.println("Digite posologia recomendada do Medicamento: ");
		String posologiaMedicamento = Complementar.readString();

		Medicamento medicamento = new Medicamento(nomeProduto, fabricanteProduto, recomendacaoProduto,
				validadeProduto, tipoMedicamento, posologiaMedicamento);

		listaDeMedicamentos.add(medicamento);

		System.out.println("O Medicamento " + medicamento.getName() + " foi cadastrado com sucesso!");
	}

	public void listarMedicamentos(ArrayList<Medicamento> listaDeMedicamentos) {
		if (listaDeMedicamentos.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("\nLista de cadastros de Medicamentos\n");
			for (int b = 0; b < listaDeMedicamentos.size(); b++) {
				Medicamento t = listaDeMedicamentos.get(b);
				System.out.println("\nCadastro de n�mero:" + (b + 1));
				System.out.println("\nNome: " + t.getName());
				System.out.println("\nFabricante: " + t.getMaker());
				System.out.println("\nRecomenda��o: " + t.getRecommendation());
				System.out.println("\nValidade: " + t.getValidity().substring(0, 2) + "/"
						+ t.getValidity().substring(2, 4) + "/" + t.getValidity().substring(4, 8));
				System.out.println("\nTipo: " + t.getTipo());
				System.out.println("\nPosologia: " + t.getPosologia());
			}
			System.out.println("Fim da lista de cadastro.\n");
		}
	}

	public void excluirMedicamento(ArrayList<Medicamento> listaDeMedicamentos) {

		if (listaDeMedicamentos.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("Digite o numero do cadastro de Medicamento que deseja excluir: ");
			this.setCodigoExclusao(scanner.nextInt());
			System.out.println("Voc� deseja realmente excluir o cadastro de numero: " + this.codigoExclusao + "?"
					+ "\n(0) - N�o" + "\n(1) - Sim");
			this.setConfirmacaoExclusaoMedicamento(scanner.nextInt());
			if (confirmacaoExclusaoMedicamento == 1) {
				this.setCodigoExclusao(codigoExclusao - 1);
				listaDeMedicamentos.remove(codigoExclusao);

				System.out.println("A lista foi alterada");
				listarMedicamentos(listaDeMedicamentos);
			}
			else if (confirmacaoExclusaoMedicamento == 0) {
				this.setCodigoExclusao(0);
			}
		}

	}

	// Getters & Setters

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public Register getRegistro() {
		return register;
	}

	public void setRegistro(Register register) {
		this.register = register;
	}

	public Medicamento[] getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento[] medicamento) {
		this.medicamento = medicamento;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public int getCodigoExclusao() {
		return codigoExclusao;
	}

	public void setCodigoExclusao(int codigoExclusao) {
		this.codigoExclusao = codigoExclusao;
	}

	public int getConfirmacaoExclusaoMedicamento() {
		return confirmacaoExclusaoMedicamento;
	}

	public void setConfirmacaoExclusaoMedicamento(int confirmacaoExclusaoMedicamento) {
		this.confirmacaoExclusaoMedicamento = confirmacaoExclusaoMedicamento;
	}

}