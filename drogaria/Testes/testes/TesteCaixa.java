package testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entidades.Caixa;


public class TesteCaixa{

	@Before
	public void setUp() throws Exception {
		System.out.println("Inicio.");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Fim.");
	}

	/** Abaixo, testes da classe Caixa.java */
	
	@Test
	public void testGetTipoo(){
		Caixa transacao = new Caixa ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transacao.getTipo(),0);
	}
	
	
	@Test
	public void testGetValor (){
		Caixa transacao = new Caixa ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transacao.getValor(),100);
	}
	
	@Test
	public void testGetData (){
		Caixa transacao = new Caixa ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transacao.getData(),"10/10/2020");
	}
	
	@Test
	public void testGetDescricao (){
		Caixa transacao = new Caixa ("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6", 0, 100, "10/10/2020", "transacaoTeste",1);
		assertEquals(transacao.getDescricao(),"transacaoTeste");
	}
}
