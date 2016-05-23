/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastros;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.ManterAluno;
import exception.ClienteException;

/**
 * 
 * @author Parley
 */
public class StudentRegistration extends ClientRegistration {

    public StudentRegistration(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setName("CadastroAluno");
    }

    @Override public void registrationAction() {
        try {
            if (btnRegistration.getText().equals("Cadastrar")) {
                ManterAluno.getInstance().inserir(nameTxtField.getText(), cpfTxtField.getText(), enrollmentTxtField.getText(),
                        phoneTxtField.getText(), emailTxtField.getText());

                JOptionPane.showMessageDialog(this, "Aluno Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                        null);

                this.setVisible(false);
            }
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
