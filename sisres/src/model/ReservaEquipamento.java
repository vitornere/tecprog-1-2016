package model;

import java.io.ObjectStreamException;
import java.util.zip.DataFormatException;

import exception.ReservaException;

public class ReservaEquipamento extends Reserva {

    private Equipamento equipamento;

    // Mensagens
    private final String EQUIPAMENTO_NULO = "O equipamneto esta nulo.";

    public ReservaEquipamento(String data, String hora, Equipamento equipamento) throws ReservaException, DataFormatException{
        super(data, hora);
        this.setEquipamento(equipamento);
    }

    public Equipamento getEquipamento() {
        return this.equipamento;
    }

    public void setEquipamento(Equipamento equipamento) throws ReservaException {
        if (equipamento == null)
            throw new ReservaException(EQUIPAMENTO_NULO);
        this.equipamento = equipamento;
    }

    public boolean equals(ReservaEquipamento obj) throws ObjectStreamException {
    	return (super.equals(obj) && this.getEquipamento().equals(obj.getEquipamento()));
    }

    @Override public String toString() {
        return "ReservaEquipamento [equipamento=" + this.getEquipamento() + ", toString()=" + super.toString() + "]";
    }

}
