/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasReservas;

import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.Equipment;
import view.timeReservations.HoursEquipamentReservation;
import view.timeReservations.HoursPatrimonyReservation;
import control.EquipmentRegister;
import exception.PatrimonyException;

/**
 * 
 * @author Parley
 */
public class EquipmentReserveDay extends PatrimonyReserveDay {

    Equipment equipment;

    public EquipmentReserveDay(Frame parent, boolean modal, int indexEquipment) throws SQLException, PatrimonyException {
        super(parent, modal);
        
        equipment = EquipmentRegister.getNewEquipment().getVectorEquipments().get(indexEquipment);
    }

    @Override protected void viewAction(String data) {
        HoursPatrimonyReservation booking = new HoursEquipamentReservation(new JFrame(), true, data, this.equipment);
        
        booking.setVisible(true);
        
        booking.setResizable(false);
    }
}
