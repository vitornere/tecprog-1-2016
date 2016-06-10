/**
 * Name:ClassroomRegistration.java
 * Class is a sub class for adding a new classroom.
 */

package view.cadastros;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.ManterSala;
import exception.PatrimonyException;

public class ClassroomRegistration extends PatrimonyRegistration {

	/**
	 * Method to generate the option screen to add classroom.
	 * 
	 * @param parent
	 * @param modal
	 */
	public ClassroomRegistration(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		this.setName("CadastroSala");
	}

	/**
	 * Method to generate the screen to add information from a new classroom and advises that 
	 * was successfully changed
	 * 
	 */
	protected void registrationAction() {
		try {

			ManterSala.getInstance().inserir(codeTxtField.getText(), discriptionTextArea.getText(),
					capacityTxtField.getText());

			JOptionPane.showMessageDialog(this, "Sala Cadastrada com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE, null);
			this.setVisible(false);

		} catch (PatrimonyException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getSQLState() + "\n" + ex.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE, null);
		}

	}
}
