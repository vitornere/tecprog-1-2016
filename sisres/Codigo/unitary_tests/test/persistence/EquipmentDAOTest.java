package test.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import model.Equipment;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.EquipmentDAO;
import exception.PatrimonyException;


public class EquipmentDAOTest {
	static EquipmentDAO instance;
	Equipment antigo, novo;
	Vector <Equipment> all;
	
	@BeforeClass
	public static void setUpClass() throws PatrimonyException, SQLException {
		instance = EquipmentDAO.getInstance();
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException, PatrimonyException {
		instance = null;
	}
	
	@Before
	public void setUp() throws PatrimonyException, SQLException {
		 antigo = new Equipment("codigo", "descricao - antigo");
		 novo = new Equipment("codigo", "descricao - alterada");
		 instance.add(antigo);
		 all = instance.searchAll();
	}
	
	@After
	public void tearDown() throws SQLException, PatrimonyException {
		all = instance.searchAll();
		Iterator<Equipment> i = all.iterator();
		while(i.hasNext()){
			Equipment e = i.next();
			instance.delete(e);
		}
		antigo = null;
		novo = null;
	}
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando EquipamentoDAO", instance instanceof EquipmentDAO);
	}
	
	@Test
	public void testSingleton() {
		EquipmentDAO inst1 = EquipmentDAO.getInstance();
		EquipmentDAO inst2 = EquipmentDAO.getInstance();
		assertSame("Testando o Padrao Singleton", inst2, inst1);
	}
	
	@Test
	public void testIncluir() throws PatrimonyException, SQLException {
		assertNotNull("Equipamento nao foi incluido", procurarNoVetor(antigo));
	}
	@Test
	public void testBuscarTodos() throws SQLException, PatrimonyException {
		assertNotNull("Testando a busca de elementos no BD.", all);
	}
	
	@Test
	public void testBuscarPorCodigo() throws SQLException, PatrimonyException {
		assertNotNull("Testando a busca por codigo de elementos no BD.", instance.searchByCode(antigo.getIdEquipment()));
	}
	
	@Test
	public void testBuscarPorDescricao() throws SQLException, PatrimonyException {
		assertNotNull("Testando a busca por descricao de elementos no BD.", instance.searchByDescription(antigo.getDescriptionEquipment()));
	}
	
	@Test
	public void testBuscarPorCodigoNull() throws SQLException, PatrimonyException {
		assertTrue("Testando a busca por codigo nulo de elementos no BD.", instance.searchByCode(null).isEmpty());
	}
	
	@Test
	public void testBuscarPorDescricaoNull() throws SQLException, PatrimonyException {
		assertTrue("Testando a busca por descricao nula de elementos no BD.", instance.searchByDescription(null).isEmpty());
	}
	
	@Test
	public void testAlterar() throws PatrimonyException, SQLException {
		instance.change(antigo, novo);
		Equipment e = procurarNoVetor(antigo);
		assertNull("Equipamento nao foi alterado", e);
		assertNotNull("Equipamento nao foi alterado", procurarNoVetor(novo));
	}
	
	@Test (expected= PatrimonyException.class)
	public void testIncluirComCodigoExistente() throws PatrimonyException, SQLException {
		instance.add(antigo);
	}
	
	@Test (expected= PatrimonyException.class)
	public void testIncluirNulo() throws PatrimonyException, SQLException {
		instance.add(null);
	}
	
	@Test (expected= PatrimonyException.class)
	public void testAlterarNull() throws PatrimonyException, SQLException {
		instance.change(null, null);
	}
	
	@Test (expected= PatrimonyException.class)
	public void testAlterarSegundoNull() throws PatrimonyException, SQLException {
		instance.change(antigo, null);
	}
	
	
	@Test (expected= PatrimonyException.class)
	public void testAlterarNaoExistente() throws PatrimonyException, SQLException {
		Equipment equip = new Equipment("codigo", "eqpt nao existente");
		Equipment equipAlter = new Equipment("codigo", "eqpt nao existente alteraddo");
		instance.change(equip, equipAlter);
	}
	
	@Test (expected= PatrimonyException.class)
	public void testAlterarIgual() throws PatrimonyException, SQLException {
		instance.change(novo, novo);
	}
	
	@Test (expected= PatrimonyException.class)
	public void testAlterarParaOutroEquipamento() throws PatrimonyException, SQLException {
		Equipment e = new Equipment("novo", "teste Alterar para outro");
		instance.add(e);
		instance.change(e, novo);
	}
	
	@Test (expected= PatrimonyException.class)
	public void testExcluirNull() throws PatrimonyException, SQLException {
		instance.delete(null);
	}
	
	@Test (expected= PatrimonyException.class)
	public void testExcluirNaoExistente() throws PatrimonyException, SQLException {
		Equipment eq = new Equipment("codigo"," nao existe descricao");
		instance.delete(eq);
	}
	
	@Test
	public void testExcluirExistente() throws PatrimonyException, SQLException {
		Equipment novoExclusao = new Equipment("cdg", "teste exclusao");
		instance.add(novoExclusao);
		instance.delete(novoExclusao);
		assertNull("Equipamento nao foi alterado", procurarNoVetor(novoExclusao));
	}
	
	public Equipment procurarNoVetor(Equipment teste) throws PatrimonyException, SQLException {
		all = instance.searchAll();
		Iterator<Equipment> i = all.iterator();
	
		while(i.hasNext()){
			Equipment e = i.next();
			if(e.equals(teste))
				return e;			
		}
		return null;
	}
	
}
