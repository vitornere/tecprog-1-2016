/**
 * 
 */
package testes;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Clerk;
import entities.Cashier;
import entities.Client;


public class TestClient {

	@Before
	public void setUp() throws Exception {
		System.out.println("Comecando o teste!");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Finalizando o teste!");
	}
	
	//Teste de idade.
	@Test
	public void testSetDigitoCpf() {
		Client novoClient = new Client("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6");
		assertEquals(novoClient.getDigitCpfPerson(),50);
		
	}

	@Test
	public void testEmail(){
		Client novoClient = new Client("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6","email@email.com");
		assertEquals("email@email.com", novoClient.getEmail());
	}
	
	@Test
	public void setEmail(){
		Client cliente = new Client();
		cliente.setEmail("emailSet@email.com");
		assertEquals("emailSet@email.com", cliente.getEmail());
	}
	
	@Test
	public void Clerk() {
		Client cliente = new Client();
		Clerk[] balconista = {new Clerk() , new Clerk()};
		cliente.setBalconistas(balconista);
		assertEquals(balconista,cliente.getBalconistas());
	}
	
	@Test
	public void Caixa() {
		Client cliente = new Client();
		Cashier[] caixa = {new Cashier() , new Cashier()};
		cliente.setCaixas(caixa);
		assertEquals(caixa,cliente.getCaixas());
	}
	
	@Test
	public void Client() {
		Client cliente = new Client();
		Client[] cliente2 = {new Client(),new Client()};
		cliente.setCliente(cliente2);
		assertEquals(cliente2,cliente.getCliente());
	}
	
	@Test
	public void testScanner(){
		Client cliente = new Client();
		Scanner scanner = new Scanner(System.in);
		cliente.setScanner(scanner);
		assertEquals(scanner,cliente.getScanner());
	}
	
	@Test
	public void codigoExclusao(){
		Client cliente = new Client();
		cliente.setCodigoExclusao(1);
		assertEquals(1, cliente.getCodigoExclusao());
	}
	
	@Test
	public void confirmacaoExclusaoClient(){
		Client cliente = new Client();
		cliente.setConfirmacaoExclusaoCliente(0);
		assertEquals(0, cliente.getConfirmacaoExclusaoCliente());
	}
}
	
