/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.reservasSalas;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Student;
import model.Professor;
import model.Classroom;
import control.StudentRegister;
import control.ProfessorRegister;
import control.ReserveClassroomForStudentRegister;
import control.ReserveClassroomForProfessorRegister;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

/**
 * 
 * @author Parley
 */
public abstract class ReservaSalaView extends javax.swing.JDialog {

    protected final int ALUNO = 1;
    protected final int PROF = 2;
    protected final int ERRO = -1;
    protected ReserveClassroomForStudentRegister instanceAluno;
    protected ReserveClassroomForProfessorRegister instanceProf;
    protected Classroom sala;
    protected Student aluno;
    protected Professor prof;

    public ReservaSalaView(java.awt.Frame parent, boolean modal) throws SQLException, PatrimonyException, PatrimonyException,
            ClientException, ReserveException {
        super(parent, modal);
        this.instanceProf = ReserveClassroomForProfessorRegister.getReserveClassroomForProfessor();
        this.instanceAluno = ReserveClassroomForStudentRegister.getReserveClassroomForStudent();
        initComponents();
        this.bucarCpfButton.setName("BuscarCpfButton");

    }
    
    abstract protected void verificarAction();

    abstract protected void reservarAluno();

    abstract protected void reservarProfessor();

    abstract protected void professorRadioButtonAction();

    abstract protected void alunoRadioButtonAction();

    protected void getAluno() {
        try {

            Vector<Student> alunos = StudentRegister.getNewStudent().searchCpfStudent(this.cpfTextField.getText());
            if (alunos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Aluno nao Cadastrado." + " Digite o CPF correto ou cadastre o aluno desejado",
                        "Erro", JOptionPane.ERROR_MESSAGE, null);
                this.alunoTextArea.setText("");
                this.aluno = null;
                return;
            }
            this.aluno = alunos.firstElement();
            this.alunoTextArea.setText(aluno.toString());
        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    protected void getProfessor() {
        try {
            Vector<Professor> professor = ProfessorRegister.getNewProfessor().searchCpfProfessor(this.cpfTextField.getText());
            if (professor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Professor nao Cadastrado."
                        + " Digite o CPF correto ou cadastre o professor desejado", "Erro", JOptionPane.ERROR_MESSAGE, null);
                this.alunoTextArea.setText("");
                this.prof = null;
                return;
            }
            this.prof = professor.firstElement();
            this.alunoTextArea.setText(professor.firstElement().toString());
        } catch (ClientException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    protected int getManterInstance() {
        if (instanceAluno != null) {
            return ALUNO;
        } else if (instanceProf != null) {
            return PROF;
        }
        return ERRO;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")// <editor-fold defaultstate="collapsed"
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                alunoProfbuttonGroup = new javax.swing.ButtonGroup();
                salaLabel = new javax.swing.JLabel();
                alunoLabel = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                professorRadioButton = new javax.swing.JRadioButton();
                alunoRadioButton = new javax.swing.JRadioButton();
                cpfLabel = new javax.swing.JLabel();
                cpfTextField = new javax.swing.JTextField();
                finalidadeTextLabel = new javax.swing.JLabel();
                finalidadeTextField = new javax.swing.JTextField();
                qntCadeirasLabel = new javax.swing.JLabel();
                qntCadeirasTxtField = new javax.swing.JTextField();
                qntCadeirasReservadasLbl = new javax.swing.JLabel();
                qntCadeirasReservadasTextField = new javax.swing.JTextField();
                dataLabel = new javax.swing.JLabel();
                horaLabel = new javax.swing.JLabel();
                horaTextField = new javax.swing.JTextField();
                reservarButton = new javax.swing.JButton();
                cancelarButton = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                alunoTextArea = new javax.swing.JTextArea();
                jScrollPane2 = new javax.swing.JScrollPane();
                salaTextArea = new javax.swing.JTextArea();
                dataTextField = new javax.swing.JTextField();
                bucarCpfButton = new javax.swing.JButton();
                verificarCadeiraButton = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("ReservaPatrimonio");
                setName("ReservaPatrimonio"); // NOI18N
                setResizable(false);

                salaLabel.setText("Sala: ");
                salaLabel.setName("SalaLabel"); // NOI18N

                alunoLabel.setText("Aluno:");
                alunoLabel.setName("AlunoLabel"); // NOI18N

                alunoProfbuttonGroup.add(professorRadioButton);
                professorRadioButton.setText("Professor");
                professorRadioButton.setName("professorRadioButton"); // NOI18N
                professorRadioButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                professorRadioButtonActionPerformed(evt);
                        }
                });

                alunoProfbuttonGroup.add(alunoRadioButton);
                alunoRadioButton.setText("Aluno");
                alunoRadioButton.setName("alunoRadioButton"); // NOI18N
                alunoRadioButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                alunoRadioButtonActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(alunoRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(professorRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(professorRadioButton)
                                        .addComponent(alunoRadioButton))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                cpfLabel.setText("Digite o CPF desejado :");
                cpfLabel.setName("CpfLabel"); // NOI18N

                cpfTextField.setName("CPF"); // NOI18N
                cpfTextField.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cpfTextFieldActionPerformed(evt);
                        }
                });

                finalidadeTextLabel.setText("Finalidade: ");
                finalidadeTextLabel.setName("FinalidadeLabel"); // NOI18N

                finalidadeTextField.setName("Finalidade"); // NOI18N

                qntCadeirasLabel.setText("Quantidade de Cadeiras Disponiveis: ");
                qntCadeirasLabel.setName("QuantidadeCadeirasDisponiveisLabel"); // NOI18N

                qntCadeirasTxtField.setEditable(false);
                qntCadeirasTxtField.setBackground(new java.awt.Color(200, 208, 254));
                qntCadeirasTxtField.setName("Quantidade de Cadeiras Disponiveis"); // NOI18N

                qntCadeirasReservadasLbl.setText("Quantidade de Cadeiras Reservadas: ");
                qntCadeirasReservadasLbl.setName("QuantidadeCadeirasReservadasLabel"); // NOI18N

                qntCadeirasReservadasTextField.setBackground(new java.awt.Color(200, 208, 254));
                qntCadeirasReservadasTextField.setName("Quantidade de Cadeiras Reservadas"); // NOI18N

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

                alunoTextArea.setEditable(false);
                alunoTextArea.setBackground(new java.awt.Color(200, 208, 254));
                alunoTextArea.setColumns(20);
                alunoTextArea.setRows(5);
                alunoTextArea.setName("AlunoTextArea"); // NOI18N
                jScrollPane1.setViewportView(alunoTextArea);

                salaTextArea.setEditable(false);
                salaTextArea.setBackground(new java.awt.Color(200, 208, 254));
                salaTextArea.setColumns(20);
                salaTextArea.setRows(5);
                salaTextArea.setName("salaTextArea"); // NOI18N
                jScrollPane2.setViewportView(salaTextArea);

                dataTextField.setEditable(false);
                dataTextField.setBackground(new java.awt.Color(200, 208, 254));
                dataTextField.setName("DiaTextField"); // NOI18N

                bucarCpfButton.setText("Buscar CPF");
                bucarCpfButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                bucarCpfButtonActionPerformed(evt);
                        }
                });

                verificarCadeiraButton.setText("Verificar Cadeiras Disponiveis");
                verificarCadeiraButton.setName("VerificarCadeirasButton"); // NOI18N
                verificarCadeiraButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                verificarCadeiraButtonActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(199, 199, 199)
                                                .addComponent(reservarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(qntCadeirasReservadasLbl)
                                                                .addGap(41, 41, 41)
                                                                .addComponent(qntCadeirasReservadasTextField))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(salaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(finalidadeTextLabel)
                                                                        .addComponent(alunoLabel))
                                                                .addGap(7, 7, 7)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(finalidadeTextField)
                                                                        .addComponent(jScrollPane1)
                                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(qntCadeirasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(qntCadeirasTxtField))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(cpfLabel)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(cpfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(bucarCpfButton)))
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(dataLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(dataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(horaLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(horaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(verificarCadeiraButton, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
                                                .addContainerGap())))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cpfLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cpfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bucarCpfButton))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addComponent(alunoLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(salaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(finalidadeTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(finalidadeTextField)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dataTextField)
                                        .addComponent(horaLabel)
                                        .addComponent(horaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(verificarCadeiraButton))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(qntCadeirasReservadasLbl)
                                        .addComponent(qntCadeirasReservadasTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(qntCadeirasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(qntCadeirasTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(reservarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void verificarCadeiraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verificarCadeiraButtonActionPerformed
               verificarAction();
        }//GEN-LAST:event_verificarCadeiraButtonActionPerformed

    private void cpfTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cpfTextFieldActionPerformed
        String nome = this.cpfTextField.getText();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum CPF digitado", "Erro", JOptionPane.ERROR_MESSAGE, null);
            this.alunoTextArea.setText("");
        } else {
            switch (getManterInstance()) {
            case ALUNO:
                getAluno();
                break;
            case PROF:
                getProfessor();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Selecione Aluno ou Professor", "Erro", JOptionPane.ERROR_MESSAGE, null);
            }
        }
    }// GEN-LAST:event_cpfTextFieldActionPerformed

    private void alunoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_alunoRadioButtonActionPerformed
        alunoRadioButtonAction();

    }// GEN-LAST:event_alunoRadioButtonActionPerformed

    private void professorRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_professorRadioButtonActionPerformed
        professorRadioButtonAction();
    }// GEN-LAST:event_professorRadioButtonActionPerformed

    private void reservarButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_reservarButtonActionPerformed
        switch (getManterInstance()) {
        case ALUNO:
            reservarAluno();
            break;
        case PROF:
            reservarProfessor();
            break;
        default:
            JOptionPane.showMessageDialog(this, "Selecione Aluno ou Professor", "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }// GEN-LAST:event_reservarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelarButtonActionPerformed
        this.setVisible(false);
    }// GEN-LAST:event_cancelarButtonActionPerformed

    private void bucarCpfButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bucarCpfButtonActionPerformed
        cpfTextFieldActionPerformed(evt);
    }// GEN-LAST:event_bucarCpfButtonActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        protected javax.swing.JLabel alunoLabel;
        private javax.swing.ButtonGroup alunoProfbuttonGroup;
        protected javax.swing.JRadioButton alunoRadioButton;
        protected javax.swing.JTextArea alunoTextArea;
        protected javax.swing.JButton bucarCpfButton;
        protected javax.swing.JButton cancelarButton;
        protected javax.swing.JLabel cpfLabel;
        protected javax.swing.JTextField cpfTextField;
        protected javax.swing.JLabel dataLabel;
        protected javax.swing.JTextField dataTextField;
        protected javax.swing.JTextField finalidadeTextField;
        protected javax.swing.JLabel finalidadeTextLabel;
        protected javax.swing.JLabel horaLabel;
        protected javax.swing.JTextField horaTextField;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        protected javax.swing.JRadioButton professorRadioButton;
        protected javax.swing.JLabel qntCadeirasLabel;
        protected javax.swing.JLabel qntCadeirasReservadasLbl;
        protected javax.swing.JTextField qntCadeirasReservadasTextField;
        protected javax.swing.JTextField qntCadeirasTxtField;
        protected javax.swing.JButton reservarButton;
        protected javax.swing.JLabel salaLabel;
        protected javax.swing.JTextArea salaTextArea;
        protected javax.swing.JButton verificarCadeiraButton;
        // End of variables declaration//GEN-END:variables
}
