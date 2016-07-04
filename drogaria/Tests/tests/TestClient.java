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
	
	/**
	 * Test if get identity valid.
	 */
	@Test
	public void testPersonIdentity() {
		Client newClient = new Client("personIdentity","Test 2", 50, "Test 3", "Test 4", "Test 5", "Test 6");
		assertEquals(newClient.getIdentity(),"personIdentity");
		
	}
	
	/**
	 * Test if get cpf valid.
	 */
	@Test
	public void testPersonCpf() {
		Client newClient = new Client("personIdentity","personCpf", 50, "Test 3", "Test 4", "Test 5", "Test 6");
		assertEquals(newClient.getCpfPerson(),"personCpf");
		
	}
	
	/**
	 * Test if get cpf digit valid.
	 */
	@Test
	public void testSetDigitoCpf() {
		Client newClient = new Client("Test 1","Test 2", 50, "Test 3", "Test 4", "Test 5", "Test 6");
		assertEquals(newClient.getDigitCpfPerson(),50);
		
	}
	
	/**
	 * Test if get person name valid.
	 */
	@Test
	public void testSetPersonName() {
		Client newClient = new Client("Test 1","Test 2", 50, "personName", "Test 4", "Test 5", "Test 6");
		assertEquals(newClient.getName(),"personName");
		
	}
	
	/**
	 * Test if get past name valid.
	 */
	@Test
	public void testSetPastName() {
		Client newClient = new Client("Test 1","Test 2", 50, "personName", "pastName", "Test 5", "Test 6");
		assertEquals(newClient.getPastName(),"pastName");
		
	}

	/**
	 * Test if get address valid.
	 */
	@Test
	public void testSetAddress() {
		Client newClient = new Client("Test 1","Test 2", 50, "personName", "pastName", "address", "Test 6");
		assertEquals(newClient.getAddress(),"address");
		
	}
	
	/**
	 * Test if get phone valid.
	 */
	@Test
	public void testSetPhone() {
		Client newClient = new Client("Test 1","Test 2", 50, "personName", "pastName", "address", "phone");
		assertEquals(newClient.getPhone(),"phone");
		
	}
	
	/**
	 * Test if get email valid.
	 */
	@Test
	public void testEmail(){
		Client newClient = new Client("Test 1","Test 2", 50, "Test 3", "Test 4", "Test 5", "Test 6","email@email.com");
		assertEquals(newClient.getEmail(), "email@email.com");
	}
	
	/**
	 * Test if get client.
	 */
	@Test
	public void Clerk() {
		Client client = new Client();
		Clerk[] clerk = {new Clerk() , new Clerk()};
		client.setClerk(clerk);
		assertEquals(clerk,client.getClerk());
	}
	
	/**
	 * Test if get cashier.
	 */
	@Test
	public void Caixa() {
		Client client = new Client();
		Cashier[] clerk = {new Cashier() , new Cashier()};
		client.setCashier(clerk);
		assertEquals(clerk,client.getCashier());
	}
	
	/**
	 * Test if get client.
	 */
	@Test
	public void Client() {
		Client client1 = new Client();
		Client[] client2 = {new Client(),new Client()};
		client1.setClient(client2);
		assertEquals(client2,client1.getClient());
	}
	
	/**
	 * Test if scanner client.
	 */
	@Test
	public void testScanner(){
		Client cliente = new Client();
		Scanner scanner = new Scanner(System.in);
		cliente.setScanner(scanner);
		assertEquals(scanner,cliente.getScanner());
	}
	
	/**
	 * Test if get delete code.
	 */
	@Test
	public void codigoExclusao(){
		Client client = new Client();
		client.setCodeExclusion(1);
		assertEquals(1, client.getDeleteCode());
	}
	
	/**
	 * Test if get confirm delete client.
	 */
	@Test
	public void confirmacaoExclusaoClient(){
		Client client = new Client();
		client.setConfirmationClientExclusion(0);
		assertEquals(0, client.getConfirmationClientExclusion());
	}
}
	
