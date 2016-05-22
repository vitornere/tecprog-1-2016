/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservasSalas;

import java.awt.Color;
import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.ReservaSalaProfessor;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

/**
 * 
 * @author Parley
 */
public class AlterarReservaProfSalaView extends ReservaSalaView {

    int index;
    ReservaSalaProfessor reservaProfessor;

    private void resetComponents() {
        this.reservarButton.setText("Alterar");
        this.reservarButton.setName("AlterarButton");
        this.professorRadioButton.setSelected(true);
        this.cpfLabel.setEnabled(false);
        professorRadioButtonAction();
    }

    public AlterarReservaProfSalaView(Frame parent, boolean modal, int index, String data) throws SQLException,
            PatrimonyException, PatrimonyException, ClientException, ReserveException {
        super(parent, modal);
        this.setName("AlterarReservaSalaView");
        this.reservaProfessor = instanceProf.buscarPorData(data).get(index);
        resetComponents();

    }

    @Override protected void reservarProfessor() {
        try {
            instanceProf.alterar(this.finalidadeTextField.getText(), reservaProfessor);

            JOptionPane.showMessageDialog(this, "Reserva alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

            this.setVisible(false);
        } catch (ReserveException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override protected void professorRadioButtonAction() {
        Color blue = new Color(200, 208, 254);
        this.instanceAluno = null;
        this.alunoRadioButton.setEnabled(false);
        this.cpfTextField.setBackground(blue);
        this.cpfTextField.setEditable(false);
        this.qntCadeirasReservadasTextField.setEditable(true);
        this.qntCadeirasReservadasTextField.setBackground(Color.white);
        this.horaTextField.setBackground(blue);
        this.horaTextField.setEditable(false);
        this.horaTextField.setText(reservaProfessor.getHour());
        this.alunoTextArea.setText(reservaProfessor.getProfessor().toString());
        this.salaTextArea.setText(reservaProfessor.getClassroom().toString());
        this.dataTextField.setText(reservaProfessor.getDate());
        this.qntCadeirasTxtField.setText(reservaProfessor.getClassroom().getCapacidade());
        this.qntCadeirasReservadasTextField.setText(reservaProfessor.getClassroom().getCapacidade());
        this.qntCadeirasReservadasTextField.setBackground(blue);
        this.qntCadeirasReservadasTextField.setEditable(false);
        this.finalidadeTextField.setText(reservaProfessor.getFinality());
        this.verificarCadeiraButton.setEnabled(false);
    }

    @Override protected void alunoRadioButtonAction() {

    }

    @Override protected void reservarAluno() {

    }

    @Override protected void verificarAction() {
       
    }
}
