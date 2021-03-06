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
		if(newConfirmation != 0 || newConfirmation != 1) {
 			System.out.println("A confirmação tem que ser 0 para não ou 1 para sim.\n" +
 							   "Como não foi indentificado nenhum das duas opções, foi atribuido não como padrão.");
 			this.confirmation = 0;
 		}
 		else {
 			this.confirmation = newConfirmation;
 		}
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
		System.out.println("Deseja realmente sair do programa?" + "\n(0) - Não" + "\n(1) - Sim");

	}

	public int GeneralConfirmation(int states) throws IllegalArgumentException {
		System.out.println("Deseja realmente sair?" + "\n(0) - Não" + "\n(1) - Sim");
		
		try {
 			int confirmation = this.readInt();
 		} catch (IllegalArgumentException ex) {
 			throw new IllegalArgumentException("Informe somente números");
 		}
		
		if (confirmation == 1) {
			states = 100;// Sai definitivamente

		}
		else if (confirmation == 0) {
			states = 0;// Volta para o menu

		}
		else {
			// Nothing to do
		}

		return states;
	}

	public int ConfirmationClerk(int states) {
		System.out.println("Deseja realmente sair do menu de cadastro de Balconista?" + 
						   "\n(0) - Não" + "\n(1) - Sim");
		
		try {
 			int clerkOutputConfirmation = this.readInt();
 		} catch (IllegalArgumentException ex) {
 			throw new IllegalArgumentException("Informe somente números");
 		}
		
		if (clerkOutputConfirmation == 1) {
			states = 0;
			System.out.println("Saindo do setor de cadastro de Balconista.");
		}
		else if (clerkOutputConfirmation == 0) {
			states = 2;
		}
		else {
			// Nothing to do
		}
		
		return states;
	}

	public int ConfirmationBox(int states) {
		System.out.println("Deseja realmente sair do menu de cadastro de Caixa?" + "\n(0) - Não" + 
						   "\n(1) - Sim");
		
		try {
 			int cashierOutputConfirmation = this.readInt();
 		} catch (IllegalArgumentException ex) {
 			throw new IllegalArgumentException("Informe somente números");
 		}
		
		if (cashierOutputConfirmation == 1) {
			states = 0;
			System.out.println("Saindo do setor de cadastro de Caixa.");
		}
		else if (cashierOutputConfirmation == 0) {
			states = 3;
		}
		else {
			// Nothing to do
		}
		
		return states;
	}

	public int ConfirmationClient(int states) {
		System.out.println("Deseja realmente sair do menu de cadastro de Cliente?" + "\n(0) - Não" + 
						   "\n(1) - Sim");
		
		try {
 			int clienteOutputConfirmation = this.readInt();
 		} catch (IllegalArgumentException ex) {
 			throw new IllegalArgumentException("Informe somente números");
 		}

		if (clienteOutputConfirmation == 1) {
			states = 0;
			System.out.println("Saindo do setor de cadastro de Cliente.");
		}
		else if (clienteOutputConfirmation == 0) {
			states = 4;
		}
		else {
			// Nothing to do
		}
		
		return states;
	}

	public int ConfirmationMedicament(int states) {
		System.out.println("Deseja realmente sair do menu de cadastro de Medicamento?" + "\n(0) - Nâo" + 
						   "\n(1) - Sim");
		
		try {
			int medicamentOutputConfirmation = this.readInt();
 		} catch (IllegalArgumentException ex) {
 			throw new IllegalArgumentException("Informe somente números");
 		}

		if (medicamentOutputConfirmation == 1) {
			states = 0;
			System.out.println("Saindo do setor de cadastro de Medicamento.");
		}
		else if (medicamentOutputConfirmation == 0) {
			states = 5;
		}
		else {
			// Nothing to do
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
				i = Integer.parseInt(readString()); // Atribuição do termo digitado ao Inteiro pedido
				erro = false;
			} catch (NumberFormatException t) {
				System.out.println("Você inseriu algo diferente de um número. Digite um número inteiro: ");
				erro = true;
			}
		} while (erro);
		
		return i;
	}

	public int menuHelp(int states) {
		System.out.println("A ajuda deste programa tem como objetivo auxilia-lo nos métodos " + 
						   "de inserção para utilização do sistema de gerência.");
		System.out.println("Escolha a opção em que você deseja saber mais");
		System.out.println("(0) - Sair\n(1) - Balconista\n(2) - Caixa \n(3) - " +
						   "Cliente\n(4) - Medicamento");

		try {
			menuOperation = new Scanner(System.in).nextInt();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Informe somente números.");
		}
		
		switch (menuOperation) {
			case 0:
				System.out.println("Deseja realmente sair do menu de Ajuda?" + "\n(0) - Não" + 
								   "\n(1) - Sim");
				
				try {
					helpOutputConfirmation = scanner.nextInt();
 				} catch (IllegalArgumentException ex) {
 					throw new IllegalArgumentException("Informe somente números.");
 				}

				if (helpOutputConfirmation == 1) {
					states = 0;
					System.out.println("Saindo do setor de cadastro de Medicamento.");
				}
				else if (helpOutputConfirmation == 0) {
					states = 6;
				}
				else {
					// Nothing to do
				}
	
				break;
				
			case 1:
				System.out.println("Os campos de nome,sobrenome e endereço para 'Balconista' devem ser " +
								   "inseridos diretamente letra a letra onde cada informação deve ser " +
								   "separada por espaço simples");
				System.out.println("Exemplo: \nDigite nome: 'Victor'");
				System.out.println("Já os campos de 'senha', 'senha de farmácia popular', 'código', " +
						  		   "'fator de comissao' e 'horas trabalhadas' devem ser inseridos como numeros inteiros");
				System.out.println("Exemplo: \nDigite a senha: 5734");
				System.out.println("Os campos de rg devem inseridos com o orgão expeditor seguido do número.");
				System.out.println("Exemplo: \nDigite o rg: MG452333");
				System.out
						.println("O campo de cpf deve ser inserido sem o dígito, pois este será inserido no campo 'Dígito" +
								 " de cpf' a partir dos 2 números que se deseja cadastrar");
				System.out.println("Exemplo: \nSe seu cpf for 123.456.789-00, digite apenas: '123456789'");
				System.out
						.println("O campo 'validade' de medicamento deve ser inserido sem nenhum caractere espaçando os " +
								 "dias do mês e ano respectivamente");
				System.out.println("Exemplo: \nData de validade: 12122014");
				states = 6;
				break;
			case 2:
				System.out
						.println("Os campos de nome,sobrenome e endereço para 'Caixa' devem ser inseridos diretamente letra"+
								 " a letra onde cada informação deve ser separada por espaço simples");
				System.out.println("Exemplo: \nDigite nome: 'Victor'");
				System.out.println("Já o campo de 'código' deve ser inserido como numero inteiro");
				System.out.println("Exemplo: \nDigite o código do caixa: 2");
				System.out.println("Os campos de rg devem inseridos com o orgão expeditor seguido do número.");
				System.out.println("Exemplo: \nDigite o rg: MG452333");
				System.out
						.println("O campo de cpf deve ser inserido sem o dígito, pois este será inserido no campo 'Dígito " +
								 "de cpf' a partir dos 2 números que se deseja cadastrar");
				System.out.println("Exemplo: \nSe seu cpf for 123.456.789-00, digite apenas: '123456789'");
				states = 6;
				break;
				
			case 3:
				System.out
						.println("Os campos de nome,sobrenome,endereço e email para 'Cliente' devem ser inseridos " +
								 "diretamente letra a letra onde cada informação deve ser separada por espaço simples");
				System.out.println("Exemplo: \nDigite nome: 'Victor'");
				System.out.println("Os campos de rg devem inseridos com o órgão expeditor seguido do número.");
				System.out.println("Exemplo: \nDigite o rg: MG452333");
				System.out
						.println("O campo de cpf deve ser inserido sem o dígito, pois este será inserido no campo " +
								 "'Dígito de cpf' a partir dos 2 números que se deseja cadastrar");
				System.out.println("Exemplo: \nSe seu cpf for 123.456.789-00, digite apenas: '123456789'");
				states = 6;
				break;
				
			case 4:
				System.out
						.println("Os campos de 'nome','fabricante','recomendação','tipo' e 'posologia' para 'Medicamento'" +
								 " devem ser inseridos diretamente onde cada informação deve ser separada por espaço simples");
				System.out.println("Exemplo: \nDigite nome: 'Neosaldina'");
				System.out
						.println("Já o campo 'validade' de medicamento deve ser inserido sem nenhum caractere espaçando os " +
								 "dias do mês e ano respectivamente");
				System.out.println("Exemplo: \nData de validade: 12122014");
				states = 6;
				break;
				
			default:
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