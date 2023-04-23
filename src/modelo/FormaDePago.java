package modelo;

public class FormaDePago {
	int id;
	String nombre;

	public FormaDePago(int id, String nombre) {
		this.id=id;
		this.nombre=nombre;
	}

	@Override
	public String toString() {
		return (id + nombre);
	}
}