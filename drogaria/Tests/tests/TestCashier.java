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
	 * Test if get Identity invalid.
	 */
	@Test
	public void GetPersonIdentityTestInvalid(){
		Cashier transition = new Cashier ("personIdentityTest","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getIdentity() ,"personIdentityTestInvalid");
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
	 * Test if get CPF invalid.
	 */
	@Test
	public void GetCpfPersonTestInvalid(){
		Cashier transition = new Cashier ("personIdentityTest","cpfPerson", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getCpfPerson() ,"cpfPersonInvalid");
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
	 * Test if get CPF digit invalid.
	 */
	@Test
	public void GetCpfDigitPersonTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getDigitCpfPerson() ,51);
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
	 * Test if get name invalid.
	 */
	@Test
	public void GetPersonNameTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getName() ,"personNameInvalid");
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
	 * Test if get past name invalid.
	 */
	@Test
	public void GetPersonPastNameTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getPastName() ,"personPastNameTestInvalid");
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
	 * Test if get address invalid.
	 */
	@Test
	public void GetPersonAddressTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "personAddress", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getAddress() ,"personAddressInvalid");
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
	 * Test if get phone invalid.
	 */
	@Test
	public void GetPersonPhoneTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "personAddress", "personPhone", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getPhone() ,"personPhoneInvalid");
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
	 * Test if get type invalid.
	 */
	@Test
	public void GetTypeTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getType() ,1);
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
	 * Test if get value invalid.
	 */
	@Test
	public void GetValueTestInvalid (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getValue(),200, 0.1);
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
	 * Test if get transition date invalid.
	 */
	@Test
	public void GetTrasitionDateTestInvalid (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getDate(),"10/10/2021");
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
	 * Test if get description invalid.
	 */
	@Test
	public void GetDescriptionTestInvalid (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getDescription(),"transacaoTesteInvalid");
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
	 * Test if get code invalid.
	 */
	@Test
	public void GetCodeTestInvalid (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getCode(),2);
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
