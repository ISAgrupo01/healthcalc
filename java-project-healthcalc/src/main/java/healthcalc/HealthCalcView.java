package healthcalc;


import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class HealthCalcView implements IHealthCalcView {
	private JFrame frame;
	private JTextField txtPeso;
	private JTextField txtAltura;
	private JLabel lblResultadoBMI;
	private JLabel lblClasificacionBMI;
	private JLabel lblErrorBMI;
	private JTextField txtAlturaIBW;
	private JComboBox<String> comboSexo;
	private JLabel lblResultadoIBW;
	private JLabel lblErrorIBW;
	private JTextField txtSistolica;
	private JTextField txtDiastolica;
	private JLabel lblResultadoMAP;
	private JLabel lblClasificacionMAP;
	private JLabel lblErrorMAP;
	private JButton btnCalcularMAP;
	private JButton btnCalcularBMI;
	private JButton btnCalcularIBW;
	
	/**
	 * Create the application.
	 */
	public HealthCalcView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("HealthCalcApp");
		frame.setBounds(100, 100, 480, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("BMI", null, panel, null);
		panel.setLayout(null);

		JLabel lblPeso = new JLabel("Peso (kg)");
		lblPeso.setFont(new Font("SimSun-ExtG", Font.PLAIN, 12));
		lblPeso.setBounds(30, 20, 120, 20);
		panel.add(lblPeso);

		txtPeso = new JTextField();
		txtPeso.setBounds(160, 18, 120, 25);
		panel.add(txtPeso);
		txtPeso.setColumns(10);

		JLabel lblAltura = new JLabel("Altura (cm)");
		lblAltura.setFont(new Font("SimSun-ExtG", Font.PLAIN, 12));
		lblAltura.setBounds(30, 55, 120, 20);
		panel.add(lblAltura);

		txtAltura = new JTextField();
		txtAltura.setBounds(160, 53, 120, 25);
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		btnCalcularBMI = new JButton("Calcular BMI");
		btnCalcularBMI.setFont(new Font("Segoe UI Historic", Font.BOLD, 12));
		btnCalcularBMI.setBounds(30, 95, 150, 30);
		panel.add(btnCalcularBMI);

		lblResultadoBMI = new JLabel("Resultado: ---");
		lblResultadoBMI.setFont(new Font("SimSun-ExtG", Font.PLAIN, 12));
		lblResultadoBMI.setBounds(30, 140, 400, 20);
		panel.add(lblResultadoBMI);

		lblClasificacionBMI = new JLabel("Clasificacion: ---");
		lblClasificacionBMI.setFont(new Font("SimSun-ExtG", Font.PLAIN, 12));
		lblClasificacionBMI.setBounds(30, 165, 400, 20);
		panel.add(lblClasificacionBMI);

		lblErrorBMI = new JLabel("");
		lblErrorBMI.setBounds(30, 195, 400, 20);
		panel.add(lblErrorBMI);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("IBW", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblAlturaIBW = new JLabel("Altura (cm)");
		lblAlturaIBW.setFont(new Font("SimSun-ExtG", Font.PLAIN, 12));
		lblAlturaIBW.setBounds(30, 20, 120, 20);
		panel_1.add(lblAlturaIBW);

		txtAlturaIBW = new JTextField();
		txtAlturaIBW.setBounds(160, 18, 120, 25);
		panel_1.add(txtAlturaIBW);
		txtAlturaIBW.setColumns(10);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("SimSun-ExtG", Font.PLAIN, 12));
		lblSexo.setBounds(30, 55, 120, 20);
		panel_1.add(lblSexo);

		comboSexo = new JComboBox<String>();
		comboSexo.setModel(new DefaultComboBoxModel<String>(new String[] {"Hombre", "Mujer"}));
		comboSexo.setBounds(160, 53, 120, 25);
		panel_1.add(comboSexo);

		btnCalcularIBW = new JButton("Calcular IBW");
		btnCalcularIBW.setFont(new Font("Segoe UI Semilight", Font.BOLD, 12));
		btnCalcularIBW.setBounds(30, 95, 150, 30);
		panel_1.add(btnCalcularIBW);

		lblResultadoIBW = new JLabel("Resultado: ---");
		lblResultadoIBW.setFont(new Font("SimSun-ExtG", Font.PLAIN, 12));
		lblResultadoIBW.setBounds(30, 140, 400, 20);
		panel_1.add(lblResultadoIBW);

		lblErrorIBW = new JLabel("");
		lblErrorIBW.setBounds(30, 165, 400, 20);
		panel_1.add(lblErrorIBW);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("MAP", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblSistolica = new JLabel("Presión sistólica (mmHg)");
		lblSistolica.setFont(new Font("SimSun", Font.PLAIN, 12));
		lblSistolica.setBounds(30, 20, 180, 20);
		panel_2.add(lblSistolica);

		txtSistolica = new JTextField();
		txtSistolica.setBounds(220, 18, 120, 25);
		panel_2.add(txtSistolica);
		txtSistolica.setColumns(10);

		JLabel lblDiastolica = new JLabel("Presión diastólica (mmHg)");
		lblDiastolica.setFont(new Font("SimSun", Font.PLAIN, 12));
		lblDiastolica.setBounds(30, 55, 180, 20);
		panel_2.add(lblDiastolica);
		
		txtDiastolica = new JTextField();
		txtDiastolica.setBounds(220, 53, 120, 25);
		panel_2.add(txtDiastolica);
		txtDiastolica.setColumns(10);
		
		btnCalcularMAP = new JButton("Calcular MAP");
		btnCalcularMAP.setFont(new Font("Segoe UI Historic", Font.BOLD, 12));
		btnCalcularMAP.setBounds(30, 95, 150, 30);
		panel_2.add(btnCalcularMAP);

		lblResultadoMAP = new JLabel("Resultado: ---");
		lblResultadoMAP.setFont(new Font("SimSun-ExtG", Font.PLAIN, 12));
		lblResultadoMAP.setBounds(30, 140, 400, 20);
		panel_2.add(lblResultadoMAP);

		lblClasificacionMAP = new JLabel("Clasificacion: ---");
		lblClasificacionMAP.setFont(new Font("SimSun-ExtG", Font.PLAIN, 12));
		lblClasificacionMAP.setBounds(30, 165, 400, 20);
		panel_2.add(lblClasificacionMAP);

		lblErrorMAP = new JLabel("");
		lblErrorMAP.setBounds(30, 195, 400, 20);
		panel_2.add(lblErrorMAP);
		
		panel.setBackground(new java.awt.Color(245, 245, 245));
		panel_1.setBackground(new java.awt.Color(245, 245, 245));
		panel_2.setBackground(new java.awt.Color(245, 245, 245));
		
		lblErrorBMI.setForeground(java.awt.Color.RED);
		lblErrorIBW.setForeground(java.awt.Color.RED);
		lblErrorMAP.setForeground(java.awt.Color.RED);

		lblResultadoBMI.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		lblClasificacionBMI.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		lblResultadoIBW.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		lblResultadoMAP.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
		lblClasificacionMAP.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
	}
	
	
	//getters
	
	public JFrame getFrame() {
		return frame;
	}
 
	public JTextField getTxtPeso() {
		return txtPeso;
	}
 
	public JTextField getTxtAltura() {
		return txtAltura;
	}
 
	public JLabel getLblResultadoBMI() {
		return lblResultadoBMI;
	}
 
	public JLabel getLblClasificacionBMI() {
		return lblClasificacionBMI;
	}
 
	public JLabel getLblErrorBMI() {
		return lblErrorBMI;
	}
 
	public JTextField getTxtAlturaIBW() {
		return txtAlturaIBW;
	}
 
	public JComboBox<String> getComboSexo() {
		return comboSexo;
	}
 
	public JLabel getLblResultadoIBW() {
		return lblResultadoIBW;
	}
 
	public JLabel getLblErrorIBW() {
		return lblErrorIBW;
	}
 
	public JTextField getTxtSistolica() {
		return txtSistolica;
	}
 
	public JTextField getTxtDiastolica() {
		return txtDiastolica;
	}
 
	public JLabel getLblResultadoMAP() {
		return lblResultadoMAP;
	}
 
	public JLabel getLblClasificacionMAP() {
		return lblClasificacionMAP;
	}
 
	public JLabel getLblErrorMAP() {
		return lblErrorMAP;
	}
 
	public JButton getBtnCalcularMAP() {
		return btnCalcularMAP;
	}
	
	public JButton getBtnCalcularBMI() {
		return btnCalcularBMI;
	}
	
	public JButton getBtnCalcularIBW() {
		return btnCalcularIBW;
	}
}