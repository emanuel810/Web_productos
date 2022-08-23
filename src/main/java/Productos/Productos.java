package Productos;

import java.util.Date;

public class Productos {
	
	private Integer producto_id;
	
	private String seccion;
	
	private String nombre;
	
	private double precio;
	
	private Date fecha;
	
	private String nacionalidad;

	public Productos(Integer id, String seccion, String nombre, Date fecha, double precio,
			String nacionalidad) {
		this.producto_id = id;
		this.seccion = seccion;
		this.nombre = nombre;
		this.precio = precio;
		this.fecha = fecha;
		this.nacionalidad = nacionalidad;
	}

	public Productos(String seccion, String nombre, Date fecha, double precio, String nacionalidad) {
		this.seccion = seccion;
		this.nombre = nombre;
		this.precio = precio;
		this.fecha = fecha;
		this.nacionalidad = nacionalidad;
	}

	public Integer getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(Integer producto_id) {
		this.producto_id = producto_id;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return "Productos [producto_id=" + producto_id + ", seccion=" + seccion + ", nombre=" + nombre + ", precio="
				+ precio + ", fecha=" + fecha + ", nacionalidad=" + nacionalidad + "]";
	}
	
	
	
	
}
