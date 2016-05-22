/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.CadastroCliente;
import control.ProfessorRegister;
import exception.ClientException;

/**
 * 
 * @author Parley
 */
public class AlterarProfessor extends CadastroCliente {

    int index2 = 0;

    public AlterarProfessor(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        this.setName("AlterarProfessor");
        this.cadastroBtn.setText("Alterar");
        this.cadastroBtn.setName("Alterar");
        this.index2 = index;

        try {
            this.nomeTxtField.setText(ProfessorRegister.getNewProfessor().getVectorProfessors().get(index).getNamePerson());
            this.emailTxtField.setText(ProfessorRegister.getNewProfessor().getVectorProfessors().get(index).getEmailPerson());
            this.telefoneTxtField.setText(ProfessorRegister.getNewProfessor().getVectorProfessors().get(index).getPhonePerson());
            this.matriculaTxtField.setText(ProfessorRegister.getNewProfessor().getVectorProfessors().get(index).getIdRegister());
            this.cpfTxtField.setText(ProfessorRegister.getNewProfessor().getVectorProfessors().get(index).getCpfPerson());

        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override public void cadastroAction() {
        try {
            ProfessorRegister.getNewProfessor().update(nomeTxtField.getText(), cpfTxtField.getText(), matriculaTxtField.getText(),
                    telefoneTxtField.getText(), emailTxtField.getText(),
                    ProfessorRegister.getNewProfessor().getVectorProfessors().get(index2));

            JOptionPane.showMessageDialog(this, "Cadastro alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
