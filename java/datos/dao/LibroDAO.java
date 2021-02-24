package datos.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.configuracion.Conexion;
import modelo.entidades.Autor;
import modelo.entidades.Ejemplar;
import modelo.entidades.Libro;
import modelo.entidades.Usuario;

public class LibroDAO {

	public void insertarLibro(Libro libro) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.save(libro);
			
			//Commit y refresh no serian 100% necesarios en este caso, pero no esta mal ponerlos por si aca
			transaccion.commit();
			sesion.refresh(libro);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			/**En caso de que se haya iniciado la transaccion y haya petado despues, hace un 
			 * rollback a la transaccion*/
			if(transaccion!=null) {
				transaccion.rollback();
			}
		}
	}
	public Libro obtenerLibroPorID(String id) {
		Libro libro;
		try (Session sesion = Conexion.obtenerSesion() ) {
			libro = sesion.get(Libro.class, id);
			//Comprueba que el libro con id pasada exista, y da un error en caso de que no sea así
			if(libro == null) {
				System.out.println("No se encuentra el Libro referenciado");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			libro = null;
		}
		return libro;
	}
	public void borrarLibro(Libro libro) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Libro l = obtenerLibroPorID(libro.getCodLibro());
			//if(l!=null)  {
				//Inicias la transacción
				transaccion = sesion.beginTransaction();
				
//				//Como cada Libro tiene una lista de Autores y una lista de Ejemplares, las recorro y los borro uno a uno en cascada
//				for(Autor autor:l.getAutores()) {
//					sesion.delete(autor);
//				}
//				for(Ejemplar ejemplar:l.getEjemplares()) {
//					sesion.delete(ejemplar);
//				}
				//borra el objeto Libro en la BBDD
				sesion.delete(libro);
				
				transaccion.commit();
				//sesion.refresh(libro);
			//}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			/**En caso de que se haya iniciado la transaccion y haya petado despues, hace un 
			 * rollback a la transaccion*/
			if(transaccion!=null) {
				transaccion.rollback();
			}
		}
	}
	public void modificarLibro(Libro libro) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.update(libro);
			
			transaccion.commit();
			sesion.refresh(libro);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			/**En caso de que se haya iniciado la transaccion y haya petado despues, hace un 
			 * rollback a la transaccion*/
			if(transaccion!=null) {
				transaccion.rollback();
			}
		}
	}
	public void queryObtenerLibrosPrestadosEntreDosFechas() {
		try (Session sesion = Conexion.obtenerSesion() ) {
			LocalDate fechaIni = introducirLocalDates();
			LocalDate fechaFin = introducirLocalDates();
//			Query<Usuario> q = sesion.createQuery("FROM Usuario WHERE idUsuario like :dniUsuario");
//			q.setParameter("dniUsuario", dni);
//			q.setReadOnly(true);
//			
//			Usuario user = q.getSingleResult();
//			System.out.println("Resultado: " + user.getIdUsuario());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public LocalDate introducirLocalDates() {
		System.out.println("Introduce la primera fecha:\t Año: ");
		Scanner sc = new Scanner(System.in);
		int año = sc.nextInt();
		System.out.println("Mes: ");
		sc = new Scanner(System.in);
		int mes = sc.nextInt();
		System.out.println("Dia: ");
		sc = new Scanner(System.in);
		int dia = sc.nextInt();
		return LocalDate.of(año, mes, dia);
	}
//	public void queryObtenerUsuariosQueTienenLibrosEnLosPrestamos() {
//		try (Session sesion = Conexion.obtenerSesion() ) {
//			Query<Usuario> q = sesion.createQuery("SELECT u FROM Usuario u, Prestamo p WHERE u.idUsuario = p.usuario.idUsuario");
//			q.setReadOnly(true);
//			
//			List<Usuario> lista = q.getResultList();
//			System.out.println("Resultado: ");
//			for(Usuario u:lista) {
//				System.out.println("- " + u.getNombre());
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
}
