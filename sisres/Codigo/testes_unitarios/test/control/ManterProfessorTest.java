package test.control;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Professor;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.FactoryConnection;


import control.ProfessorRegister;
import exception.ClientException;

public class ManterProfessorTest {

	private static Vector<Professor> vet;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vet = ProfessorRegister.getNewProfessor().getVectorProfessors();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testInstance() {
		assertTrue("Teste de Intancia de ManterProfessor", ProfessorRegister.getNewProfessor() instanceof ProfessorRegister);
	}
	
	@Test
	public void testSingleton() {
		ProfessorRegister p = ProfessorRegister.getNewProfessor();
		ProfessorRegister q = ProfessorRegister.getNewProfessor();
		assertSame("Teste Singleton de ManterProfessor", p, q);
	}
	
	
	
	@Test
	public void testInserirVet() throws ClientException, SQLException {
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ProfessorRegister.getNewProfessor().insert("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		
		boolean resultado = this.estaNoBanco("SELECT * FROM professor WHERE " +
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
		
		Professor p = vet.lastElement();
		boolean resultado2 = prof.equals(p);
		vet.remove(vet.lastElement());
		assertTrue("Teste de Inclusao do Professor.", resultado == true && resultado2 == true);
	}
	
	@Test
	public void testAlterarVet() throws ClientException, SQLException {
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		Professor p = new Professor("Nome para Alterar", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		
		this.executaNoBanco("INSERT INTO " +
				"professor (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + prof.getNamePerson() + "\", " +
				"\"" + prof.getCpfPerson()+ "\", " +
				"\"" + prof.getPhonePerson() + "\", " +
				"\"" + prof.getEmailPerson() + "\", " +
				"\"" + prof.getIdRegister() + "\"); ");
		
		ProfessorRegister.getNewProfessor().update("Nome para Alterar", "868.563.327-34", "123456", 
				"1234-5678", "Nome@email", prof);
		
		boolean resultado =  this.estaNoBanco("SELECT * FROM professor WHERE " +
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
		
		assertTrue("Teste de Alteracao do Professor.", resultado);
	}
	
	@Test
	public void testExcluirVet() throws ClientException, SQLException {
		Professor prof = new Professor("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		
		this.executaNoBanco("INSERT INTO " +
				"professor (nome, cpf, telefone, email, matricula) VALUES (" +
				"\"" + prof.getNamePerson() + "\", " +
				"\"" + prof.getCpfPerson()+ "\", " +
				"\"" + prof.getPhonePerson() + "\", " +
				"\"" + prof.getEmailPerson() + "\", " +
				"\"" + prof.getIdRegister() + "\");");
		
		ProfessorRegister.getNewProfessor().delete(prof);
		
		boolean resultado =  this.estaNoBanco("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + prof.getNamePerson() + "\" and " +
				"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
				"professor.email = \"" + prof.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + prof.getIdRegister() + "\";");
		if(resultado)
			this.executaNoBanco("DELETE FROM professor WHERE " +
					"professor.nome = \"" + prof.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof.getIdRegister() + "\";");
		
		boolean resultado2 = true;
		if(vet.size() > 0)
			resultado2 = !vet.lastElement().equals(prof);
		
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
