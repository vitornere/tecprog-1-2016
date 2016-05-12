/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservasEquipamentos;

import java.awt.Color;
import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.ReservaEquipamentoProfessor;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

/**
 * 
 * @author Parley  

 */
public class ChangeReservationEquipamentView extends ReservationEquipmentView {

    int index;
    ReservaEquipamentoProfessor reservation;

    private void resetComponents() {
        this.reservationButton.setText("Alterar");
        this.reservationButton.setName("AlterarButton");
        this.cpfLabel.setEnabled(false);
        this.cpfTextField.setBackground(new Color(200, 208, 254));
        this.cpfTextField.setEditable(false);
        this.timeTextField.setText(reservation.getHora());
        this.dateTextField.setText(reservation.getData());
        this.teacherTextArea.setText(reservation.getProfessor().toString());
    }

    public ChangeReservationEquipamentView(Frame parent, boolean modal, int index, int mes) throws SQLException, PatrimonioException,
            PatrimonioException, ClienteException, ReservaException {
        super(parent, modal);
        this.index = index;
        reservation = this.instanceProf.getReservasMes(mes).get(index);
        resetComponents();
    }

    @Override protected void reservationTeacher() {
        try {

            instanceProf.alterar(null, reservation);
            JOptionPane.showMessageDialog(this, "Reserva alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
        } catch (ReservaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
