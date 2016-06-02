package sistemadrogaria;

//Classe de teste de relacionamentos, contendo todos os utilizados no sistema

import java.text.DecimalFormat;

<<<<<<< HEAD
import entidades.Balconista;
import entidades.Caixa;
import entidades.Cliente;
import entidades.Administrativo;
import entidades.Medicamento;
import entidades.People;
import entidades.Register;
=======
import entities.Clerk;
import entities.Cashier;
import entities.Client;
import entities.Administrativo;
import entities.Medicament;
import entities.Person;
import entities.Registro;

>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c

public class SistemaDrogaria {

	public SistemaDrogaria() {
	}

	public static void main(String[] args) throws InstantiationException{

<<<<<<< HEAD
		Balconista funcionarioDrogaria = new Balconista("13.523.542-PA", "123.456.789", 12, "Victor", "Fellipe",
=======
		// Instanciando um objeto da classe dos funcionarios.
		Clerk funcionarioDrogaria = new Clerk("13.523.542-PA", "123.456.789", 12, "Victor", "Fellipe",
>>>>>>> devel
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
<<<<<<< HEAD
		
		// Imprimindo no console as informacoes do Funcion�rio
=======

		// Imprimindo no console as informacoes do Funcion�rio
<<<<<<< HEAD
>>>>>>> devel
		System.out.println("Funcionario:" + funcionarioDrogaria.getNome() + funcionarioDrogaria.getSobrenome());
=======
		System.out.println("Funcionario:" + funcionarioDrogaria.getName() + funcionarioDrogaria.getLastName());

>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c
		System.out.println("CPF: " + funcionarioDrogaria.getCpf() + "-" + funcionarioDrogaria.getDigitoCpf());
		System.out.println("Senha: " + funcionarioDrogaria.getSenha() + ". Codigo: "
				+ funcionarioDrogaria.getCodigo() + ". Fator de comissao: "
				+ funcionarioDrogaria.getFatorComissao() + " %");
		System.out.println("Endereco: " + funcionarioDrogaria.getEndereco() + ".Telefone: "
				+ funcionarioDrogaria.getPhone());

		// Imprimindo no console as informacoes do Cliente
		System.out.println("Nome do Cliente cadastrado: " + novoCliente.getName() + novoCliente.getLastName()
				+ ".Endereco: " + novoCliente.getEndereco() + ".Telefone: " + novoCliente.getPhone());
		System.out.println("RG: " + novoCliente.getRg());
		System.out.println("CPF: " + novoCliente.getCpf());
		System.out.println("Email: " + novoCliente.getEmail());

		// Imprimindo no console as informacoes de Medicamento
<<<<<<< HEAD
		System.out.println("Nome do medicamento: " + novoMedicamento.getName());
		System.out.println("Laboratorio fabricante: " + novoMedicamento.getMaker());
		System.out.println("Recomenda��o: " + novoMedicamento.getRecommendation());
=======
		System.out.println("Nome do medicamento: " + novoMedicamento.getNome());
		System.out.println("Laboratorio fabricante: " + novoMedicamento.getFabricante());
		System.out.println("Recomenda��o: " + novoMedicamento.getRecomendacao());
<<<<<<< HEAD
=======
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c
>>>>>>> devel
		System.out.println("Tipo: " + novoMedicamento.getTipo());
		System.out.println("Validade: " + novoMedicamento.getValidity());

		// Imprimindo no console as informacoes do Caixa
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> devel
		System.out.println("\nTipo da transa��o:" + caixa.getTipo());
		System.out.printf("Valor da transa��o: R$ %.2f\n", caixa.getValor());
		System.out.println("Data da transa��o: " + caixa.getData());
		System.out.println("Descri��o: " + caixa.getDescricao());
		System.out.println("Saldo atual: " + caixa.getSaldoAtual());
		caixa.depositar(150);
		System.out.println("\nSaldo atual: " + caixa.getSaldoAtual());
<<<<<<< HEAD
		
=======
=======
		System.out.println("\nTipo da transa��o:" + caixa.getType());
		System.out.printf("Valor da transa��o: R$ %.2f\n", caixa.getValue());
		System.out.println("Data da transa��o: " + caixa.getDate());
		System.out.println("Descri��o: " + caixa.getDescription());
		System.out.println("Saldo atual: " + caixa.getCurrentBalance());
		caixa.deposit(150);
		System.out.println("\nSaldo atual: " + caixa.getCurrentBalance());
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c
>>>>>>> devel

		// Decimal Format para restringir o numero de casas decimais dos valores a serem apresentados
		DecimalFormat df = new DecimalFormat("0.00");
		Administrativo administrativo;

		double salarioSemana, salarioMes;
		// Utiliza��o de upcasting (Polimorfismo) e classe abstrata
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> devel
		Balconista funcionarioDrogaria1 = new Balconista("13.523.542-PA", "123.456.789", 12, "Victor", "Fellipe",
=======
		Clerk funcionarioDrogaria1 = new Clerk("13.523.542-PA", "123.456.789", 12, "Victor", "Fellipe",
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> devel
		Caixa funcionarioDrogaria2 = new Caixa("25.555.444-DF", "123.456.789", 15, "Henrique", "Augusto",
=======
		Cashier funcionarioDrogaria2 = new Cashier("25.555.444-DF", "123.456.789", 15, "Henrique", "Augusto",

>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c
				"SHCES Qd 1000", "(61)-1224-5678", 1, 50, "2/10/2012", "Descricao teste!", 1);
		administrativo = funcionarioDrogaria2;
		salarioCaixaSemana = administrativo.calculateSalary() / 30 * 4;
		salarioCaixaMes = administrativo.calculateSalary();
		System.out.println("Salario do balconista " + funcionarioDrogaria2.getName() + " por semana e: RS "
				+ df.format(salarioCaixaSemana) + " E por mes: RS " + df.format(salarioCaixaMes));

		// Associacao
		Medicament medicamento1 = new Medicament("NomeTeste", "LaboratorioTeste", "Adulto", "Comprimido",
				"10/10/2020", "1cp. a cada 8 horas");
<<<<<<< HEAD
		Medicamento medicamento2 = new Medicamento("NomeTeste 2", "LaboratorioTeste 2", "Crian�a", "L�quido",
=======
<<<<<<< HEAD
		Medicamento medicamento2 = new Medicamento("NomeTeste 2", "LaboratorioTeste 2", "Crian�a", "L�quido",
=======
		Medicament medicamento2 = new Medicament("NomeTeste 2", "LaboratorioTeste 2", "Crian�a", "L�quido",
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c
>>>>>>> devel
				"01/07/2014", "5 ml de 6 em 6 horas");

		// Lista de medicamentos para associacao
		Medicament[] medicamentosAs = { medicamento1, medicamento2 };
		funcionarioDrogaria.setMedicaments(medicamentosAs);
		// Listando
		funcionarioDrogaria.listarMedicamentsAssociados();
		// Fim Associacao

		// Agregacao
<<<<<<< HEAD
		Medicamento medicamento3 = new Medicamento("Paracetamol", "Germed", "Infantil", "L�quido", "01/08/2015",
=======
<<<<<<< HEAD
		Medicamento medicamento3 = new Medicamento("Paracetamol", "Germed", "Infantil", "L�quido", "01/08/2015",
=======
		Medicament medicamento3 = new Medicament("Paracetamol", "Germed", "Infantil", "L�quido", "01/08/2015",
>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c
>>>>>>> devel
				"2,5 ml a cada 4 horas");
		Medicament medicamento4 = new Medicament("Aspirina", "Germed", "Adulto", "Comprimido", "01/08/2015",
				"8 em 8 horas");

		Register registro1 = new Register("12.345");
		Register registro2 = new Register("P-4.333");

		registro1.setMedicamento(medicamento3);
		medicamento3.setRegistro(registro1);
		medicamento3.listarRegistro();
		// Espelho da Agregacao
		medicamento4.setRegistro(registro2);
		registro2.setMedicamento(medicamento4);
		registro2.listMedicine();
		// Fim Agregacao
		

		// Composicao3
		Cashier caixaComposicao = new Cashier("25.555.444-DF", "123.456.789", 15, "Henrique", "Augusto",
				"SHCES Qd 1000", "(61)-1224-5678", 1, 50, "2/10/2012", "Descricao teste!", 1);

		caixaComposicao.createClerk();
		caixaComposicao.getClerk().cadastraBalconista("13.523.542-PA", "123.456.789", 12, "Joao", "Augusto",
				"Asa sul", "(61)1234-5678");

<<<<<<< HEAD
		System.out.println("O Funcionario do Caixa �:" + caixaComposicao.getBalconista().getNome());
=======
<<<<<<< HEAD
		System.out.println("O Funcionario do Caixa �:" + caixaComposicao.getBalconista().getNome());
=======
		System.out.println("O Funcionario do Caixa �:" + caixaComposicao.getClerk().getName());

>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c
>>>>>>> devel

		// Fim Composicao

		// Polimorfismo
<<<<<<< HEAD
		Pessoa pessoa;
		pessoa = funcionarioDrogaria;
		System.out.println("A confirma��o do pagamento do Funcion�rio (1: Confirmado e 0:Rejeitado) �: "
				+ pessoa.confirmacaoPagamento());
		// Upcasting
		pessoa = caixa;
		System.out.println("A confirma��o do pagamento do Caixa (1: Confirmado e 0:Rejeitado) �: "
				+ pessoa.confirmacaoPagamento());
=======
<<<<<<< HEAD
		People people;
		// Upcasting
		people = funcionarioDrogaria;
		System.out.println("A confirma��o do pagamento do Funcion�rio (1: Confirmado e 0:Rejeitado) �: "
				+ people.paymentConfirmation());
		// Upcasting
		people = caixa;
		System.out.println("A confirma��o do pagamento do Caixa (1: Confirmado e 0:Rejeitado) �: "
				+ people.paymentConfirmation());
=======
		Person pessoa;
		// Upcasting
		pessoa = funcionarioDrogaria;
		System.out.println("A confirma��o do pagamento do Funcion�rio (1: Confirmado e 0:Rejeitado) �: "
				+ pessoa.paymentConfirmation());
		// Upcasting
		pessoa = caixa;
		System.out.println("A confirma��o do pagamento do Caixa (1: Confirmado e 0:Rejeitado) �: "
				+ pessoa.paymentConfirmation());

>>>>>>> 7dc6155feb316d34c05aba94bb91558233b9184c
>>>>>>> devel

		// Interface
		Client novoClienteInterface = new Client("13.999.888-DF", "444.555.666", 11, "INTERFACE", "INTERFACE",
				"Gama qd. 1000", "(61)-1234-5678", "cliente1@gmail.com");
<<<<<<< HEAD
		novoClienteInterface.remediosRecomendados("GENERICO", "PEDIATRICO");

=======
		novoClienteInterface.recommendedMedicines("GENERICO", "PEDIATRICO");
>>>>>>> devel
	}

}
