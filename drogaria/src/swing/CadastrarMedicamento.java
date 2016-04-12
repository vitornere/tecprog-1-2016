package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class CadastrarMedicamento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtFabricante;
	private JTextField txtRecomendacao;
	private JTextField txtValidade;
	private JTextField txtTipo;
	private JTextField txtPosologia;
	private JTextField txtRegistro;
	private JLabel label;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarMedicamento frame = new CadastrarMedicamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastrarMedicamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTopo = new JLabel("Cadastro de Medicamento");
		lblTopo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTopo.setBounds(145, 5, 150, 14);
		contentPane.add(lblTopo);

		JLabel lblRg = new JLabel("Nome:");
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRg.setBounds(54, 30, 36, 14);
		contentPane.add(lblRg);

		JLabel lblCpf = new JLabel("Fabricante:");
		lblCpf.setBounds(new Rectangle(0, 47, 0, 0));
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(28, 55, 67, 14);
		contentPane.add(lblCpf);

		/*
		 * JLabel lblDigitoCpf = new JLabel("-");
		 * lblDigitoCpf.setBounds(new Rectangle(0, 47, 0, 0));
		 * lblDigitoCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 * lblDigitoCpf.setBounds(150, 55, 20, 16);
		 * contentPane.add(lblDigitoCpf);
		 */

		JLabel lblNome = new JLabel("Recomendação:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(0, 80, 90, 14);
		contentPane.add(lblNome);

		JLabel lblSobrenome = new JLabel("Validade:");
		lblSobrenome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSobrenome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSobrenome.setBounds(23, 105, 67, 14);
		contentPane.add(lblSobrenome);

		JLabel lblEndereo = new JLabel("Tipo:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEndereo.setBounds(62, 127, 28, 14);
		contentPane.add(lblEndereo);

		JLabel lblTelefone = new JLabel("Posologia:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefone.setBounds(34, 152, 56, 14);
		contentPane.add(lblTelefone);

		JLabel lblEmail = new JLabel("Registro:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(42, 177, 48, 14);
		contentPane.add(lblEmail);

		label = new JLabel("");
		label.setVisible(false);
		label.setBounds(210, 239, 202, 23);
		contentPane.add(label);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setBounds(95, 30, 130, 16);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtFabricante = new JTextField();
		txtFabricante.setBounds(new Rectangle(0, 47, 0, 0));
		txtFabricante.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFabricante.setBounds(95, 55, 130, 16);
		contentPane.add(txtFabricante);
		txtFabricante.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(256, 28, 156, 205);
		contentPane.add(scrollPane);

		final JTextArea textArea = new JTextArea();
		textArea.setVisible(false);
		scrollPane.setViewportView(textArea);

		txtRecomendacao = new JTextField();
		txtRecomendacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRecomendacao.setBounds(95, 79, 130, 16);
		contentPane.add(txtRecomendacao);
		txtRecomendacao.setColumns(10);

		txtValidade = new JTextField();
		txtValidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtValidade.setBounds(95, 105, 130, 16);
		contentPane.add(txtValidade);
		txtValidade.setColumns(10);

		txtTipo = new JTextField();
		txtTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTipo.setBounds(95, 127, 130, 16);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);

		txtPosologia = new JTextField();
		txtPosologia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPosologia.setBounds(95, 152, 130, 16);
		contentPane.add(txtPosologia);
		txtPosologia.setColumns(10);

		txtRegistro = new JTextField();
		txtRegistro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRegistro.setBounds(95, 177, 130, 16);
		contentPane.add(txtRegistro);
		txtRegistro.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}

			private void cadastrar() {

				label.setVisible(true);
				label.setText(txtNome.getText() + " foi cadastrado com sucesso!");

				// Para não extrapolar o label

				textArea.setVisible(true);
				scrollPane.setVisible(true);
				textArea.setText("   Dados   " + "\nNome: " + txtNome.getText() + "\nFabricante: "
						+ txtFabricante.getText() + "\nRecomendação: " + txtRecomendacao.getText()
						+ "\nValidade: " + txtValidade.getText() + "\nTipo: " + txtTipo.getText()
						+ "\nPosologia: " + txtPosologia.getText() + "\nRegistro: " + txtRegistro.getText());

			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(60, 238, 150, 20);
		contentPane.add(btnCadastrar);

		JButton btnNewButton_1 = new JButton("< Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Medicamento frameMedicamento = new Medicamento();
				frameMedicamento.dispose();
				Medicamento.main(null);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(1, 0, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
