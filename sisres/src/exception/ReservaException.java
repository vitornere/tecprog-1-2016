package exception;

import javax.xml.crypto.Data;

@SuppressWarnings("serial")
public class ReservaException extends Exception{
	
	public ReservaException() {
		super();
	}
	
	public ReservaException(String msg) {
		super(msg);
	}
	
	public void DataException(){
		System.out.println("erro" + getStackTrace());
	}
}
