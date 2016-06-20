package tests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Clerk;
import entities.Cashier;
import entities.Client;
import entities.Medicament;

public class TestClerk {
	Clerk balconista;

	@Before
	public void setUp() throws Exception {
		balconista = new Clerk();
		System.out.println("Comecando o teste!");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("OK! Finalizando o teste!");
	}

	public void testConstrutor() {
		Clerk balconistaVazio = new Clerk();
		assertNotNull(balconistaVazio);
	}

	// Teste para confirma��o do digito do CPF.
	@Test
	public void testSetDigitoCpf() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getDigitCpfPerson(), 50);
	}

	// Teste para confirma��o da senha
	@Test
	public void testGetSenha() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 15000, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getPassword(), 15000);
	}

	// Teste para confirma��o da senha da farm�cia popular
	@Test
	public void testSetSenhaFarmaciaPopular() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 2432, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getSenhaFarmaciaPopular(), 2432);
	}

	// Teste para confirma��o do c�digo do funcion�rio
	@Test
	public void testGetCodigo() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 20, 2, 0);
		assertEquals(funcionarioDrogaria.getIdFuncionary(), 20);
	}

	// Teste para confirma��o do fator de comiss�o do funcion�rio
	@Test
	public void testGetFatorComissao() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 0);
		assertEquals(funcionarioDrogaria.getCommissionFactor(), 15);
	}

	@Test
	public void testGetCashier() {
		Cashier caixa = new Cashier();
		balconista.setCaixa(caixa);
		assertEquals(caixa, balconista.getCaixa());
	}

	@Test
	public void testSetMedicamento() {
		Medicament[] medicamento = { new Medicament(), new Medicament() };

		balconista.setMedicaments(medicamento);
		assertNotNull(balconista.getMedicaments());
	}

	@Test
	public void testVerifMedicamento() {
		Medicament[] medicamento = { new Medicament(), new Medicament() };

		balconista.verificarMedicament(medicamento);
		assertNotNull(balconista.getMedicaments());
	}

	@Test
	public void testVerifMedicamento2() {
		Medicament[] medicamento = { new Medicament(), new Medicament() };

		balconista.verificarMedicament(medicamento);
		assertNotNull(balconista.getMedicaments());
	}

	@Test
	public void testDgCadastra() {
		Clerk balconista2 = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 0);
		balconista2.registerClerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6");
		assertEquals(balconista2.getDigitCpfPerson(), 50);
	}

	@Test
	public void testSalario() {
		Clerk balconista = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 5);
		assertEquals(675.0, balconista.calculateSalary(), 0.1);
	}

	@Test
	public void testConfirmacaoPagamento() {
		balconista.setPaymentConfirmation(1);
		assertEquals(1, balconista.paymentConfirmation(), 0.1);

	}

	@Test
	public void testConfirmacaoPagamentoElse() {
		balconista.setPaymentConfirmation(0);
		assertEquals(0, balconista.paymentConfirmation(), 0.1);

	}

	@Test
	public void testSetSenha() {
		balconista.setSenha(1234);
		assertEquals(1234, balconista.getPassword());

	}

	@Test
	public void testSetSenhaFarmPop() {
		balconista.setSenhaFarmaciaPopular(1234);
		assertEquals(1234, balconista.getSenhaFarmaciaPopular());

	}

	@Test
	public void testSetCodigo() {
		balconista.setCodigo(1);
		assertEquals(1, balconista.getIdFuncionary());

	}

	@Test
	public void testSetFatorComissao() {
		balconista.setFatorComissao(10);
		assertEquals(10, balconista.getCommissionFactor());

	}

	@Test
	public void testSetQuantidade() {
		balconista.setQuantidade(10);
		assertEquals(10, balconista.getQuantidade());

	}

	@Test
	public void getTestBoolean() {
		balconista.setStatusClerk(true);
		assertTrue(balconista.isStatusClerk());
	}

	@Test
	public void Horas() {
		balconista.setHoras(50);
		assertEquals(50, balconista.getHoras());
	}

	@Test
	public void Operacao() {
		balconista.setOperacao(5);
		assertEquals(5, balconista.getOperacao());
	}

	@Test
	public void OperacaoClerk() {
		balconista.setOperacaoBalconista(1);
		assertEquals(1, balconista.getOperacaoBalconista());
	}

	@Test
	public void repeteClerk() {
		balconista.setRepeteBalconista(1);
		assertEquals(1, balconista.getRepeteBalconista());
	}

	@Test
	public void repeteCadastroClerk() {
		balconista.setRepeteCadastroBalconista(1);
		assertEquals(1, balconista.getRepeteCadastroBalconista());
	}

	@Test
	public void codigoExclusao() {
		balconista.setCodigoExclusao(1);
		assertEquals(1, balconista.getCodigoExclusao());
	}

	@Test
	public void confirmacaoExclusaoClerk() {
		balconista.setConfirmacaoExclusaoBalconista(0);
		assertEquals(0, balconista.getConfirmacaoExclusaoBalconista());
	}

	@Test
	public void GetCpf() {
		balconista.setGetCpf(012345);
		assertEquals(012345, balconista.getGetCpf());
	}

	@Test
	public void testClientes() {
		Client[] cliente = { new Client(), new Client() };
		balconista.setClientes(cliente);
		assertEquals(cliente, balconista.getClientes());
	}

	@Test
	public void testClerks() {
		Clerk[] balconista2 = { new Clerk(), new Clerk() };
		balconista.setBalconista(balconista2);
		assertEquals(balconista2, balconista.getBalconista());
	}

	@Test
	public void testScanner() {
		Scanner scanner = new Scanner(System.in);
		balconista.setScanner(scanner);
		assertEquals(scanner, balconista.getScanner());
	}

	@Test
	public void testScanner1() {
		Scanner scanner1 = new Scanner(System.in);
		balconista.setScanner1(scanner1);
		assertEquals(scanner1, balconista.getScanner1());
	}
}
