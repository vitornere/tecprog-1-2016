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
			else if(this.inDBCpf(prof.getCpfProfessor()))
				throw new ClientException(CPF_JA_EXISTENTE);
			else if(this.inDBMatricula(prof.getIdProfessor()))
				throw new ClientException(MATRICULA_JA_EXISTENTE);
			this.updateQuery("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + prof.getName() + "\", " +
						"\"" + prof.getCpfProfessor()+ "\", " +
						"\"" + prof.getPhoneProfessor() + "\", " +
						"\"" + prof.getEmailProfessor() + "\", " +
						"\"" + prof.getIdProfessor() + "\"); "
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
		else if(!prof_velho.getCpfProfessor().equals(prof_novo.getCpfProfessor()) && this.inDBCpf(prof_novo.getCpfProfessor()))
			throw new ClientException(CPF_JA_EXISTENTE);
		else if(!prof_velho.getIdProfessor().equals(prof_novo.getIdProfessor()) && this.inDBMatricula(prof_novo.getIdProfessor()))
			throw new ClientException(MATRICULA_JA_EXISTENTE);
		else if(!this.inDB(prof_novo)){
			String msg = "UPDATE professor SET " +
					"nome = \"" + prof_novo.getName() + "\", " +
					"cpf = \"" + prof_novo.getCpfProfessor() + "\", " +
					"telefone = \"" + prof_novo.getPhoneProfessor() + "\", " +
					"email = \"" + prof_novo.getEmailProfessor() + "\", " +
					"matricula = \"" + prof_novo.getIdProfessor() + "\""+
					" WHERE " +
					"professor.nome = \"" + prof_velho.getName() + "\" and " +
					"professor.cpf = \"" + prof_velho.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof_velho.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof_velho.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof_velho.getIdProfessor() + "\";";
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
				"professor.nome = \"" + prof.getName() + "\" and " +
				"professor.cpf = \"" + prof.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + prof.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + prof.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + prof.getIdProfessor() + "\";"
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
				"professor.nome = \"" + prof.getName() + "\" and " +
				"professor.cpf = \"" + prof.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + prof.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + prof.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + prof.getIdProfessor() + "\";");
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
				"professor.nome = \"" + prof.getName() + "\" and " +
				"professor.cpf = \"" + prof.getCpfProfessor() + "\" and " +
				"professor.telefone = \"" + prof.getPhoneProfessor() + "\" and " +
				"professor.email = \"" + prof.getEmailProfessor() + "\" and " +
				"professor.matricula = \"" + prof.getIdProfessor() + "\");") == false)
		{
			if(this.inDBGeneric(
					"SELECT * FROM reserva_equipamento WHERE " +
					"id_professor = (SELECT id_professor FROM professor WHERE " +
					"professor.nome = \"" + prof.getName() + "\" and " +
					"professor.cpf = \"" + prof.getCpfProfessor() + "\" and " +
					"professor.telefone = \"" + prof.getPhoneProfessor() + "\" and " +
					"professor.email = \"" + prof.getEmailProfessor() + "\" and " +
					"professor.matricula = \"" + prof.getIdProfessor() + "\");") == false)
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
