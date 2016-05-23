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

import model.Patrimonio;
import model.ReservaSalaAluno;
import model.ReservaSalaProfessor;
import model.Classroom;
import view.reservasSalas.AlterarReservaAlunoSalaView;
import view.reservasSalas.AlterarReservaProfSalaView;
import view.reservasSalas.FazerReservaSalaView;
import view.reservasSalas.ReservaSalaView;
import control.ManterResSalaAluno;
import control.ManterResSalaProfessor;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;

/**
 * 
 * @author Parley
 */
public class HoursRoomReservation extends HoursPatrimonyReservation {

    ManterResSalaAluno instanceAluno;
    ManterResSalaProfessor instanceProf;

    Classroom room;

    public HoursRoomReservation(java.awt.Frame parent, boolean modal, String data, Classroom room) {
        super(parent, modal, data, room);
        
        this.room = room;
        
        this.setName("HorarioReservaSala");
    }

    protected Vector<String> fillDataVector(Object o, int index) {
        Vector<String> nomesTabela = new Vector<String>();
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

    @Override protected DefaultTableModel fillTable(Patrimonio sala) {
        this.room = (Classroom) sala;
        DefaultTableModel table = new DefaultTableModel();
        instanceAluno = ManterResSalaAluno.getInstance();
        instanceProf = ManterResSalaProfessor.getInstance();
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
            Vector v = instanceProf.buscarPorData(this.data);

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
            v = instanceAluno.getReservasMes(this.data);
            
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
        } catch (ClienteException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReservaException ex) {
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
                        "Deseja mesmo excluir Reserva?\n" + instanceAluno.getReservasMes(data).get(index).toString(), "Excluir",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    this.instanceAluno.excluir(instanceAluno.getReservasMes(data).get(index));
                    JOptionPane.showMessageDialog(this, "Reserva excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                            null);
                }
                else {
                	// Nothing to do
                }
            } else if (tipoCliente.equals("Professor")) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Deseja mesmo excluir Reserva?\n" + instanceProf.buscarPorData(data).get(index).toString(), "Excluir",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    this.instanceProf.excluir(instanceProf.buscarPorData(data).get(index));
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
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ReservaException ex) {
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
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ReservaException ex) {
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
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ReservaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}