package control;

import java.sql.SQLException;
import java.util.Vector;
import java.util.zip.DataFormatException;

import model.Aluno;
import model.ReservaSalaAluno;
import model.Classroom;
import persistence.ReservationRoomForStudentDAO;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;

public class ManterResSalaAluno {

	private Vector<ReservaSalaAluno> rev_sala_aluno_vet = new Vector<ReservaSalaAluno>();
	//Singleton
	private static ManterResSalaAluno instance;

	private ManterResSalaAluno() {
	}

	public static ManterResSalaAluno getInstance() {
		if (instance == null) {
			instance = new ManterResSalaAluno();
		}
		return instance;
	}
	//

<<<<<<< HEAD:sisres/src/control/ManterResSalaAluno.java
	public Vector<ReservaSalaAluno> getReservasHora(String hora) throws SQLException, PatrimonioException, ClienteException, ReservaException, DataFormatException{
		return ResSalaAlunoDAO.getInstance().buscarPorHora(hora);
		
	}
	
	public Vector<ReservaSalaAluno> getReservasMes(String data) throws SQLException, PatrimonioException, ClienteException, ReservaException, DataFormatException{
		return ResSalaAlunoDAO.getInstance().buscarPorDia(data);
	}
	
	public Vector<ReservaSalaAluno> getResAlunoSala_vet() throws SQLException, PatrimonioException, ClienteException, ReservaException, DataFormatException {
		this.rev_sala_aluno_vet = ResSalaAlunoDAO.getInstance().buscarTodos();
		return this.rev_sala_aluno_vet;
	}

	public int cadeirasDisponveis(Sala sala, String data, String hora) throws SQLException, PatrimonioException, ClienteException, ReservaException, DataFormatException {
		return ResSalaAlunoDAO.getInstance().cadeirasDisponiveis(sala, data, hora);
=======
	public Vector<ReservaSalaAluno> getReservasHora(String hora) throws SQLException, PatrimonyException, ClienteException, ReservaException{
		return ReservationRoomForStudentDAO.getInstance().searchByHour(hora);
		
	}
	
	public Vector<ReservaSalaAluno> getReservasMes(String data) throws SQLException, PatrimonyException, ClienteException, ReservaException{
		return ReservationRoomForStudentDAO.getInstance().searchbyDay(data);
	}
	
	public Vector<ReservaSalaAluno> getResAlunoSala_vet() throws SQLException, PatrimonyException, ClienteException, ReservaException {
		this.rev_sala_aluno_vet = ReservationRoomForStudentDAO.getInstance().searchAll();
		return this.rev_sala_aluno_vet;
	}

	public int cadeirasDisponveis(Classroom sala, String data, String hora) throws SQLException, PatrimonyException, ClienteException, ReservaException {
		return ReservationRoomForStudentDAO.getInstance().availableChair(sala, data, hora);
>>>>>>> devel:sisres/src/control/ManterResSalaAluno.java
	}

	public void inserir(Classroom sala, Aluno aluno,
		String data, String hora, String finalidade, String cadeiras_reservadas)
<<<<<<< HEAD:sisres/src/control/ManterResSalaAluno.java
		throws SQLException, ReservaException, ClienteException, PatrimonioException, NumberFormatException, DataFormatException {
=======
		throws SQLException, ReservaException, ClienteException, PatrimonyException {
>>>>>>> devel:sisres/src/control/ManterResSalaAluno.java

		ReservaSalaAluno r = new ReservaSalaAluno(data, hora, sala, finalidade, cadeiras_reservadas, aluno);
		ReservationRoomForStudentDAO.getInstance().add(r);
		this.rev_sala_aluno_vet.add(r);
	}

	public void alterar(String finalidade, String cadeiras_reservadas, ReservaSalaAluno r)
		throws SQLException, ReservaException, ClienteException, PatrimonyException {

		ReservaSalaAluno res_old = new ReservaSalaAluno(r.getData(), r.getHora(), r.getSala(),
			r.getFinalidade(), r.getCadeiras_reservadas(), r.getAluno());
		r.setFinalidade(finalidade);
		r.setCadeiras_reservadas(cadeiras_reservadas);
		ReservationRoomForStudentDAO.getInstance().alterar(res_old, r);
	}

	public void excluir(ReservaSalaAluno r) throws SQLException, ReservaException {
		ReservationRoomForStudentDAO.getInstance().delete(r);
		this.rev_sala_aluno_vet.remove(r);
	}
}
