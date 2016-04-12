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

public class CadastrarBalconista extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRg;
	private JTextField txtCpf;
	private JTextField txtDigito;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JTextField txtSenha;
	private JTextField txtSenhaFP;
	private JTextField txtCodigo;
	private JTextField txtComissao;
	private JTextField txtHoras;
	private JLabel label;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarBalconista frame = new CadastrarBalconista();
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
	public CadastrarBalconista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTopo = new JLabel("Cadastro de Balconista");
		lblTopo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTopo.setBounds(145, 5, 125, 14);
		contentPane.add(lblTopo);

		JLabel lblRg = new JLabel("RG:");
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRg.setBounds(48, 30, 20, 14);
		contentPane.add(lblRg);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(new Rectangle(0, 47, 0, 0));
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(44, 48, 24, 14);
		contentPane.add(lblCpf);

		JLabel lblDigitoCpf = new JLabel("-");
		lblDigitoCpf.setBounds(new Rectangle(0, 47, 0, 0));
		lblDigitoCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigitoCpf.setBounds(150, 48, 20, 16);
		contentPane.add(lblDigitoCpf);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(32, 66, 36, 14);
		contentPane.add(lblNome);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSobrenome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSobrenome.setBounds(1, 84, 67, 14);
		contentPane.add(lblSobrenome);

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEndereo.setBounds(12, 102, 56, 14);
		contentPane.add(lblEndereo);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefone.setBounds(15, 120, 53, 14);
		contentPane.add(lblTelefone);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(30, 138, 38, 14);
		contentPane.add(lblSenha);

		JLabel lblSenhaFarmaciaPopular = new JLabel("Senha F.P.:");
		lblSenhaFarmaciaPopular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenhaFarmaciaPopular.setBounds(5, 156, 63, 14);
		contentPane.add(lblSenhaFarmaciaPopular);

		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigo.setBounds(27, 174, 41, 14);
		contentPane.add(lblCodigo);

		JLabel lblComissao = new JLabel("Comiss\u00E3o:");
		lblComissao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblComissao.setBounds(15, 192, 55, 14);
		contentPane.add(lblComissao);

		JLabel lblHoras = new JLabel("Horas/S:");
		lblHoras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHoras.setBounds(22, 210, 46, 14);
		contentPane.add(lblHoras);

		label = new JLabel("");
		label.setVisible(false);
		label.setBounds(210, 239, 202, 23);
		contentPane.add(label);

		txtRg = new JTextField();
		txtRg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRg.setBounds(70, 30, 130, 16);
		contentPane.add(txtRg);
		txtRg.setColumns(10);

		txtCpf = new JTextField();
		txtCpf.setBounds(new Rectangle(0, 47, 0, 0));
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCpf.setBounds(70, 48, 75, 16);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(256, 28, 156, 205);
		contentPane.add(scrollPane);

		final JTextArea textArea = new JTextArea();
		textArea.setVisible(false);
		scrollPane.setViewportView(textArea);

		txtDigito = new JTextField();
		txtDigito.setBounds(new Rectangle(0, 47, 0, 0));
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDigito.setBounds(160, 48, 40, 16);
		contentPane.add(txtDigito);
		txtDigito.setColumns(10);

		txtNome = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setBounds(70, 66, 130, 16);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtSobrenome = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSobrenome.setBounds(70, 84, 130, 16);
		contentPane.add(txtSobrenome);
		txtSobrenome.setColumns(10);

		txtEndereco = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEndereco.setBounds(70, 102, 130, 16);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		txtTelefone = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTelefone.setBounds(70, 120, 130, 16);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);

		txtSenha = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSenha.setBounds(70, 138, 130, 16);
		contentPane.add(txtSenha);
		txtSenha.setColumns(10);

		txtSenhaFP = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSenhaFP.setBounds(70, 156, 130, 16);
		contentPane.add(txtSenhaFP);
		txtSenhaFP.setColumns(10);

		txtCodigo = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCodigo.setBounds(70, 174, 130, 16);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtComissao = new JTextField();
		txtComissao.setBounds(70, 192, 130, 16);
		contentPane.add(txtComissao);
		txtComissao.setColumns(10);

		txtHoras = new JTextField();
		txtHoras.setBounds(70, 210, 130, 16);
		contentPane.add(txtHoras);
		txtHoras.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}

			private void cadastrar() {

				label.setVisible(true);
				label.setText(txtNome.getText() + " foi cadastrado com sucesso!");

				textArea.setVisible(true);
				scrollPane.setVisible(true);
				textArea.setText("   Dados   " + "\nRg: " + txtRg.getText() + "\nCpf: " + txtCpf.getText() + "-"
						+ txtDigito.getText() + "\nNome: " + txtNome.getText() + " " + txtSobrenome.getText()
						+ "\nEndereço: " + txtEndereco.getText() + "\nTelefone: " + txtTelefone.getText()
						+ "\nSenha: " + txtSenha.getText() + "\nSenha F.P.: " + txtSenhaFP.getText()
						+ "\nCódigo: " + txtCodigo.getText() + "\nComissao: " + txtComissao.getText()
						+ "\nHoras Mensais de trabalho: " + txtHoras.getText());

			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(60, 238, 150, 20);
		contentPane.add(btnCadastrar);

		JButton btnNewButton_1 = new JButton("< Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Balconista frameBalconista = new Balconista();
				frameBalconista.dispose();
				Balconista.main(null);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(1, 0, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
