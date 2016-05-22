package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import exception.ReserveException;

public class Reserva {

	private String hora;
	private String data;
	
	//Mesagens, Alertas e Padroes
		private final String HORA_NULA = "A hora esta nula.";
		private final String HORA_INVALIDA = "A hora eh invalida.";
		private final String HORA_BRANCA = "A hora esta em branco.";
		private final String HORA_PATTERN = "^[012]?[\\d]:[0-5][\\d]$";
		private final String DATA_NULA = "A data esta nula.";
		private final String DATA_INVALIDA = "A data eh invalida.";
		private final String DATA_BRANCA = "A data esta em branco.";
		private final String DATA_PATTERN = "^[0123]?[\\d]([./-])[01]?[\\d]\\1[\\d]{2,4}$";
	
	public Reserva(String data, String hora) throws ReserveException {
		this.setData(data);
		this.setHora(hora);
	}

	public String getHour() {
		return this.hora;
	}

	public String getDate() {
		return this.data;
	}

	public void setHora(String hora) throws ReserveException {
		if(hora == null)
			throw new ReserveException(HORA_NULA);
		
		hora = hora.trim();
		if(hora.equals(""))
			throw new ReserveException(HORA_BRANCA);
		else if(hora.matches(HORA_PATTERN)){
			if(hora.length() == 4)
				this.hora = "0" + hora;
			else
				this.hora = hora;
		}
		else
			throw new ReserveException(HORA_INVALIDA);
	}

	public void setData(String data) throws ReserveException {
		if(data == null)
			throw new ReserveException(DATA_NULA);
		
		data = data.trim();
		if(data.equals(""))
			throw new ReserveException(DATA_BRANCA);
		else if(data.matches(DATA_PATTERN)){
			this.data = padronizarData(data);
		}
		else
			throw new ReserveException(DATA_INVALIDA);
		
	}

	public boolean equals(Reserva obj) {
		return (this.hora.equals(obj.getHour()) &&
			this.data.equals(obj.getDate()));
	}
	
	@Override
	public String toString() {
		return "\nHora=" + this.hora 
			+ "\nData=" + this.data;
	}
	
	private static String padronizarData(String data){
		String agora[] = dataAtual().split("[./-]");
		String partes[] = data.split("[./-]");
		String dataNoPadrao = "";
		
		for(int i = 0; i < 3; i++){
			if(i == 0)
				dataNoPadrao += agora[i].substring(0, 
						agora[i].length() - partes[i].length()) + partes[i];
			else
				dataNoPadrao +=  "/" + agora[i].substring(0, 
						agora[i].length() - partes[i].length()) + partes[i];
				
		}
		
		return dataNoPadrao;
	}
	
	private static String dataAtual(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}
}
