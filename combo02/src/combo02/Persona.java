package combo02;

public class Persona {
	private String id;
	private String nombre;
	private String apellido;
	private String tlf;
	public Persona(String id, String nombre, String apellido, String tlf) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tlf = tlf;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	
	
}
