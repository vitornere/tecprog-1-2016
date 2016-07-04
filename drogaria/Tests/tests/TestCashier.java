package tests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import entities.Cashier;


public class TestCashier{


	/**
	 * Test if get Identity valid.
	 */
	@Test
	public void GetPersonIdentityTest(){
		Cashier transition = new Cashier ("personIdentityTest","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getIdentity() ,"personIdentityTest");
	}
	
	/**
	 * Test if get CPF valid.
	 */
	@Test
	public void GetCpfPersonTest(){
		Cashier transition = new Cashier ("personIdentityTest","cpfPerson", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getCpfPerson() ,"cpfPerson");
	}
	
	/**
	 * Test if get CPF digit valid.
	 */
	@Test
	public void GetCpfDigitPersonTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getDigitCpfPerson() ,50);
	}
	
	/**
	 * Test if get Name valid.
	 */
	@Test
	public void GetPersonNameTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getName() ,"personName");
	}
	
	/**
	 * Test if get past name valid.
	 */
	@Test
	public void GetPersonPastNameTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getPastName() ,"personPastNameTest");
	}
	
	/**
	 * Test if get address valid.
	 */
	@Test
	public void GetPersonAddressTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "personAddress", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getAddress() ,"personAddress");
	}
		
	/**
	 * Test if get phone valid.
	 */
	@Test
	public void GetPersonPhoneTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "personAddress", "personPhone", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getPhone() ,"personPhone");
	}
	
	/**
	 * Test if get type valid.
	 */
	@Test
	public void GetTypeTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getType() ,0);
	}
	
	/**
	 * Test if get value valid.
	 */
	@Test
	public void GetValueTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getValue(),100, 0.1);
	}
	
	/**
	 * Test if get transition date valid.
	 */
	@Test
	public void GetTrasitionDateTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getDate(),"10/10/2020");
	}
	
	/**
	 * Test if get description valid.
	 */
	@Test
	public void GetDescriptionTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getDescription(),"transacaoTeste");
	}
		
	/**
	 * Test if get code valid.
	 */
	@Test
	public void GetCodeTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getCode(),1);
	}
	
	/**
	 * Test if scanner tha cashier.
	 */
	@Test
	public void ScannerTest(){
		Cashier cashier = new Cashier();
		Scanner scanner = new Scanner(System.in);
		cashier.setScanner(scanner);
		assertEquals(scanner,cashier.getScanner());
	}
	
	/**
	 * Test if delete the cashier.
	 */
	@Test
	public void codeExclusionTest(){
		Cashier cashier = new Cashier();
		cashier.setDeleteCode(1);
		assertEquals(1, cashier.getDeleteCode());
	}
	
	/**
	 * Test if confirm the delete client.
	 */
	@Test
	public void confirmacaoExclusaoClientTest(){
		Cashier cashier = new Cashier();
		cashier.setConfirmationExclusionCashier(0);
		assertEquals(0, cashier.getConfirmationExclusionCashier());
	}
	
	/**
	 * Test if the current balance is valid.
	 */
	@Test
	public void CurrentBalanceTest(){
		Cashier cashier = new Cashier();
		cashier.setCurrentBalance(100);
		assertEquals(100, cashier.getCurrentBalance(), 0.1);
	}
	
	/**
	 * Test if has deposited the current balance.
	 */
	@Test
	public void depositTest(){
		Cashier cashier = new Cashier();
		cashier.setCurrentBalance(100);
		cashier.deposit(20);
		assertEquals(120, cashier.getCurrentBalance(), 0.1);
	}
	
}
