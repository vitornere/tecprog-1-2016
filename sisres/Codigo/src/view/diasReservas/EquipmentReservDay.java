/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasReservas;

import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.Equipamento;
import view.horariosReservas.HorariosReservaEquipamento;
import view.horariosReservas.HorariosReservaPatrimonio;
import control.ManterEquipamento;
import exception.PatrimonioException;

/**
 * 
 * @author Parley
 */
public class EquipmentReservDay extends DiaReservaPatrimonio {

    Equipamento equipment;

    public EquipmentReservDay(Frame parent, boolean modal, int indexEquipment) throws SQLException, PatrimonioException {
        super(parent, modal);
        equipment = ManterEquipamento.getInstance().getEquipamento_vet().get(indexEquipment);
    }

    @Override protected void visualizarAction(String data) {
        HorariosReservaPatrimonio booking = new HorariosReservaEquipamento(new JFrame(), true, data, this.equipment);
        booking.setVisible(true);
        booking.setResizable(false);
    }
}
