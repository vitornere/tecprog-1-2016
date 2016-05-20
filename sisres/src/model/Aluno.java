package model;

import java.util.InputMismatchException;

import exception.ClienteException;

public class Aluno extends Cliente {
	
	//Mensagens de Erro e Alertas
		//private final String MATRICULA_INVALIDO = "Matricula Invalido.";
		private final String MATRICULA_BRANCO = "Matricula em Branco.";
		private final String MATRICULA_NULO = "Matricula esta Nula.";
		
	
	public Aluno(String nome, String cpf, String matricula,
			String telefone, String email) throws InputMismatchException, ClienteException {
		super(nome, cpf, matricula, telefone, email);
	}

	public void setMatricula(String matricula) throws  ClienteException {
		try{
			if(matricula == null)
				throw new ClienteException(MATRICULA_NULO);
			else if("".equals(matricula.trim()))
				throw new ClienteException(MATRICULA_BRANCO);
			super.matricula = matricula;//
		}catch(Exception ex){
			System.out.println("erro! "+ ex.getStackTrace());
		}finally{
			System.out.println("O programa será encerrado");
		}	
	}
}

