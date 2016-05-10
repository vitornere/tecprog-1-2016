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

import model.Equipamento;
import model.Patrimonio;
import model.ReservaEquipamentoProfessor;
import view.reservasEquipamentos.FazerReservaEquipamentoView;
import view.reservasEquipamentos.ReservaEquipamentoView;
import control.ManterResEquipamentoProfessor;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

/**
 * 
 * @author Parley
 */
public class HoursEquipamentReservation extends HoursPatrimonyReservation {

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
                tableNames.add(r.getEquipamento().getCodigo());                
                tableNames.add(r.getEquipamento().getDescricao());
            }
            else {
            	// Nothing to do
            }
        }
        else {
        	// Nothing to do
        }

        return tableNames;
    }

    @Override protected DefaultTableModel fillTable(Patrimonio equip) {
        this.equipament = (Equipamento) equip;
        
        DefaultTableModel table = new DefaultTableModel();
        
        instanceOfProfessorEquipament = ManterResEquipamentoProfessor.getInstance();
        
        try {
            table.addColumn("");
            table.addColumn("Hora:");
            table.addColumn("Nome");
            table.addColumn("Matricula");
            table.addColumn("Codigo Eqpt.");
            table.addColumn("Descricao Eqpt.");

            this.month = Integer.parseInt(this.data.substring(3, 5));

            Vector<ReservaEquipamentoProfessor> professorEquipament = instanceOfProfessorEquipament.getReservasMes(month);
            
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
        } catch (PatrimonioException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClienteException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReservaException ex) {
            Logger.getLogger(HoursPatrimonyReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return table;
    }

    @Override protected void cancelActionReservation(int index) {
        try {
            int confirm = JOptionPane.showConfirmDialog(this,
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
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ReservaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override protected void actionReservation() {
        try {
            ReservaEquipamentoView reserva = new FazerReservaEquipamentoView(new JFrame(), true, this.equipament, this.data);
            
            reserva.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ReservaException ex) {
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
