/**
 * Name:ChangeStudent.java
 * Class for change information of a student already registered.  
 */

package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.ClientRegistration;
import control.StudentRegister;
import exception.ClientException;

public class ChangeStudent extends ClientRegistration {

	int index2 = 0;

	/**
	 * Method to generate the option screen to change student.
	 * 
	 * @param parent
	 * @param modal
	 * @param index
	 */
	
	public ChangeStudent(java.awt.Frame parent, boolean modal, int index) {
		super(parent, modal);
		this.setTitle("Alterar");
		this.setName("AlterarAluno");
		this.btnRegistration.setText("Alterar");
		this.btnRegistration.setName("Alterar");
		this.index2 = index;

		try {
			this.nameTxtField.setText(StudentRegister.getInstance().getVectorStudents().get(index).getNamePerson());
			this.emailTxtField.setText(StudentRegister.getInstance().getVectorStudents().get(index).getEmailPerson());
			this.phoneTxtField.setText(StudentRegister.getInstance().getVectorStudents().get(index).getPhonePerson());
			this.enrollmentTxtField.setText(StudentRegister.getInstance().getVectorStudents().get(index).getIdRegister());
			this.cpfTxtField.setText(StudentRegister.getInstance().getVectorStudents().get(index).getCpfPerson());

		} catch (ClientException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	/**
	 * Method to generate the screen to change information from student and advises that 
	 * was successfully changed
	 */

	public void registrationAction() {
		try {
			StudentRegister.getInstance().update(nameTxtField.getText(), cpfTxtField.getText(),
					enrollmentTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(),
					StudentRegister.getInstance().getVectorStudents().get(index2));

			JOptionPane.showMessageDialog(this, "Aluno alterado com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE, null);
			this.setVisible(false);
		} catch (ClientException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}
}
