/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainViews;

import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import view.alteracoes.ChangeStudent;
import view.cadastros.StudentRegistration;
import view.cadastros.ClientRegistration;
import control.ManterAluno;
import exception.ClienteException;

/**
 * 
 * @author Parley
 */
public class StudentView extends ClientView {

    public StudentView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setName("AlunoView");
    }

    public Iterator getIterator() {
        try {
            return ManterAluno.getInstance().getAluno_vet().iterator();

        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        return null;
    }

    @Override public void registerAction() {

		ClientRegistration studentRegister = new StudentRegistration(new javax.swing.JFrame(), true);
        studentRegister.setResizable(false);
        studentRegister.setVisible(true);
        clientTable.setModel(fillTable());
    }

    @Override public void alterarAction(int index) {

		ChangeStudent studentChange = new ChangeStudent(new javax.swing.JFrame(), true, index);
        studentChange.setResizable(false);
        studentChange.setVisible(true);
        this.clientTable.setModel(fillTable());
    }

    @Override public void excluirAction() {
        try {
            int index = this.clientTable.getSelectedRow();
            
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            else {
            	// Nothing to do
            }

            int confirmExclusion = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir Aluno: " + 
            					   ManterAluno.getInstance().getAluno_vet().get(index).getNome() + 
            					   "?", "Excluir", JOptionPane.YES_NO_OPTION);
            
            if (confirmExclusion == JOptionPane.YES_OPTION) {
                ManterAluno.getInstance().excluir(ManterAluno.getInstance().getAluno_vet().get(index));
                
                JOptionPane.showMessageDialog(this, "Aluno excluido com sucesso", "Sucesso", 
                							  JOptionPane.INFORMATION_MESSAGE, null);
            }
            else {
            	// Nothing to do
            }
            
            this.clientTable.setModel(fillTable());
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

}
