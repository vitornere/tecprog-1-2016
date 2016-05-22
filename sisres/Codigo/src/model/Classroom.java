package model;

import exception.PatrimonyException;

public class Classroom extends Patrimonio {

	private String capacity;

	private final String INVALID_CAPACITY = "Capacidade Invalida.";
	private final String EMPTY_CAPACITY = "Capacidade em Branco.";
	private final String NULL_CAPACITY = "Capacidade esta nula.";

	public Classroom(String idClassroom, String descriptionClassroom,
			String capacityClassroom) throws PatrimonyException {
		super(idClassroom, descriptionClassroom);
		this.setCapacity(capacityClassroom);
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) throws PatrimonyException {
		if (capacity.matches("[\\d]+")) {
			this.capacity = capacity;
		} else if (capacity == null) {
			throw new PatrimonyException(NULL_CAPACITY);
		} else if ("".equals(capacity.trim())) {
			throw new PatrimonyException(EMPTY_CAPACITY);
		}

		else {
			throw new PatrimonyException(INVALID_CAPACITY);
		}
	}

	public boolean equals(Classroom classroom) {
		if (super.equals(classroom)
				&& this.getCapacity().equals(classroom.getCapacity())) {
			return true;
		}

		return false;
	}
}
