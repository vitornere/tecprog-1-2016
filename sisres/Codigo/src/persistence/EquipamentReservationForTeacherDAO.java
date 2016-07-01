/*
 * File: EquipamentReservationForTeacherDAO.java
 * Description: Class to make transactions with the database and reservation of equipaments by teacher
 * */
package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import model.Equipment;
import model.Professor;
import model.ReserveEquipmentProfessor;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;


public class EquipamentReservationForTeacherDAO extends DAO {

	// Constants with errors messenger
	private final String NULA = "Termo nulo.";
	private final String EQUIPAMENTO_INDISPONIVEL = "O Equipamento esta reservada no mesmo dia e horario.";
	private final String PROFESSOR_INEXISTENTE = "Professor inexistente.";
	private final String EQUIPAMENTO_INEXISTENTE = "Equipamento inexistente";
	private final String RESERVA_INEXISTENTE = "Reserva inexistente";
	private final String RESERVA_EXISTENTE = "A reserva ja existe.";

	// Singleton
	private static EquipamentReservationForTeacherDAO instance;

	
	private EquipamentReservationForTeacherDAO() {
	}

	/**
	 * Method to return a current instance
	 * 
	 * @return current instance or a new instance
	 */
	public static EquipamentReservationForTeacherDAO getInstance() {
		if (instance == null) {
			instance = new EquipamentReservationForTeacherDAO();
		}
		else {
			// Nothing to do
		}
		return instance;
	}

	// Querys de Reuso
	private String select_id_professor(Professor p) {
		return "SELECT id_professor FROM professor WHERE "
				+ "professor.nome = \"" + p.getNamePerson() + "\" and "
				+ "professor.cpf = \"" + p.getCpfPerson() + "\" and "
				+ "professor.telefone = \"" + p.getPhonePerson() + "\" and "
				+ "professor.email = \"" + p.getEmailPerson() + "\" and "
				+ "professor.matricula = \"" + p.getIdRegister() + "\"";
	}

	private String select_id_equipamento(Equipment equipamento) {
		return "SELECT id_equipamento FROM equipamento WHERE "
				+ "equipamento.codigo = \"" + equipamento.getIdEquipment()
				+ "\" and " + "equipamento.descricao = \""
				+ equipamento.getDescriptionEquipment();
	}

	private String where_reserva_equipamento_professor(
			ReserveEquipmentProfessor r) {
		return " WHERE " + "id_professor = ( "
				+ select_id_professor(r.getProfessor()) + " ) and "
				+ "id_equipamento = ( "
				+ select_id_equipamento(r.getEquipment()) + " ) and "
				+ "hora = \"" + r.getHour() + "\" and " + "data = \""
				+ r.getDate();
	}

	private String values_reserva_equipamento_professor(
			ReserveEquipmentProfessor r) {
		return "( " + select_id_professor(r.getProfessor()) + " ), " + "( "
				+ select_id_equipamento(r.getEquipment()) + " ), " + "\""
				+ r.getHour() + "\", " + "\"" + r.getDate();
	}

	private String atributes_value_reserva_equipamento_professor(
			ReserveEquipmentProfessor r) {
		return "id_professor = ( " + select_id_professor(r.getProfessor())
				+ " ), " + "id_equipamento = ( "
				+ select_id_equipamento(r.getEquipment()) + " ), "
				+ "hora = \"" + r.getHour() + "\", " + "data = \""
				+ r.getDate();
	}

	private String insert_into(ReserveEquipmentProfessor r) {
		return "INSERT INTO "
				+ "reserva_equipamento_professor (id_professor, id_equipamento, hora, data) "
				+ "VALUES ( " + values_reserva_equipamento_professor(r) + " );";
	}

	private String update(ReserveEquipmentProfessor r,
			ReserveEquipmentProfessor r2) {
		return "UPDATE reserva_equipamento_professor SET "
				+ this.atributes_value_reserva_equipamento_professor(r2)
				+ this.where_reserva_equipamento_professor(r) + " ;";
	}

	private String delete_from_professor(ReserveEquipmentProfessor r) {
		return "DELETE FROM reserva_equipamento_professor "
				+ this.where_reserva_equipamento_professor(r) + " ;";
	}

	private String delete_from_aluno(ReserveEquipmentProfessor r) {
		return "DELETE FROM reserva_equipamento_aluno WHERE " + "hora = \""
				+ r.getHour() + "\" and " + "data = \"" + r.getDate() + " ;";
	}

	public void add(ReserveEquipmentProfessor r) throws ReserveException,
			SQLException {
		if (r == null) {
			throw new ReserveException(NULA);
		} 
		else {
			if (!this.professorinDB(r.getProfessor())) {
				throw new ReserveException(PROFESSOR_INEXISTENTE);
			} 
			else {
				if (!this.equipamentoinDB(r.getEquipment())) {
					throw new ReserveException(EQUIPAMENTO_INEXISTENTE);
				} 
				else {
					if (this.equipamentoinReservaDB(r.getEquipment(),
							r.getDate(), r.getHour())) {
						throw new ReserveException(EQUIPAMENTO_INDISPONIVEL);
					} 
					else {
						if (this.professorinReservaDB(r.getProfessor(),
								r.getDate(), r.getHour())) {
							throw new ReserveException(RESERVA_EXISTENTE);
						} 
						else {
							super.executeQuery(this.delete_from_aluno(r));
							super.executeQuery(this.insert_into(r));
						}
					}
				}
			}
		}

	}

	public void change(ReserveEquipmentProfessor r,
			ReserveEquipmentProfessor r_new) throws ReserveException,
			SQLException {
		if (r == null) {
			throw new ReserveException(NULA);
		} 
		else {
			if (r_new == null) {
				throw new ReserveException(NULA);
			} 
			else {
				if (!this.reservainDB(r)) {
					throw new ReserveException(RESERVA_INEXISTENTE);
				}
				else {
					if (this.reservainDB(r_new)) {
						throw new ReserveException(RESERVA_EXISTENTE);
					} 
					else {
						if (!r.getDate().equals(r_new.getDate())
								|| !r.getHour().equals(r_new.getHour())) {
							if (this.professorinReservaDB(r_new.getProfessor(),
									r_new.getDate(), r_new.getHour())) {
								throw new ReserveException(RESERVA_EXISTENTE);// Perguntar
																				// pro
																				// Matheus
							} 
							else {
								if (this.equipamentoinReservaDB(
										r_new.getEquipment(),
										r_new.getDate(), r_new.getHour())) {
									throw new ReserveException(
											EQUIPAMENTO_INDISPONIVEL);
								}
								else {
									if (!this.professorinDB(r_new
											.getProfessor())) {
										throw new ReserveException(
												PROFESSOR_INEXISTENTE);
									}
									else {
										if (!this.equipamentoinDB(r_new
												.getEquipment())) {
											throw new ReserveException(
													EQUIPAMENTO_INEXISTENTE);
										}
										else {
											super.updateQuery(this.update(r,
													r_new));
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public void delete(ReserveEquipmentProfessor r) throws ReserveException,
			SQLException {
		if (r == null) {
			throw new ReserveException(NULA);
		} 
		else {
			if (!this.reservainDB(r)) {
				throw new ReserveException(RESERVA_INEXISTENTE);
			} 
			else {
				super.executeQuery(this.delete_from_professor(r));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Vector<Object> searchAll() throws SQLException, ClientException,
			PatrimonyException, ReserveException {
		return super
				.search("SELECT * FROM reserva_sala_professor "
						+ "INNER JOIN sala ON sala.id_sala = reserva_sala_professor.id_sala "
						+ "INNER JOIN professor ON professor.id_professor = reserva_sala_professor.id_professor;");
	}

	@SuppressWarnings("unchecked")
	public Vector<ReserveEquipmentProfessor> searchForMonth(int mes)
			throws SQLException, ClientException, PatrimonyException,
			ReserveException {
		Vector<ReserveEquipmentProfessor> reservas_prof_mes = super
				.search("SELECT * FROM reserva_equipamento_professor "
						+ "INNER JOIN equipamento ON equipamento.id_equipamento = reserva_equipamento_professor.id_equipamento "
						+ "INNER JOIN professor ON professor.id_professor = reserva_equipamento_professor.id_professor;");
		Iterator<ReserveEquipmentProfessor> it = reservas_prof_mes.iterator();
		while (it.hasNext()) {
			ReserveEquipmentProfessor obj = it.next();
			if (Integer.parseInt(obj.getDate().split("[./-]")[1]) != mes) {
				reservas_prof_mes.remove(obj);
			}
		}
		return reservas_prof_mes;
	}

	@SuppressWarnings("unchecked")
	public Vector<ReserveEquipmentProfessor> searchForHour(String hora)
			throws SQLException, ClientException, PatrimonyException,
			ReserveException {
		String hora_a = "", hora_b = "";
		if (hora.length() == 4) {
			hora_a = "0" + hora;
		}
		if (hora.charAt(0) == '0') {
			hora_b = hora.substring(1);
		}
		return super
				.search("SELECT * FROM reserva_equipamento_professor "
						+ "INNER JOIN equipamento ON equipamento.id_equipamento = reserva_equipamento_professor.id_equipamento "
						+ "INNER JOIN professor ON professor.id_professor = reserva_equipamento_professor.id_professor "
						+ " WHERE hora = \"" + hora + "\" or hora = \""
						+ hora_a + "\" or hora = \"" + hora_b + "\";");
	}

	@Override
	protected Object fetch(ResultSet rs) throws SQLException, ClientException,
			PatrimonyException, ReserveException {
		Professor p = new Professor(rs.getString("nome"), rs.getString("cpf"),
				rs.getString("matricula"), rs.getString("telefone"),
				rs.getString("email"));

		Equipment s = new Equipment(rs.getString("codigo"),
				rs.getString("descricao"));

		ReserveEquipmentProfessor r = new ReserveEquipmentProfessor(
				rs.getString("data"), rs.getString("hora"), s, p);

		return r;
	}

	private boolean professorinDB(Professor professor) throws SQLException {
		return super.inDBGeneric("SELECT * FROM professor WHERE "
				+ "professor.nome = \"" + professor.getNamePerson() + "\" and "
				+ "professor.cpf = \"" + professor.getCpfPerson() + "\" and "
				+ "professor.telefone = \"" + professor.getPhonePerson()
				+ "\" and " + "professor.email = \"" + professor.getEmailPerson()
				+ "\" and " + "professor.matricula = \""
				+ professor.getIdRegister() + "\";");
	}

	private boolean equipamentoinDB(Equipment equipamento)
			throws SQLException {
		return super.inDBGeneric("SELECT * FROM equipamento WHERE "
				+ "equipamento.codigo = \"" + equipamento.getIdEquipment()
				+ "\" and " + "equipamento.descricao = \""
				+ equipamento.getDescriptionEquipment() + "\" and " + ";");
	}

	private boolean professorinReservaDB(Professor professor, String data,
			String hora) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE "
				+ "data = \"" + data + "\" and " + "hora = \"" + hora
				+ "\" and "
				+ "id_professor = (SELECT id_professor FROM professor WHERE "
				+ "professor.nome = \"" + professor.getNamePerson() + "\" and "
				+ "professor.cpf = \"" + professor.getCpfPerson() + "\" and "
				+ "professor.telefone = \"" + professor.getPhonePerson()
				+ "\" and " + "professor.email = \"" + professor.getEmailPerson()
				+ "\" and " + "professor.matricula = \""
				+ professor.getIdRegister() + "\");");
	}

	private boolean equipamentoinReservaDB(Equipment equipamento,
			String data, String hora) throws SQLException {
		return super
				.inDBGeneric("SELECT * FROM reserva_equipamento_professor WHERE "
						+ "data = \""
						+ data
						+ "\" and "
						+ "hora = \""
						+ hora
						+ "\" and "
						+ "id_equipamento = (SELECT id_equipamento FROM equipamento WHERE "
						+ "equipamento.codigo = \""
						+ equipamento.getIdEquipment()
						+ "\" and "
						+ "equipamento.descricao = \""
						+ equipamento.getDescriptionEquipment() + "\" and " + ");");
	}

	private boolean reservainDB(ReserveEquipmentProfessor r)
			throws SQLException {
		return super
				.inDBGeneric("SELECT * FROM reserva_equipamento_professor WHERE "
						+ "id_professor = (SELECT id_professor FROM professor WHERE "
						+ "professor.nome = \""
						+ r.getProfessor().getNamePerson()
						+ "\" and "
						+ "professor.cpf = \""
						+ r.getProfessor().getCpfPerson()
						+ "\" and "
						+ "professor.telefone = \""
						+ r.getProfessor().getPhonePerson()
						+ "\" and "
						+ "professor.email = \""
						+ r.getProfessor().getEmailPerson()
						+ "\" and "
						+ "professor.matricula = \""
						+ r.getProfessor().getIdRegister()
						+ "\") and "
						+ "id_equipamento = (SELECT id_equipamento FROM equipamento WHERE "
						+ "equipamento.codigo = \""
						+ r.getEquipment().getIdEquipment()
						+ "\" and "
						+ "equipamento.descricao = \""
						+ r.getEquipment().getDescriptionEquipment()
						+ "\" and "
						+ "hora = \""
						+ r.getHour()
						+ "\" and "
						+ "data = \""
						+ r.getDate() + ";");
	}

}