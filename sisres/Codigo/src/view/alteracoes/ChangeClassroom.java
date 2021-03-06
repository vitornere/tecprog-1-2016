/**
 * Name:ChangeClassroom.java
 * Class for change information of a classroom already registered.  
 */

package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.PatrimonyRegistration;
import control.ClassroomRegister;
import exception.PatrimonyException;

public class ChangeClassroom extends PatrimonyRegistration {

	private int index2 = 0;
	
	/**
	 * Method to generate the option screen to change classroom.
	 * 
	 * @param parent
	 * @param modal
	 * @param index
	 */
	
	public ChangeClassroom(java.awt.Frame parent, boolean modal, int index) {
		super(parent, modal);
		this.setTitle("Alterar");
		this.setName("AlterarSala");
		this.btnRegistration.setText("Alterar");
		this.btnRegistration.setName("Alterar");
		index2 = index;

		try {

			this.codeTxtField.setText(ClassroomRegister.getInstance().getVectorClassroom().get(index).getIdEquipment());
			this.capacityTxtField.setText(ClassroomRegister.getInstance().getVectorClassroom().get(index).getCapacity());
			this.discriptionTextArea.setText(ClassroomRegister.getInstance().getVectorClassroom().get(index).getDescriptionEquipment());
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
	 * Method to generate the screen to change information from classroom and advises that 
	 * was successfully changed
	 */
	
	protected void registrationAction() {
		try {

			ClassroomRegister.getInstance().update(codeTxtField.getText(), discriptionTextArea.getText(),
					capacityTxtField.getText(), ClassroomRegister.getInstance().getVectorClassroom().get(index2));

			JOptionPane.showMessageDialog(this, "Sala Alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
					null);
			this.setVisible(false);

		} catch (PatrimonyException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}

}
