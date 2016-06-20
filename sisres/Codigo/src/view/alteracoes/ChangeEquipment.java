/**
 * Name:ChangeEquipment.java
 * Class for change information of a equipment already registered.  
 */

package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.PatrimonyRegistration;
import control.EquipmentRegister;
import exception.PatrimonyException;

public class ChangeEquipment extends PatrimonyRegistration {

	private int index2 = 0;

	/**
	 * Method to generate the option screen to change equipment.
	 * 
	 * @param parent
	 * @param modal
	 * @param index
	 */
	public ChangeEquipment(java.awt.Frame parent, boolean modal, int index) {
		super(parent, modal);
		this.setTitle("Alterar");
		this.setName("AlterarEquipamento");
		this.btnRegistration.setText("Alterar");
		this.btnRegistration.setName("Alterar");
		this.lblCapacity.setVisible(false);
		this.capacityTxtField.setVisible(false);
		index2 = index;

		try {

			this.codeTxtField.setText(EquipmentRegister.getNewEquipment().getVectorEquipments().get(index).getIdEquipment());
			this.discriptionTextArea.setText(EquipmentRegister.getNewEquipment().getVectorEquipments().get(index).getDescriptionEquipment());
			this.index2 = index;

		} catch (PatrimonyException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}

	}

	/**
	 * Method to generate the screen to change information from equipment and advises that 
	 * was successfully changed
	 */
	
	protected void registrationAction() {
		try {

			EquipmentRegister.getNewEquipment().update(codeTxtField.getText(), discriptionTextArea.getText(),
					EquipmentRegister.getNewEquipment().getVectorEquipments().get(index2));

			JOptionPane.showMessageDialog(this, "Equipamento alterado com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE, null);
			this.setVisible(false);

		} catch (PatrimonyException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}
}
