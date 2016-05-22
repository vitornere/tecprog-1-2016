/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastros;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.ProfessorRegister;
import exception.ClientException;

/**
 * 
 * @author Parley
 */
public class CadastroProfessor extends CadastroCliente {

    public CadastroProfessor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setName("CadastroProfessor");

    }

    @Override public void cadastroAction() {
        try {
            if (cadastroBtn.getText().equals("Cadastrar")) {
                // TODO add your handling code here:
                ProfessorRegister.getNewProfessor().insert(nomeTxtField.getText(), cpfTxtField.getText(), matriculaTxtField.getText(),
                        telefoneTxtField.getText(), emailTxtField.getText());

                JOptionPane.showMessageDialog(this, "Professor Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                        null);

                this.setVisible(false);
            }
        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
