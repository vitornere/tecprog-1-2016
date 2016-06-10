package test.model;

import static org.junit.Assert.*;
import model.Classroom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import exception.PatrimonyException;

public class SalaTest {
	
	Classroom sala;
	
	@Before
	public void setUp() throws PatrimonyException{
		sala = new Classroom("codigo", "descricao", "1");
	}
	
	@After
	public void tearDown() throws PatrimonyException{
		sala = null;
	}
	
	@Test
    public void testInstance() throws PatrimonyException {
		assertTrue(new Classroom("codigo", "descricao","1") instanceof Classroom);
	}
	
	@Test
	public void testEquals() throws PatrimonyException {
		setUp();
		Classroom sala_new = new Classroom("codigo", "descricao", "1");
		assertTrue("Falha no Equals.", sala_new.equals(sala));
		sala_new = null;
		tearDown();
	}
	
	@Test
	public void testNotEqualsCapacidade() throws PatrimonyException {
		Classroom s = new Classroom("codigo", "descricao", "1");
		Classroom s2 = new Classroom("codigo", "descricao", "2");
		assertFalse("Falha no Equals.", s.equals(s2));

	}
	
	@Test
	public void testNotEqualsDescricao() throws PatrimonyException {
		setUp();
		Classroom sala_new = new Classroom("codigo", "d", "1");
		assertFalse("Falha no Equals.", sala.equals(sala_new));
		sala_new = null;
		tearDown();
	}
	
	@Test
	public void testNotEqualsCodigo() throws PatrimonyException {
		setUp();
		Classroom sala_new = new Classroom("c", "descricao", "1");
		assertFalse("Falha no Equals.", sala.equals(sala_new));
		sala_new = null;
		tearDown();
	}
	
	@Test
	public void testCodigo() throws PatrimonyException {
		setUp();
<<<<<<< HEAD
		assertEquals("codigo diferente instanciado", "codigo", sala.getIdEquipment());
=======
		assertEquals("codigo diferente instanciado", "codigo", sala.getCode());
>>>>>>> devel
		tearDown();
	}
	
	@Test
	public void testDescricao() throws PatrimonyException {
		setUp();
<<<<<<< HEAD
		assertEquals("Descricao diferente instanciada", "descricao", sala.getDescriptionEquipment());
=======
		assertEquals("Descricao diferente instanciada", "descricao", sala.getDescription());
>>>>>>> devel
		tearDown();
	}	
	
	@Test
	public void testCapacidade() throws PatrimonyException {
		setUp();
		assertEquals("Capacidade diferente instanciada", "1", sala.getCapacity());
		tearDown();
	}
	
	@Test(expected = exception.PatrimonyException.class)
	public void testCapacidadeNegativo() throws PatrimonyException {
		setUp();
<<<<<<< HEAD
		sala.setCapacity("-1");
=======
		sala.setCapacidade("-1");
>>>>>>> devel
		assertEquals("Capacidade diferente instanciada", "1", sala.getCapacity());
		tearDown();
	}
	
	@Test(expected = exception.PatrimonyException.class)
	public void testCapacidadeLetra() throws PatrimonyException {
		setUp();
<<<<<<< HEAD
		sala.setCapacity("a");
=======
		sala.setCapacidade("a");
>>>>>>> devel
		assertEquals("Capacidade diferente instanciada", "1", sala.getCapacity());
		tearDown();
	}
	
	@Test(expected = exception.PatrimonyException.class)
	public void testDescricaoVazia() throws PatrimonyException {
		new Classroom("codigo", "", "1");
	}
	
	@Test(expected = exception.PatrimonyException.class)
	public void testCapacidadeVazia() throws PatrimonyException {
		new Classroom("codigo", "descricao", "");
	}
	
	@Test(expected = exception.PatrimonyException.class)
	public void testCodigoVazio() throws PatrimonyException {
		new Classroom("", "descricao","1");
	}
	
	@Test(expected = exception.PatrimonyException.class)
	public void testCodigoNulo() throws PatrimonyException {
		new Classroom(null, "descricao", "1");
	}
	
	@Test(expected = exception.PatrimonyException.class)
	public void testDescricaoNulo() throws PatrimonyException {
		new Classroom("codigo", null,"1");
	}
	
	@Test(expected = exception.PatrimonyException.class)
	public void testCapacidadeNulo() throws PatrimonyException {
		new Classroom("codigo", "descricao", null);
	}
}
