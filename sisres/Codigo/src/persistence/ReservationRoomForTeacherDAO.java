/**
 * Description: Class to make transactions with the database and reservation room for teacher
 * */

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

public class ReservationRoomForTeacherDAO extends DAO {

	// Constants with error message
	private final String NULL = "Termo nulo.";
	private final String UNAVAILABLEROOM = "A Sala esta reservada no mesmo dia e horario.";
	private final String INEXISTENTTEACHER = "Professor inexistente.";
	private final String INEXISTENTROOM = "Sala inexistente";
	private final String INEXISTENTRESERVATION = "Reserva inexistente";
	private final String EXISTENTRESERVATION = "A reserva ja existe.";
	private final String PASTDATE = "A data escolhida ja passou.";
	private final String PASTHOUR = "A hora escolhida ja passou.";

	/**
	 * Current instance
	 */
	
	private static ReservationRoomForTeacherDAO instance;

	private ReservationRoomForTeacherDAO() {
	}
	
	/**
	 * Method to provider current instance or create a new.
	 * 
	 * @return instance
	 */

	public static ReservationRoomForTeacherDAO getInstance() {
		if (instance != null){
			//Nothing to do
		} else{
			instance = new ReservationRoomForTeacherDAO();
		}
		return instance;
	}

	/**
	 * Method to select a teacher by his ID
	 * 
	 * @param teacher
	 * @return the teacher found
	 */
	private String selectIdTeacher(Professor teacher) {
		return "SELECT id_professor FROM professor WHERE " + "professor.nome = \"" + teacher.getNome() + "\" and "
				+ "professor.cpf = \"" + teacher.getCpf() + "\" and " + "professor.telefone = \""
				+ teacher.getTelefone() + "\" and " + "professor.email = \"" + teacher.getEmail() + "\" and "
				+ "professor.matricula = \"" + teacher.getMatricula() + "\"";
	}

	/**
	 * Method to select a room by its ID 
	 * 
	 * @param room
	 * @return the room found
	 */
	
	private String selectByIdRoom(Classroom room) {
		return "SELECT id_sala FROM sala WHERE " + "sala.codigo = \"" + room.getCode() + "\" and "
				+ "sala.descricao = \"" + room.getDescription() + "\" and " + "sala.capacidade = " + room.getCapacity();
	}

	/**
	 * Method to select in the database the classroom reserved by the teacher
	 * 
	 * @param reservation
	 * @return Classrooms reserved by the teacher
	 */
	private String whereRoomReservedByTeacher(ReservaSalaProfessor reservation) {
		return " WHERE " + "id_professor = ( " + selectIdTeacher(reservation.getProfessor()) + " ) and "
				+ "id_sala = ( " + selectByIdRoom(reservation.getSala()) + " ) and " + "finalidade = \""
				+ reservation.getFinalidade() + "\" and " + "hora = \"" + reservation.getHora() + "\" and "
				+ "data = \"" + reservation.getData() + "\"";
	}

	/**
	 * Method to get the values (purpose , time and date) about a reserved room by the teacher
	 * 
	 * @param reservation
	 * @return information about the room reserved
	 */
	private String valuesRoomReservedByTeacher(ReservaSalaProfessor reservation) {
		return "( " + selectIdTeacher(reservation.getProfessor()) + " ), " + "( "
				+ selectByIdRoom(reservation.getSala()) + " ), " + "\"" + reservation.getFinalidade() + "\", " + "\""
				+ reservation.getHora() + "\", " + "\"" + reservation.getData() + "\"";
	}

	/**
	 * Method 
	 * 
	 * @param reservation
	 * @return
	 */
	private String atributesValuesForReservationRoomByTeacher(ReservaSalaProfessor reservation) {
		return "id_professor = ( " + selectIdTeacher(reservation.getProfessor()) + " ), " + "id_sala = ( "
				+ selectByIdRoom(reservation.getSala()) + " ), " + "finalidade = \"" + reservation.getFinalidade()
				+ "\", " + "hora = \"" + reservation.getHora() + "\", " + "data = \"" + reservation.getData() + "\"";
	}

	/**
	 * Method to add the values (Teacher Identity, Classroom Identity, purpose, time and date) to the Classroom 
	 * reserved by the teacher 
	 * 
	 * @param reservation
	 * @return addiction of values about the reservation
	 */
	private String insertIntoReservationRoomByTeacher(ReservaSalaProfessor reservation) {
		return "INSERT INTO " + "reserva_sala_professor (id_professor, id_sala, finalidade, hora, data) " + "VALUES ( "
				+ valuesRoomReservedByTeacher(reservation) + " );";
	}

	/**
	 * Method to delete the values (Teacher Identity, Classroom Identity, purpose, time and date) to the Classroom 
	 * reserved by the teacher 
	 * 
	 * @param reservation
	 * @return exclusion of values about reservation
	 */
	private String deleteReservationByTeacher(ReservaSalaProfessor reservation) {
		return "DELETE FROM reserva_sala_professor " + this.whereRoomReservedByTeacher(reservation) + " ;";
	}

	/**
	 * Method to delete the values to the Classroom reserved by the student 
	 * 
	 * @param reservation
	 * @return 
	 */
	private String deleteReservationByStudent(ReservaSalaProfessor reservation) {
		return "DELETE FROM reserva_sala_aluno WHERE " + "hora = \"" + reservation.getHora() + "\" and " + "data = \""
				+ reservation.getData() + "\" ;";
	}

	/**
	 * 
	 * @param old_reservation
	 * @param new_reservation
	 * @return exclusion of values about reservation
	 */
	private String update(ReservaSalaProfessor old_reservation, ReservaSalaProfessor new_reservation) {
		return "UPDATE reserva_sala_professor SET " + this.atributesValuesForReservationRoomByTeacher(new_reservation)
				+ this.whereRoomReservedByTeacher(old_reservation) + " ;";
	}

	/**
	 * Method to add a reservation made by the professor
	 * 
	 * @param reservation
	 * @throws ReservaException
	 * @throws SQLException
	 */
	public void add(ReservaSalaProfessor reservation) throws ReservaException, SQLException {
		if (reservation == null) {
			throw new ReservaException(NULL);
		} else {
			if (!this.teacherInDB(reservation.getProfessor())) {
				throw new ReservaException(INEXISTENTTEACHER);
			} else {
				if (!this.roomInDB(reservation.getSala())) {
					throw new ReservaException(INEXISTENTROOM);
				} else {
					if (this.roomInReservationDB(reservation.getSala(), reservation.getData(), reservation.getHora())) {
						throw new ReservaException(UNAVAILABLEROOM);
					} else {
						if (this.reservainDB(reservation)) {
							throw new ReservaException(EXISTENTRESERVATION);
						} else {
							if (this.studentInReservedDB(reservation.getData(), reservation.getHora())) {
								super.executeQuery(this.deleteReservationByStudent(reservation));
							}
							if (this.pastDate(reservation.getData())) {
								throw new ReservaException(PASTDATE);
							}
							if (this.equalDate(reservation.getData())) {
								if (this.validHour(reservation.getHora())) {
									throw new ReservaException(PASTHOUR);
								} else {
									super.executeQuery(this.insertIntoReservationRoomByTeacher(reservation));
								}
							} else {
								super.executeQuery(this.insertIntoReservationRoomByTeacher(reservation));
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Method to update a reservation made by the professor
	 * 
	 * @param old_reservation
	 * @param new_reservation
	 * @throws ReservaException
	 * @throws SQLException
	 */
	public void change(ReservaSalaProfessor old_reservation, ReservaSalaProfessor new_reservation)
			throws ReservaException, SQLException {
		if (old_reservation == null) {
			throw new ReservaException(NULL);
		} else {
			if (new_reservation == null) {
				throw new ReservaException(NULL);
			} else {
				if (!this.reservainDB(old_reservation)) {
					throw new ReservaException(INEXISTENTRESERVATION);
				} else {
					if (this.reservainDB(new_reservation)) {
						throw new ReservaException(EXISTENTRESERVATION);
					} else {
						if (!this.teacherInDB(new_reservation.getProfessor())) {
							throw new ReservaException(INEXISTENTTEACHER);
						} else {
							if (!this.roomInDB(new_reservation.getSala())) {
								throw new ReservaException(INEXISTENTROOM);
							} else {
								if (!old_reservation.getData().equals(new_reservation.getData())
										|| !old_reservation.getHora().equals(new_reservation.getHora())) {
									if (this.roomInReservationDB(new_reservation.getSala(), new_reservation.getData(),
											new_reservation.getHora())) {
										throw new ReservaException(UNAVAILABLEROOM);
									}
								}
								if (this.pastDate(new_reservation.getData())) {
									throw new ReservaException(PASTDATE);
								}
								if (this.validHour(new_reservation.getHora())
										&& this.equalDate(new_reservation.getData())) {
									throw new ReservaException(PASTHOUR);
								} else {
									super.updateQuery(this.update(old_reservation, new_reservation));
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Method to delete a reservation made by the professor
	 * 
	 * @param reservation_to_delete
	 * @throws ReservaException
	 * @throws SQLException
	 */
	public void delete(ReservaSalaProfessor reservation_to_delete) throws ReservaException, SQLException {
		if (reservation_to_delete == null) {
			throw new ReservaException(NULL);
		} else {
			if (!this.reservainDB(reservation_to_delete)) {
				throw new ReservaException(INEXISTENTRESERVATION);
			} else {
				super.executeQuery(this.deleteReservationByTeacher(reservation_to_delete));
			}
		}
	}

	/**
	 * Method to display the list of classrooms reserved with the respective teacher.
	 * 
	 * @return list of classroom reserved with the respective teacher.
	 * @throws SQLException
	 * @throws ClienteException
	 * @throws PatrimonyException
	 * @throws ReservaException
	 */
	@SuppressWarnings("unchecked")
	public Vector<ReservaSalaProfessor> searchAll()
			throws SQLException, ClienteException, PatrimonyException, ReservaException {
		return super.search("SELECT * FROM reserva_sala_professor "
				+ "INNER JOIN sala ON sala.id_sala = reserva_sala_professor.id_sala "
				+ "INNER JOIN professor ON professor.id_professor = reserva_sala_professor.id_professor;");
	}

	/**
	 * Method to display the list of classrooms reserved with the respective teacher according to a date.
	 * 
	 * @param date
	 * @return list of classroom reserved with the respective teacher according to a date.
	 * @throws SQLException
	 * @throws ClienteException
	 * @throws PatrimonyException
	 * @throws ReservaException
	 */
	@SuppressWarnings("unchecked")
	public Vector<ReservaSalaProfessor> searchByDate(String date)
			throws SQLException, ClienteException, PatrimonyException, ReservaException {
		return super.search("SELECT * FROM reserva_sala_professor "
				+ "INNER JOIN sala ON sala.id_sala = reserva_sala_professor.id_sala "
				+ "INNER JOIN professor ON professor.id_professor = reserva_sala_professor.id_professor"
				+ " WHERE data = \"" + this.defaultDate(date) + "\";");
	}

	@Override
	protected Object fetch(ResultSet data_teacher)
			throws SQLException, ClienteException, PatrimonyException, ReservaException {
		Professor p = new Professor(data_teacher.getString("nome"), data_teacher.getString("cpf"),
				data_teacher.getString("matricula"), data_teacher.getString("telefone"),
				data_teacher.getString("email"));

		Classroom s = new Classroom(data_teacher.getString("codigo"), data_teacher.getString("descricao"),
				data_teacher.getString("capacidade"));

		ReservaSalaProfessor r = new ReservaSalaProfessor(data_teacher.getString("data"),
				data_teacher.getString("hora"), s, data_teacher.getString("finalidade"), p);

		return r;
	}


	private boolean teacherInDB(Professor teacher) throws SQLException {
		return super.inDBGeneric("SELECT * FROM professor WHERE " + "professor.nome = \"" + teacher.getNome()
				+ "\" and " + "professor.cpf = \"" + teacher.getCpf() + "\" and " + "professor.telefone = \""
				+ teacher.getTelefone() + "\" and " + "professor.email = \"" + teacher.getEmail() + "\" and "
				+ "professor.matricula = \"" + teacher.getMatricula() + "\";");
	}


	private boolean roomInDB(Classroom room) throws SQLException {
		return super.inDBGeneric(
				"SELECT * FROM sala WHERE " + "sala.codigo = \"" + room.getCode() + "\" and " + "sala.descricao = \""
						+ room.getDescription() + "\" and " + "sala.capacidade = " + room.getCapacity() + ";");
	}


	private boolean roomInReservationDB(Classroom room, String date, String hour) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " + "data = \"" + date + "\" and "
				+ "hora = \"" + hour + "\" and " + "id_sala = (SELECT id_sala FROM sala WHERE " + "sala.codigo = \""
				+ room.getCode() + "\" and " + "sala.descricao = \"" + room.getDescription() + "\" and "
				+ "sala.capacidade = " + room.getCapacity() + " );");
	}


	private boolean reservainDB(ReservaSalaProfessor data_result) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE "
				+ "id_professor = (SELECT id_professor FROM professor WHERE " + "professor.nome = \""
				+ data_result.getProfessor().getNome() + "\" and " + "professor.cpf = \"" + data_result.getProfessor().getCpf() + "\" and "
				+ "professor.telefone = \"" + data_result.getProfessor().getTelefone() + "\" and " + "professor.email = \""
				+ data_result.getProfessor().getEmail() + "\" and " + "professor.matricula = \"" + data_result.getProfessor().getMatricula()
				+ "\") and " + "id_sala = (SELECT id_sala FROM sala WHERE " + "sala.codigo = \"" + data_result.getSala().getCode()
				+ "\" and " + "sala.descricao = \"" + data_result.getSala().getDescription() + "\" and " + "sala.capacidade = "
				+ data_result.getSala().getCapacity() + " ) and " + "finalidade = \"" + data_result.getFinalidade() + "\" and "
				+ "hora = \"" + data_result.getHora() + "\" and " + "data = \"" + data_result.getData() + "\";");
	}


	private boolean studentInReservedDB(String date, String hour) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " + "data = \"" + date + "\" and "
				+ "hora = \"" + hour + "\";");
	}

	/**
	 * Method to get from the system the current date.
	 * 
	 * @return the currently date
	 */
	private String currentDate() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}

	/**
	 * Method to get from the system the current hour.
	 * 
	 * @return the currently hour
	 */
	private String currentHour() {
		Date date = new Date(System.currentTimeMillis());
		return date.toString().substring(11, 16);
	}

	/**
	 * Method to verify id the reservation date already passed
	 * 
	 * @param date
	 * @return if the date already passed or not.
	 */
	private boolean pastDate(String date) {
		String now[] = this.currentDate().split("[./-]");
		String splited_date[] = date.split("[./-]");
		boolean verification = false;

		int diference = now[2].length() - splited_date[2].length();
		splited_date[2] = now[2].substring(0, diference) + splited_date[2];

		if (Integer.parseInt(now[2]) > Integer.parseInt(splited_date[2])) {
			verification = true;
		} else {
			// Nothing to do.
		}

		diference = now[1].length() - splited_date[1].length();
		splited_date[1] = now[1].substring(0, diference) + splited_date[1];

		if (Integer.parseInt(now[1]) > Integer.parseInt(splited_date[1])) {
			verification = true;
		} else {
			if (Integer.parseInt(now[1]) == Integer.parseInt(splited_date[1])) {
				diference = now[0].length() - splited_date[0].length();
				splited_date[0] = now[0].substring(0, diference) + splited_date[0];

				if (Integer.parseInt(now[0]) > Integer.parseInt(splited_date[0])) {
					verification = true;
				} else {
					// Nothing to do.
				}
			}
		}
		return verification;
	}

	/**
	 * Method to verify if the date is equal the current date.
	 * 
	 * @param date
	 * @return if the date is equal or not.
	 */
	
	public boolean equalDate(String date) {
		date = this.defaultDate(date);
		String now[] = this.currentDate().split("[./-]");
		String splited_date[] = date.split("[./-]");
		boolean verification = true;

		if (now[0].equals(splited_date[0]) && now[1].equals(splited_date[1]) && now[2].equals(splited_date[2])) {
		} else {
			verification = false;
		}
		return verification;
	}

	/**
	 * Method to verify if the hour is valid
	 * 
	 * @param hour
	 * @return if the hour is valid or not.
	 */
	private boolean validHour(String hour) {
		String now = this.currentHour();
		boolean verification = true;
		if (hour.length() == 4) {
			hour = "0" + hour;
		} else {
			// Nothing to do.
		}
		if (Integer.parseInt(now.substring(0, 2)) > Integer.parseInt(hour.substring(0, 2))) {
		} else {
			if (Integer.parseInt(now.substring(0, 2)) == Integer.parseInt(hour.substring(0, 2))) {
				if (Integer.parseInt(now.substring(3, 5)) > Integer.parseInt(hour.substring(3, 5))) {
				} else {
					verification = false;
				}
			} else {
				verification = false;
			}
		}
		return verification;
	}

	/**
	 * Method to put the date in a default form 
	 * 
	 * @param date
	 * @return date in the correct form
	 */
	private String defaultDate(String date) {
		String now[] = currentDate().split("[./-]");
		String splited_date[] = date.split("[./-]");
		String date_in_defalut = "";

		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				date_in_defalut += now[i].substring(0, now[i].length() - splited_date[i].length()) + splited_date[i];
			} else {
				date_in_defalut += "/" + now[i].substring(0, now[i].length() - splited_date[i].length())
						+ splited_date[i];
			}
		}

		return date_in_defalut;
	}
}