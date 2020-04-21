package concesionario.cliente.ventana.mecanico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.Controller;
import concesionario.datos.CocheTaller;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistroCocheTaller extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller loginController; 
	private JTextField txtMatricula;
	private JTextField txtModelo;
	private JTextField txtMarca;
	private JTextField txtCoste;
	private JTextField txtDni;

	public VentanaRegistroCocheTaller (Controller loginController, String nickname) {
		setResizable(false);
		this.loginController = loginController;
		iniciarVentanaRegistrarCocheTaller(nickname);
	}
	
	private void iniciarVentanaRegistrarCocheTaller(String nickname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 312);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuMecanico vmm = new VentanaMenuMecanico(loginController, nickname);
				vmm.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(106, 227, 117, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(37, 32, 171, 16);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(220, 27, 199, 26);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblMarca = new JLabel("Nombre de la Marca:");
		lblMarca.setBounds(37, 70, 171, 16);
		contentPane.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(220, 65, 199, 26);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Nombre del Modelo: ");
		lblModelo.setBounds(37, 111, 171, 16);
		contentPane.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(220, 106, 199, 26);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblCoste = new JLabel("Coste:");
		lblCoste.setBounds(37, 148, 148, 16);
		contentPane.add(lblCoste);
		
		txtCoste = new JTextField();
		txtCoste.setBounds(220, 143, 199, 26);
		contentPane.add(txtCoste);
		txtCoste.setColumns(10);
		
		
		JLabel lblDni = new JLabel("Dni cliente:");
		lblDni.setBounds(37, 185, 148, 16);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(220, 180, 199, 26);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobarCampos()) {
					String matricula = txtMatricula.getText();
					String marca = txtMarca.getText();
					String modelo = txtModelo.getText();
					String mecanico = nickname;
					String dniCliente = txtDni.getText();
					double coste = Double.parseDouble(txtCoste.getText());
					int estado = 0;   //Estado de inicio de reparacion es el estado 0 
					
					CocheTaller cocheTaller = new CocheTaller(matricula, marca, modelo, mecanico, dniCliente, coste, estado);
					registrarCocheTaller(cocheTaller, nickname);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Todos los campos deben estar completos");
				}
				
			}
		});
		btnNewButton.setBounds(258, 227, 117, 29);
		contentPane.add(btnNewButton);
		
	}
	
	
	public void registrarCocheTaller(CocheTaller cocheTaller, String nickname) {
		Response response = loginController.registrarCocheTaller(cocheTaller);
		if(response.getStatus() == Status.OK.getStatusCode()) {
			JOptionPane.showMessageDialog(this, "Coche taller registrado");
			VentanaMenuMecanico vmm = new VentanaMenuMecanico(loginController, nickname);
			vmm.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "ERROR al regristar el coche taller");
		}
	}
	
	public boolean comprobarCampos() {
		boolean datos = false;
		if (!txtMatricula.getText().isEmpty() && !txtMarca.getText().isEmpty() && !txtModelo.getText().isEmpty() && !txtDni.getText().isEmpty() && !txtCoste.getText().isEmpty()) {
			datos = true;
		}
		return datos;
	}
}
