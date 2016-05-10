/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasReservas;

import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.Sala;
import view.timeReservations.HoursRoomReservation;
import control.ManterSala;
import exception.PatrimonioException;

/**
 * 
 * @author Parley
 */
public class ClassRoomReserveDay extends PatrimonyReserveDay {

    Sala classRoom;

    public ClassRoomReserveDay(Frame parent, boolean modal, int indexSala) throws SQLException, PatrimonioException {
        super(parent, modal);
        
        classRoom = ManterSala.getInstance().getSalas_vet().get(indexSala);
        
        this.setName("DiaReservaSala");
    }

    @Override protected void viewAction(String data) {
        HoursRoomReservation reserva = new HoursRoomReservation(new JFrame(), true, data, classRoom);
        
        reserva.setVisible(true);
        
        reserva.setResizable(false);
    }

}
