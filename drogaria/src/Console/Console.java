package Console;

import java.util.ArrayList;
import java.util.Scanner;

import entidades.Balconista;
import entidades.Caixa;
import entidades.Cliente;
import entidades.Complementar;
import entidades.Medicamento;

public class Console {

	private static Scanner scanner;

	public Console() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);

		// Atributos

		ArrayList<Balconista> listaDeBalconistas = new ArrayList<Balconista>();
		ArrayList<Caixa> listaDeCaixas = new ArrayList<Caixa>();
		ArrayList<Cliente> listaDeClientes = new ArrayList<Cliente>();
		ArrayList<Medicamento> listaDeMedicamentos = new ArrayList<Medicamento>();

		Balconista balconista = new Balconista();
		Caixa caixa = new Caixa();
		Cliente cliente = new Cliente();
		Medicamento medicamento = new Medicamento();

		Complementar complementar = new Complementar();

		int operacao = 0;
		int operacaoBalconista = 0;
		int operacaoCaixa = 0;
		int operacaoCliente = 0;
		int operacaoMedicamento = 0;

		int estados = 0;

		/*
		 * Lista de estados:
		 * 0 = Inicio do programa, menu do que se deseja fazer
		 * 1 = Passou do menu inicial, volta pro menu do q se deseja fazer (balconista, caixa etc.)
		 * 2 = balconista
		 * 3 = caixa
		 * 4 = cliente
		 * 5 = medicamento
		 */

		while (estados != 100) {
			if (estados == 0) {

				balconista.menuInicial();

				operacao = scanner.nextInt();

				assert ((operacao >= 0) && (operacao <= 5)) : "Numero invalido: " + operacao; // Se digitar numero errado, sai do programa

				// Amarração para sair
				if (operacao == 0) {
					estados = complementar.ConfirmacaoGeral2(estados);
				}
				else {
					estados++;

					if (estados >= 1 && estados != 100) {
						// Dentro do menu
						switch (operacao) {
						case 1:
							if (operacao == 1) {
								estados = 2;
							}
							while (estados == 2) {

								balconista.menuBalconista();// Menu de balconistas
								operacaoBalconista = scanner.nextInt();
								if (operacaoBalconista == 0) {
									estados = complementar.ConfirmacaoBalconista(estados);
								}

								else if (operacaoBalconista == 1) {
									balconista.cadastrarBalconista(listaDeBalconistas);
									estados = 2;
								}

								else if (operacaoBalconista == 2) {
									balconista.listarBalconistas(listaDeBalconistas);
									estados = 2;
								}

								else if (operacaoBalconista == 3) {
									balconista.excluirBalconista(listaDeBalconistas);
									estados = 2;
								}

								// Fecha o if de balconista
							}// if de estados = 2;
						case 2:
							if (operacao == 2) {
								estados = 3;
							}
							while (estados == 3) {

								caixa.menuCaixa();// Menu caixa
								operacaoCaixa = scanner.nextInt();
								if (operacaoCaixa == 0) {
									estados = complementar.ConfirmacaoCaixa(estados);
								}

								else if (operacaoCaixa == 1) {
									caixa.cadastrarCaixa(listaDeCaixas);
									estados = 3;
								}

								else if (operacaoCaixa == 2) {
									caixa.listarCaixas(listaDeCaixas);
									estados = 3;
								}

								else if (operacaoCaixa == 3) {
									caixa.excluirCaixa(listaDeCaixas);
									estados = 3;
								}
							}
							break;

						case 3:
							if (operacao == 3) {
								estados = 4;
							}
							while (estados == 4) {

								cliente.menuCliente();// Menu cliente
								operacaoCliente = scanner.nextInt();
								if (operacaoCliente == 0) {
									estados = complementar.ConfirmacaoCliente(estados);
								}

								else if (operacaoCliente == 1) {
									cliente.cadastrarCliente(listaDeClientes);
									estados = 4;
								}

								else if (operacaoCliente == 2) {
									cliente.listarClientes(listaDeClientes);
									estados = 4;
								}

								else if (operacaoCliente == 3) {
									cliente.excluirCliente(listaDeClientes);
									estados = 4;
								}

							}
							break;

						case 4:
							if (operacao == 4) {
								estados = 5;
							}
							while (estados == 5) {

								medicamento.menuMedicamento();// Menu Medicamento
								operacaoMedicamento = scanner.nextInt();
								if (operacaoMedicamento == 0) {
									estados = complementar.ConfirmacaoMedicamento(estados);
								}

								else if (operacaoMedicamento == 1) {
									medicamento.cadastrarMedicamento(listaDeMedicamentos);
									estados = 5;
								}

								else if (operacaoMedicamento == 2) {
									medicamento.listarMedicamentos(listaDeMedicamentos);
									estados = 5;
								}

								else if (operacaoMedicamento == 3) {
									medicamento.excluirMedicamento(listaDeMedicamentos);
									estados = 5;
								}

							}
							break;

						case 5:
							if (operacao == 5) {
								estados = 6;
							}
							while (estados == 6) {
								estados = complementar.menuAjuda(estados);
							}

						}// Saindo do Switch principal
					}// Saindo do if de estados = 1
				}
			}// Estados = 0
		}// Saindo do While de estados != 100 / fim do programa
		System.out.println("Obrigado por usar nosso sistema de gerência!" + " Saindo do programa!");
	}
}
