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
public class ReservationRoomForStudentDAO extends DAO {

	// Mensagens e Alertas
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

	// Current instance
	private static ReservationRoomForStudentDAO instance;

	private ReservationRoomForStudentDAO() {
	}

	/**
	 * Method to return a current instance
	 * 
	 * @return current instance or a new instance
	 */
	public static ReservationRoomForStudentDAO getInstance() {
		if (instance == null) {
			instance = new ReservationRoomForStudentDAO();
		} else {
			// Nothing to do.
		}
		return instance;
	}

	/**
	 * Construct a query to find in database Students by student data
	 * 
	 * @param student
	 *            not receive null value
	 * @return Query with the consult
	 */
	private String selectIdStudent(Student student) {
		return "SELECT id_aluno FROM aluno WHERE " + "aluno.nome = \""
				+ student.getNamePerson() + "\" and " + "aluno.cpf = \""
				+ student.getCpfPerson() + "\" and " + "aluno.telefone = \""
				+ student.getPhonePerson() + "\" and " + "aluno.email = \""
				+ student.getEmailPerson() + "\" and " + "aluno.matricula = \""
				+ student.getIdRegister() + "\"";
	}

	/**
	 * Construct a query to find in database Room by room data
	 * 
	 * @param room
	 *            not receive null value
	 * @return Query with the consult
	 */
	private String selectIdRoom(Classroom room) {
		return "SELECT id_sala FROM sala WHERE " + "sala.codigo = \""
				+ room.getIdEquipment() + "\" and " + "sala.descricao = \""
				+ room.getDescriptionEquipment() + "\" and "
				+ "sala.capacidade = " + room.getDescriptionEquipment();
	}

	/**
	 * Construct a query to find in database Room by reservation for student
	 * 
	 * @param reservation
	 *            not receive null value
	 * @return Query with the consult
	 */
	private String whereReservationRoomForStudent(
			ReserveClassroomForStudent reservation) {
		return " WHERE " + "id_aluno = ( "
				+ selectIdStudent(reservation.getStudent()) + " ) and "
				+ "id_sala = ( " + selectIdRoom(reservation.getClassroom())
				+ " ) and " + "finalidade = \"" + reservation.getFinality()
				+ "\" and " + "hora = \"" + reservation.getHour() + "\" and "
				+ "data = \"" + reservation.getDate() + "\" and "
				+ "cadeiras_reservadas = " + reservation.getReservedChairs();
	}

	/**
	 * Construct a query with the values in database Room by reservation for
	 * student
	 * 
	 * @param reservation
	 *            not receive null value
	 * @return Query with the consult
	 */
	private String valuesReservationForStudent(
			ReserveClassroomForStudent reservation) {
		return "( " + selectIdStudent(reservation.getStudent()) + " ), " + "( "
				+ selectIdRoom(reservation.getClassroom()) + " ), " + "\""
				+ reservation.getFinality() + "\", " + "\""
				+ reservation.getHour() + "\", " + "\"" + reservation.getDate()
				+ "\", " + reservation.getReservedChairs();
	}

	/**
	 * Construct a query to find in database Room by reservation with many
	 * attributes
	 * 
	 * @param reservation
	 *            not receive null value
	 * @return Query with the consult
	 */
	private String reservationRoomForStudentWithManyAttributes(
			ReserveClassroomForStudent reservation) {
		return "id_aluno = ( " + selectIdStudent(reservation.getStudent())
				+ " ), " + "id_sala = ( "
				+ selectIdRoom(reservation.getClassroom()) + " ), "
				+ "finalidade = \"" + reservation.getFinality() + "\", "
				+ "hora = \"" + reservation.getHour() + "\", " + "data = \""
				+ reservation.getDate() + "\", " + "cadeiras_reservadas = "
				+ reservation.getReservedChairs();
	}

	/**
	 * Construct a query to insert into database a reservation
	 * 
	 * @param reservation
	 *            not receive null value
	 * @return Query with the consult
	 */
	private String insertInto(ReserveClassroomForStudent reservation) {
		return "INSERT INTO "
				+ "reserva_sala_aluno (id_aluno, id_sala, finalidade, hora, data, cadeiras_reservadas) "
				+ "VALUES ( " + valuesReservationForStudent(reservation)
				+ " );";
	}

	/**
	 * Construct a query to update database reservation
	 * 
	 * @param old_reservation
	 *            not receive null value
	 * @param changed_reservation
	 *            not receive null value
	 * @return Query with the consult
	 */
	private String update(ReserveClassroomForStudent old_reservation,
			ReserveClassroomForStudent changed_reservation) {
		return "UPDATE reserva_sala_aluno SET "
				+ this.reservationRoomForStudentWithManyAttributes(changed_reservation)
				+ this.whereReservationRoomForStudent(old_reservation) + " ;";
	}

	/**
	 * Construct a query to update database reservation
	 * 
	 * @param reservation
	 *            not receive null value
	 * @return Query with the consult
	 */
	private String deleteFrom(ReserveClassroomForStudent reservation) {
		return "DELETE FROM reserva_sala_aluno "
				+ this.whereReservationRoomForStudent(reservation) + " ;";
	}

	/**
	 * Query to add database reservation
	 * 
	 * @param reservation
	 *            not receive null value
	 */
	public void add(ReserveClassroomForStudent reservation)
			throws ReserveException, SQLException, ClientException,
			PatrimonyException {
		if (reservation == null) {
			throw new ReserveException(NULL);
		} else {
			if (!this.studentInDB(reservation.getStudent())) {
				throw new ReserveException(INEXISTENTSTUDENT);
			} else {
				if (!this.roomInDB(reservation.getClassroom())) {
					throw new ReserveException(INEXISTENTROOM);
				} else {
					if (this.roomReservedByTeacherInDB(
							reservation.getClassroom(), reservation.getDate(),
							reservation.getHour())) {
						throw new ReserveException(ROOMUNAVALIBLE);
					} else {
						if (this.studentReservationDB(reservation.getStudent(),
								reservation.getDate(), reservation.getHour())) {
							throw new ReserveException(STUDENTUNAVALIBLE);
						} else {
							if (!this.hasChairs(
									reservation.getReservedChairs(),
									reservation.getClassroom(),
									reservation.getDate(),
									reservation.getHour())) {
								throw new ReserveException(UNAVAILABLECHAIR);
							}
							if (this.isValidDate(reservation.getDate())) {
								throw new ReserveException(PASTDATE);
							}
							if (this.equalsDate(reservation.getDate())) {
								if (this.isValidHour(reservation.getHour())) {
									throw new ReserveException(PASTHOUR);
								} else {
									super.executeQuery(this
											.insertInto(reservation));
								}
							} else {
								super.executeQuery(this.insertInto(reservation));
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Update database data to reservation
	 * 
	 * @param reservation
	 *            not receive null value
	 */
	public void change(ReserveClassroomForStudent old_reservation,
			ReserveClassroomForStudent new_reservation)
			throws ReserveException, SQLException, ClientException,
			PatrimonyException {
		if (old_reservation == null) {
			throw new ReserveException(NULL);
		} else {
			if (new_reservation == null) {
				throw new ReserveException(NULL);
			} else {
				if (!this.reservationInDB(old_reservation)) {
					throw new ReserveException(INEXISTENTRESERVATION);
				} else {
					if (this.reservationInDB(new_reservation)) {
						throw new ReserveException(EXISTENTRESERVATION);
					} else {
						if (!this.studentInDB(new_reservation.getStudent())) {
							throw new ReserveException(INEXISTENTSTUDENT);
						} else {
							if (!this.roomInDB(new_reservation.getClassroom())) {
								throw new ReserveException(INEXISTENTROOM);
							} else {
								if (!old_reservation.getDate().equals(
										new_reservation.getDate())
										|| !old_reservation.getHour().equals(
												new_reservation.getHour())) {
									if (this.studentReservationDB(
											new_reservation.getStudent(),
											new_reservation.getDate(),
											new_reservation.getHour())) {
										throw new ReserveException(
												STUDENTUNAVALIBLE);
									} else {
										if (this.roomReservedByTeacherInDB(
												new_reservation.getClassroom(),
												new_reservation.getDate(),
												new_reservation.getHour())) {
											throw new ReserveException(
													ROOMUNAVALIBLE);
										}
									}
									if (!this
											.hasChairs(
													""
															+ (Integer
																	.parseInt(new_reservation
																			.getReservedChairs()) - Integer
																	.parseInt(old_reservation
																			.getReservedChairs())),
													new_reservation
															.getClassroom(),
													new_reservation.getDate(),
													new_reservation.getHour())) {
										throw new ReserveException(
												UNAVAILABLECHAIR);
									}
									if (this.isValidDate(new_reservation
											.getDate()))
										throw new ReserveException(PASTDATE);
								}
								if (this.isValidHour(new_reservation.getHour())
										&& this.equalsDate(new_reservation
												.getDate())) {
									throw new ReserveException(PASTHOUR);
								} else {
									super.updateQuery(this.update(
											old_reservation, new_reservation));
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Delete data from database reservation
	 * 
	 * @param reservation
	 *            not receive null value
	 * @return Query with the consult
	 */
	public void delete(ReserveClassroomForStudent reservation)
			throws ReserveException, SQLException {
		if (reservation == null) {
			throw new ReserveException(NULL);
		} else {
			if (!this.reservationInDB(reservation)) {
				throw new ReserveException(INEXISTENTRESERVATION);
			} else {
				super.executeQuery(this.deleteFrom(reservation));
			}
		}
	}

	/**
	 * Method to display the list of classrooms reserved with the respective
	 * teacher.
	 * 
	 * @return list of classroom reserved with the respective teacher.
	 * @throws SQLException
	 * @throws ClientException
	 * @throws PatrimonyException
	 * @throws ReserveException
	 */
	public Vector<ReserveClassroomForStudent> searchAll() throws SQLException,
			ClientException, PatrimonyException, ReserveException {
		return super
				.search("SELECT * FROM reserva_sala_aluno "
						+ "INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala "
						+ "INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno;");
	}

	/**
	 * Method to display the list of classrooms reserved with the respective
	 * teacher according to a date.
	 * 
	 * @param date
	 * @return list of classroom reserved with the respective teacher according
	 *         to a date.
	 * @throws SQLException
	 * @throws ClientException
	 * @throws PatrimonyException
	 * @throws ReserveException
	 */
	public Vector<ReserveClassroomForStudent> searchbyDay(String date)
			throws SQLException, ClientException, PatrimonyException,
			ReserveException {
		try {
			date = this.mountDefaultDate(date);
		} catch (NullPointerException e) {
			System.exit(1);
		}
		return super
				.search("SELECT * FROM reserva_sala_aluno "
						+ "INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala "
						+ "INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno "
						+ "WHERE data = \"" + date + "\";");
	}

	/**
	 * Method to display the list of classrooms reserved with the respective
	 * teacher according to a hour.
	 * 
	 * @param hour
	 * @return list of classroom reserved with the respective teacher according
	 *         to a hour.
	 * @throws SQLException
	 * @throws ClientException
	 * @throws PatrimonyException
	 * @throws ReserveException
	 */
	public Vector<ReserveClassroomForStudent> searchByHour(String hour)
			throws SQLException, ClientException, PatrimonyException,
			ReserveException {
		try {
			hour = this.mountDefaultHour(hour);
		} catch (NullPointerException e) {
			System.exit(1);
		}
		return super
				.search("SELECT * FROM reserva_sala_aluno "
						+ "INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala "
						+ "INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno "
						+ " WHERE hora = \"" + hour + "\";");
	}

	/**
	 * Method to return a number of teaspoonful chair
	 * 
	 * @param room
	 *            to consult not null value
	 * @param date
	 *            to consult, not null value
	 * @param hour
	 *            to consult, not null value
	 * @return total chairs ins room
	 * @throws SQLException
	 * @throws PatrimonyException
	 * @throws ClientException
	 * @throws ReserveException
	 */
	public int availableChair(Classroom room, String date, String hour)
			throws SQLException, PatrimonyException, ClientException,
			ReserveException {
		// Get date and hour
		
		try {
			hour = this.mountDefaultHour(hour);
			date = this.mountDefaultDate(date);
		} catch (NullPointerException e) {
			System.exit(1);
		}

		// Search all classrooms
		Vector<ReserveClassroomForStudent> vet = this.searchAll();
		Iterator<ReserveClassroomForStudent> it = vet.iterator();

		// Get total chairs
		int total = Integer.parseInt(room.getDescriptionEquipment());

		// Count total chairs available
		while (it.hasNext()) {
			ReserveClassroomForStudent reservation = it.next();
			if (reservation.getClassroom().equals(room)
					&& reservation.getDate().equals(date)
					&& reservation.getHour().equals(hour))
				total -= Integer.parseInt(reservation.getReservedChairs());
		}

		return total;
	}

	/**
	 * Verify to have more chairs
	 * 
	 * @param reserved_chairs
	 *            int with all chairs, only int
	 * @param room
	 *            to consult not null value
	 * @param date
	 *            to consult, not null value
	 * @param hour
	 *            to consult, not null value
	 * @return true if has or false
	 * @throws SQLException
	 * @throws ClientException
	 * @throws PatrimonyException
	 * @throws ReserveException
	 */
	private boolean hasChairs(String reserved_chairs, Classroom room,
			String date, String hour) throws SQLException, ClientException,
			PatrimonyException, ReserveException {

		boolean has = true;
		int chairs_in_room = this.availableChair(room, date, hour);
		
		assert chairs_in_room >= 0;
		
		has =  chairs_in_room >= Integer
				.parseInt(reserved_chairs);
		return has;
	}

	@Override
	protected Object fetch(ResultSet student_data) throws SQLException,
			ClientException, PatrimonyException, ReserveException {
		Student student = new Student(student_data.getString("nome"),
				student_data.getString("cpf"),
				student_data.getString("matricula"),
				student_data.getString("telefone"),
				student_data.getString("email"));

		Classroom room = new Classroom(student_data.getString("codigo"),
				student_data.getString("descricao"),
				student_data.getString("capacidade"));

		ReserveClassroomForStudent room_reservation = new ReserveClassroomForStudent(
				student_data.getString("data"), student_data.getString("hora"),
				room, student_data.getString("finalidade"),
				student_data.getString("cadeiras_reservadas"), student);

		return room_reservation;
	}

	/**
	 * Method to select in the data base the reservation according to the
	 * parameters.
	 * 
	 * @param student
	 * @return information about professor according to parameters
	 * @throws SQLException
	 */
	private boolean studentInDB(Student student) throws SQLException {
		return super.inDBGeneric("SELECT * FROM aluno WHERE "
				+ "aluno.nome = \"" + student.getNamePerson() + "\" and "
				+ "aluno.cpf = \"" + student.getCpfPerson() + "\" and "
				+ "aluno.telefone = \"" + student.getPhonePerson() + "\" and "
				+ "aluno.email = \"" + student.getEmailPerson() + "\" and "
				+ "aluno.matricula = \"" + student.getIdRegister() + "\";");
	}

	/**
	 * Method to select in the data base the reservation according to the
	 * parameters.
	 * 
	 * @param room
	 * @return information about room according to parameters
	 * @throws SQLException
	 */
	private boolean roomInDB(Classroom room) throws SQLException {
		return super.inDBGeneric("SELECT * FROM sala WHERE "
				+ "sala.codigo = \"" + room.getIdEquipment() + "\" and "
				+ "sala.descricao = \"" + room.getDescriptionEquipment()
				+ "\" and " + "sala.capacidade = "
				+ room.getDescriptionEquipment() + ";");
	}

	/**
	 * Method to verify if the room is reserved by param student
	 * 
	 * @param student
	 *            not null value
	 * @param date
	 *            string with not null value and data format
	 * @param hour
	 *            string with not null value and hour format
	 * @return true if room is reserved or false
	 * @throws SQLException
	 */
	private boolean studentReservationDB(Student student, String date,
			String hour) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE "
				+ "data = \"" + date + "\" and " + "hora = \"" + hour
				+ "\" and " + "id_aluno = (SELECT id_aluno FROM aluno WHERE "
				+ "aluno.nome = \"" + student.getNamePerson() + "\" and "
				+ "aluno.cpf = \"" + student.getCpfPerson() + "\" and "
				+ "aluno.telefone = \"" + student.getPhonePerson() + "\" and "
				+ "aluno.email = \"" + student.getEmailPerson() + "\" and "
				+ "aluno.matricula = \"" + student.getIdRegister() + "\");");
	}

	/**
	 * Method to select in the data base the reservation according to the
	 * parameters.
	 * 
	 * @param room
	 * @param date
	 * @param hour
	 * @return information about reservation according to parameters
	 * @throws SQLException
	 */
	private boolean roomReservedByTeacherInDB(Classroom room, String date,
			String hour) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE "
				+ "data = \"" + this.mountDefaultDate(date) + "\" and "
				+ "hora = \"" + this.mountDefaultHour(hour) + "\" and "
				+ "id_sala = (SELECT id_sala FROM sala WHERE "
				+ "sala.codigo = \"" + room.getIdEquipment() + "\" and "
				+ "sala.descricao = \"" + room.getDescriptionEquipment()
				+ "\" and " + "sala.capacidade = "
				+ room.getDescriptionEquipment() + " );");
	}

	private boolean reservationInDB(ReserveClassroomForStudent reservation)
			throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE "
				+ "id_aluno = (SELECT id_aluno FROM aluno WHERE "
				+ "aluno.nome = \""
				+ reservation.getStudent().getNamePerson()
				+ "\" and "
				+ "aluno.cpf = \""
				+ reservation.getStudent().getCpfPerson()
				+ "\" and "
				+ "aluno.telefone = \""
				+ reservation.getStudent().getPhonePerson()
				+ "\" and "
				+ "aluno.email = \""
				+ reservation.getStudent().getEmailPerson()
				+ "\" and "
				+ "aluno.matricula = \""
				+ reservation.getStudent().getIdRegister()
				+ "\") and "
				+ "id_sala = (SELECT id_sala FROM sala WHERE "
				+ "sala.codigo = \""
				+ reservation.getClassroom().getClass()
				+ "\" and "
				+ "sala.descricao = \""
				+ reservation.getClassroom().getDescriptionEquipment()
				+ "\" and "
				+ "sala.capacidade = "
				+ reservation.getClassroom().getCapacity()
				+ " ) and "
				+ "finalidade = \""
				+ reservation.getFinality()
				+ "\" and "
				+ "hora = \""
				+ reservation.getHour()
				+ "\" and "
				+ "data = \""
				+ reservation.getDate()
				+ "\" and "
				+ "cadeiras_reservadas = "
				+ reservation.getReservedChairs() + ";");
	}

	/**
	 * Method to get current date
	 * 
	 * @return String with current date
	 */
	private String currentDate() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}

	/**
	 * Method to get current hour
	 * 
	 * @return String with current hour
	 */
	private String currentHour() {
		Date date = new Date(System.currentTimeMillis());
		return date.toString().substring(11, 16);
	}

	/**
	 * Method to get current hour
	 * 
	 * @return String with current hour
	 */
	private boolean isValidDate(String date) {
		String split_current_date[] = this.currentDate().split("[./-]");
		String split_date[] = date.split("[./-]");

		int dif = split_current_date[2].length() - split_date[2].length();
		split_date[2] = split_current_date[2].substring(0, dif) + split_date[2];

		if (Integer.parseInt(split_current_date[2]) > Integer
				.parseInt(split_date[2]))
			return true;

		dif = split_current_date[1].length() - split_date[1].length();
		split_date[1] = split_current_date[1].substring(0, dif) + split_date[1];

		if (Integer.parseInt(split_current_date[1]) > Integer
				.parseInt(split_date[1]))
			return true;
		else if (Integer.parseInt(split_current_date[1]) == Integer
				.parseInt(split_date[1])) {
			dif = split_current_date[0].length() - split_date[0].length();
			split_date[0] = split_current_date[0].substring(0, dif)
					+ split_date[0];

			if (Integer.parseInt(split_current_date[0]) > Integer
					.parseInt(split_date[0]))
				return true;
		}
		return false;
	}

	/**
	 * Verify if date is equals to current date
	 * 
	 * @param date
	 *            not null string
	 * @return true if equals or false if not equals
	 */
	public boolean equalsDate(String date) {
		try {
			date = this.mountDefaultDate(date);
		} catch (NullPointerException e) {
			System.exit(1);
		}
		String split_current_date[] = this.currentDate().split("[./-]");
		String split_date[] = date.split("[./-]");

		if (split_current_date[0].equals(split_date[0])
				&& split_current_date[1].equals(split_date[1])
				&& split_current_date[2].equals(split_date[2]))
			return true;
		return false;
	}

	/**
	 * Method to verify if is valid hour
	 * 
	 * @param hour
	 *            in default format
	 * @return true if hour is valid
	 */
	private boolean isValidHour(String hour) {
		String now = this.currentHour();
		if (hour.length() == 4)
			hour = "0" + hour;
		if (Integer.parseInt(now.substring(0, 2)) > Integer.parseInt(hour
				.substring(0, 2)))
			return true;
		else if (Integer.parseInt(now.substring(0, 2)) == Integer.parseInt(hour
				.substring(0, 2))) {
			if (Integer.parseInt(now.substring(3, 5)) > Integer.parseInt(hour
					.substring(3, 5)))
				return true;
			else
				return false;
		} else
			return false;
	}

	/**
	 * Method to mount a date in default format
	 * 
	 * @param date
	 *            not null and in the predefine format
	 * @return date in default format
	 */
	private String mountDefaultDate(String date) {
		String now[] = currentDate().split("[./-]");
		String parts_of_the_date[] = date.split("[./-]");
		String date_in_default_model = "";

		for (int i = 0; i < 3; i++) {
			if (i == 0)
				date_in_default_model += now[i].substring(0, now[i].length()
						- parts_of_the_date[i].length())
						+ parts_of_the_date[i];
			else
				date_in_default_model += "/"
						+ now[i].substring(0, now[i].length()
								- parts_of_the_date[i].length())
						+ parts_of_the_date[i];

		}

		return date_in_default_model;
	}

	/**
	 * Method to mount a hour in default format
	 * 
	 * @param date
	 *            not null and in the predefine format
	 * @return hour in default format
	 */
	private String mountDefaultHour(String hour) {
		if (hour.length() == 4)
			return "0" + hour;
		return hour;
	}

}
