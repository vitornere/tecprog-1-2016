package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import exception.ReserveException;

public class Reserve {

	private String hour;
	private String date;

	private final String NULL_HOUR = "A hora esta nula.";
	private final String INVALID_HOUR = "A hora eh invalida.";
	private final String EMPTY_HOUR = "A hora esta em branco.";
	private final String PATTERN_HOUR = "^[012]?[\\d]:[0-5][\\d]$";
	private final String NULL_DATE = "A data esta nula.";
	private final String INVALID_DATE = "A data eh invalida.";
	private final String EMPTY_DATE = "A data esta em branco.";
	private final String PATTERN_DATE = "^[0123]?[\\d]([./-])[01]?[\\d]\\1[\\d]{2,4}$";

	public Reserve(String date, String hour) throws ReserveException {
		this.setDate(date);
		this.setHour(hour);
	}

	public String getHour() {
		return this.hour;
	}

	public String getDate() {
		return this.date;
	}

	public void setHour(String hour) throws ReserveException {
		if ((hour != null) && (hour.equals("") == false)
				&& (hour.matches(PATTERN_HOUR))) {
			hour = hour.trim();
			this.hour = hour;
		}
		else if (hour == null) {
			throw new ReserveException(NULL_HOUR);
		} else if (hour.equals("")) {
			throw new ReserveException(EMPTY_HOUR);
		} else if (hour.matches(PATTERN_HOUR)) {
			if (hour.length() == 4) {
				this.hour = "0" + hour;
			} else {
				// Nothing to do.
			}
		} else {
			throw new ReserveException(INVALID_HOUR);
		}
	}

	public void setDate(String date) throws ReserveException {
		if (date != null) {
			// Nothing to do.
		}
		else
		{
			throw new ReserveException(NULL_DATE);
		}
		if (!date.equals("")) {
			// Nothing to do.
		}
		else
		{
			throw new ReserveException(EMPTY_DATE);
		}

		date = date.trim();
		if (date.matches(PATTERN_DATE)) {
			this.date = padronizeDate(date);
		}  else {
			throw new ReserveException(INVALID_DATE);
		}

	}

	public boolean equals(Reserve obj) {
		return (this.hour.equals(obj.getHour()) && this.date.equals(obj
				.getDate()));
	}

	@Override
	public String toString() {
		return "\nHour=" + this.hour + "\nDate=" + this.date;
	}

	private static String padronizeDate(String date) {
		String now[] = currentDate().split("[./-]");
		String parts[] = date.split("[./-]");
		String dateInThePattern = "";
		final int PARTS_OF_THE_DATE = 3;

		for (int dateIndex = 0; dateIndex < PARTS_OF_THE_DATE; dateIndex++) {
			if (dateIndex == 0)
				dateInThePattern += now[dateIndex].substring(0,
						now[dateIndex].length() - parts[dateIndex].length())
						+ parts[dateIndex];
			else
				dateInThePattern += "/"
						+ now[dateIndex].substring(0, now[dateIndex].length()
								- parts[dateIndex].length()) + parts[dateIndex];

		}

		return dateInThePattern;
	}

	private static String currentDate() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
		return formator.format(date);
	}
}
