package persistence;

import model.Student;

import java.sql.*;
import java.util.Vector;

import exception.ClientException;

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
		public static StudentDAO getNewStudent(){
			if(instance == null)
				instance = new StudentDAO();
			return instance;
		}
	//
	
		
	public void include(Student aluno) throws SQLException, ClientException {
		if(aluno == null)
			throw new ClientException(ALUNO_NULO);
		else if(this.inDBCpf(aluno.getCpfPerson()))
			throw new ClientException(CPF_JA_EXISTENTE);
		else if(this.inDBMatricula(aluno.getIdRegister()))
				throw new ClientException(MATRICULA_JA_EXISTENTE);
		else if(!this.inDB(aluno))
		{
			this.updateQuery("INSERT INTO " +
					"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
					"\"" + aluno.getNamePerson() + "\", " +
					"\"" + aluno.getCpfPerson()+ "\", " +
					"\"" + aluno.getPhonePerson() + "\", " +
					"\"" + aluno.getEmailPerson() + "\", " +
					"\"" + aluno.getIdRegister() + "\"); "
					);
		}
		else
			throw new ClientException(ALUNO_JA_EXISTENTE);
	}

	public void alterar(Student aluno_velho, Student aluno_novo) throws SQLException, ClientException {
		if(aluno_velho == null)
			throw new ClientException(ALUNO_NULO);
		if(aluno_novo == null)
			throw new ClientException(ALUNO_NULO);
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;
		
		if(!this.inDB(aluno_velho))
			throw new ClientException(ALUNO_NAO_EXISTENTE);
		else if(this.inOtherDB(aluno_velho))
			throw new ClientException(ALUNO_EM_USO);
		else if(!aluno_velho.getCpfPerson().equals(aluno_novo.getCpfPerson()) && this.inDBCpf(aluno_novo.getCpfPerson()))
			throw new ClientException(CPF_JA_EXISTENTE);
		else if(!aluno_velho.getIdRegister().equals(aluno_novo.getIdRegister()) && this.inDBMatricula(aluno_novo.getIdRegister()))
				throw new ClientException(MATRICULA_JA_EXISTENTE);
		else if(!this.inDB(aluno_novo))
		{
			String msg = "UPDATE aluno SET " +
				"nome = \"" + aluno_novo.getNamePerson() + "\", " +
				"cpf = \"" + aluno_novo.getCpfPerson() + "\", " +
				"telefone = \"" + aluno_novo.getPhonePerson() + "\", " +
				"email = \"" + aluno_novo.getEmailPerson() + "\", " +
				"matricula = \"" + aluno_novo.getIdRegister() + "\""+
				" WHERE " +
				"aluno.nome = \"" + aluno_velho.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + aluno_velho.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + aluno_velho.getPhonePerson() + "\" and " +
				"aluno.email = \"" + aluno_velho.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + aluno_velho.getIdRegister() + "\";";
			con.setAutoCommit(false);
			pst = con.prepareStatement(msg);
			pst.executeUpdate();
			con.commit();
		}
		else
			throw new ClientException(ALUNO_JA_EXISTENTE);

		pst.close();
		con.close();
	}

	public void delete(Student aluno) throws SQLException, ClientException {
		if(aluno == null)
			throw new ClientException(ALUNO_NULO);
		else if(this.inOtherDB(aluno))
			throw new ClientException(ALUNO_EM_USO);
		else if(this.inDB(aluno)){
			this.updateQuery("DELETE FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
				"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + aluno.getIdRegister() + "\";"
				);
		}
		else
			throw new ClientException(ALUNO_NAO_EXISTENTE);
	}

	
	
	public Vector<Student> searchAll() throws SQLException, ClientException {
		return this.buscar("SELECT * FROM aluno;");
	}
	public Vector<Student> searchNameStudent(String valor) throws SQLException, ClientException {
		return this.buscar("SELECT * FROM aluno WHERE nome = " + "\"" + valor + "\";");
	}
	public Vector<Student> searchCpfStudent(String valor) throws SQLException, ClientException {
		return this.buscar("SELECT * FROM aluno WHERE cpf = " + "\"" + valor + "\";");
	}
	public Vector<Student> searchIdStudent(String valor) throws SQLException, ClientException {
		return this.buscar("SELECT * FROM aluno WHERE matricula = " + "\"" + valor + "\";");
	}
	public Vector<Student> searchEmailStudent(String valor) throws SQLException, ClientException {
		return this.buscar("SELECT * FROM aluno WHERE email = " + "\"" + valor + "\";");
	}
	public Vector<Student> searchPhoneStudent(String valor) throws SQLException, ClientException {
		return this.buscar("SELECT * FROM aluno WHERE telefone = " + "\"" + valor + "\";");
	}
	
	
	/**
	 * Metodos Privados
	 * */
	
	private Vector<Student> buscar(String query) throws SQLException, ClientException {
		Vector<Student> vet = new Vector<Student>();
		
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
	private boolean inDB(Student aluno) throws SQLException{
		return this.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
				"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + aluno.getIdRegister() + "\";");
	}
	private boolean inDBCpf(String codigo) throws SQLException{
		return this.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.cpf = \"" + codigo + "\";");
	}
	private boolean inDBMatricula(String codigo) throws SQLException{
		return this.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.matricula = \"" + codigo + "\";");
	}
	private boolean inOtherDB(Student aluno) throws SQLException, ClientException{
		return this.inDBGeneric(
				"SELECT * FROM reserva_sala_aluno WHERE " +
				"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
				"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + aluno.getIdRegister() + "\");");
	}
	
	
	private Student fetchAluno(ResultSet rs) throws ClientException, SQLException{
		return new Student(rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"),
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
