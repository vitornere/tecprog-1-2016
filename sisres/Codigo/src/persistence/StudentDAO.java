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
		private static final String EXISTENTSTUDENT = "O Aluno ja esta cadastrado.";
		private static final String NULLSTUDENT = "O Aluno esta nulo.";
		private static final String STUDENTNOTEXIST = "O Aluno nao esta cadastrado.";
		private static final String STUDENTINUSE = "Sala esta sendo utilizada em uma reserva.";
		private static final String EXISTENTCPF = "Ja existe um aluno cadastrado com esse CPF.";
		private static final String EXISTENTREGISTER = "Ja existe um aluno cadastrado com essa matricula.";
	
	//Singleton
		private static StudentDAO instance;
		private StudentDAO(){
		}
		public static StudentDAO getInstance(){
			if(instance == null){
				instance = new StudentDAO();
			}
			else{
				// Nothing to do.
			}
			return instance;
		}
	//
	
		
	public void add(Aluno student) throws SQLException, ClienteException {
		if(student == null)
			throw new ClienteException(NULLSTUDENT);
		else if(this.inDBCpf(student.getCpf()))
			throw new ClienteException(EXISTENTCPF);
		else if(this.inDBMatricula(student.getMatricula()))
				throw new ClienteException(EXISTENTREGISTER);
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
			throw new ClienteException(EXISTENTSTUDENT);
	}

	public void change(Aluno old_student, Aluno new_student) throws SQLException, ClienteException {
		if(old_student == null)
			throw new ClienteException(NULLSTUDENT);
		if(new_student == null)
			throw new ClienteException(NULLSTUDENT);
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;
		
		if(!this.inDB(old_student))
			throw new ClienteException(STUDENTNOTEXIST);
		else if(this.inOtherDB(old_student))
			throw new ClienteException(STUDENTINUSE);
		else if(!old_student.getCpf().equals(new_student.getCpf()) && this.inDBCpf(new_student.getCpf()))
			throw new ClienteException(EXISTENTCPF);
		else if(!old_student.getMatricula().equals(new_student.getMatricula()) && this.inDBMatricula(new_student.getMatricula()))
				throw new ClienteException(EXISTENTREGISTER);
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
			throw new ClienteException(EXISTENTSTUDENT);

		pst.close();
		con.close();
	}

	public void delete(Aluno student) throws SQLException, ClienteException {
		if(student == null)
			throw new ClienteException(NULLSTUDENT);
		else if(this.inOtherDB(student))
			throw new ClienteException(STUDENTINUSE);
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
			throw new ClienteException(STUDENTNOTEXIST);
	}

	
	
	public Vector<Aluno> searchAll() throws SQLException, ClienteException {
		return this.search("SELECT * FROM aluno;");
	}
	public Vector<Aluno> searchByName(String name) throws SQLException, ClienteException {
		return this.search("SELECT * FROM aluno WHERE nome = " + "\"" + name + "\";");
	}
	public Vector<Aluno> searchByCpf(String cpf) throws SQLException, ClienteException {
		return this.search("SELECT * FROM aluno WHERE cpf = " + "\"" + cpf + "\";");
	}
	public Vector<Aluno> searchByRegister(String register) throws SQLException, ClienteException {
		return this.search("SELECT * FROM aluno WHERE matricula = " + "\"" + register + "\";");
	}
	public Vector<Aluno> searcByEmail(String email) throws SQLException, ClienteException {
		return this.search("SELECT * FROM aluno WHERE email = " + "\"" + email + "\";");
	}
	public Vector<Aluno> searchByPhone(String phone) throws SQLException, ClienteException {
		return this.search("SELECT * FROM aluno WHERE telefone = " + "\"" + phone + "\";");
	}
	
	
	/**
	 * Metodos Privados
	 * */
	
	private Vector<Aluno> search(String query) throws SQLException, ClienteException {
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
