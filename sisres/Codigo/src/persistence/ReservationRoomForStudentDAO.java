/*
 * File: ReservationRoomForStudentDAO.java
 * Description: Class to make transactions with the database and reservation room for student
 * */

package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

import model.Aluno;
import model.ReservaSalaAluno;
import model.Sala;

@SuppressWarnings("unchecked")
public class ReservationRoomForStudentDAO extends DAO{
	
	//Mensagens e Alertas
		private final String NULL = "Termo nulo.";
		private final String STUDENTUNAVALIBLE = "O aluno possui uma reserva no mesmo dia e horario.";
		private final String ROOMUNAVALIBLE = "A Sala esta reservada no mesmo dia e horario.";
		private final String INEXISTENTSTUDENT = "Aluno inexistente.";
		private final String INEXISTENTROOM = "Sala inexistente";
		private final String INEXISTENTRESERVATION = "Reserva inexistente";
		private final String EXISTENTRESERVATION = "A reserva ja existe.";
		private final String UNAVAILABLECHAIR = "O numero de cadeiras reservadas esta indisponivel para esta sala.";
		private final String PASTDATE = "A data escolhida ja passou.";
		private final String PASTHOUR = "A hora escolhida ja passou.";

	
	//Singleton
		private static ReservationRoomForStudentDAO instance;
		private ReservationRoomForStudentDAO(){
		}
		public static ReservationRoomForStudentDAO getInstance(){
			if(instance == null){
				instance = new ReservationRoomForStudentDAO();
			}
			else{
				// Nothing to do.
			}
			return instance;
		}
	//
		
		
	//Querys de Reuso
		private String selectIdStudent(Aluno student){
			return "SELECT id_aluno FROM aluno WHERE " +
					"aluno.nome = \"" + student.getNome() + "\" and " +
					"aluno.cpf = \"" + student.getCpf() + "\" and " +
					"aluno.telefone = \"" + student.getTelefone() + "\" and " +
					"aluno.email = \"" + student.getEmail() + "\" and " +
					"aluno.matricula = \"" + student.getMatricula() + "\"";
		}
		private String selectIdRoom(Sala room){
			return "SELECT id_sala FROM sala WHERE " +
					"sala.codigo = \"" + room.getCodigo() + "\" and " +
					"sala.descricao = \"" + room.getDescricao() +  "\" and " +
					"sala.capacidade = " + room.getCapacidade();
		}
		private String whereReservationRoomForStudent(ReservaSalaAluno reservation){
			return " WHERE " +
			"id_aluno = ( " + selectIdStudent(reservation.getAluno()) + " ) and " +
			"id_sala = ( " + selectIdRoom(reservation.getSala()) + " ) and " +
			"finalidade = \"" + reservation.getFinalidade() + "\" and " +
			"hora = \"" + reservation.getHora() + "\" and " +
			"data = \"" + reservation.getData() + "\" and " +
			"cadeiras_reservadas = " + reservation.getCadeiras_reservadas();
		}
		private String valuesReservationForStudent(ReservaSalaAluno reservation){
			return "( " + selectIdStudent(reservation.getAluno()) + " ), " +
			"( " + selectIdRoom(reservation.getSala()) + " ), " +
			"\"" + reservation.getFinalidade() + "\", " +
			"\"" + reservation.getHora() + "\", " +
			"\"" + reservation.getData() + "\", " +
			reservation.getCadeiras_reservadas();
		}
		private String reservationRoomForStudentWithManyAtributes(ReservaSalaAluno reservation){
			return "id_aluno = ( " + selectIdStudent(reservation.getAluno()) + " ), " +
			"id_sala = ( " + selectIdRoom(reservation.getSala()) + " ), " +
			"finalidade = \"" + reservation.getFinalidade() + "\", " +
			"hora = \"" + reservation.getHora() + "\", " +
			"data = \"" + reservation.getData() + "\", " +
			"cadeiras_reservadas = " + reservation.getCadeiras_reservadas();
		}
		private String insertInto(ReservaSalaAluno reservation){
			return "INSERT INTO " +
					"reserva_sala_aluno (id_aluno, id_sala, finalidade, hora, data, cadeiras_reservadas) " +
					"VALUES ( " + valuesReservationForStudent(reservation) + " );";
		}
		private String update(ReservaSalaAluno old_reservation, ReservaSalaAluno changed_reservation){
			return "UPDATE reserva_sala_aluno SET " + 
					this.reservationRoomForStudentWithManyAtributes(changed_reservation) +
					this.whereReservationRoomForStudent(old_reservation) + " ;";
		}
		private String deleteFrom(ReservaSalaAluno reservation){
			return "DELETE FROM reserva_sala_aluno " + this.whereReservationRoomForStudent(reservation) + " ;";
		}

		
		
	public void add(ReservaSalaAluno reservation) throws ReservaException, SQLException, ClienteException, PatrimonioException {
		if(reservation == null){
			throw new ReservaException(NULL);
		}
		else{ 
			if(!this.studentInDB(reservation.getAluno())){
				throw new ReservaException(INEXISTENTSTUDENT);
			}
			else{ 
				if(!this.roomInDB(reservation.getSala())){
					throw new ReservaException(INEXISTENTROOM);
				}
				else{
					if(this.roomReservedByTeacherInDB(reservation.getSala(), reservation.getData(), reservation.getHora())){
						throw new ReservaException(ROOMUNAVALIBLE);
					}
					else{
						if(this.studentReservationDB(reservation.getAluno(), reservation.getData(), reservation.getHora())){
						throw new ReservaException(STUDENTUNAVALIBLE);
						}
						else{
							if(!this.hasChairs(reservation.getCadeiras_reservadas(), reservation.getSala(), reservation.getData(), reservation.getHora())){
								throw new ReservaException(UNAVAILABLECHAIR);
							}
							if(this.isValidDate(reservation.getData())){
								throw new ReservaException(PASTDATE);
							}
							if(this.equalsDate(reservation.getData()))
							{
								if(this.isValidHour(reservation.getHora())){
									throw new ReservaException(PASTHOUR);
								}
								else{
									super.executeQuery(this.insertInto(reservation));
								}
							}
							else{
								super.executeQuery(this.insertInto(reservation));
							}
						}
					}
				}
			}
		}
	}
	public void alterar(ReservaSalaAluno r, ReservaSalaAluno r_new) throws ReservaException, SQLException, ClienteException, PatrimonioException{
		if(r == null){
			throw new ReservaException(NULL);
		}
		else{
			if(r_new == null){
				throw new ReservaException(NULL);
			}
			else{ 
				if(!this.reservationInDB(r)){
					throw new ReservaException(INEXISTENTRESERVATION);
				}
				else{
					if(this.reservationInDB(r_new)){
						throw new ReservaException(EXISTENTRESERVATION);
					}
					else{
						if(!this.studentInDB(r_new.getAluno())){
							throw new ReservaException(INEXISTENTSTUDENT);
						}
						else{
							if(!this.roomInDB(r_new.getSala())){
								throw new ReservaException(INEXISTENTROOM);
							}
							else{
								if(!r.getData().equals(r_new.getData()) || !r.getHora().equals(r_new.getHora())){
									if(this.studentReservationDB(r_new.getAluno(), r_new.getData(), r_new.getHora())){
										throw new ReservaException(STUDENTUNAVALIBLE);
									}
									else{ 
										if(this.roomReservedByTeacherInDB(r_new.getSala(), r_new.getData(), r_new.getHora())){
											throw new ReservaException(ROOMUNAVALIBLE);
										}
									}
								if(!this.hasChairs(""+(Integer.parseInt(r_new.getCadeiras_reservadas()) - 
										Integer.parseInt(r.getCadeiras_reservadas())), r_new.getSala(), 
										r_new.getData(), r_new.getHora())){
									throw new ReservaException(UNAVAILABLECHAIR);
								}
								if(this.isValidDate(r_new.getData()))
									throw new ReservaException(PASTDATE);
								}
								if(this.isValidHour(r_new.getHora()) && this.equalsDate(r_new.getData())){
									throw new ReservaException(PASTHOUR);
								}
								else{
									super.updateQuery(this.update(r, r_new));
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void delete(ReservaSalaAluno reservation) throws ReservaException, SQLException {
		if(reservation == null){
			throw new ReservaException(NULL);
		}
		else{
			if(!this.reservationInDB(reservation)){
				throw new ReservaException(INEXISTENTRESERVATION);
			}
			else{
				super.executeQuery(this.deleteFrom(reservation));
			}
		}
	}
	
	public Vector<ReservaSalaAluno> searchAll() throws SQLException, ClienteException, PatrimonioException, ReservaException{
		return super.buscar("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno;");
	}
	public Vector<ReservaSalaAluno> searchbyDay(String date) throws SQLException, ClienteException, PatrimonioException, ReservaException{
		date = this.mountDefaultDate(date);
		return super.buscar("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno " +
				"WHERE data = \""+ date + "\";");
	}
	public Vector<ReservaSalaAluno> searchByHour(String hour) 
			throws SQLException, ClienteException, PatrimonioException, ReservaException{
		hour = this.mountDefaultHour(hour);
		return super.buscar("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno " +
				" WHERE hora = \"" + hour +"\";");
	}

	
	public int availableChair(Sala room, String date, String hour) 
			throws SQLException, PatrimonioException, ClienteException, ReservaException{
		date = this.mountDefaultDate(date);
		hour = this.mountDefaultHour(hour);
		Vector<ReservaSalaAluno> vet = this.searchAll();
		Iterator<ReservaSalaAluno> it = vet.iterator();
		int total = Integer.parseInt(room.getCapacidade());
		while(it.hasNext()){
			ReservaSalaAluno reservation = it.next();
			if(reservation.getSala().equals(room) && reservation.getData().equals(date) && reservation.getHora().equals(hour))
				total -= Integer.parseInt(reservation.getCadeiras_reservadas());
		}
		return total;
	}
	
	
	private boolean hasChairs(String reserved_chairs, Sala room, String date, String hour) 
			throws SQLException, ClienteException, PatrimonioException, ReservaException {
		if(this.availableChair(room, date, hour) >= Integer.parseInt(reserved_chairs))
			return true;
		return false;
	}
	
	@Override
	protected Object fetch(ResultSet student_data) throws SQLException, ClienteException, PatrimonioException, ReservaException {
		Aluno student = new Aluno(student_data.getString("nome"), student_data.getString("cpf"), student_data.getString("matricula"),
				student_data.getString("telefone"), student_data.getString("email"));
		
		Sala room = new Sala(student_data.getString("codigo"), student_data.getString("descricao"), student_data.getString("capacidade"));
		
		ReservaSalaAluno room_reservation = new ReservaSalaAluno(student_data.getString("data"),student_data.getString("hora"),
				room ,student_data.getString("finalidade"),student_data.getString("cadeiras_reservadas"), student);
		
		return room_reservation;
	}
	
	private boolean studentInDB(Aluno student) throws SQLException{
		return super.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + student.getNome() + "\" and " +
				"aluno.cpf = \"" + student.getCpf() + "\" and " +
				"aluno.telefone = \"" + student.getTelefone() + "\" and " +
				"aluno.email = \"" + student.getEmail() + "\" and " +
				"aluno.matricula = \"" + student.getMatricula() + "\";");
	}
	
	private boolean roomInDB(Sala room) throws SQLException{
		return super.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + room.getCodigo() + "\" and " +
				"sala.descricao = \"" + room.getDescricao() + "\" and " +
				"sala.capacidade = " + room.getCapacidade() +
				";");
	}
	
	private boolean studentReservationDB(Aluno student, String date, String hour) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
				"data = \"" + date + "\" and " +
				"hora = \"" + hour + "\" and " +
				"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
				"aluno.nome = \"" + student.getNome() + "\" and " +
				"aluno.cpf = \"" + student.getCpf() + "\" and " +
				"aluno.telefone = \"" + student.getTelefone() + "\" and " +
				"aluno.email = \"" + student.getEmail() + "\" and " +
				"aluno.matricula = \"" + student.getMatricula() + "\");");
	}
	private boolean roomReservedByTeacherInDB(Sala room, String date, String hour) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
				"data = \"" + this.mountDefaultDate(date) + "\" and " +
				"hora = \"" + this.mountDefaultHour(hour) + "\" and " +
				"id_sala = (SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + room.getCodigo() + "\" and " +
				"sala.descricao = \"" + room.getDescricao() +  "\" and " +
				"sala.capacidade = " + room.getCapacidade() +" );");
	}
	
	private boolean reservationInDB(ReservaSalaAluno reservation) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
					"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
							"aluno.nome = \"" + reservation.getAluno().getNome() + "\" and " +
							"aluno.cpf = \"" + reservation.getAluno().getCpf() + "\" and " +
							"aluno.telefone = \"" + reservation.getAluno().getTelefone() + "\" and " +
							"aluno.email = \"" + reservation.getAluno().getEmail() + "\" and " +
							"aluno.matricula = \"" + reservation.getAluno().getMatricula() + "\") and " +
					"id_sala = (SELECT id_sala FROM sala WHERE " +
									"sala.codigo = \"" + reservation.getSala().getCodigo() + "\" and " +
									"sala.descricao = \"" + reservation.getSala().getDescricao() +  "\" and " +
									"sala.capacidade = " + reservation.getSala().getCapacidade() +" ) and " +
					"finalidade = \"" + reservation.getFinalidade() + "\" and " +
					"hora = \"" + reservation.getHora() + "\" and " +
					"data = \"" + reservation.getData() + "\" and " +
					"cadeiras_reservadas = " + reservation.getCadeiras_reservadas() + ";");
	}

	private String currentDate(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}
	
	private String currentHour(){
		Date date = new Date(System.currentTimeMillis());
		return date.toString().substring(11, 16);
	}
	
	private boolean isValidDate(String date){
		String split_current_date[] = this.currentDate().split("[./-]");
		String split_date[] = date.split("[./-]");
		
		int dif = split_current_date[2].length() - split_date[2].length();
		split_date[2] = split_current_date[2].substring(0, dif) + split_date[2];
		
		if(Integer.parseInt(split_current_date[2]) > Integer.parseInt(split_date[2]))
			return true;
		
		dif = split_current_date[1].length() - split_date[1].length();
		split_date[1] = split_current_date[1].substring(0, dif) + split_date[1];
		
		if(Integer.parseInt(split_current_date[1]) > Integer.parseInt(split_date[1]))
			return true;
		else if(Integer.parseInt(split_current_date[1]) == Integer.parseInt(split_date[1])){
			dif = split_current_date[0].length() - split_date[0].length();
			split_date[0] = split_current_date[0].substring(0, dif) + split_date[0];
			
			if(Integer.parseInt(split_current_date[0]) > Integer.parseInt(split_date[0]))
				return true;
		}
		return false;
	}
	
	public boolean equalsDate(String date){
		date = this.mountDefaultDate(date);
		String split_current_date[] = this.currentDate().split("[./-]");
		String split_date[] = date.split("[./-]");
		
		if(split_current_date[0].equals(split_date[0]) && split_current_date[1].equals(split_date[1]) && split_current_date[2].equals(split_date[2]))
			return true;
		return false;
	}
	
	private boolean isValidHour(String hour){
		String now = this.currentHour();
		if(hour.length() == 4)
			hour = "0" + hour;
		if(Integer.parseInt(now.substring(0, 2)) > Integer.parseInt(hour.substring(0, 2)))
			return true;
		else if(Integer.parseInt(now.substring(0, 2)) == Integer.parseInt(hour.substring(0, 2))){
			if(Integer.parseInt(now.substring(3, 5)) > Integer.parseInt(hour.substring(3, 5)))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	private String mountDefaultDate(String date){
		String now[] = currentDate().split("[./-]");
		String parts_of_the_date[] = date.split("[./-]");
		String date_in_default_model = "";
		
		for(int i = 0; i < 3; i++){
			if(i == 0)
				date_in_default_model += now[i].substring(0, 
						now[i].length() - parts_of_the_date[i].length()) + parts_of_the_date[i];
			else
				date_in_default_model +=  "/" + now[i].substring(0, 
						now[i].length() - parts_of_the_date[i].length()) + parts_of_the_date[i];
				
		}
		
		return date_in_default_model;
	}
	
	private String mountDefaultHour(String hour){
		if(hour.length() == 4)
			return "0" + hour;
		return hour;
	}
	
}
