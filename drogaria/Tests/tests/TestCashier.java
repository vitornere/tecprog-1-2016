package tests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import entities.Cashier;


public class TestCashier{


	@Test
	public void GetPersonIdentityTest(){
		Cashier transition = new Cashier ("personIdentityTest","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getIdentity() ,"personIdentityTest");
	}
	
	@Test
	public void GetPersonIdentityTestInvalid(){
		Cashier transition = new Cashier ("personIdentityTest","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getIdentity() ,"personIdentityTestInvalid");
	}
	
	@Test
	public void GetCpfPersonTest(){
		Cashier transition = new Cashier ("personIdentityTest","cpfPerson", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getCpfPerson() ,"cpfPerson");
	}
	
	@Test
	public void GetCpfPersonTestInvalid(){
		Cashier transition = new Cashier ("personIdentityTest","cpfPerson", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getCpfPerson() ,"cpfPersonInvalid");
	}
	
	@Test
	public void GetCpfDigitPersonTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getDigitCpfPerson() ,50);
	}
	
	@Test
	public void GetCpfDigitPersonTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getDigitCpfPerson() ,51);
	}
	
	@Test
	public void GetPersonNameTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getName() ,"personName");
	}
	
	@Test
	public void GetPersonNameTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getName() ,"personNameInvalid");
	}
	
	@Test
	public void GetPersonPastNameTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getPastName() ,"personPastNameTest");
	}
	
	@Test
	public void GetPersonPastNameTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getPastName() ,"personPastNameTestInvalid");
	}
	
	@Test
	public void GetPersonAddressTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "personAddress", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getAddress() ,"personAddress");
	}
	
	@Test
	public void GetPersonAddressTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "personAddress", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getAddress() ,"personAddressInvalid");
	}
	
	@Test
	public void GetPersonPhoneTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "personAddress", "personPhone", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getPhone() ,"personPhone");
	}
	
	@Test
	public void GetPersonPhoneTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "personName", "personPastNameTest", "personAddress", "personPhone", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getPhone() ,"personPhoneInvalid");
	}
	
	@Test
	public void GetTypeTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getType() ,0);
	}
	
	@Test
	public void GetTypeTestInvalid(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getType() ,1);
	}
	
	@Test
	public void GetValueTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getValue(),100, 0.1);
	}
	
	@Test
	public void GetValueTestInvalid (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getValue(),200, 0.1);
	}
	
	@Test
	public void GetTrasitionDateTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getDate(),"10/10/2020");
	}
	
	@Test
	public void GetTrasitionDateTestInvalid (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getDate(),"10/10/2021");
	}
	
	@Test
	public void GetDescriptionTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getDescription(),"transacaoTeste");
	}
	
	@Test
	public void GetDescriptionTestInvalid (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getDescription(),"transacaoTesteInvalid");
	}
	
	@Test
	public void GetCodeTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getCode(),1);
	}
	
	@Test
	public void GetCodeTestInvalid (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertNotEquals(transition.getCode(),2);
	}
	
	@Test
	public void ScannerTest(){
		Cashier cashier = new Cashier();
		Scanner scanner = new Scanner(System.in);
		cashier.setScanner(scanner);
		assertEquals(scanner,cashier.getScanner());
	}
	
	@Test
	public void codeExclusionTest(){
		Cashier cashier = new Cashier();
		cashier.setDeleteCode(1);
		assertEquals(1, cashier.getDeleteCode());
	}
	
	@Test
	public void confirmacaoExclusaoClientTest(){
		Cashier cashier = new Cashier();
		cashier.setConfirmationExclusionCashier(0);
		assertEquals(0, cashier.getConfirmationExclusionCashier());
	}
	
	@Test
	public void CurrentBalanceTest(){
		Cashier cashier = new Cashier();
		cashier.setCurrentBalance(100);
		assertEquals(100, cashier.getCurrentBalance(), 0.1);
	}
	
	@Test
	public void depositTest(){
		Cashier cashier = new Cashier();
		cashier.setCurrentBalance(100);
		cashier.deposit(20);
		assertEquals(120, cashier.getCurrentBalance(), 0.1);
	}
	
}
