/**
 * 
 */
package testes;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entidades.Caixa;
import entidades.Cliente;
import entidades.Balconista;


public class TesteCliente {

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
		Cliente novoCliente = new Cliente("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6");
		assertEquals(novoCliente.getDigitoCpf(),50);
		
	}

	@Test
	public void testEmail(){
		Cliente novoCliente = new Cliente("Teste 1","Teste 2", 50, "Teste 3", "Teste 4", "Teste 5", "Teste 6","email@email.com");
		assertEquals("email@email.com", novoCliente.getEmail());
	}
	
	@Test
	public void setEmail(){
		Cliente cliente = new Cliente();
		cliente.setEmail("emailSet@email.com");
		assertEquals("emailSet@email.com", cliente.getEmail());
	}
	
	@Test
	public void Balconista() {
		Cliente cliente = new Cliente();
		Balconista[] balconista = {new Balconista() , new Balconista()};
		cliente.setBalconistas(balconista);
		assertEquals(balconista,cliente.getBalconistas());
	}
	
	@Test
	public void Caixa() {
		Cliente cliente = new Cliente();
		Caixa[] caixa = {new Caixa() , new Caixa()};
		cliente.setCaixas(caixa);
		assertEquals(caixa,cliente.getCaixas());
	}
	
	@Test
	public void Cliente() {
		Cliente cliente = new Cliente();
		Cliente[] cliente2 = {new Cliente(),new Cliente()};
		cliente.setCliente(cliente2);
		assertEquals(cliente2,cliente.getCliente());
	}
	
	@Test
	public void testScanner(){
		Cliente cliente = new Cliente();
		Scanner scanner = new Scanner(System.in);
		cliente.setScanner(scanner);
		assertEquals(scanner,cliente.getScanner());
	}
	
	@Test
	public void codigoExclusao(){
		Cliente cliente = new Cliente();
		cliente.setCodigoExclusao(1);
		assertEquals(1, cliente.getCodigoExclusao());
	}
	
	@Test
	public void confirmacaoExclusaoCliente(){
		Cliente cliente = new Cliente();
		cliente.setConfirmacaoExclusaoCliente(0);
		assertEquals(0, cliente.getConfirmacaoExclusaoCliente());
	}
}
	
