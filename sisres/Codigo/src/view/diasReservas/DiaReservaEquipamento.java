/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasReservas;

import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JFrame;

<<<<<<< HEAD
import model.Equipment;
import view.horariosReservas.HorariosReservaEquipamento;
import view.horariosReservas.HorariosReservaPatrimonio;
import control.EquipmentRegister;
=======
import model.Equipamento;
import view.timeReservations.HoursEquipamentReservation;
import view.timeReservations.HoursPatrimonyReservation;
import control.ManterEquipamento;
>>>>>>> devel
import exception.PatrimonyException;

/**
 * 
 * @author Parley
 */
public class DiaReservaEquipamento extends PatrimonyReserveDay {

    Equipment eq;

    public DiaReservaEquipamento(Frame parent, boolean modal, int indexEquipamento) throws SQLException, PatrimonyException {
        super(parent, modal);
        eq = EquipmentRegister.getNewEquipment().getVectorEquipments().get(indexEquipamento);
    }

    @Override protected void viewAction(String data) {
    	HoursPatrimonyReservation reserva = new HoursEquipamentReservation(new JFrame(), true, data, this.eq);
        reserva.setVisible(true);
        reserva.setResizable(false);
    }
}
