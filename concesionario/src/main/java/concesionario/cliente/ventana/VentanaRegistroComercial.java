package concesionario.cliente.ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import concesionario.servidor.BaseDatos.BD;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class VentanaRegistroComercial extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDNI;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldNick;
	private JTextField textFieldCiudad;
	private JPasswordField passwordField;
	private JTextField textFieldCP;
	private JTextField textFieldDireccion;
	private JTextField textFieldTelefono;
	private JTextField textFieldNSS;
	private JTextField textFieldCuenta;
	private JTextField textFieldSueldo;
	private Connection con;
	private Statement st;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroComercial frame = new VentanaRegistroComercial();
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
	public VentanaRegistroComercial() {
		setResizable(false);
		setTitle("Registro Comercial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		JLabel lblNewLabel = new JLabel("Si desea registrar un comercial rellene los siguintes datos:");
		lblNewLabel.setBounds(22, 20, 426, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblDNI = new JLabel("DNI: ");
		lblDNI.setBounds(53, 61, 150, 16);
		contentPane.add(lblDNI);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(231, 56, 228, 26);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(53, 267, 150, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(53, 305, 150, 16);
		contentPane.add(lblApellido);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(231, 262, 228, 26);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo: ");
		lblSexo.setBounds(53, 173, 150, 16);
		contentPane.add(lblSexo);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaEmpleados ventana = new VentanaEmpleados();
				ventana.setVisible(true);
				dispose();
				
			}
		});
		btnCancelar.setBounds(109, 638, 117, 29);
		contentPane.add(btnCancelar);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(231, 300, 228, 26);
		contentPane.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JComboBox comboBoxSexo = new JComboBox();
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer", "Otro"}));
		comboBoxSexo.setBounds(231, 169, 228, 27);
		contentPane.add(comboBoxSexo);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(53, 215, 150, 16);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(231, 210, 228, 26);
		contentPane.add(textFieldEmail);
		
		JLabel lblContrasenya = new JLabel("Contraseña: ");
		lblContrasenya.setBounds(53, 135, 150, 16);
		contentPane.add(lblContrasenya);
		
		JLabel lblNickname = new JLabel("Nickname: ");
		lblNickname.setBounds(53, 97, 150, 16);
		contentPane.add(lblNickname);
		
		JLabel lblCiudad = new JLabel("Ciudad: ");
		lblCiudad.setBounds(53, 346, 150, 16);
		contentPane.add(lblCiudad);
		
		JLabel lblCp = new JLabel("Codigo Postal: ");
		lblCp.setBounds(53, 384, 150, 16);
		contentPane.add(lblCp);
		
		JLabel lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setBounds(53, 421, 150, 16);
		contentPane.add(lblDireccion);
		
		textFieldNick = new JTextField();
		textFieldNick.setColumns(10);
		textFieldNick.setBounds(231, 93, 228, 26);
		contentPane.add(textFieldNick);
		
		textFieldCiudad = new JTextField();
		textFieldCiudad.setColumns(10);
		textFieldCiudad.setBounds(231, 341, 228, 26);
		contentPane.add(textFieldCiudad);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(231, 131, 228, 26);
		contentPane.add(passwordField);
		
		textFieldCP = new JTextField();
		textFieldCP.setColumns(10);
		textFieldCP.setBounds(231, 379, 228, 26);
		contentPane.add(textFieldCP);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(231, 416, 228, 26);
		contentPane.add(textFieldDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono:\n");
		lblTelefono.setBounds(53, 464, 150, 16);
		contentPane.add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(231, 459, 228, 26);
		contentPane.add(textFieldTelefono);
		
		JLabel lblNss = new JLabel("NSS: ");
		lblNss.setBounds(53, 506, 150, 16);
		contentPane.add(lblNss);
		
		JLabel lblCuentaBancaria = new JLabel("Cuenta bancaria: ");
		lblCuentaBancaria.setBounds(53, 546, 150, 16);
		contentPane.add(lblCuentaBancaria);
		
		textFieldNSS = new JTextField();
		textFieldNSS.setColumns(10);
		textFieldNSS.setBounds(231, 501, 228, 26);
		contentPane.add(textFieldNSS);
		
		textFieldCuenta = new JTextField();
		textFieldCuenta.setColumns(10);
		textFieldCuenta.setBounds(231, 541, 228, 26);
		contentPane.add(textFieldCuenta);
		
		JLabel Sueldo = new JLabel("Sueldo:");
		Sueldo.setBounds(53, 593, 150, 16);
		contentPane.add(Sueldo);
		
		textFieldSueldo = new JTextField();
		textFieldSueldo.setColumns(10);
		textFieldSueldo.setBounds(231, 588, 228, 26);
		contentPane.add(textFieldSueldo);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean datos = comprobarDatos();
				if (datos) {
					String nickname = textFieldNick.getText();
					String contrasenia = new String (passwordField.getPassword());
					String nombre = textFieldNombre.getText();
					String dni = textFieldDNI.getText();
					String apellido = textFieldApellido.getText();
					String ciudad = textFieldCiudad.getText();
					int codigoPostal = Integer.parseInt(textFieldCP.getText());
					int sueldo = Integer.parseInt(textFieldSueldo.getText());
					String dir = textFieldDireccion.getText();
					String numTelefono = textFieldTelefono.getText();
					String nss = textFieldNSS.getText();
					String numeroCuenta = textFieldCuenta.getText();
					String email = textFieldEmail.getText();
					int tipo = comboBoxSexo.getSelectedIndex();
					String sexo = comprobarSexo(tipo);
					BD.empleadosInsert(st, dni, nickname, contrasenia, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, nss, numeroCuenta, sueldo, 1);
					BD.comercialesInsert(st, dni, nickname, contrasenia, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, nss, numeroCuenta, sueldo, 0);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Todos los campos deben estar rellenados.");
				}
				
			}
		});
		btnRegistrar.setBounds(277, 638, 117, 29);
		contentPane.add(btnRegistrar);
	}
	
	public boolean comprobarDatos() {
		boolean datos = false;
		if (!textFieldDNI.getText().isEmpty() && !textFieldNombre.getText().isEmpty() && !textFieldApellido.getText().isEmpty() && !textFieldNick.getText().isEmpty() && !textFieldEmail.getText().isEmpty() && !textFieldCiudad.getText().isEmpty() && !textFieldCP.getText().isEmpty() && !textFieldDireccion.getText().isEmpty() && !textFieldTelefono.getText().isEmpty() && !textFieldCuenta.getText().isEmpty() && !textFieldNSS.getText().isEmpty() && !textFieldSueldo.getText().isEmpty()) {
			datos = true;
		} 
		if(textFieldDNI.getText().length()!=9) {
			JOptionPane.showMessageDialog(contentPane, "DNI incorrecto. Introduzca este campo con el siguiente formato XXXXXXXXY siendo Y una letra");
			datos = false;
		}
		if(textFieldTelefono.getText().length()!=9) {
			JOptionPane.showMessageDialog(contentPane, "Telefono incorrecto. Introduzca este campo con el siguiente formato XXXXXXXXX.");
			datos = false;
		}
		if(!textFieldEmail.getText().contains("@")) {
			JOptionPane.showMessageDialog(contentPane, "Email incorrecto.");
			datos = false;
		}
		if(textFieldCP.getText().length()!=5) {
			JOptionPane.showMessageDialog(contentPane, "Codigo postal incorrecto.Escriba 5 numeros.");
			datos = false;
		}
		
		return datos;
	}
	
	private String comprobarSexo(int s){
		String sexo = "";
		switch (s) {
		case 0:
			sexo = "Hombre";
			break;
		case 1: 
			sexo = "Mujer";
		case 3: 
			sexo = "Otro";
		}
		return sexo;
	}
}