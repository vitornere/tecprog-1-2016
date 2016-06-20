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


public class SalaDAOTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	
	
	@Test
	public void testInstance() {
<<<<<<< HEAD
		assertTrue("Instanciando SalaDAO", ClassroomDAO.getClassroom() instanceof ClassroomDAO);
	}
	@Test
	public void testSingleton() {
		ClassroomDAO inst1 = ClassroomDAO.getClassroom();
		ClassroomDAO inst2 = ClassroomDAO.getClassroom();
=======
		assertTrue("Instanciando SalaDAO", ClassroomDAO.getInstance() instanceof ClassroomDAO);
	}
	@Test
	public void testSingleton() {
		ClassroomDAO inst1 = ClassroomDAO.getInstance();
		ClassroomDAO inst2 = ClassroomDAO.getInstance();
>>>>>>> devel
		assertSame("Testando o Padrao Singleton", inst2, inst1);
	}
	

	@Test
	public void testIncluir() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		boolean rs = false;
		
<<<<<<< HEAD
		ClassroomDAO.getClassroom().include(s);
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() + "\" and " +
=======
		ClassroomDAO.getInstance().add(s);
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() + "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() +
				";");
		
		if(rs)
			this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() + ";");
		
		assertTrue("Testando Inclusao no Banco", rs);
	}
	@Test (expected= PatrimonyException.class)
	public void testIncluirNulo() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		ClassroomDAO.getClassroom().include(null);
=======
		ClassroomDAO.getInstance().add(null);
>>>>>>> devel
	}
	@Test (expected= PatrimonyException.class)
	public void testIncluirCodigoExistente() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		Classroom s2 = new Classroom("CodigoInc", "Descricao Dois", "200");
		boolean rs = false;
		
<<<<<<< HEAD
		ClassroomDAO.getClassroom().include(s2);
		try{
			ClassroomDAO.getClassroom().include(s);
		} finally {
			rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
					"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
					"sala.descricao = \"" + s.getDescriptionEquipment() + "\" and " +
=======
		ClassroomDAO.getInstance().add(s2);
		try{
			ClassroomDAO.getInstance().add(s);
		} finally {
			rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
					"sala.codigo = \"" + s.getCode() + "\" and " +
					"sala.descricao = \"" + s.getDescription() + "\" and " +
>>>>>>> devel
					"sala.capacidade = " + s.getCapacity() +
					";");
			if(rs)
				this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
						"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
						"sala.descricao = \"" + s.getDescriptionEquipment() +  "\" and " +
						"sala.capacidade = " + s.getCapacity() + ";");
			this.executaNoBanco("DELETE FROM sala WHERE " +
					"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
					"sala.descricao = \"" + s2.getDescriptionEquipment() +  "\" and " +
=======
						"sala.codigo = \"" + s.getCode() + "\" and " +
						"sala.descricao = \"" + s.getDescription() +  "\" and " +
						"sala.capacidade = " + s.getCapacity() + ";");
			this.executaNoBanco("DELETE FROM sala WHERE " +
					"sala.codigo = \"" + s2.getCode() + "\" and " +
					"sala.descricao = \"" + s2.getDescription() +  "\" and " +
>>>>>>> devel
					"sala.capacidade = " + s2.getCapacity() + ";");
		}
		assertFalse("Teste de Inclus�o.", rs);
	}
	
	@Test
	public void testAlerar() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		Classroom s2 = new Classroom("CodigoAlt", "Descricao Dois", "200");
		boolean rs = true, rs2 = false;
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
<<<<<<< HEAD
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		ClassroomDAO.getClassroom().update(s, s2);
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() + "\" and " +
=======
				"\"" + s.getCode() + "\", " +
				"\"" + s.getDescription() + "\", " +
				s.getCapacity() + ");");
		
		ClassroomDAO.getInstance().change(s, s2);
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() + "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() +
				";");
		
		if(rs)
			this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() + ";");
		
		
		rs2 = this.estaNoBanco("SELECT * FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() + "\" and " +
=======
				"sala.codigo = \"" + s2.getCode() + "\" and " +
				"sala.descricao = \"" + s2.getDescription() + "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s2.getCapacity() +
				";");
		if(rs2)
			this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + s2.getCode() + "\" and " +
				"sala.descricao = \"" + s2.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s2.getCapacity() + ";");
		
		assertTrue("Testando Inclusao no Banco", rs2 && !rs);
	}
	@Test (expected= PatrimonyException.class)
	public void testAletarPrimeiroNulo() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
<<<<<<< HEAD
		ClassroomDAO.getClassroom().update(null, s);
=======
		ClassroomDAO.getInstance().change(null, s);
>>>>>>> devel
	}
	@Test (expected= PatrimonyException.class)
	public void testAletarSegundoNulo() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
<<<<<<< HEAD
		ClassroomDAO.getClassroom().update(s, null);
=======
		ClassroomDAO.getInstance().change(s, null);
>>>>>>> devel
	}
	@Test (expected= PatrimonyException.class)
	public void testAletarNaoExistente() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		Classroom s2 = new Classroom("CodigoAlt", "Descricao Dois", "200");
		boolean rs2 = true;
		
		try{
<<<<<<< HEAD
			ClassroomDAO.getClassroom().update(s, s2);
		} finally {		
		
		rs2 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() + "\" and " +
=======
			ClassroomDAO.getInstance().change(s, s2);
		} finally {		
		
		rs2 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s2.getCode() + "\" and " +
				"sala.descricao = \"" + s2.getDescription() + "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s2.getCapacity() +
				";");
		if(rs2)
			this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + s2.getCode() + "\" and " +
				"sala.descricao = \"" + s2.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s2.getCapacity() + ";");
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
<<<<<<< HEAD
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
=======
				"\"" + s.getCode() + "\", " +
				"\"" + s.getDescription() + "\", " +
>>>>>>> devel
				s.getCapacity() + ");");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
<<<<<<< HEAD
				"\"" + s2.getIdEquipment() + "\", " +
				"\"" + s2.getDescriptionEquipment() + "\", " +
				s2.getCapacity() + ");");
		
		try{
			ClassroomDAO.getClassroom().update(s, s2);
		} finally {
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() + "\" and " +
=======
				"\"" + s2.getCode() + "\", " +
				"\"" + s2.getDescription() + "\", " +
				s2.getCapacity() + ");");
		
		try{
			ClassroomDAO.getInstance().change(s, s2);
		} finally {
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() + "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() +
				";");
		
		if(rs)
			this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() + ";");
		
		
		rs2 = this.estaNoBanco("SELECT * FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() + "\" and " +
=======
				"sala.codigo = \"" + s2.getCode() + "\" and " +
				"sala.descricao = \"" + s2.getDescription() + "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s2.getCapacity() +
				";");
		if(rs2)
			this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() +  "\" and " +
				"sala.capacidade = " + s2.getCapacity() + ";");
		
		rs3 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s3.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s3.getDescriptionEquipment() + "\" and " +
=======
				"sala.codigo = \"" + s2.getCode() + "\" and " +
				"sala.descricao = \"" + s2.getDescription() +  "\" and " +
				"sala.capacidade = " + s2.getCapacity() + ";");
		
		rs3 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s3.getCode() + "\" and " +
				"sala.descricao = \"" + s3.getDescription() + "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s3.getCapacity() +
				";");
		if(rs3)
			this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s3.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s3.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + s3.getCode() + "\" and " +
				"sala.descricao = \"" + s3.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s3.getCapacity() + ";");
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
<<<<<<< HEAD
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		try{
			ClassroomDAO.getClassroom().update(s, s2);
		} finally {
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() + "\" and " +
=======
				"\"" + s.getCode() + "\", " +
				"\"" + s.getDescription() + "\", " +
				s.getCapacity() + ");");
		
		try{
			ClassroomDAO.getInstance().change(s, s2);
		} finally {
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() + "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() +
				";");
		
		if(rs)
			this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() +  "\" and " +
				"sala.capacidade = " + s.getCapacity() + ";");
		
		rs2 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() + "\" and " +
=======
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() +  "\" and " +
				"sala.capacidade = " + s.getCapacity() + ";");
		
		rs2 = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s2.getCode() + "\" and " +
				"sala.descricao = \"" + s2.getDescription() + "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s2.getCapacity() +
				";");
		if(rs2)
			this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s2.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s2.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + s2.getCode() + "\" and " +
				"sala.descricao = \"" + s2.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s2.getCapacity() + ";");
		}
		assertTrue("Testando Inclusao no Banco", rs && !rs2);
	}
	
	
	@Test
	public void testExcluir() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		boolean rs = true;
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
<<<<<<< HEAD
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		ClassroomDAO.getClassroom().delete(s);
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() + "\" and " +
=======
				"\"" + s.getCode() + "\", " +
				"\"" + s.getDescription() + "\", " +
				s.getCapacity() + ");");
		
		ClassroomDAO.getInstance().delete(s);
		
		rs = this.estaNoBanco("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() + "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() +
				";");
		
		if(rs)
			this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() + ";");
		
		assertTrue("Testando Inclusao no Banco", !rs);
	}
	@Test (expected= PatrimonyException.class)
	public void testExcluirNulo() throws PatrimonyException, SQLException {
<<<<<<< HEAD
		ClassroomDAO.getClassroom().delete(null);
=======
		ClassroomDAO.getInstance().delete(null);
>>>>>>> devel
	}
	@Ignore // (expected= PatrimonioException.class)
	public void testExcluirEnvolvidoEmReserva() throws PatrimonyException, SQLException {
		
	}
	@Test (expected= PatrimonyException.class)
	public void testExcluirNaoExistente() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
<<<<<<< HEAD
		ClassroomDAO.getClassroom().delete(s);
=======
		ClassroomDAO.getInstance().delete(s);
>>>>>>> devel
	}
	
	
	@Test
	public void testBuscarCodigo() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
<<<<<<< HEAD
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		Vector<Classroom> vet = ClassroomDAO.getClassroom().buscarPorCodigo("CodigoInc");
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() +  "\" and " +
=======
				"\"" + s.getCode() + "\", " +
				"\"" + s.getDescription() + "\", " +
				s.getCapacity() + ");");
		
		Vector<Classroom> vet = ClassroomDAO.getInstance().searchCode("CodigoInc");
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() + ";");
		
		assertTrue("Testando Buscar o Vetor de ", vet.size() > 0);
	}
	@Test
	public void testDescricao() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
<<<<<<< HEAD
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		Vector<Classroom> vet = ClassroomDAO.getClassroom().buscarPorDescricao("Descricao Da Sala Inclusao");
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() +  "\" and " +
=======
				"\"" + s.getCode() + "\", " +
				"\"" + s.getDescription() + "\", " +
				s.getCapacity() + ");");
		
		Vector<Classroom> vet = ClassroomDAO.getInstance().searchDescription("Descricao Da Sala Inclusao");
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() + ";");
		
		assertTrue("Testando Buscar o Vetor de ", vet.size() > 0);
	}
	@Test
	public void testCapacidade() throws PatrimonyException, SQLException {
		Classroom s = new Classroom("CodigoInc", "Descricao Da Sala Inclusao", "123");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
<<<<<<< HEAD
				"\"" + s.getIdEquipment() + "\", " +
				"\"" + s.getDescriptionEquipment() + "\", " +
				s.getCapacity() + ");");
		
		Vector<Classroom> vet = ClassroomDAO.getClassroom().buscarPorCapacidade("123");
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + s.getDescriptionEquipment() +  "\" and " +
=======
				"\"" + s.getCode() + "\", " +
				"\"" + s.getDescription() + "\", " +
				s.getCapacity() + ");");
		
		Vector<Classroom> vet = ClassroomDAO.getInstance().searchCapacity("123");
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + s.getCode() + "\" and " +
				"sala.descricao = \"" + s.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + s.getCapacity() + ";");
		
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
