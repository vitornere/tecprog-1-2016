/**
 * Name:StudentRegistration.java
 * Class is a sub class for adding a new student.
 */

package view.cadastros;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.StudentRegister;
import exception.ClientException;

public class StudentRegistration extends ClientRegistration {

	/**
	 * Method to generate the option screen to add student.
	 * 
	 * @param parent
	 * @param modal
	 */
	
	public StudentRegistration(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		this.setName("CadastroAluno");
	}

	/**
	 * Method to generate the screen to add information from a new student and advises that 
	 * was successfully changed
	 */
	
	public void registrationAction() {
		try {
			if (btnRegistration.getText().equals("Cadastrar")) {
				StudentRegister.getInstance().insert(nameTxtField.getText(), cpfTxtField.getText(),
						enrollmentTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText());

				JOptionPane.showMessageDialog(this, "Aluno Cadastrado com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE, null);

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
