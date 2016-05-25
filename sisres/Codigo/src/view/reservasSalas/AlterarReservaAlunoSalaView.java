/**
 * This class is a screen of to change student's reserve generated by swing
 */
package view.reservasSalas;

import java.awt.Color;
import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.ReservaSalaAluno;
import model.ReservaSalaProfessor;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;

public class AlterarReservaAlunoSalaView extends ReservaSalaView {

    int index; // Index of reserve
    ReservaSalaAluno reservaAluno; // Object of student's reserve
    ReservaSalaProfessor reservaProfessor; // Object of teacher's reserve

    /**
     * Clean all components
     */
    private void resetComponents() {
        this.reservarButton.setText("Alterar");
        this.reservarButton.setName("AlterarButton");
        this.alunoRadioButton.setSelected(true);
        this.cpfLabel.setEnabled(false);
        alunoRadioButtonAction();
    }

    /**
     * 
     * @param parent
     * @param modal
     * @param index
     * @param data
     * @throws SQLException
     * @throws PatrimonyException
     * @throws PatrimonyException
     * @throws ClienteException
     * @throws ReservaException
     */
    public AlterarReservaAlunoSalaView(Frame parent, boolean modal, int index, String data) throws SQLException,
            PatrimonyException, PatrimonyException, ClienteException, ReservaException {
        super(parent, modal);
        this.setName("AlterarReservaSalaView");
        this.reservaAluno = instanceAluno.getReservasMes(data).get(index);
        resetComponents();

    }

    /**
     * @exception Reserve is null or Patrimony is null or Client is null Database is null or NullPointer is null
     *
     * (non-Javadoc)
     * @see view.reservasSalas.ReservaSalaView#reservarAluno()
     */
    @Override protected void reservarAluno() {
        try {
            instanceAluno.alterar(this.finalidadeTextField.getText(), this.qntCadeirasReservadasTextField.getText(), reservaAluno);

            JOptionPane.showMessageDialog(this, "Reserva alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

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

    /*
     * (non-Javadoc)
     * @see view.reservasSalas.ReservaSalaView#reservarProfessor()
     */
    @Override protected void reservarProfessor() {

    }

    /*
     * (non-Javadoc)
     * @see view.reservasSalas.ReservaSalaView#professorRadioButtonAction()
     */
    @Override protected void professorRadioButtonAction() {
    }

    /*
     * (non-Javadoc)
     * @see view.reservasSalas.ReservaSalaView#alunoRadioButtonAction()
     */
    @Override protected void alunoRadioButtonAction() {
        this.instanceProf = null;
        this.professorRadioButton.setEnabled(false);
        this.cpfTextField.setBackground(new Color(200, 208, 254));
        this.cpfTextField.setEditable(false);
        this.qntCadeirasReservadasTextField.setEditable(true);
        this.qntCadeirasReservadasTextField.setBackground(Color.white);
        this.horaTextField.setBackground(new Color(200, 208, 254));
        this.horaTextField.setEditable(false);
        this.horaTextField.setText(reservaAluno.getHora());
        this.alunoTextArea.setText(reservaAluno.getAluno().toString());
        this.salaTextArea.setText(reservaAluno.getSala().toString());
        this.dataTextField.setText(reservaAluno.getData());
        this.qntCadeirasTxtField.setText(reservaAluno.getSala().getCapacity());
        this.qntCadeirasReservadasTextField.setText(reservaAluno.getCadeiras_reservadas());
        this.finalidadeTextField.setText(reservaAluno.getFinalidade());
    }

    /**
     * @exception Patrimony is null or Client is null or NullPointer is null
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
