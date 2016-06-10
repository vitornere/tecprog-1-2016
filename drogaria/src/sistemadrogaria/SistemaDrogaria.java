package sistemadrogaria;

//Classe de teste de relacionamentos, contendo todos os utilizados no sistema

import java.text.DecimalFormat;

import entities.Clerk;
import entities.Cashier;
import entities.Client;
import entities.Administrative;
import entities.Medicament;
import entities.Person;
import entities.Register;


public class SistemaDrogaria {

	public SistemaDrogaria() {
	}

	public static void main(String[] args) {

		// Instanciando um objeto da classe dos funcionarios.
		Clerk funcionarioDrogaria = new Clerk("13.523.542-PA", "123.456.789", 12, "Victor", "Fellipe",
				"SHCES Qd 1000", "(61)1234-5678", 1234, 5678, 20, 2, 20);

		// Instanciando um objeto da classe dos clientes - DEPENDENCIA CORRIGIDA
		Client novoCliente = new Client("13.999.888-DF", "444.555.666", 11, "Teste", "Teste", "Gama qd. 1000",
				"(61)-1234-5678", "cliente1@gmail.com");

		// Criando e cadastrando um medicamento
		Medicament novoMedicamento = new Medicament("NomeTeste", "LaboratorioTeste", "Adulto", "Comprimido",
				"10/10/2020", "1cp. a cada 8 horas");

		// Criando e cadastrando uma transacao do caixa
		Cashier caixa = new Cashier("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100,
				"10/10/2020", "transacaoTeste", 1);

		// Imprimindo no console as informacoes do Funcion�rio
		System.out.println("Funcionario:" + funcionarioDrogaria.getName() + funcionarioDrogaria.getPastName());

		System.out.println("CPF: " + funcionarioDrogaria.getCpfPerson() + "-" + funcionarioDrogaria.getDigitCpfPerson());
		System.out.println("Senha: " + funcionarioDrogaria.getPassword() + ". Codigo: "
				+ funcionarioDrogaria.getIdFuncionary() + ". Fator de comissao: "
				+ funcionarioDrogaria.getCommissionFactor() + " %");
		System.out.println("Endereco: " + funcionarioDrogaria.getAddress() + ".Telefone: "
				+ funcionarioDrogaria.getPhone());

		// Imprimindo no console as informacoes do Cliente
		System.out.println("Nome do Cliente cadastrado: " + novoCliente.getName() + novoCliente.getPastName()
				+ ".Endereco: " + novoCliente.getAddress() + ".Telefone: " + novoCliente.getPhone());
		System.out.println("RG: " + novoCliente.getIdentity());
		System.out.println("CPF: " + novoCliente.getCpfPerson());
		System.out.println("Email: " + novoCliente.getEmail());

		// Imprimindo no console as informacoes de Medicamento
		System.out.println("Nome do medicamento: " + novoMedicamento.getNameMedicament());
		System.out.println("Laboratorio fabricante: " + novoMedicamento.getManufacturer());
		System.out.println("Recomenda��o: " + novoMedicamento.getRecommendation());
		System.out.println("Tipo: " + novoMedicamento.getTipo());
		System.out.println("Validade: " + novoMedicamento.getExpirationDate());

		// Imprimindo no console as informacoes do Caixa
		System.out.println("\nTipo da transa��o:" + caixa.getTransationType());
		System.out.printf("Valor da transa��o: R$ %.2f\n", caixa.getTransactionValue());
		System.out.println("Data da transa��o: " + caixa.getTransationDate());
		System.out.println("Descri��o: " + caixa.getDescription());
		System.out.println("Saldo atual: " + caixa.getCurrentSale());
		caixa.depositar(150);
		System.out.println("\nSaldo atual: " + caixa.getCurrentSale());

		// Decimal Format para restringir o numero de casas decimais dos valores a serem apresentados
		DecimalFormat df = new DecimalFormat("0.00");
		Administrative administrativo;

		double salarioSemana, salarioMes;
		// Utiliza��o de upcasting (Polimorfismo) e classe abstrata
		Clerk funcionarioDrogaria1 = new Clerk("13.523.542-PA", "123.456.789", 12, "Victor", "Fellipe",
				"SHCES Qd 1000", "(61)-1234-5678", 1234, 5678, 20, 10, 40);
		administrativo = funcionarioDrogaria1;
		salarioSemana = administrativo.calculateSalary();
		salarioMes = (administrativo.calculateSalary()) / 7 * 30;
		System.out.println("Salario do balconista " + funcionarioDrogaria1.getName() + " por semana e: RS"
				+ df.format(salarioSemana));
		System.out.println(" E por mes: RS " + df.format(salarioMes));

		double salarioCaixaSemana;
		double salarioCaixaMes;
		// Utiliza��o de upcasting (Polimorfismo) e classe abstrata para o Caixa
		Cashier funcionarioDrogaria2 = new Cashier("25.555.444-DF", "123.456.789", 15, "Henrique", "Augusto",

				"SHCES Qd 1000", "(61)-1224-5678", 1, 50, "2/10/2012", "Descricao teste!", 1);
		administrativo = funcionarioDrogaria2;
		salarioCaixaSemana = administrativo.calculateSalary() / 30 * 4;
		salarioCaixaMes = administrativo.calculateSalary();
		System.out.println("Salario do balconista " + funcionarioDrogaria2.getName() + " por semana e: RS "
				+ df.format(salarioCaixaSemana) + " E por mes: RS " + df.format(salarioCaixaMes));

		// Associacao
		Medicament medicamento1 = new Medicament("NomeTeste", "LaboratorioTeste", "Adulto", "Comprimido",
				"10/10/2020", "1cp. a cada 8 horas");
		Medicament medicamento2 = new Medicament("NomeTeste 2", "LaboratorioTeste 2", "Crian�a", "L�quido",
				"01/07/2014", "5 ml de 6 em 6 horas");

		// Lista de medicamentos para associacao
		Medicament[] medicamentosAs = { medicamento1, medicamento2 };
		funcionarioDrogaria.setMedicaments(medicamentosAs);
		// Listando
		funcionarioDrogaria.listarMedicamentsAssociados();
		// Fim Associacao

		// Agregacao
		Medicament medicamento3 = new Medicament("Paracetamol", "Germed", "Infantil", "L�quido", "01/08/2015",
				"2,5 ml a cada 4 horas");
		Medicament medicamento4 = new Medicament("Aspirina", "Germed", "Adulto", "Comprimido", "01/08/2015",
				"8 em 8 horas");

		Register registro1 = new Register("12.345");
		Register registro2 = new Register("P-4.333");

		registro1.setMedicament(medicamento3);
		medicamento3.setRegistro(registro1);
		medicamento3.listarRegistro();
		// Espelho da Agregacao
		medicamento4.setRegistro(registro2);
		registro2.setMedicament(medicamento4);
		registro2.medicamentList();
		// Fim Agregacao

		// Composicao3
		Cashier caixaComposicao = new Cashier("25.555.444-DF", "123.456.789", 15, "Henrique", "Augusto",
				"SHCES Qd 1000", "(61)-1224-5678", 1, 50, "2/10/2012", "Descricao teste!", 1);

		caixaComposicao.createClerk();
		caixaComposicao.getClerk().registerClerk("13.523.542-PA", "123.456.789", 12, "Joao", "Augusto",
				"Asa sul", "(61)1234-5678");

		System.out.println("O Funcionario do Caixa �:" + caixaComposicao.getClerk().getName());


		// Fim Composicao

		// Polimorfismo
		Person pessoa;
		// Upcasting
		pessoa = funcionarioDrogaria;
		System.out.println("A confirma��o do pagamento do Funcion�rio (1: Confirmado e 0:Rejeitado) �: "
				+ pessoa.paymentConfirmation());
		// Upcasting
		pessoa = caixa;
		System.out.println("A confirma��o do pagamento do Caixa (1: Confirmado e 0:Rejeitado) �: "
				+ pessoa.paymentConfirmation());


		// Interface
		Client novoClienteInterface = new Client("13.999.888-DF", "444.555.666", 11, "INTERFACE", "INTERFACE",
				"Gama qd. 1000", "(61)-1234-5678", "cliente1@gmail.com");
		novoClienteInterface.recommendedMedicaments("GENERICO", "PEDIATRICO");
	}

}
