package test.control;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.FactoryConnection;
import control.ManterSala;
import model.Classroom;
import exception.PatrimonyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;


public class ManterSalaTest {
	
	@BeforeClass
	public static void setUpClass(){
		
	}

	@AfterClass
	public static void tearDownClass(){
	}
	
	@Test
	public void testGetInstance() {
		assertTrue("Verifica metodo getInstance().", ManterSala.getInstance() instanceof ManterSala);
	}
	
	@Test
	public void testSingleton() {
		ManterSala p = ManterSala.getInstance();
		ManterSala q = ManterSala.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}


	@Test
	public void testInserir() throws PatrimonyException, SQLException {
		Classroom sala_new = new Classroom("codigo", "descricao", "2");
		ManterSala.getInstance().inserir("codigo", "descricao", "2");
		assertNotNull("Falha ao inserir", this.procurarNoVetor(sala_new));
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + sala_new.getCode() + "\" and " +
				"sala.descricao = \"" + sala_new.getDescription() +  "\" and " +
				"sala.capacidade = " + sala_new.getCapacity() + ";"
				);
	}

	@Test
	public void testAlterar() throws PatrimonyException, SQLException {
		Classroom sala = new Classroom("codigo_old", "descricao", "1");
		Classroom sala_new = new Classroom("codigo", "descricao", "2");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + sala.getCode() + "\", " +
				"\"" + sala.getDescription() + "\", " +
				"" + sala.getCapacity() + "); "
				);
		ManterSala.getInstance().alterar("codigo", "descricao", "2", sala);
		
		assertNotNull("Falha ao alterar", this.procurarNoVetor(sala_new));
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + sala_new.getCode() + "\" and " +
				"sala.descricao = \"" + sala_new.getDescription() +  "\" and " +
				"sala.capacidade = " + sala_new.getCapacity() + ";"
				);
	}

	@Test
	public void testExcluir() throws SQLException, PatrimonyException {
		Classroom sala = new Classroom("codigo_old", "descricao", "1");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + sala.getCode() + "\", " +
				"\"" + sala.getDescription() + "\", " +
				"" + sala.getCapacity() + "); "
				);
		
		ManterSala.getInstance().excluir(sala);
		
		assertNull("Falha ao excluir", this.procurarNoVetor(sala));
	}

	public Classroom procurarNoVetor(Classroom teste) throws PatrimonyException, SQLException {
		Vector<Classroom> todos = ManterSala.getInstance().getSalas_vet();
		Iterator<Classroom> i = todos.iterator();
		while(i.hasNext()){
			Classroom e = i.next();
			if(e.equals(teste))
				return e;			
		}
		return null;
	}
	
	private void executaNoBanco(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();
		pst.close();
		con.close();
	}
	
}
