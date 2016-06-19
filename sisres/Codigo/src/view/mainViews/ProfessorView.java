/**
 * This class is screen of professor generated by swing
 */
package view.mainViews;

import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import view.alteracoes.ChangeProfessor;
import view.cadastros.ClientRegistration;
import view.cadastros.ProfessorRegistration;
import control.ProfessorRegister;
import exception.ClientException;

public class ProfessorView extends ClientView {

	/**
	 * 
	 * @param parent
	 * @param modal
	 */
	public ProfessorView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setName("ProfessorView");
    }

	/**
	 * Gets value of quantity of professors
	 * 
	 * @exception Client null or Database null
	 */
    public Iterator getIterator() {
        try {
            return ProfessorRegister.getInstance().getVectorProfessors().iterator();

        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see view.mainViews.ClientView#registerAction()
     */
    @Override public void registerAction() {

        ClientRegistration cadastrar = new ProfessorRegistration(new javax.swing.JFrame(), true);
        cadastrar.setResizable(false);
        cadastrar.setVisible(true);
        clientTable.setModel(fillTable());

    }

    /*
     * (non-Javadoc)
     * @see view.mainViews.ClientView#alterarAction(int)
     */
    @Override public void alterarAction(int index) {

        ChangeProfessor alterar = new ChangeProfessor(new javax.swing.JFrame(), true, index);
        alterar.setResizable(false);
        alterar.setVisible(true);
        this.clientTable.setModel(fillTable());
    }

    /**
	 * @exception Client null or Database null
	 *
     * (non-Javadoc)
     * @see view.mainViews.ClientView#excluirAction()
     */
    @Override public void excluirAction() {
        try {
            int index = this.clientTable.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir Professor: "
                    + ProfessorRegister.getInstance().getVectorProfessors().get(index).getNamePerson() + "?", "Excluir",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ProfessorRegister.getInstance().delete(ProfessorRegister.getInstance().getVectorProfessors().get(index));
                JOptionPane.showMessageDialog(this, "Professor excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                        null);
            }
            this.clientTable.setModel(fillTable());

        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}