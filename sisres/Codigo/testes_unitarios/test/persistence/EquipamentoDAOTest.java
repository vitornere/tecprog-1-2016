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

<<<<<<< HEAD
import persistence.EquipmentDAO;
=======
import persistence.EquipamentoDAO;
>>>>>>> devel
import exception.PatrimonyException;


public class EquipamentoDAOTest {
	static EquipmentDAO instance;
	Equipment antigo, novo;
	Vector <Equipment> todos;
	
	@BeforeClass
	public static void setUpClass() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		instance = EquipmentDAO.getNewEquipment();
=======
		instance = EquipamentoDAO.getInstance();
>>>>>>> devel
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException, PatrimonyException {
		instance = null;
	}
	
	@Before
	public void setUp() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		 antigo = new Equipment("codigo", "descricao - antigo");
		 novo = new Equipment("codigo", "descricao - alterada");
		 instance.include(antigo);
		 todos = instance.searchAll();
=======
		 antigo = new Equipamento("codigo", "descricao - antigo");
		 novo = new Equipamento("codigo", "descricao - alterada");
		 instance.incluir(antigo);
		 todos = instance.buscarTodos();
>>>>>>> devel
	}
	
	@After
	public void tearDown() throws SQLException, PatrimonyException {
<<<<<<< HEAD
		todos = instance.searchAll();
		Iterator<Equipment> i = todos.iterator();
=======
		todos = instance.buscarTodos();
		Iterator<Equipamento> i = todos.iterator();
>>>>>>> devel
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
		EquipmentDAO inst1 = EquipmentDAO.getNewEquipment();
		EquipmentDAO inst2 = EquipmentDAO.getNewEquipment();
		assertSame("Testando o Padrao Singleton", inst2, inst1);
	}
	
	@Test
	public void testIncluir() throws PatrimonyException, SQLException {
		assertNotNull("Equipamento nao foi incluido", procurarNoVetor(antigo));
	}
	@Test
	public void testBuscarTodos() throws SQLException, PatrimonyException {
		assertNotNull("Testando a busca de elementos no BD.", todos);
	}
	
	@Test
	public void testBuscarPorCodigo() throws SQLException, PatrimonyException {
<<<<<<< HEAD
		assertNotNull("Testando a busca por codigo de elementos no BD.", instance.buscarPorCodigo(antigo.getIdEquipment()));
=======
		assertNotNull("Testando a busca por codigo de elementos no BD.", instance.buscarPorCodigo(antigo.getCode()));
>>>>>>> devel
	}
	
	@Test
	public void testBuscarPorDescricao() throws SQLException, PatrimonyException {
<<<<<<< HEAD
		assertNotNull("Testando a busca por descricao de elementos no BD.", instance.buscarPorDescricao(antigo.getDescriptionEquipment()));
=======
		assertNotNull("Testando a busca por descricao de elementos no BD.", instance.buscarPorDescricao(antigo.getDescription()));
>>>>>>> devel
	}
	
	@Test
	public void testBuscarPorCodigoNull() throws SQLException, PatrimonyException {
		assertTrue("Testando a busca por codigo nulo de elementos no BD.", instance.buscarPorCodigo(null).isEmpty());
	}
	
	@Test
	public void testBuscarPorDescricaoNull() throws SQLException, PatrimonyException {
		assertTrue("Testando a busca por descricao nula de elementos no BD.", instance.buscarPorDescricao(null).isEmpty());
	}
	
	@Test
	public void testAlterar() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		instance.update(antigo, novo);
		Equipment e = procurarNoVetor(antigo);
=======
		instance.alterar(antigo, novo);
		Equipamento e = procurarNoVetor(antigo);
>>>>>>> devel
		assertNull("Equipamento nao foi alterado", e);
		assertNotNull("Equipamento nao foi alterado", procurarNoVetor(novo));
	}
	
	@Test (expected= PatrimonyException.class)
	public void testIncluirComCodigoExistente() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		instance.include(antigo);
=======
		instance.incluir(antigo);
>>>>>>> devel
	}
	
	@Test (expected= PatrimonyException.class)
	public void testIncluirNulo() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		instance.include(null);
=======
		instance.incluir(null);
>>>>>>> devel
	}
	
	@Test (expected= PatrimonyException.class)
	public void testAlterarNull() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		instance.update(null, null);
=======
		instance.alterar(null, null);
>>>>>>> devel
	}
	
	@Test (expected= PatrimonyException.class)
	public void testAlterarSegundoNull() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		instance.update(antigo, null);
=======
		instance.alterar(antigo, null);
>>>>>>> devel
	}
	
	
	@Test (expected= PatrimonyException.class)
	public void testAlterarNaoExistente() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		Equipment equip = new Equipment("codigo", "eqpt nao existente");
		Equipment equipAlter = new Equipment("codigo", "eqpt nao existente alteraddo");
		instance.update(equip, equipAlter);
=======
		Equipamento equip = new Equipamento("codigo", "eqpt nao existente");
		Equipamento equipAlter = new Equipamento("codigo", "eqpt nao existente alteraddo");
		instance.alterar(equip, equipAlter);
>>>>>>> devel
	}
	
	@Test (expected= PatrimonyException.class)
	public void testAlterarIgual() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		instance.update(novo, novo);
=======
		instance.alterar(novo, novo);
>>>>>>> devel
	}
	
	@Test (expected= PatrimonyException.class)
	public void testAlterarParaOutroEquipamento() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		Equipment e = new Equipment("novo", "teste Alterar para outro");
		instance.include(e);
		instance.update(e, novo);
=======
		Equipamento e = new Equipamento("novo", "teste Alterar para outro");
		instance.incluir(e);
		instance.alterar(e, novo);
>>>>>>> devel
	}
	
	@Test (expected= PatrimonyException.class)
	public void testExcluirNull() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		instance.delete(null);
=======
		instance.excluir(null);
>>>>>>> devel
	}
	
	@Test (expected= PatrimonyException.class)
	public void testExcluirNaoExistente() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		Equipment eq = new Equipment("codigo"," nao existe descricao");
		instance.delete(eq);
=======
		Equipamento eq = new Equipamento("codigo"," nao existe descricao");
		instance.excluir(eq);
>>>>>>> devel
	}
	
	@Test
	public void testExcluirExistente() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		Equipment novoExclusao = new Equipment("cdg", "teste exclusao");
		instance.include(novoExclusao);
		instance.delete(novoExclusao);
		assertNull("Equipamento nao foi alterado", procurarNoVetor(novoExclusao));
	}
	
	public Equipment procurarNoVetor(Equipment teste) throws PatrimonyException, SQLException {
		todos = instance.searchAll();
		Iterator<Equipment> i = todos.iterator();
=======
		Equipamento novoExclusao = new Equipamento("cdg", "teste exclusao");
		instance.incluir(novoExclusao);
		instance.excluir(novoExclusao);
		assertNull("Equipamento nao foi alterado", procurarNoVetor(novoExclusao));
	}
	
	public Equipamento procurarNoVetor(Equipamento teste) throws PatrimonyException, SQLException {
		todos = instance.buscarTodos();
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
