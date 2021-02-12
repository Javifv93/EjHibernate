package modelo.entidades;
// Generated 27 ene. 2021 10:46:18 by Hibernate Tools 5.2.12.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * Libro generated by hbm2java
 */
public class Libro implements java.io.Serializable {

	private String codLibro;
	private String titulo;
	private String editorial;
	private float precio;
	private int ejemplarIdEjemplar;
//	private Set autorlibros = new HashSet(0);
//	private Set ejemplars = new HashSet(0);
	private ArrayList<Autor> autores = new ArrayList<Autor>();
	private ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
	
	public Libro() {}	

	public Libro(String codLibro, String titulo, String editorial, float precio, int ejemplarIdEjemplar) {
		this.codLibro = codLibro;
		this.titulo = titulo;
		this.editorial = editorial;
		this.precio = precio;
		this.ejemplarIdEjemplar = ejemplarIdEjemplar;
	}

	public Libro(String codLibro, String titulo, String editorial, float precio, int ejemplarIdEjemplar,
			ArrayList<Autor> autores, ArrayList<Ejemplar> ejemplares) {
		this.codLibro = codLibro;
		this.titulo = titulo;
		this.editorial = editorial;
		this.precio = precio;
		this.ejemplarIdEjemplar = ejemplarIdEjemplar;
		this.autores = autores;
		this.ejemplares = ejemplares;
		//this.autorlibros = autorlibros;
		//this.ejemplars = ejemplars;
	}

	public String getCodLibro() {
		return this.codLibro;
	}

	public void setCodLibro(String codLibro) {
		this.codLibro = codLibro;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditorial() {
		return this.editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getEjemplarIdEjemplar() {
		return this.ejemplarIdEjemplar;
	}

	public void setEjemplarIdEjemplar(int ejemplarIdEjemplar) {
		this.ejemplarIdEjemplar = ejemplarIdEjemplar;
	}

//	public Set getAutorlibros() {
//		return this.autorlibros;
//	}
//
//	public void setAutorlibros(Set autorlibros) {
//		this.autorlibros = autorlibros;
//	}
//
//	public Set getEjemplars() {
//		return this.ejemplars;
//	}
//
//	public void setEjemplars(Set ejemplars) {
//		this.ejemplars = ejemplars;
//	}
	public ArrayList<Autor> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}

	public ArrayList<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(ArrayList<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

}
