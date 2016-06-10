/**
 * Name:ChangeStudent.java
 * Class for change information of a student already registered.  
 */

package view.alteracoes;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.cadastros.ClientRegistration;
import control.ManterAluno;
import exception.ClienteException;

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
			this.nameTxtField.setText(ManterAluno.getInstance().getAluno_vet().get(index).getNome());
			this.emailTxtField.setText(ManterAluno.getInstance().getAluno_vet().get(index).getEmail());
			this.phoneTxtField.setText(ManterAluno.getInstance().getAluno_vet().get(index).getTelefone());
			this.enrollmentTxtField.setText(ManterAluno.getInstance().getAluno_vet().get(index).getMatricula());
			this.cpfTxtField.setText(ManterAluno.getInstance().getAluno_vet().get(index).getCpf());

		} catch (ClienteException ex) {
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
			ManterAluno.getInstance().alterar(nameTxtField.getText(), cpfTxtField.getText(),
					enrollmentTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(),
					ManterAluno.getInstance().getAluno_vet().get(index2));

			JOptionPane.showMessageDialog(this, "Aluno alterado com sucesso", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE, null);
			this.setVisible(false);
		} catch (ClienteException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
	}
}
