/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.CadastroCliente;
import control.StudentRegister;
import exception.ClientException;

/**
 * 
 * @author Parley
 */
public class AlterarAluno extends CadastroCliente {

    int index2 = 0;

    public AlterarAluno(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        this.setTitle("Alterar");
        this.setName("AlterarAluno");
        this.cadastroBtn.setText("Alterar");
        this.cadastroBtn.setName("Alterar");
        this.index2 = index;

        try {
            this.nomeTxtField.setText(StudentRegister.getNewStudent().getVectorStudents().get(index).getNamePerson());
            this.emailTxtField.setText(StudentRegister.getNewStudent().getVectorStudents().get(index).getEmailPerson());
            this.telefoneTxtField.setText(StudentRegister.getNewStudent().getVectorStudents().get(index).getPhonePerson());
            this.matriculaTxtField.setText(StudentRegister.getNewStudent().getVectorStudents().get(index).getIdRegister());
            this.cpfTxtField.setText(StudentRegister.getNewStudent().getVectorStudents().get(index).getCpfPerson());

        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override public void cadastroAction() {
        try {
            StudentRegister.getNewStudent().update(nomeTxtField.getText(), cpfTxtField.getText(), matriculaTxtField.getText(),
                    telefoneTxtField.getText(), emailTxtField.getText(), StudentRegister.getNewStudent().getVectorStudents().get(index2));

            JOptionPane.showMessageDialog(this, "Aluno alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);
        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
