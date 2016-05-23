/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.PatrimonyRegistration;
import control.ManterSala;
import exception.PatrimonyException;

/**
 * 
 * @author Parley
 */
public class ChangeClassroom extends PatrimonyRegistration {

    private int index2 = 0;

    public ChangeClassroom(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        this.setTitle("Alterar");
        this.setName("AlterarSala");
        this.btnRegistration.setText("Alterar");
        this.btnRegistration.setName("Alterar");
        index2 = index;

        try {

            this.codeTxtField.setText(ManterSala.getInstance().getSalas_vet().get(index).getCode());
            this.capacityTxtField.setText(ManterSala.getInstance().getSalas_vet().get(index).getCapacity());
            this.discriptionTextArea.setText(ManterSala.getInstance().getSalas_vet().get(index).getDescription());
            this.index2 = index;

        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }

    }

    @Override protected void registrationAction() {
        try {

            ManterSala.getInstance().alterar(codeTxtField.getText(), discriptionTextArea.getText(), capacityTxtField.getText(),
                    ManterSala.getInstance().getSalas_vet().get(index2));

            JOptionPane.showMessageDialog(this, "Sala Alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);

        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
