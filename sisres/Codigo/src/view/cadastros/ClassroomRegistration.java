package view.cadastros;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.ManterSala;
import exception.PatrimonyException;

public class ClassroomRegistration extends PatrimonyRegistration {

    public ClassroomRegistration(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setName("CadastroSala");
    }

 protected void registrationAction() {
        try {
     
            ManterSala.getInstance().inserir(codeTxtField.getText(), discriptionTextArea.getText(), capacityTxtField.getText());

            JOptionPane.showMessageDialog(this, "Sala Cadastrada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, null);
            this.setVisible(false);

        } catch (PatrimonyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getSQLState() + "\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }

    }
}
