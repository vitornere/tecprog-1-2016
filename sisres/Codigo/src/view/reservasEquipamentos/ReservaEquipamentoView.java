/**
 * This class is main screen of equipaments reservations generated by swing
 */
package view.reservasEquipamentos;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Professor;
import control.ManterProfessor;
import control.ManterResEquipamentoProfessor;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;

public abstract class ReservaEquipamentoView extends javax.swing.JDialog {

    protected ManterResEquipamentoProfessor instanceProf; // Object of professor's reservation
    protected Professor prof; // Object of professor

    public ReservaEquipamentoView(java.awt.Frame parent, boolean modal) throws SQLException, PatrimonyException,
            PatrimonyException, ClienteException, ReservaException {
        super(parent, modal);
        this.instanceProf = ManterResEquipamentoProfessor.getInstance();

        initComponents();
    }

    abstract protected void reservarProfessor();

    /**
     * 
     * @exception Client null or Database null or NullPoiner null
     */
    protected void getProfessor() {
        try {
            Vector<Professor> professor = ManterProfessor.getInstance().buscarCpf(this.cpfTextField.getText());
            if (professor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Professor nao Cadastrado."
                        + " Digite o CPF correto ou cadastre o professor desejado", "Erro", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            this.prof = professor.firstElement();
            this.professorTextArea.setText(professor.firstElement().toString());
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked") // <editor-fold defaultstate="collapsed"
                                  // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

    	/*
    	 * All components of screen
    	 */
        alunoProfbuttonGroup = new javax.swing.ButtonGroup();
        equipamentoLabel = new javax.swing.JLabel();
        professorLabel = new javax.swing.JLabel();
        cpfLabel = new javax.swing.JLabel();
        cpfTextField = new javax.swing.JTextField();
        dataLabel = new javax.swing.JLabel();
        horaLabel = new javax.swing.JLabel();
        horaTextField = new javax.swing.JTextField();
        reservarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        professorTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        equipamentoTextArea = new javax.swing.JTextArea();
        dataTextField = new javax.swing.JTextField();
        buscarCpfButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ReservaPatrimonio");
        setName("ReservaPatrimonio"); // NOI18N
        setResizable(false);

        equipamentoLabel.setText("Equipamento:");
        equipamentoLabel.setName("EquipamentoLabel"); // NOI18N

        professorLabel.setText("Professor:");
        professorLabel.setName("ProfessorLabel"); // NOI18N

        cpfLabel.setText("Digite o CPF desejado :");
        cpfLabel.setName("CpfLabel"); // NOI18N

        cpfTextField.setName("CPF"); // NOI18N
        cpfTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfTextFieldActionPerformed(evt);
            }
        });

        dataLabel.setText("Data: ");

        horaLabel.setText("Hora: ");
        horaLabel.setName("HoraLabel"); // NOI18N

        horaTextField.setName("Hora"); // NOI18N

        reservarButton.setText("Reservar");
        reservarButton.setName("Reservar"); // NOI18N
        reservarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservarButtonActionPerformed(evt);
            }
        });

        cancelarButton.setText("Cancelar");
        cancelarButton.setName("Cancelar"); // NOI18N
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        professorTextArea.setEditable(false);
        professorTextArea.setBackground(new java.awt.Color(200, 208, 254));
        professorTextArea.setColumns(20);
        professorTextArea.setRows(5);
        professorTextArea.setName("ProfessorTextArea"); // NOI18N
        jScrollPane1.setViewportView(professorTextArea);

        equipamentoTextArea.setEditable(false);
        equipamentoTextArea.setBackground(new java.awt.Color(200, 208, 254));
        equipamentoTextArea.setColumns(20);
        equipamentoTextArea.setRows(5);
        equipamentoTextArea.setName("EquipamentoTextArea"); // NOI18N
        jScrollPane2.setViewportView(equipamentoTextArea);

        dataTextField.setEditable(false);
        dataTextField.setBackground(new java.awt.Color(200, 208, 254));
        dataTextField.setName("DiaTextField"); // NOI18N

        buscarCpfButton.setText("Buscar CPF");
        buscarCpfButton.setName("BuscarCpfButton"); // NOI18N
        buscarCpfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarCpfButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addGap(199, 199, 199)
                                                                .addComponent(reservarButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 82,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(cancelarButton,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 81,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE))
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addGroup(
                                                                        layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(
                                                                                        layout.createSequentialGroup()
                                                                                                .addComponent(cpfLabel)
                                                                                                .addPreferredGap(
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(
                                                                                                        cpfTextField,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        164,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(buscarCpfButton)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addGroup(
                                                                                        layout.createSequentialGroup()
                                                                                                .addGroup(
                                                                                                        layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(
                                                                                                                        professorLabel)
                                                                                                                .addComponent(
                                                                                                                        equipamentoLabel,
                                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                        81,
                                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(
                                                                                                        layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(
                                                                                                                        jScrollPane1)
                                                                                                                .addComponent(
                                                                                                                        jScrollPane2,
                                                                                                                        javax.swing.GroupLayout.Alignment.TRAILING))))
                                                                .addContainerGap())
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addComponent(dataLabel)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(dataTextField,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 143,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(horaLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(horaTextField,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 143,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cpfLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cpfTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buscarCpfButton))
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup().addGap(51, 51, 51).addComponent(professorLabel))
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(equipamentoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dataTextField)
                                        .addComponent(horaLabel)
                                        .addComponent(horaTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(reservarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));

        pack();
    } // </editor-fold>//GEN-END:initComponents

    private void cpfTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cpfTextFieldActionPerformed
        String nome = this.cpfTextField.getText();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum CPF digitado", "Erro", JOptionPane.ERROR_MESSAGE, null);
        } else {
            getProfessor();
        }
    } // GEN-LAST:event_cpfTextFieldActionPerformed

    private void reservarButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_reservarButtonActionPerformed
        reservarProfessor();
    } // GEN-LAST:event_reservarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelarButtonActionPerformed
        this.setVisible(false);
    } // GEN-LAST:event_cancelarButtonActionPerformed

    private void buscarCpfButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buscarCpfButtonActionPerformed
        cpfTextFieldActionPerformed(evt);
    } // GEN-LAST:event_buscarCpfButtonActionPerformed

    /*
     *  Variables declaration - do not modify | GEN-BEGIN:variables
     */
    private javax.swing.ButtonGroup alunoProfbuttonGroup;
    private javax.swing.JButton buscarCpfButton;
    protected javax.swing.JButton cancelarButton;
    protected javax.swing.JLabel cpfLabel;
    protected javax.swing.JTextField cpfTextField;
    protected javax.swing.JLabel dataLabel;
    protected javax.swing.JTextField dataTextField;
    protected javax.swing.JLabel equipamentoLabel;
    protected javax.swing.JTextArea equipamentoTextArea;
    protected javax.swing.JLabel horaLabel;
    protected javax.swing.JTextField horaTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JLabel professorLabel;
    protected javax.swing.JTextArea professorTextArea;
    protected javax.swing.JButton reservarButton;
    /*
     *  End of variables declaration//GEN-END:variables
     */
}
