package test.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Equipment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.PatrimonyException;

/**
 * Testa Patrimonio, de modo indireto por causa da Heranca de Equipamento
 * */

public class EquipamentoTest {

	Equipment eq;
	
	@Before
	public void setUp() throws PatrimonyException{
<<<<<<< HEAD
		eq = new Equipment("codigo", "descricao");
=======
		eq = new Equipamento("codigo", "descricao");
>>>>>>> devel
	}
	
	@After
	public void tearDown() throws PatrimonyException{
		eq = null;
	}
	
	@Test
	public void testInstance() throws PatrimonyException {
<<<<<<< HEAD
		assertTrue(eq instanceof Equipment);
=======
		assertTrue(eq instanceof Equipamento);
>>>>>>> devel
	}
	
	@Test
	public void testNome() throws PatrimonyException {
<<<<<<< HEAD
		assertTrue("codigo diferente instanciado", "codigo" == eq.getIdEquipment());
=======
		assertTrue("codigo diferente instanciado", "codigo" == eq.getCode());
>>>>>>> devel
	}
	
	@Test
	public void testDescricao() throws PatrimonyException {
<<<<<<< HEAD
		assertTrue("Descricao diferente instanciada", "descricao" == eq.getDescriptionEquipment());
=======
		assertTrue("Descricao diferente instanciada", "descricao" == eq.getDescription());
>>>>>>> devel
	}
	
	@Test
	public void testEquals() throws PatrimonyException {
<<<<<<< HEAD
		Equipment eq2 = new Equipment("codigo", "descricao");
=======
		Equipamento eq2 = new Equipamento("codigo", "descricao");
>>>>>>> devel
		assertTrue("Equipamentos deviam ser iguais", eq.equals(eq2));
	}
	
	@Test
	public void testEqualsCodigoDiferente() throws PatrimonyException {
<<<<<<< HEAD
		Equipment eq2 = new Equipment("codigo diferente", "descricao");
=======
		Equipamento eq2 = new Equipamento("codigo diferente", "descricao");
>>>>>>> devel
		assertFalse("Equipamentos deviam ser diferentes", eq.equals(eq2));
	}
	
	@Test
	public void testEqualsDescricaoDiferente() throws PatrimonyException {
<<<<<<< HEAD
		Equipment eq2 = new Equipment("codigo", "descricao diferente");
=======
		Equipamento eq2 = new Equipamento("codigo", "descricao diferente");
>>>>>>> devel
		assertFalse("Equipamentos deviam ser diferentes", eq.equals(eq2));
	}
	
	@Test(expected = PatrimonyException.class)
	public void testDescricaoVazia() throws PatrimonyException {
<<<<<<< HEAD
		new Equipment("abc", "");
=======
		new Equipamento("abc", "");
>>>>>>> devel
	}
	
	@Test(expected = PatrimonyException.class)
	public void testCodigoVazio() throws PatrimonyException {
<<<<<<< HEAD
		new Equipment("", "abc");
=======
		new Equipamento("", "abc");
>>>>>>> devel
	}
	
	@Test(expected = PatrimonyException.class)
	public void testCodigoNulo() throws PatrimonyException {
<<<<<<< HEAD
		new Equipment(null, "abc");
=======
		new Equipamento(null, "abc");
>>>>>>> devel
	}
	
	@Test(expected = PatrimonyException.class)
	public void testDescricaoNulo() throws PatrimonyException {
<<<<<<< HEAD
		new Equipment("abc", null);
=======
		new Equipamento("abc", null);
>>>>>>> devel
	}

}
