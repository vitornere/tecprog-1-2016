/**
 * Name: Cashier.java
 * This class is a subclass of Person and keeps cashier information.
 */

package entities;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Client;
import entities.Clerk;

public class Cashier extends Person {

	private static final int INCOME = 0;
	private static final int OUTGO = 1;
	private static final int FIRST = 0;
	private static final double PAYMENT_ACCEPTED = 1;
	private static final double PAYMENT_NOT_ACCEPTED = 0;
	private static final int YES = 1;
	private static final int NO = 0;

	private float CurrentBalance = 0; // Saves the current value ( total value
										// of past products).
	private int type = 0; // Informs the type if it is income or expense ( 0 or
							// 1, respectively) .
	private float value = 0; // Store the value of the last product.
	private String date = "";
	private String description = ""; // product description.
	private Clerk clerk;
	protected Client[] clients;
	protected Clerk[] employee;
	private int code = 0; // Store the product code.
	private double confirmation = 0;

	Cashier[] cashier = {}; // Store the cashier information that is servicing
							// the client.
	Scanner scanner = new Scanner(System.in);
	int operation = 0;
	int operationCashier = 0;
	int repeatCashierRegister = 0;
	int repeatCashier = 0;
	int deleteCode = 0; // Store the information of exclusion desire ( 0 - no, 1
						// - yes) .
	int confirmationExclusionCashier = 0; // Store the confirmation of exclusion
											// desire ( 0 - no, 1 - yes) .

	/**
	 * Creates an empty object.
	 */

	public Cashier() {
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
	 * @param trasitionType
	 * @param trasitionValue
	 * @param trasitionDate
	 * @param trasitionDescription
	 * @param code
	 */

	public Cashier(String personIdentity, String cpfPerson, int cpfDigitPerson, String personName,
			String personLastName, String personAddress, String personPhone, int trasitionType, float trasitionValue,
			String trasitionDate, String trasitionDescription, int code) {
		super(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName, personAddress, personPhone);

		if (type == INCOME || type == OUTGO) {
			this.type = trasitionType;
			this.value = trasitionValue;
			this.date = trasitionDate;
			this.description = trasitionDescription;
			this.code = code;
		} else {
			System.out.println("Caixa nao cadastrado.");

		}

		Clerk clerk = new Clerk(this);
		this.clerk = clerk;

		System.out.println("Verificação de um funcionário para atendê-lo.");
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
	 * @param code
	 */

	public Cashier(String personIdentity, String cpfPerson, int cpfDigitPerson, String personName,
			String personLastName, String personAddress, String personPhone, int code) {
		super(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName, personAddress, personPhone);
		this.code = code;
	}

	/**
	 * Creates a clerk.
	 */

	public void createClerk() {
		Clerk clerk = new Clerk(this);
		this.clerk = clerk;
	}

	/**
	 * Gets the payment confirmation.
	 */

	public double paymentConfirmation() {
		assert (Person.getPaymentConfirmation() < 2);
		assert (Person.getPaymentConfirmation() > -1);

		if (Person.getPaymentConfirmation() == PAYMENT_ACCEPTED) {
			confirmation = PAYMENT_ACCEPTED;
		} else {
			confirmation = PAYMENT_NOT_ACCEPTED;
		}
		return confirmation;
	}

	/**
	 * Puts the value of the current balance.
	 * 
	 * @param value
	 */

	public void deposit(float value) {
		assert (value != 0);

		System.out.println("Valor do saldo atual: " + CurrentBalance);
		CurrentBalance += value;
		System.out.println("Valor após o depósito: " + CurrentBalance);
	}

	/**
	 * Calculate the employee's salary.
	 */

	public double calculateSalary() {
		this.setSalary(715);
		return 715;
	}

	/**
	 * Displays the menu The options are 0 to 3. Where 0 is exit, 1 is register
	 * a new cashier, 2 is cashier list and 3 is to delete a cashier.
	 */

	public void cashierMenu() {
		System.out.println("\nInsira o que deseja fazer de acordo com as opções seguintes:" + "\n(0) - Sair\n"
				+ "(1) - Cadastrar novo Caixa\n" + "(2) - Listar Caixas\n" + "(3) - Excluir Caixa\n");
	}

	/**
	 * Register a new cashier
	 * 
	 * @param cashierList
	 */

	public void cashierRegister(ArrayList<Cashier> cashierList) {

		String personIdentity = null;
		String cpfPerson = null;
		String personPhone = null;
		int cpfDigitPerson = 0;
		String personName = null;
		String personLastName = null;
		String personAddress = null;
		int code = 0;
		boolean returnVerification = false;

		do {
			System.out.println("Digite o rg do Caixa: ");
			try {
				personIdentity = ConsoleMenu.readString();
				returnVerification = true;
			} catch (NullPointerException e) {
				System.out.println("RG vazio. Exceção " + e);
				returnVerification = false;
			}
		} while (returnVerification == false);

		do {
			System.out.println("Digite o cpf do Caixa: ");
			try {
				cpfPerson = ConsoleMenu.readString();
				returnVerification = true;
			} catch (NullPointerException e) {
				System.out.println("CPF vazio. Exceção " + e);
				returnVerification = false;
			}
		} while (returnVerification == false);

		do {
			System.out.println("Digite o digito do cpf do Caixa: ");
			try {
				cpfDigitPerson = ConsoleMenu.readInt();
				returnVerification = true;
			} catch (NumberFormatException e) {
				System.out.println("Exceção " + e);
				returnVerification = false;
			}
		} while (returnVerification == false);

		do {
			System.out.println("Digite o nome do Caixa: ");
			try {
				personName = ConsoleMenu.readString();
				returnVerification = true;
			} catch (NullPointerException e) {
				System.out.println("Nome vazio. Exceção " + e);
				returnVerification = false;
			}
		} while (returnVerification == false);

		do {
			System.out.println("Digite o sobrenome completo do Caixa: ");
			try {
				personLastName = ConsoleMenu.readString();
				returnVerification = true;
			} catch (NullPointerException e) {
				System.out.println("Ultimo nome vazio. Exceção " + e);
				returnVerification = false;
			}
		} while (returnVerification == false);

		do {
			System.out.println("Digite o endereco do Caixa: ");
			try {
				personAddress = ConsoleMenu.readString();
				returnVerification = true;
			} catch (NullPointerException e) {
				System.out.println("Endereço vazio. Exceção " + e);
				returnVerification = false;
			}
		} while (returnVerification == false);

		do {
			System.out.println("Digite o telefone do Caixa:");
			try {
				personPhone = ConsoleMenu.readString();
				returnVerification = true;
			} catch (NullPointerException e) {
				System.out.println("Telefone vazio. Exceção " + e);
				returnVerification = false;
			}
		} while (returnVerification == false);

		do {
			System.out.println("Digite o código do Caixa:");
			try {
				code = ConsoleMenu.readInt();
				returnVerification = true;
			} catch (NumberFormatException e) {
				System.out.println("Exceção " + e);
				returnVerification = false;
			}
		} while (returnVerification == false);

		Cashier cashier = new Cashier(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName,
				personAddress, personPhone, code);

		cashierList.add(cashier);

		System.out.println("O(A) caixa " + cashier.getName() + " foi cadastrado(a) com sucesso!");

	}

	/**
	 * list cashiers registered
	 * 
	 * @param cashierList
	 */

	public void listCashiers(ArrayList<Cashier> cashierList) {
		if (cashierList.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		} else {
			System.out.println("\nLista de cadastros de Caixas\n");
			for (int position = FIRST; position < cashierList.size(); position++) {
				Cashier t = cashierList.get(position);
				System.out.println("\nCadastro de número:" + (position + 1));

				System.out.println("\nNome: " + cashierList.get(position).getName() + " "
						+ cashierList.get(position).getPastName());

				System.out.println("\nRG: " + t.getIdentity().substring(0, 2) + "-"
						+ t.getIdentity().substring(2, t.getIdentity().length()));

				System.out.println("Cpf: " + t.getCpfPerson().substring(0, 3) + "." + t.getCpfPerson().substring(3, 6)
						+ "." + t.getCpfPerson().substring(6, 9) + "-" + t.getDigitCpfPerson());

				System.out.println("\nTelefone: (" + t.getPhone().substring(0, 2) + ") " + t.getPhone().substring(2, 6)
						+ "-" + t.getPhone().substring(6, 10));

				System.out.println("\nCódigo do Caixa: " + t.getCode());

				System.out.println("\nSalário: R$ " + t.calculateSalary());
			}
			System.out.println("Fim da lista de cadastro de Caixas.\n");
		}

	}

	/**
	 * Delete cashier registered.
	 * 
	 * @param cashierList
	 */

	public void deleteCashier(ArrayList<Cashier> cashierList) {

		if (cashierList.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		} else {
			System.out.println("Digite o numero do cadastro do Caixa que deseja excluir: ");
			this.setDeleteCode(scanner.nextInt());
			System.out.println("Você deseja realmente excluir o cadastro de numero: " + this.deleteCode + "?"
					+ "\n(0) - Não" + "\n(1) - Sim");
			this.setConfirmationExclusionCashier(scanner.nextInt());
			if (confirmationExclusionCashier == YES) {
				this.setDeleteCode(deleteCode - 1);
				cashierList.remove(deleteCode);

				System.out.println("A lista foi alterada");
				listCashiers(cashierList);
			} else if (confirmationExclusionCashier == NO) {
				this.setDeleteCode(0);
			} else {
				// Nothing to do.
			}
		}

	}

	/**
	 * Displays the client list.
	 */

	public void clientList() {
		for (int position = FIRST; position < (clients.length); position += 1) {
			System.out.println(("Clientes: [" + (position + 1) + "]: " + clients[position]));
		}
	}

	/**
	 * Getters and Setters
	 */

	private void setSalary(double i) {
	}

	public Clerk[] getEmployee() {
		return employee;
	}

	public Client[] getClient() {
		return clients;
	}

	public void setClient(Client[] client) {
		this.clients = client;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getCurrentBalance() {
		return CurrentBalance;
	}

	public void setCurrentBalance(float currentBalance) {
		this.CurrentBalance = currentBalance;
	}

	public Client[] getClients() {
		return clients;
	}

	public void setEmployee(Clerk[] employee) {
		this.employee = employee;
	}

	public Clerk getClerk() {
		return clerk;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Cashier[] getCashier() {
		return cashier;
	}

	public void setCashier(Cashier[] cashier) {
		this.cashier = cashier;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public int getOperationCashier() {
		return operationCashier;
	}

	public void setOperationCashier(int operationCashier) {
		this.operationCashier = operationCashier;
	}

	public int getRepeatCashierRegister() {
		return repeatCashierRegister;
	}

	public void setRepeatCashierRegister(int repeatCashierRegister) {
		this.repeatCashierRegister = repeatCashierRegister;
	}

	public int getRepeatCashier() {
		return repeatCashier;
	}

	public void setRepeatCashier(int repeatCashier) {
		this.repeatCashier = repeatCashier;
	}

	public int getDeleteCode() {
		return deleteCode;
	}

	public void setDeleteCode(int deleteCode) {
		this.deleteCode = deleteCode;
	}

	public int getConfirmationExclusionCashier() {
		return confirmationExclusionCashier;
	}

	public void setConfirmationExclusionCashier(int confirmationExclusionCashier) {
		this.confirmationExclusionCashier = confirmationExclusionCashier;
	}

	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}
}