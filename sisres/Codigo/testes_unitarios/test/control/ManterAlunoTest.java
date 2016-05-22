package test.control;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Student;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.FactoryConnection;

import control.StudentRegister;
import exception.ClientException;

public class ManterAlunoTest {

	private static Vector<Student> alunos;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		alunos = StudentRegister.getNewStudent().getVectorStudents();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testGetInstance() {
		assertTrue("Verifica m�todo getInstance() de ManterAluno.", StudentRegister.getNewStudent() instanceof StudentRegister);
	}

	@Test
	public void testSingleton() {
		StudentRegister p = StudentRegister.getNewStudent();
		StudentRegister q = StudentRegister.getNewStudent();
		assertSame("Testando o Padrao Singleton em ManterAluno", p, q);
	}

	
	
	@Test
	public void testInserir() throws ClientException, SQLException {
		Student aluno = new Student("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		StudentRegister.getNewStudent().insert("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		
		boolean resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
				"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + aluno.getIdRegister() + "\";");
				
		if(resultado){
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno.getIdRegister() + "\";");
		}
		
		Student a = alunos.lastElement();
		boolean resultado2 = aluno.equals(a);
		alunos.remove(alunos.lastElement());
		assertTrue("Teste de Inclusao do Aluno.", resultado == true && resultado2 == true);
	}
	
	
	@Test
	public void testAlterar() throws ClientException, SQLException {
		Student aluno = new Student("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		Student a = new Student("Alterando", "040.757.021-70", "123456", "9999-9999", "Nome@email");
		
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + aluno.getNamePerson() + "\", " +
				"\"" + aluno.getCpfPerson()+ "\", " +
				"\"" + aluno.getPhonePerson() + "\", " +
				"\"" + aluno.getEmailPerson() + "\", " +
				"\"" + aluno.getIdRegister() + "\"); ");
		
		StudentRegister.getNewStudent().update("Alterando", "040.757.021-70", "123456", 
				"9999-9999", "Nome@email", aluno);
		
		boolean resultado =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
				"aluno.email = \"" + a.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + a.getIdRegister() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
					"aluno.email = \"" + a.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + a.getIdRegister() + "\";");
		
		assertTrue("Teste de Alteracao do Aluno.", resultado);
	}
	
	@Test
	public void testExcluir() throws ClientException, SQLException {
		Student aluno = new Student("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + aluno.getNamePerson() + "\", " +
				"\"" + aluno.getCpfPerson()+ "\", " +
				"\"" + aluno.getPhonePerson() + "\", " +
				"\"" + aluno.getEmailPerson() + "\", " +
				"\"" + aluno.getIdRegister() + "\");");
		
		StudentRegister.getNewStudent().delete(aluno);
		
		boolean resultado =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
				"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + aluno.getIdRegister() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno.getIdRegister() + "\";");
		
		boolean resultado2 = true;
		if(alunos.size() > 0)
			resultado2 = !alunos.lastElement().equals(aluno);
		
		assertTrue("Teste de Exclusao do Professor.", resultado == false && resultado2 == true);
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
