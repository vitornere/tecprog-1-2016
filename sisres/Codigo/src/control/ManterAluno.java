package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.AlunoDAO;
import exception.ClientException;
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
	
	public Vector<Aluno> buscarNome(String valor) throws SQLException, ClientException {
		return AlunoDAO.getInstance().buscarNome(valor);
	}
	public Vector<Aluno> buscarCpf(String valor) throws SQLException, ClientException {
		return AlunoDAO.getInstance().buscarCpf(valor);
	}
	public Vector<Aluno> buscarMatricula(String valor) throws SQLException, ClientException {
		return AlunoDAO.getInstance().buscarMatricula(valor);
	}
	public Vector<Aluno> buscarEmail(String valor) throws SQLException, ClientException {
		return AlunoDAO.getInstance().buscarEmail(valor);
	}
	public Vector<Aluno> buscarTelefone(String valor) throws SQLException, ClientException {
		return AlunoDAO.getInstance().buscarTelefone(valor);
	}
		
		
	public Vector<Aluno> getAluno_vet() throws SQLException, ClientException{
		this.alunos_vet = AlunoDAO.getInstance().buscarTodos();
		return this.alunos_vet;
	}
	
	public void inserir(String nome, String cpf, String matricula,
			String telefone, String email) throws ClientException, SQLException {
		Aluno aluno = new Aluno(nome, cpf, matricula, telefone, email);
		AlunoDAO.getInstance().incluir(aluno);
		this.alunos_vet.add(aluno);
	}

	public void alterar(String nome, String cpf, String matricula,
			String telefone, String email, Aluno aluno) throws ClientException, SQLException {
		Aluno aluno_velho = new Aluno(
								aluno.getName(),
								aluno.getCpfProfessor(),
								aluno.getIdProfessor(),
								aluno.getPhoneProfessor(),
								aluno.getEmailProfessor());
		aluno.setName(nome);
		aluno.setCpfProfessor(cpf);
		aluno.setIdProfessor(matricula);
		aluno.setPhoneProfessor(telefone);
		aluno.setEmailProfessor(email);
		AlunoDAO.getInstance().alterar(aluno_velho, aluno);
	}

	public void excluir(Aluno aluno) throws SQLException, ClientException {
		AlunoDAO.getInstance().excluir(aluno);
		this.alunos_vet.remove(aluno);
	}

}
