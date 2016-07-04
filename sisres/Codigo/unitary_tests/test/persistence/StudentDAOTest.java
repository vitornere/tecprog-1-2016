package test.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Student;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import exception.ClientException;

import persistence.StudentDAO;
import persistence.FactoryConnection;

public class StudentDAOTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando AlunoDAO", StudentDAO.getInstance() instanceof StudentDAO);
	}
	
	@Test
	public void testSingleton() {
		StudentDAO p = StudentDAO.getInstance();
		StudentDAO q = StudentDAO.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}
	

	@Test
	public void testIncluir() throws ClientException, SQLException {
		boolean resultado = false;
		Student aluno = new Student("Incluindo", "040.757.021-70", "098765", "9999-9999", "aluno@email");
		StudentDAO.getInstance().add(aluno);
		
		resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
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
		assertTrue("Teste de Inclus�o.", resultado);
	}
	
	@Test (expected= ClientException.class)
	public void testIncluirNulo() throws ClientException, SQLException {
		StudentDAO.getInstance().add(null);
	}
	
	@Test (expected= ClientException.class)
	public void testIncluirComMesmoCpf() throws ClientException, SQLException {
		boolean resultado = true;
		Student aluno = new Student("Incluindo", "040.757.021-70", "098765", "1111-1111", "aluno@email");
		Student aluno2 = new Student("Incluindo CPF Igual", "040.747.021-70", "987654", "2222-2222", "aluno2@email");
		StudentDAO.getInstance().add(aluno);
		try{
			StudentDAO.getInstance().add(aluno2);
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdRegister() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno.getIdRegister() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdRegister() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testIncluirComMesmaMatricula() throws ClientException, SQLException {
		boolean resultado = true;
		Student aluno = new Student("Incluindo", "040.757.021-70", "111111", "1111-1111", "aluno@email");
		Student aluno2 = new Student("Incluindo Matricula Igual", "490.491.781-20", "111111", "2222-2222", "aluno2@email");
		StudentDAO.getInstance().add(aluno);
		try{
			StudentDAO.getInstance().add(aluno2);
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdRegister() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno.getIdRegister() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdRegister() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testIncluirJaExistente() throws ClientException, SQLException {
		boolean resultado = true;
		Student aluno = new Student("Incluindo", "040.757.021-70", "58801", "3333-3333", "aluno@email");
		Student aluno2 = new Student("Incluindo", "040.757.021-70", "58801", "3333-3333", "aluno@email");
		StudentDAO.getInstance().add(aluno);
		try{
			StudentDAO.getInstance().add(aluno2);
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdRegister() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno.getIdRegister() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhonePerson() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdRegister() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	
	
	
	@Test
	public void testAlterar() throws ClientException, SQLException {
		Student a = new Student("Incluindo", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Student an = new Student("Alterando", "387.807.647-97", "098765", "(123)4567-8899", "email@Nome");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNamePerson() + "\", " +
						"\"" + a.getCpfPerson()+ "\", " +
						"\"" + a.getPhonePerson() + "\", " +
						"\"" + a.getEmailPerson() + "\", " +
						"\"" + a.getIdRegister() + "\"); ");
		
		StudentDAO.getInstance().change(a, an);
		
		boolean resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + an.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + an.getPhonePerson() + "\" and " +
				"aluno.email = \"" + an.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + an.getIdRegister() + "\";");
		boolean resultado2 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
				"aluno.email = \"" + a.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + a.getIdRegister() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + an.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + an.getPhonePerson() + "\" and " +
					"aluno.email = \"" + an.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + an.getIdRegister() + "\";");
		if(resultado2)
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
					"aluno.email = \"" + a.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + a.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", resultado == true && resultado2 == false);
	}
	
	@Test (expected= ClientException.class)
	public void testAlterarPrimeiroArgNulo() throws ClientException, SQLException {
		Student an = new Student("Alterando", "00.757.021-70", "123456", "(999)9999-9999", "aluno@email");
		StudentDAO.getInstance().change(null, an);
	}
	
	@Test (expected= ClientException.class)
	public void testAlterarSegundoArgNulo() throws ClientException, SQLException {
		Student an = new Student("Alterando", "00.757.021-70", "123456", "(999)9999-9999", "aluno@email");
		StudentDAO.getInstance().change(an, null);
	}
	@Test (expected= ClientException.class)
	public void testAlterarNaoExistente() throws ClientException, SQLException {
		boolean resultado = true;
		Student a = new Student("Incluindo", "040.757.021-70", "123456", "1111-1111", "aluno@email");
		Student an = new Student("Alterando", "490.491.781-20", "098765", "(999)9999-9999", "email@aluno");
		
		try{
			StudentDAO.getInstance().change(a, an);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + an.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + an.getPhonePerson() + "\" and " +
				"aluno.email = \"" + an.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + an.getIdRegister() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + an.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + an.getPhonePerson() + "\" and " +
					"aluno.email = \"" + an.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + an.getIdRegister() + "\";");
		}
		assertFalse("Teste de Altera��o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testAlterarParaJaExistente() throws ClientException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		Student a = new Student("Incluindo", "040.757.021-70", "058801", "9999-9999", "aluno@email");
		Student an = new Student("Incluindo", "040.757.021-70", "058801", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNamePerson() + "\", " +
						"\"" + a.getCpfPerson()+ "\", " +
						"\"" + a.getPhonePerson() + "\", " +
						"\"" + a.getEmailPerson() + "\", " +
						"\"" + a.getIdRegister() + "\"); ");
		
		try{
			StudentDAO.getInstance().change(a, an);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + an.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + an.getPhonePerson() + "\" and " +
				"aluno.email = \"" + an.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + an.getIdRegister() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
				"aluno.email = \"" + a.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + a.getIdRegister() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + an.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + an.getPhonePerson() + "\" and " +
					"aluno.email = \"" + an.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + an.getIdRegister() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
					"aluno.email = \"" + a.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + a.getIdRegister() + "\";");
		}
		assertTrue("Teste de Altera��o.", resultado == false && resultado2 == true);
	}
	@Test (expected= ClientException.class)
	public void testAlterarParaCpfExistente() throws ClientException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		boolean resultado3 = false;
		Student a = new Student("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		Student an = new Student("Incluindo Segundo", "490.491.781-20", "1234", "4444-4444", "novoAluno@email");
		Student ann = new Student("Incluindo Segundo", "040.757.021-70", "1234", "4444-4444", "novoAluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNamePerson() + "\", " +
						"\"" + a.getCpfPerson()+ "\", " +
						"\"" + a.getPhonePerson() + "\", " +
						"\"" + a.getEmailPerson() + "\", " +
						"\"" + a.getIdRegister() + "\"); ");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + an.getNamePerson() + "\", " +
				"\"" + an.getCpfPerson()+ "\", " +
				"\"" + an.getPhonePerson() + "\", " +
				"\"" + an.getEmailPerson() + "\", " +
				"\"" + an.getIdRegister() + "\"); ");
		
		try{
			StudentDAO.getInstance().change(an, ann);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + an.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + an.getPhonePerson() + "\" and " +
				"aluno.email = \"" + an.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + an.getIdRegister() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
				"aluno.email = \"" + a.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + a.getIdRegister() + "\";");
			resultado3 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + ann.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + ann.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + ann.getPhonePerson() + "\" and " +
					"aluno.email = \"" + ann.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + ann.getIdRegister() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + an.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + an.getPhonePerson() + "\" and " +
					"aluno.email = \"" + an.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + an.getIdRegister() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
					"aluno.email = \"" + a.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + a.getIdRegister() + "\";");
			if(resultado3)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"aluno.nome = \"" + ann.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + ann.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + ann.getPhonePerson() + "\" and " +
					"aluno.email = \"" + ann.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + ann.getIdRegister() + "\";");
		}
		assertTrue("Teste de Altera��o.", resultado == true && resultado2 == true && resultado3 == false);
	}
	@Test (expected= ClientException.class)
	public void testAlterarParaMatriculaExistente() throws ClientException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		boolean resultado3 = false;
		Student a = new Student("Incluindo", "040.757.021-70", "123456", "9999-99999", "aluno@email");
		Student an = new Student("Incluindo Segundo", "490.491.781-20", "0987", "5555-5555", "alunoNovo@email");
		Student ann = new Student("Incluindo Segundo", "490.491.781-20", "123456", "5555-5555", "alunoNovo@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNamePerson() + "\", " +
						"\"" + a.getCpfPerson()+ "\", " +
						"\"" + a.getPhonePerson() + "\", " +
						"\"" + a.getEmailPerson() + "\", " +
						"\"" + a.getIdRegister() + "\"); ");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + an.getNamePerson() + "\", " +
				"\"" + an.getCpfPerson()+ "\", " +
				"\"" + an.getPhonePerson() + "\", " +
				"\"" + an.getEmailPerson() + "\", " +
				"\"" + an.getIdRegister() + "\"); ");
		
		try{
			StudentDAO.getInstance().change(an, ann);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + an.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + an.getPhonePerson() + "\" and " +
				"aluno.email = \"" + an.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + an.getIdRegister() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"aluno.nome = \"" + a.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
				"aluno.email = \"" + a.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + a.getIdRegister() + "\";");
			resultado3 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + ann.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + ann.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + ann.getPhonePerson() + "\" and " +
					"aluno.email = \"" + ann.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + ann.getIdRegister() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
						"aluno.nome = \"" + an.getNamePerson() + "\" and " +
						"aluno.cpf = \"" + an.getCpfPerson() + "\" and " +
						"aluno.telefone = \"" + an.getPhonePerson() + "\" and " +
						"aluno.email = \"" + an.getEmailPerson() + "\" and " +
						"aluno.matricula = \"" + an.getIdRegister() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
					"aluno.email = \"" + a.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + a.getIdRegister() + "\";");
			if(resultado3)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + ann.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + ann.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + ann.getPhonePerson() + "\" and " +
					"aluno.email = \"" + ann.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + ann.getIdRegister() + "\";");
		}
		assertTrue("Teste de Altera��o.", resultado == true && resultado2 == true && resultado3 == false);
	}
	@Ignore // (expected= ClienteException.class)
	public void testAlterarEnvolvidoEmReserva() throws ClientException, SQLException {
		fail();
	}
	
	
	
	@Test
	public void testExcluir() throws ClientException, SQLException {
		boolean resultado = true;
		Student a = new Student("Incluindo", "040.757.021-70", "058801", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNamePerson() + "\", " +
						"\"" + a.getCpfPerson()+ "\", " +
						"\"" + a.getPhonePerson() + "\", " +
						"\"" + a.getEmailPerson() + "\", " +
						"\"" + a.getIdRegister() + "\"); ");
		
		StudentDAO.getInstance().delete(a);
		

		resultado =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
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
		
		assertFalse("Teste de Altera��o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testExcluirNulo() throws ClientException, SQLException {
		StudentDAO.getInstance().delete(null);
	}
	@Ignore // (expected= ClienteException.class)
	public void testExcluirEnvolvidoEmReserva() throws ClientException, SQLException {
		fail();
	}
	@Test (expected= ClientException.class)
	public void testExcluirNaoExistente() throws ClientException, SQLException {
		Student a = new Student("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		StudentDAO.getInstance().delete(a);
	}
	
	
	
	@Test
	public void testBuscarNome() throws ClientException, SQLException {
		Student a = new Student("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNamePerson() + "\", " +
						"\"" + a.getCpfPerson()+ "\", " +
						"\"" + a.getPhonePerson() + "\", " +
						"\"" + a.getEmailPerson() + "\", " +
						"\"" + a.getIdRegister() + "\"); ");
		
		Vector<Student> vet = StudentDAO.getInstance().searchByName("Incluindo");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
					"aluno.email = \"" + a.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + a.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarCpf() throws ClientException, SQLException {
		Student a = new Student("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNamePerson() + "\", " +
						"\"" + a.getCpfPerson()+ "\", " +
						"\"" + a.getPhonePerson() + "\", " +
						"\"" + a.getEmailPerson() + "\", " +
						"\"" + a.getIdRegister() + "\"); ");
		
		Vector<Student> vet = StudentDAO.getInstance().searchByCpf("040.757.021-70");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
					"aluno.email = \"" + a.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + a.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarMatricula() throws ClientException, SQLException {
		Student a = new Student("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getNamePerson() + "\", " +
						"\"" + a.getCpfPerson()+ "\", " +
						"\"" + a.getPhonePerson() + "\", " +
						"\"" + a.getEmailPerson() + "\", " +
						"\"" + a.getIdRegister() + "\"); ");
		
		Vector<Student> vet = StudentDAO.getInstance().searchByRegister("123456");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
					"aluno.email = \"" + a.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + a.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarTelefone() throws ClientException, SQLException {
		Student a = new Student("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + a.getNamePerson() + "\", " +
				"\"" + a.getCpfPerson()+ "\", " +
				"\"" + a.getPhonePerson() + "\", " +
				"\"" + a.getEmailPerson() + "\", " +
				"\"" + a.getIdRegister() + "\"); ");
		
		Vector<Student> vet = StudentDAO.getInstance().searchByPhone("9999-9999");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
				"aluno.email = \"" + a.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + a.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarEmail() throws ClientException, SQLException {
		Student a = new Student("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + a.getNamePerson() + "\", " +
				"\"" + a.getCpfPerson()+ "\", " +
				"\"" + a.getPhonePerson() + "\", " +
				"\"" + a.getEmailPerson() + "\", " +
				"\"" + a.getIdRegister() + "\"); ");
		
		Vector<Student> vet = StudentDAO.getInstance().searchByEmail("aluno@email");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
				"aluno.email = \"" + a.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + a.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
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

	