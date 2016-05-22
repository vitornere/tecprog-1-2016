/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservasSalas;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Classroom;
import control.ReserveClassroomForStudentRegister;
import control.ManterResSalaProfessor;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

/**
 * 
 * @author Parley
 */
public class FazerReservaSalaView extends ReservaSalaView {

    public FazerReservaSalaView(Frame parent, boolean modal, Classroom sala, String data) throws SQLException, PatrimonyException,
            PatrimonyException, ClientException, ReserveException {
        super(parent, modal);
        this.sala = sala;
        this.dataTextField.setText(data);
        this.salaTextArea.setText(sala.toString());
        this.qntCadeirasTxtField.setText(sala.getCapacidade());
        this.setName("FazerReservaSalaView");
        
    }

    @Override protected void reservarAluno() {
        try {
            instanceAluno.insert(sala, aluno, this.dataTextField.getText(), this.horaTextField.getText(),
                    this.finalidadeTextField.getText(), this.qntCadeirasReservadasTextField.getText());

            instanceAluno.getVectorReserveClassroomForStudent();
            // System.out.println(v.toString());

            JOptionPane.showMessageDialog(this, "Reserva feita com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

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

    @Override protected void reservarProfessor() {
        try {

            instanceProf.inserir(sala, prof, this.dataTextField.getText(), this.horaTextField.getText(),
                    this.finalidadeTextField.getText());

            JOptionPane.showMessageDialog(this, "Reserva feita com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);

            this.setVisible(false);
        } catch (ReserveException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

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

    @Override protected void alunoRadioButtonAction() {
        this.instanceAluno = ReserveClassroomForStudentRegister.getReserveClassroomForStudent();
        this.alunoLabel.setText(this.alunoRadioButton.getText() + ": ");
        this.alunoTextArea.setText("");
        this.cpfTextField.setText("");
        this.qntCadeirasReservadasTextField.setEnabled(true);
        this.qntCadeirasReservadasTextField.setEditable(true);
        this.qntCadeirasReservadasTextField.setBackground(Color.white);
        this.instanceProf = null;
        this.verificarCadeiraButton.setEnabled(true);
    }

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
