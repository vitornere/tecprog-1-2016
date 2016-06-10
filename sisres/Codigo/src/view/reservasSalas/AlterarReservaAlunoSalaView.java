/**
 * This class is a screen of to change student's reserve generated by swing
 */
package view.reservasSalas;

import java.awt.Color;
import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JOptionPane;

<<<<<<< HEAD
import model.ReserveClassroomForStudent;
import model.ReserveClassroomForProfessor;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;
=======
import model.ReservaSalaAluno;
import model.ReservaSalaProfessor;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;
>>>>>>> devel

public class AlterarReservaAlunoSalaView extends ReservaSalaView {

<<<<<<< HEAD
    int index;
    ReserveClassroomForStudent reservaAluno;
    ReserveClassroomForProfessor reservaProfessor;
=======
    int index; // Index of reserve
    ReservaSalaAluno reservaAluno; // Object of student's reserve
    ReservaSalaProfessor reservaProfessor; // Object of teacher's reserve
>>>>>>> devel

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
<<<<<<< HEAD
            PatrimonyException, PatrimonyException, ClientException, ReserveException {
=======
            PatrimonyException, PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
        super(parent, modal);
        this.setName("AlterarReservaSalaView");
        this.reservaAluno = instanceAluno.getMonthReservations(data).get(index);
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
            instanceAluno.update(this.finalidadeTextField.getText(), this.qntCadeirasReservadasTextField.getText(), reservaAluno);

            JOptionPane.showMessageDialog(this, "Reserva alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

            this.setVisible(false);
        } catch (ReserveException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClientException ex) {
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
<<<<<<< HEAD
        this.horaTextField.setText(reservaAluno.getHour());
        this.alunoTextArea.setText(reservaAluno.getStudent().toString());
        this.salaTextArea.setText(reservaAluno.getClassroom().toString());
        this.dataTextField.setText(reservaAluno.getDate());
        this.qntCadeirasTxtField.setText(reservaAluno.getClassroom().getCapacity());
        this.qntCadeirasReservadasTextField.setText(reservaAluno.getReservedChairs());
        this.finalidadeTextField.setText(reservaAluno.getFinality());
=======
        this.horaTextField.setText(reservaAluno.getHora());
        this.alunoTextArea.setText(reservaAluno.getAluno().toString());
        this.salaTextArea.setText(reservaAluno.getSala().toString());
        this.dataTextField.setText(reservaAluno.getData());
        this.qntCadeirasTxtField.setText(reservaAluno.getSala().getCapacity());
        this.qntCadeirasReservadasTextField.setText(reservaAluno.getCadeiras_reservadas());
        this.finalidadeTextField.setText(reservaAluno.getFinalidade());
>>>>>>> devel
    }

    /**
     * @exception Patrimony is null or Client is null or NullPointer is null
     *
     * (non-Javadoc)
     * @see view.reservasSalas.ReservaSalaView#verificarAction()
     */
    @Override protected void verificarAction() {
        try {
            this.qntCadeirasTxtField.setText(String.valueOf(instanceAluno.chairsAvailable(sala, this.dataTextField.getText(),
                    this.horaTextField.getText())));
        } catch (ReserveException ex) {
            
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClientException ex) {
            
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        
    }
}
