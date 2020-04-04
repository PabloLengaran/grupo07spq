package concesionario.servidor;

import concesionario.servidor.BaseDatos.BD;
import concesionario.servidor.datos.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("loginp")
public class LoginResources {
	
	private Connection con;
	private Statement st;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUser() {
		Usuario usu= new Usuario("Una","Prueba",0);
		return usu;
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Una","Loko",2));
		usuarios.add(new Usuario("Dos","Prue",3));
		
		return usuarios;
		
	}
	//Por ahora solo funciona este metodo
	@POST
	@Path("inicio")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response anadirUsuario(Usuario concat) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		
		String username = concat.getNickname();
		String pass = concat.getContrasenya();
		
		Usuario nuevo = BD.usuarioSelect(st, username);
		
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			String tipo = Integer.toString(nuevo.getTipo());
			if(nuevo.getNickname().equals(username) && nuevo.getContrasenya().equals(pass)) {
				return Response.status(Response.Status.OK).entity(tipo).build();
			}else {
				return Response.status(Response.Status.NOT_ACCEPTABLE).build();
			}
		}
	}
	
	@POST
	@Path("insertClient")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarCliente(Cliente client) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		
		String username = client.getNickname();
		String pass = client.getContrasenya();
		String dni = client.getDNI();
		String nombre = client.getNombre();
		String apellido = client.getApellido();
		String sexo = client.getSexo();
		String email = client.getEmail();
		String ciudad = client.getCiudad();
		int codigoPostal = client.getCodigoPostal();
		String dir = client.getDireccion();
		String numTelefono = client.getNumeroTelefono();
		
		BD.clientesInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono);
		BD.usuariosInsert(st, username, pass, 3);
		
		Usuario nuevo = BD.usuarioSelect(st, username);
		Cliente clienteNuevo = BD.clienteSelect(st, username);
		
		if (nuevo == null) {
			if (clienteNuevo == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				return Response.status(Response.Status.OK).build();
			}
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("insertCocheConcesionario")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registrarCocheConcesionario(CocheConcesionario auto) {
		System.out.println(auto.getModelo());
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.cochesInsert(st, auto.getModelo(), auto.getMarca(), auto.getColor(), auto.getCv(), auto.getNumPuertas(), auto.getUnidades(), auto.getPrecio());
		CocheConcesionario coche = BD.cocheConcesionarioSelect(st, auto.getModelo());
		
		if (coche == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
 	@Path("insertVenta")
 	@Consumes(MediaType.APPLICATION_JSON)
 	public Response registrarVenta(Venta venta) {
 		System.out.println(venta.getModelo());
 		con =BD.initBD("Taller");
 		st = BD.usarCrearTablasBD(con);

 		BD.cochesVendidodsInsert(st, venta.getFecha(), venta.getNicknameComercial(), venta.getNombreComprador(), venta.getMarca(), venta.getModelo(), venta.getMatricula());
 		Venta v = BD.ventaSelect(st, venta.getMatricula());
 		
 		if (v == null) {
 			return Response.status(Response.Status.NOT_FOUND).build();
 		} else {
 			return Response.status(Response.Status.OK).build();
 		}
 	}
	
	@POST
	@Path("insertMecanic")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarMecanico(Mecanico mecanic) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String username = mecanic.getNickname();
		String pass = mecanic.getContrasenia();
		String dni = mecanic.getDNI();
		String nombre = mecanic.getNombre();
		String apellido = mecanic.getApellido();
		String sexo = mecanic.getSexo();
		String email = mecanic.getEmail();
		String ciudad = mecanic.getCiudad();
		int codigoPostal = mecanic.getCodigoPostal();
		String dir = mecanic.getDireccion();
		String numTelefono = mecanic.getNumeroTelefono();
		String NSS = mecanic.getNSS();
		String numeroCuenta = mecanic.getNumeroCuenta();
		int sueldo = mecanic.getSueldo();
		int horas = mecanic.getHoras();
		
		BD.mecanicosInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, horas);
		BD.empleadosInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, 0);
		BD.usuariosInsert(st, username, pass, 1);
		
		Usuario nuevo = BD.usuarioSelect(st, username);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("insertComercial")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarComercial(Comercial comercial) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		
		String username = comercial.getNickname();
		String pass = comercial.getContrasenia();
		String dni = comercial.getDNI();
		String nombre = comercial.getNombre();
		String apellido = comercial.getApellido();
		String sexo = comercial.getSexo();
		String email = comercial.getEmail();
		String ciudad = comercial.getCiudad();
		int codigoPostal = comercial.getCodigoPostal();
		String dir = comercial.getDireccion();
		String numTelefono = comercial.getNumeroTelefono();
		String NSS = comercial.getNSS();
		String numeroCuenta = comercial.getNumeroCuenta();
		int sueldo = comercial.getSueldo();
		int horas = comercial.getHoras();
		int cochesVendidos = comercial.getCochesVendidos();
		int importeObtenido = comercial.getImporteObetenido();
		
		
		BD.comercialesInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, horas,cochesVendidos, importeObtenido);
		BD.empleadosInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, 1);
		BD.usuariosInsert(st, username, pass, 2);
		
		Usuario nuevo = BD.usuarioSelect(st, username);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("insertDepartamentoCompras")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarDepartamentoCompras(DepartamentoCompras departamentoCompras) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		
		String username = departamentoCompras.getNickname();
		String pass = departamentoCompras.getContrasenia();
		String dni = departamentoCompras.getDNI();
		String nombre = departamentoCompras.getNombre();
		String apellido = departamentoCompras.getApellido();
		String sexo = departamentoCompras.getSexo();
		String email = departamentoCompras.getEmail();
		String ciudad = departamentoCompras.getCiudad();
		int codigoPostal = departamentoCompras.getCodigoPostal();
		String dir = departamentoCompras.getDireccion();
		String numTelefono = departamentoCompras.getNumeroTelefono();
		String NSS = departamentoCompras.getNSS();
		String numeroCuenta = departamentoCompras.getNumeroCuenta();
		int sueldo = departamentoCompras.getSueldo();
		int pedidos = departamentoCompras.getPedidos();
		
		
		BD.departamentoComprasInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, pedidos);
		BD.empleadosInsert(st, dni, username, pass, nombre, apellido, sexo, email, ciudad, codigoPostal, dir, numTelefono, NSS, numeroCuenta, sueldo, 2);
		BD.usuariosInsert(st, username, pass, 4);
		
		Usuario nuevo = BD.usuarioSelect(st, username);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("deleteClient")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deleteCliente(Cliente client) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.clientesDelete(st, client.getDNI());
		BD.usuariosDelete(st, client.getNickname());
		Cliente nuevo = BD.clienteSelect(st, client.getNickname());
		
		if (nuevo == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	
	@POST
	@Path("selectClient")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectCliente(String nickname) {
		
		st = BD.usarCrearTablasBD(con);
		
		Cliente nuevo = BD.clienteSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			Entity<Usuario> entity = Entity.entity(nuevo, MediaType.APPLICATION_JSON);
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@POST
	@Path("selectMecanico")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectMecanico(String nickname) {

		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		Mecanico nuevo = BD.mecanicoSelect(st, nickname);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			Entity<Mecanico> entity = Entity.entity(nuevo, MediaType.APPLICATION_JSON);
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@POST
	@Path("selectPiezaUtilizada")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response selectPiezaUtilizada(String codigo) {

		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		Pieza nuevo = BD.piezaUtilizadaSelect(st, codigo);
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			Entity<Pieza> entity = Entity.entity(nuevo, MediaType.APPLICATION_JSON);
			return Response.status(Response.Status.OK).entity(nuevo).build();
		}
	}
	
	@POST
	@Path("insertPiezas")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarPieza(Pieza pieza) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String codigo = pieza.getCodigo();
		String nombre = pieza.getNombre();
		String ubicacion = pieza.getUbicacion();
		int stock = pieza.getUnidades();
		
		BD.piezasInsert(st, codigo, nombre, stock, ubicacion);
		BD.piezasUtilizadasInsert(st, codigo, nombre, 0, ubicacion);
		Pieza nuevo = BD.piezaSelect(st, pieza.getCodigo());
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("insertPiezasUtilizadas")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response registrarPiezaUtilizada(Pieza pieza) {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		String codigo = pieza.getCodigo();
		String nombre = pieza.getNombre();
		String ubicacion = pieza.getUbicacion();
		int stock = pieza.getUnidades();
		
		BD.piezasUtilizadasInsert(st, codigo, nombre, stock, ubicacion);
		Pieza nuevo = BD.piezaUtilizadaSelect(st, pieza.getCodigo());
		
		if (nuevo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("deletePiezaUtilizada")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response deletePiezaUtilizada(Pieza pieza) {
		con = BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		BD.piezaUtilizadaDelete(st, pieza.getCodigo());
		Pieza nuevo = BD.piezaSelect(st, pieza.getCodigo());
		
		if (nuevo == null) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("loadTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CocheConcesionario> cargarTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.cochesTodosSelect(st);
		List<CocheConcesionario> coches_result = new ArrayList<CocheConcesionario>();
		
		if (rs == null) {
			System.out.println("No hay coches bd");
			return coches_result;
		} else {
			
			while(rs.next()) {
				System.out.println("entra rs");
				
				//Obtener atributos rs
				
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String color = rs.getString("color");
				int CV = rs.getInt("CV");
				int numeroPuertas = rs.getInt("numeroPuertas");
				int unidades = rs.getInt("unidades");
				int precio = rs.getInt("precio");
						
				CocheConcesionario coche = new CocheConcesionario(marca, modelo, precio, CV , numeroPuertas, color, unidades );
				coches_result.add(coche);
			}
			 
			return coches_result;
		}
	}
	
	@GET
	@Path("loadPiezaTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pieza> cargarPiezaTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.piezasTodasSelect(st);
		List<Pieza> pieza_result = new ArrayList<Pieza>();
		
		if (rs == null) {
			return pieza_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				int unidades = rs.getInt("stock");
				String ubicacion = rs.getString("ubicacion");
				
				Pieza pieza = new Pieza(codigo, nombre, unidades, ubicacion);	
				pieza_result.add(pieza);
			}
			 
			return pieza_result;
		}
	}
	
	@GET
	@Path("loadPiezaUtilizadasTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pieza> cargarPiezaUtilizadaTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		ResultSet rs = BD.piezasUtilizadasTodasSelect(st);
		List<Pieza> pieza_result = new ArrayList<Pieza>();
		
		if (rs == null) {
			return pieza_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				int unidades = rs.getInt("unidades");
				String ubicacion = rs.getString("ubicacion");
				
				Pieza pieza = new Pieza(codigo, nombre, unidades, ubicacion);	
				pieza_result.add(pieza);
			}
			 
			return pieza_result;
		}
	}
	
	
	
	
	
	@GET
	@Path("loadCochesMatricTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CocheMatriculado> cargarCochesMatriculadosTabla()throws SQLException {
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.cochesMatricTodosSelect(st);
		List<CocheMatriculado> cochesMatric_result = new ArrayList<CocheMatriculado>();
		
		if (rs == null) {
			System.out.println("No hay coches bd");
			return cochesMatric_result;
		} else {
			
			while(rs.next()) {
				//Obtener atributos rs
				String matricula = rs.getString("matriculaCoche");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				int anyo_matric = rs.getInt("anyomatriculacion");
				int revisiones = rs.getInt("revisiones");
						
				CocheMatriculado cocheMatric = new CocheMatriculado(marca, modelo, matricula, anyo_matric, revisiones);
				cochesMatric_result.add(cocheMatric);
			}
			 
			return cochesMatric_result;
		}
	}
	
	@GET
	@Path("loadEmpleadosTable")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Empleado> cargarEmpleadoTabla()throws SQLException {
		System.out.println("llega");
		con =BD.initBD("Taller");
		st = BD.usarCrearTablasBD(con);
		
		ResultSet rs = BD.empleadosTodasSelect(st);
		List<Empleado> empleados_result = new ArrayList<Empleado>();
		
		if (rs == null) {
			System.out.println("No hay empleados bd");
			return empleados_result;
		} else {
			while(rs.next()) {
				//Obtener atributos rs
				String nickname = rs.getString("nickname");
				String contrasenia = rs.getString("contrasenia");
				String dNI = rs.getString("dni");
				int tipo = 0;
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String sexo = rs.getString("sexo");
				String email = rs.getString("email");
				String ciudad = rs.getString("ciudad");
				int codigoPostal = rs.getInt("codigoPostal");
				String direccion = rs.getString("dir");
				String nSS = rs.getString("NSS");
				String numeroCuenta = rs.getString("numeroCuenta");
				String numeroTelefono = rs.getString("numTelefono");
				int sueldo = rs.getInt("sueldo");
				int tipoEmpleado = rs.getInt("tipoEmpleado");
				Empleado empleado = new Empleado(nickname, contrasenia, tipo, dNI, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, nSS, numeroCuenta, sueldo, numeroTelefono, tipoEmpleado);
				empleados_result.add(empleado);
			}
			return empleados_result;
		}
	}
	
	
	
	@DELETE
	@Path("{code}")
	public Response deleteUser (@PathParam("code") int codigo) {
		if(codigo==10) {
			System.out.println("borrando usuario");
			return Response.status(Response.Status.OK).build();
		}else {
			System.out.println("usuario no encontrado");
			return Response.status(Response.Status.NOT_FOUND).build();
		}
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
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
	    return dtm;
	}
	
}



