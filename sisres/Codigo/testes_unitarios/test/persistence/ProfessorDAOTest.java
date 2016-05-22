package test.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Professor;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import exception.ClientException;

import persistence.FactoryConnection;
import persistence.ProfessorDAO;

public class ProfessorDAOTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando ProfessorDAO", ProfessorDAO.getNewProfessor() instanceof ProfessorDAO);
	}
	
	@Test
	public void testSingleton() {
		ProfessorDAO p = ProfessorDAO.getNewProfessor();
		ProfessorDAO q = ProfessorDAO.getNewProfessor();
		assertSame("Testando o Padrao Singleton", p, q);
	}
	
	
	
	@Test
	public void testIncluir() throws ClientException, SQLException {
		boolean resultado = false;
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ProfessorDAO.getNewProfessor().include(prof);
		
		resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
		"professor.nome = \"" + prof.getName() + "\" and " +
		"professor.cpf = \"" + prof.getCpfProfessor() + "\" and " +
		"professor.telefone = \"" + prof.getPhoneProfessor() + "\" and " +
		"professor.email = \"" + prof.getEmailProfessor() + "\" and " +
		"professor.matricula = \"" + prof.getIdProfessor() + "\";");
		
		if(resultado){
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof.getName() + "\" and " +
					"professor.cpf = \"" + prof.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof.getIdProfessor() + "\";");
		}
		assertTrue("Teste de Inclus�o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testIncluirNulo() throws ClientException, SQLException {
		ProfessorDAO.getNewProfessor().include(null);
	}
	@Test (expected= ClientException.class)
	public void testIncluirComMesmoCpf() throws ClientException, SQLException {
		boolean resultado = true;
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor prof2 = new Professor("Nome para Incluir Segundo", "868.563.327-34", "0987", "5678-5555", "");
		ProfessorDAO.getNewProfessor().include(prof);
		try{
			ProfessorDAO.getNewProfessor().include(prof2);
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof2.getName() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof2.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof2.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof2.getIdProfessor() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof.getName() + "\" and " +
					"professor.cpf = \"" + prof.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof.getIdProfessor() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
					"professor.nome = \"" + prof2.getName() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof2.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof2.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof2.getIdProfessor() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testIncluirComMesmaMatricula() throws ClientException, SQLException {
		boolean resultado = true;
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor prof2 = new Professor("Nome para Incluir Segundo", "387.807.647-97", "123456", "5678-5555", "");
		ProfessorDAO.getNewProfessor().include(prof);
		try{
			ProfessorDAO.getNewProfessor().include(prof2);
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof2.getName() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof2.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof2.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof2.getIdProfessor() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof.getName() + "\" and " +
					"professor.cpf = \"" + prof.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof.getIdProfessor() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
					"professor.nome = \"" + prof2.getName() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof2.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof2.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof2.getIdProfessor() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testIncluirJaExistente() throws ClientException, SQLException {
		boolean resultado = true;
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor prof2 = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ProfessorDAO.getNewProfessor().include(prof);
		try{
			ProfessorDAO.getNewProfessor().include(prof2);
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof2.getName() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof2.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof2.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof2.getIdProfessor() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof.getName() + "\" and " +
					"professor.cpf = \"" + prof.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof.getIdProfessor() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
					"professor.nome = \"" + prof2.getName() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof2.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof2.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof2.getIdProfessor() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	
	
	
	@Test
	public void testAlterar() throws ClientException, SQLException {
		boolean resultado = false;
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor pn = new Professor("Nome para Alterar", "387.807.647-97", "098765", "(123)4567-8899", "email@Nome");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getName() + "\", " +
						"\"" + p.getCpfProfessor()+ "\", " +
						"\"" + p.getPhoneProfessor() + "\", " +
						"\"" + p.getEmailProfessor() + "\", " +
						"\"" + p.getIdProfessor() + "\"); ");
		
		ProfessorDAO.getNewProfessor().update(p, pn);
		
		resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + pn.getName() + "\" and " +
				"professor.cpf = \"" + pn.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + pn.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + pn.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + pn.getIdProfessor() + "\";");
		boolean resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + p.getName() + "\" and " +
				"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + p.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + p.getIdProfessor() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pn.getName() + "\" and " +
					"professor.cpf = \"" + pn.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + pn.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + pn.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + pn.getIdProfessor() + "\";");
		if(resultado2)
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getName() + "\" and " +
					"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + p.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + p.getIdProfessor() + "\";");
		
		assertTrue("Teste de Altera��o.", resultado == true && resultado2 == false);
	}
	@Test (expected= ClientException.class)
	public void testAlterarPrimeiroArgNulo() throws ClientException, SQLException {
		Professor pn = new Professor("Nome para Alterar", "868.563.327-34", "098765", "(123)4567-8899", "email@Nome");
		ProfessorDAO.getNewProfessor().update(null, pn);
	}
	@Test (expected= ClientException.class)
	public void testAlterarSegundoArgNulo() throws ClientException, SQLException {
		Professor pn = new Professor("Nome para Alterar", "868.563.327-34", "098765", "(123)4567-8899", "email@Nome");
		ProfessorDAO.getNewProfessor().update(pn, null);
	}
	@Test (expected= ClientException.class)
	public void testAlterarNaoExistente() throws ClientException, SQLException {
		boolean resultado = true;
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor pn = new Professor("Nome para Alterar", "387.807.647-97", "098765", "(123)4567-8899", "email@Nome");
		
		try{
			ProfessorDAO.getNewProfessor().update(p, pn);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + pn.getName() + "\" and " +
				"professor.cpf = \"" + pn.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + pn.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + pn.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + pn.getIdProfessor() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pn.getName() + "\" and " +
					"professor.cpf = \"" + pn.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + pn.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + pn.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + pn.getIdProfessor() + "\";");
		}
		assertFalse("Teste de Altera��o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testAlterarParaJaExistente() throws ClientException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor pn = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getName() + "\", " +
						"\"" + p.getCpfProfessor()+ "\", " +
						"\"" + p.getPhoneProfessor() + "\", " +
						"\"" + p.getEmailProfessor() + "\", " +
						"\"" + p.getIdProfessor() + "\"); ");
		
		try{
			ProfessorDAO.getNewProfessor().update(p, pn);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + pn.getName() + "\" and " +
				"professor.cpf = \"" + pn.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + pn.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + pn.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + pn.getIdProfessor() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + p.getName() + "\" and " +
				"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + p.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + p.getIdProfessor() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pn.getName() + "\" and " +
					"professor.cpf = \"" + pn.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + pn.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + pn.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + pn.getIdProfessor() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getName() + "\" and " +
					"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + p.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + p.getIdProfessor() + "\";");
		}
		assertTrue("Teste de Altera��o.", resultado == false && resultado2 == true);
	}
	@Test (expected= ClientException.class)
	public void testAlterarParaCpfExistente() throws ClientException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		boolean resultado3 = false;
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor pn = new Professor("Nome para Incluir Segundo", "387.807.647-97", "0987", "5555-5678", "Ne@email");
		Professor pnn = new Professor("Nome para Incluir Segundo", "868.563.327-34", "0987", "5555-5678", "Ne@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getName() + "\", " +
						"\"" + p.getCpfProfessor()+ "\", " +
						"\"" + p.getPhoneProfessor() + "\", " +
						"\"" + p.getEmailProfessor() + "\", " +
						"\"" + p.getIdProfessor() + "\"); ");
		this.executaNoBanco("INSERT INTO " +
				"professor (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + pn.getName() + "\", " +
				"\"" + pn.getCpfProfessor()+ "\", " +
				"\"" + pn.getPhoneProfessor() + "\", " +
				"\"" + pn.getEmailProfessor() + "\", " +
				"\"" + pn.getIdProfessor() + "\"); ");
		
		try{
			ProfessorDAO.getNewProfessor().update(pn, pnn);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + pn.getName() + "\" and " +
				"professor.cpf = \"" + pn.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + pn.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + pn.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + pn.getIdProfessor() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + p.getName() + "\" and " +
				"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + p.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + p.getIdProfessor() + "\";");
			resultado3 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
					"professor.nome = \"" + pnn.getName() + "\" and " +
					"professor.cpf = \"" + pnn.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + pnn.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + pnn.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + pnn.getIdProfessor() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pn.getName() + "\" and " +
					"professor.cpf = \"" + pn.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + pn.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + pn.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + pn.getIdProfessor() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getName() + "\" and " +
					"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + p.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + p.getIdProfessor() + "\";");
			if(resultado3)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pnn.getName() + "\" and " +
					"professor.cpf = \"" + pnn.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + pnn.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + pnn.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + pnn.getIdProfessor() + "\";");
		}
		assertTrue("Teste de Altera��o.", resultado == true && resultado2 == true && resultado3 == false);
	}
	@Test (expected= ClientException.class)
	public void testAlterarParaMatriculaExistente() throws ClientException, SQLException {
		boolean resultado = true;
		boolean resultado2 = false;
		boolean resultado3 = false;
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor pn = new Professor("Nome para Incluir Segundo", "387.807.647-97", "0987", "5555-5678", "Ne@email");
		Professor pnn = new Professor("Nome para Incluir Segundo", "387.807.647-97", "123456", "5555-5678", "Ne@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getName() + "\", " +
						"\"" + p.getCpfProfessor()+ "\", " +
						"\"" + p.getPhoneProfessor() + "\", " +
						"\"" + p.getEmailProfessor() + "\", " +
						"\"" + p.getIdProfessor() + "\"); ");
		this.executaNoBanco("INSERT INTO " +
				"professor (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + pn.getName() + "\", " +
				"\"" + pn.getCpfProfessor()+ "\", " +
				"\"" + pn.getPhoneProfessor() + "\", " +
				"\"" + pn.getEmailProfessor() + "\", " +
				"\"" + pn.getIdProfessor() + "\"); ");
		
		try{
			ProfessorDAO.getNewProfessor().update(pn, pnn);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + pn.getName() + "\" and " +
				"professor.cpf = \"" + pn.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + pn.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + pn.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + pn.getIdProfessor() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + p.getName() + "\" and " +
				"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + p.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + p.getIdProfessor() + "\";");
			resultado3 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
					"professor.nome = \"" + pnn.getName() + "\" and " +
					"professor.cpf = \"" + pnn.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + pnn.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + pnn.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + pnn.getIdProfessor() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pn.getName() + "\" and " +
					"professor.cpf = \"" + pn.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + pn.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + pn.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + pn.getIdProfessor() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getName() + "\" and " +
					"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + p.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + p.getIdProfessor() + "\";");
			if(resultado3)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pnn.getName() + "\" and " +
					"professor.cpf = \"" + pnn.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + pnn.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + pnn.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + pnn.getIdProfessor() + "\";");
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
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getName() + "\", " +
						"\"" + p.getCpfProfessor()+ "\", " +
						"\"" + p.getPhoneProfessor() + "\", " +
						"\"" + p.getEmailProfessor() + "\", " +
						"\"" + p.getIdProfessor() + "\"); ");
		
		ProfessorDAO.getNewProfessor().delete(p);
		

		resultado =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + p.getName() + "\" and " +
				"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + p.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + p.getIdProfessor() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getName() + "\" and " +
					"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + p.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + p.getIdProfessor() + "\";");
		
		assertFalse("Teste de Altera��o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testExcluirNulo() throws ClientException, SQLException {
		ProfessorDAO.getNewProfessor().delete(null);
	}
	@Ignore //(expected= ClienteException.class)
	public void testExcluirEnvolvidoEmReserva() throws ClientException, SQLException {
		fail();
	}
	@Test (expected= ClientException.class)
	public void testExcluirNaoExistente() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ProfessorDAO.getNewProfessor().delete(p);
	}
	
	
	
	@Test
	public void testBuscarNome() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getName() + "\", " +
						"\"" + p.getCpfProfessor()+ "\", " +
						"\"" + p.getPhoneProfessor() + "\", " +
						"\"" + p.getEmailProfessor() + "\", " +
						"\"" + p.getIdProfessor() + "\"); ");
		
		Vector<Professor> vet = ProfessorDAO.getNewProfessor().searchNameProfessor("Nome para Incluir");

		this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getName() + "\" and " +
					"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + p.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + p.getIdProfessor() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarCpf() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getName() + "\", " +
						"\"" + p.getCpfProfessor()+ "\", " +
						"\"" + p.getPhoneProfessor() + "\", " +
						"\"" + p.getEmailProfessor() + "\", " +
						"\"" + p.getIdProfessor() + "\"); ");
		
		Vector<Professor> vet = ProfessorDAO.getNewProfessor().searchCpfProfessor("868.563.327-34");

		this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getName() + "\" and " +
					"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + p.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + p.getIdProfessor() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarMatricula() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getName() + "\", " +
						"\"" + p.getCpfProfessor()+ "\", " +
						"\"" + p.getPhoneProfessor() + "\", " +
						"\"" + p.getEmailProfessor() + "\", " +
						"\"" + p.getIdProfessor() + "\"); ");
		
		Vector<Professor> vet = ProfessorDAO.getNewProfessor().searchIdProfessor("123456");

		this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getName() + "\" and " +
					"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + p.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + p.getIdProfessor() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarTelefone() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getName() + "\", " +
						"\"" + p.getCpfProfessor()+ "\", " +
						"\"" + p.getPhoneProfessor() + "\", " +
						"\"" + p.getEmailProfessor() + "\", " +
						"\"" + p.getIdProfessor() + "\"); ");
		
		Vector<Professor> vet = ProfessorDAO.getNewProfessor().searchPhoneProfessor("1234-5678");

		this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getName() + "\" and " +
					"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + p.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + p.getIdProfessor() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarEmail() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getName() + "\", " +
						"\"" + p.getCpfProfessor()+ "\", " +
						"\"" + p.getPhoneProfessor() + "\", " +
						"\"" + p.getEmailProfessor() + "\", " +
						"\"" + p.getIdProfessor() + "\"); ");
		
		Vector<Professor> vet = ProfessorDAO.getNewProfessor().searchEmailProfessor("Nome@email");

		this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getName() + "\" and " +
					"professor.cpf = \"" + p.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + p.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + p.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + p.getIdProfessor() + "\";");
		
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
