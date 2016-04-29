package testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.ConsoleMenu;

public class TesteComplementar {

	@Before
	public void setUp() throws Exception {
		System.out.println("Inicio");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Testado com sucesso!");
	}

	@Test
	public void test() {
		ConsoleMenu complementar = new ConsoleMenu();
		assertNotNull(complementar);
	}
	
	

}
