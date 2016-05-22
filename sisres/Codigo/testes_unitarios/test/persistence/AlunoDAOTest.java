package test.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Aluno;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import exception.ClientException;

import persistence.AlunoDAO;
import persistence.FactoryConnection;

public class AlunoDAOTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando AlunoDAO", AlunoDAO.getInstance() instanceof AlunoDAO);
	}
	
	@Test
	public void testSingleton() {
		AlunoDAO p = AlunoDAO.getInstance();
		AlunoDAO q = AlunoDAO.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}
	

	@Test
	public void testIncluir() throws ClientException, SQLException {
		boolean resultado = false;
		Aluno aluno = new Aluno("Incluindo", "040.757.021-70", "098765", "9999-9999", "aluno@email");
		AlunoDAO.getInstance().incluir(aluno);
		
		resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
		"aluno.nome = \"" + aluno.getName() + "\" and " +
		"aluno.cpf = \"" + aluno.getCpfProfessor() + "\" and " +
		"aluno.telefone = \"" + aluno.getPhoneProfessor() + "\" and " +
		"aluno.email = \"" + aluno.getEmailProfessor() + "\" and " +
		"aluno.matricula = \"" + aluno.getIdProfessor() + "\";");
		
		if(resultado){
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getName() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + aluno.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + aluno.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + aluno.getIdProfessor() + "\";");
		}
		assertTrue("Teste de Inclus�o.", resultado);
	}
	
	@Test (expected= ClientException.class)
	public void testIncluirNulo() throws ClientException, SQLException {
		AlunoDAO.getInstance().incluir(null);
	}
	
	@Test (expected= ClientException.class)
	public void testIncluirComMesmoCpf() throws ClientException, SQLException {
		boolean resultado = true;
		Aluno aluno = new Aluno("Incluindo", "040.757.021-70", "098765", "1111-1111", "aluno@email");
		Aluno aluno2 = new Aluno("Incluindo CPF Igual", "040.747.021-70", "987654", "2222-2222", "aluno2@email");
		AlunoDAO.getInstance().incluir(aluno);
		try{
			AlunoDAO.getInstance().incluir(aluno2);
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getName() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdProfessor() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getName() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + aluno.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + aluno.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + aluno.getIdProfessor() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getName() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdProfessor() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testIncluirComMesmaMatricula() throws ClientException, SQLException {
		boolean resultado = true;
		Aluno aluno = new Aluno("Incluindo", "040.757.021-70", "111111", "1111-1111", "aluno@email");
		Aluno aluno2 = new Aluno("Incluindo Matricula Igual", "490.491.781-20", "111111", "2222-2222", "aluno2@email");
		AlunoDAO.getInstance().incluir(aluno);
		try{
			AlunoDAO.getInstance().incluir(aluno2);
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getName() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdProfessor() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getName() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + aluno.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + aluno.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + aluno.getIdProfessor() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getName() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdProfessor() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testIncluirJaExistente() throws ClientException, SQLException {
		boolean resultado = true;
		Aluno aluno = new Aluno("Incluindo", "040.757.021-70", "58801", "3333-3333", "aluno@email");
		Aluno aluno2 = new Aluno("Incluindo", "040.757.021-70", "58801", "3333-3333", "aluno@email");
		AlunoDAO.getInstance().incluir(aluno);
		try{
			AlunoDAO.getInstance().incluir(aluno2);
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getName() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdProfessor() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getName() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + aluno.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + aluno.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + aluno.getIdProfessor() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + aluno2.getName() + "\" and " +
					"aluno.cpf = \"" + aluno2.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + aluno2.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + aluno2.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + aluno2.getIdProfessor() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	
	
	
	@Test
	public void testAlterar() throws ClientException, SQLException {
		boolean resultado = false;
		Aluno a = new Aluno("Incluindo", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Aluno an = new Aluno("Alterando", "387.807.647-97", "098765", "(123)4567-8899", "email@Nome");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getName() + "\", " +
						"\"" + a.getCpfProfessor()+ "\", " +
						"\"" + a.getPhoneProfessor() + "\", " +
						"\"" + a.getEmailProfessor() + "\", " +
						"\"" + a.getIdProfessor() + "\"); ");
		
		AlunoDAO.getInstance().alterar(a, an);
		
		resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getName() + "\" and " +
				"aluno.cpf = \"" + an.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + an.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + an.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + an.getIdProfessor() + "\";");
		boolean resultado2 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getName() + "\" and " +
				"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + a.getIdProfessor() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getName() + "\" and " +
					"aluno.cpf = \"" + an.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + an.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + an.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + an.getIdProfessor() + "\";");
		if(resultado2)
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getName() + "\" and " +
					"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + a.getIdProfessor() + "\";");
		
		assertTrue("Teste de Altera��o.", resultado == true && resultado2 == false);
	}
	
	@Test (expected= ClientException.class)
	public void testAlterarPrimeiroArgNulo() throws ClientException, SQLException {
		Aluno an = new Aluno("Alterando", "00.757.021-70", "123456", "(999)9999-9999", "aluno@email");
		AlunoDAO.getInstance().alterar(null, an);
	}
	
	@Test (expected= ClientException.class)
	public void testAlterarSegundoArgNulo() throws ClientException, SQLException {
		Aluno an = new Aluno("Alterando", "00.757.021-70", "123456", "(999)9999-9999", "aluno@email");
		AlunoDAO.getInstance().alterar(an, null);
	}
	@Test (expected= ClientException.class)
	public void testAlterarNaoExistente() throws ClientException, SQLException {
		boolean resultado = true;
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "1111-1111", "aluno@email");
		Aluno an = new Aluno("Alterando", "490.491.781-20", "098765", "(999)9999-9999", "email@aluno");
		
		try{
			AlunoDAO.getInstance().alterar(a, an);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getName() + "\" and " +
				"aluno.cpf = \"" + an.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + an.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + an.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + an.getIdProfessor() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getName() + "\" and " +
					"aluno.cpf = \"" + an.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + an.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + an.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + an.getIdProfessor() + "\";");
		}
		assertFalse("Teste de Altera��o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testAlterarParaJaExistente() throws ClientException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "058801", "9999-9999", "aluno@email");
		Aluno an = new Aluno("Incluindo", "040.757.021-70", "058801", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getName() + "\", " +
						"\"" + a.getCpfProfessor()+ "\", " +
						"\"" + a.getPhoneProfessor() + "\", " +
						"\"" + a.getEmailProfessor() + "\", " +
						"\"" + a.getIdProfessor() + "\"); ");
		
		try{
			AlunoDAO.getInstance().alterar(a, an);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getName() + "\" and " +
				"aluno.cpf = \"" + an.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + an.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + an.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + an.getIdProfessor() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getName() + "\" and " +
				"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + a.getIdProfessor() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getName() + "\" and " +
					"aluno.cpf = \"" + an.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + an.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + an.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + an.getIdProfessor() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getName() + "\" and " +
					"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + a.getIdProfessor() + "\";");
		}
		assertTrue("Teste de Altera��o.", resultado == false && resultado2 == true);
	}
	@Test (expected= ClientException.class)
	public void testAlterarParaCpfExistente() throws ClientException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		boolean resultado3 = false;
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		Aluno an = new Aluno("Incluindo Segundo", "490.491.781-20", "1234", "4444-4444", "novoAluno@email");
		Aluno ann = new Aluno("Incluindo Segundo", "040.757.021-70", "1234", "4444-4444", "novoAluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getName() + "\", " +
						"\"" + a.getCpfProfessor()+ "\", " +
						"\"" + a.getPhoneProfessor() + "\", " +
						"\"" + a.getEmailProfessor() + "\", " +
						"\"" + a.getIdProfessor() + "\"); ");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + an.getName() + "\", " +
				"\"" + an.getCpfProfessor()+ "\", " +
				"\"" + an.getPhoneProfessor() + "\", " +
				"\"" + an.getEmailProfessor() + "\", " +
				"\"" + an.getIdProfessor() + "\"); ");
		
		try{
			AlunoDAO.getInstance().alterar(an, ann);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getName() + "\" and " +
				"aluno.cpf = \"" + an.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + an.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + an.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + an.getIdProfessor() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getName() + "\" and " +
				"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + a.getIdProfessor() + "\";");
			resultado3 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + ann.getName() + "\" and " +
					"aluno.cpf = \"" + ann.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + ann.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + ann.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + ann.getIdProfessor() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + an.getName() + "\" and " +
					"aluno.cpf = \"" + an.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + an.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + an.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + an.getIdProfessor() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getName() + "\" and " +
					"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + a.getIdProfessor() + "\";");
			if(resultado3)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"aluno.nome = \"" + ann.getName() + "\" and " +
					"aluno.cpf = \"" + ann.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + ann.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + ann.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + ann.getIdProfessor() + "\";");
		}
		assertTrue("Teste de Altera��o.", resultado == true && resultado2 == true && resultado3 == false);
	}
	@Test (expected= ClientException.class)
	public void testAlterarParaMatriculaExistente() throws ClientException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		boolean resultado3 = false;
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-99999", "aluno@email");
		Aluno an = new Aluno("Incluindo Segundo", "490.491.781-20", "0987", "5555-5555", "alunoNovo@email");
		Aluno ann = new Aluno("Incluindo Segundo", "490.491.781-20", "123456", "5555-5555", "alunoNovo@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getName() + "\", " +
						"\"" + a.getCpfProfessor()+ "\", " +
						"\"" + a.getPhoneProfessor() + "\", " +
						"\"" + a.getEmailProfessor() + "\", " +
						"\"" + a.getIdProfessor() + "\"); ");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + an.getName() + "\", " +
				"\"" + an.getCpfProfessor()+ "\", " +
				"\"" + an.getPhoneProfessor() + "\", " +
				"\"" + an.getEmailProfessor() + "\", " +
				"\"" + an.getIdProfessor() + "\"); ");
		
		try{
			AlunoDAO.getInstance().alterar(an, ann);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + an.getName() + "\" and " +
				"aluno.cpf = \"" + an.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + an.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + an.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + an.getIdProfessor() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"aluno.nome = \"" + a.getName() + "\" and " +
				"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + a.getIdProfessor() + "\";");
			resultado3 =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + ann.getName() + "\" and " +
					"aluno.cpf = \"" + ann.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + ann.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + ann.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + ann.getIdProfessor() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
						"aluno.nome = \"" + an.getName() + "\" and " +
						"aluno.cpf = \"" + an.getCpfProfessor() + "\" and " +
						"aluno.telefone = \"" + an.getPhoneProfessor() + "\" and " +
						"aluno.email = \"" + an.getEmailProfessor() + "\" and " +
						"aluno.matricula = \"" + an.getIdProfessor() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getName() + "\" and " +
					"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + a.getIdProfessor() + "\";");
			if(resultado3)
				this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + ann.getName() + "\" and " +
					"aluno.cpf = \"" + ann.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + ann.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + ann.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + ann.getIdProfessor() + "\";");
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
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "058801", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getName() + "\", " +
						"\"" + a.getCpfProfessor()+ "\", " +
						"\"" + a.getPhoneProfessor() + "\", " +
						"\"" + a.getEmailProfessor() + "\", " +
						"\"" + a.getIdProfessor() + "\"); ");
		
		AlunoDAO.getInstance().excluir(a);
		

		resultado =  this.estaNoBanco("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + a.getName() + "\" and " +
				"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + a.getIdProfessor() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getName() + "\" and " +
					"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + a.getIdProfessor() + "\";");
		
		assertFalse("Teste de Altera��o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testExcluirNulo() throws ClientException, SQLException {
		AlunoDAO.getInstance().excluir(null);
	}
	@Ignore // (expected= ClienteException.class)
	public void testExcluirEnvolvidoEmReserva() throws ClientException, SQLException {
		fail();
	}
	@Test (expected= ClientException.class)
	public void testExcluirNaoExistente() throws ClientException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		AlunoDAO.getInstance().excluir(a);
	}
	
	
	
	@Test
	public void testBuscarNome() throws ClientException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getName() + "\", " +
						"\"" + a.getCpfProfessor()+ "\", " +
						"\"" + a.getPhoneProfessor() + "\", " +
						"\"" + a.getEmailProfessor() + "\", " +
						"\"" + a.getIdProfessor() + "\"); ");
		
		Vector<Aluno> vet = AlunoDAO.getInstance().buscarNome("Incluindo");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getName() + "\" and " +
					"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + a.getIdProfessor() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarCpf() throws ClientException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getName() + "\", " +
						"\"" + a.getCpfProfessor()+ "\", " +
						"\"" + a.getPhoneProfessor() + "\", " +
						"\"" + a.getEmailProfessor() + "\", " +
						"\"" + a.getIdProfessor() + "\"); ");
		
		Vector<Aluno> vet = AlunoDAO.getInstance().buscarCpf("040.757.021-70");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getName() + "\" and " +
					"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + a.getIdProfessor() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarMatricula() throws ClientException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
						"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + a.getName() + "\", " +
						"\"" + a.getCpfProfessor()+ "\", " +
						"\"" + a.getPhoneProfessor() + "\", " +
						"\"" + a.getEmailProfessor() + "\", " +
						"\"" + a.getIdProfessor() + "\"); ");
		
		Vector<Aluno> vet = AlunoDAO.getInstance().buscarMatricula("123456");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
					"aluno.nome = \"" + a.getName() + "\" and " +
					"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
					"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
					"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
					"aluno.matricula = \"" + a.getIdProfessor() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarTelefone() throws ClientException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + a.getName() + "\", " +
				"\"" + a.getCpfProfessor()+ "\", " +
				"\"" + a.getPhoneProfessor() + "\", " +
				"\"" + a.getEmailProfessor() + "\", " +
				"\"" + a.getIdProfessor() + "\"); ");
		
		Vector<Aluno> vet = AlunoDAO.getInstance().buscarTelefone("9999-9999");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
				"aluno.nome = \"" + a.getName() + "\" and " +
				"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + a.getIdProfessor() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarEmail() throws ClientException, SQLException {
		Aluno a = new Aluno("Incluindo", "040.757.021-70", "123456", "9999-9999", "aluno@email");
		this.executaNoBanco("INSERT INTO " +
				"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + a.getName() + "\", " +
				"\"" + a.getCpfProfessor()+ "\", " +
				"\"" + a.getPhoneProfessor() + "\", " +
				"\"" + a.getEmailProfessor() + "\", " +
				"\"" + a.getIdProfessor() + "\"); ");
		
		Vector<Aluno> vet = AlunoDAO.getInstance().buscarEmail("aluno@email");

		this.executaNoBanco("DELETE FROM aluno WHERE " +
				"aluno.nome = \"" + a.getName() + "\" and " +
				"aluno.cpf = \"" + a.getCpfProfessor() + "\" and " +
				"aluno.telefone = \"" + a.getPhoneProfessor() + "\" and " +
				"aluno.email = \"" + a.getEmailProfessor() + "\" and " +
				"aluno.matricula = \"" + a.getIdProfessor() + "\";");
		
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

	