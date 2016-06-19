/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainViews;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Client;

/**
 * 
 * @author Parley
 */
public abstract class ClientView extends javax.swing.JDialog {

    /**
     * Creates new form ClienteView
     */
    public ClientView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public abstract Iterator getIterator();

    public abstract void registerAction();

    public abstract void alterarAction(int index);

    public abstract void excluirAction();

    protected Vector<String> fillDataVector(Client cliente) {

        Vector<String> nomesTabela = new Vector<String>();

        if (cliente == null) {
            return null;
        }
        else {
        	// Nothing to do
        }

        nomesTabela.add(cliente.getIdRegister());
        nomesTabela.add(cliente.getNamePerson());
        nomesTabela.add(cliente.getPhonePerson());
        nomesTabela.add(cliente.getCpfPerson());
        nomesTabela.add(cliente.getEmailPerson());

        return nomesTabela;

    }

    protected DefaultTableModel fillTable() {
        DefaultTableModel table = new DefaultTableModel();

        Iterator<Client> i = getIterator();

        table.addColumn("Matricula");
        table.addColumn("Nome");
        table.addColumn("Telefone");
        table.addColumn("CPF");
        table.addColumn("E-mail");

        while (i.hasNext()) {
            // int col, row = 0;
            Client client = i.next();
            table.addRow(fillDataVector(client));
        }

        return table;
    }

    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotoes = new javax.swing.JPanel();
        registerBtn = new javax.swing.JButton();
        changeBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        panelLista = new javax.swing.JPanel();
        searchLbl = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cliente");

        panelBotoes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        registerBtn.setText("Cadastrar");
        registerBtn.setName("Cadastrar");
        
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarBtnActionPerformed(evt);
            }
        });

        changeBtn.setText("Alterar");
        changeBtn.setName("Alterar");
        
        changeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("Excluir");
        deleteBtn.setName("Excluir");
        
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotoesLayout = new javax.swing.GroupLayout(panelBotoes);
        panelBotoes.setLayout(panelBotoesLayout);
        panelBotoesLayout.setHorizontalGroup(panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout
        				 .Alignment.LEADING).addGroup(
        panelBotoesLayout.createSequentialGroup().addContainerGap().addGroup(
        panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE,javax
                         .swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) .addComponent(changeBtn, javax
                         .swing.GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(registerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 135,Short.MAX_VALUE))
                         .addContainerGap()));
        panelBotoesLayout.setVerticalGroup(
        panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        panelBotoesLayout.createSequentialGroup().addGap(65, 65, 65)
        				 .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80,javax.swing
        				 .GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle
        				 .ComponentPlacement.UNRELATED).addComponent(changeBtn, javax.swing
        				 .GroupLayout.PREFERRED_SIZE, 82,javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                         .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80,javax
                         .swing.GroupLayout.PREFERRED_SIZE).addContainerGap(78, Short.MAX_VALUE)));

        searchLbl.setText("Digite a matricula desejada: ");

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListaLayout = new javax.swing.GroupLayout(panelLista);
        panelLista.setLayout(panelListaLayout);
        panelListaLayout.setHorizontalGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout
        				.Alignment.LEADING).addGroup(
        panelListaLayout.createSequentialGroup().addContainerGap().addComponent(searchLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchTextField)));
        panelListaLayout.setVerticalGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout
        				.Alignment.LEADING).addGroup(
        panelListaLayout.createSequentialGroup().addContainerGap().addGroup(
        panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28,javax.swing
                        .GroupLayout.PREFERRED_SIZE).addComponent(searchTextField, javax.swing
                        .GroupLayout.PREFERRED_SIZE,javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
                        .GroupLayout.PREFERRED_SIZE)).addContainerGap(javax.swing
                        .GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        clientTable.setModel(fillTable());
        clientTable.setName("tabelaCliente");
        jScrollPane1.setViewportView(clientTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing
        	  .GroupLayout.Alignment.TRAILING,
        layout.createSequentialGroup().addContainerGap().addGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(panelLista, javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing
              .GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jScrollPane1, javax.swing
              .GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle
              .ComponentPlacement.RELATED).addComponent(panelBotoes, javax.swing
              .GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
        
        javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        
        layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addContainerGap().addGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addComponent(panelLista, javax.swing
        	  .GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353,javax.swing
              .GroupLayout.PREFERRED_SIZE)).addComponent(panelBotoes, javax.swing
              .GroupLayout.PREFERRED_SIZE,javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
              .GroupLayout.PREFERRED_SIZE))
              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisarTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_pesquisarTextFieldActionPerformed
        String nome = this.searchTextField.getText();
        
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum texto digitado", "Erro", JOptionPane.ERROR_MESSAGE, null);
        } else {
            JOptionPane.showMessageDialog(this, "Funciona", "Teste", JOptionPane.WARNING_MESSAGE, null);
        }
    }// GEN-LAST:event_pesquisarTextFieldActionPerformed

    private void cadastrarBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cadastrarBtnActionPerformed
        // TODO add your handling code here:
        registerAction();

    }// GEN-LAST:event_cadastrarBtnActionPerformed

    private void alterarBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_alterarBtnActionPerformed
        // TODO add your handling code here:

        int index = this.clientTable.getSelectedRow();
        
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        else {
        	// Nothing to do
        }

        alterarAction(index);

    }// GEN-LAST:event_alterarBtnActionPerformed

    private void excluirBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_excluirBtnActionPerformed
        excluirAction();

    }// GEN-LAST:event_excluirBtnActionPerformed
     // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JButton changeBtn;
    private javax.swing.JButton registerBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelLista;
    private javax.swing.JLabel searchLbl;
    private javax.swing.JTextField searchTextField;
    protected javax.swing.JTable clientTable;
    // End of variables declaration//GEN-END:variables
}
