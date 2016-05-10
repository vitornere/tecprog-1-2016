/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasReservas;

import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.Equipamento;
import view.timeReservations.HoursEquipamentReservation;
import view.timeReservations.HoursPatrimonyReservation;
import control.ManterEquipamento;
import exception.PatrimonioException;

/**
 * 
 * @author Parley
 */
public class EquipmentReserveDay extends PatrimonyReserveDay {

    Equipamento equipment;

    public EquipmentReserveDay(Frame parent, boolean modal, int indexEquipment) throws SQLException, PatrimonioException {
        super(parent, modal);
        
        equipment = ManterEquipamento.getInstance().getEquipamento_vet().get(indexEquipment);
    }

    @Override protected void viewAction(String data) {
        HoursPatrimonyReservation booking = new HoursEquipamentReservation(new JFrame(), true, data, this.equipment);
        
        booking.setVisible(true);
        
        booking.setResizable(false);
    }
}
