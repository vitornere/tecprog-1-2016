package model;

import exception.ClientException;

public class Student extends Cliente {
	
	//Mensagens de Erro e Alertas
		//private final String MATRICULA_INVALIDO = "Matricula Invalido.";
		private final String MATRICULA_BRANCO = "Matricula em Branco.";
		private final String MATRICULA_NULO = "Matricula esta Nula.";
		
	
	public Student(String nome, String cpf, String matricula,
			String telefone, String email) throws ClientException {
		super(nome, cpf, matricula, telefone, email);
	}

	public void setIdPerson(String matricula) throws ClientException {
		if(matricula == null)
			throw new ClientException(MATRICULA_NULO);
		else if("".equals(matricula.trim()))
			throw new ClientException(MATRICULA_BRANCO);
		//else if(matricula.matches("^[\\d]{2,2}/[\\d]{5,7}$"))
			//super.matricula = matricula;
		//else
			//throw new ClienteException(MATRICULA_INVALIDO);
		super.matricula = matricula;//
	}
}

