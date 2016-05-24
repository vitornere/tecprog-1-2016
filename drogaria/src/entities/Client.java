package entities;

import java.util.ArrayList;
import java.util.Scanner;

import interfaces.*;

public class Client extends Person implements Recommended {

	private static final int FIRST = 0;
	private static final int YES = 1;
	private static final int NO = 0;
	
	protected String email;
	protected Cashier[] cashiers;
	Clerk[] clerk = {};
	Client[] client = {};
	Scanner scanner = new Scanner(System.in);
	private int deleteCode = 0;
	private int confirmationClientExclusion = 0;

	public Client() {
		super();
	}

	public Client(String personIdentity, String cpfPerson, int cpfDigitPerson, String personName,
			String personLastName, String personAddress, String personPhone) {
		super(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName, personAddress, personPhone);
		System.out.println("Possui pelo menos um balconista para atend�-lo!!");
		Clerk.setStatusClerk(true);
	}

	public Client(String personIdentity, String cpfPerson, int cpfDigitPerson, String personName,
			String personLastName, String personAddress, String personPhone, String emailClient) {
		super(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName, personAddress, personPhone);
		this.email = emailClient;

	}

	// Interface
	public void recommendedMedicine(String medicineType, String use) {
		if (medicineType == "TARJA PRETA" && use == "ADULTO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 1");
		}

		if (medicineType == "TARJA PRETA" && use == "PEDIATRICO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 0");
		}

		if (medicineType == "GENERICO" && use == "ADULTO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 5");
		}

		if (medicineType == "GENERICO" && use == "PEDIATRICO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 3");
		}

	}

	public void cashierList() {
		for (int position = FIRST; position < (cashiers.length); position += 1) {
			System.out.println(("Caixa [" + (position + 1) + "]:" + cashiers[position]));
		}
	}

	// Console

	public void clientMenu() {
		System.out.println("\nInsira o que deseja fazer de acordo com as op��es seguintes:" + "\n(0) - Sair\n"
				+ "(1) - Cadastrar novo Cliente\n" + "(2) - Listar Clientes\n" + "(3) - Excluir Cliente\n");
	}

	public void clientRegister(ArrayList<Client> clientsList) {

		System.out.println("Digite o rg do Cliente: ");
		String rgPessoa = ConsoleMenu.readString();

		System.out.println("Digite o cpf do Cliente: ");
		String cpfPerson = ConsoleMenu.readString();

		System.out.println("Digite o digito do cpf do Cliente: ");
		int cpfDigitPerson = ConsoleMenu.readInt();

		System.out.println("Digite o nome do Cliente: ");
		String personName = ConsoleMenu.readString();

		System.out.println("Digite o sobrenome completo do Cliente: ");
		String personLastName = ConsoleMenu.readString();

		System.out.println("Digite o endereco do Cliente: ");
		String personAddress = ConsoleMenu.readString();

		System.out.println("Digite o telefone do Cliente:");
		String personPhone = ConsoleMenu.readString();

		System.out.println("Digite o email do Cliente");
		String emailClient = ConsoleMenu.readString();

		Client client = new Client(rgPessoa, cpfPerson, cpfDigitPerson, personName, personLastName,
				personAddress, personPhone, emailClient);

		clientsList.add(client);

		System.out.println("O(A) Cliente " + client.getName() + " foi cadastrado(a) com sucesso!");
	}

	public void listCLients(ArrayList<Client> clientsList) {
		if (clientsList.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("\nLista de cadastros de Clientes\n");
			for (int position = FIRST; position < clientsList.size(); position++) {
				Client t = clientsList.get(position);
				System.out.println("\nCadastro de n�mero:" + (position + 1));

				System.out.println("\nNome: " + t.getName() + " " + t.getLastName());

				System.out.println("\nRG: " + t.getRg().substring(0, 2) + "-"
						+ t.getRg().substring(2, t.getRg().length()));

				System.out.println("Cpf: " + t.getCpf().substring(0, 3) + "."
						+ t.getCpf().substring(3, 6) + "." + t.getCpf().substring(6, 9) + "-"  + t.getDigitoCpf());

				System.out.println("\nTelefone: (" + t.getPhone().substring(0, 2) + ") "
						+ t.getPhone().substring(2, 6) + "-" + t.getPhone().substring(6, 10));

				System.out.println("\nEmail: " + t.getEmail());
			}
			System.out.println("Fim da lista de cadastro de Clientes.\n");
		}

	}

	public void deleteClient(ArrayList<Client> clientList) {

		if (clientList.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("Digite o numero do cadastro de Cliente que deseja excluir: ");
			this.setCodeExclusion(scanner.nextInt());
			System.out.println("Voc� deseja realmente excluir o cadastro de numero: " + this.deleteCode + "?"
					+ "\n(0) - N�o" + "\n(1) - Sim");
			this.setConfirmationCodeExclusion(scanner.nextInt());
			if (confirmationClientExclusion == YES) {
				this.setCodeExclusion(deleteCode - 1);
				clientList.remove(deleteCode);

				System.out.println("A lista foi alterada");
				listCLients(clientList);
			}
			else if (confirmationClientExclusion == NO) {
				this.setCodeExclusion(0);
			}
			else {
				//nothing to do.
			}
		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cashier[] getCashier() {
		return cashiers;
	}

	public void setCashier(Cashier[] cashiers) {
		this.cashiers = cashiers;
	}

	public Clerk[] getClerk() {
		return clerk;
	}

	public void setClerk(Clerk[] clerks) {
		this.clerk = clerks;
	}

	public Client[] getClient() {
		return client;
	}

	public void setClient(Client[] client) {
		this.client = client;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public int getDeleteCode() {
		return deleteCode;
	}

	public void setCodeExclusion(int codigoExclusao) {
		this.deleteCode = codigoExclusao;
	}

	public int getConfirmacaoExclusaoCliente() {
		return confirmationClientExclusion;
	}

	public void setConfirmationCodeExclusion(int confirmacaoExclusaoCliente) {
		this.confirmationClientExclusion = confirmacaoExclusaoCliente;
	}

	@Override
	public void recommendedMedicines(String medicineType, String use) {
		// TODO Auto-generated method stub
		
	}

}
