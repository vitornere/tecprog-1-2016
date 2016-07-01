package tests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import entities.Cashier;


public class TestCashier{


	@Test
	public void GetTypeTest(){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getType() ,0);
	}
	
	
	@Test
	public void GetValueTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getValue(),100, 0.1);
	}
	

	
	@Test
	public void GetCodeTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getCode(),1);
	}
	
	@Test
	public void GetDescriptionTest (){
		Cashier transition = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transition.getDescription(),"transacaoTeste");
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
