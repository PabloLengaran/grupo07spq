package concesionario.datos;


public class Comercial{
	private String nickname;
	private String contrasenia;
	private String DNI;
	private String Nombre;
	private String Apellido;
	private String Sexo;
	private String Email;
	private String Ciudad;
	private int CodigoPostal;
	private String Direccion;
	private String NSS;
	private String NumeroCuenta; 
	private int Sueldo;
	private String NumeroTelefono;
	private int TipoEmpleado;
	private int CochesVendidos;
	private int ImporteObetenido;
	private int horas;
	
	public Comercial() {
		
	}
	
	
	public Comercial(String nickname, String contrasenia, String dNI, String nombre, String apellido,
			String sexo, String email, String ciudad, int codigoPostal, String direccion, String nSS,
			String numeroCuenta, int sueldo, String numeroTelefono, int tipoEmpleado, int cochesVendidos,
			int importeObetenido, int horas) {
		this.nickname = nickname;
		this.contrasenia = contrasenia;
		DNI = dNI;
		Nombre = nombre;
		Apellido = apellido;
		Sexo = sexo;
		Email = email;
		Ciudad = ciudad;
		CodigoPostal = codigoPostal;
		Direccion = direccion;
		NSS = nSS;
		NumeroCuenta = numeroCuenta;
		Sueldo = sueldo;
		NumeroTelefono = numeroTelefono;
		TipoEmpleado = tipoEmpleado;
		CochesVendidos = cochesVendidos;
		ImporteObetenido = importeObetenido;
		this.horas = horas;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getSexo() {
		return Sexo;
	}
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCiudad() {
		return Ciudad;
	}
	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}
	public int getCodigoPostal() {
		return CodigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		CodigoPostal = codigoPostal;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getNSS() {
		return NSS;
	}
	public void setNSS(String nSS) {
		NSS = nSS;
	}
	public String getNumeroCuenta() {
		return NumeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		NumeroCuenta = numeroCuenta;
	}
	public int getSueldo() {
		return Sueldo;
	}
	public void setSueldo(int sueldo) {
		Sueldo = sueldo;
	}
	public String getNumeroTelefono() {
		return NumeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		NumeroTelefono = numeroTelefono;
	}
	public int getTipoEmpleado() {
		return TipoEmpleado;
	}
	public void setTipoEmpleado(int tipoEmpleado) {
		TipoEmpleado = tipoEmpleado;
	}
	public int getCochesVendidos() {
		return CochesVendidos;
	}
	public void setCochesVendidos(int cochesVendidos) {
		CochesVendidos = cochesVendidos;
	}
	public int getImporteObetenido() {
		return ImporteObetenido;
	}
	public void setImporteObetenido(int importeObetenido) {
		ImporteObetenido = importeObetenido;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
}
