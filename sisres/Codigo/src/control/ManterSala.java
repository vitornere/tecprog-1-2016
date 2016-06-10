package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.ClassroomDAO;
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
		this.salas_vet = ClassroomDAO.getInstance().searchAll();
		return this.salas_vet;
	}

	public void inserir(String codigo, String descricao, String capacidade) throws PatrimonyException, SQLException {
		Classroom sala = new Classroom(codigo, descricao, capacidade);
		ClassroomDAO.getInstance().add(sala);
		this.salas_vet.add(sala);
	}

	public void alterar(String codigo, String descricao, String capacidade, Classroom sala) throws PatrimonyException, SQLException {
		Classroom old_sala = new Classroom(sala.getCode(), sala.getDescription(),
								sala.getCapacity());
		sala.setCodigo(codigo);
		sala.setDescricao(descricao);
		sala.setCapacidade(capacidade);
		ClassroomDAO.getInstance().change(old_sala, sala);
	}

	public void excluir(Classroom sala) throws SQLException, PatrimonyException {
		ClassroomDAO.getInstance().delete(sala);
		this.salas_vet.remove(sala);
	}

}
