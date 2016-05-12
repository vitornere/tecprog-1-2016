/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservasEquipamentos;

import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Equipamento;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

/**
 * 
 * @author Parley
 */
public class MakeReservationEquipmentsView extends ReservationEquipmentView {

    Equipamento equipamento;

    public MakeReservationEquipmentsView(Frame parent, boolean modal, Equipamento e, String data) throws SQLException,
            PatrimonioException, PatrimonioException, ClienteException, ReservaException {
        super(parent, modal);
        this.equipamento = e;
        this.dateTextField.setText(data);
        this.equipmentTextArea.setText(e.toString());
    }

    @Override protected void reservationTeacher() {
        try {

            instanceProf.inserir(equipamento, prof, this.dateTextField.getText(), this.timeTextField.getText());

            JOptionPane.showMessageDialog(this, "Reserva feita com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

            this.setVisible(false);
        } catch (ReservaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
