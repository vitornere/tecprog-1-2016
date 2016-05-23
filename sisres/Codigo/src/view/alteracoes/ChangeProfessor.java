/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.ClientRegistration;
import control.ManterProfessor;
import exception.ClienteException;

/**
 * 
 * @author Parley
 */
public class ChangeProfessor extends ClientRegistration {

    int index2 = 0;

    public ChangeProfessor(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        this.setName("AlterarProfessor");
        this.btnRegistration.setText("Alterar");
        this.btnRegistration.setName("Alterar");
        this.index2 = index;

        try {
            this.nameTxtField.setText(ManterProfessor.getInstance().getProfessores_vet().get(index).getNome());
            this.emailTxtField.setText(ManterProfessor.getInstance().getProfessores_vet().get(index).getEmail());
            this.phoneTxtField.setText(ManterProfessor.getInstance().getProfessores_vet().get(index).getTelefone());
            this.enrollmentTxtField.setText(ManterProfessor.getInstance().getProfessores_vet().get(index).getMatricula());
            this.cpfTxtField.setText(ManterProfessor.getInstance().getProfessores_vet().get(index).getCpf());

        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override public void registrationAction() {
        try {
            ManterProfessor.getInstance().alterar(nameTxtField.getText(), cpfTxtField.getText(), enrollmentTxtField.getText(),
                    phoneTxtField.getText(), emailTxtField.getText(),
                    ManterProfessor.getInstance().getProfessores_vet().get(index2));

            JOptionPane.showMessageDialog(this, "Cadastro alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
