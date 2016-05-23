package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.StudentDAO;
import exception.ClienteException;
import model.Aluno;

public class ManterAluno {
	
	private Vector<Aluno> alunos_vet = new Vector<Aluno>();
	
	//Singlenton
		private static ManterAluno instance;
		private ManterAluno() {
	}
		public static ManterAluno getInstance() {
		if(instance == null)
			instance = new ManterAluno();
		return instance;
	}
	//
	
	public Vector<Aluno> buscarNome(String valor) throws SQLException, ClienteException {
		return StudentDAO.getInstance().searchByName(valor);
	}
	public Vector<Aluno> buscarCpf(String valor) throws SQLException, ClienteException {
		return StudentDAO.getInstance().searchByCpf(valor);
	}
	public Vector<Aluno> buscarMatricula(String valor) throws SQLException, ClienteException {
		return StudentDAO.getInstance().searchByRegister(valor);
	}
	public Vector<Aluno> buscarEmail(String valor) throws SQLException, ClienteException {
		return StudentDAO.getInstance().searcByEmail(valor);
	}
	public Vector<Aluno> buscarTelefone(String valor) throws SQLException, ClienteException {
		return StudentDAO.getInstance().searchByPhone(valor);
	}
		
		
	public Vector<Aluno> getAluno_vet() throws SQLException, ClienteException{
		this.alunos_vet = StudentDAO.getInstance().searchAll();
		return this.alunos_vet;
	}
	
	public void inserir(String nome, String cpf, String matricula,
			String telefone, String email) throws ClienteException, SQLException {
		Aluno aluno = new Aluno(nome, cpf, matricula, telefone, email);
		StudentDAO.getInstance().add(aluno);
		this.alunos_vet.add(aluno);
	}

	public void alterar(String nome, String cpf, String matricula,
			String telefone, String email, Aluno aluno) throws ClienteException, SQLException {
		Aluno aluno_velho = new Aluno(
								aluno.getNome(),
								aluno.getCpf(),
								aluno.getMatricula(),
								aluno.getTelefone(),
								aluno.getEmail());
		aluno.setNome(nome);
		aluno.setCpf(cpf);
		aluno.setMatricula(matricula);
		aluno.setTelefone(telefone);
		aluno.setEmail(email);
		StudentDAO.getInstance().change(aluno_velho, aluno);
	}

	public void excluir(Aluno aluno) throws SQLException, ClienteException {
		StudentDAO.getInstance().delete(aluno);
		this.alunos_vet.remove(aluno);
	}

}
