/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import entities.Clerk;
import entities.Cashier;
import entities.Client;


public class TestClient {
	

	@Test
	public void testSetDigitoCpf() {
		Client newClient = new Client("Test 1","Test 2", 50, "Test 3", "Test 4", "Test 5", "Test 6");
		assertEquals(newClient.getDigitCpfPerson(),50);
		
	}

	@Test
	public void testEmail(){
		Client newClient = new Client("Test 1","Test 2", 50, "Test 3", "Test 4", "Test 5", "Test 6","email@email.com");
		assertEquals("email@email.com", newClient.getEmail());
	}
	
	@Test
	public void setEmail(){
		Client client = new Client();
		client.setEmail("emailSet@email.com");
		assertEquals("emailSet@email.com", client.getEmail());
	}
	
	@Test
	public void Clerk() {
		Client client = new Client();
		Clerk[] clerk = {new Clerk() , new Clerk()};
		client.setClerk(clerk);
		assertEquals(clerk,client.getClerk());
	}
	
	
	@Test
	public void Caixa() {
		Client client = new Client();
		Cashier[] clerk = {new Cashier() , new Cashier()};
		client.setCashier(clerk);
		assertEquals(clerk,client.getCashier());
	}
	
	
	
	@Test
	public void Client() {
		Client client1 = new Client();
		Client[] client2 = {new Client(),new Client()};
		client1.setClient(client2);
		assertEquals(client2,client1.getClient());
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
		Client client = new Client();
		client.setCodeExclusion(1);
		assertEquals(1, client.getDeleteCode());
	}
	
	@Test
	public void confirmacaoExclusaoClient(){
		Client client = new Client();
		client.setConfirmationClientExclusion(0);
		assertEquals(0, client.getConfirmationClientExclusion());
	}
}
	
