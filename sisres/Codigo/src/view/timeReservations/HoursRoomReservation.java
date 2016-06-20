/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.timeReservations;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import model.Patrimony;
import model.ReserveClassroomForStudent;
import model.ReserveClassroomForProfessor;
import model.Classroom;
import view.reservasSalas.AlterarReservaAlunoSalaView;
import view.reservasSalas.AlterarReservaProfSalaView;
import view.reservasSalas.FazerReservaSalaView;
import view.reservasSalas.ReservaSalaView;
import control.ReserveClassroomForStudentRegister;
import control.ReserveClassroomForProfessorRegister;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

/**
 * 
 * @author Parley
 */
public class HoursRoomReservation extends HoursPatrimonyReservation {


    ReserveClassroomForStudentRegister instanceAluno;
    ReserveClassroomForProfessorRegister instanceProf;
    Classroom room;

    public HoursRoomReservation(java.awt.Frame parent, boolean modal, String data, Classroom room) {
        super(parent, modal, data, room);
        
        this.room = room;
        this.setName("HorarioReservaSala");
    }

    protected Vector<String> fillDataVector(Object o, int index) {
        Vector<String> nomesTabela = new Vector<String>();
        if (o instanceof ReserveClassroomForStudent) {
            ReserveClassroomForStudent r = (ReserveClassroomForStudent) o;
            if (this.room != null && (r.getClassroom().equals(this.room))) {
                nomesTabela.add(String.valueOf(index));
                nomesTabela.add("Aluno");
                nomesTabela.add(r.getHour());
                nomesTabela.add(r.getStudent().getNamePerson());
                nomesTabela.add(r.getStudent().getIdRegister());
                nomesTabela.add(r.getFinality());
                nomesTabela.add(r.getClassroom().getIdEquipment());
                nomesTabela.add(r.getClassroom().getDescriptionEquipment());
                nomesTabela.add(r.getReservedChairs());
                nomesTabela.add(r.getClassroom().getCapacity());
            }
        } else if (o instanceof ReserveClassroomForProfessor) {
            ReserveClassroomForProfessor r = (ReserveClassroomForProfessor) o;
            if (this.room != null && (r.getClassroom().equals(this.room))) {

                nomesTabela.add(String.valueOf(index));
                nomesTabela.add("Professor");
                nomesTabela.add(r.getHour());
                nomesTabela.add(r.getProfessor().getNamePerson());
                nomesTabela.add(r.getProfessor().getIdRegister());
                nomesTabela.add(r.getFinality());
                nomesTabela.add(r.getClassroom().getIdEquipment());
                nomesTabela.add(r.getClassroom().getDescriptionEquipment());
                nomesTabela.add(r.getClassroom().getCapacity());
                nomesTabela.add(r.getClassroom().getCapacity());
            }
            else {
            	// Nothing to do
            }
        }
        else {
        	// Nothing to do
        }

        return nomesTabela;

    }


    @Override protected DefaultTableModel fillTable(Patrimony sala) {
        this.room = (Classroom) sala;
        DefaultTableModel table = new DefaultTableModel();
        instanceAluno = ReserveClassroomForStudentRegister.getInstance();
        instanceProf = ReserveClassroomForProfessorRegister.getInstance();
        table.addColumn("");
        table.addColumn("Tipo:");
        table.addColumn("Hora:");
        table.addColumn("Nome");
        table.addColumn("Matricula");
        table.addColumn("Finalidade");
        table.addColumn("Codigo da Sala");
        table.addColumn("Descricao da Sala");
        table.addColumn("Reservadas");
        table.addColumn("Capacidade");

        this.month = Integer.parseInt(this.data.substring(3, 5));

        try {
            Vector v = instanceProf.searchForDate(this.data);

            if (v != null) {
                for (int i = 0; i < v.size(); i++) {
                    Vector<String> linha = fillDataVector(v.get(i), i);
                    if (!linha.isEmpty()) {
                        table.addRow(linha);
                    }
                    else {
                    	// Nothing to do
                    }
                }
            }
            else {
            	// Nothing to do
            }
            v.clear();
            v = instanceAluno.getMonthReservations(this.data);
            
            if (v != null) {
                for (int i = 0; i < v.size(); i++) {
                    Vector<String> linha = fillDataVector(v.get(i), i);
                    if (!linha.isEmpty()) {
                        table.addRow(linha);
                    }
                    else {
                    	// Nothing to do
                    }

                }
            }
            else {
            	// Nothing to do
            }

        } catch (SQLException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PatrimonyException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReserveException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return table;

    }

    @Override protected void cancelActionReservation(int index) {
        try {
            String tipoCliente = (String) this.reservationsTable.getModel().getValueAt(index, 1);
            index = Integer.parseInt((String) this.reservationsTable.getModel().getValueAt(index, 0));
            
            if (tipoCliente.equals("Aluno")) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Deseja mesmo excluir Reserva?\n" + instanceAluno.getMonthReservations(data).get(index).toString(), "Excluir",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    this.instanceAluno.delete(instanceAluno.getMonthReservations(data).get(index));
                    JOptionPane.showMessageDialog(this, "Reserva excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                            null);
                }
                else {
                	// Nothing to do
                }
            } else if (tipoCliente.equals("Professor")) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Deseja mesmo excluir Reserva?\n" + instanceProf.searchForDate(data).get(index).toString(), "Excluir",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    this.instanceProf.delete(instanceProf.searchForDate(data).get(index));
                    JOptionPane.showMessageDialog(this, "Reserva excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                            null);
                }
                else {
                	// Nothing to do
                }
            }
            else {
            	// Nothing to do
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ReserveException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override protected void actionReservation() {
        try {
            ReservaSalaView reserva = new FazerReservaSalaView(new JFrame(), true, room, this.data);
            reserva.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ReserveException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override protected void alterarAction(int index) {
        try {
            String tipoCliente = (String) this.reservationsTable.getModel().getValueAt(index, 1);
            index = Integer.parseInt((String) this.reservationsTable.getModel().getValueAt(index, 0));
            if (tipoCliente.equals("Aluno")) {
                ReservaSalaView reserva = new AlterarReservaAlunoSalaView(new JFrame(), true, index, this.data);
                reserva.setVisible(true);
            } else if (tipoCliente.equals("Professor")) {
                ReservaSalaView reserva = new AlterarReservaProfSalaView(new JFrame(), true, index, this.data);
                reserva.setVisible(true);
            }
            else {
            	// Nothing to do
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (HeadlessException ex) {
        	JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (PatrimonyException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (ClientException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		} catch (ReserveException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
		}
    }
}