package entidades;

import java.util.ArrayList;
import java.util.Scanner;

import interfaces.*;

public class Cliente extends Pessoa implements Recomendavel {

	protected String email;
	protected Caixa[] caixas;
	Balconista[] balconistas = {};

	// Console
	Cliente[] cliente = {};
	Scanner scanner = new Scanner(System.in);
	private int codigoExclusao = 0, confirmacaoExclusaoCliente = 0;

	public Cliente() {
		super();
	}

	// Construtor só para herança
	public Cliente(String rgPessoa, String cpfPessoa, int digitoCpfPessoa, String nomePessoa,
			String sobrenomePessoa, String enderecoPessoa, String telefonePessoa) {
		super(rgPessoa, cpfPessoa, digitoCpfPessoa, nomePessoa, sobrenomePessoa, enderecoPessoa, telefonePessoa);
		System.out.println("Possui pelo menos um balconista para atendê-lo!!"); // ---> DEPENDENCIA
		Balconista.setStatusBalconista(true);
		// Por ser static, não é necessário instanciar.
	}

	public Cliente(String rgPessoa, String cpfPessoa, int digitoCpfPessoa, String nomePessoa,
			String sobrenomePessoa, String enderecoPessoa, String telefonePessoa, String emailCliente) {
		super(rgPessoa, cpfPessoa, digitoCpfPessoa, nomePessoa, sobrenomePessoa, enderecoPessoa, telefonePessoa);
		this.email = emailCliente;

	}

	// Interface
	public void remediosRecomendados(String tipoRemedio, String uso) {
		if (tipoRemedio == "TARJA PRETA" && uso == "ADULTO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 1");
		}

		if (tipoRemedio == "TARJA PRETA" && uso == "PEDIATRICO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 0");
		}

		if (tipoRemedio == "GENERICO" && uso == "ADULTO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 5");
		}

		if (tipoRemedio == "GENERICO" && uso == "PEDIATRICO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 3");
		}

	}

	public void listarCaixas() {
		for (int x = 0; x < (caixas.length); x += 1) {
			System.out.println(("Caixa [" + x + "]:" + caixas[x]));
		}
	}

	// Console

	public void menuCliente() {
		System.out.println("\nInsira o que deseja fazer de acordo com as opções seguintes:" + "\n(0) - Sair\n"
				+ "(1) - Cadastrar novo Cliente\n" + "(2) - Listar Clientes\n" + "(3) - Excluir Cliente\n");
	}

	public void cadastrarCliente(ArrayList<Cliente> listaDeClientes) {

		System.out.println("Digite o rg do Cliente: ");
		String rgPessoa = Complementar.readString();

		System.out.println("Digite o cpf do Cliente: ");
		String cpfPessoa = Complementar.readString();

		System.out.println("Digite o digito do cpf do Cliente: ");
		int digitoCpfPessoa = Complementar.readInt();

		System.out.println("Digite o nome do Cliente: ");
		String nomePessoa = Complementar.readString();

		System.out.println("Digite o sobrenome completo do Cliente: ");
		String sobrenomePessoa = Complementar.readString();

		System.out.println("Digite o endereco do Cliente: ");
		String enderecoPessoa = Complementar.readString();

		System.out.println("Digite o telefone do Cliente:");
		String telefonePessoa = Complementar.readString();

		System.out.println("Digite o email do Cliente");
		String emailCliente = Complementar.readString();

		Cliente cliente = new Cliente(rgPessoa, cpfPessoa, digitoCpfPessoa, nomePessoa, sobrenomePessoa,
				enderecoPessoa, telefonePessoa, emailCliente);

		listaDeClientes.add(cliente);

		System.out.println("O(A) Cliente " + cliente.getNome() + " foi cadastrado(a) com sucesso!");
	}

	public void listarClientes(ArrayList<Cliente> listaDeClientes) {
		if (listaDeClientes.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("\nLista de cadastros de Clientes\n");
			for (int b = 0; b < listaDeClientes.size(); b++) {
				Cliente t = listaDeClientes.get(b);
				System.out.println("\nCadastro de número:" + (b + 1));

				System.out.println("\nNome: " + t.getNome() + " " + t.getSobrenome());

				System.out.println("\nRG: " + t.getRg().substring(0, 2) + "-"
						+ t.getRg().substring(2, t.getRg().length()));

				System.out.println("Cpf: " + t.getCpf().substring(0, 3) + "."
						+ t.getCpf().substring(3, 6) + "." + t.getCpf().substring(6, 9) + "-"  + t.getDigitoCpf());

				System.out.println("\nTelefone: (" + t.getTelefone().substring(0, 2) + ") "
						+ t.getTelefone().substring(2, 6) + "-" + t.getTelefone().substring(6, 10));

				System.out.println("\nEmail: " + t.getEmail());
			}
			System.out.println("Fim da lista de cadastro de Clientes.\n");
		}

	}

	public void excluirCliente(ArrayList<Cliente> listaDeClientes) {

		if (listaDeClientes.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("Digite o numero do cadastro de Cliente que deseja excluir: ");
			this.setCodigoExclusao(scanner.nextInt());
			System.out.println("Você deseja realmente excluir o cadastro de numero: " + this.codigoExclusao + "?"
					+ "\n(0) - Não" + "\n(1) - Sim");
			this.setConfirmacaoExclusaoCliente(scanner.nextInt());
			if (confirmacaoExclusaoCliente == 1) {
				this.setCodigoExclusao(codigoExclusao - 1);
				listaDeClientes.remove(codigoExclusao);

				System.out.println("A lista foi alterada");
				listarClientes(listaDeClientes);
			}
			else if (confirmacaoExclusaoCliente == 0) {
				this.setCodigoExclusao(0);
			}
		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Caixa[] getCaixas() {
		return caixas;
	}

	public void setCaixas(Caixa[] caixas) {
		this.caixas = caixas;
	}

	public Balconista[] getBalconistas() {
		return balconistas;
	}

	public void setBalconistas(Balconista[] balconistas) {
		this.balconistas = balconistas;
	}

	public Cliente[] getCliente() {
		return cliente;
	}

	public void setCliente(Cliente[] cliente) {
		this.cliente = cliente;
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

	public int getConfirmacaoExclusaoCliente() {
		return confirmacaoExclusaoCliente;
	}

	public void setConfirmacaoExclusaoCliente(int confirmacaoExclusaoCliente) {
		this.confirmacaoExclusaoCliente = confirmacaoExclusaoCliente;
	}

}
