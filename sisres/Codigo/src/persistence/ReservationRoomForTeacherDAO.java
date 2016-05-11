/*
 * File: ReservationRoomForTeacherDAO.java
 * Description: Class to make transactions with the database and reservation room for student
 * */


package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import model.Professor;
import model.ReservaSalaProfessor;
import model.Sala;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public class ReservationRoomForTeacherDAO extends DAO{

	//Mensagens e Alertas
	private final String NULL = "Termo nulo.";
	private final String UNAVAILABLEROOM = "A Sala esta reservada no mesmo dia e horario.";
	private final String INEXISTENTTEACHER = "Professor inexistente.";
	private final String INEXISTENTROOM = "Sala inexistente";
	private final String INEXISTENTRESERVATION = "Reserva inexistente";
	private final String EXISTENTRESERVATION = "A reserva ja existe.";
	private final String PASTDATE = "A data escolhida ja passou.";
	private final String PASTHOUR = "A hora escolhida ja passou.";
	
	
	//Singleton
		private static ReservationRoomForTeacherDAO instance;
		private ReservationRoomForTeacherDAO(){
		}
		public static ReservationRoomForTeacherDAO getInstance(){
			if(instance == null)
				instance = new ReservationRoomForTeacherDAO();
			return instance;
		}
		//Querys de Reuso
			private String selectIdTeacher(Professor teacher){
				return "SELECT id_professor FROM professor WHERE " +
						"professor.nome = \"" + teacher.getNome() + "\" and " +
						"professor.cpf = \"" + teacher.getCpf() + "\" and " +
						"professor.telefone = \"" + teacher.getTelefone() + "\" and " +
						"professor.email = \"" + teacher.getEmail() + "\" and " +
						"professor.matricula = \"" + teacher.getMatricula() + "\"";
			}
			private String selectByIdRoom(Sala room){
				return "SELECT id_sala FROM sala WHERE " +
						"sala.codigo = \"" + room.getCodigo() + "\" and " +
						"sala.descricao = \"" + room.getDescricao() +  "\" and " +
						"sala.capacidade = " + room.getCapacidade();
			}
			private String whereRoomReservedByTeacher(ReservaSalaProfessor reservation){
				return " WHERE " +
				"id_professor = ( " + selectIdTeacher(reservation.getProfessor()) + " ) and " +
				"id_sala = ( " + selectByIdRoom(reservation.getSala()) + " ) and " +
				"finalidade = \"" + reservation.getFinalidade() + "\" and " +
				"hora = \"" + reservation.getHora() + "\" and " +
				"data = \"" + reservation.getData() + "\"";
			}
			private String valuesRoomReservedByTeacher(ReservaSalaProfessor reservation){
				return "( " + selectIdTeacher(reservation.getProfessor()) + " ), " +
				"( " + selectByIdRoom(reservation.getSala()) + " ), " +
				"\"" + reservation.getFinalidade() + "\", " +
				"\"" + reservation.getHora() + "\", " +
				"\"" + reservation.getData() + "\"";
			}
			private String atributesValuesForReservationRoomByTeacher(ReservaSalaProfessor reservation){
				return "id_professor = ( " + selectIdTeacher(reservation.getProfessor()) + " ), " +
				"id_sala = ( " + selectByIdRoom(reservation.getSala()) + " ), " +
				"finalidade = \"" + reservation.getFinalidade() + "\", " +
				"hora = \"" + reservation.getHora() + "\", " +
				"data = \"" + reservation.getData() + "\"";
			}
		
			private String insertIntoReservationRoomByTeacher(ReservaSalaProfessor reservation){
				return "INSERT INTO " +
						"reserva_sala_professor (id_professor, id_sala, finalidade, hora, data) " +
						"VALUES ( " + valuesRoomReservedByTeacher(reservation) + " );";
			}
			
			private String deleteReservationByTeacher(ReservaSalaProfessor reservation){
				return "DELETE FROM reserva_sala_professor " + this.whereRoomReservedByTeacher(reservation) + " ;";
			}
			
			private String deleteReservationByStudent(ReservaSalaProfessor reservation){
				return "DELETE FROM reserva_sala_aluno WHERE " +
						"hora = \"" + reservation.getHora() + "\" and " +
						"data = \"" + reservation.getData() +  "\" ;";
			}
			
			private String update(ReservaSalaProfessor old_reservation, ReservaSalaProfessor new_reservation){
				return "UPDATE reserva_sala_professor SET " + 
						this.atributesValuesForReservationRoomByTeacher(new_reservation) +
						this.whereRoomReservedByTeacher(old_reservation) + " ;";
			}
			
	public void add(ReservaSalaProfessor reservation) throws ReservaException, SQLException {
		if(reservation == null){
			throw new ReservaException(NULL);
		}
		else{
			if(!this.teacherInDB(reservation.getProfessor())){
					throw new ReservaException(INEXISTENTTEACHER);
			}
			else{
				if(!this.roomInDB(reservation.getSala())){
					throw new ReservaException(INEXISTENTROOM);
				}
				else{
					if(this.roomInReservationDB(reservation.getSala(), reservation.getData(), reservation.getHora())){
						throw new ReservaException(UNAVAILABLEROOM);
					}
					else{ 
						if(this.reservainDB(reservation)){
							throw new ReservaException(EXISTENTRESERVATION);
						}
						else{
							if(this.studentInReservedDB(reservation.getData(), reservation.getHora())){
								super.executeQuery(this.deleteReservationByStudent(reservation));
							}
							if(this.pastDate(reservation.getData())){
								throw new ReservaException(PASTDATE);
							}
							if(this.equalDate(reservation.getData()))
							{
								if(this.validHour(reservation.getHora())){
									throw new ReservaException(PASTHOUR);
								}
								else{
									super.executeQuery(this.insertIntoReservationRoomByTeacher(reservation));
								}
							}
							else{
								super.executeQuery(this.insertIntoReservationRoomByTeacher(reservation));		
							}
						}
					}
				}
			}
		}
	}
	
	public void change(ReservaSalaProfessor old_reservation, ReservaSalaProfessor new_reservation) throws ReservaException, SQLException {
		if(old_reservation == null){
			throw new ReservaException(NULL);
		}		
		else{
			if(new_reservation == null){
				throw new ReservaException(NULL);
			}
			else{
				if(!this.reservainDB(old_reservation)){
					throw new ReservaException(INEXISTENTRESERVATION);
				}
				else{
					if(this.reservainDB(new_reservation)){
						throw new ReservaException(EXISTENTRESERVATION);
					}
					else{
						if(!this.teacherInDB(new_reservation.getProfessor())){
							throw new ReservaException(INEXISTENTTEACHER);
						}
						else{
							if(!this.roomInDB(new_reservation.getSala())){
								throw new ReservaException(INEXISTENTROOM);
							}
							else{ 
								if(!old_reservation.getData().equals(new_reservation.getData()) || !old_reservation.getHora().equals(new_reservation.getHora())) {
									if(this.roomInReservationDB(new_reservation.getSala(), new_reservation.getData(), new_reservation.getHora())){
										throw new ReservaException(UNAVAILABLEROOM);
									}
								}		
								if(this.pastDate(new_reservation.getData())){
									throw new ReservaException(PASTDATE);
								}
								if(this.validHour(new_reservation.getHora()) && this.equalDate(new_reservation.getData())){
									throw new ReservaException(PASTHOUR);
								}
								else{
									super.updateQuery(this.update(old_reservation, new_reservation));
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void delete(ReservaSalaProfessor reservation_to_delete) throws ReservaException, SQLException {
		if(reservation_to_delete == null){
			throw new ReservaException(NULL);
		}
		else{
			if(!this.reservainDB(reservation_to_delete)){
				throw new ReservaException(INEXISTENTRESERVATION);
			}
			else{
				super.executeQuery(this.deleteReservationByTeacher(reservation_to_delete));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Vector<ReservaSalaProfessor> searchAll() throws SQLException, ClienteException, PatrimonioException, ReservaException{
		return super.search("SELECT * FROM reserva_sala_professor " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_professor.id_sala " +
				"INNER JOIN professor ON professor.id_professor = reserva_sala_professor.id_professor;");
	}

	
	@SuppressWarnings("unchecked")
	public Vector<ReservaSalaProfessor> searchByDate(String date) throws SQLException, ClienteException, PatrimonioException, ReservaException{
		return super.search("SELECT * FROM reserva_sala_professor " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_professor.id_sala " +
				"INNER JOIN professor ON professor.id_professor = reserva_sala_professor.id_professor" +
				" WHERE data = \"" + this.defaltDate(date) + "\";");
	} 
	
	
	@Override
	protected Object fetch(ResultSet data_teacher) throws SQLException, ClienteException, PatrimonioException, ReservaException {
		Professor p = new Professor(data_teacher.getString("nome"), data_teacher.getString("cpf"), data_teacher.getString("matricula"),
				data_teacher.getString("telefone"), data_teacher.getString("email"));
		
		Sala s = new Sala(data_teacher.getString("codigo"), data_teacher.getString("descricao"), data_teacher.getString("capacidade"));
		
		ReservaSalaProfessor r = new ReservaSalaProfessor(data_teacher.getString("data"),data_teacher.getString("hora"),
				s ,data_teacher.getString("finalidade"), p);
		
		return r;
	}
	
	private boolean teacherInDB(Professor teacher) throws SQLException{
		return super.inDBGeneric("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + teacher.getNome() + "\" and " +
				"professor.cpf = \"" + teacher.getCpf() + "\" and " +
				"professor.telefone = \"" + teacher.getTelefone() + "\" and " +
				"professor.email = \"" + teacher.getEmail() + "\" and " +
				"professor.matricula = \"" + teacher.getMatricula() + "\";");
	}
	
	private boolean roomInDB(Sala room) throws SQLException{
		return super.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + room.getCodigo() + "\" and " +
				"sala.descricao = \"" + room.getDescricao() + "\" and " +
				"sala.capacidade = " + room.getCapacidade() +
				";");
	}
	
	private boolean roomInReservationDB(Sala room, String date, String hour) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
				"data = \"" + date + "\" and " +
				"hora = \"" + hour + "\" and " +
				"id_sala = (SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + room.getCodigo() + "\" and " +
				"sala.descricao = \"" + room.getDescricao() +  "\" and " +
				"sala.capacidade = " + room.getCapacidade() +" );");
	}
	
	private boolean reservainDB(ReservaSalaProfessor r) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
					"id_professor = (SELECT id_professor FROM professor WHERE " +
							"professor.nome = \"" + r.getProfessor().getNome() + "\" and " +
							"professor.cpf = \"" + r.getProfessor().getCpf() + "\" and " +
							"professor.telefone = \"" + r.getProfessor().getTelefone() + "\" and " +
							"professor.email = \"" + r.getProfessor().getEmail() + "\" and " +
							"professor.matricula = \"" + r.getProfessor().getMatricula() + "\") and " +
					"id_sala = (SELECT id_sala FROM sala WHERE " +
									"sala.codigo = \"" + r.getSala().getCodigo() + "\" and " +
									"sala.descricao = \"" + r.getSala().getDescricao() +  "\" and " +
									"sala.capacidade = " + r.getSala().getCapacidade() +" ) and " +
					"finalidade = \"" + r.getFinalidade() + "\" and " +
					"hora = \"" + r.getHora() + "\" and " +
					"data = \"" + r.getData() + "\";");
	}
	private boolean studentInReservedDB(String date, String hour) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
				"data = \"" + date + "\" and " +
				"hora = \"" + hour + "\";");
	}

	private String currenteDate(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}
	
	private String currentHour(){
		Date date = new Date(System.currentTimeMillis());
		return date.toString().substring(11, 16);
	}
	
	private boolean pastDate(String date){
		String now[] = this.currenteDate().split("[./-]");
		String splited_date[] = date.split("[./-]");
		
		int diference = now[2].length() - splited_date[2].length();
		splited_date[2] = now[2].substring(0, diference) + splited_date[2];
		
		if(Integer.parseInt(now[2]) > Integer.parseInt(splited_date[2])){
			return true;
		}
		else{
			//Nothing to do.
		}
		
		diference = now[1].length() - splited_date[1].length();
		splited_date[1] = now[1].substring(0, diference) + splited_date[1];
		
		if(Integer.parseInt(now[1]) > Integer.parseInt(splited_date[1])){
			return true;
		}
		else{
			if(Integer.parseInt(now[1]) == Integer.parseInt(splited_date[1])){
				diference = now[0].length() - splited_date[0].length();
				splited_date[0] = now[0].substring(0, diference) + splited_date[0];
				
				if(Integer.parseInt(now[0]) > Integer.parseInt(splited_date[0])){
					return true;
				}
				else{
					//Nothing to do.
				}
			}
		}
		return false;
	}
	
	public boolean equalDate(String date){
		date = this.defaltDate(date);
		String now[] = this.currenteDate().split("[./-]");
		String splited_date[] = date.split("[./-]");
		
		if(now[0].equals(splited_date[0]) && now[1].equals(splited_date[1]) && now[2].equals(splited_date[2])){
			return true;
		}
		else{
			//Nothing to do.
		}
		return false;
	}
	
	private boolean validHour(String hour){
		String now = this.currentHour();
		if(hour.length() == 4){
			hour = "0" + hour;
		}
		else{
			//Nothing to do.
		}
		if(Integer.parseInt(now.substring(0, 2)) > Integer.parseInt(hour.substring(0, 2))){
			return true;
		}
		else{
			if(Integer.parseInt(now.substring(0, 2)) == Integer.parseInt(hour.substring(0, 2))){
				if(Integer.parseInt(now.substring(3, 5)) > Integer.parseInt(hour.substring(3, 5))){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
	}
	
	private String defaltDate(String date){
		String now[] = currenteDate().split("[./-]");
		String splited_date[] = date.split("[./-]");
		String date_in_defalut = "";
		
		for(int i = 0; i < 3; i++){
			if(i == 0){
				date_in_defalut += now[i].substring(0, 
						now[i].length() - splited_date[i].length()) + splited_date[i];
			}
			else{
				date_in_defalut +=  "/" + now[i].substring(0, 
						now[i].length() - splited_date[i].length()) + splited_date[i];
			}
		}
		
		return date_in_defalut;
	}
	/*
	private String padronizarHora(String hora){
		if(hora.length() == 4)
			return "0" + hora;
		return hora;
	}*/
}