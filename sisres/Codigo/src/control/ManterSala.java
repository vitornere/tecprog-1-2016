package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.SalaDAO;
import exception.PatrimonyException;
import model.Classroom;

public class ManterSala {

	private Vector<Classroom> salas_vet = new Vector<Classroom>();
	
	//Singleton
		private static ManterSala instance;
		private ManterSala() {
		}
		public static ManterSala getInstance() {
		if(instance == null)
			instance = new ManterSala();
		return instance;
	}
	//
		
	public Vector<Classroom> getSalas_vet() throws SQLException, PatrimonyException{
		this.salas_vet = SalaDAO.getInstance().buscarTodos();
		return this.salas_vet;
	}

	public void inserir(String codigo, String descricao, String capacidade) throws PatrimonyException, SQLException {
		Classroom sala = new Classroom(codigo, descricao, capacidade);
		SalaDAO.getInstance().incluir(sala);
		this.salas_vet.add(sala);
	}

	public void alterar(String codigo, String descricao, String capacidade, Classroom sala) throws PatrimonyException, SQLException {
		Classroom old_sala = new Classroom(sala.getIdEquipment(), sala.getDescriptionEquipment(),
								sala.getCapacidade());
		sala.setIdEquipment(codigo);
		sala.setDescriptionEquipment(descricao);
		sala.setCapacidade(capacidade);
		SalaDAO.getInstance().alterar(old_sala, sala);
	}

	public void excluir(Classroom sala) throws SQLException, PatrimonyException {
		SalaDAO.getInstance().excluir(sala);
		this.salas_vet.remove(sala);
	}

}
