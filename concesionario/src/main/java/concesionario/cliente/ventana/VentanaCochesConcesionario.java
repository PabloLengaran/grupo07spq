package concesionario.cliente.ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import concesionario.cliente.controller.LoginController;
import concesionario.servidor.datos.Cliente;
import concesionario.servidor.datos.CocheConcesionario;

public class VentanaCochesConcesionario extends JFrame {
		
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private LoginController loginController;
	
	public VentanaCochesConcesionario(LoginController loginController, String nickname) {
		this.loginController = loginController;
		iniciarVentanaCochesConcesionario(nickname);
	}
	
	public void iniciarVentanaCochesConcesionario(String nickname){
		
		setAutoRequestFocus(false);
		setBounds(100, 100, 992, 360);
		setTitle("Gestion de empleados");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuComercial vmc = new VentanaMenuComercial(loginController, nickname);
				vmc.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(849, 299, 117, 29);
		getContentPane().add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 980, 286);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

		//boton para ver las propiedades del vehiculo seleccionado (no se bien como sacar todas las propiedades)
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla(table);
			}
		});
		btnVer.setBounds(446, 299, 112, 29);
		getContentPane().add(btnVer);
		
	}
	
	private static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
	    ResultSetMetaData metaData = rs.getMetaData();
	    // Nombre de las columnas:
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }
	    // Datos de la tabla:
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
	    return new DefaultTableModel(data, columnNames);
	}
	
	public void cargarTabla(JTable table) {
		List<CocheConcesionario> coches = loginController.cargarTablaCochesConcesionario();
		
		String[] columnNames = {"MARCA",
		        "MODELO",
		        "PRECIO",
		        "CV",
		        "NUMPUERTAS",
		        "COLOR",
		        "UNIDADES"};
		
		if (!coches.isEmpty()) {
			 DefaultTableModel model = new DefaultTableModel();
			   table.setModel(model);
			   model.setColumnIdentifiers(columnNames);
			   
			   for (CocheConcesionario c : coches) {
				   Object[] o = new Object[7];
				   o[0] = c.getMarca();
				   o[1] = c.getModelo();
				   o[2] = c.getPrecio();
				   o[3] = c.getCv();
				   o[4] = c.getNumPuertas();
				   o[5] = c.getColor();
				   o[6] = c.getUnidades();
				   model.addRow(o);
				 }
		} else {
			System.out.println("llega mal");
		}
		
	}
}