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
	
	private float CurrentBalance = 0;
	private int type; 
	private float value;
	private String date;
	private String description;
	private Clerk clerk;
	protected Client[] clients;
	protected Clerk[] employee;
	private int code;

	// Console
	Cashier[] cashier = {};
	Scanner scanner = new Scanner(System.in);
	int operation = 0;
	int operationCashier = 0, repeatCashierRegister = 0, repeatCashier = 0;
	int deleteCode = 0;
	int confirmationExclusionCashier = 0;
	

	public Cashier() {
		super();
	}

	public Cashier(String personIdentity, String cpfPerson, int cpfDigitPerson, String personName,
			String personLastName, String personAddress, String personPhone, int trasitionType,
			float trasitionValue, String trasitionDate, String trasitionDescription, int code) {
		super(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName, personAddress, personPhone);
		if (type == INCOME || type == OUTGO) {
			this.type = trasitionType;
			this.value = trasitionValue;
			this.date = trasitionDate;
			this.description = trasitionDescription;
			this.code = code;
		}
		else {
			System.out.println("Caixa nao cadastrado.");

		}
		// Composition - there is only a cashier, if there is already a clerk.
		Clerk clerk = new Clerk(this);
		this.clerk = clerk;

		System.out.println("Verificacao de um funcion�rio para atend�-lo.");
		Clerk.setStatusClerk(true); // Utilization of static for dependence.
	}

	public Cashier(String personIdentity, String cpfPerson, int cpfDigitPerson, String personName,
			String personLastName, String personAddress, String personPhone, int code) {
		super(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName, personAddress, personPhone);
		this.code = code;
	}

	// Composition - creation of a new cashier from a clerk.
	public void createClerk() {
		Clerk clerk = new Clerk(this);
		this.clerk = clerk;
	}

	// Checks payment confirmation in cashier (polymorphism).
	public double paymentConfirmation() {
		if (this.getPaymentConfirmation() == 1) {
			return PAYMENT_ACCEPTED; // It confirms that the payment has been accepted.
		}
		else {
			return PAYMENT_NOT_ACCEPTED; // It confirms that the payment has not been accepted.
		}
	}

	public void deposito(float value) {
		System.out.println("Valor do saldo atual: " + CurrentBalance);
		CurrentBalance += value;
		System.out.println("Valor ap�s o dep�sito: " + CurrentBalance);
	}

	public double calculateSalary() {
		this.setSalary(715);
		return 715;
	}

	public void cashierMenu() {
		System.out.println("\nInsira o que deseja fazer de acordo com as op��es seguintes:" + "\n(0) - Sair\n"
				+ "(1) - Cadastrar novo Caixa\n" + "(2) - Listar Caixas\n" + "(3) - Excluir Caixa\n");
	}

	public void cashierRegister(ArrayList<Cashier> cashierList) {

		System.out.println("Digite o rg do Caixa: ");
		String personIdentity = ConsoleMenu.readString();

		System.out.println("Digite o cpf do Caixa: ");
		String cpfPerson = ConsoleMenu.readString();

		System.out.println("Digite o digito do cpf do Caixa: ");
		int cpfDigitPerson = ConsoleMenu.readInt();

		System.out.println("Digite o nome do Caixa: ");
		String personName = ConsoleMenu.readString();

		System.out.println("Digite o sobrenome completo do Caixa: ");
		String personLastName = ConsoleMenu.readString();

		System.out.println("Digite o endereco do Caixa: ");
		String personAddress = ConsoleMenu.readString();

		System.out.println("Digite o telefone do Caixa:");
		String personPhone = ConsoleMenu.readString();

		System.out.println("Digite o c�digo do Caixa:");
		int code = ConsoleMenu.readInt();

		Cashier cashier = new Cashier(personIdentity, cpfPerson, cpfDigitPerson, personName, personLastName, personAddress,
				personPhone, code);

		cashierList.add(cashier);

		System.out.println("O(A) caixa " + cashier.getName() + " foi cadastrado(a) com sucesso!");

	}

	public void listCashiers(ArrayList<Cashier> cashierList) {
		if (cashierList.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("\nLista de cadastros de Caixas\n");
			for (int b = 0; b < cashierList.size(); b++) {
				Cashier t = cashierList.get(b);
				System.out.println("\nCadastro de n�mero:" + (b + 1));

				System.out.println("\nNome: " + cashierList.get(b).getName() + " "
						+ cashierList.get(b).getLastName());

				System.out.println("\nRG: " + t.getRg().substring(0, 2) + "-"
						+ t.getRg().substring(2, t.getRg().length()));

				System.out.println("Cpf: " + t.getCpf().substring(0, 3) + "."
						+ t.getCpf().substring(3, 6) + "." + t.getCpf().substring(6, 9) + "-"  + t.getDigitoCpf());

				System.out.println("\nTelefone: (" + t.getPhone().substring(0, 2) + ") "
						+ t.getPhone().substring(2, 6) + "-" + t.getPhone().substring(6, 10));

				System.out.println("\nC�digo do Caixa: " + t.getCode());

				System.out.println("\nSal�rio: R$ " + t.calculateSalary());
			}
			System.out.println("Fim da lista de cadastro de Caixas.\n");
		}

	}

	public void deleteCashier(ArrayList<Cashier> cashierList) {

		if (cashierList.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("Digite o numero do cadastro do Caixa que deseja excluir: ");
			this.setDeleteCode(scanner.nextInt());
			System.out.println("Voc� deseja realmente excluir o cadastro de numero: " + this.deleteCode + "?"
					+ "\n(0) - N�o" + "\n(1) - Sim");
			this.setConfirmationExclusionCashier(scanner.nextInt());
			if (confirmationExclusionCashier == 1) {
				this.setDeleteCode(deleteCode - 1);
				cashierList.remove(deleteCode);

				System.out.println("A lista foi alterada");
				listCashiers(cashierList);
			}
			else if (confirmationExclusionCashier == 0) {
				this.setDeleteCode(0);
			}
		}

	}
	
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

	public void clientList() {
		for (int position = FIRST; position < (clients.length); position += 1) {
			System.out.println(("Clientes: [" + (position + 1) + "]: " + clients[position]));
		}
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

	public void setCode (int code) {
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