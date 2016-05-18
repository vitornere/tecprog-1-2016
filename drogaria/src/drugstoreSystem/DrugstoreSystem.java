package drugstoreSystem;

// Test relationships containing all used in the system

import java.text.DecimalFormat;

import entities.Administrativo;
import entities.Cashier;
import entities.Clerk;
import entities.Client;
import entities.Medicament;
import entities.Pessoa;
import entities.Register;

public class DrugstoreSystem {

	public DrugstoreSystem() {
	}

	public static void main(String[] args) {

		// Creating a Clerk.
		Clerk drugstoreClerk = new Clerk("13.523.542-PA", "123.456.789", 12, "Victor", "Fellipe",
				"SHCES Qd 1000", "(61)1234-5678", 1234, 5678, 20, 2, 20);

		// creating a Client
		Client newClient = new Client("13.999.888-DF", "444.555.666", 11, "Teste", "Teste", "Gama qd. 1000",
				"(61)-1234-5678", "cliente1@gmail.com");

		// creating a Medicament
		Medicament newMedicament = new Medicament("NomeTeste", "LaboratorioTeste", "Adulto", "Comprimido",
				"10/10/2020", "1cp. a cada 8 horas");

		// Creating a transaction cashier
		Cashier cashier = new Cashier("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100,
				"10/10/2020", "transacaoTeste", 1);

		// Printing the Clerk Info
		System.out.println("Funcionario:" + drugstoreClerk.getNome() + drugstoreClerk.getSobrenome());
		System.out.println("CPF: " + drugstoreClerk.getCpf() + "-" + drugstoreClerk.getDigitoCpf());
		System.out.println("Senha: " + drugstoreClerk.getSenha() + ". Codigo: "
				+ drugstoreClerk.getCodigo() + ". Fator de comissao: "
				+ drugstoreClerk.getFatorComissao() + " %");
		System.out.println("Endereco: " + drugstoreClerk.getEndereco() + ".Telefone: "
				+ drugstoreClerk.getTelefone());

		// Printing the Client Info
		System.out.println("Nome do Cliente cadastrado: " + newClient.getNome() + newClient.getSobrenome()
				+ ".Endereco: " + newClient.getEndereco() + ".Telefone: " + newClient.getTelefone());
		System.out.println("RG: " + newClient.getRg());
		System.out.println("CPF: " + newClient.getCpf());
		System.out.println("Email: " + newClient.getEmail());

		// Printing the Medicament Info
		System.out.println("Nome do medicamento: " + newMedicament.getNome());
		System.out.println("Laboratorio fabricante: " + newMedicament.getFabricante());
		System.out.println("Recomenda��o: " + newMedicament.getRecomendacao());
		System.out.println("Tipo: " + newMedicament.getTipo());
		System.out.println("Validade: " + newMedicament.getValidade());

		// Printing the Cashier Info
		System.out.println("\nTipo da transa��o:" + cashier.getTipo());
		System.out.printf("Valor da transa��o: R$ %.2f\n", cashier.getValor());
		System.out.println("Data da transa��o: " + cashier.getData());
		System.out.println("Descri��o: " + cashier.getDescricao());
		System.out.println("Saldo atual: " + cashier.getSaldoAtual());
		cashier.depositar(150);
		System.out.println("\nSaldo atual: " + cashier.getSaldoAtual());

		// Restricts the number of decimal places to be displayed
		DecimalFormat df = new DecimalFormat("0.00");
		Administrativo administrative;

		double weeklySalary, monthlySalary;
		Clerk secondDrugstoreClerk = new Clerk("13.523.542-PA", "123.456.789", 12, "Victor", "Fellipe",
				"SHCES Qd 1000", "(61)-1234-5678", 1234, 5678, 20, 10, 40);
		administrative = secondDrugstoreClerk;
		weeklySalary = administrative.calculateSalary();
		monthlySalary = (administrative.calculateSalary()) / 7 * 30;
		System.out.println("Salario do balconista " + secondDrugstoreClerk.getNome() + " por semana e: RS"
				+ df.format(weeklySalary));
		System.out.println(" E por mes: RS " + df.format(monthlySalary));

		double weeklySalaryCashier;
		double monthlySalaryCashier;
		Cashier thirdDrugstoreClerk = new Cashier("25.555.444-DF", "123.456.789", 15, "Henrique", "Augusto",
				"SHCES Qd 1000", "(61)-1224-5678", 1, 50, "2/10/2012", "Descricao teste!", 1);
		administrative = thirdDrugstoreClerk;
		weeklySalaryCashier = administrative.calculateSalary() / 30 * 4;
		monthlySalaryCashier = administrative.calculateSalary();
		System.out.println("Salario do balconista " + thirdDrugstoreClerk.getNome() + " por semana e: RS "
				+ df.format(weeklySalaryCashier) + " E por mes: RS " + df.format(monthlySalaryCashier));

		Medicament secondMedicament = new Medicament("NomeTeste", "LaboratorioTeste", "Adulto", "Comprimido",
				"10/10/2020", "1cp. a cada 8 horas");
		Medicament thirdMedicament = new Medicament("NomeTeste 2", "LaboratorioTeste 2", "Crian�a", "L�quido",
				"01/07/2014", "5 ml de 6 em 6 horas");

		// Medicament list
		Medicament[] medicaments = { secondMedicament, thirdMedicament };
		drugstoreClerk.setMedicamentos(medicaments);
		// Listing
		drugstoreClerk.listarMedicamentosAssociados();

		Medicament fourthMedicament = new Medicament("Paracetamol", "Germed", "Infantil", "L�quido", "01/08/2015",
				"2,5 ml a cada 4 horas");
		Medicament fifthMedicament = new Medicament("Aspirina", "Germed", "Adulto", "Comprimido", "01/08/2015",
				"8 em 8 horas");

		Register register = new Register("12.345");
		Register secondRegister = new Register("P-4.333");

		register.setMedicamento(fourthMedicament);
		fourthMedicament.setRegistro(register);
		fourthMedicament.registerList();
		fifthMedicament.setRegistro(secondRegister);
		secondRegister.setMedicamento(fifthMedicament);
		secondRegister.medicamentList();

		Cashier cashierComposition = new Cashier("25.555.444-DF", "123.456.789", 15, "Henrique", "Augusto",
				"SHCES Qd 1000", "(61)-1224-5678", 1, 50, "2/10/2012", "Descricao teste!", 1);

		cashierComposition.criarBalconista();;
		cashierComposition.getBalconista().cadastraBalconista("13.523.542-PA", "123.456.789", 12, "Joao", "Augusto",
				"Asa sul", "(61)1234-5678");

		System.out.println("O Funcionario do Caixa �:" + cashierComposition.getBalconista().getNome());

		Pessoa pessoa;
		pessoa = drugstoreClerk;
		System.out.println("A confirma��o do pagamento do Funcion�rio (1: Confirmado e 0:Rejeitado) �: "
				+ pessoa.confirmacaoPagamento());
		pessoa = cashier;
		System.out.println("A confirma��o do pagamento do Caixa (1: Confirmado e 0:Rejeitado) �: "
				+ pessoa.confirmacaoPagamento());

		Client novoClienteInterface = new Client("13.999.888-DF", "444.555.666", 11, "INTERFACE", "INTERFACE",
				"Gama qd. 1000", "(61)-1234-5678", "cliente1@gmail.com");
		novoClienteInterface.remediosRecomendados("GENERICO", "PEDIATRICO");
	}

}
