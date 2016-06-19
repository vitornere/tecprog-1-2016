/**
 * Name:ProfessorRegistration.java
 * Class is a sub class for adding a new professor.
 */

package view.cadastros;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.ProfessorRegister;
import exception.ClientException;

public class ProfessorRegistration extends ClientRegistration {

	/**
	 * Method to generate the option screen to add professor.
	 * 
	 * @param parent
	 * @param modal
	 */
	
	public ProfessorRegistration(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		this.setName("CadastroProfessor");

	}

	/**
	 * Method to generate the screen to add information from a new professor and advises that 
	 * was successfully changed
	 */
	
	public void registrationAction() {
		try {
			if (btnRegistration.getText().equals("Cadastrar")) {

				ProfessorRegister.getInstance().insert(nameTxtField.getText(), cpfTxtField.getText(),
						enrollmentTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText());

				JOptionPane.showMessageDialog(this, "Professor Cadastrado com sucesso", "Sucesso",
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
