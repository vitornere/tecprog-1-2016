package model;

import exception.ClientException;

public abstract class Client {
	private String nameClient;
	private String cpfClient;
	private String phoneClient;
	private String emailClient;
	protected String idClient;

	private final String INVALID_NAME = "Nome Invalido.";
	private final String EMPTY_NAME = "Nome em Branco.";
	private final String NULL_NAME = "Nome esta Nulo.";
	private final String INVALID_CPF = "CPF Invalido.";
	private final String EMPTY_CPF = "CPF em Branco.";
	private final String NULL_CPF = "CPF esta Nulo.";
	private final String INVALID_PHONE = "Telefone Invalido.";
	private final String NULL_PHONE = "Telefone esta Nulo.";
	private final String NULL_EMAIL = "E-mail esta Nulo.";

	public Client(String nameClient, String cpfClient, String idClient,
			String phoneClient, String emailClient) throws ClientException {
		this.setNamePerson(nameClient);
		this.setCpfPerson(cpfClient);
		this.setIdPerson(idClient);
		this.setPhonePerson(phoneClient);
		this.setEmailPerson(emailClient);
	}

	public String getNamePerson() {
		return nameClient;
	}

	public String getCpfPerson() {
		return cpfClient;
	}

	public String getPhonePerson() {
		return phoneClient;
	}

	public String getEmailPerson() {
		return emailClient;
	}

	public String getIdRegister() {
		return idClient;
	}

	public void setNamePerson(String nameClient) throws ClientException {
		if (nameClient != null) {
			// Nothing to do
		} else {
			throw new ClientException(NULL_NAME);
		}
		if (nameClient.trim().matches("[a-zA-Z][a-zA-Z\\s]+")) {
			this.nameClient = nameClient.trim();
		} else if ("".equals(nameClient.trim())) {
			throw new ClientException(EMPTY_NAME);
		} else {
			throw new ClientException(INVALID_NAME);
		}
	}

	public void setCpfPerson(String cpfClient) throws ClientException {
		if (cpfClient != null) {
			// Nothing to do.
		} else {
			throw new ClientException(NULL_CPF);
		}
		if (!"".equals(cpfClient)) {
			// Nothing to do
		} else {
			throw new ClientException(EMPTY_CPF);
		}

		if (cpfClient.matches("[\\d]{3,3}.[\\d]{3,3}.[\\d]{3,3}-[\\d]{2,2}$")
				&& this.validateCpfClient(cpfClient.split("[\\. | -]")[0]
						+ cpfClient.split("[\\. | -]")[1]
						+ cpfClient.split("[\\. | -]")[2]
						+ cpfClient.split("[\\. | -]")[3])) {
			this.cpfClient = cpfClient;
		} else {
			throw new ClientException(INVALID_CPF);
		}
	}

	public void setPhonePerson(String phoneClient) throws ClientException {
		if (phoneClient != null) {
			// Nothing to do.
		} else {
			throw new ClientException(NULL_PHONE);
		}

		if (phoneClient
				.matches("(\\([ ]*[\\d]{2,3}[ ]*\\))?[ ]*[\\d]{4,4}[ ]*-?[ ]*[\\d]{4,4}[ ]*$")) {
			this.phoneClient = phoneClient.replaceAll(" ", "");
		} else if ("".equals(phoneClient)) {
			this.phoneClient = phoneClient;
		} else {
			throw new ClientException(INVALID_PHONE);
		}
	}

	public void setEmailPerson(String emailClient) throws ClientException {
		if (emailClient != null) {
			this.emailClient = emailClient;
		} else {
			throw new ClientException(NULL_EMAIL);
		}
	}

	public abstract void setIdPerson(String idClient) throws ClientException;

	@Override
	public String toString() {
		return "Nome: " + nameClient + "\nCpf: " + cpfClient + "\nTelefone: "
				+ phoneClient + "\nEmail: " + emailClient + "\nMatricula: "
				+ idClient;
	}

	public boolean equals(Client client) {
		return this.getNamePerson().equals(client.getNamePerson())
				&& this.getCpfPerson().equals(client.getCpfPerson())
				&& this.getIdRegister().equals(client.getIdRegister())
				&& this.getPhonePerson().equals(client.getPhonePerson())
				&& this.getEmailPerson().equals(client.getEmailPerson());
	}

	private boolean validateCpfClient(String cpfClient) {

		int firstDigitCpf, secondDigitCpf;
		int firstDigit, secondDigit, remainder;
		int digitCpf;
		String digitResult;

		firstDigitCpf = secondDigitCpf = 0;
		firstDigit = secondDigit = remainder = 0;

		for (int numberCount = 1; numberCount < cpfClient.length() - 1; numberCount++) {
			digitCpf = Integer.valueOf(
					cpfClient.substring(numberCount - 1, numberCount))
					.intValue();

			// multiply numeral place for the last 2 to 3 by following the
			// following by 4 and so on.
			firstDigitCpf = firstDigitCpf + (11 - numberCount) * digitCpf;

			// for the second digit repeat the procedure including the first
			// digit calculated in the previous step.
			secondDigitCpf = secondDigitCpf + (12 - numberCount) * digitCpf;
		}
		;

		// First remainder of the division by 11.
		remainder = (firstDigitCpf % 11);

		// If the result is 0 or 1, the digit is 0 otherwise the digit is 11
		// minus the previous result.
		if (remainder < 2) {
			firstDigit = 0;
		} else {
			firstDigit = 11 - remainder;
		}

		secondDigitCpf += 2 * firstDigit;

		// Second remainder of the division by 11.
		remainder = (secondDigitCpf % 11);

		// If the result is 0 or 1, the digit is 0 otherwise the digit is 11
		// minus the previous result.
		if (remainder < 2) {
			secondDigit = 0;
		} else {
			secondDigit = 11 - remainder;
		}

		// Checker Digit CPF being validated.
		String numberDigitVerific = cpfClient.substring(cpfClient.length() - 2,
				cpfClient.length());

		// Concatenating the first rest with the second.
		digitResult = String.valueOf(firstDigit) + String.valueOf(secondDigit);

		// Compare cpf tester I type the first rest + the second rest.
		return numberDigitVerific.equals(digitResult);

	}

}
