package test.persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Classroom;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import persistence.FactoryConnection;
import persistence.ClassroomDAO;
import exception.PatrimonyException;


public class ClassroomDAOTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando SalaDAO", ClassroomDAO.getInstance() instanceof ClassroomDAO);
	}
	@Test
	public void testSingleton() {
		ClassroomDAO inst1 = ClassroomDAO.getInstance();
		ClassroomDAO inst2 = ClassroomDAO.getInstance();
		assertSame("Testando o Padrao Singleton", inst2, inst1);
	}
	

	@Test
	public void testIncluir() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		boolean rs = false;
		
		ClassroomDAO.getInstance().add(s);
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + 
				"\";");
		
		if(rs)
			this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\";");
		
		assertTrue("Testando Inclusao no Banco", rs);
	}
	
	@Test (expected= PatrimonyException.class)
	public void testIncluirNulo() throws PatrimonyException, SQLException {
		ClassroomDAO.getInstance().add(null);
	}
	
	@Test (expected= PatrimonyException.class)
	public void testIncluirCodigoExistente() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		Classroom s2 = new Classroom("CodigoInc", "Descricao Dois", "200");
		boolean rs = false;
		
		ClassroomDAO.getInstance().add(s2);
		try{
			ClassroomDAO.getInstance().add(s);
		} finally {
			rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
					"sala.codigo = \"" + s.getIdEquipment() + 
					"\"	;");
			if(rs){
				this.executaNoBanco("DELETE FROM sala WHERE " +
						"sala.codigo = \"" + s.getIdEquipment() + "\";");
				this.executaNoBanco("DELETE FROM sala WHERE " +
					"sala.codigo = \"" + s2.getIdEquipment() + "\";");
			}
		}
		assertFalse("Teste de Inclus�o.", rs);
	}
	
	@Test
	public void testAlterar() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInco", "Descricao Da Sala Inclusao", "123");
		Classroom s2 = new Classroom("CodigoAlt", "Descricao Dois", "200");
		boolean rs = true, rs2 = false;
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		ClassroomDAO.getInstance().change(s, s2);
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() +
				"\";");
		
		if(rs){
			this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\";");
		}
		
		rs2 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s2.getIdEquipment() + 
				"\";");
		if(rs2){
			this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s2.getIdEquipment() + "\";");
		}
		assertTrue("Testando Inclusao no Banco", rs2 && !rs);
	}
	@Test (expected= PatrimonyException.class)
	public void testAletarPrimeiroNulo() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		ClassroomDAO.getInstance().change(null, s);
	}
	@Test (expected= PatrimonyException.class)
	public void testAletarSegundoNulo() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		ClassroomDAO.getInstance().change(s, null);
	}
	@Test (expected= PatrimonyException.class)
	public void testAletarNaoExistente() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		Classroom s2 = new Classroom("CodigoAlt", "Descricao Dois", "200");
		boolean rs2 = true;
		
		try{
			ClassroomDAO.getInstance().change(s, s2);
		} finally {		
		
		rs2 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() + "\" and " +
				"sala.capacidade = " + s2.getCapacity() +
				";");
		if(rs2)
			this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s2.getIdEquipment() + "\";");
		}
		assertTrue("Testando Inclusao no Banco", !rs2);
	}
	@Ignore // (expected= PatrimonioException.class)
	public void testAletarEvolvidoEmReserva() throws PatrimonyException, SQLException {
	}
	@Test (expected= PatrimonyException.class)
	public void testAletarComMesmoCodigo() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		Classroom s2 = new Classroom("CodigoAlt", "Descricao Dois", "200");
		Classroom s3 = new Classroom("CodigoInc", "Descricao Dois", "200");
		boolean rs = false, rs2 = false, rs3 = true;
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + s2.getIdEquipment() + "\", " +
				"\"" + s2.getDescriptionEquipment() + "\", " +
				s2.getCapacity() + ");");
		
		try{
			ClassroomDAO.getInstance().change(s, s2);
		} finally {
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() + "\" and " +
				"sala.capacidade = " + s.getCapacity() +
				";");
		
		if(rs)
			this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\";");
		
		
		rs2 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() + "\" and " +
				"sala.capacidade = " + s2.getCapacity() +
				";");
		if(rs2)
			this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s2.getIdEquipment() + "\";");
		
		rs3 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s3.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s3.getDescriptionEquipment() + "\" and " +
				"sala.capacidade = " + s3.getCapacity() +
				";");
		if(rs3)
			this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s3.getIdEquipment() + "\";");
		}
		assertTrue("Testando Inclusao no Banco", rs && rs2 && !rs3);
	}
	@Test (expected= PatrimonyException.class)
	public void testAletarParaExistente() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoAlt", "Descricao Dois", "200");
		Classroom s2 = new Classroom("CodigoAlt", "Descricao Dois", "200");
		boolean rs = false, rs2 = true;
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		try{
			ClassroomDAO.getInstance().change(s, s2);
		} finally {
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() + "\" and " +
				"sala.capacidade = " + s.getCapacity() +
				";");
		
		if(rs)
			this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\";");
		
		rs2 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() + "\" and " +
				"sala.capacidade = " + s2.getCapacity() +
				";");
		if(rs2)
			this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s2.getIdEquipment() + "\";");
		}
		assertTrue("Testando Inclusao no Banco", rs && !rs2);
	}
	
	
	@Test
	public void testExcluir() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		boolean rs = true;
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		ClassroomDAO.getInstance().delete(s);
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() + "\" and " +
				"sala.capacidade = " + s.getCapacity() +
				";");
		
		if(rs)
			this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\";");
		
		assertTrue("Testando Inclusao no Banco", !rs);
	}
	
	@Test (expected= PatrimonyException.class)
	public void testExcluirNulo() throws PatrimonyException, SQLException {
		ClassroomDAO.getInstance().delete(null);
	}
	@Ignore // (expected= PatrimonioException.class)
	public void testExcluirEnvolvidoEmReserva() throws PatrimonyException, SQLException {
		
	}
	
	@Test (expected= PatrimonyException.class)
	public void testExcluirNaoExistente() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		ClassroomDAO.getInstance().delete(s);
	}
	
	
	@Test
	public void testBuscarCodigo() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		Vector<Classroom> vet = ClassroomDAO.getInstance().searchCode("CodigoInc");
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\";");
		
		assertTrue("Testando Buscar o Vetor de ", vet.size() > 0);
	}
	@Test
	public void testDescricao() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		Vector<Classroom> vet = ClassroomDAO.getInstance().searchDescription("Descricao Da Sala Inclusao");
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\";");
		
		assertTrue("Testando Buscar o Vetor de ", vet.size() > 0);
	}
	@Test
	public void testCapacidade() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		Vector<Classroom> vet = ClassroomDAO.getInstance().searchCapacity("123");
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\";");
		
		assertTrue("Testando Buscar o Vetor de ", vet.size() > 0);
	}

	
	private void executaNoBanco(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();
		pst.close();
		con.close();
	}
	private boolean estaNoBanco(String query) throws SQLException{
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
		{
			rs.close();
			pst.close();
			con.close();
			return false;
		}
		else {
			rs.close();
			pst.close();
			con.close();
			return true;
		}
	}
	
}
