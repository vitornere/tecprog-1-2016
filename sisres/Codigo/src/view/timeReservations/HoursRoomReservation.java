/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.timeReservations;

import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaSala.java
import model.Patrimony;
import model.ReserveClassroomForStudent;
import model.ReserveClassroomForProfessor;
=======
import model.Patrimonio;
import model.ReservaSalaAluno;
import model.ReservaSalaProfessor;
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursRoomReservation.java
import model.Classroom;
import view.reservasSalas.AlterarReservaAlunoSalaView;
import view.reservasSalas.AlterarReservaProfSalaView;
import view.reservasSalas.FazerReservaSalaView;
import view.reservasSalas.ReservaSalaView;
<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaSala.java
import control.ReserveClassroomForStudentRegister;
import control.ReserveClassroomForProfessorRegister;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;
=======
import control.ManterResSalaAluno;
import control.ManterResSalaProfessor;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursRoomReservation.java

/**
 * 
 * @author Parley
 */
public class HoursRoomReservation extends HoursPatrimonyReservation {

<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaSala.java
    ReserveClassroomForStudentRegister instanceAluno;
    ReserveClassroomForProfessorRegister instanceProf;
    Classroom sala;

    public HorariosReservaSala(java.awt.Frame parent, boolean modal, String data, Classroom sala) {
        super(parent, modal, data, sala);
        this.sala = sala;
=======
    ManterResSalaAluno instanceAluno;
    ManterResSalaProfessor instanceProf;

    Classroom room;

    public HoursRoomReservation(java.awt.Frame parent, boolean modal, String data, Classroom room) {
        super(parent, modal, data, room);
        
        this.room = room;
        
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursRoomReservation.java
        this.setName("HorarioReservaSala");
    }

    protected Vector<String> fillDataVector(Object o, int index) {
        Vector<String> nomesTabela = new Vector<String>();
<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaSala.java
        if (o instanceof ReserveClassroomForStudent) {
            ReserveClassroomForStudent r = (ReserveClassroomForStudent) o;
            if (this.sala != null && (r.getClassroom().equals(this.sala))) {
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
            if (this.sala != null && (r.getClassroom().equals(this.sala))) {

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
=======
        if (o instanceof ReservaSalaAluno) {
            ReservaSalaAluno r = (ReservaSalaAluno) o;
            
            if (this.room != null && (r.getSala().equals(this.room))) {
                nomesTabela.add(String.valueOf(index));
                nomesTabela.add("Aluno");
                nomesTabela.add(r.getHora());
                nomesTabela.add(r.getAluno().getNome());
                nomesTabela.add(r.getAluno().getMatricula());
                nomesTabela.add(r.getFinalidade());
                nomesTabela.add(r.getSala().getCode());
                nomesTabela.add(r.getSala().getDescription());
                nomesTabela.add(r.getCadeiras_reservadas());
                nomesTabela.add(r.getSala().getCapacity());
            }
            else {
            	// Nothing to do
            }
        } else if (o instanceof ReservaSalaProfessor) {
            ReservaSalaProfessor r = (ReservaSalaProfessor) o;
            
            if (this.room != null && (r.getSala().equals(this.room))) {

                nomesTabela.add(String.valueOf(index));
                nomesTabela.add("Professor");
                nomesTabela.add(r.getHora());
                nomesTabela.add(r.getProfessor().getNome());
                nomesTabela.add(r.getProfessor().getMatricula());
                nomesTabela.add(r.getFinalidade());
                nomesTabela.add(r.getSala().getCode());
                nomesTabela.add(r.getSala().getDescription());
                nomesTabela.add(r.getSala().getCapacity());
                nomesTabela.add(r.getSala().getCapacity());
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursRoomReservation.java
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

<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaSala.java
    @Override protected DefaultTableModel fillTable(Patrimony sala) {
        this.sala = (Classroom) sala;
=======
    @Override protected DefaultTableModel fillTable(Patrimonio sala) {
        this.room = (Classroom) sala;
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursRoomReservation.java
        DefaultTableModel table = new DefaultTableModel();
        instanceAluno = ReserveClassroomForStudentRegister.getReserveClassroomForStudent();
        instanceProf = ReserveClassroomForProfessorRegister.getReserveClassroomForProfessor();
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

<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaSala.java
            v = instanceAluno.getMonthReservations(this.data);
            if (v != null)
=======
            v.clear();
            v = instanceAluno.getReservasMes(this.data);
            
            if (v != null) {
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursRoomReservation.java
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
<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaSala.java
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PatrimonyException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReserveException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
=======
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PatrimonyException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClienteException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReservaException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursRoomReservation.java
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
        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ReserveException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}