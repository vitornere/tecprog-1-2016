/**
 * Name:PatrimonyRegistration.java
 * Class is a super class for adding a new patrimony, 
 * which can be a classroom or a equipment.
 */

package view.cadastros;

public abstract class PatrimonyRegistration extends javax.swing.JDialog {
	
	/**
	 * Method that builds the heritage of the registration form.
	 * 
	 * @param parent
	 * @param modal
	 */
	
	public PatrimonyRegistration(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}
	
	/**
	 * Method constructor that initializes the heritage of the registration form.
	 */
	
	protected abstract void registrationAction();

	/**
	 * Method that initializes the components of the patrimony of 
	 * the registration form.
	 */
	
	public void initComponents() {

		codigoLbl = new javax.swing.JLabel();
		lblCapacity = new javax.swing.JLabel();
		descricaoLbl = new javax.swing.JLabel();
		codeTxtField = new javax.swing.JTextField();
		capacityTxtField = new javax.swing.JTextField();
		btnRegistration = new javax.swing.JButton();
		cancelBtn = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		discriptionTextArea = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Cadastro");
		setResizable(false);

		codigoLbl.setText("Codigo: ");

		lblCapacity.setText("Capacidade: ");

		descricaoLbl.setText("Descricao:");

		capacityTxtField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

		codeTxtField.setName("Codigo");
		capacityTxtField.setName("Capacidade");
		discriptionTextArea.setName("Descricao");

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

		discriptionTextArea.setColumns(20);
		discriptionTextArea.setRows(5);
		jScrollPane1.setViewportView(discriptionTextArea);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(descricaoLbl, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblCapacity, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(codigoLbl,
												javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jScrollPane1).addComponent(capacityTxtField)
										.addComponent(codeTxtField)))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnRegistration)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(8, 8, 8)))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(codigoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
										javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(codeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(lblCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 19,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(capacityTxtField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(descricaoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(btnRegistration).addComponent(cancelBtn))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}
	
	/**
	 * Method adds the event on the button to register the heritage.
	 * 
	 * @param evt
	 */

	private void cadastroBtnActionPerformed(java.awt.event.ActionEvent evt) {
		registrationAction();

	}

	/**
	 * Method adds the event on the button to cancel the registration of the equity
	 * 
	 * @param evt
	 */
	protected void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);
	}

	protected javax.swing.JButton btnRegistration;
	protected javax.swing.JButton cancelBtn;
	protected javax.swing.JLabel codigoLbl;
	protected javax.swing.JTextField codeTxtField;
	protected javax.swing.JLabel descricaoLbl;
	protected javax.swing.JScrollPane jScrollPane1;
	protected javax.swing.JLabel lblCapacity;
	protected javax.swing.JTextField capacityTxtField;
	protected javax.swing.JTextArea discriptionTextArea;
}
