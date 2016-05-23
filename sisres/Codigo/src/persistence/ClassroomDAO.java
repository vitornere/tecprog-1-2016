package persistence;

import model.Classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import exception.PatrimonyException;

public class ClassroomDAO {

		private static final String EXISTING_CLASSROOM = "Sala ja cadastrada.";
		private static final String NOT_EXISTING_CLASSROOM = "Sala nao cadastrada.";
		private static final String CLASSROOM_IN_USE = "Sala esta sendo utilizada em uma reserva.";
		private static final String NULL_CLASSROOM = "Sala esta nula.";
		private static final String EXISTING_CODE = "Sala com o mesmo codigo ja cadastrada.";
	
		private static ClassroomDAO instance;
		private ClassroomDAO(){
		}
		public static ClassroomDAO getInstance(){
			if(instance == null)
				instance = new ClassroomDAO();
			return instance;
		}

		
	public void add(Classroom classroom) throws SQLException, PatrimonyException {	
		if(classroom == null)
			throw new PatrimonyException(NULL_CLASSROOM);
		else if(this.inDBCode(classroom.getCode()))
			throw new PatrimonyException(EXISTING_CODE);
		this.updateQuery("INSERT INTO " +
					"sala (codigo, descricao, capacidade) VALUES (" +
					"\"" + classroom.getCode() + "\", " +
					"\"" + classroom.getDescription() + "\", " +
					classroom.getCapacity() + ");");
	}

	public void change(Classroom old_classroom, Classroom new_classroom) throws SQLException, PatrimonyException {
		if(new_classroom == null)
			throw new PatrimonyException(NULL_CLASSROOM);
		if(old_classroom == null)
			throw new PatrimonyException(NULL_CLASSROOM);
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;
		
		if(!this.inDB(old_classroom))
			throw new PatrimonyException(NOT_EXISTING_CLASSROOM);
		else if(this.inOtherDB(old_classroom))
			throw new PatrimonyException(CLASSROOM_IN_USE);
		else if(!old_classroom.getCode().equals(new_classroom.getCode()) && this.inDBCode(new_classroom.getCode()))
			throw new PatrimonyException(EXISTING_CODE);
		if(!this.inDB(new_classroom)){
			String msg = "UPDATE sala SET " +				
				"codigo = \"" + new_classroom.getCode() + "\", " +
				"descricao = \"" + new_classroom.getDescription() + "\", " +
				"capacidade = " + new_classroom.getCapacity() +
				" WHERE " +
				"sala.codigo = \"" + old_classroom.getCode() + "\" and " +
				"sala.descricao = \"" + old_classroom.getDescription() +  "\" and " +
				"sala.capacidade = " + old_classroom.getCapacity() +";";
			con.setAutoCommit(false);
			pst = con.prepareStatement(msg);
			pst.executeUpdate();
			con.commit();
		}
		else
			throw new PatrimonyException(EXISTING_CLASSROOM);
		
		pst.close();
		con.close();
	}

	public void delete(Classroom classroom) throws SQLException, PatrimonyException {
		if(classroom == null)
			throw new PatrimonyException(NULL_CLASSROOM);
		else if(this.inOtherDB(classroom))
			throw new PatrimonyException(CLASSROOM_IN_USE);
		else if(this.inDB(classroom)){
			this.updateQuery("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + classroom.getCode() + "\" and " +
				"sala.descricao = \"" + classroom.getDescription() +  "\" and " +
				"sala.capacidade = " + classroom.getCapacity() + ";"				
				);
		}
		else
			throw new PatrimonyException(NOT_EXISTING_CLASSROOM);
	}

	
	
	public Vector<Classroom> searchAll() throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala;");
	}
	public Vector<Classroom> searchCode(String value) throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala WHERE codigo = " + "\"" + value + "\";");
	}
	public Vector<Classroom> searchDescription(String value) throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala WHERE descricao = " + "\"" + value + "\";");
	}
	public Vector<Classroom> searchCapacity(String value) throws SQLException, PatrimonyException {
		return this.buscar("SELECT * FROM sala WHERE capacidade = " + value + ";");
	}
	
	
	private Vector<Classroom> buscar(String query) throws SQLException, PatrimonyException {
		Vector<Classroom> vet = new Vector<Classroom>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
			vet.add(this.fetchSala(rs));
		
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
	private boolean inDB(Classroom classroom) throws SQLException{
		return this.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + classroom.getCode() + "\" and " +
				"sala.descricao = \"" + classroom.getDescription() + "\" and " +
				"sala.capacidade = " + classroom.getCapacity() +
				";");
	}
	private boolean inDBCode(String code) throws SQLException{
		return this.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + code + "\";");
	}
	private boolean inOtherDB(Classroom classroom) throws SQLException{
		if( this.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
				"id_sala = (SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + classroom.getCode() + "\" and " +
				"sala.descricao = \"" + classroom.getDescription() +  "\" and " +
				"sala.capacidade = " + classroom.getCapacity() +" );") == false)
		{
			if(this.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
							"id_sala = (SELECT id_sala FROM sala WHERE " +
							"sala.codigo = \"" + classroom.getCode() + "\" and " +
							"sala.descricao = \"" + classroom.getDescription() +  "\" and " +
							"sala.capacidade = " + classroom.getCapacity() +" );") == false)
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	private Classroom fetchSala(ResultSet rs) throws PatrimonyException, SQLException{
		return new Classroom(rs.getString("codigo"), rs.getString("descricao"), rs.getString("capacidade"));
	}
	
	private void updateQuery(String menssage) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(menssage);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}

}
