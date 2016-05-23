package view.mainViews;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Equipamento;
import view.alteracoes.ChangeEquipment;
import view.cadastros.EquipmentRegistration;
import view.diasReservas.EquipmentReserveDay;
import control.ManterEquipamento;
import exception.PatrimonyException;

/**
 * 
 * @author Parley
 */
public class EquipamentView extends PratimonyView {

    public EquipamentView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        searchLbl.setText("Digite o eqpto. desejado: ");
        this.setTitle("Equipamentos");
        this.setName("EquipamentoView");
    }

    private Vector<String> fillDataVector(Equipamento equipament) {

        if (equipament == null) {
            return null;
        }

        Vector<String> tableNames = new Vector<String>();

        tableNames.add(equipament.getCode());
        tableNames.add(equipament.getDescription());

        return tableNames;

    }

    @Override protected DefaultTableModel fillTable() {
        try {
            DefaultTableModel table = new DefaultTableModel();

            Iterator<Equipamento> i = control.ManterEquipamento.getInstance().getEquipamento_vet().iterator();

            table.addColumn("Codigo");
            table.addColumn("Descricao");

            while (i.hasNext()) {
                Equipamento equipamento = i.next();
                table.addRow(fillDataVector(equipamento));
            }
            
            return table;

        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        
        return null;
    }

    @Override protected void registerAction() {
	EquipmentRegistration newRegister = new EquipmentRegistration(new javax.swing.JFrame(), true);
        newRegister.setResizable(false);
        newRegister.setVisible(true);
        this.patrimonyTable.setModel(fillTable());
    }

    @Override protected void changeAction(int index) {

		ChangeEquipment newChange = new ChangeEquipment(new javax.swing.JFrame(), true, index);
        newChange.setResizable(false);
        newChange.setVisible(true);
        this.patrimonyTable.setModel(fillTable());

    }

    @Override protected void excluirAction(int index) {

        try {
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir Equipamento: "
                    + ManterEquipamento.getInstance().getEquipamento_vet().get(index).getDescription() + "?", "Excluir",
                    JOptionPane.YES_NO_OPTION);


            if (confirm == JOptionPane.YES_OPTION) {
                ManterEquipamento.getInstance().excluir(ManterEquipamento.getInstance().getEquipamento_vet()
                				 .get(index));
                JOptionPane.showMessageDialog(this, "Equipamento excluido com sucesso", "Sucesso", 
                							  JOptionPane.INFORMATION_MESSAGE, null);
            }
            this.patrimonyTable.setModel(fillTable());

        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }

    }

    @Override protected void visualizarAction(int index) {
        try {
            EquipmentReserveDay reserva = new EquipmentReserveDay(new javax.swing.JFrame(), true, index);
            reserva.setResizable(false);
            reserva.setVisible(true);
        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}
