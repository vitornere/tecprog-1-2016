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

import model.Equipment;
import model.Patrimony;
import model.ReserveEquipmentProfessor;
import view.reservasEquipamentos.FazerReservaEquipamentoView;
import view.reservasEquipamentos.ReservaEquipamentoView;
<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaEquipamento.java
import control.ReserveEquipmentProfessorRegister;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;
=======
import control.ManterResEquipamentoProfessor;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursEquipamentReservation.java

/**
 * 
 * @author Parley
 */
public class HoursEquipamentReservation extends HoursPatrimonyReservation {

<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaEquipamento.java
    Equipment eq;
    ReserveEquipmentProfessorRegister instance;

    public HorariosReservaEquipamento(java.awt.Frame parent, boolean modal, String data, Equipment eq) {
        super(parent, modal, data, eq);
        this.eq = eq;
    }

    protected Vector<String> fillDataVector(Object o, int index) {
        Vector<String> nomesTabela = new Vector<String>();
        if (o instanceof ReserveEquipmentProfessor) {
            ReserveEquipmentProfessor r = (ReserveEquipmentProfessor) o;
            if (this.eq != null && (r.getEquipment().equals(this.eq))) {

                nomesTabela.add(String.valueOf(index));
                nomesTabela.add(r.getHour());
                nomesTabela.add(r.getProfessor().getNamePerson());
                nomesTabela.add(r.getProfessor().getIdRegister());
                nomesTabela.add(r.getEquipment().getIdEquipment());
                nomesTabela.add(r.getEquipment().getDescriptionEquipment());
=======
    Equipamento equipament;
    ManterResEquipamentoProfessor instanceOfProfessorEquipament;

    public HoursEquipamentReservation(java.awt.Frame parent, boolean modal, String data, Equipamento equipament) {
        super(parent, modal, data, equipament);
        
        this.equipament = equipament;
    }

    protected Vector<String> fillDataVector(Object o, int index) {
        Vector<String> tableNames = new Vector<String>();
        
        if (o instanceof ReservaEquipamentoProfessor) {
            ReservaEquipamentoProfessor r = (ReservaEquipamentoProfessor) o;
           
            if (this.equipament != null && (r.getEquipamento().equals(this.equipament))) {
                tableNames.add(String.valueOf(index));                
                tableNames.add(r.getHora());                
                tableNames.add(r.getProfessor().getNome());
                tableNames.add(r.getProfessor().getMatricula());                
                tableNames.add(r.getEquipamento().getCode());                
                tableNames.add(r.getEquipamento().getDescription());
            }
            else {
            	// Nothing to do
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursEquipamentReservation.java
            }
        }
        else {
        	// Nothing to do
        }

        return tableNames;
    }

<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaEquipamento.java
    @Override protected DefaultTableModel fillTable(Patrimony equip) {
        this.eq = (Equipment) equip;
        DefaultTableModel table = new DefaultTableModel();
        instance = ReserveEquipmentProfessorRegister.getReserveEquipmentProfessor();
=======
    @Override protected DefaultTableModel fillTable(Patrimonio equip) {
        this.equipament = (Equipamento) equip;
        
        DefaultTableModel table = new DefaultTableModel();
        
        instanceOfProfessorEquipament = ManterResEquipamentoProfessor.getInstance();
        
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursEquipamentReservation.java
        try {
            table.addColumn("");
            table.addColumn("Hora:");
            table.addColumn("Nome");
            table.addColumn("Matricula");
            table.addColumn("Codigo Eqpt.");
            table.addColumn("Descricao Eqpt.");

            this.month = Integer.parseInt(this.data.substring(3, 5));

<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaEquipamento.java
            Vector<ReserveEquipmentProfessor> v = instance.getMonthReservations(mes);
            if (v != null)
                for (int i = 0; i < v.size(); i++) {
                    table.addRow(fillDataVector(v.get(i), i));
=======
            Vector<ReservaEquipamentoProfessor> professorEquipament = instanceOfProfessorEquipament.getReservasMes(month);
            
            if (professorEquipament != null) {
                for (int i = 0; i < professorEquipament.size(); i++) {
                    table.addRow(fillDataVector(professorEquipament.get(i), i));
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursEquipamentReservation.java

                }
            }
            else {
            	// Nothing to do
            }
        } catch (SQLException ex) {
<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaEquipamento.java
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
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursEquipamentReservation.java
        }
        
        return table;
    }

    @Override protected void cancelActionReservation(int index) {
        try {
            int confirm = JOptionPane.showConfirmDialog(this,
<<<<<<< HEAD:sisres/Codigo/src/view/horariosReservas/HorariosReservaEquipamento.java
                    "Deseja mesmo excluir Reserva?\n" + instance.getMonthReservations(mes).get(index).toString(), "Excluir",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                this.instance.delete(instance.getMonthReservations(mes).get(index));
                JOptionPane.showMessageDialog(this, "Reserva excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                        null);
=======
                    "Deseja mesmo excluir Reserva?\n" + 
                     instanceOfProfessorEquipament.getReservasMes(month).get(index).toString(), 
                     "Excluir", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                this.instanceOfProfessorEquipament.excluir(instanceOfProfessorEquipament.getReservasMes(month).get(index));
                
                JOptionPane.showMessageDialog(this, "Reserva excluida com sucesso", "Sucesso", 
                							  JOptionPane.INFORMATION_MESSAGE, null);
            }
            else {
            	// Nothing to do
>>>>>>> devel:sisres/Codigo/src/view/timeReservations/HoursEquipamentReservation.java
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
            ReservaEquipamentoView reserva = new FazerReservaEquipamentoView(new JFrame(), true, this.equipament, this.data);
            
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
        /**
         * try { index = Integer.parseInt((String)
         * this.reservasTable.getModel().getValueAt(index, 0));
         * ReservaEquipamentoView reserva = new
         * AlterarReservaEquipamentoView(new JFrame(), true, index, this.mes);
         * reserva.setVisible(true);
         * 
         * } catch (SQLException ex) { JOptionPane.showMessageDialog(this,
         * ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null); } catch
         * (PatrimonioException ex) { JOptionPane.showMessageDialog(this,
         * ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null); } catch
         * (ClienteException ex) { JOptionPane.showMessageDialog(this,
         * ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null); } catch
         * (ReservaException ex) { JOptionPane.showMessageDialog(this,
         * ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null); }
         */
    }
}
