package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Setor de Cliente");
		lblNewLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		lblNewLabel.setBounds(135, 11, 162, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Selecione a op\u00E7\u00E3o desejada");
		lblNewLabel_1.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(127, 47, 194, 14);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Cadastrar novo Cliente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarCliente novoFrame = new CadastrarCliente();
				novoFrame.dispose();
				String[] string = {};
				CadastrarCliente.main(string);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(115, 100, 200, 50);
		contentPane.add(btnNewButton);

		/*
		 * JButton btnNewButton_1 = new JButton("Listar Caixas cadastrados");
		 * btnNewButton_1.setVisible(false);
		 * btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 * btnNewButton_1.setBounds(115, 133, 205, 23);
		 * contentPane.add(btnNewButton_1);
		 * 
		 * JButton btnNewButton_2 = new JButton("Excluir Caixa");
		 * btnNewButton_2.setVisible(false);
		 * btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 * btnNewButton_2.setBounds(115, 171, 205, 23);
		 * contentPane.add(btnNewButton_2);
		 */

		JButton btnNewButton_3 = new JButton("Voltar para o menu Inicial");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				FrameInicial frameComeco = new FrameInicial();
				frameComeco.dispose();
				FrameInicial.main(null);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3.setBounds(115, 190, 205, 50);
		contentPane.add(btnNewButton_3);
	}

}
