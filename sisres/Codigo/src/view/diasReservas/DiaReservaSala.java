/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasReservas;

import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JFrame;

import model.Sala;
import view.horariosReservas.HorariosReservaSala;
import control.ManterSala;
import exception.PatrimonioException;

/**
 * 
 * @author Parley
 */
public class DiaReservaSala extends PatrimonyReserveDay {

    Sala sala;

    public DiaReservaSala(Frame parent, boolean modal, int indexSala) throws SQLException, PatrimonioException {
        super(parent, modal);
        sala = ManterSala.getInstance().getSalas_vet().get(indexSala);
        this.setName("DiaReservaSala");
    }

    @Override protected void viewAction(String data) {
        HorariosReservaSala reserva = new HorariosReservaSala(new JFrame(), true, data, sala);
        reserva.setVisible(true);
        reserva.setResizable(false);
    }

}
