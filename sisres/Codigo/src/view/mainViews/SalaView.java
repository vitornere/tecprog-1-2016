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
import view.alteracoes.AlterarSala;
import view.cadastros.CadastroPatrimonio;
import view.cadastros.CadastroSala;
import view.diasReservas.DiaReservaSala;
import control.ClassroomRegister;
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

        nomesTabela.add(sala.getIdEquipment());
        nomesTabela.add(sala.getDescriptionEquipment());
        nomesTabela.add(sala.getCapacity());

        return nomesTabela;

    }

    @Override protected DefaultTableModel fillTable() {
        try {
            DefaultTableModel table = new DefaultTableModel();

            Iterator<Classroom> i = ClassroomRegister.getClassroom().getVectorClassroom().iterator();

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
        CadastroPatrimonio cadastro = new CadastroSala(new javax.swing.JFrame(), true);
        cadastro.setResizable(false);
        cadastro.setVisible(true);
        this.tabelaPatrimonio.setModel(fillTable());
    }

    @Override protected void alterarAction(int index) {

        AlterarSala alteracao = new AlterarSala(new javax.swing.JFrame(), true, index);
        alteracao.setResizable(false);
        alteracao.setVisible(true);
        this.tabelaPatrimonio.setModel(fillTable());
    }

    @Override protected void excluirAction(int index) {
        try {
            int confirm = JOptionPane
                    .showConfirmDialog(this, "Deseja mesmo excluir Sala: "
                            + ClassroomRegister.getClassroom().getVectorClassroom().get(index).getDescriptionEquipment() + "?", "Excluir",
                            JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                ClassroomRegister.getClassroom().delete(ClassroomRegister.getClassroom().getVectorClassroom().get(index));
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
