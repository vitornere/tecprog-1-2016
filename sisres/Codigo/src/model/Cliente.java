package model;

import exception.ClientException;


/*Para fazer uma melhor validacoa e captura do dados
 * se pega todos os dados como string.
 * 
 * 
 */
public abstract class Cliente {
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	protected String matricula;
	
	//Mensagens de Erro e Alertas
		private final String NOME_INVALIDO = "Nome Invalido.";
		private final String NOME_BRANCO = "Nome em Branco.";
		private final String NOME_NULO = "Nome esta Nulo.";
		private final String CPF_INVALIDO = "CPF Invalido.";
		private final String CPF_BRANCO = "CPF em Branco.";
		private final String CPF_NULO = "CPF esta Nulo.";
		private final String TELEFONE_INVALIDO = "Telefone Invalido.";
		//private final String TELEFONE_BRANCO = "Telefone em Branco.";
		private final String TELEFONE_NULO = "Telefone esta Nulo.";
		//private final String EMAIL_INVALIDO = "E-mail Invalido.";
		//private final String EMAIL_BRANCO = "E-mail em Branco.";
		private final String EMAIL_NULO = "E-mail esta Nulo.";
	
	
	public Cliente(String nome, String cpf, String matricula,
			String telefone, String email) throws ClientException{
		this.setNamePerson(nome);
		this.setCpfPerson(cpf);
        this.setIdPerson(matricula);
		this.setPhonePerson(telefone);
		this.setEmailPerson(email);
	}

	public String getNamePerson() {
		return nome;
	}
	
	public String getCpfPerson() {
		return cpf;
	}
	
	public String getPhonePerson() {
		return telefone;
	}
	
	public String getEmailPerson() {
		return email;
	}
	
	public String getIdRegister() {
		return matricula;
	}
	
	public void setNamePerson(String nome) throws ClientException{
		if(nome == null)
			throw new ClientException(NOME_NULO);
		else if("".equals(nome.trim()))
			throw new ClientException(NOME_BRANCO);
		else if(nome.trim().matches("[a-zA-Z][a-zA-Z\\s]+"))
			this.nome = nome.trim();
		else
			throw new ClientException(NOME_INVALIDO);
	}
	
	public void setCpfPerson(String cpf) throws ClientException {
		if(cpf == null)
			throw new ClientException(CPF_NULO);
		else if("".equals(cpf))
			throw new ClientException(CPF_BRANCO);
		else if(cpf.matches("[\\d]{3,3}.[\\d]{3,3}.[\\d]{3,3}-[\\d]{2,2}$"))
		{
			if(this.validarCpf(
					cpf.split("[\\. | -]")[0] + 
					cpf.split("[\\. | -]")[1] + 
					cpf.split("[\\. | -]")[2] + 
					cpf.split("[\\. | -]")[3]))
				this.cpf = cpf;
			else
				throw new ClientException(CPF_INVALIDO);
		}
		else
			throw new ClientException(CPF_INVALIDO);
	}
	
	public void setPhonePerson(String telefone) throws ClientException {
		if(telefone == null)
			throw new ClientException(TELEFONE_NULO);
		else if("".equals(telefone))
			this.telefone = telefone;
		//Telefone ser� guardado sem espa�os.
		else if(telefone.matches("(\\([ ]*[\\d]{2,3}[ ]*\\))?[ ]*[\\d]{4,4}[ ]*-?[ ]*[\\d]{4,4}[ ]*$"))
			this.telefone = telefone.replaceAll(" ", "");
		else
			throw new ClientException(TELEFONE_INVALIDO);
	}
	
	public void setEmailPerson(String email) throws ClientException {
		if(email == null)
			throw new ClientException(EMAIL_NULO);
		else
			this.email = email;
	}
	
	public abstract void setIdPerson(String matricula) throws ClientException;
	
	@Override
	public String toString() {
		return "Nome: " + nome + 
			"\nCpf: " + cpf + 
			"\nTelefone: " + telefone + 
			"\nEmail: " + email + 
			"\nMatricula: " + matricula;
	}

	public boolean equals(Cliente b){
		if(	this.getNamePerson().equals(b.getNamePerson()) &&
			this.getCpfPerson().equals(b.getCpfPerson()) &&
			this.getIdRegister().equals(b.getIdRegister()) &&
			this.getPhonePerson().equals(b.getPhonePerson()) &&
			this.getEmailPerson().equals(b.getEmailPerson())){
			
			return true;
		}
		return false;
	}
	
	private boolean validarCpf(String cpf) {

		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String	nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < cpf.length() -1; nCount++)
		{
			 digitoCPF = Integer.valueOf (cpf.substring(nCount -1, nCount)).intValue();

			 //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
			 d1 = d1 + ( 11 - nCount ) * digitoCPF;

			 //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
			 d2 = d2 + ( 12 - nCount ) * digitoCPF;
		};

		//Primeiro resto da divisão por 11.
		resto = (d1 % 11);

		//Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
		if (resto < 2)
			 digito1 = 0;
		else
			 digito1 = 11 - resto;

		d2 += 2 * digito1;

		//Segundo resto da divisão por 11.
		resto = (d2 % 11);

		//Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
		if (resto < 2)
			 digito2 = 0;
		else
			 digito2 = 11 - resto;

		//Digito verificador do CPF que está sendo validado.
		String nDigVerific = cpf.substring (cpf.length()-2, cpf.length());

		//Concatenando o primeiro resto com o segundo.
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		//comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
		return nDigVerific.equals(nDigResult);

	} // fim do método validarCpf

}
