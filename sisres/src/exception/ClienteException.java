package exception;

import java.util.InputMismatchException;

@SuppressWarnings("serial")
public class ClienteException extends Exception {

	public ClienteException() {
		super();
	}

	public ClienteException(String message) {
		super(message);
	}
	
	public String InputMismatchException(String message){
		return "Há campos a serem preenchidos";
	}

}
