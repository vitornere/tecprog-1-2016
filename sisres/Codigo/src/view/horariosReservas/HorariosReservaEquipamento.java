/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.horariosReservas;

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
public class HorariosReservaEquipamento extends HorariosReservaPatrimonio {

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
            }
        }

        return nomesTabela;

    }

    @Override protected DefaultTableModel fillTable(Patrimony equip) {
        this.eq = (Equipment) equip;
        DefaultTableModel table = new DefaultTableModel();
        instance = ReserveEquipmentProfessorRegister.getReserveEquipmentProfessor();
        try {
            table.addColumn("");
            table.addColumn("Hora:");
            table.addColumn("Nome");
            table.addColumn("Matricula");
            table.addColumn("Codigo Eqpt.");
            table.addColumn("Descricao Eqpt.");

            this.mes = Integer.parseInt(this.data.substring(3, 5));

            Vector<ReserveEquipmentProfessor> v = instance.getMonthReservations(mes);
            if (v != null)
                for (int i = 0; i < v.size(); i++) {
                    table.addRow(fillDataVector(v.get(i), i));

                }

        } catch (SQLException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PatrimonyException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReserveException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table;

    }

    @Override protected void cancelarReservaAction(int index) {
        try {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Deseja mesmo excluir Reserva?\n" + instance.getMonthReservations(mes).get(index).toString(), "Excluir",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                this.instance.delete(instance.getMonthReservations(mes).get(index));
                JOptionPane.showMessageDialog(this, "Reserva excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                        null);
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

    @Override protected void reservarAction() {
        try {
            ReservaEquipamentoView reserva = new FazerReservaEquipamentoView(new JFrame(), true, this.eq, this.data);
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
