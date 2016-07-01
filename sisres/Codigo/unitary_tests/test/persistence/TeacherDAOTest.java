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
import persistence.TeacherDAO;

public class TeacherDAOTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando TeacherDAO", TeacherDAO.getInstance() instanceof TeacherDAO);
	}
	
	@Test
	public void testSingleton() {
		TeacherDAO p = TeacherDAO.getInstance();
		TeacherDAO q = TeacherDAO.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}
	
	
	
	@Test
	public void testAdd() throws ClientException, SQLException {
		boolean resultado = false;
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		TeacherDAO.getInstance().add(prof);
		
		resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
		"professor.nome = \"" + prof.getNamePerson() + "\" and " +
		"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
		"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
		"professor.email = \"" + prof.getEmailPerson() + "\" and " +
		"professor.matricula = \"" + prof.getIdRegister() + "\";");
		
		if(resultado){
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof.getIdRegister() + "\";");
		}
		assertTrue("Teste de Inclus�o.", resultado);
	}
	
	@Test (expected= ClientException.class)
	public void testAddNull() throws ClientException, SQLException {
		TeacherDAO.getInstance().add(null);
	}
	
	@Test (expected= ClientException.class)
	public void testAddNullName() throws ClientException, SQLException {
		Professor prof = new Professor(null, "868.563.327-34", "123456", "1234-5678", "Nome@email");
		TeacherDAO.getInstance().add(prof);
	}

	
	@Test (expected= ClientException.class)
	public void testAddEmptyName() throws ClientException, SQLException {
		Professor prof = new Professor("", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		TeacherDAO.getInstance().add(prof);
	}
	
	@Test (expected= ClientException.class)
	public void testIncluirComMesmoCpf() throws ClientException, SQLException {
		boolean resultado = true;
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor prof2 = new Professor("Nome para Incluir Segundo", "868.563.327-34", "0987", "5678-5555", "");
		TeacherDAO.getInstance().add(prof);
		try{
			TeacherDAO.getInstance().add(prof2);
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof2.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof2.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof2.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof2.getIdRegister() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof.getIdRegister() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
					"professor.nome = \"" + prof2.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof2.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof2.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof2.getIdRegister() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	
	@Test (expected= ClientException.class)
	public void testIncluirComMesmaMatricula() throws ClientException, SQLException {
		boolean resultado = true;
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor prof2 = new Professor("Nome para Incluir Segundo", "387.807.647-97", "123456", "5678-5555", "");
		TeacherDAO.getInstance().add(prof);
		try{
			TeacherDAO.getInstance().add(prof2);
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof2.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof2.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof2.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof2.getIdRegister() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof.getIdRegister() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
					"professor.nome = \"" + prof2.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof2.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof2.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof2.getIdRegister() + "\";");
		}
		
		assertFalse("Teste de Inclus�o.", resultado);
	}
	
	@Test (expected= ClientException.class)
	public void testIncluirJaExistente() throws ClientException, SQLException {
		boolean resultado = true;
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor prof2 = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		TeacherDAO.getInstance().add(prof);
		try{
			TeacherDAO.getInstance().add(prof2);
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof2.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof2.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof2.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof2.getIdRegister() + "\";");
			
		} finally {
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof.getIdRegister() + "\";");
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
					"professor.nome = \"" + prof2.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof2.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof2.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof2.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof2.getIdRegister() + "\";");
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
						"\"" + p.getNamePerson() + "\", " +
						"\"" + p.getCpfPerson()+ "\", " +
						"\"" + p.getPhonePerson() + "\", " +
						"\"" + p.getEmailPerson() + "\", " +
						"\"" + p.getIdRegister() + "\"); ");
		
		TeacherDAO.getInstance().change(p, pn);
		
		resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + pn.getNamePerson() + "\" and " +
				"professor.cpf = \"" + pn.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + pn.getPhonePerson() + "\" and " +
				"professor.email = \"" + pn.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + pn.getIdRegister() + "\";");
		boolean resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + p.getNamePerson() + "\" and " +
				"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
				"professor.email = \"" + p.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + p.getIdRegister() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pn.getNamePerson() + "\" and " +
					"professor.cpf = \"" + pn.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + pn.getPhonePerson() + "\" and " +
					"professor.email = \"" + pn.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + pn.getIdRegister() + "\";");
		if(resultado2)
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getNamePerson() + "\" and " +
					"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
					"professor.email = \"" + p.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + p.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", resultado == true && resultado2 == false);
	}
	@Test (expected= ClientException.class)
	public void testAlterarPrimeiroArgNulo() throws ClientException, SQLException {
		Professor pn = new Professor("Nome para Alterar", "868.563.327-34", "098765", "(123)4567-8899", "email@Nome");
		TeacherDAO.getInstance().change(null, pn);
	}
	@Test (expected= ClientException.class)
	public void testAlterarSegundoArgNulo() throws ClientException, SQLException {
		Professor pn = new Professor("Nome para Alterar", "868.563.327-34", "098765", "(123)4567-8899", "email@Nome");
		TeacherDAO.getInstance().change(pn, null);
	}
	@Test (expected= ClientException.class)
	public void testAlterarNaoExistente() throws ClientException, SQLException {
		boolean resultado = true;
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor pn = new Professor("Nome para Alterar", "387.807.647-97", "098765", "(123)4567-8899", "email@Nome");
		
		try{
			TeacherDAO.getInstance().change(p, pn);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + pn.getNamePerson() + "\" and " +
				"professor.cpf = \"" + pn.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + pn.getPhonePerson() + "\" and " +
				"professor.email = \"" + pn.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + pn.getIdRegister() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pn.getNamePerson() + "\" and " +
					"professor.cpf = \"" + pn.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + pn.getPhonePerson() + "\" and " +
					"professor.email = \"" + pn.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + pn.getIdRegister() + "\";");
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
						"\"" + p.getNamePerson() + "\", " +
						"\"" + p.getCpfPerson()+ "\", " +
						"\"" + p.getPhonePerson() + "\", " +
						"\"" + p.getEmailPerson() + "\", " +
						"\"" + p.getIdRegister() + "\"); ");
		
		try{
			TeacherDAO.getInstance().change(p, pn);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + pn.getNamePerson() + "\" and " +
				"professor.cpf = \"" + pn.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + pn.getPhonePerson() + "\" and " +
				"professor.email = \"" + pn.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + pn.getIdRegister() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + p.getNamePerson() + "\" and " +
				"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
				"professor.email = \"" + p.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + p.getIdRegister() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pn.getNamePerson() + "\" and " +
					"professor.cpf = \"" + pn.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + pn.getPhonePerson() + "\" and " +
					"professor.email = \"" + pn.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + pn.getIdRegister() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getNamePerson() + "\" and " +
					"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
					"professor.email = \"" + p.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + p.getIdRegister() + "\";");
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
						"\"" + p.getNamePerson() + "\", " +
						"\"" + p.getCpfPerson()+ "\", " +
						"\"" + p.getPhonePerson() + "\", " +
						"\"" + p.getEmailPerson() + "\", " +
						"\"" + p.getIdRegister() + "\"); ");
		this.executaNoBanco("INSERT INTO " +
				"professor (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + pn.getNamePerson() + "\", " +
				"\"" + pn.getCpfPerson()+ "\", " +
				"\"" + pn.getPhonePerson() + "\", " +
				"\"" + pn.getEmailPerson() + "\", " +
				"\"" + pn.getIdRegister() + "\"); ");
		
		try{
			TeacherDAO.getInstance().change(pn, pnn);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + pn.getNamePerson() + "\" and " +
				"professor.cpf = \"" + pn.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + pn.getPhonePerson() + "\" and " +
				"professor.email = \"" + pn.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + pn.getIdRegister() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + p.getNamePerson() + "\" and " +
				"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
				"professor.email = \"" + p.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + p.getIdRegister() + "\";");
			resultado3 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
					"professor.nome = \"" + pnn.getNamePerson() + "\" and " +
					"professor.cpf = \"" + pnn.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + pnn.getPhonePerson() + "\" and " +
					"professor.email = \"" + pnn.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + pnn.getIdRegister() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pn.getNamePerson() + "\" and " +
					"professor.cpf = \"" + pn.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + pn.getPhonePerson() + "\" and " +
					"professor.email = \"" + pn.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + pn.getIdRegister() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getNamePerson() + "\" and " +
					"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
					"professor.email = \"" + p.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + p.getIdRegister() + "\";");
			if(resultado3)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pnn.getNamePerson() + "\" and " +
					"professor.cpf = \"" + pnn.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + pnn.getPhonePerson() + "\" and " +
					"professor.email = \"" + pnn.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + pnn.getIdRegister() + "\";");
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
						"\"" + p.getNamePerson() + "\", " +
						"\"" + p.getCpfPerson()+ "\", " +
						"\"" + p.getPhonePerson() + "\", " +
						"\"" + p.getEmailPerson() + "\", " +
						"\"" + p.getIdRegister() + "\"); ");
		this.executaNoBanco("INSERT INTO " +
				"professor (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + pn.getNamePerson() + "\", " +
				"\"" + pn.getCpfPerson()+ "\", " +
				"\"" + pn.getPhonePerson() + "\", " +
				"\"" + pn.getEmailPerson() + "\", " +
				"\"" + pn.getIdRegister() + "\"); ");
		
		try{
			TeacherDAO.getInstance().change(pn, pnn);
		} finally {
			resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + pn.getNamePerson() + "\" and " +
				"professor.cpf = \"" + pn.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + pn.getPhonePerson() + "\" and " +
				"professor.email = \"" + pn.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + pn.getIdRegister() + "\";");
			resultado2 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + p.getNamePerson() + "\" and " +
				"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
				"professor.email = \"" + p.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + p.getIdRegister() + "\";");
			resultado3 =  this.estaNoBanco("SELECT * FROM professor WHERE " +
					"professor.nome = \"" + pnn.getNamePerson() + "\" and " +
					"professor.cpf = \"" + pnn.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + pnn.getPhonePerson() + "\" and " +
					"professor.email = \"" + pnn.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + pnn.getIdRegister() + "\";");
			if(resultado)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pn.getNamePerson() + "\" and " +
					"professor.cpf = \"" + pn.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + pn.getPhonePerson() + "\" and " +
					"professor.email = \"" + pn.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + pn.getIdRegister() + "\";");
			if(resultado2)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getNamePerson() + "\" and " +
					"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
					"professor.email = \"" + p.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + p.getIdRegister() + "\";");
			if(resultado3)
				this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + pnn.getNamePerson() + "\" and " +
					"professor.cpf = \"" + pnn.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + pnn.getPhonePerson() + "\" and " +
					"professor.email = \"" + pnn.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + pnn.getIdRegister() + "\";");
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
						"\"" + p.getNamePerson() + "\", " +
						"\"" + p.getCpfPerson()+ "\", " +
						"\"" + p.getPhonePerson() + "\", " +
						"\"" + p.getEmailPerson() + "\", " +
						"\"" + p.getIdRegister() + "\"); ");
		
		TeacherDAO.getInstance().delete(p);
		

		resultado =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + p.getNamePerson() + "\" and " +
				"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
				"professor.email = \"" + p.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + p.getIdRegister() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getNamePerson() + "\" and " +
					"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
					"professor.email = \"" + p.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + p.getIdRegister() + "\";");
		
		assertFalse("Teste de Altera��o.", resultado);
	}
	@Test (expected= ClientException.class)
	public void testExcluirNulo() throws ClientException, SQLException {
		TeacherDAO.getInstance().delete(null);
	}
	@Ignore //(expected= ClienteException.class)
	public void testExcluirEnvolvidoEmReserva() throws ClientException, SQLException {
		fail();
	}
	@Test (expected= ClientException.class)
	public void testExcluirNaoExistente() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		TeacherDAO.getInstance().delete(p);
	}
	
	
	
	@Test
	public void testBuscarNome() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getNamePerson() + "\", " +
						"\"" + p.getCpfPerson()+ "\", " +
						"\"" + p.getPhonePerson() + "\", " +
						"\"" + p.getEmailPerson() + "\", " +
						"\"" + p.getIdRegister() + "\"); ");
		
		Vector<Professor> vet = TeacherDAO.getInstance().searchByName("Nome para Incluir");

		this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getNamePerson() + "\" and " +
					"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
					"professor.email = \"" + p.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + p.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarCpf() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getNamePerson() + "\", " +
						"\"" + p.getCpfPerson()+ "\", " +
						"\"" + p.getPhonePerson() + "\", " +
						"\"" + p.getEmailPerson() + "\", " +
						"\"" + p.getIdRegister() + "\"); ");
		
		Vector<Professor> vet = TeacherDAO.getInstance().searchByCpf("868.563.327-34");

		this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getNamePerson() + "\" and " +
					"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
					"professor.email = \"" + p.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + p.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarMatricula() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getNamePerson() + "\", " +
						"\"" + p.getCpfPerson()+ "\", " +
						"\"" + p.getPhonePerson() + "\", " +
						"\"" + p.getEmailPerson() + "\", " +
						"\"" + p.getIdRegister() + "\"); ");
		
		Vector<Professor> vet = TeacherDAO.getInstance().searchByRegister("123456");

		this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getNamePerson() + "\" and " +
					"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
					"professor.email = \"" + p.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + p.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarTelefone() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getNamePerson() + "\", " +
						"\"" + p.getCpfPerson()+ "\", " +
						"\"" + p.getPhonePerson() + "\", " +
						"\"" + p.getEmailPerson() + "\", " +
						"\"" + p.getIdRegister() + "\"); ");
		
		Vector<Professor> vet = TeacherDAO.getInstance().searchByPhone("1234-5678");

		this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getNamePerson() + "\" and " +
					"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
					"professor.email = \"" + p.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + p.getIdRegister() + "\";");
		
		assertTrue("Teste de Altera��o.", vet.size() > 0);
	}
	@Test
	public void testBuscarEmail() throws ClientException, SQLException {
		Professor p = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		this.executaNoBanco("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + p.getNamePerson() + "\", " +
						"\"" + p.getCpfPerson()+ "\", " +
						"\"" + p.getPhonePerson() + "\", " +
						"\"" + p.getEmailPerson() + "\", " +
						"\"" + p.getIdRegister() + "\"); ");
		
		Vector<Professor> vet = TeacherDAO.getInstance().searchByEmail("Nome@email");

		this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + p.getNamePerson() + "\" and " +
					"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
					"professor.email = \"" + p.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + p.getIdRegister() + "\";");
		
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
