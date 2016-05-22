package view.cadastros;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.ManterEquipamento;
import exception.PatrimonyException;

public class EquipmentRegistration extends PatrimonyRegistration {

    public EquipmentRegistration(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setName("CadastroEquipamento");
        this.lblCapacity.setVisible(false);
        this.capacityTxtField.setVisible(false);
    }

   protected void registrationAction() {

        try {
            ManterEquipamento.getInstance().inserir(codeTxtField.getText(), discriptionTextArea.getText());
            JOptionPane.showMessageDialog(this, "Equipamento Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                    null);
            this.setVisible(false);

        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }

    }
}
