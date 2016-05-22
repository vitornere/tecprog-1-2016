package persistence;

import model.Professor;

import java.sql.*;
import java.util.Vector;

import exception.ClientException;

public class ProfessorDAO {

	//Mensagens
		private static final String PROFESSOR_JA_EXISTENTE = "O Professor ja esta cadastrado.";
		private static final String PROFESSOR_NAO_EXISTENTE = "O Professor nao esta cadastrado.";
		private static final String PROFESSOR_NULO = "O Professor esta nulo.";
		private static final String PROFESSOR_EM_USO = "Sala esta sendo utilizada em uma reserva.";
		private static final String CPF_JA_EXISTENTE = "Ja existe um professor cadastrado com esse CPF.";
		private static final String MATRICULA_JA_EXISTENTE = "Ja existe um professor cadastrado com essa matricula.";
	
	//Singleton
		private static ProfessorDAO instance;
		private ProfessorDAO(){
		}
		public static ProfessorDAO getNewProfessor(){
			if(instance == null)
				instance = new ProfessorDAO();
			return instance;
		}
	//
	
	
	public void include(Professor prof) throws SQLException, ClientException {
			if(prof == null)
				throw new ClientException(PROFESSOR_NULO);
			else if(this.inDBCpf(prof.getCpfPerson()))
				throw new ClientException(CPF_JA_EXISTENTE);
			else if(this.inDBMatricula(prof.getIdRegister()))
				throw new ClientException(MATRICULA_JA_EXISTENTE);
			this.updateQuery("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + prof.getNamePerson() + "\", " +
						"\"" + prof.getCpfPerson()+ "\", " +
						"\"" + prof.getPhonePerson() + "\", " +
						"\"" + prof.getEmailPerson() + "\", " +
						"\"" + prof.getIdRegister() + "\"); "
					);			
	}

	public void update(Professor prof_velho, Professor prof_novo) throws SQLException, ClientException {
		if(prof_velho == null)
			throw new ClientException(PROFESSOR_NULO);
		if(prof_novo == null)
			throw new ClientException(PROFESSOR_NULO);
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;
		
		if(!this.inDB(prof_velho))
			throw new ClientException(PROFESSOR_NAO_EXISTENTE);
		if(this.inOtherDB(prof_velho))
			throw new ClientException(PROFESSOR_EM_USO);
		else if(!prof_velho.getCpfPerson().equals(prof_novo.getCpfPerson()) && this.inDBCpf(prof_novo.getCpfPerson()))
			throw new ClientException(CPF_JA_EXISTENTE);
		else if(!prof_velho.getIdRegister().equals(prof_novo.getIdRegister()) && this.inDBMatricula(prof_novo.getIdRegister()))
			throw new ClientException(MATRICULA_JA_EXISTENTE);
		else if(!this.inDB(prof_novo)){
			String msg = "UPDATE professor SET " +
					"nome = \"" + prof_novo.getNamePerson() + "\", " +
					"cpf = \"" + prof_novo.getCpfPerson() + "\", " +
					"telefone = \"" + prof_novo.getPhonePerson() + "\", " +
					"email = \"" + prof_novo.getEmailPerson() + "\", " +
					"matricula = \"" + prof_novo.getIdRegister() + "\""+
					" WHERE " +
					"professor.nome = \"" + prof_velho.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof_velho.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof_velho.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof_velho.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof_velho.getIdRegister() + "\";";
			con.setAutoCommit(false);
			pst = con.prepareStatement(msg);
			pst.executeUpdate();
			con.commit();
		}else
			throw new ClientException(PROFESSOR_JA_EXISTENTE);
		
		pst.close();
		con.close();
	}

	public void delete(Professor prof) throws SQLException, ClientException {
		if(prof == null)
			throw new ClientException(PROFESSOR_NULO);
		if(this.inOtherDB(prof))
			throw new ClientException(PROFESSOR_EM_USO);
		else if(this.inDB(prof)){
			this.updateQuery("DELETE FROM professor WHERE " +
				"professor.nome = \"" + prof.getNamePerson() + "\" and " +
				"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
				"professor.email = \"" + prof.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + prof.getIdRegister() + "\";"
				);
		}
		else
			throw new ClientException(PROFESSOR_NAO_EXISTENTE);
	}

	
	
	public Vector<Professor> seachAll() throws SQLException, ClientException {
		return this.buscar("SELECT * FROM professor;");
	}
	public Vector<Professor> searchNameProfessor(String valor) throws SQLException, ClientException {
		return this.buscar("SELECT * FROM professor WHERE nome = " + "\"" + valor + "\";");
	}
	public Vector<Professor> searchCpfProfessor(String valor) throws SQLException, ClientException {
		return this.buscar("SELECT * FROM professor WHERE cpf = " + "\"" + valor + "\";");
	}
	public Vector<Professor> searchIdProfessor(String valor) throws SQLException, ClientException {
		return this.buscar("SELECT * FROM professor WHERE matricula = " + "\"" + valor + "\";");
	}
	public Vector<Professor> searchEmailProfessor(String valor) throws SQLException, ClientException {
		return this.buscar("SELECT * FROM professor WHERE email = " + "\"" + valor + "\";");
	}
	public Vector<Professor> searchPhoneProfessor(String valor) throws SQLException, ClientException {
		return this.buscar("SELECT * FROM professor WHERE telefone = " + "\"" + valor + "\";");
	}

	
	/**
	 * Metodos Privados
	 * */
	
	private Vector<Professor> buscar(String query) throws SQLException, ClientException {		
		Vector<Professor> vet = new Vector<Professor>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
			vet.add(this.fetchProfessor(rs));
		
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
	private boolean inDB(Professor prof) throws SQLException{
		return this.inDBGeneric("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + prof.getNamePerson() + "\" and " +
				"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
				"professor.email = \"" + prof.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + prof.getIdRegister() + "\";");
	}
	private boolean inDBCpf(String codigo) throws SQLException{
		return this.inDBGeneric("SELECT * FROM professor WHERE " +
				"cpf = \"" + codigo + "\";");
	}
	private boolean inDBMatricula(String codigo) throws SQLException{
		return this.inDBGeneric("SELECT * FROM professor WHERE " +
				"matricula = \"" + codigo + "\";");
	}
	private boolean inOtherDB(Professor prof) throws SQLException{
		if ( this.inDBGeneric(
				"SELECT * FROM reserva_sala_professor WHERE " +
				"id_professor = (SELECT id_professor FROM professor WHERE " +
				"professor.nome = \"" + prof.getNamePerson() + "\" and " +
				"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
				"professor.email = \"" + prof.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + prof.getIdRegister() + "\");") == false)
		{
			if(this.inDBGeneric(
					"SELECT * FROM reserva_equipamento WHERE " +
					"id_professor = (SELECT id_professor FROM professor WHERE " +
					"professor.nome = \"" + prof.getNamePerson() + "\" and " +
					"professor.cpf = \"" + prof.getCpfPerson() + "\" and " +
					"professor.telefone = \"" + prof.getPhonePerson() + "\" and " +
					"professor.email = \"" + prof.getEmailPerson() + "\" and " +
					"professor.matricula = \"" + prof.getIdRegister() + "\");") == false)
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	private Professor fetchProfessor(ResultSet rs) throws ClientException, SQLException{
		return new Professor(rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"),
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
