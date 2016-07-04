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
	
	/**
	 * Test if get Identity valid.
	 */
	@Test
	public void testRgPerson() {
		Clerk funcionarioDrogaria = new Clerk("RgPerson", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getIdentity(), "RgPerson");
	}
	
	/**
	 * Test if get CPF valid.
	 */
	@Test
	public void testSetCpfPerson() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "cpfPerson", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getCpfPerson(), "cpfPerson");
	}
	
	/**
	 * Test if get CPF digit valid.
	 */
	@Test
	public void testSetDigitoCpf() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getDigitCpfPerson(), 50);
	}
	
	/**
	 * Test if get name valid.
	 */
	@Test
	public void GetPersonNameTest(){
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "nameTest", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getName(), "nameTest");
	}
	
	/**
	 * Test if get past name valid.
	 */
	@Test
	public void GetPersonPastNameTest(){
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "nameTest", "pastNameTest", "Teste 5",
				"Teste 6", 1234, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getPastName(), "pastNameTest");
	}
	
	/**
	 * Test if get address valid.
	 */
	@Test
	public void GetPersonAddressTest(){
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "nameTest", "pastNameTest", "address",
				"Teste 6", 1234, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getAddress(), "address");
	}
	
	/**
	 * Test if get phone valid.
	 */
	@Test
	public void GetPersonPhoneTest(){
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "nameTest", "pastNameTest", "address",
				"phone", 1234, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getPhone(), "phone");
	}
	
	/**
	 * Test if get password valid.
	 */
	@Test
	public void testGetSenha() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 15000, 5678, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getPassword(), 15000);
	}
	
	/**
	 * Test if get drugstore password valid.
	 */
	@Test
	public void testSetSenhaFarmaciaPopular() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 2432, 1, 2, 0);
		assertEquals(funcionarioDrogaria.getSenhaFarmaciaPopular(), 2432);
	}
	
	/**
	 * Test if get code valid.
	 */
	@Test
	public void testGetCodigo() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 20, 2, 0);
		assertEquals(funcionarioDrogaria.getIdFuncionary(), 20);
	}

	/**
	 * Test if get comission factor valid.
	 */
	@Test
	public void testGetFatorComissao() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 0);
		assertEquals(funcionarioDrogaria.getCommissionFactor(), 15);
	}

	/**
	 * Test if get work hours valid.
	 */
	@Test
	public void testGetWorkHours() {
		Clerk funcionarioDrogaria = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 0);
		assertEquals(funcionarioDrogaria.getHoras(), 0);
	}
	
	/**
	 * Test if get cashier.
	 */
	@Test
	public void testGetCashier() {
		Cashier caixa = new Cashier();
		balconista.setCaixa(caixa);
		assertEquals(caixa, balconista.getCaixa());
	}

	/**
	 * Test if get medicament.
	 */
	@Test
	public void testSetMedicamento() {
		Medicament[] medicamento = { new Medicament(), new Medicament() };

		balconista.setMedicaments(medicamento);
		assertNotNull(balconista.getMedicaments());
	}

	/**
	 * Test if verify medicament.
	 */
	@Test
	public void testVerifMedicamento() {
		Medicament[] medicamento = { new Medicament(), new Medicament() };

		balconista.verificarMedicament(medicamento);
		assertNotNull(balconista.getMedicaments());
	}

	/**
	 * Test if get verify other medicament.
	 */
	@Test
	public void testVerifMedicamento2() {
		Medicament[] medicamento = { new Medicament(), new Medicament() };

		balconista.verificarMedicament(medicamento);
		assertNotNull(balconista.getMedicaments());
	}

	/**
	 * Test if get clerk CPF digit valid.
	 */
	@Test
	public void testDgCadastra() {
		Clerk balconista2 = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 0);
		balconista2.registerClerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6");
		assertEquals(balconista2.getDigitCpfPerson(), 50);
	}

	/**
	 * Test if get pay of clerk.
	 */
	@Test
	public void testSalario() {
		Clerk balconista = new Clerk("Teste 1", "Teste 2", 50, "Teste 3", "Teste 4", "Teste 5",
				"Teste 6", 1234, 5678, 1, 15, 5);
		assertEquals(675.0, balconista.calculateSalary(), 0.1);
	}

	/**
	 * Test if confirm pay.
	 */
	@Test
	public void testConfirmacaoPagamento() {
		balconista.setPaymentConfirmation(1);
		assertEquals(1, balconista.paymentConfirmation(), 0.1);

	}
	
	/**
	 * Test if get password clerk.
	 */
	@Test
	public void testSetSenha() {
		balconista.setSenha(1234);
		assertEquals(1234, balconista.getPassword());

	}

	/**
	 * Test if get password farm pop.
	 */
	@Test
	public void testSetSenhaFarmPop() {
		balconista.setSenhaFarmaciaPopular(1234);
		assertEquals(1234, balconista.getSenhaFarmaciaPopular());

	}

	/**
	 * Test if get code valid.
	 */
	@Test
	public void testSetCodigo() {
		balconista.setCodigo(1);
		assertEquals(1, balconista.getIdFuncionary());

	}

	/**
	 * Test if get comission factor.
	 */
	@Test
	public void testSetFatorComissao() {
		balconista.setFatorComissao(10);
		assertEquals(10, balconista.getCommissionFactor());

	}

	/**
	 * Test if get quantity.
	 */
	@Test
	public void testSetQuantidade() {
		balconista.setQuantidade(10);
		assertEquals(10, balconista.getQuantidade());

	}

	/**
	 * Test status clerk.
	 */
	@Test
	public void getTestBoolean() {
		balconista.setStatusClerk(true);
		assertTrue(balconista.isStatusClerk());
	}

	/**
	 * Test if get hours.
	 */
	@Test
	public void Horas() {
		balconista.setHoras(50);
		assertEquals(50, balconista.getHoras());
	}

	/**
	 * Test if get operation.
	 */
	@Test
	public void Operacao() {
		balconista.setOperacao(5);
		assertEquals(5, balconista.getOperacao());
	}

	/**
	 * Test if get operation clerk.
	 */
	@Test
	public void OperacaoClerk() {
		balconista.setOperacaoBalconista(1);
		assertEquals(1, balconista.getOperacaoBalconista());
	}

	/**
	 * Test if get repeat clerk.
	 */
	@Test
	public void repeteClerk() {
		balconista.setRepeteBalconista(1);
		assertEquals(1, balconista.getRepeteBalconista());
	}

	/**
	 * Test if get repeat insert clerk.
	 */
	@Test
	public void repeteCadastroClerk() {
		balconista.setRepeteCadastroBalconista(1);
		assertEquals(1, balconista.getRepeteCadastroBalconista());
	}

	/**
	 * Test if get delete.
	 */
	@Test
	public void codigoExclusao() {
		balconista.setCodigoExclusao(1);
		assertEquals(1, balconista.getCodigoExclusao());
	}

	/**
	 * Test if get confirm delete.
	 */
	@Test
	public void confirmacaoExclusaoClerk() {
		balconista.setConfirmacaoExclusaoBalconista(0);
		assertEquals(0, balconista.getConfirmacaoExclusaoBalconista());
	}

	/**
	 * Test if get cpf.
	 */
	@Test
	public void GetCpf() {
		balconista.setGetCpf(012345);
		assertEquals(012345, balconista.getGetCpf());
	}

	/**
	 * Test clients.
	 */
	@Test
	public void testClientes() {
		Client[] cliente = { new Client(), new Client() };
		balconista.setClientes(cliente);
		assertEquals(cliente, balconista.getClientes());
	}

	/**
	 * Test clerks.
	 */
	@Test
	public void testClerks() {
		Clerk[] balconista2 = { new Clerk(), new Clerk() };
		balconista.setBalconista(balconista2);
		assertEquals(balconista2, balconista.getBalconista());
	}

	/**
	 * Test scanner.
	 */
	@Test
	public void testScanner() {
		Scanner scanner = new Scanner(System.in);
		balconista.setScanner(scanner);
		assertEquals(scanner, balconista.getScanner());
	}

	/**
	 * Test scanner.
	 */
	@Test
	public void testScanner1() {
		Scanner scanner1 = new Scanner(System.in);
		balconista.setScanner1(scanner1);
		assertEquals(scanner1, balconista.getScanner1());
	}
}
