package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import model.Professor;
import model.ReservaSalaProfessor;
import model.Classroom;

import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;

public class ReserveClassroomProfessorDAO extends DAO{

	private final String NULL_TERM = "Termo nulo.";
	private final String UNAVAILABLE_CLASSROOM = "A Sala esta reservada no mesmo dia e horario.";
	private final String NOEXISTENT_PROFESSOR = "Professor inexistente.";
	private final String NOEXISTENT_CLASSROOM = "Sala inexistente";
	private final String NOEXISTENT_RESERVE = "Reserva inexistente";
	private final String EXISTENT_RESERVE = "A reserva ja existe.";
	private final String DATE_PASSED = "A data escolhida ja passou.";
	private final String TIME_PASSED = "A hora escolhida ja passou.";
	
	
	//Singleton
		private static ReserveClassroomProfessorDAO instance;
		private ReserveClassroomProfessorDAO(){
		}
		public static ReserveClassroomProfessorDAO getInstance(){
			if(instance == null)
				instance = new ReserveClassroomProfessorDAO();
			return instance;
		}
		//Querys de Reuso
			private String selectIdProfessor(Professor p){
				return "SELECT id_professor FROM professor WHERE " +
						"professor.nome = \"" + p.getNome() + "\" and " +
						"professor.cpf = \"" + p.getCpf() + "\" and " +
						"professor.telefone = \"" + p.getTelefone() + "\" and " +
						"professor.email = \"" + p.getEmail() + "\" and " +
						"professor.matricula = \"" + p.getMatricula() + "\"";
			}
			private String selectIdSala(Classroom sala){
				return "SELECT id_sala FROM sala WHERE " +
						"sala.codigo = \"" + sala.getCode() + "\" and " +
						"sala.descricao = \"" + sala.getDescription() +  "\" and " +
						"sala.capacidade = " + sala.getCapacity();
			}
			private String whereProfessorReserveClassroom(ReservaSalaProfessor r){
				return " WHERE " +
				"id_professor = ( " + selectIdProfessor(r.getProfessor()) + " ) and " +
				"id_sala = ( " + selectIdSala(r.getSala()) + " ) and " +
				"finalidade = \"" + r.getFinalidade() + "\" and " +
				"hora = \"" + r.getHora() + "\" and " +
				"data = \"" + r.getData() + "\"";
			}
			private String classroomValuesReservedProfessor(ReservaSalaProfessor r){
				return "( " + selectIdProfessor(r.getProfessor()) + " ), " +
				"( " + selectIdSala(r.getSala()) + " ), " +
				"\"" + r.getFinalidade() + "\", " +
				"\"" + r.getHora() + "\", " +
				"\"" + r.getData() + "\"";
			}
			private String classroomValuesReservedProfessorAttributes(ReservaSalaProfessor r){
				return "id_professor = ( " + selectIdProfessor(r.getProfessor()) + " ), " +
				"id_sala = ( " + selectIdSala(r.getSala()) + " ), " +
				"finalidade = \"" + r.getFinalidade() + "\", " +
				"hora = \"" + r.getHora() + "\", " +
				"data = \"" + r.getData() + "\"";
			}
		
			private String insertInto(ReservaSalaProfessor r){
				return "INSERT INTO " +
						"reserva_sala_professor (id_professor, id_sala, finalidade, hora, data) " +
						"VALUES ( " + classroomValuesReservedProfessor(r) + " );";
			}
			
			private String deleteFromProfessor(ReservaSalaProfessor r){
				return "DELETE FROM reserva_sala_professor " + this.whereProfessorReserveClassroom(r) + " ;";
			}
			
			private String deleteFromAluno(ReservaSalaProfessor r){
				return "DELETE FROM reserva_sala_aluno WHERE " +
						"hora = \"" + r.getHora() + "\" and " +
						"data = \"" + r.getData() +  "\" ;";
			}
			
			private String update(ReservaSalaProfessor r, ReservaSalaProfessor r2){
				return "UPDATE reserva_sala_professor SET " + 
						this.classroomValuesReservedProfessorAttributes(r2) +
						this.whereProfessorReserveClassroom(r) + " ;";
			}
			
	public void add(ReservaSalaProfessor r) throws ReservaException, SQLException {
		if(r == null)
			throw new ReservaException(NULL_TERM);
		else if(!this.professorInDB(r.getProfessor()))
			throw new ReservaException(NOEXISTENT_PROFESSOR);
		else if(!this.classroomInDB(r.getSala()))
			throw new ReservaException(NOEXISTENT_CLASSROOM);
		else if(this.classroomInReserveDB(r.getSala(), r.getData(), r.getHora()))
			throw new ReservaException(UNAVAILABLE_CLASSROOM);
		else if(this.reserveInDB(r))
			throw new ReservaException(EXISTENT_RESERVE);
		else if(this.studantInReserveDB(r.getData(), r.getHora()))
				super.executeQuery(this.deleteFromAluno(r));
		if(this.datePassed(r.getData()))
			throw new ReservaException(DATE_PASSED);
		if(this.equalDate(r.getData()))
		{
			if(this.timePassed(r.getHora()))
				throw new ReservaException(TIME_PASSED);
			else
				super.executeQuery(this.insertInto(r));
		}
		else
			super.executeQuery(this.insertInto(r));		
	}
	
	public void change(ReservaSalaProfessor r, ReservaSalaProfessor r_new) throws ReservaException, SQLException {
		if(r == null)
			throw new ReservaException(NULL_TERM);
		else if(r_new == null)
			throw new ReservaException(NULL_TERM);
		
		else if(!this.reserveInDB(r))
			throw new ReservaException(NOEXISTENT_RESERVE);
		else if(this.reserveInDB(r_new))
			throw new ReservaException(EXISTENT_RESERVE);
		else if(!this.professorInDB(r_new.getProfessor()))
			throw new ReservaException(NOEXISTENT_PROFESSOR);
		else if(!this.classroomInDB(r_new.getSala()))
			throw new ReservaException(NOEXISTENT_CLASSROOM);
		else if(!r.getData().equals(r_new.getData()) || !r.getHora().equals(r_new.getHora())) {
			 if(this.classroomInReserveDB(r_new.getSala(), r_new.getData(), r_new.getHora()))
				throw new ReservaException(UNAVAILABLE_CLASSROOM);
		}		
		if(this.datePassed(r_new.getData()))
			throw new ReservaException(DATE_PASSED);
		if(this.timePassed(r_new.getHora()) && this.equalDate(r_new.getData()))
			throw new ReservaException(TIME_PASSED);
		else
			super.updateQuery(this.update(r, r_new));
	}
	
	public void delete(ReservaSalaProfessor r) throws ReservaException, SQLException {
		if(r == null)
			throw new ReservaException(NULL_TERM);
		else if(!this.reserveInDB(r))
			throw new ReservaException(NOEXISTENT_RESERVE);
		else
			super.executeQuery(this.deleteFromProfessor(r));
	}

	@SuppressWarnings("unchecked")
	public Vector<ReservaSalaProfessor> buscarTodos() throws SQLException, ClienteException, PatrimonyException, ReservaException{
		return super.buscar("SELECT * FROM reserva_sala_professor " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_professor.id_sala " +
				"INNER JOIN professor ON professor.id_professor = reserva_sala_professor.id_professor;");
	}

	
	@SuppressWarnings("unchecked")
	public Vector<ReservaSalaProfessor> buscarPorData(String data) throws SQLException, ClienteException, PatrimonyException, ReservaException{
		return super.buscar("SELECT * FROM reserva_sala_professor " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_professor.id_sala " +
				"INNER JOIN professor ON professor.id_professor = reserva_sala_professor.id_professor" +
				" WHERE data = \"" + this.standardizeDate(data) + "\";");
	} 
	
	
	@Override
	protected Object fetch(ResultSet rs) throws SQLException, ClienteException, PatrimonyException, ReservaException {
		Professor p = new Professor(rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"),
				rs.getString("telefone"), rs.getString("email"));
		
		Classroom s = new Classroom(rs.getString("codigo"), rs.getString("descricao"), rs.getString("capacidade"));
		
		ReservaSalaProfessor r = new ReservaSalaProfessor(rs.getString("data"),rs.getString("hora"),
				s ,rs.getString("finalidade"), p);
		
		return r;
	}
	
	private boolean professorInDB(Professor professor) throws SQLException{
		return super.inDBGeneric("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + professor.getNome() + "\" and " +
				"professor.cpf = \"" + professor.getCpf() + "\" and " +
				"professor.telefone = \"" + professor.getTelefone() + "\" and " +
				"professor.email = \"" + professor.getEmail() + "\" and " +
				"professor.matricula = \"" + professor.getMatricula() + "\";");
	}
	
	private boolean classroomInDB(Classroom sala) throws SQLException{
		return super.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCode() + "\" and " +
				"sala.descricao = \"" + sala.getDescription() + "\" and " +
				"sala.capacidade = " + sala.getCapacity() +
				";");
	}
	
	private boolean classroomInReserveDB(Classroom sala, String data, String hora) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
				"data = \"" + data + "\" and " +
				"hora = \"" + hora + "\" and " +
				"id_sala = (SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCode() + "\" and " +
				"sala.descricao = \"" + sala.getDescription() +  "\" and " +
				"sala.capacidade = " + sala.getCapacity() +" );");
	}
	
	private boolean reserveInDB(ReservaSalaProfessor r) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
					"id_professor = (SELECT id_professor FROM professor WHERE " +
							"professor.nome = \"" + r.getProfessor().getNome() + "\" and " +
							"professor.cpf = \"" + r.getProfessor().getCpf() + "\" and " +
							"professor.telefone = \"" + r.getProfessor().getTelefone() + "\" and " +
							"professor.email = \"" + r.getProfessor().getEmail() + "\" and " +
							"professor.matricula = \"" + r.getProfessor().getMatricula() + "\") and " +
					"id_sala = (SELECT id_sala FROM sala WHERE " +
									"sala.codigo = \"" + r.getSala().getCode() + "\" and " +
									"sala.descricao = \"" + r.getSala().getDescription() +  "\" and " +
									"sala.capacidade = " + r.getSala().getCapacity() +" ) and " +
					"finalidade = \"" + r.getFinalidade() + "\" and " +
					"hora = \"" + r.getHora() + "\" and " +
					"data = \"" + r.getData() + "\";");
	}
	private boolean studantInReserveDB(String data, String hora) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
				"data = \"" + data + "\" and " +
				"hora = \"" + hora + "\";");
	}

	private String currentDate(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}
	
	private String currentTime(){
		Date date = new Date(System.currentTimeMillis());
		return date.toString().substring(11, 16);
	}
	
	private boolean datePassed(String d){
		String now[] = this.currentDate().split("[./-]");
		String date[] = d.split("[./-]");
		
		int dif = now[2].length() - date[2].length();
		date[2] = now[2].substring(0, dif) + date[2];
		
		if(Integer.parseInt(now[2]) > Integer.parseInt(date[2]))
			return true;
		
		dif = now[1].length() - date[1].length();
		date[1] = now[1].substring(0, dif) + date[1];
		
		if(Integer.parseInt(now[1]) > Integer.parseInt(date[1]))
			return true;
		else if(Integer.parseInt(now[1]) == Integer.parseInt(date[1])){
			dif = now[0].length() - date[0].length();
			date[0] = now[0].substring(0, dif) + date[0];
			
			if(Integer.parseInt(now[0]) > Integer.parseInt(date[0]))
				return true;
		}
		return false;
	}
	
	public boolean equalDate(String d){
		d = this.standardizeDate(d);
		String now[] = this.currentDate().split("[./-]");
		String date[] = d.split("[./-]");
		
		if(now[0].equals(date[0]) && now[1].equals(date[1]) && now[2].equals(date[2]))
			return true;
		return false;
	}
	
	private boolean timePassed(String hora){
		String now = this.currentTime();
		if(hora.length() == 4)
			hora = "0" + hora;
		if(Integer.parseInt(now.substring(0, 2)) > Integer.parseInt(hora.substring(0, 2)))
			return true;
		else if(Integer.parseInt(now.substring(0, 2)) == Integer.parseInt(hora.substring(0, 2))){
			if(Integer.parseInt(now.substring(3, 5)) > Integer.parseInt(hora.substring(3, 5)))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	private String standardizeDate(String data){
		String now[] = currentDate().split("[./-]");
		String parts[] = data.split("[./-]");
		String standardizedDate = "";
		
		for(int i = 0; i < 3; i++){
			if(i == 0)
				standardizedDate += now[i].substring(0, 
						now[i].length() - parts[i].length()) + parts[i];
			else
				standardizedDate +=  "/" + now[i].substring(0, 
						now[i].length() - parts[i].length()) + parts[i];
				
		}
		
		return standardizedDate;
	}

}