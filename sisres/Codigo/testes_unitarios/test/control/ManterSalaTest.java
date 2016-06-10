package test.control;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.FactoryConnection;
<<<<<<< HEAD
import control.ClassroomRegister;
=======
import control.ManterSala;
>>>>>>> devel
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
		assertTrue("Verifica metodo getInstance().", ClassroomRegister.getClassroom() instanceof ClassroomRegister);
	}
	
	@Test
	public void testSingleton() {
		ClassroomRegister p = ClassroomRegister.getClassroom();
		ClassroomRegister q = ClassroomRegister.getClassroom();
		assertSame("Testando o Padrao Singleton", p, q);
	}


	@Test
	public void testInserir() throws PatrimonyException, SQLException {
		Classroom sala_new = new Classroom("codigo", "descricao", "2");
<<<<<<< HEAD
		ClassroomRegister.getClassroom().insert("codigo", "descricao", "2");
		assertNotNull("Falha ao inserir", this.procurarNoVetor(sala_new));
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + sala_new.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala_new.getDescriptionEquipment() +  "\" and " +
=======
		ManterSala.getInstance().inserir("codigo", "descricao", "2");
		assertNotNull("Falha ao inserir", this.procurarNoVetor(sala_new));
		this.executaNoBanco("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + sala_new.getCode() + "\" and " +
				"sala.descricao = \"" + sala_new.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + sala_new.getCapacity() + ";"
				);
	}

	@Test
	public void testAlterar() throws PatrimonyException, SQLException {
		Classroom sala = new Classroom("codigo_old", "descricao", "1");
		Classroom sala_new = new Classroom("codigo", "descricao", "2");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
<<<<<<< HEAD
				"\"" + sala.getIdEquipment() + "\", " +
				"\"" + sala.getDescriptionEquipment() + "\", " +
=======
				"\"" + sala.getCode() + "\", " +
				"\"" + sala.getDescription() + "\", " +
>>>>>>> devel
				"" + sala.getCapacity() + "); "
				);
		ClassroomRegister.getClassroom().update("codigo", "descricao", "2", sala);
		
		assertNotNull("Falha ao alterar", this.procurarNoVetor(sala_new));
		
		this.executaNoBanco("DELETE FROM sala WHERE " +
<<<<<<< HEAD
				"sala.codigo = \"" + sala_new.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala_new.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + sala_new.getCode() + "\" and " +
				"sala.descricao = \"" + sala_new.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + sala_new.getCapacity() + ";"
				);
	}

	@Test
	public void testExcluir() throws SQLException, PatrimonyException {
		Classroom sala = new Classroom("codigo_old", "descricao", "1");
		
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
<<<<<<< HEAD
				"\"" + sala.getIdEquipment() + "\", " +
				"\"" + sala.getDescriptionEquipment() + "\", " +
=======
				"\"" + sala.getCode() + "\", " +
				"\"" + sala.getDescription() + "\", " +
>>>>>>> devel
				"" + sala.getCapacity() + "); "
				);
		
		ClassroomRegister.getClassroom().delete(sala);
		
		assertNull("Falha ao excluir", this.procurarNoVetor(sala));
	}

	public Classroom procurarNoVetor(Classroom teste) throws PatrimonyException, SQLException {
<<<<<<< HEAD
		Vector<Classroom> todos = ClassroomRegister.getClassroom().getVectorClassroom();
=======
		Vector<Classroom> todos = ManterSala.getInstance().getSalas_vet();
>>>>>>> devel
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
