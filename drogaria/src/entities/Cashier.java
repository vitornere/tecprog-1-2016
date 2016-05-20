package entities;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Clerk;
import entities.Client;

public class Cashier extends Person {
	private float saldoAtual = 0;
	private int tipo; // 0-Receita ou 1-Despesa
	private float valor;
	private String data;
	private String descricao; // Informacoes
	private Clerk balconista;
	protected Client[] clientes;
	protected Clerk[] funcionario;
	private int codigo;

	// COnsole
	Cashier[] caixa = {};
	Scanner scanner = new Scanner(System.in);
	Scanner scanner1 = new Scanner(System.in);
	int operacao = 0;
	int operacaoCaixa = 0, repeteCadastroCaixa = 0, repeteCaixa = 0;
	int codigoExclusao = 0;
	int confirmacaoExclusaoCaixa = 0;

	public Cashier() {
		super();
	}

	public Cashier(String rgPessoa, String cpfPessoa, int digitoCpfPessoa, String nomePessoa,
			String sobrenomePessoa, String enderecoPessoa, String telefonePessoa, int tipoTransacao,
			float valorTransacao, String dataTransacao, String descricaoTransacao, int codigo) {
		super(rgPessoa, cpfPessoa, digitoCpfPessoa, nomePessoa, sobrenomePessoa, enderecoPessoa, telefonePessoa);
		if (tipo == 0 || tipo == 1) {
			this.tipo = tipoTransacao;
			this.valor = valorTransacao;
			this.data = dataTransacao;
			this.descricao = descricaoTransacao;
			this.codigo = codigo;
		}
		else {
			System.out.println("Caixa nao cadastrado.");

		}
		// Composicao - so existe um Caixa se ja existir um balconista!
		Clerk balconista = new Clerk(this);
		this.balconista = balconista;

		System.out.println("Verificacao de um funcion�rio para atend�-lo.");
		Clerk.setStatusBalconista(true); // Utilizacao de static para depend�ncia
	}

	public Cashier(String rgPessoa, String cpfPessoa, int digitoCpfPessoa, String nomePessoa,
			String sobrenomePessoa, String enderecoPessoa, String telefonePessoa, int codigo) {
		super(rgPessoa, cpfPessoa, digitoCpfPessoa, nomePessoa, sobrenomePessoa, enderecoPessoa, telefonePessoa);
		this.codigo = codigo;
	}

	// Composicao - Criacao de um novo caixa a partir de um balconista
	public void createClerk() {
		Clerk balconista = new Clerk(this);
		this.balconista = balconista;
	}

	// Verifica a confirma��o de pagamento na Caixa (POLIMORFISMO)
	public double paymentConfirmation() {
		if (this.getConfirmacaoPagamento() == 1) {
			return 1; // Confirma que o pagamento foi aceito.
		}
		else {
			return 0; // Confirma que o pagamento n�o foi aceito
		}
	}

	// M�todo depositar
	public void depositar(float valor) {
		System.out.println("Valor do saldo atual: " + saldoAtual);
		saldoAtual += valor;
		System.out.println("Valor ap�s o dep�sito: " + saldoAtual);
	}

	public double calculateSalary() {
		this.setSalario(715);
		return 715;
	}

	public void menuCashier() {
		System.out.println("\nInsira o que deseja fazer de acordo com as op��es seguintes:" + "\n(0) - Sair\n"
				+ "(1) - Cadastrar novo Caixa\n" + "(2) - Listar Caixas\n" + "(3) - Excluir Caixa\n");
	}

	public void registerCashier(ArrayList<Cashier> listaDeCaixas) {

		System.out.println("Digite o rg do Caixa: ");
		String rgPessoa = Complementary.readString();

		System.out.println("Digite o cpf do Caixa: ");
		String cpfPessoa = Complementary.readString();

		System.out.println("Digite o digito do cpf do Caixa: ");
		int digitoCpfPessoa = Complementary.readInt();

		System.out.println("Digite o nome do Caixa: ");
		String nomePessoa = Complementary.readString();

		System.out.println("Digite o sobrenome completo do Caixa: ");
		String sobrenomePessoa = Complementary.readString();

		System.out.println("Digite o endereco do Caixa: ");
		String enderecoPessoa = Complementary.readString();

		System.out.println("Digite o telefone do Caixa:");
		String telefonePessoa = Complementary.readString();

		System.out.println("Digite o c�digo do Caixa:");
		int codigo = Complementary.readInt();

		Cashier caixa = new Cashier(rgPessoa, cpfPessoa, digitoCpfPessoa, nomePessoa, sobrenomePessoa, enderecoPessoa,
				telefonePessoa, codigo);

		listaDeCaixas.add(caixa);

		System.out.println("O(A) caixa " + caixa.getName() + " foi cadastrado(a) com sucesso!");

	}

	public void listCashiers(ArrayList<Cashier> listaDeCaixas) {
		if (listaDeCaixas.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("\nLista de cadastros de Caixas\n");
			for (int b = 0; b < listaDeCaixas.size(); b++) {
				Cashier t = listaDeCaixas.get(b);
				System.out.println("\nCadastro de n�mero:" + (b + 1));

				System.out.println("\nNome: " + listaDeCaixas.get(b).getName() + " "
						+ listaDeCaixas.get(b).getPastName());

				System.out.println("\nRG: " + t.getIdentity().substring(0, 2) + "-"
						+ t.getIdentity().substring(2, t.getIdentity().length()));

				System.out.println("Cpf: " + t.getCpfPerson().substring(0, 3) + "."
						+ t.getCpfPerson().substring(3, 6) + "." + t.getCpfPerson().substring(6, 9) + "-"  + t.getDigitCpfPerson());

				System.out.println("\nTelefone: (" + t.getPhone().substring(0, 2) + ") "
						+ t.getPhone().substring(2, 6) + "-" + t.getPhone().substring(6, 10));

				System.out.println("\nC�digo do Caixa: " + t.getCodigo());

				System.out.println("\nSal�rio: R$ " + t.calculateSalary());
			}
			System.out.println("Fim da lista de cadastro de Caixas.\n");
		}

	}

	public void deleteCashier(ArrayList<Cashier> listaDeCaixas) {

		if (listaDeCaixas.size() == 0) {
			System.out.println("Cadastro em branco!\n");
		}
		else {
			System.out.println("Digite o numero do cadastro do Caixa que deseja excluir: ");
			this.setCodigoExclusao(scanner.nextInt());
			System.out.println("Voc� deseja realmente excluir o cadastro de numero: " + this.codigoExclusao + "?"
					+ "\n(0) - N�o" + "\n(1) - Sim");
			this.setConfirmacaoExclusaoCaixa(scanner.nextInt());
			if (confirmacaoExclusaoCaixa == 1) {
				this.setCodigoExclusao(codigoExclusao - 1);
				listaDeCaixas.remove(codigoExclusao);

				System.out.println("A lista foi alterada");
				listCashiers(listaDeCaixas);
			}
			else if (confirmacaoExclusaoCaixa == 0) {
				this.setCodigoExclusao(0);
			}
		}

	}

	// Especificando m�todos get and set.
	private void setSalario(double i) {
	}

	public Clerk[] getFuncionario() {
		return funcionario;
	}

	public Client[] getCliente() {
		return clientes;
	}

	public void setClientes(Client[] clientes) {
		this.clientes = clientes;
	}

	public void listarClientes() {
		for (int x = 0; x < (clientes.length); x += 1) {
			System.out.println(("Clientes: [" + x + "]: " + clientes[x]));
		}
	}

	public int getTransationType() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public float getTransactionValue() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getTransationDate() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescription() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getCurrentSale() {
		return saldoAtual;
	}

	public void setSaldoAtual(float saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public Client[] getClientes() {
		return clientes;
	}

	public void setFuncionario(Clerk[] funcionario) {
		this.funcionario = funcionario;
	}

	public Clerk getClerk() {
		return balconista;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Cashier[] getCaixa() {
		return caixa;
	}

	public void setCaixa(Cashier[] caixa) {
		this.caixa = caixa;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public Scanner getScanner1() {
		return scanner1;
	}

	public void setScanner1(Scanner scanner1) {
		this.scanner1 = scanner1;
	}

	public int getOperacao() {
		return operacao;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}

	public int getOperacaoCaixa() {
		return operacaoCaixa;
	}

	public void setOperacaoCaixa(int operacaoCaixa) {
		this.operacaoCaixa = operacaoCaixa;
	}

	public int getRepeteCadastroCaixa() {
		return repeteCadastroCaixa;
	}

	public void setRepeteCadastroCaixa(int repeteCadastroCaixa) {
		this.repeteCadastroCaixa = repeteCadastroCaixa;
	}

	public int getRepeteCaixa() {
		return repeteCaixa;
	}

	public void setRepeteCaixa(int repeteCaixa) {
		this.repeteCaixa = repeteCaixa;
	}

	public int getCodigoExclusao() {
		return codigoExclusao;
	}

	public void setCodigoExclusao(int codigoExclusao) {
		this.codigoExclusao = codigoExclusao;
	}

	public int getConfirmacaoExclusaoCaixa() {
		return confirmacaoExclusaoCaixa;
	}

	public void setConfirmacaoExclusaoCaixa(int confirmacaoExclusaoCaixa) {
		this.confirmacaoExclusaoCaixa = confirmacaoExclusaoCaixa;
	}

	public void setBalconista(Clerk balconista) {
		this.balconista = balconista;
	}
}