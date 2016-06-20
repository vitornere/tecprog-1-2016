/**
 * Name: ClientException.java
 * Class of exception client.
 */
package exception;

@SuppressWarnings("serial")
public class ClientException extends Exception {

	public ClientException() {
		super();
	}

	public ClientException(String message) {
		super(message);
	}

}
