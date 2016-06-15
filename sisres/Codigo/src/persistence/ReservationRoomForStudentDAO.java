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

import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;
import model.Student;
import model.ReserveClassroomForStudent;
import model.Classroom;

@SuppressWarnings("unchecked")
public class ReservationRoomForStudentDAO extends DAO{
	
	//Mensagens e Alertas
		private final String NULL = "Termo nulo.";
		private final String STUDENTUNAVALIBLE = "O aluno possui uma reserva no mesmo dia e horario.";
		private final String ROOMUNAVALIBLE = "A Classroom esta reservada no mesmo dia e horario.";
		private final String INEXISTENTSTUDENT = "Student inexistente.";
		private final String INEXISTENTROOM = "Classroom inexistente";
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
		private String selectIdStudent(Student student){
			return "SELECT id_aluno FROM aluno WHERE " +
					"aluno.nome = \"" + student.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + student.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + student.getPhonePerson() + "\" and " +
					"aluno.email = \"" + student.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + student.getIdRegister() + "\"";
		}
		private String selectIdRoom(Classroom room){
			return "SELECT id_sala FROM sala WHERE " +
					"sala.codigo = \"" + room.getIdEquipment() + "\" and " +
					"sala.descricao = \"" + room.getDescriptionEquipment() +  "\" and " +
					"sala.capacidade = " + room.getDescriptionEquipment();
		}
		private String whereReservationRoomForStudent(ReserveClassroomForStudent reservation){
			return " WHERE " +
			"id_aluno = ( " + selectIdStudent(reservation.getStudent()) + " ) and " +
			"id_sala = ( " + selectIdRoom(reservation.getClassroom()) + " ) and " +
			"finalidade = \"" + reservation.getFinality() + "\" and " +
			"hora = \"" + reservation.getHour() + "\" and " +
			"data = \"" + reservation.getDate() + "\" and " +
			"cadeiras_reservadas = " + reservation.getReservedChairs();
		}
		
		private String valuesReservationForStudent(ReserveClassroomForStudent reservation){
			return "( " + selectIdStudent(reservation.getStudent()) + " ), " +
			"( " + selectIdRoom(reservation.getClassroom()) + " ), " +
			"\"" + reservation.getFinality() + "\", " +
			"\"" + reservation.getHour() + "\", " +
			"\"" + reservation.getDate() + "\", " +
			reservation.getReservedChairs();
		}
		private String reservationRoomForStudentWithManyAtributes(ReserveClassroomForStudent reservation){
			return "id_aluno = ( " + selectIdStudent(reservation.getStudent()) + " ), " +
			"id_sala = ( " + selectIdRoom(reservation.getClassroom()) + " ), " +
			"finalidade = \"" + reservation.getFinality() + "\", " +
			"hora = \"" + reservation.getHour() + "\", " +
			"data = \"" + reservation.getDate() + "\", " +
			"cadeiras_reservadas = " + reservation.getReservedChairs();
		}
		private String insertInto(ReserveClassroomForStudent reservation){
			return "INSERT INTO " +
					"reserva_sala_aluno (id_aluno, id_sala, finalidade, hora, data, cadeiras_reservadas) " +
					"VALUES ( " + valuesReservationForStudent(reservation) + " );";
		}
		private String update(ReserveClassroomForStudent old_reservation, ReserveClassroomForStudent changed_reservation){
			return "UPDATE reserva_sala_aluno SET " + 
					this.reservationRoomForStudentWithManyAtributes(changed_reservation) +
					this.whereReservationRoomForStudent(old_reservation) + " ;";
		}
		private String deleteFrom(ReserveClassroomForStudent reservation){
			return "DELETE FROM reserva_sala_aluno " + this.whereReservationRoomForStudent(reservation) + " ;";
		}

		
		
	public void add(ReserveClassroomForStudent reservation) throws ReserveException, SQLException, ClientException, PatrimonyException {
		if(reservation == null){
			throw new ReserveException(NULL);
		}
		else{ 
			if(!this.studentInDB(reservation.getStudent())){
				throw new ReserveException(INEXISTENTSTUDENT);
			}
			else{ 
				if(!this.roomInDB(reservation.getClassroom())){
					throw new ReserveException(INEXISTENTROOM);
				}
				else{
					if(this.roomReservedByTeacherInDB(reservation.getClassroom(), reservation.getDate(), reservation.getHour())){
						throw new ReserveException(ROOMUNAVALIBLE);
					}
					else{
						if(this.studentReservationDB(reservation.getStudent(), reservation.getDate(), reservation.getHour())){
						throw new ReserveException(STUDENTUNAVALIBLE);
						}
						else{
							if(!this.hasChairs(reservation.getReservedChairs(), reservation.getClassroom(), reservation.getDate(), reservation.getHour())){
								throw new ReserveException(UNAVAILABLECHAIR);
							}
							if(this.isValidDate(reservation.getDate())){
								throw new ReserveException(PASTDATE);
							}
							if(this.equalsDate(reservation.getDate()))
							{
								if(this.isValidHour(reservation.getHour())){
									throw new ReserveException(PASTHOUR);
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
	public void alterar(ReserveClassroomForStudent old_reservation, ReserveClassroomForStudent new_reservation) throws ReserveException, SQLException, ClientException, PatrimonyException{
		if(old_reservation == null){
			throw new ReserveException(NULL);
		}
		else{
			if(new_reservation == null){
				throw new ReserveException(NULL);
			}
			else{ 
				if(!this.reservationInDB(old_reservation)){
					throw new ReserveException(INEXISTENTRESERVATION);
				}
				else{
					if(this.reservationInDB(new_reservation)){
						throw new ReserveException(EXISTENTRESERVATION);
					}
					else{
						if(!this.studentInDB(new_reservation.getStudent())){
							throw new ReserveException(INEXISTENTSTUDENT);
						}
						else{
							if(!this.roomInDB(new_reservation.getClassroom())){
								throw new ReserveException(INEXISTENTROOM);
							}
							else{
								if(!old_reservation.getDate().equals(new_reservation.getDate()) || !old_reservation.getHour().equals(new_reservation.getHour())){
									if(this.studentReservationDB(new_reservation.getStudent(), new_reservation.getDate(), new_reservation.getHour())){
										throw new ReserveException(STUDENTUNAVALIBLE);
									}
									else{ 
										if(this.roomReservedByTeacherInDB(new_reservation.getClassroom(), new_reservation.getDate(), new_reservation.getHour())){
											throw new ReserveException(ROOMUNAVALIBLE);
										}
									}
								if(!this.hasChairs(""+(Integer.parseInt(new_reservation.getReservedChairs()) - 
										Integer.parseInt(old_reservation.getReservedChairs())), new_reservation.getClassroom(), 
										new_reservation.getDate(), new_reservation.getHour())){
									throw new ReserveException(UNAVAILABLECHAIR);
								}
								if(this.isValidDate(new_reservation.getDate()))
									throw new ReserveException(PASTDATE);
								}
								if(this.isValidHour(new_reservation.getHour()) && this.equalsDate(new_reservation.getDate())){
									throw new ReserveException(PASTHOUR);
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
	
	public void delete(ReserveClassroomForStudent reservation) throws ReserveException, SQLException {
		if(reservation == null){
			throw new ReserveException(NULL);
		}
		else{
			if(!this.reservationInDB(reservation)){
				throw new ReserveException(INEXISTENTRESERVATION);
			}
			else{
				super.executeQuery(this.deleteFrom(reservation));
			}
		}
	}
	
	public Vector<ReserveClassroomForStudent> searchAll() throws SQLException, ClientException, PatrimonyException, ReserveException{
		return super.search("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno;");
	}
	public Vector<ReserveClassroomForStudent> searchbyDay(String date) throws SQLException, ClientException, PatrimonyException, ReserveException{
		date = this.mountDefaultDate(date);
		return super.search("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno " +
				"WHERE data = \""+ date + "\";");
	}
	public Vector<ReserveClassroomForStudent> searchByHour(String hour) 
			throws SQLException, ClientException, PatrimonyException, ReserveException{
		hour = this.mountDefaultHour(hour);
		return super.search("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno " +
				" WHERE hora = \"" + hour +"\";");
	}

	
	public int availableChair(Classroom room, String date, String hour) 
			throws SQLException, PatrimonyException, ClientException, ReserveException{
		date = this.mountDefaultDate(date);
		hour = this.mountDefaultHour(hour);
		Vector<ReserveClassroomForStudent> vet = this.searchAll();
		Iterator<ReserveClassroomForStudent> it = vet.iterator();
		int total = Integer.parseInt(room.getDescriptionEquipment());
		while(it.hasNext()){
			ReserveClassroomForStudent reservation = it.next();
			if(reservation.getClassroom().equals(room) && reservation.getDate().equals(date) && reservation.getHour().equals(hour))
				total -= Integer.parseInt(reservation.getReservedChairs());
		}
		return total;
	}
	
	
	private boolean hasChairs(String reserved_chairs, Classroom room, String date, String hour) 
			throws SQLException, ClientException, PatrimonyException, ReserveException {
		if(this.availableChair(room, date, hour) >= Integer.parseInt(reserved_chairs))
			return true;
		return false;
	}
	
	@Override
	protected Object fetch(ResultSet student_data) throws SQLException, ClientException, PatrimonyException, ReserveException {
		Student student = new Student(student_data.getString("nome"), student_data.getString("cpf"), student_data.getString("matricula"),
				student_data.getString("telefone"), student_data.getString("email"));
		
		Classroom room = new Classroom(student_data.getString("codigo"), student_data.getString("descricao"), student_data.getString("capacidade"));
		
		ReserveClassroomForStudent room_reservation = new ReserveClassroomForStudent(student_data.getString("data"),student_data.getString("hora"),
				room ,student_data.getString("finalidade"),student_data.getString("cadeiras_reservadas"), student);
		
		return room_reservation;
	}
	
	private boolean studentInDB(Student student) throws SQLException{
		return super.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + student.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + student.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + student.getPhonePerson() + "\" and " +
				"aluno.email = \"" + student.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + student.getIdRegister() + "\";");
	}
	
	private boolean roomInDB(Classroom room) throws SQLException{
		return super.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + room.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + room.getDescriptionEquipment() + "\" and " +
				"sala.capacidade = " + room.getDescriptionEquipment() +
				";");
	}
	
	private boolean studentReservationDB(Student student, String date, String hour) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
				"data = \"" + date + "\" and " +
				"hora = \"" + hour + "\" and " +
				"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
				"aluno.nome = \"" + student.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + student.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + student.getPhonePerson() + "\" and " +
				"aluno.email = \"" + student.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + student.getIdRegister() + "\");");
	}
	private boolean roomReservedByTeacherInDB(Classroom room, String date, String hour) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
				"data = \"" + this.mountDefaultDate(date) + "\" and " +
				"hora = \"" + this.mountDefaultHour(hour) + "\" and " +
				"id_sala = (SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + room.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + room.getDescriptionEquipment() +  "\" and " +
				"sala.capacidade = " + room.getDescriptionEquipment() +" );");
	}
	
	private boolean reservationInDB(ReserveClassroomForStudent reservation) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
					"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
							"aluno.nome = \"" + reservation.getStudent().getNamePerson() + "\" and " +
							"aluno.cpf = \"" + reservation.getStudent().getCpfPerson() + "\" and " +
							"aluno.telefone = \"" + reservation.getStudent().getPhonePerson() + "\" and " +
							"aluno.email = \"" + reservation.getStudent().getEmailPerson() + "\" and " +
							"aluno.matricula = \"" + reservation.getStudent().getIdRegister() + "\") and " +
					"id_sala = (SELECT id_sala FROM sala WHERE " +
									"sala.codigo = \"" + reservation.getClassroom().getClass() + "\" and " +
									"sala.descricao = \"" + reservation.getClassroom().getDescriptionEquipment() +  "\" and " +
									"sala.capacidade = " + reservation.getClassroom().getCapacity() +" ) and " +
					"finalidade = \"" + reservation.getFinality() + "\" and " +
					"hora = \"" + reservation.getHour() + "\" and " +
					"data = \"" + reservation.getDate() + "\" and " +
					"cadeiras_reservadas = " + reservation.getReservedChairs() + ";");
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
