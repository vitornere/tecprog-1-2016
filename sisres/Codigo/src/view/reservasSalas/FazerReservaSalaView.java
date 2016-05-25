/**
 * This class is a screen of to make reserve generated by swing
 */
package view.reservasSalas;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Classroom;
import control.ManterResSalaAluno;
import control.ManterResSalaProfessor;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;

public class FazerReservaSalaView extends ReservaSalaView {

	/**
	 * 
	 * @param parent
	 * @param modal
	 * @param sala
	 * @param data
	 * @throws SQLException
	 * @throws PatrimonyException
	 * @throws PatrimonyException
	 * @throws ClienteException
	 * @throws ReservaException
	 */
	public FazerReservaSalaView(Frame parent, boolean modal, Classroom sala, String data) throws SQLException, PatrimonyException,
            PatrimonyException, ClienteException, ReservaException {
        super(parent, modal);
        this.sala = sala;
        this.dataTextField.setText(data);
        this.salaTextArea.setText(sala.toString());
        this.qntCadeirasTxtField.setText(sala.getCapacity());
        this.setName("FazerReservaSalaView");
        
    }

	/**
	 *@exception Reserve is null or Patrimony is null or Client is null or Database is null or NullPointer is null 
	 *
	 * (non-Javadoc)
	 * @see view.reservasSalas.ReservaSalaView#reservarAluno()
	 */
    @Override protected void reservarAluno() {
        try {
            instanceAluno.inserir(sala, aluno, this.dataTextField.getText(), this.horaTextField.getText(),
                    this.finalidadeTextField.getText(), this.qntCadeirasReservadasTextField.getText());

            instanceAluno.getResAlunoSala_vet();
            // System.out.println(v.toString());

            JOptionPane.showMessageDialog(this, "Reserva feita com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

            this.setVisible(false);
        } catch (ReservaException ex) {
            
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClienteException ex) {
            
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    /**
     * @exception Reserve is null or Database is null 
     *
     * (non-Javadoc)
     * @see view.reservasSalas.ReservaSalaView#reservarProfessor()
     */
    @Override protected void reservarProfessor() {
        try {

            instanceProf.inserir(sala, prof, this.dataTextField.getText(), this.horaTextField.getText(),
                    this.finalidadeTextField.getText());

            JOptionPane.showMessageDialog(this, "Reserva feita com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

            this.setVisible(false);
        } catch (ReservaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    /*
     * (non-Javadoc)
     * @see view.reservasSalas.ReservaSalaView#professorRadioButtonAction()
     */
    @Override protected void professorRadioButtonAction() {
        this.alunoLabel.setText(this.professorRadioButton.getText() + ": ");
        this.alunoTextArea.setText("");
        this.cpfTextField.setText("");
        this.qntCadeirasReservadasTextField.setEditable(false);
        this.qntCadeirasReservadasTextField.setBackground(new Color(200, 208, 254));
        this.qntCadeirasReservadasTextField.setText(this.qntCadeirasTxtField.getText());
        this.instanceProf = ManterResSalaProfessor.getInstance();
        this.instanceAluno = null;
        this.verificarCadeiraButton.setEnabled(false);
        
    }

    /*
     * (non-Javadoc)
     * @see view.reservasSalas.ReservaSalaView#alunoRadioButtonAction()
     */
    @Override protected void alunoRadioButtonAction() {
        this.instanceAluno = ManterResSalaAluno.getInstance();
        this.alunoLabel.setText(this.alunoRadioButton.getText() + ": ");
        this.alunoTextArea.setText("");
        this.cpfTextField.setText("");
        this.qntCadeirasReservadasTextField.setEnabled(true);
        this.qntCadeirasReservadasTextField.setEditable(true);
        this.qntCadeirasReservadasTextField.setBackground(Color.white);
        this.instanceProf = null;
        this.verificarCadeiraButton.setEnabled(true);
    }

    /**
     * @exception Reserve is null or Patrimony is null or Client is null or Database is null or NullPointer is null 
     *
     * (non-Javadoc)
     * @see view.reservasSalas.ReservaSalaView#verificarAction()
     */
    @Override protected void verificarAction() {
        try {
            this.qntCadeirasTxtField.setText(String.valueOf(instanceAluno.cadeirasDisponveis(sala, this.dataTextField.getText(),
                    this.horaTextField.getText())));
        } catch (ReservaException ex) {
            
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClienteException ex) {
            
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
