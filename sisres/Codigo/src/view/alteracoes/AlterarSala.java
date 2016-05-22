/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.CadastroPatrimonio;
import control.ClassroomRegister;
import exception.PatrimonyException;

/**
 * 
 * @author Parley
 */
public class AlterarSala extends CadastroPatrimonio {

    private int index2 = 0;

    public AlterarSala(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        this.setTitle("Alterar");
        this.setName("AlterarSala");
        this.cadastroBtn.setText("Alterar");
        this.cadastroBtn.setName("Alterar");
        index2 = index;

        try {

            this.codigoTxtField.setText(ClassroomRegister.getClassroom().getVectorClassroom().get(index).getIdEquipment());
            this.capacidadeTxtField.setText(ClassroomRegister.getClassroom().getVectorClassroom().get(index).getCapacity());
            this.descricaoTextArea.setText(ClassroomRegister.getClassroom().getVectorClassroom().get(index).getDescriptionEquipment());
            this.index2 = index;

        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }

    }

    @Override protected void cadastroAction() {
        try {

            ClassroomRegister.getClassroom().update(codigoTxtField.getText(), descricaoTextArea.getText(), capacidadeTxtField.getText(),
                    ClassroomRegister.getClassroom().getVectorClassroom().get(index2));

            JOptionPane.showMessageDialog(this, "Sala Alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);

        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
