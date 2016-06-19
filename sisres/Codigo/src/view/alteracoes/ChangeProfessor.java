/**
 * Name:ChangeProfessor.java
 * Class for change information of a professor already registered.  
 */
package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.ClientRegistration;
import control.ProfessorRegister;
import exception.ClientException;

public class ChangeProfessor extends ClientRegistration {

	int index2 = 0;

	/**
	 * Method to generate the option screen to change professor.
	 * 
	 * @param parent
	 * @param modal
	 * @param index
	 */
	
	public ChangeProfessor(java.awt.Frame parent, boolean modal, int index) {
		super(parent, modal);
		this.setName("AlterarProfessor");
		this.btnRegistration.setText("Alterar");
		this.btnRegistration.setName("Alterar");
		this.index2 = index;

		try {
			this.nameTxtField.setText(ProfessorRegister.getInstance().getVectorProfessors().get(index).getNamePerson());
			this.emailTxtField.setText(ProfessorRegister.getInstance().getVectorProfessors().get(index).getEmailPerson());
			this.phoneTxtField.setText(ProfessorRegister.getInstance().getVectorProfessors().get(index).getPhonePerson());
			this.enrollmentTxtField.setText(ProfessorRegister.getInstance().getVectorProfessors().get(index).getIdRegister());
			this.cpfTxtField.setText(ProfessorRegister.getInstance().getVectorProfessors().get(index).getCpfPerson());

		} catch (ClientException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}

	/**
	 * Method to generate the screen to change information from professor and advises that 
	 * was successfully changed
	 */
	
	public void registrationAction() {
		try {
			ProfessorRegister.getInstance().change(nameTxtField.getText(), cpfTxtField.getText(),
					enrollmentTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(),
					ProfessorRegister.getInstance().getVectorProfessors().get(index2));

			JOptionPane.showMessageDialog(this, "Cadastro alterado com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE, null);
			this.setVisible(false);
		} catch (ClientException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}
}
