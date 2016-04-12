package sistemadrogaria;

//Classe de teste de relacionamentos, contendo todos os utilizados no sistema

import java.text.DecimalFormat;

import entidades.Balconista;
import entidades.Caixa;
import entidades.Cliente;
import entidades.Administrativo;
import entidades.Medicamento;
import entidades.Pessoa;
import entidades.Registro;

public class SistemaDrogaria {

	public SistemaDrogaria() {
	}

	public static void main(String[] args) {

		// Instanciando um objeto da classe dos funcionarios.
		Balconista funcionarioDrogaria = new Balconista("13.523.542-PA", "123.456.789", 12, "Victor", "Fellipe",
				"SHCES Qd 1000", "(61)1234-5678", 1234, 5678, 20, 2, 20);

		// Instanciando um objeto da classe dos clientes - DEPENDENCIA CORRIGIDA
		Cliente novoCliente = new Cliente("13.999.888-DF", "444.555.666", 11, "Teste", "Teste", "Gama qd. 1000",
				"(61)-1234-5678", "cliente1@gmail.com");

		// Criando e cadastrando um medicamento
		Medicamento novoMedicamento = new Medicamento("NomeTeste", "LaboratorioTeste", "Adulto", "Comprimido",
				"10/10/2020", "1cp. a cada 8 horas");

		// Criando e cadastrando uma transacao do caixa
		Caixa caixa = new Caixa("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100,
				"10/10/2020", "transacaoTeste", 1);

		// Imprimindo no console as informacoes do Funcionário
		System.out.println("Funcionario:" + funcionarioDrogaria.getNome() + funcionarioDrogaria.getSobrenome());
		System.out.println("CPF: " + funcionarioDrogaria.getCpf() + "-" + funcionarioDrogaria.getDigitoCpf());
		System.out.println("Senha: " + funcionarioDrogaria.getSenha() + ". Codigo: "
				+ funcionarioDrogaria.getCodigo() + ". Fator de comissao: "
				+ funcionarioDrogaria.getFatorComissao() + " %");
		System.out.println("Endereco: " + funcionarioDrogaria.getEndereco() + ".Telefone: "
				+ funcionarioDrogaria.getTelefone());

		// Imprimindo no console as informacoes do Cliente
		System.out.println("Nome do Cliente cadastrado: " + novoCliente.getNome() + novoCliente.getSobrenome()
				+ ".Endereco: " + novoCliente.getEndereco() + ".Telefone: " + novoCliente.getTelefone());
		System.out.println("RG: " + novoCliente.getRg());
		System.out.println("CPF: " + novoCliente.getCpf());
		System.out.println("Email: " + novoCliente.getEmail());

		// Imprimindo no console as informacoes de Medicamento
		System.out.println("Nome do medicamento: " + novoMedicamento.getNome());
		System.out.println("Laboratorio fabricante: " + novoMedicamento.getFabricante());
		System.out.println("Recomendação: " + novoMedicamento.getRecomendacao());
		System.out.println("Tipo: " + novoMedicamento.getTipo());
		System.out.println("Validade: " + novoMedicamento.getValidade());

		// Imprimindo no console as informacoes do Caixa
		System.out.println("\nTipo da transação:" + caixa.getTipo());
		System.out.printf("Valor da transação: R$ %.2f\n", caixa.getValor());
		System.out.println("Data da transação: " + caixa.getData());
		System.out.println("Descrição: " + caixa.getDescricao());
		System.out.println("Saldo atual: " + caixa.getSaldoAtual());
		caixa.depositar(150);
		System.out.println("\nSaldo atual: " + caixa.getSaldoAtual());

		// Decimal Format para restringir o numero de casas decimais dos valores a serem apresentados
		DecimalFormat df = new DecimalFormat("0.00");
		Administrativo administrativo;

		double salarioSemana, salarioMes;
		// Utilização de upcasting (Polimorfismo) e classe abstrata
		Balconista funcionarioDrogaria1 = new Balconista("13.523.542-PA", "123.456.789", 12, "Victor", "Fellipe",
				"SHCES Qd 1000", "(61)-1234-5678", 1234, 5678, 20, 10, 40);
		administrativo = funcionarioDrogaria1;
		salarioSemana = administrativo.calcularSalario();
		salarioMes = (administrativo.calcularSalario()) / 7 * 30;
		System.out.println("Salario do balconista " + funcionarioDrogaria1.getNome() + " por semana e: RS"
				+ df.format(salarioSemana));
		System.out.println(" E por mes: RS " + df.format(salarioMes));

		double salarioCaixaSemana;
		double salarioCaixaMes;
		// Utilização de upcasting (Polimorfismo) e classe abstrata para o Caixa
		Caixa funcionarioDrogaria2 = new Caixa("25.555.444-DF", "123.456.789", 15, "Henrique", "Augusto",
				"SHCES Qd 1000", "(61)-1224-5678", 1, 50, "2/10/2012", "Descricao teste!", 1);
		administrativo = funcionarioDrogaria2;
		salarioCaixaSemana = administrativo.calcularSalario() / 30 * 4;
		salarioCaixaMes = administrativo.calcularSalario();
		System.out.println("Salario do balconista " + funcionarioDrogaria2.getNome() + " por semana e: RS "
				+ df.format(salarioCaixaSemana) + " E por mes: RS " + df.format(salarioCaixaMes));

		// Associacao
		Medicamento medicamento1 = new Medicamento("NomeTeste", "LaboratorioTeste", "Adulto", "Comprimido",
				"10/10/2020", "1cp. a cada 8 horas");
		Medicamento medicamento2 = new Medicamento("NomeTeste 2", "LaboratorioTeste 2", "Criança", "Líquido",
				"01/07/2014", "5 ml de 6 em 6 horas");

		// Lista de medicamentos para associacao
		Medicamento[] medicamentosAs = { medicamento1, medicamento2 };
		funcionarioDrogaria.setMedicamentos(medicamentosAs);
		// Listando
		funcionarioDrogaria.listarMedicamentosAssociados();
		// Fim Associacao

		// Agregacao
		Medicamento medicamento3 = new Medicamento("Paracetamol", "Germed", "Infantil", "Líquido", "01/08/2015",
				"2,5 ml a cada 4 horas");
		Medicamento medicamento4 = new Medicamento("Aspirina", "Germed", "Adulto", "Comprimido", "01/08/2015",
				"8 em 8 horas");

		Registro registro1 = new Registro("12.345");
		Registro registro2 = new Registro("P-4.333");

		registro1.setMedicamento(medicamento3);
		medicamento3.setRegistro(registro1);
		medicamento3.listarRegistro();
		// Espelho da Agregacao
		medicamento4.setRegistro(registro2);
		registro2.setMedicamento(medicamento4);
		registro2.listarMedicamento();
		// Fim Agregacao

		// Composicao3
		Caixa caixaComposicao = new Caixa("25.555.444-DF", "123.456.789", 15, "Henrique", "Augusto",
				"SHCES Qd 1000", "(61)-1224-5678", 1, 50, "2/10/2012", "Descricao teste!", 1);

		caixaComposicao.criarBalconista();
		caixaComposicao.getBalconista().cadastraBalconista("13.523.542-PA", "123.456.789", 12, "Joao", "Augusto",
				"Asa sul", "(61)1234-5678");

		System.out.println("O Funcionario do Caixa é:" + caixaComposicao.getBalconista().getNome());

		// Fim Composicao

		// Polimorfismo
		Pessoa pessoa;
		// Upcasting
		pessoa = funcionarioDrogaria;
		System.out.println("A confirmação do pagamento do Funcionário (1: Confirmado e 0:Rejeitado) é: "
				+ pessoa.confirmacaoPagamento());
		// Upcasting
		pessoa = caixa;
		System.out.println("A confirmação do pagamento do Caixa (1: Confirmado e 0:Rejeitado) é: "
				+ pessoa.confirmacaoPagamento());

		// Interface
		Cliente novoClienteInterface = new Cliente("13.999.888-DF", "444.555.666", 11, "INTERFACE", "INTERFACE",
				"Gama qd. 1000", "(61)-1234-5678", "cliente1@gmail.com");
		novoClienteInterface.remediosRecomendados("GENERICO", "PEDIATRICO");
	}

}
