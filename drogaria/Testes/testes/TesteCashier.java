package testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Cashier;


public class TesteCashier{

	@Before
	public void setUp() throws Exception {
		System.out.println("Inicio.");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Fim.");
	}

	/** Abaixo, testes da classe Cashier.java */
	
	@Test
	public void testGetTipoo(){
		Cashier transacao = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transacao.getTransationType() ,0);
	}
	
	
	@Test
	public void testGetValor (){
		Cashier transacao = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transacao.getTransactionValue(),100, 0.1);
	}
	
	@Test
	public void testGetData (){
		Cashier transacao = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transacao.getTransationDate(),"10/10/2020");
	}
	
	@Test
	public void testGetDescricao (){
		Cashier transacao = new Cashier ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transacao.getDescription(),"transacaoTeste");
	}
}
