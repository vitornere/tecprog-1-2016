package drugstoreSystem;

// Test relationships containing all used in the system

import java.text.DecimalFormat;

import entities.Administrative;
import entities.Cashier;
import entities.Clerk;
import entities.Client;
import entities.Medicament;
import entities.Person;
import entities.Register;

public class DrugstoreSystem {

	public DrugstoreSystem() {
	}

	public static void main(String[] args) {

		// Creating a Clerk.
		Clerk drugstoreClerk = new Clerk("13.523.542-PA", "123.456.789", 12,
				"Victor", "Fellipe", "SHCES Qd 1000", "(61)1234-5678", 1234,
				5678, 20, 2, 20);

		// creating a Client
		Client newClient = new Client("13.999.888-DF", "444.555.666", 11,
				"Teste", "Teste", "Gama qd. 1000", "(61)-1234-5678",
				"client@gmail.com");

		// creating a Medicament
		Medicament newMedicament = new Medicament("NameTest", "LaboratoryTest",
				"Adult", "Pill", "10/10/2020", "One pill every eight hours");

		// Creating a transaction cashier
		Cashier cashier = new Cashier("Test 1", "Test 2", 50, "Test 3",
				"Test 4", "Test 5", "Test 6", 0, 100, "10/10/2020",
				"transactionTest", 1);

		// Printing the Clerk Info
		System.out.println("Functionary:" + drugstoreClerk.getName()
				+ drugstoreClerk.getPastName());
		System.out.println("CPF Clerk: " + drugstoreClerk.getCpfPerson() + "-"
				+ drugstoreClerk.getDigitCpfPerson());
		System.out.println("Password: " + drugstoreClerk.getPassword()
				+ ". Id Clerk: " + drugstoreClerk.getIdFuncionary()
				+ ". Commission Factor: "
				+ drugstoreClerk.getCommissionFactor() + " %");
		System.out.println("Address Clerk: " + drugstoreClerk.getAddress()
				+ ".Phone Clerk: " + drugstoreClerk.getPhone());

		// Printing the Client Info
		System.out.println("Client name registered: " + newClient.getName()
				+ newClient.getPastName() + ".Address Client: "
				+ newClient.getAddress() + ".Phone Client: "
				+ newClient.getPhone());
		System.out.println("Identity Client: " + newClient.getIdentity());
		System.out.println("CPF Client: " + newClient.getCpfPerson());
		System.out.println("Email Client: " + newClient.getEmail());

		// Printing the Medicament Info
		System.out.println("Medicament Name: "
				+ newMedicament.getNameMedicament());
		System.out.println("Manufactures Laboratory: "
				+ newMedicament.getManufacturer());
		System.out.println("Recommendation: "
				+ newMedicament.getRecommendation());
		System.out.println("Type Medicament: "
				+ newMedicament.getMedicamento());
		System.out.println("Expiration Date: "
				+ newMedicament.getExpirationDate());

		// Printing the Cashier Info
		System.out.println("\nTransation Type:" + cashier.getTransationType());
		System.out.printf("Transation Value: R$ %.2f\n",
				cashier.getTransactionValue());
		System.out.println("Transation Date: " + cashier.getTransationDate());
		System.out.println("Description: " + cashier.getDescription());
		System.out.println("Current Sale: " + cashier.getCurrentSale());
		cashier.depositar(150);
		System.out.println("\nCurrent Sale: " + cashier.getCurrentSale());

		// Restricts the number of decimal places to be displayed
		DecimalFormat df = new DecimalFormat("0.00");
		Administrative administrative;

		double weeklySalary, monthlySalary;
		Clerk secondDrugstoreClerk = new Clerk("13.523.542-PA", "123.456.789",
				12, "Victor", "Fellipe", "SHCES Qd 1000", "(61)-1234-5678",
				1234, 5678, 20, 10, 40);
		administrative = secondDrugstoreClerk;
		weeklySalary = administrative.calculateSalary();
		monthlySalary = (administrative.calculateSalary()) / 7 * 30;
		System.out.println("Clerk Salary " + secondDrugstoreClerk.getName()
				+ " weekly is: U$" + df.format(weeklySalary));
		System.out.println(" and monthly: U$ " + df.format(monthlySalary));

		double weeklySalaryCashier;
		double monthlySalaryCashier;
		Cashier thirdDrugstoreClerk = new Cashier("25.555.444-DF",
				"123.456.789", 15, "Henrique", "Augusto", "SHCES Qd 1000",
				"(61)-1224-5678", 1, 50, "2/10/2012", "Description test!", 1);
		administrative = thirdDrugstoreClerk;
		weeklySalaryCashier = administrative.calculateSalary() / 30 * 4;
		monthlySalaryCashier = administrative.calculateSalary();
		System.out.println("Clerk Salary " + thirdDrugstoreClerk.getName()
				+ " weekly is: U$ " + df.format(weeklySalaryCashier)
				+ " and monthly: U$ " + df.format(monthlySalaryCashier));

		Medicament secondMedicament = new Medicament("NameTest",
				"LaboratoryTest", "Adult", "Pill", "10/10/2020",
				"one pill. every eight hours");
		Medicament thirdMedicament = new Medicament("NameTest ",
				"LaboratoryTest ", "Children", "Liquid", "01/07/2014",
				"5 ml of six in six hours");

		// Medicament list
		Medicament[] medicaments = { secondMedicament, thirdMedicament };
		drugstoreClerk.setMedicaments(medicaments);
		// Listing
		drugstoreClerk.listarMedicamentsAssociados();

		Medicament fourthMedicament = new Medicament("Paracetamol", "Germed",
				"Infant", "Liquid", "01/08/2015",
				"2,5 ml of four in four hours");
		Medicament fifthMedicament = new Medicament("Aspirina", "Germed",
				"Adult", "Pill", "01/08/2015",
				"One pill of eight em eight horas");

		Register register = new Register("12.345");
		Register secondRegister = new Register("P-4.333");

		register.setMedicament(fourthMedicament);
		fourthMedicament.setRegistro(register);
		fourthMedicament.listarRegistro();
		fifthMedicament.setRegistro(secondRegister);
		secondRegister.setMedicament(fifthMedicament);
		secondRegister.medicamentList();

		Cashier cashierComposition = new Cashier("25.555.444-DF",
				"123.456.789", 15, "Henrique", "Augusto", "SHCES Qd 1000",
				"(61)-1224-5678", 1, 50, "2/10/2012", "Description test!", 1);

		cashierComposition.createClerk();
		;
		cashierComposition.getClerk().registerClerk("13.523.542-PA",
				"123.456.789", 12, "João", "Augusto", "Asa sul",
				"(61)1234-5678");

		System.out.println("The Cashier Housing:"
				+ cashierComposition.getClerk().getName());

		Person pessoa;
		pessoa = drugstoreClerk;
		System.out
				.println("The confirmation of the funcionary's payment (1: Confirmed and 0:rejected): "
						+ pessoa.paymentConfirmation());
		pessoa = cashier;
		System.out
				.println("The confirmation of the funcionary's payment (1: Confirmed and 0:rejected): "
						+ pessoa.paymentConfirmation());

		Client novoClienteInterface = new Client("13.999.888-DF",
				"444.555.666", 11, "INTERFACE", "INTERFACE", "Gama qd. 1000",
				"(61)-1234-5678", "client@gmail.com");
		novoClienteInterface.recommendedMedicine("GENERIC", "PEDIATRIC");
	}

}
