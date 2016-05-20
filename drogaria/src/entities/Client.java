package entities;

import java.util.ArrayList;
import java.util.Scanner;

import interfaces.*;

public class Client extends Person implements Recommended {

	protected String email;
	protected Cashier[] caixas;
	Clerk[] balconistas = {};

	// Console
	Client[] cliente = {};
	Scanner scanner = new Scanner(System.in);
	private int codigoExclusao = 0, confirmacaoExclusaoCliente = 0;

	public Client() {
		super();
	}

	// Construtor s� para heran�a
	public Client(String rgPessoa, String cpfPessoa, int digitoCpfPessoa, String nomePessoa,
			String sobrenomePessoa, String enderecoPessoa, String telefonePessoa) {
		super(rgPessoa, cpfPessoa, digitoCpfPessoa, nomePessoa, sobrenomePessoa, enderecoPessoa, telefonePessoa);
		System.out.println("Possui pelo menos um balconista para atend�-lo!!"); // ---> DEPENDENCIA
		Clerk.setStatusBalconista(true);
		// Por ser static, n�o � necess�rio instanciar.
	}

	public Client(String rgPessoa, String cpfPessoa, int digitoCpfPessoa, String nomePessoa,
			String sobrenomePessoa, String enderecoPessoa, String telefonePessoa, String emailCliente) {
		super(rgPessoa, cpfPessoa, digitoCpfPessoa, nomePessoa, sobrenomePessoa, enderecoPessoa, telefonePessoa);
		this.email = emailCliente;

	}

	// Interface
	public void recommendedMedicaments(String tipoRemedio, String uso) {
		if (tipoRemedio == "TARJA PRETA" && uso == "ADULTO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista �: 1");
		}

		if (tipoRemedio == "TARJA PRETA" && uso == "PEDIATRICO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista �: 0");
		}

		if (tipoRemedio == "GENERICO" && uso == "ADULTO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista �: 5");
		}

		if (tipoRemedio == "GENERICO" && uso == "PEDIATRICO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista �: 3");
		}

	}

	public void listarCaixas() {
		for (int x = 0; x < (caixas.length); x += 1) {
			System.out.println(("Caixa [" + x + "]:" + caixas[x]));
		}
	}

	// Console

	public void menuClient() {
		System.out.println("\nInsira o que deseja fazer de acordo com as op��es seguintes:" + "\n(0) - Sair\n"
				+ "(1) - Cadastrar novo Cliente\n" + "(2) - Listar Clientes\n" + "(3) - Excluir Cliente\n");
	}

	public void registerClient(ArrayList<Client> listaDeClientes) {

		System.out.println("Digite o rg do Cliente: ");
		String rgPessoa = Complementary.readString();

		System.out.println("Digite o cpf do Cliente: ");
		String cpfPessoa = Complementary.readString();

		System.out.println("Digite o digito do cpf do Cliente: ");
		int digitoCpfPessoa = Complementary.readInt();

		System.out.println("Digite o nome do Cliente: ");
		String nomePessoa = Complementary.readString();

		System.out.println("Digite o sobrenome completo do Cliente: ");
		String sobrenomePessoa = Complementary.readString();

		System.out.println("Digite o endereco do Cliente: ");
		String enderecoPessoa = Complementary.readString();

		System.out.println("Digite o telefone do Cliente:");
		String telefonePessoa = Complementary.readString();

		System.out.println("Digite o email do Cliente");
		String emailCliente = Complementary.readString();

		Client cliente = new Client(rgPessoa, cpfPessoa, digitoCpfPessoa, nomePessoa, sobrenomePessoa,
				enderecoPessoa, telefonePessoa, emailCliente);

		listaDeClientes.add(cliente);

		System.out.println("O(A) Cliente " + cliente.getName() + " foi cadastrado(a) com sucesso!");
	}

	public void listClients(ArrayList<Client> listaDeClientes) {
		if (listaDeClientes.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("\nLista de cadastros de Clientes\n");
			for (int b = 0; b < listaDeClientes.size(); b++) {
				Client t = listaDeClientes.get(b);
				System.out.println("\nCadastro de n�mero:" + (b + 1));

				System.out.println("\nNome: " + t.getName() + " " + t.getPastName());

				System.out.println("\nRG: " + t.getIdentity().substring(0, 2) + "-"
						+ t.getIdentity().substring(2, t.getIdentity().length()));

				System.out.println("Cpf: " + t.getCpfPerson().substring(0, 3) + "."
						+ t.getCpfPerson().substring(3, 6) + "." + t.getCpfPerson().substring(6, 9) + "-"  + t.getDigitCpfPerson());

				System.out.println("\nTelefone: (" + t.getPhone().substring(0, 2) + ") "
						+ t.getPhone().substring(2, 6) + "-" + t.getPhone().substring(6, 10));

				System.out.println("\nEmail: " + t.getEmail());
			}
			System.out.println("Fim da lista de cadastro de Clientes.\n");
		}

	}

	public void deleteClient(ArrayList<Client> listaDeClientes) {

		if (listaDeClientes.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("Digite o numero do cadastro de Cliente que deseja excluir: ");
			this.setCodigoExclusao(scanner.nextInt());
			System.out.println("Voc� deseja realmente excluir o cadastro de numero: " + this.codigoExclusao + "?"
					+ "\n(0) - N�o" + "\n(1) - Sim");
			this.setConfirmacaoExclusaoCliente(scanner.nextInt());
			if (confirmacaoExclusaoCliente == 1) {
				this.setCodigoExclusao(codigoExclusao - 1);
				listaDeClientes.remove(codigoExclusao);

				System.out.println("A lista foi alterada");
				listClients(listaDeClientes);
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

	public Cashier[] getCaixas() {
		return caixas;
	}

	public void setCaixas(Cashier[] caixas) {
		this.caixas = caixas;
	}

	public Clerk[] getBalconistas() {
		return balconistas;
	}

	public void setBalconistas(Clerk[] balconistas) {
		this.balconistas = balconistas;
	}

	public Client[] getCliente() {
		return cliente;
	}

	public void setCliente(Client[] cliente) {
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

	@Override
	public void medicineRecommended(String typeMedicine, String uso) {
		// TODO Auto-generated method stub
		
	}

}
