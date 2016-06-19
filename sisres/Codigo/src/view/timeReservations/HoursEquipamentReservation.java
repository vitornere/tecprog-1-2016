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
import control.ReserveEquipmentProfessorRegister;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

/**
 * 
 * @author Parley
 */
public class HoursEquipamentReservation extends HoursPatrimonyReservation {

    Equipment equipment;
    ReserveEquipmentProfessorRegister instanceOfProfessorEquipament;

    public HoursEquipamentReservation(java.awt.Frame parent, boolean modal, String data, Equipment eq) {
        super(parent, modal, data, eq);
        this.equipment = eq;
    }

    protected Vector<String> fillDataVector(Object o, int index) {
        Vector<String> tableNames = new Vector<String>();
        if (o instanceof ReserveEquipmentProfessor) {
            ReserveEquipmentProfessor r = (ReserveEquipmentProfessor) o;
            if (this.equipment != null && (r.getEquipment().equals(this.equipment))) {

                tableNames.add(String.valueOf(index));
                tableNames.add(r.getHour());
                tableNames.add(r.getProfessor().getNamePerson());
                tableNames.add(r.getProfessor().getIdRegister());
                tableNames.add(r.getEquipment().getIdEquipment());
                tableNames.add(r.getEquipment().getDescriptionEquipment());
            }
        }
        else {
        	// Nothing to do
        }

        return tableNames;
    }

    @Override 
    protected DefaultTableModel fillTable(Patrimony equip) {
        this.equipment = (Equipment) equip;
        DefaultTableModel table = new DefaultTableModel();
        instanceOfProfessorEquipament = ReserveEquipmentProfessorRegister.getInstanceEquipmentProfessor();
        try {
            table.addColumn("");
            table.addColumn("Hora:");
            table.addColumn("Nome");
            table.addColumn("Matricula");
            table.addColumn("Codigo Eqpt.");
            table.addColumn("Descricao Eqpt.");

            this.month = Integer.parseInt(this.data.substring(3, 5));

            Vector<ReserveEquipmentProfessor> professorEquipament = instanceOfProfessorEquipament.getMonthReservations(month);
            
            if (professorEquipament != null) {
                for (int i = 0; i < professorEquipament.size(); i++) {
                    table.addRow(fillDataVector(professorEquipament.get(i), i));

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
        }
        
        return table;
    }

    @Override protected void cancelActionReservation(int index) {
        try {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Deseja mesmo excluir Reserva?\n" + 
                     instanceOfProfessorEquipament.getMonthReservations(month).get(index).toString(), 
                     "Excluir", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                this.instanceOfProfessorEquipament.delete(instanceOfProfessorEquipament.getMonthReservations(month).get(index));
                
                JOptionPane.showMessageDialog(this, "Reserva excluida com sucesso", "Sucesso", 
                							  JOptionPane.INFORMATION_MESSAGE, null);
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
            ReservaEquipamentoView reserva = new FazerReservaEquipamentoView(new JFrame(), true, this.equipment, this.data);
            
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
