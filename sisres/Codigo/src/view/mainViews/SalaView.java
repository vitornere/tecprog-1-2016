/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainViews;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Classroom;
import view.alteracoes.ChangeClassroom;
import view.cadastros.PatrimonyRegistration;
import view.cadastros.ClassroomRegistration;
import view.diasReservas.DiaReservaSala;
import control.ManterSala;
import exception.PatrimonyException;

/**
 * 
 * @author Parley
 */
public class SalaView extends PatrimonioView {

    public SalaView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        pesquisarLbl.setText("Digite a sala desejada: ");
        this.setName("SalaView");
    }

    protected Vector<String> fillDataVector(Classroom sala) {

        if (sala == null) {
            return null;
        }

        Vector<String> nomesTabela = new Vector<String>();

        nomesTabela.add(sala.getCode());
        nomesTabela.add(sala.getDescription());
        nomesTabela.add(sala.getCapacity());

        return nomesTabela;

    }

    @Override protected DefaultTableModel fillTable() {
        try {
            DefaultTableModel table = new DefaultTableModel();

            Iterator<Classroom> i = ManterSala.getInstance().getSalas_vet().iterator();

            table.addColumn("Codigo");
            table.addColumn("Nome");
            table.addColumn("Capacidade");
            while (i.hasNext()) {
                Classroom sala = i.next();
                table.addRow(fillDataVector(sala));
            }

            return table;

        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }

        return null;
    }

    @Override protected void cadastrarAction() {
        PatrimonyRegistration cadastro = new ClassroomRegistration(new javax.swing.JFrame(), true);
        cadastro.setResizable(false);
        cadastro.setVisible(true);
        this.tabelaPatrimonio.setModel(fillTable());
    }

    @Override protected void alterarAction(int index) {

        ChangeClassroom alteracao = new ChangeClassroom(new javax.swing.JFrame(), true, index);
        alteracao.setResizable(false);
        alteracao.setVisible(true);
        this.tabelaPatrimonio.setModel(fillTable());
    }

    @Override protected void excluirAction(int index) {
        try {
            int confirm = JOptionPane
                    .showConfirmDialog(this, "Deseja mesmo excluir Sala: "
                            + ManterSala.getInstance().getSalas_vet().get(index).getDescription() + "?", "Excluir",
                            JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                ManterSala.getInstance().excluir(ManterSala.getInstance().getSalas_vet().get(index));
                JOptionPane.showMessageDialog(this, "Sala excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            }
            this.tabelaPatrimonio.setModel(fillTable());

        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override protected void visualizarAction(int index) {
        try {
            DiaReservaSala reserva = new DiaReservaSala(new javax.swing.JFrame(), true, index);
            reserva.setResizable(false);
            reserva.setVisible(true);
        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
