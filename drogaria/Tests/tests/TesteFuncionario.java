package tests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Balconista;
import entities.Caixa;
import entities.Cliente;
import entities.Medicament;

public class TesteFuncionario {
	Balconista balconista;

	@Before
	public void setUp() throws Exception {
		balconista = new Balconista();
		System.out.println("Comecando o teste!");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("OK! Finalizando o teste!");
	}

	public void testConstrutor() {
		Balconista balconistaVazio = new Balconista();
		assertNotNull(balconistaVazio);
	}

	// Teste para confirma��o do digito do CPF.
	@Test
	public void testSetDigitoCpf() {
		Balconista funcionarioDrogaria = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getDigitCpfPerson(), 50);
	}

	// Teste para confirma��o da senha
	@Test
	public void testGetSenha() {
		Balconista funcionarioDrogaria = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 15000, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getPassword(), 15000);
	}

	// Teste para confirma��o da senha da farm�cia popular
	@Test
	public void testSetSenhaFarmaciaPopular() {
		Balconista funcionarioDrogaria = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 2432, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getSenhaFarmaciaPopular(), 2432);
	}

	// Teste para confirma��o do c�digo do funcion�rio
	@Test
	public void testGetCodigo() {
		Balconista funcionarioDrogaria = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 20, 2, 0);
		assertEquals(funcionarioDrogaria.getIdFuncionary(), 20);
	}

	// Teste para confirma��o do fator de comiss�o do funcion�rio
	@Test
	public void testGetFatorComissao() {
		Balconista funcionarioDrogaria = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 0);
		assertEquals(funcionarioDrogaria.getCommissionFactor(), 15);
	}

	@Test
	public void testGetCaixa() {
		Caixa caixa = new Caixa();
		balconista.setCaixa(caixa);
		assertEquals(caixa, balconista.getCaixa());
	}

	@Test
	public void testSetMedicamento() {
		Medicament[] medicamento = { new Medicament(), new Medicament() };

		balconista.setMedicaments(medicamento);
		assertNotNull(balconista.getMedicamentos());
	}

	@Test
	public void testSetMedicamentoNull() {
<<<<<<< HEAD
		Medicamento[] medicamento = {};
		balconista.setMedicaments(medicamento);
=======
		Medicament[] medicamento = {};
		balconista.setMedicamentos(medicamento);
>>>>>>> devel
		assertNull(balconista.getMedicamentos());

	}

	@Test
	public void testVerifMedicamento() {
		Medicament[] medicamento = { new Medicament(), new Medicament() };

		balconista.verificarMedicamento(medicamento);
		assertNotNull(balconista.getMedicamentos());
	}

	@Test
	public void testVerifMedicamento2() {
		Medicament[] medicamento = { new Medicament(), new Medicament() };

		balconista.verificarMedicamento(medicamento);
		assertNotNull(balconista.getMedicamentos());
	}

	@Test
	public void testDgCadastra() {
		Balconista balconista2 = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 0);
		balconista2.registerClerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6");
		assertEquals(balconista2.getDigitCpfPerson(), 50);
	}

	@Test
	public void testSalario() {
		Balconista balconista = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 5);
		assertEquals(75, balconista.calculateSalary());
	}

	@Test
	public void testConfirmacaoPagamento() {
		balconista.setConfirmacaoPagamento(1);
		assertEquals(1, balconista.paymentConfirmation());

	}

	@Test
	public void testConfirmacaoPagamentoElse() {
		balconista.setConfirmacaoPagamento(0);
		assertEquals(0, balconista.paymentConfirmation());

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
	public void OperacaoBalconista() {
		balconista.setOperacaoBalconista(1);
		assertEquals(1, balconista.getOperacaoBalconista());
	}

	@Test
	public void repeteBalconista() {
		balconista.setRepeteBalconista(1);
		assertEquals(1, balconista.getRepeteBalconista());
	}

	@Test
	public void repeteCadastroBalconista() {
		balconista.setRepeteCadastroBalconista(1);
		assertEquals(1, balconista.getRepeteCadastroBalconista());
	}

	@Test
	public void codigoExclusao() {
		balconista.setCodigoExclusao(1);
		assertEquals(1, balconista.getCodigoExclusao());
	}

	@Test
	public void confirmacaoExclusaoBalconista() {
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
		Cliente[] cliente = { new Cliente(), new Cliente() };
		balconista.setClientes(cliente);
		assertEquals(cliente, balconista.getClientes());
	}

	@Test
	public void testBalconistas() {
		Balconista[] balconista2 = { new Balconista(), new Balconista() };
		balconista.setBalconista(balconista2);
		assertEquals(balconista2, balconista.getClerk());
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
