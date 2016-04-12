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

//O programa deve ser iniciado por aqui!

public class FrameInicial extends JFrame {

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
					FrameInicial frame = new FrameInicial();
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
	public FrameInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sistema de Ger\u00EAncia de Drogaria");
		lblNewLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 14));
		lblNewLabel.setBounds(79, 11, 267, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Caro Administrador, escolha a op\u00E7\u00E3o desejada.");
		lblNewLabel_1.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(45, 51, 350, 20);
		contentPane.add(lblNewLabel_1);

		JButton btnBalconista = new JButton("Balconista");
		btnBalconista.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		btnBalconista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Balconista novoFrame = new Balconista();
				novoFrame.dispose();
				String[] string = {};
				Balconista.main(string);
			}
		});
		btnBalconista.setBounds(35, 100, 150, 50);
		contentPane.add(btnBalconista);

		JButton btnCliente = new JButton("Cliente");
		btnCliente.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente novoFrame = new Cliente();
				novoFrame.dispose();
				String[] string = {};
				Cliente.main(string);
			}
		});
		btnCliente.setBounds(35, 175, 150, 50);
		contentPane.add(btnCliente);

		JButton btnCaixa = new JButton("Caixa");
		btnCaixa.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		btnCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Caixa novoFrame = new Caixa();
				novoFrame.dispose();
				String[] string = {};
				Caixa.main(string);
			}
		});
		btnCaixa.setBounds(250, 100, 150, 50);
		contentPane.add(btnCaixa);

		JButton btnMedicamento = new JButton("Medicamento");
		btnMedicamento.setFont(new Font("Trajan Pro", Font.PLAIN, 12));
		btnMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Medicamento novoFrame = new Medicamento();
				novoFrame.dispose();
				String[] string = {};
				Medicamento.main(string);
			}
		});
		btnMedicamento.setBounds(250, 175, 150, 50);
		contentPane.add(btnMedicamento);
	}
}
