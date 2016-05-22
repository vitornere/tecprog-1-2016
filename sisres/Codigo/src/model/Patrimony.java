package model;

import exception.PatrimonyException;

public class Patrimony {

	private String idPatrimony;
	private String descriptionPatrimony;

	private final String EMPTY_ID_PATRIMONY = "Codigo em Branco.";
	private final String NULL_ID_PATRIMONY = "Codigo esta Nulo.";
	private final String EMPTY_DESCRIPTION_PATRIMONY = "Descricao em Branco.";
	private final String NULL_DESCRIPTION_PATRIMONY = "Descricao esta Nula.";

	public Patrimony(String idPatrimony, String descriptionPatrimony)
			throws PatrimonyException {
		this.setIdEquipment(idPatrimony);
		this.setDescriptionEquipment(descriptionPatrimony);
	}

	public String getIdEquipment() {
		return idPatrimony;
	}

	public String getDescriptionEquipment() {
		return descriptionPatrimony;
	}

	public void setIdEquipment(String idPatrimony) throws PatrimonyException {
		if ((idPatrimony != null) || (!("".equals(idPatrimony.trim())))) {
			this.idPatrimony = idPatrimony;
		} else if (idPatrimony == null) {
			throw new PatrimonyException(NULL_ID_PATRIMONY);
		} else {
			throw new PatrimonyException(EMPTY_ID_PATRIMONY);
		}
	}

	public void setDescriptionEquipment(String descriptionPatrimony)
			throws PatrimonyException {
		if ((descriptionPatrimony != null)
				|| (!("".equals(descriptionPatrimony.trim())))) {
			this.descriptionPatrimony = descriptionPatrimony;
		} else if (descriptionPatrimony == null) {
			throw new PatrimonyException(NULL_DESCRIPTION_PATRIMONY);
		} else {
			throw new PatrimonyException(EMPTY_DESCRIPTION_PATRIMONY);
		}
	}

	public boolean equals(Patrimony patrimony) {
		if (this.getIdEquipment().equals(patrimony.getIdEquipment())
				&& this.getDescriptionEquipment().equals(
						patrimony.getDescriptionEquipment())) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {
		return "Codigo=" + idPatrimony + "\nDescricao=" + descriptionPatrimony;
	}
}
