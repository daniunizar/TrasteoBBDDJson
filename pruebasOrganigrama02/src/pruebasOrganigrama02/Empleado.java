package pruebasOrganigrama02;

public class Empleado {
	private int id;
	private String nombre;
	private String apellido;
	private String cargo;
	private int superior;
	
	public Empleado(int id, String nombre, String apellido, String cargo, int superior) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cargo = cargo;
		this.superior = superior;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getSuperior() {
		return superior;
	}

	public void setSuperior(int superior) {
		this.superior = superior;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cargo=" + cargo
				+ ", superior=" + superior + "]";
	}
	
}
