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
import model.ReservaSalaAluno;
import model.Sala;

@SuppressWarnings("unchecked")
public class ResSalaAlunoDAO extends DAO{
	
	//Mensagens e Alertas
		private final String NULA = "Termo nulo.";
		private final String ALUNO_INDISPONIVEL = "O aluno possui uma reserva no mesmo dia e horario.";
		private final String SALA_INDISPONIVEL = "A Sala esta reservada no mesmo dia e horario.";
		private final String ALUNO_INEXISTENTE = "Aluno inexistente.";
		private final String SALA_INEXISTENTE = "Sala inexistente";
		private final String RESERVA_INEXISTENTE = "Reserva inexistente";
		private final String RESERVA_EXISTENTE = "A reserva ja existe.";
		private final String CADEIRAS_INDISPONIVEIS = "O numero de cadeiras reservadas esta indisponivel para esta sala.";
		private final String DATA_JA_PASSOU = "A data escolhida ja passou.";
		private final String HORA_JA_PASSOU = "A hora escolhida ja passou.";

	
	//Singleton
		private static ResSalaAlunoDAO instance;
		private ResSalaAlunoDAO(){
		}
		public static ResSalaAlunoDAO getInstance(){
			if(instance == null)
				instance = new ResSalaAlunoDAO();
			return instance;
		}
	//
		
		
	//Querys de Reuso
		private String select_id_aluno(Student a){
			return "SELECT id_aluno FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNamePerson() + "\" and " +
					"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
					"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
					"aluno.email = \"" + a.getEmailPerson() + "\" and " +
					"aluno.matricula = \"" + a.getIdRegister() + "\"";
		}
		private String select_id_sala(Sala sala){
			return "SELECT id_sala FROM sala WHERE " +
					"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
					"sala.descricao = \"" + sala.getDescriptionEquipment() +  "\" and " +
					"sala.capacidade = " + sala.getCapacidade();
		}
		private String where_reserva_sala_aluno(ReservaSalaAluno r){
			return " WHERE " +
			"id_aluno = ( " + select_id_aluno(r.getAluno()) + " ) and " +
			"id_sala = ( " + select_id_sala(r.getSala()) + " ) and " +
			"finalidade = \"" + r.getFinalidade() + "\" and " +
			"hora = \"" + r.getHour() + "\" and " +
			"data = \"" + r.getDate() + "\" and " +
			"cadeiras_reservadas = " + r.getCadeiras_reservadas();
		}
		private String values_reserva_sala_aluno(ReservaSalaAluno r){
			return "( " + select_id_aluno(r.getAluno()) + " ), " +
			"( " + select_id_sala(r.getSala()) + " ), " +
			"\"" + r.getFinalidade() + "\", " +
			"\"" + r.getHour() + "\", " +
			"\"" + r.getDate() + "\", " +
			r.getCadeiras_reservadas();
		}
		private String atibutes_value_reserva_sala_aluno(ReservaSalaAluno r){
			return "id_aluno = ( " + select_id_aluno(r.getAluno()) + " ), " +
			"id_sala = ( " + select_id_sala(r.getSala()) + " ), " +
			"finalidade = \"" + r.getFinalidade() + "\", " +
			"hora = \"" + r.getHour() + "\", " +
			"data = \"" + r.getDate() + "\", " +
			"cadeiras_reservadas = " + r.getCadeiras_reservadas();
		}
		private String insert_into(ReservaSalaAluno r){
			return "INSERT INTO " +
					"reserva_sala_aluno (id_aluno, id_sala, finalidade, hora, data, cadeiras_reservadas) " +
					"VALUES ( " + values_reserva_sala_aluno(r) + " );";
		}
		private String update(ReservaSalaAluno r, ReservaSalaAluno r2){
			return "UPDATE reserva_sala_aluno SET " + 
					this.atibutes_value_reserva_sala_aluno(r2) +
					this.where_reserva_sala_aluno(r) + " ;";
		}
		private String delete_from(ReservaSalaAluno r){
			return "DELETE FROM reserva_sala_aluno " + this.where_reserva_sala_aluno(r) + " ;";
		}

		
		
	public void incluir(ReservaSalaAluno r) throws ReserveException, SQLException, ClientException, PatrimonyException {
		if(r == null)
			throw new ReserveException(NULA);
		else if(!this.alunoinDB(r.getAluno()))
			throw new ReserveException(ALUNO_INEXISTENTE);
		else if(!this.salainDB(r.getSala()))
			throw new ReserveException(SALA_INEXISTENTE);
		else if(this.salainReservaProfessorDB(r.getSala(), r.getDate(), r.getHour()))
			throw new ReserveException(SALA_INDISPONIVEL);
		else if(this.alunoinReservaDB(r.getAluno(), r.getDate(), r.getHour()))
			throw new ReserveException(ALUNO_INDISPONIVEL);
		else if(!this.haCadeiras(r.getCadeiras_reservadas(), r.getSala(), r.getDate(), r.getHour()))
			throw new ReserveException(CADEIRAS_INDISPONIVEIS);
		if(this.dataPassou(r.getDate()))
			throw new ReserveException(DATA_JA_PASSOU);
		if(this.dataIgual(r.getDate()))
		{
			if(this.horaPassou(r.getHour()))
				throw new ReserveException(HORA_JA_PASSOU);
			else
				super.executeQuery(this.insert_into(r));
		}
		else
			super.executeQuery(this.insert_into(r));
	}
	
	public void alterar(ReservaSalaAluno r, ReservaSalaAluno r_new) throws ReserveException, SQLException, ClientException, PatrimonyException{
		if(r == null)
			throw new ReserveException(NULA);
		else if(r_new == null)
			throw new ReserveException(NULA);
		
		else if(!this.reservainDB(r))
			throw new ReserveException(RESERVA_INEXISTENTE);
		else if(this.reservainDB(r_new))
			throw new ReserveException(RESERVA_EXISTENTE);
		else if(!this.alunoinDB(r_new.getAluno()))
			throw new ReserveException(ALUNO_INEXISTENTE);
		else if(!this.salainDB(r_new.getSala()))
			throw new ReserveException(SALA_INEXISTENTE);
		else if(!r.getDate().equals(r_new.getDate()) || !r.getHour().equals(r_new.getHour())){
			if(this.alunoinReservaDB(r_new.getAluno(), r_new.getDate(), r_new.getHour()))
				throw new ReserveException(ALUNO_INDISPONIVEL);
			else if(this.salainReservaProfessorDB(r_new.getSala(), r_new.getDate(), r_new.getHour()))
				throw new ReserveException(SALA_INDISPONIVEL);
		}
		if(!this.haCadeiras(""+(Integer.parseInt(r_new.getCadeiras_reservadas()) - 
				Integer.parseInt(r.getCadeiras_reservadas())), r_new.getSala(), 
				r_new.getDate(), r_new.getHour()))
			throw new ReserveException(CADEIRAS_INDISPONIVEIS);
		if(this.dataPassou(r_new.getDate()))
			throw new ReserveException(DATA_JA_PASSOU);
		if(this.horaPassou(r_new.getHour()) && this.dataIgual(r_new.getDate()))
			throw new ReserveException(HORA_JA_PASSOU);
		else
			super.updateQuery(this.update(r, r_new));
			
	}
	
	public void excluir(ReservaSalaAluno r) throws ReserveException, SQLException {
		if(r == null)
			throw new ReserveException(NULA);
		else if(!this.reservainDB(r))
			throw new ReserveException(RESERVA_INEXISTENTE);
		else
			super.executeQuery(this.delete_from(r));
	}
	
	public Vector<ReservaSalaAluno> buscarTodos() throws SQLException, ClientException, PatrimonyException, ReserveException{
		return super.buscar("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno;");
	}
	public Vector<ReservaSalaAluno> buscarPorDia(String data) throws SQLException, ClientException, PatrimonyException, ReserveException{
		data = this.padronizarData(data);
		return super.buscar("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno " +
				"WHERE data = \""+ data + "\";");
	}
	public Vector<ReservaSalaAluno> buscarPorHora(String hora) 
			throws SQLException, ClientException, PatrimonyException, ReserveException{
		hora = this.padronizarHora(hora);
		return super.buscar("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno " +
				" WHERE hora = \"" + hora +"\";");
	}

	
	public int cadeirasDisponiveis(Sala sala, String data, String hora) 
			throws SQLException, PatrimonyException, ClientException, ReserveException{
		data = this.padronizarData(data);
		hora = this.padronizarHora(hora);
		Vector<ReservaSalaAluno> vet = this.buscarTodos();
		Iterator<ReservaSalaAluno> it = vet.iterator();
		int total = Integer.parseInt(sala.getCapacidade());
		while(it.hasNext()){
			ReservaSalaAluno r = it.next();
			if(r.getSala().equals(sala) && r.getDate().equals(data) && r.getHour().equals(hora))
				total -= Integer.parseInt(r.getCadeiras_reservadas());
		}
		return total;
	}
	
	
	private boolean haCadeiras(String cadeiras_reservadas, Sala sala, String data, String hora) 
			throws SQLException, ClientException, PatrimonyException, ReserveException {
		if(this.cadeirasDisponiveis(sala, data, hora) >= Integer.parseInt(cadeiras_reservadas))
			return true;
		return false;
	}
	
	@Override
	protected Object fetch(ResultSet rs) throws SQLException, ClientException, PatrimonyException, ReserveException {
		Student a = new Student(rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"),
				rs.getString("telefone"), rs.getString("email"));
		
		Sala s = new Sala(rs.getString("codigo"), rs.getString("descricao"), rs.getString("capacidade"));
		
		ReservaSalaAluno r = new ReservaSalaAluno(rs.getString("data"),rs.getString("hora"),
				s ,rs.getString("finalidade"),rs.getString("cadeiras_reservadas"), a);
		
		return r;
	}
	
	private boolean alunoinDB(Student aluno) throws SQLException{
		return super.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
				"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + aluno.getIdRegister() + "\";");
	}
	
	private boolean salainDB(Sala sala) throws SQLException{
		return super.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala.getDescriptionEquipment() + "\" and " +
				"sala.capacidade = " + sala.getCapacidade() +
				";");
	}
	
	private boolean alunoinReservaDB(Student aluno, String data, String hora) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
				"data = \"" + data + "\" and " +
				"hora = \"" + hora + "\" and " +
				"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + aluno.getPhonePerson() + "\" and " +
				"aluno.email = \"" + aluno.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + aluno.getIdRegister() + "\");");
	}
	private boolean salainReservaProfessorDB(Sala sala, String data, String hora) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
				"data = \"" + this.padronizarData(data) + "\" and " +
				"hora = \"" + this.padronizarHora(hora) + "\" and " +
				"id_sala = (SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala.getDescriptionEquipment() +  "\" and " +
				"sala.capacidade = " + sala.getCapacidade() +" );");
	}
	
	private boolean reservainDB(ReservaSalaAluno r) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
					"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
							"aluno.nome = \"" + r.getAluno().getNamePerson() + "\" and " +
							"aluno.cpf = \"" + r.getAluno().getCpfPerson() + "\" and " +
							"aluno.telefone = \"" + r.getAluno().getPhonePerson() + "\" and " +
							"aluno.email = \"" + r.getAluno().getEmailPerson() + "\" and " +
							"aluno.matricula = \"" + r.getAluno().getIdRegister() + "\") and " +
					"id_sala = (SELECT id_sala FROM sala WHERE " +
									"sala.codigo = \"" + r.getSala().getIdEquipment() + "\" and " +
									"sala.descricao = \"" + r.getSala().getDescriptionEquipment() +  "\" and " +
									"sala.capacidade = " + r.getSala().getCapacidade() +" ) and " +
					"finalidade = \"" + r.getFinalidade() + "\" and " +
					"hora = \"" + r.getHour() + "\" and " +
					"data = \"" + r.getDate() + "\" and " +
					"cadeiras_reservadas = " + r.getCadeiras_reservadas() + ";");
	}

	private String dataAtual(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}
	
	private String horaAtual(){
		Date date = new Date(System.currentTimeMillis());
		return date.toString().substring(11, 16);
	}
	
	private boolean dataPassou(String d){
		String agora[] = this.dataAtual().split("[./-]");
		String data[] = d.split("[./-]");
		
		int dif = agora[2].length() - data[2].length();
		data[2] = agora[2].substring(0, dif) + data[2];
		
		if(Integer.parseInt(agora[2]) > Integer.parseInt(data[2]))
			return true;
		
		dif = agora[1].length() - data[1].length();
		data[1] = agora[1].substring(0, dif) + data[1];
		
		if(Integer.parseInt(agora[1]) > Integer.parseInt(data[1]))
			return true;
		else if(Integer.parseInt(agora[1]) == Integer.parseInt(data[1])){
			dif = agora[0].length() - data[0].length();
			data[0] = agora[0].substring(0, dif) + data[0];
			
			if(Integer.parseInt(agora[0]) > Integer.parseInt(data[0]))
				return true;
		}
		return false;
	}
	
	public boolean dataIgual(String d){
		d = this.padronizarData(d);
		String agora[] = this.dataAtual().split("[./-]");
		String data[] = d.split("[./-]");
		
		if(agora[0].equals(data[0]) && agora[1].equals(data[1]) && agora[2].equals(data[2]))
			return true;
		return false;
	}
	
	private boolean horaPassou(String hora){
		String agora = this.horaAtual();
		if(hora.length() == 4)
			hora = "0" + hora;
		if(Integer.parseInt(agora.substring(0, 2)) > Integer.parseInt(hora.substring(0, 2)))
			return true;
		else if(Integer.parseInt(agora.substring(0, 2)) == Integer.parseInt(hora.substring(0, 2))){
			if(Integer.parseInt(agora.substring(3, 5)) > Integer.parseInt(hora.substring(3, 5)))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	private String padronizarData(String data){
		String agora[] = dataAtual().split("[./-]");
		String partes[] = data.split("[./-]");
		String dataNoPadrao = "";
		
		for(int i = 0; i < 3; i++){
			if(i == 0)
				dataNoPadrao += agora[i].substring(0, 
						agora[i].length() - partes[i].length()) + partes[i];
			else
				dataNoPadrao +=  "/" + agora[i].substring(0, 
						agora[i].length() - partes[i].length()) + partes[i];
				
		}
		
		return dataNoPadrao;
	}
	
	private String padronizarHora(String hora){
		if(hora.length() == 4)
			return "0" + hora;
		return hora;
	}
	
}
