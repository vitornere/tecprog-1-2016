package view;

import view.mainViews.AlunoView;
import view.mainViews.ClienteView;
import view.mainViews.EquipamentoView;
import view.mainViews.PatrimonioView;
import view.mainViews.TeacherView;
import view.mainViews.RoomView;

/**i
 * 
 * @author Parley
 */
@SuppressWarnings("serial") public class Main2 extends javax.swing.JFrame {

    /**
     * Creates new form Main2
     */
    public Main2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fundoLbl = new javax.swing.JLabel();
        panelreservation1 = new javax.swing.JPanel();
        roomBtn = new javax.swing.JButton();
        equipmentBtn = new javax.swing.JButton();
        panelreservation = new javax.swing.JPanel();
        teacherBtn = new javax.swing.JButton();
        studentBnt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SisRES");
        setName("Main"); // NOI18N
        setResizable(false);

        fundoLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fundoLbl.setText("SisRES");

        panelreservation1.setBorder(javax.swing.BorderFactory.createTitledBorder("Reserva"));

        roomBtn.setText("Sala");
        roomBtn.setName("Sala");
        roomBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomBtnActionPerformed(evt);
            }
        });

        equipmentBtn.setText("Equipamento");
        equipmentBtn.setName("Equipamento");
        equipmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipmentBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelreservation1Layout = new javax.swing.GroupLayout(panelreservation1);
        panelreservation1.setLayout(panelreservation1Layout);
        panelreservation1Layout.setHorizontalGroup(panelreservation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        panelreservation1Layout
                                .createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(roomBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equipmentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                .addContainerGap()));
        panelreservation1Layout.setVerticalGroup(panelreservation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        panelreservation1Layout
                                .createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        panelreservation1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(roomBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(equipmentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        panelreservation.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro"));

        teacherBtn.setText("Professor");
        teacherBtn.setName("Professor");
        teacherBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherBtnActionPerformed(evt);
            }
        });

        studentBnt.setText("Aluno");
        studentBnt.setName("Aluno");
        studentBnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentBntActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelreservationLayout = new javax.swing.GroupLayout(panelreservation);
        panelreservation.setLayout(panelreservationLayout);
        panelreservationLayout.setHorizontalGroup(panelreservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        panelreservationLayout
                                .createSequentialGroup()
                                .addContainerGap()
                                .addComponent(teacherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentBnt, javax.swing.GroupLayout.PREFERRED_SIZE, 105,
                                        javax.swing.GroupLayout.PREFERRED_SIZE).addGap(14, 14, 14)));
        panelreservationLayout.setVerticalGroup(panelreservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        panelreservationLayout
                                .createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        panelreservationLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(teacherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(studentBnt, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelreservation1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelreservation, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()
                                .addGap(191, 191, 191)
                                .addComponent(fundoLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE).addGap(185, 185, 185)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(panelreservation, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelreservation1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(111, 111, 111)
                        .addComponent(fundoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(168, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roomBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_salaBtnActionPerformed
        // TODO add your handling code here:
        PatrimonioView room = new RoomView(this, true);
        room.setResizable(false);
        room.setVisible(true);
    }// GEN-LAST:event_salaBtnActionPerformed

    private void equipmentBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_equipamentoBtnActionPerformed

        PatrimonioView equipment = new EquipamentoView(this, true);
        equipment.setResizable(false);
        equipment.setVisible(true);

    }// GEN-LAST:event_equipamentoBtnActionPerformed

    private void teacherBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_teacherBtnActionPerformed
        // TODO add your handling code here:
        ClienteView client = new TeacherView(this, true);
        client.setResizable(false);
        client.setVisible(true);
    }// GEN-LAST:event_teacherBtnActionPerformed

    private void studentBntActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_studentBntActionPerformed
        // TODO add your handling code here:
        ClienteView client = new AlunoView(this, true);
        client.setResizable(false);
        client.setVisible(true);
    }// GEN-LAST:event_studentBntActionPerformed

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed"
        // desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase
         * /tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton studentBnt;
    private javax.swing.JButton equipmentBtn;
    private javax.swing.JLabel fundoLbl;
    private javax.swing.JPanel panelreservation;
    private javax.swing.JPanel panelreservation1;
    private javax.swing.JButton teacherBtn;
    private javax.swing.JButton roomBtn;
    // End of variables declaration//GEN-END:variables
}
