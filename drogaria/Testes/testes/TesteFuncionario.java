package testes;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entidades.Balconista;
import entidades.Caixa;
import entidades.Cliente;
import entidades.Medicamento;

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

	// Teste para confirmação do digito do CPF.
	@Test
	public void testSetDigitoCpf() {
		Balconista funcionarioDrogaria = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getDigitoCpf(), 50);
	}

	// Teste para confirmação da senha
	@Test
	public void testGetSenha() {
		Balconista funcionarioDrogaria = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 15000, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getSenha(), 15000);
	}

	// Teste para confirmação da senha da farmácia popular
	@Test
	public void testSetSenhaFarmaciaPopular() {
		Balconista funcionarioDrogaria = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 2432, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getSenhaFarmaciaPopular(), 2432);
	}

	// Teste para confirmação do código do funcionário
	@Test
	public void testGetCodigo() {
		Balconista funcionarioDrogaria = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 20, 2, 0);
		assertEquals(funcionarioDrogaria.getCodigo(), 20);
	}

	// Teste para confirmação do fator de comissão do funcionário
	@Test
	public void testGetFatorComissao() {
		Balconista funcionarioDrogaria = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 0);
		assertEquals(funcionarioDrogaria.getFatorComissao(), 15);
	}

	@Test
	public void testGetCaixa() {
		Caixa caixa = new Caixa();
		balconista.setCaixa(caixa);
		assertEquals(caixa, balconista.getCaixa());
	}

	@Test
	public void testSetMedicamento() {
		Medicamento[] medicamento = { new Medicamento(), new Medicamento() };

		balconista.setMedicamentos(medicamento);
		assertNotNull(balconista.getMedicamentos());
	}

	@Test
	public void testSetMedicamentoNull() {
		Medicamento[] medicamento = {};
		balconista.setMedicamentos(medicamento);
		assertNull(balconista.getMedicamentos());

	}

	@Test
	public void testVerifMedicamento() {
		Medicamento[] medicamento = { new Medicamento(), new Medicamento() };

		balconista.verificarMedicamento(medicamento);
		assertNotNull(balconista.getMedicamentos());
	}

	@Test
	public void testVerifMedicamento2() {
		Medicamento[] medicamento = { new Medicamento(), new Medicamento() };

		balconista.verificarMedicamento(medicamento);
		assertNotNull(balconista.getMedicamentos());
	}

	@Test
	public void testDgCadastra() {
		Balconista balconista2 = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 0);
		balconista2.cadastraBalconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6");
		assertEquals(balconista2.getDigitoCpf(), 50);
	}

	@Test
	public void testSalario() {
		Balconista balconista = new Balconista("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 5);
		assertEquals(75, balconista.calcularSalario());
	}

	@Test
	public void testConfirmacaoPagamento() {
		balconista.setConfirmacaoPagamento(1);
		assertEquals(1, balconista.confirmacaoPagamento());

	}

	@Test
	public void testConfirmacaoPagamentoElse() {
		balconista.setConfirmacaoPagamento(0);
		assertEquals(0, balconista.confirmacaoPagamento());

	}

	@Test
	public void testSetSenha() {
		balconista.setSenha(1234);
		assertEquals(1234, balconista.getSenha());

	}

	@Test
	public void testSetSenhaFarmPop() {
		balconista.setSenhaFarmaciaPopular(1234);
		assertEquals(1234, balconista.getSenhaFarmaciaPopular());

	}

	@Test
	public void testSetCodigo() {
		balconista.setCodigo(1);
		assertEquals(1, balconista.getCodigo());

	}

	@Test
	public void testSetFatorComissao() {
		balconista.setFatorComissao(10);
		assertEquals(10, balconista.getFatorComissao());

	}

	@Test
	public void testSetQuantidade() {
		balconista.setQuantidade(10);
		assertEquals(10, balconista.getQuantidade());

	}

	@Test
	public void getTestBoolean() {
		balconista.setStatusBalconista(true);
		assertTrue(balconista.isStatusBalconista());
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
