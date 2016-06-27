/**
 * Name: Client.java
 * This class is a subclass of Person and keeps Clients information.
 */

package entities;

import java.util.ArrayList;
import java.util.Scanner;

import interfaces.*;

public class Client extends Person implements Recommended {

	private static final int FIRST = 0;
	private static final int YES = 1;
	private static final int NO = 0;

	protected String email = " ";
	protected Cashier[] cashiers;
	Clerk[] clerk = {};
	Client[] client = {};
	Scanner scanner = new Scanner(System.in);
	private int deleteCode = 0; // Client code to be deleted.
	private int confirmationClientExclusion = 0; // Delete confirmation code ( 0
													// or 1).

	/**
	 * Creates an empty object.
	 */

	public Client() {
		super();
	}

	/**
	 * 
	 * @param personIdentity
	 * @param cpfPerson
	 * @param cpfDigitPerson
	 * @param personName
	 * @param personLastName
	 * @param personAddress
	 * @param personPhone
	 */

	public Client(String personIdentity, String cpfPerson, int cpfDigitPerson, String personName, String personLastName,
			String personAddress, String personPhone) {
		super(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName, personAddress, personPhone);
		System.out.println("Possui pelo menos um balconista para atend�-lo!!");
		Clerk.setStatusClerk(true);
	}

	/**
	 * 
	 * @param personIdentity
	 * @param cpfPerson
	 * @param cpfDigitPerson
	 * @param personName
	 * @param personLastName
	 * @param personAddress
	 * @param personPhone
	 * @param emailClient
	 */

	public Client(String personIdentity, String cpfPerson, int cpfDigitPerson, String personName, String personLastName,
			String personAddress, String personPhone, String emailClient) {
		super(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName, personAddress, personPhone);
		this.email = emailClient;

	}

	/**
	 * Method for the selection of the amount of medicine recommended by the
	 * clerk in accordance with the parameters.
	 * 
	 * @param medicineType
	 * @param use
	 */
	public void recommendedMedicine(String medicineType, String use) {
		assert (medicineType != null);
		assert (use != null);

		if (medicineType == "TARJA PRETA" && use == "ADULTO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 1");
		} else {
			// Nothing to do.
		}

		if (medicineType == "TARJA PRETA" && use == "PEDIATRICO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 0");
		} else {
			// Nothing to do.
		}

		if (medicineType == "GENERICO" && use == "ADULTO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 5");
		} else {
			// Nothing to do.
		}

		if (medicineType == "GENERICO" && use == "PEDIATRICO") {
			System.out.println("A quantidade de remedios recomendados pelo Balconista é: 3");
		} else {
			// Nothing to do.
		}

	}

	/**
	 * Method that lists the registered cashiers.
	 */

	public void cashierList() {
		for (int position = FIRST; position < (cashiers.length); position += 1) {
			System.out.println(("Caixa [" + (position + 1) + "]:" + cashiers[position]));
		}
	}

	/**
	 * Method that prints a menu with options for customer to choose . The
	 * options are 0 to 3. Where 0 is exit, 1 is register a new client, 2 is
	 * client list and 3 is to delete a client.
	 */

	public void clientMenu() {
		System.out.println("\nInsira o que deseja fazer de acordo com as opções seguintes:" + "\n(0) - Sair\n"
				+ "(1) - Cadastrar novo Cliente\n" + "(2) - Listar Clientes\n" + "(3) - Excluir Cliente\n");
	}

	/**
	 * Method to register a client.
	 * 
	 * @param clientsList
	 */

	public void clientRegister(ArrayList<Client> clientsList) {
		
		String personIdentity = null;
		String cpfPerson = null;
		int cpfDigitPerson = 0;
		String personName = null;
		String personLastName = null;
		String personAddress = null;
		String personPhone = null;
		String emailClient = null;

		System.out.println("Digite o rg do Cliente: ");
		try {
			personIdentity = ConsoleMenu.readString();
		} catch (NullPointerException e) {
			System.out.println("RG vazio. Exceção " + e);
		}

		System.out.println("Digite o cpf do Cliente: ");
		try {
			cpfPerson = ConsoleMenu.readString();
		} catch (NullPointerException e) {
			System.out.println("CPF vazio. Exceção " + e);
		}

		System.out.println("Digite o digito do cpf do Cliente: ");
		try {
			cpfDigitPerson = ConsoleMenu.readInt();
		} catch (NumberFormatException e) {
			System.out.println("Exceção " + e);
		}

		System.out.println("Digite o nome do Cliente: ");
		try {
			personName = ConsoleMenu.readString();
		} catch (NullPointerException e) {
			System.out.println("Nome vazio. Exceção " + e);
		}

		System.out.println("Digite o sobrenome completo do Cliente: ");
		try {
			personLastName = ConsoleMenu.readString();
		} catch (NullPointerException e) {
			System.out.println("Ultimo nome vazio. Exceção " + e);
		}

		System.out.println("Digite o endereco do Cliente: ");
		try {
			personAddress = ConsoleMenu.readString();
		} catch (NullPointerException e) {
			System.out.println("Endereço vazio. Exceção " + e);
		}

		System.out.println("Digite o telefone do Cliente:");
		try {
			personPhone = ConsoleMenu.readString();
		} catch (NullPointerException e) {
			System.out.println("Telefone vazio. Exceção " + e);
		}

		System.out.println("Digite o email do Cliente");
		try {
			emailClient = ConsoleMenu.readString();
		} catch (NullPointerException e) {
			System.out.println("Email vazio. Exceção " + e);
		}

		Client client = new Client(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName,
				personAddress, personPhone, emailClient);

		clientsList.add(client);

		System.out.println("O(A) Cliente " + client.getName() + " foi cadastrado(a) com sucesso!");
	}

	/**
	 * Method to list the clients registered.
	 * 
	 * @param clientsList
	 */
	public void listClients(ArrayList<Client> clientsList) {
		if (clientsList.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		} else {
			System.out.println("\nLista de cadastros de Clientes\n");
			for (int position = FIRST; position < clientsList.size(); position++) {
				Client t = clientsList.get(position);
				System.out.println("\nCadastro de número:" + (position + 1));

				System.out.println("\nNome: " + t.getName() + " " + t.getPastName());

				System.out.println("\nRG: " + t.getIdentity().substring(0, 2) + "-"
						+ t.getIdentity().substring(2, t.getIdentity().length()));

				System.out.println("Cpf: " + t.getCpfPerson().substring(0, 3) + "." + t.getCpfPerson().substring(3, 6)
						+ "." + t.getCpfPerson().substring(6, 9) + "-" + t.getDigitCpfPerson());

				System.out.println("\nTelefone: (" + t.getPhone().substring(0, 2) + ") " + t.getPhone().substring(2, 6)
						+ "-" + t.getPhone().substring(6, 10));

				System.out.println("\nEmail: " + t.getEmail());
			}
			System.out.println("Fim da lista de cadastro de Clientes.\n");
		}

	}

	/**
	 * Method to delete a client.
	 * 
	 * @param clientList
	 */

	public void deleteClient(ArrayList<Client> clientList) {

		if (clientList.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		} else {
			System.out.println("Digite o numero do cadastro de Cliente que deseja excluir: ");
			this.setCodeExclusion(scanner.nextInt());
			System.out.println("Você deseja realmente excluir o cadastro de numero: " + this.deleteCode + "?"
					+ "\n(0) - Não" + "\n(1) - Sim");
			this.setConfirmationClientExclusion(scanner.nextInt());
			if (confirmationClientExclusion == YES) {
				this.setCodeExclusion(deleteCode - 1);
				clientList.remove(deleteCode);

				System.out.println("A lista foi alterada");
				listClients(clientList);
			} else if (confirmationClientExclusion == NO) {
				this.setCodeExclusion(0);
			} else {
				// nothing to do.
			}
		}

	}

	/**
	 * Getters and Setters
	 */

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

	public int getConfirmationClientExclusion() {
		return confirmationClientExclusion;
	}

	public void setConfirmationClientExclusion(int confirmacaoExclusaoCliente) {
		this.confirmationClientExclusion = confirmacaoExclusaoCliente;
	}

	public void recommendedMedicines(String medicineType, String use) {

	}

	@Override
	public void medicineRecommended(String typeMedicine, String use) {
		// TODO Auto-generated method stub

	}

}