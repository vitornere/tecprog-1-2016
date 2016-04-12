package entidades;

import java.util.Scanner;

public class Complementar {

	protected int confirmacao = 0, confirmacaoSaidaBalconista = 0, confirmacaoSaidaCaixa = 0,
			confirmacaoSaidaCliente = 0, confirmacaoSaidaMedicamento = 0, confirmacaoSaidaAjuda = 0,
			operacaoMenu = 0;

	private static String string = "";

	Scanner scanner = new Scanner(System.in);
	Scanner scannerR = new Scanner(System.in);

	public Complementar() {
	}

	public Complementar(int confirmacaoTeste) {
		this.confirmacao = confirmacaoTeste;
	}

	public Complementar(int confirmacaoTeste, int confirmacaoSaidaBalconista, int confirmacaoSaidaCaixa,
			int confirmacaoSaidaCliente, int confirmacaoSaidaMedicamento, int confirmacaoSaidaAjuda,
			int operacaoMenu) {
			
		this.confirmacao = confirmacaoTeste;
		this.confirmacaoSaidaBalconista = confirmacaoSaidaBalconista;
		this.confirmacaoSaidaCaixa = confirmacaoSaidaCaixa;
		this.confirmacaoSaidaCliente = confirmacaoSaidaCliente;
		this.confirmacaoSaidaMedicamento = confirmacaoSaidaMedicamento;
		this.confirmacaoSaidaAjuda = confirmacaoSaidaAjuda;
		this.operacaoMenu = operacaoMenu;
		
	}	

	public void ConfirmacaoGeral() {
		System.out.println("Deseja realmente sair do programa?" + "\n(0) - Não" + "\n(1) - Sim");

	}

	public int ConfirmacaoGeral2(int estados) {
		System.out.println("Deseja realmente sair?" + "\n(0) - Não" + "\n(1) - Sim");
		int confirmacao = this.readInt();
		if (confirmacao == 1) {
			estados = 100;// Sai definitivamente

		}
		else if (confirmacao == 0) {
			estados = 0;// Volta para o menu

		}

		return estados;

	}

	public int ConfirmacaoBalconista(int estados) {
		System.out.println("Deseja realmente sair do menu de cadastro de Balconista?" + "\n(0) - Não"
				+ "\n(1) - Sim");
		int confirmacaoSaidaBalconista = this.readInt();
		if (confirmacaoSaidaBalconista == 1) {
			estados = 0;
			System.out.println("Saindo do setor de cadastro de Balconista.");
		}
		else if (confirmacaoSaidaBalconista == 0) {
			estados = 2;
		}
		return estados;
	}

	public int ConfirmacaoCaixa(int estados) {
		System.out.println("Deseja realmente sair do menu de cadastro de Caixa?" + "\n(0) - Não" + "\n(1) - Sim");
		int confirmacaoSaidaCaixa = this.readInt();
		if (confirmacaoSaidaCaixa == 1) {
			estados = 0;
			System.out.println("Saindo do setor de cadastro de Caixa.");
		}
		else if (confirmacaoSaidaCaixa == 0) {
			estados = 3;
		}
		return estados;
	}

	public int ConfirmacaoCliente(int estados) {
		System.out
				.println("Deseja realmente sair do menu de cadastro de Cliente?" + "\n(0) - Não" + "\n(1) - Sim");
		int confirmacaoSaidaCliente = this.readInt();
		if (confirmacaoSaidaCliente == 1) {
			estados = 0;
			System.out.println("Saindo do setor de cadastro de Cliente.");
		}
		else if (confirmacaoSaidaCliente == 0) {
			estados = 4;
		}
		return estados;
	}

	public int ConfirmacaoMedicamento(int estados) {
		System.out.println("Deseja realmente sair do menu de cadastro de Medicamento?" + "\n(0) - Não"
				+ "\n(1) - Sim");
		int confirmacaoSaidaMedicamento = this.readInt();
		if (confirmacaoSaidaMedicamento == 1) {
			estados = 0;
			System.out.println("Saindo do setor de cadastro de Medicamento.");
		}
		else if (confirmacaoSaidaMedicamento == 0) {
			estados = 5;
		}

		return estados;
	}

	// Ler String
	public static String readString() {
		string = new Scanner(System.in).nextLine();
		return string;
	}

	// Ler Int
	public static int readInt() {
		boolean erro = false;
		int i = 0;
		do {
			try {
				i = Integer.parseInt(readString()); // Atribuição do termo digitado ao Inteiro pedido
				erro = false;
			} catch (NumberFormatException t) {
				System.out.println("Você inseriu algo diferente de um número. Digite um número inteiro: ");
				erro = true;
			}
		} while (erro);
		return i;
	}

	public int menuAjuda(int estados) {
		System.out
				.println("A ajuda deste programa tem como objetivo auxiliá-lo nos métodos de inserção para utilização do sistema de gerência.");
		System.out.println("Escolha a opção em que você deseja saber mais");
		System.out.println("(0) - Sair\n(1) - Balconista\n(2) - Caixa \n(3) - Cliente\n(4) - Medicamento");

		operacaoMenu = new Scanner(System.in).nextInt();
		switch (operacaoMenu) {

		case 0:
			System.out.println("Deseja realmente sair do menu de Ajuda?" + "\n(0) - Não" + "\n(1) - Sim");
			confirmacaoSaidaAjuda = scanner.nextInt();
			if (confirmacaoSaidaAjuda == 1) {
				estados = 0;
				System.out.println("Saindo do setor de cadastro de Medicamento.");
			}
			else if (confirmacaoSaidaAjuda == 0) {
				estados = 6;
			}

			break;
		case 1:
			System.out
					.println("Os campos de nome,sobrenome e endereço para 'Balconista' devem ser inseridos diretamente letra a letra onde cada informação deve ser separada por espaço simples");
			System.out.println("Exemplo: \nDigite nome: 'Victor'");
			System.out
					.println("Já os campos de 'senha', 'senha de farmácia popular', 'código', 'fator de comissao' e 'horas trabalhadas' devem ser inseridos como numeros inteiros");
			System.out.println("Exemplo: \nDigite a senha: 5734");
			System.out.println("Os campos de rg devem inseridos com o órgão expeditor seguido do número.");
			System.out.println("Exemplo: \nDigite o rg: MG452333");
			System.out
					.println("O campo de cpf deve ser inserido sem o dígito, pois este será inserido no campo 'Dígito de cpf' a partir dos 2 números que se deseja cadastrar");
			System.out.println("Exemplo: \nSe seu cpf for 123.456.789-00, digite apenas: '123456789'");
			System.out
					.println("O campo 'validade' de medicamento deve ser inserido sem nenhum caractere espaçando os dias do mês e ano respectivamente");
			System.out.println("Exemplo: \nData de validade: 12122014");
			estados = 6;
			break;
		case 2:
			System.out
					.println("Os campos de nome,sobrenome e endereço para 'Caixa' devem ser inseridos diretamente letra a letra onde cada informação deve ser separada por espaço simples");
			System.out.println("Exemplo: \nDigite nome: 'Victor'");
			System.out.println("Já o campo de 'código' deve ser inserido como numero inteiro");
			System.out.println("Exemplo: \nDigite o código do caixa: 2");
			System.out.println("Os campos de rg devem inseridos com o órgão expeditor seguido do número.");
			System.out.println("Exemplo: \nDigite o rg: MG452333");
			System.out
					.println("O campo de cpf deve ser inserido sem o dígito, pois este será inserido no campo 'Dígito de cpf' a partir dos 2 números que se deseja cadastrar");
			System.out.println("Exemplo: \nSe seu cpf for 123.456.789-00, digite apenas: '123456789'");
			estados = 6;
			break;
		case 3:
			System.out
					.println("Os campos de nome,sobrenome,endereço e email para 'Cliente' devem ser inseridos diretamente letra a letra onde cada informação deve ser separada por espaço simples");
			System.out.println("Exemplo: \nDigite nome: 'Victor'");
			System.out.println("Os campos de rg devem inseridos com o órgão expeditor seguido do número.");
			System.out.println("Exemplo: \nDigite o rg: MG452333");
			System.out
					.println("O campo de cpf deve ser inserido sem o dígito, pois este será inserido no campo 'Dígito de cpf' a partir dos 2 números que se deseja cadastrar");
			System.out.println("Exemplo: \nSe seu cpf for 123.456.789-00, digite apenas: '123456789'");
			estados = 6;
			break;
		case 4:
			System.out
					.println("Os campos de 'nome','fabricante','recomendação','tipo' e 'posologia' para 'Medicamento' devem ser inseridos diretamente onde cada informação deve ser separada por espaço simples");
			System.out.println("Exemplo: \nDigite nome: 'Neosaldina'");
			System.out
					.println("Já o campo 'validade' de medicamento deve ser inserido sem nenhum caractere espaçando os dias do mês e ano respectivamente");
			System.out.println("Exemplo: \nData de validade: 12122014");
			estados = 6;
			break;
		}
		return estados;

	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public int getconfirmacao() {
		return confirmacao;
	}

	public void setconfirmacao(int confirmacao) {
		this.confirmacao = confirmacao;
	}

	public int getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(int confirmacao) {
		this.confirmacao = confirmacao;
	}

	public int getConfirmacaoSaidaBalconista() {
		return confirmacaoSaidaBalconista;
	}

	public void setConfirmacaoSaidaBalconista(int confirmacaoSaidaBalconista) {
		this.confirmacaoSaidaBalconista = confirmacaoSaidaBalconista;
	}

	public int getConfirmacaoSaidaCaixa() {
		return confirmacaoSaidaCaixa;
	}

	public void setConfirmacaoSaidaCaixa(int confirmacaoSaidaCaixa) {
		this.confirmacaoSaidaCaixa = confirmacaoSaidaCaixa;
	}

	public int getConfirmacaoSaidaCliente() {
		return confirmacaoSaidaCliente;
	}

	public void setConfirmacaoSaidaCliente(int confirmacaoSaidaCliente) {
		this.confirmacaoSaidaCliente = confirmacaoSaidaCliente;
	}

	public int getConfirmacaoSaidaMedicamento() {
		return confirmacaoSaidaMedicamento;
	}

	public void setConfirmacaoSaidaMedicamento(int confirmacaoSaidaMedicamento) {
		this.confirmacaoSaidaMedicamento = confirmacaoSaidaMedicamento;
	}

	public int getConfirmacaoSaidaAjuda() {
		return confirmacaoSaidaAjuda;
	}

	public void setConfirmacaoSaidaAjuda(int confirmacaoSaidaAjuda) {
		this.confirmacaoSaidaAjuda = confirmacaoSaidaAjuda;
	}

	public int getOperacaoMenu() {
		return operacaoMenu;
	}

	public void setOperacaoMenu(int operacaoMenu) {
		this.operacaoMenu = operacaoMenu;
	}

	public static String getString() {
		return string;
	}

	public static void setString(String string) {
		Complementar.string = string;
	}

	public Scanner getScannerR() {
		return scannerR;
	}

	public void setScannerR(Scanner scannerR) {
		this.scannerR = scannerR;
	}

}
