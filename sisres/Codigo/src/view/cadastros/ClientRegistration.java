/**
 * Name:ClienteRegistration.java
 * Class is a super class for adding a new client, which can be
 * student and professor.
 */

package view.cadastros;

public abstract class ClientRegistration extends javax.swing.JDialog {

	/**
	 * Method that builds customer registration form.
	 * 
	 * @param parent
	 * @param modal
	 */
	
	public ClientRegistration(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();

	}

	/**
	 * constructor that initializes in the customer registration form.
	 */
	
	public abstract void registrationAction();

	/**
	 * Method that initializes the customer registration form components.
	 */
	private void initComponents() {

		nomeLbl = new javax.swing.JLabel();
		matriculaLbl = new javax.swing.JLabel();
		cpfLbl = new javax.swing.JLabel();
		emailLbl = new javax.swing.JLabel();
		telefoneLbl = new javax.swing.JLabel();
		nameTxtField = new javax.swing.JTextField();
		enrollmentTxtField = new javax.swing.JTextField();
		cpfTxtField = new javax.swing.JTextField();
		emailTxtField = new javax.swing.JTextField();
		phoneTxtField = new javax.swing.JTextField();
		btnRegistration = new javax.swing.JButton();
		cancelBtn = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Cadastro");
		setResizable(false);

		nomeLbl.setText("Nome: ");

		matriculaLbl.setText("Matricula: ");

		cpfLbl.setText("CPF:");

		emailLbl.setText("E-mail: ");

		telefoneLbl.setText("Telefone: ");

		nameTxtField.setName("Nome");

		enrollmentTxtField.setName("Matricula");
		enrollmentTxtField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

		cpfTxtField.setName("CPF");

		emailTxtField.setName("E-mail");

		phoneTxtField.setName("Telefone");

		btnRegistration.setText("Cadastrar");
		btnRegistration.setName("Cadastrar");
		btnRegistration.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cadastroBtnActionPerformed(evt);
			}
		});

		cancelBtn.setText("Cancelar");
		cancelBtn.setName("Cancelar");
		cancelBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelBtnActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(telefoneLbl, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(emailLbl, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cpfLbl, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(matriculaLbl, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(nomeLbl, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(nameTxtField)
										.addComponent(enrollmentTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 453,
												Short.MAX_VALUE)
										.addComponent(cpfTxtField, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
										.addComponent(emailTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 453,
												Short.MAX_VALUE)
										.addComponent(phoneTxtField, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(btnRegistration)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cancelBtn)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(nomeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
										javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(matriculaLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 19,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(enrollmentTxtField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(cpfLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cpfTxtField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(emailLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(emailTxtField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(telefoneLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(phoneTxtField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnRegistration).addComponent(cancelBtn))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}
	
	/**
	 * Method adds the event on the button to register the client
	 * 
	 * @param evt
	 */

	private void cadastroBtnActionPerformed(java.awt.event.ActionEvent evt) {
		registrationAction();
	}
	
	/**
	 * Method adds the event on the button to cancel the customer registration
	 * 
	 * @param evt
	 */

	private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);
	}

	protected javax.swing.JButton btnRegistration;
	private javax.swing.JButton cancelBtn;
	private javax.swing.JLabel cpfLbl;
	protected javax.swing.JTextField cpfTxtField;
	private javax.swing.JLabel emailLbl;
	protected javax.swing.JTextField emailTxtField;
	private javax.swing.JLabel matriculaLbl;
	protected javax.swing.JTextField enrollmentTxtField;
	private javax.swing.JLabel nomeLbl;
	protected javax.swing.JTextField nameTxtField;
	private javax.swing.JLabel telefoneLbl;
	protected javax.swing.JTextField phoneTxtField;

}
