package entities;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Registro;

public class Medicament extends Produto {

	protected String tipo; // Comprimidos, liquido, etc
	protected String posologia;
	protected Registro registro;

	// Console
	Medicament[] medicamento = {};
	Scanner scanner = new Scanner(System.in);
	private int codigoExclusao = 0, confirmacaoExclusaoMedicamento = 0;

	public Medicament() {
		super();
	}

	public Medicament(String nomeProduto, String fabricanteProduto, String recomendacaoProduto,
			String validadeProduto, String tipoMedicamento, String posologiaMedicamento) {
		super(nomeProduto, fabricanteProduto, recomendacaoProduto, validadeProduto);
		this.tipo = tipoMedicamento;
		this.posologia = posologiaMedicamento;
	}

	// Agregacao - Listagem e adicao
	public void listarRegistro() {
		System.out.println("O registro do medicamento " + this.nome + " �:" + registro.codigo);
	}

	public void adicionarRegistro() {
		Registro novoRegistro = new Registro();
		novoRegistro = this.registro;
		this.setRegistro(novoRegistro);
	}// Fim Agregacao

	public void menuMedicament() {
		System.out.println("\nInsira o que deseja fazer de acordo com as op��es seguintes:" + "\n(0) - Sair\n"
				+ "(1) - Cadastrar novo Medicamento\n" + "(2) - Listar Medicamentos\n"
				+ "(3) - Excluir Medicamento\n");
	}

	public void registerMedicament(ArrayList<Medicament> listaDeMedicamentos) {

		System.out.println("Digite o nome do Medicamento: ");
		String nomeProduto = Complementary.readString();

		System.out.println("Digite o fabricante do Medicamento: ");
		String fabricanteProduto = Complementary.readString();

		System.out.println("Digite a recomenda��o de uso deste Medicamento: ");
		String recomendacaoProduto = Complementary.readString();

		System.out.println("Digite a validade do Medicamento: ");
		String validadeProduto = Complementary.readString();

		System.out.println("Digite o tipo do Medicamento: ");
		String tipoMedicamento = Complementary.readString();

		System.out.println("Digite posologia recomendada do Medicamento: ");
		String posologiaMedicamento = Complementary.readString();

		Medicament medicamento = new Medicament(nomeProduto, fabricanteProduto, recomendacaoProduto,
				validadeProduto, tipoMedicamento, posologiaMedicamento);

		listaDeMedicamentos.add(medicamento);

		System.out.println("O Medicamento " + medicamento.getNome() + " foi cadastrado com sucesso!");
	}

	public void listMedicaments(ArrayList<Medicament> listaDeMedicamentos) {
		if (listaDeMedicamentos.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("\nLista de cadastros de Medicamentos\n");
			for (int b = 0; b < listaDeMedicamentos.size(); b++) {
				Medicament t = listaDeMedicamentos.get(b);
				System.out.println("\nCadastro de n�mero:" + (b + 1));
				System.out.println("\nNome: " + t.getNome());
				System.out.println("\nFabricante: " + t.getFabricante());
				System.out.println("\nRecomenda��o: " + t.getRecomendacao());
				System.out.println("\nValidade: " + t.getValidade().substring(0, 2) + "/"
						+ t.getValidade().substring(2, 4) + "/" + t.getValidade().substring(4, 8));
				System.out.println("\nTipo: " + t.getTipo());
				System.out.println("\nPosologia: " + t.getPosologia());
			}
			System.out.println("Fim da lista de cadastro.\n");
		}
	}

	public void deleteMedicament(ArrayList<Medicament> listaDeMedicamentos) {

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
				listMedicaments(listaDeMedicamentos);
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

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public Medicament[] getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicament[] medicamento) {
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