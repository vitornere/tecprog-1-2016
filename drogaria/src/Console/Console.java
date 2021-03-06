/**
 * Name: Console.java
 * Main class of the user console.
 */
package Console;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Cashier;
import entities.Clerk;
import entities.Client;
import entities.ConsoleMenu;
import entities.Medicament;

public class Console {

	private static final int BEGINNING_OF_THE_PROGRAM = 0;
	private static final int PASS_OF_START_MENU = 1;
	private static final int CLERK = 2;
	private static final int CASHIER = 3;
	private static final int CLIENT = 4;
	private static final int MEDICAMENT = 5;
	private static final int HELP = 6;
	private static final int EXIT = 100;

	private static Scanner scanner;

	public Console() {
	}

	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);

		/**
		 * Lists of the Clerks, Cashiers, Clients and Medicaments.
		 */
		ArrayList<Clerk> listOfClerks = new ArrayList<Clerk>();
		ArrayList<Cashier> listOfCashier = new ArrayList<Cashier>();
		ArrayList<Client> listOfClients = new ArrayList<Client>();
		ArrayList<Medicament> listOfMedicaments = new ArrayList<Medicament>();

		/**
		 * Creating a new Clerk, Cashier, Client, Medicament and ConsoleMenu.
		 */
		Clerk clerk = new Clerk();
		Cashier cashier = new Cashier();
		Client client = new Client();
		Medicament medicament = new Medicament();
		ConsoleMenu consoleMenu = new ConsoleMenu();

		int operation = 0;
		int operationOfClerk = 0;
		int operationOfCashier = 0;
		int operationOfClient = 0;
		int operationOfMedicament = 0;

		int status = 0;
		assert(status < BEGINNING_OF_THE_PROGRAM);
		assert((status > HELP) && (status < EXIT));
		assert(status > EXIT);

		while (status != EXIT) {
			if (status == BEGINNING_OF_THE_PROGRAM) {

				clerk.startMenu();
				
				do {
					try {
						operation = scanner.nextInt();
					} catch (NumberFormatException e) {
						operation = -1;
						System.out.println("Somente números.");
					}
				} while(operation != -1);
					
				/**
				 * If you enter the wrong number, exit the program.
				 */
				assert ((operation >= BEGINNING_OF_THE_PROGRAM) && (operation <= MEDICAMENT)) : "Numero invalido: "
						+ operation;

				if (operation == BEGINNING_OF_THE_PROGRAM) {
					try {
						status = consoleMenu.GeneralConfirmation(status);
					} catch (NumberFormatException e) {
						System.out.println("Retorno errado na linha 86");
						System.exit(1);
					}
				} else {
					status++;

					if (status >= PASS_OF_START_MENU && status != EXIT) {
						/**
						 * Inside of menu.
						 */
						switch (operation) {
						case PASS_OF_START_MENU:
							if (operation == PASS_OF_START_MENU) {
								status = CLERK;
							}
							while (status == CLERK) {

								/**
								 * Menu of Clerks.
								 */
								clerk.menuClerk();
								operationOfClerk = scanner.nextInt();
								if (operationOfClerk == BEGINNING_OF_THE_PROGRAM) {
									try {
										status = consoleMenu
											.ConfirmationClerk(status);
									} catch (NumberFormatException e) {
										System.out.println("Retorno de parâmetro errado na linha 113");
										System.exit(2);
									}
								}

								else if (operationOfClerk == PASS_OF_START_MENU) {
									clerk.registerClerk(listOfClerks);
									status = CLERK;
								}

								else if (operationOfClerk == CLERK) {
									clerk.listClerks(listOfClerks);
									status = CLERK;
								}

								else if (operationOfClerk == CASHIER) {
									clerk.deleteClerk(listOfClerks);
									status = CLERK;
								}

							}
						case CLERK:
							if (operation == CLERK) {
								status = CASHIER;
							}
							while (status == CASHIER) {

								/**
								 * Menu of Cashier.
								 */
								cashier.cashierMenu();
								operationOfCashier = scanner.nextInt();
								if (operationOfCashier == BEGINNING_OF_THE_PROGRAM) {
									try {
										status = consoleMenu
											.ConfirmationBox(status);
									} catch (NumberFormatException e) {
										System.out.println("Retorno de parâmetro errado na linha 152");
										System.exit(1);
									}
								}

								else if (operationOfCashier == PASS_OF_START_MENU) {
									cashier.cashierRegister(listOfCashier);
									status = CASHIER;
								}

								else if (operationOfCashier == CLERK) {
									cashier.listCashiers(listOfCashier);
									status = CASHIER;
								}

								else if (operationOfCashier == CASHIER) {
									cashier.deleteCashier(listOfCashier);
									status = CASHIER;
								}
							}
							break;

						case CASHIER:
							if (operation == CASHIER) {
								status = CLIENT;
							}
							while (status == CLIENT) {
								/**
								 * Menu of Clients.
								 */
								client.clientMenu();
								operationOfClient = scanner.nextInt();
								if (operationOfClient == BEGINNING_OF_THE_PROGRAM) {
									status = consoleMenu
											.ConfirmationClient(status);
								}

								else if (operationOfClient == PASS_OF_START_MENU) {
									client.clientRegister(listOfClients);
									status = CLIENT;
								}

								else if (operationOfClient == CLERK) {
									client.listClients(listOfClients);
									status = CLIENT;
								}

								else if (operationOfClient == CASHIER) {
									client.deleteClient(listOfClients);
									status = CLIENT;
								}

							}
							break;

						case CLIENT:
							if (operation == CLIENT) {
								status = MEDICAMENT;
							}
							while (status == MEDICAMENT) {

								/**
								 * Menu of Medicaments.
								 */
								medicament.menuMedicamento();
								operationOfMedicament = scanner.nextInt();
								if (operationOfMedicament == BEGINNING_OF_THE_PROGRAM) {
									try {
										status = consoleMenu
											.ConfirmationMedicament(status);
									} catch(NumberFormatException e) {
										System.out.println("Retorno de parâmetro errado na linha 223");
										System.exit(1);
									}
								}

								else if (operationOfMedicament == PASS_OF_START_MENU) {
									medicament
											.cadastrarMedicamento(listOfMedicaments);
									status = MEDICAMENT;
								}

								else if (operationOfMedicament == CLERK) {
									medicament
											.listarMedicamentos(listOfMedicaments);
									status = MEDICAMENT;
								}

								else if (operationOfMedicament == CASHIER) {
									medicament
											.excluirMedicamento(listOfMedicaments);
									status = MEDICAMENT;
								}

							}
							break;

						case MEDICAMENT:
							if (operation == MEDICAMENT) {
								status = HELP;
							}
							while (status == HELP) {
								try {
									status = consoleMenu.menuHelp(status);
								} catch (NumberFormatException e) {
									System.out.println("Retorno de parâmetro errado na linha 257");
									System.exit(1);
								}
							}

						}
					}
				}
			}
			
		}
		System.out.println("Thank you for using our management system!"
				+ " Exiting the program!");
	}
}
