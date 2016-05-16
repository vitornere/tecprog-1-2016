package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.ReserveClassroomProfessorDAO;

import model.Professor;
import model.ReservaSalaProfessor;
import model.Classroom;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;

public class ManterResSalaProfessor {
	private Vector<ReservaSalaProfessor> rev_sala_professor_vet = new Vector<ReservaSalaProfessor>();
	
	//Singleton
		private static ManterResSalaProfessor instance;
		private ManterResSalaProfessor() {
		}
		public static ManterResSalaProfessor getInstance() {
		if(instance == null)
			instance = new ManterResSalaProfessor();
		return instance;
	}
	//
		
		public Vector<ReservaSalaProfessor> buscarPorData(String data) throws SQLException, ClienteException, PatrimonyException, ReservaException{
	        return ReserveClassroomProfessorDAO.getInstance().buscarPorData(data);
	    } 
	    	
		
	public Vector<ReservaSalaProfessor> getResProfessorSala_vet() throws SQLException, ClienteException, PatrimonyException, ReservaException {
		this.rev_sala_professor_vet = ReserveClassroomProfessorDAO.getInstance().buscarTodos();
		return this.rev_sala_professor_vet;
	}

	public void inserir(Classroom sala, Professor prof,
						String data, String hora, String finalidade) 
					throws SQLException, ReservaException {

		ReservaSalaProfessor reserva = new ReservaSalaProfessor(data, hora, sala , finalidade, prof);
		ReserveClassroomProfessorDAO.getInstance().add(reserva);
		this.rev_sala_professor_vet.add(reserva);
	}

	public void alterar(String finalidade, ReservaSalaProfessor reserva) 
				throws SQLException, ReservaException {
		
		ReservaSalaProfessor reserva_old = new ReservaSalaProfessor(reserva.getData(), reserva.getHora(), reserva.getSala() , 
				reserva.getFinalidade(), reserva.getProfessor());
		
		reserva.setFinalidade(finalidade);
		ReserveClassroomProfessorDAO.getInstance().change(reserva_old, reserva);
		
	}

	public void excluir(ReservaSalaProfessor reserva) throws SQLException, ReservaException {
		ReserveClassroomProfessorDAO.getInstance().delete(reserva);
		this.rev_sala_professor_vet.remove(reserva);
	}
}