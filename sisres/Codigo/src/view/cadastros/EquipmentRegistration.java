/** 
 * Name:EquipmentRegistration.java
 * Class is a sub class for adding a new equipment.
 */

package view.cadastros;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.EquipmentRegister;
import exception.PatrimonyException;

public class EquipmentRegistration extends PatrimonyRegistration {

	/**
	 * Method to generate the option screen to add equipment.
	 * 
	 * @param parent
	 * @param modal
	 */
	
	public EquipmentRegistration(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		this.setName("CadastroEquipamento");
		this.lblCapacity.setVisible(false);
		this.capacityTxtField.setVisible(false);
	}

	/**
	 * Method to generate the screen to add information from a new equipment and advises that 
	 * was successfully changed
	 */
	
	protected void registrationAction() {

		try {
			EquipmentRegister.getNewEquipment().insert(codeTxtField.getText(), discriptionTextArea.getText());
			JOptionPane.showMessageDialog(this, "Equipamento Cadastrado com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE, null);
			this.setVisible(false);

		} catch (PatrimonyException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}

	}
}
