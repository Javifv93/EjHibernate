package vista.pantallas;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import datos.dao.AutorDAO;
import datos.dao.ContactousuarioDAO;
import datos.dao.EjemplarDAO;
import datos.dao.LibroDAO;
import datos.dao.PrestamoDAO;
import datos.dao.UsuarioDAO;
import modelo.entidades.Autor;
import modelo.entidades.Contactousuario;
import modelo.entidades.Ejemplar;
import modelo.entidades.Libro;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;

public class Programa {
	private static LibroDAO libdao = new LibroDAO();
	private static AutorDAO autdao = new AutorDAO();
	private static ContactousuarioDAO contdao = new ContactousuarioDAO();
	private static UsuarioDAO usudao = new UsuarioDAO();
	private static PrestamoDAO presdao = new PrestamoDAO();
	private static EjemplarDAO ejedao = new EjemplarDAO();
	
	public static void main(String[] args) {
	//AP 7
		//Relación N:M
//		Libro l1 = new Libro("003", "Titulo1", "Editorial", 3.45f);
//		Autor a1 = new Autor(1, "fulanito", "detal");
//		ArrayList<Autor> listaAutores = new ArrayList<Autor>();
//		listaAutores.add(a1);
//		l1.setAutores(listaAutores);
//		
//		libdao.insertarLibro(l1);
//		autdao.insertarAutor(a1);
		
		//Relacion 1:N sin existencia
//		Libro l1 = new Libro("004", "Titulo", "Editorial", 3.45f);
//		
//		//libdao.insertarLibro(l1);
//		libdao.borrarLibro(l1);
		
		//Relacion 1:1 con existencia
//		Usuario usuario = new Usuario(-1, "Usuario", "Apellidos", LocalDate.of(2010, 2, 2));
//		Contactousuario contacto = new Contactousuario(0, "jaja@gmail.com", "621321512", "141521152");
//		contacto.setUsuario(usuario);
//		usuario.setContactousuarios(contacto);
//		usudao.insertarUsuario(usuario);
//		
//		usudao.borrarUsuario(usuario);
//		
		//Relacion N:M convertida en dos 1:N
//		Usuario usuario = new Usuario(-1,"Usuario", "Apellidos", LocalDate.now());
//		Contactousuario contacto = new Contactousuario(0, "jaja@gmail.com", "621321512", "141521152");
//		contacto.setUsuario(usuario);
//		usuario.setContactousuarios(contacto);
//		usudao.insertarUsuario(usuario);
//		
//		Libro libro = new Libro((Integer.toString((int)(Math.random()*1000)+1)), "Titulo", "Editorial", 2421.2f);
//		libdao.insertarLibro(libro);
//		
//		Ejemplar ejemplar = new Ejemplar(-1,libro,123,"Libre");
//		Prestamo prestamo = new Prestamo(-1,ejemplar,usuario, LocalDate.now(), LocalDate.of(2312, 2, 2));
//		
//		ejedao.insertarEjemplar(ejemplar);
//		presdao.insertarPrestamo(prestamo);
//		
//		List<Prestamo> listaPrestamos = (usudao.obtenerUsuarioPorID(55).getPrestamos());
//		Iterator it = listaPrestamos.iterator();
//		while(it.hasNext()) {
//			Prestamo p = (Prestamo) it.next();
//			if(p.getFechaDevolucion()==null) {
//				p.setFechaDevolucion(LocalDate.now());
//				presdao.modificarPrestamo(p);
//			}
//		}
		
		//AP 8
		//usudao.queryObtenerUsuariosPorDNI();
		//usudao.queryObtenerUsuariosQueTienenLibrosEnLosPrestamos();
		//usudao.queryObtenerUsuariosMenoresDeEdad();
		
		libdao.
	}
}
