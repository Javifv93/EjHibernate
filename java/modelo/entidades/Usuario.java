package modelo.entidades;
// Generated 27 ene. 2021 10:46:18 by Hibernate Tools 5.2.12.Final

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario implements java.io.Serializable {

	private int idUsuario;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private List<Prestamo> prestamos = new ArrayList<Prestamo>();
	private Contactousuario contactousuarios = new Contactousuario();

	public Usuario() {
	}

	public Usuario(int idUsuario, String nombre, String apellidos, LocalDate fechaNacimiento) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Usuario(int idUsuario, String nombre, String apellidos, LocalDate fechaNacimiento, Contactousuario contactousuarios,
			List<Prestamo> prestamos) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.contactousuarios = contactousuarios;
		this.prestamos = prestamos;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Contactousuario getContactousuarios() {
		return this.contactousuarios;
	}

	public void setContactousuarios(Contactousuario contactousuarios) {
		this.contactousuarios = contactousuarios;
	}

	public List<Prestamo> getPrestamos() {
		return this.prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

}
