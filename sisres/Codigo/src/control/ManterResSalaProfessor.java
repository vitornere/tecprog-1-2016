package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.ResSalaProfessorDAO;

import model.Professor;
import model.ReservaSalaProfessor;
import model.Classroom;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

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
		
		public Vector<ReservaSalaProfessor> buscarPorData(String data) throws SQLException, ClientException, PatrimonyException, ReserveException{
	        return ResSalaProfessorDAO.getInstance().buscarPorData(data);
	    } 
	    	
		
	public Vector<ReservaSalaProfessor> getResProfessorSala_vet() throws SQLException, ClientException, PatrimonyException, ReserveException {
		this.rev_sala_professor_vet = ResSalaProfessorDAO.getInstance().buscarTodos();
		return this.rev_sala_professor_vet;
	}

	public void inserir(Classroom sala, Professor prof,
						String data, String hora, String finalidade) 
					throws SQLException, ReserveException {

		ReservaSalaProfessor reserva = new ReservaSalaProfessor(data, hora, sala , finalidade, prof);
		ResSalaProfessorDAO.getInstance().incluir(reserva);
		this.rev_sala_professor_vet.add(reserva);
	}

	public void alterar(String finalidade, ReservaSalaProfessor reserva) 
				throws SQLException, ReserveException {
		
		ReservaSalaProfessor reserva_old = new ReservaSalaProfessor(reserva.getDate(), reserva.getHour(), reserva.getClassroom() , 
				reserva.getFinality(), reserva.getProfessor());
		
		reserva.setFinality(finalidade);
		ResSalaProfessorDAO.getInstance().alterar(reserva_old, reserva);
		
	}

	public void excluir(ReservaSalaProfessor reserva) throws SQLException, ReserveException {
		ResSalaProfessorDAO.getInstance().excluir(reserva);
		this.rev_sala_professor_vet.remove(reserva);
	}
}