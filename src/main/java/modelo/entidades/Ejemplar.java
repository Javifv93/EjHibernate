package modelo.entidades;
// Generated 27 ene. 2021 10:46:18 by Hibernate Tools 5.2.12.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ejemplar generated by hbm2java
 */
public class Ejemplar implements java.io.Serializable {

	private int idEjemplar;
	private Libro libro;
	private int numEjemplar;
	private String estado;
	private List<Prestamo> prestamos = new ArrayList<Prestamo>();

	public Ejemplar() {
	}

	public Ejemplar(int idEjemplar, Libro libro, int numEjemplar, String estado) {
		this.idEjemplar = idEjemplar;
		this.libro = libro;
		this.numEjemplar = numEjemplar;
		this.estado = estado;
	}

	public Ejemplar(int idEjemplar, Libro libro, int numEjemplar, String estado, List<Prestamo> prestamos) {
		this.idEjemplar = idEjemplar;
		this.libro = libro;
		this.numEjemplar = numEjemplar;
		this.estado = estado;
		this.prestamos = prestamos;
	}

	public int getIdEjemplar() {
		return this.idEjemplar;
	}

	public void setIdEjemplar(int idEjemplar) {
		this.idEjemplar = idEjemplar;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public int getNumEjemplar() {
		return this.numEjemplar;
	}

	public void setNumEjemplar(int numEjemplar) {
		this.numEjemplar = numEjemplar;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Prestamo> getPrestamos() {
		return this.prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

}
