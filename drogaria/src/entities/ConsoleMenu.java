package entities;

import java.util.Scanner;

public class ConsoleMenu {

	protected int confirmation = 0;
	protected int clerkOutputConfirmation = 0;
	protected int cashierOutputConfirmation = 0;
	protected int clienteOutputConfirmation = 0; 
	protected int medicamentOutputConfirmation = 0;
	protected int helpOutputConfirmation = 0;
	protected int menuOperation = 0;

	private static String string = "";

	Scanner scanner = new Scanner(System.in);

	public ConsoleMenu() {
	}

	public ConsoleMenu(int newConfirmation) {
		this.confirmation = newConfirmation;
	}

	public ConsoleMenu(int newConfirmation, int clerkOutputConfirmation, int cashierOutputConfirmation,
			int clienteOutputConfirmation, int medicamentOutputConfirmation, int helpOutputConfirmation,
			int menuOperation) {
			
		this.confirmation = newConfirmation;
		this.clerkOutputConfirmation = clerkOutputConfirmation;
		this.cashierOutputConfirmation = cashierOutputConfirmation;
		this.clienteOutputConfirmation = clienteOutputConfirmation;
		this.medicamentOutputConfirmation = medicamentOutputConfirmation;
		this.helpOutputConfirmation = helpOutputConfirmation;
		this.menuOperation = menuOperation;
		
	}	

	public void consoleOutputConfirmation() {
		System.out.println("Deseja realmente sair do programa?" + "\n(0) - Nao" + "\n(1) - Sim");

	}

	public int menuOutputConfirmation(int states) {
		System.out.println("Deseja realmente sair?" + "\n(0) - Nao" + "\n(1) - Sim");
		int confirmation = this.readInt();
		if (confirmation == 1) {
			states = 100;// Sai definitivamente

		}
		else if (confirmation == 0) {
			states = 0;// Volta para o menu

		}

		return states;

	}

	public int clerkConfirmation(int states) {
		System.out.println("Deseja realmente sair do menu de cadastro de Balconista?" + 
						   "\n(0) - Nao" + "\n(1) - Sim");
		
		int clerkOutputConfirmation = this.readInt();
		
		if (clerkOutputConfirmation == 1) {
			states = 0;
			System.out.println("Saindo do setor de cadastro de Balconista.");
		}
		else if (clerkOutputConfirmation == 0) {
			states = 2;
		}
		
		return states;
	}

	public int confirmacaoCaixa(int states) {
		System.out.println("Deseja realmente sair do menu de cadastro de Caixa?" + "\n(0) - Nao" + "\n(1) - Sim");
		int cashierOutputConfirmation = this.readInt();
		if (cashierOutputConfirmation == 1) {
			states = 0;
			System.out.println("Saindo do setor de cadastro de Caixa.");
		}
		else if (cashierOutputConfirmation == 0) {
			states = 3;
		}
		return states;
	}

	public int ConfirmacaoCliente(int states) {
		System.out
				.println("Deseja realmente sair do menu de cadastro de Cliente?" + "\n(0) - Nao" + "\n(1) - Sim");
		int clienteOutputConfirmation = this.readInt();
		if (clienteOutputConfirmation == 1) {
			states = 0;
			System.out.println("Saindo do setor de cadastro de Cliente.");
		}
		else if (clienteOutputConfirmation == 0) {
			states = 4;
		}
		return states;
	}

	public int ConfirmacaoMedicamento(int states) {
		System.out.println("Deseja realmente sair do menu de cadastro de Medicamento?" + "\n(0) - Nao"
				+ "\n(1) - Sim");
		int medicamentOutputConfirmation = this.readInt();
		if (medicamentOutputConfirmation == 1) {
			states = 0;
			System.out.println("Saindo do setor de cadastro de Medicamento.");
		}
		else if (medicamentOutputConfirmation == 0) {
			states = 5;
		}

		return states;
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
				i = Integer.parseInt(readString()); // Atribui��o do termo digitado ao Inteiro pedido
				erro = false;
			} catch (NumberFormatException t) {
				System.out.println("Voce inseriu algo diferente de um numero. Digite um numero inteiro: ");
				erro = true;
			}
		} while (erro);
		return i;
	}

	public int menuAjuda(int states) {
		System.out
				.println("A ajuda deste programa tem como objetivo auxilia-lo nos metodos de insercao para utilizacao do sistema de gerencia.");
		System.out.println("Escolha a opcao em que voce deseja saber mais");
		System.out.println("(0) - Sair\n(1) - Balconista\n(2) - Caixa \n(3) - Cliente\n(4) - Medicamento");

		menuOperation = new Scanner(System.in).nextInt();
		switch (menuOperation) {

		case 0:
			System.out.println("Deseja realmente sair do menu de Ajuda?" + "\n(0) - Nao" + "\n(1) - Sim");
			helpOutputConfirmation = scanner.nextInt();
			if (helpOutputConfirmation == 1) {
				states = 0;
				System.out.println("Saindo do setor de cadastro de Medicamento.");
			}
			else if (helpOutputConfirmation == 0) {
				states = 6;
			}

			break;
		case 1:
			System.out
					.println("Os campos de nome,sobrenome e endereco para 'Balconista' devem ser inseridos diretamente letra a letra onde cada informacao deve ser separada por espaco simples");
			System.out.println("Exemplo: \nDigite nome: 'Victor'");
			System.out
					.println("Ja os campos de 'senha', 'senha de farmacia popular', 'codigo', 'fator de comissao' e 'horas trabalhadas' devem ser inseridos como numeros inteiros");
			System.out.println("Exemplo: \nDigite a senha: 5734");
			System.out.println("Os campos de rg devem inseridos com o orgao expeditor seguido do numero.");
			System.out.println("Exemplo: \nDigite o rg: MG452333");
			System.out
					.println("O campo de cpf deve ser inserido sem o digito, pois este sera inserido no campo 'Digito de cpf' a partir dos 2 numeros que se deseja cadastrar");
			System.out.println("Exemplo: \nSe seu cpf for 123.456.789-00, digite apenas: '123456789'");
			System.out
					.println("O campo 'validade' de medicamento deve ser inserido sem nenhum caractere espacando os dias do mes e ano respectivamente");
			System.out.println("Exemplo: \nData de validade: 12122014");
			states = 6;
			break;
		case 2:
			System.out
					.println("Os campos de nome,sobrenome e endereco para 'Caixa' devem ser inseridos diretamente letra a letra onde cada informacao deve ser separada por espaco simples");
			System.out.println("Exemplo: \nDigite nome: 'Victor'");
			System.out.println("Ja o campo de 'codigo' deve ser inserido como numero inteiro");
			System.out.println("Exemplo: \nDigite o codigo do caixa: 2");
			System.out.println("Os campos de rg devem inseridos com o orgao expeditor seguido do numero.");
			System.out.println("Exemplo: \nDigite o rg: MG452333");
			System.out
					.println("O campo de cpf deve ser inserido sem o digito, pois este sera inserido no campo 'Digito de cpf' a partir dos 2 numeros que se deseja cadastrar");
			System.out.println("Exemplo: \nSe seu cpf for 123.456.789-00, digite apenas: '123456789'");
			states = 6;
			break;
		case 3:
			System.out
					.println("Os campos de nome,sobrenome,endereco e email para 'Cliente' devem ser inseridos diretamente letra a letra onde cada informacao deve ser separada por espaco simples");
			System.out.println("Exemplo: \nDigite nome: 'Victor'");
			System.out.println("Os campos de rg devem inseridos com o orgao expeditor seguido do numero.");
			System.out.println("Exemplo: \nDigite o rg: MG452333");
			System.out
					.println("O campo de cpf deve ser inserido sem o digito, pois este sera inserido no campo 'Digito de cpf' a partir dos 2 numeros que se deseja cadastrar");
			System.out.println("Exemplo: \nSe seu cpf for 123.456.789-00, digite apenas: '123456789'");
			states = 6;
			break;
		case 4:
			System.out
					.println("Os campos de 'nome','fabricante','recomendacao','tipo' e 'posologia' para 'Medicamento' devem ser inseridos diretamente onde cada informacao deve ser separada por espaco simples");
			System.out.println("Exemplo: \nDigite nome: 'Neosaldina'");
			System.out
					.println("Ja o campo 'validade' de medicamento deve ser inserido sem nenhum caractere espacando os dias do mas e ano respectivamente");
			System.out.println("Exemplo: \nData de validade: 12122014");
			states = 6;
			break;
		}
		return states;

	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public int getconfirmation() {
		return confirmation;
	}

	public void setconfirmation(int confirmation) {
		this.confirmation = confirmation;
	}

	public int getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(int confirmation) {
		this.confirmation = confirmation;
	}

	public int getClerkOutputConfirmation() {
		return clerkOutputConfirmation;
	}

	public void setClerkOutputConfirmation(int clerkOutputConfirmation) {
		this.clerkOutputConfirmation = clerkOutputConfirmation;
	}

	public int getCashierOutputConfirmation() {
		return cashierOutputConfirmation;
	}

	public void setCashierOutputConfirmation(int cashierOutputConfirmation) {
		this.cashierOutputConfirmation = cashierOutputConfirmation;
	}

	public int getClienteOutputConfirmation() {
		return clienteOutputConfirmation;
	}

	public void setClienteOutputConfirmation(int clienteOutputConfirmation) {
		this.clienteOutputConfirmation = clienteOutputConfirmation;
	}

	public int getMedicamentOutputConfirmation() {
		return medicamentOutputConfirmation;
	}

	public void setMedicamentOutputConfirmation(int medicamentOutputConfirmation) {
		this.medicamentOutputConfirmation = medicamentOutputConfirmation;
	}

	public int getHelpOutputConfirmation() {
		return helpOutputConfirmation;
	}

	public void setHelpOutputConfirmation(int helpOutputConfirmation) {
		this.helpOutputConfirmation = helpOutputConfirmation;
	}

	public int getMenuOperation() {
		return menuOperation;
	}

	public void setMenuOperation(int menuOperation) {
		this.menuOperation = menuOperation;
	}

	public static String getString() {
		return string;
	}

	public static void setString(String string) {
		ConsoleMenu.string = string;
	}
}