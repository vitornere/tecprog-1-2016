package test.control;

<<<<<<< HEAD
import control.EquipmentRegister;
=======
import control.ManterEquipamento;
>>>>>>> devel
import exception.PatrimonyException;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import model.Equipment;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ManterEquipamentoTest {

	static EquipmentRegister instance;
	Vector<Equipment> todos;
	Equipment e;
 
	public ManterEquipamentoTest() {
	}

	@BeforeClass
	public static void setUpClass() throws PatrimonyException {
<<<<<<< HEAD
		instance = EquipmentRegister.getNewEquipment();
=======
		instance = ManterEquipamento.getInstance();
>>>>>>> devel
	}

	@AfterClass
	public static void tearDownClass() {
		instance = null;
	}

	@Before
	public void setUp() throws Exception {
		e = new Equipment("codigo", "descricao");
		instance.insert("codigo","descricao");
		todos = instance.getVectorEquipments();
	}

	@After
	public void tearDown() throws SQLException, PatrimonyException {
<<<<<<< HEAD
		todos = instance.getVectorEquipments();
		Iterator<Equipment> i = todos.iterator();
=======
		todos = instance.getEquipamento_vet();
		Iterator<Equipamento> i = todos.iterator();
>>>>>>> devel
		while(i.hasNext()){
			e = i.next();
			instance.delete(e);
		}
		e = null;
	}
	
	@Test
	public void testGetEquipamento_vet() throws Exception {
		assertNotNull(todos);
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull("Get Instance falhou",instance);
	}
	
	@Test
	public void testSingleton(){
		EquipmentRegister me = EquipmentRegister.getNewEquipment();
		assertSame("Instancias diferentes", me, instance);
		
	}

	@Test
	public void testIncluirVet() throws SQLException, PatrimonyException {
		assertNotNull("Teste de Inclusao no Equipamento Vet.", procurarNoVetor(e));
	}
	
	@Test
	public void testAlterarVet() throws SQLException, PatrimonyException {
<<<<<<< HEAD
		instance.update("codigo alterado", "descricao alterarda", e);
		Equipment e2 = new Equipment("codigo alterado", "descricao alterarda");
=======
		instance.alterar("codigo alterado", "descricao alterarda", e);
		Equipamento e2 = new Equipamento("codigo alterado", "descricao alterarda");
>>>>>>> devel
		assertNotNull("Teste de Inclusao no Equipamento Vet.", procurarNoVetor(e2));
	}
	
	@Test(expected = PatrimonyException.class)
	public void testAlterarNaoExistente() throws SQLException, PatrimonyException {
<<<<<<< HEAD
		Equipment eq = new Equipment("codigo", "nao existe");
		instance.update("codigo alterado", "descricao alterarda", eq);
=======
		Equipamento eq = new Equipamento("codigo", "nao existe");
		instance.alterar("codigo alterado", "descricao alterarda", eq);
>>>>>>> devel
	}
	
	@Test(expected = PatrimonyException.class)
	public void testAlterarNull() throws SQLException, PatrimonyException {
<<<<<<< HEAD
		instance.update("codigo alterado", "descricao alterarda", null);
=======
		instance.alterar("codigo alterado", "descricao alterarda", null);
>>>>>>> devel
	}
	
	@Test (expected = PatrimonyException.class)
	public void testExcluirNull() throws SQLException, PatrimonyException {
		e = null;
		instance.delete(e);
	}
	
<<<<<<< HEAD
	public Equipment procurarNoVetor(Equipment teste) throws PatrimonyException, SQLException {
		todos = instance.getVectorEquipments();
		Iterator<Equipment> i = todos.iterator();
=======
	public Equipamento procurarNoVetor(Equipamento teste) throws PatrimonyException, SQLException {
		todos = instance.getEquipamento_vet();
		Iterator<Equipamento> i = todos.iterator();
>>>>>>> devel
		while(i.hasNext()){
			Equipment e = i.next();
			if(e.equals(teste))
				return e;			
		}
		return null;
	}
	
}
