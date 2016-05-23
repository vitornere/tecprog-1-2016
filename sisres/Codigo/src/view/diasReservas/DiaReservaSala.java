/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasReservas;

import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.Classroom;
import view.timeReservations.HoursRoomReservation;
import control.ManterSala;
import exception.PatrimonyException;

/**
 * 
 * @author Parley
 */
public class DiaReservaSala extends PatrimonyReserveDay {

    Classroom sala;

    public DiaReservaSala(Frame parent, boolean modal, int indexSala) throws SQLException, PatrimonyException {
        super(parent, modal);
        sala = ManterSala.getInstance().getSalas_vet().get(indexSala);
        this.setName("DiaReservaSala");
    }

    @Override protected void viewAction(String data) {
        HoursRoomReservation reserva = new HoursRoomReservation(new JFrame(), true, data, sala);
        reserva.setVisible(true);
        reserva.setResizable(false);
    }

}
