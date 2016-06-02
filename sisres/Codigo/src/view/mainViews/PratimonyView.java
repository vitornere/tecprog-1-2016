/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mainViews;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Parley
 */
public abstract class PratimonyView extends JDialog {

    /**
     * Creates new form ClienteView
     */
    public PratimonyView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
    }

    protected abstract DefaultTableModel fillTable();

    protected abstract void visualizarAction(int index);

    protected abstract void registerAction();

    protected abstract void changeAction(int index);

    protected abstract void excluirAction(int index);

    private void initComponents() {

        panelBotoes = new javax.swing.JPanel();
        patrimonyRegister = new javax.swing.JButton();
        patrimonyChange = new javax.swing.JButton();
        patrimonyDelete = new javax.swing.JButton();
        visualizeBtn = new javax.swing.JButton();
        panelList = new javax.swing.JPanel();
        searchLbl = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        patrimonyTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Salas");

        panelBotoes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        patrimonyRegister.setText("Cadastrar");
        patrimonyRegister.setName("Cadastrar");
        patrimonyRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        patrimonyChange.setText("Alterar");
        patrimonyChange.setName("Alterar");
        patrimonyChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeActionPerformed(evt);
            }
        });

        patrimonyDelete.setText("Excluir");
        patrimonyDelete.setName("Excluir");
        patrimonyDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        visualizeBtn.setText("Visualizar Horarios");
        visualizeBtn.setName("Visualizar Horarios");
        visualizeBtn.setEnabled(true);
        visualizeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotoesLayout = new javax.swing.GroupLayout(panelBotoes);
        panelBotoes.setLayout(panelBotoesLayout);
        panelBotoesLayout.setHorizontalGroup(
        panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
        panelBotoesLayout.createSequentialGroup().addContainerGap().addGroup(
        panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(visualizeBtn, javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing
                         .GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(patrimonyDelete, javax.swing
                         .GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
                         .GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(patrimonyChange, javax.swing
                         .GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
                         .GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(patrimonyRegister, javax.swing
                         .GroupLayout.Alignment.LEADING, javax.swing
                         .GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)).addContainerGap()));
        panelBotoesLayout.setVerticalGroup(
        panelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        panelBotoesLayout.createSequentialGroup().addGap(30, 30, 30).addComponent(patrimonyRegister, javax.swing
        				 .GroupLayout.PREFERRED_SIZE, 80,javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                         .addComponent(patrimonyChange, javax.swing.GroupLayout.PREFERRED_SIZE, 82,javax.swing
                         .GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle
                         .ComponentPlacement.RELATED).addComponent(patrimonyDelete, javax.swing
                         .GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(visualizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81,javax.swing
                         .GroupLayout.PREFERRED_SIZE).addContainerGap(31, Short.MAX_VALUE)));

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListaLayout = new javax.swing.GroupLayout(panelList);
        panelList.setLayout(panelListaLayout);
        panelListaLayout.setHorizontalGroup(
        panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        panelListaLayout.createSequentialGroup().addContainerGap().addComponent(searchLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing
                        .GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle
                        .ComponentPlacement.RELATED).addContainerGap()));
        panelListaLayout.setVerticalGroup(
        panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        panelListaLayout.createSequentialGroup().addContainerGap().addGroup(
        panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28,javax.swing
                        .GroupLayout.PREFERRED_SIZE).addComponent(searchTextField, javax.swing
                        .GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
                        .GroupLayout.PREFERRED_SIZE)).addContainerGap(javax.swing
                        .GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        patrimonyTable.setModel(fillTable());
        patrimonyTable.setName("tabelaPatrimonio");
        patrimonyTable.setRowSelectionAllowed(true);
        // tabelaPatrimonio.setSelectionMode();

        jScrollPane1.setViewportView(patrimonyTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing
        	  .GroupLayout.Alignment.TRAILING,
        layout.createSequentialGroup().addContainerGap().addGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panelList, javax.swing
        	   .GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(panelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing
               .GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addContainerGap().addGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addComponent(panelList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing
        	  .GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle
        	  .ComponentPlacement.RELATED).addComponent(jScrollPane1, javax.swing
        	  .GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(panelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing
              .GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void visualizeActionPerformed(java.awt.event.ActionEvent evt) {
        int index = this.patrimonyTable.getSelectedRow();
        
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        else {
        	// Nothing to do
        }
        
        visualizarAction(index);
    }

    protected void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_pesquisarTextFieldActionPerformed
        String nome = this.searchTextField.getText();
        
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum texto digitado", "Erro", JOptionPane.ERROR_MESSAGE, null);
        } else {
            JOptionPane.showMessageDialog(this, "Funciona", "Teste", JOptionPane.WARNING_MESSAGE, null);
        }
    }

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cadastrarActionPerformed

        registerAction();
    }

    private void changeActionPerformed(java.awt.event.ActionEvent evt) {
        int index = this.patrimonyTable.getSelectedRow();
        
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        else {
        	// Nothing to do
        }

        changeAction(index);
    }

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_excluirActionPerformed

        int index = this.patrimonyTable.getSelectedRow();
        
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha!", "Erro", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        else {
        	// Nothing to do
        }

        excluirAction(index);

    }// GEN-LAST:event_excluirActionPerformed

    protected javax.swing.JButton patrimonyChange;
    protected javax.swing.JButton patrimonyRegister;
    protected javax.swing.JButton patrimonyDelete;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JPanel panelBotoes;
    protected javax.swing.JPanel panelList;
    protected javax.swing.JLabel searchLbl;
    protected javax.swing.JTextField searchTextField;
    protected javax.swing.JTable patrimonyTable;
    protected javax.swing.JButton visualizeBtn;
}
