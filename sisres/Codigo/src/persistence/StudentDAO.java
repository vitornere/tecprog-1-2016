/*
 * File: AlunoDAO.java
 * Description: Class to make transactions with the database to crud student
 * */

package persistence;

import model.Aluno;

import java.sql.*;
import java.util.Vector;

import exception.ClienteException;

public class StudentDAO {

	//Mensagens
		private static final String ALUNO_JA_EXISTENTE = "O Aluno ja esta cadastrado.";
		private static final String ALUNO_NULO = "O Aluno esta nulo.";
		private static final String ALUNO_NAO_EXISTENTE = "O Aluno nao esta cadastrado.";
		private static final String ALUNO_EM_USO = "Sala esta sendo utilizada em uma reserva.";
		private static final String CPF_JA_EXISTENTE = "Ja existe um aluno cadastrado com esse CPF.";
		private static final String MATRICULA_JA_EXISTENTE = "Ja existe um aluno cadastrado com essa matricula.";
	
	//Singleton
		private static StudentDAO instance;
		private StudentDAO(){
		}
		public static StudentDAO getInstance(){
			if(instance == null)
				instance = new StudentDAO();
			return instance;
		}
	//
	
		
	public void incluir(Aluno student) throws SQLException, ClienteException {
		if(student == null)
			throw new ClienteException(ALUNO_NULO);
		else if(this.inDBCpf(student.getCpf()))
			throw new ClienteException(CPF_JA_EXISTENTE);
		else if(this.inDBMatricula(student.getMatricula()))
				throw new ClienteException(MATRICULA_JA_EXISTENTE);
		else if(!this.inDB(student))
		{
			this.updateQuery("INSERT INTO " +
					"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
					"\"" + student.getNome() + "\", " +
					"\"" + student.getCpf()+ "\", " +
					"\"" + student.getTelefone() + "\", " +
					"\"" + student.getEmail() + "\", " +
					"\"" + student.getMatricula() + "\"); "
					);
		}
		else
			throw new ClienteException(ALUNO_JA_EXISTENTE);
	}

	public void alterar(Aluno old_student, Aluno new_student) throws SQLException, ClienteException {
		if(old_student == null)
			throw new ClienteException(ALUNO_NULO);
		if(new_student == null)
			throw new ClienteException(ALUNO_NULO);
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;
		
		if(!this.inDB(old_student))
			throw new ClienteException(ALUNO_NAO_EXISTENTE);
		else if(this.inOtherDB(old_student))
			throw new ClienteException(ALUNO_EM_USO);
		else if(!old_student.getCpf().equals(new_student.getCpf()) && this.inDBCpf(new_student.getCpf()))
			throw new ClienteException(CPF_JA_EXISTENTE);
		else if(!old_student.getMatricula().equals(new_student.getMatricula()) && this.inDBMatricula(new_student.getMatricula()))
				throw new ClienteException(MATRICULA_JA_EXISTENTE);
		else if(!this.inDB(new_student))
		{
			String msg = "UPDATE aluno SET " +
				"nome = \"" + new_student.getNome() + "\", " +
				"cpf = \"" + new_student.getCpf() + "\", " +
				"telefone = \"" + new_student.getTelefone() + "\", " +
				"email = \"" + new_student.getEmail() + "\", " +
				"matricula = \"" + new_student.getMatricula() + "\""+
				" WHERE " +
				"aluno.nome = \"" + old_student.getNome() + "\" and " +
				"aluno.cpf = \"" + old_student.getCpf() + "\" and " +
				"aluno.telefone = \"" + old_student.getTelefone() + "\" and " +
				"aluno.email = \"" + old_student.getEmail() + "\" and " +
				"aluno.matricula = \"" + old_student.getMatricula() + "\";";
			con.setAutoCommit(false);
			pst = con.prepareStatement(msg);
			pst.executeUpdate();
			con.commit();
		}
		else
			throw new ClienteException(ALUNO_JA_EXISTENTE);

		pst.close();
		con.close();
	}

	public void excluir(Aluno student) throws SQLException, ClienteException {
		if(student == null)
			throw new ClienteException(ALUNO_NULO);
		else if(this.inOtherDB(student))
			throw new ClienteException(ALUNO_EM_USO);
		else if(this.inDB(student)){
			this.updateQuery("DELETE FROM aluno WHERE " +
				"aluno.nome = \"" + student.getNome() + "\" and " +
				"aluno.cpf = \"" + student.getCpf() + "\" and " +
				"aluno.telefone = \"" + student.getTelefone() + "\" and " +
				"aluno.email = \"" + student.getEmail() + "\" and " +
				"aluno.matricula = \"" + student.getMatricula() + "\";"
				);
		}
		else
			throw new ClienteException(ALUNO_NAO_EXISTENTE);
	}

	
	
	public Vector<Aluno> buscarTodos() throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno;");
	}
	public Vector<Aluno> buscarNome(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno WHERE nome = " + "\"" + valor + "\";");
	}
	public Vector<Aluno> buscarCpf(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno WHERE cpf = " + "\"" + valor + "\";");
	}
	public Vector<Aluno> buscarMatricula(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno WHERE matricula = " + "\"" + valor + "\";");
	}
	public Vector<Aluno> buscarEmail(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno WHERE email = " + "\"" + valor + "\";");
	}
	public Vector<Aluno> buscarTelefone(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno WHERE telefone = " + "\"" + valor + "\";");
	}
	
	
	/**
	 * Metodos Privados
	 * */
	
	private Vector<Aluno> buscar(String query) throws SQLException, ClienteException {
		Vector<Aluno> vet = new Vector<Aluno>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
			vet.add(this.fetchAluno(rs));
		
		pst.close();
		rs.close();
		con.close();
		return vet;
	}
	
	
	private boolean inDBGeneric(String query) throws SQLException{
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
	private boolean inDB(Aluno student) throws SQLException{
		return this.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + student.getNome() + "\" and " +
				"aluno.cpf = \"" + student.getCpf() + "\" and " +
				"aluno.telefone = \"" + student.getTelefone() + "\" and " +
				"aluno.email = \"" + student.getEmail() + "\" and " +
				"aluno.matricula = \"" + student.getMatricula() + "\";");
	}
	private boolean inDBCpf(String codigo) throws SQLException{
		return this.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.cpf = \"" + codigo + "\";");
	}
	private boolean inDBMatricula(String codigo) throws SQLException{
		return this.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.matricula = \"" + codigo + "\";");
	}
	private boolean inOtherDB(Aluno student) throws SQLException, ClienteException{
		return this.inDBGeneric(
				"SELECT * FROM reserva_sala_aluno WHERE " +
				"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
				"aluno.nome = \"" + student.getNome() + "\" and " +
				"aluno.cpf = \"" + student.getCpf() + "\" and " +
				"aluno.telefone = \"" + student.getTelefone() + "\" and " +
				"aluno.email = \"" + student.getEmail() + "\" and " +
				"aluno.matricula = \"" + student.getMatricula() + "\");");
	}
	
	
	private Aluno fetchAluno(ResultSet rs) throws ClienteException, SQLException{
		return new Aluno(rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"),
				rs.getString("telefone"), rs.getString("email"));
	}
	
	private void updateQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}

}
