package model;

import exception.ReserveException;

public class EquipmentReserve extends Reserve {

    private Equipment equipamento;

    // Mensagens
    private final String EQUIPAMENTO_NULO = "O equipamneto esta nulo.";

    public EquipmentReserve(String data, String hora, Equipment equipamento) throws ReserveException {
        super(data, hora);
        this.setEquipamento(equipamento);
    }

    public Equipment getEquipment() {
        return this.equipamento;
    }

    public void setEquipamento(Equipment equipamento) throws ReserveException {
        if (equipamento == null)
            throw new ReserveException(EQUIPAMENTO_NULO);
        this.equipamento = equipamento;
    }

    public boolean equals(EquipmentReserve obj) {
        return (super.equals(obj) && this.getEquipment().equals(obj.getEquipment()));
    }

    @Override public String toString() {
        return "ReservaEquipamento [equipamento=" + this.getEquipment() + ", toString()=" + super.toString() + "]";
    }

}