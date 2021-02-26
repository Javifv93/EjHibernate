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

	/**Inserta un Libro en base a un objeto Libro*/
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
	/**Obtiene un objeto Libro en base a un ID*/
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
	/**Borra el Libro pasado por parametro de la BBDD*/
	public void borrarLibro(Libro libro) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Libro l = obtenerLibroPorID(libro.getCodLibro());
			//if(l!=null)  {
				//Inicias la transacción
				transaccion = sesion.beginTransaction();
				
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
	/**Modifica el Libro pasado por parametro en la BBDD*/
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
	/**Hace una consulta en la BBDD para obtener un listado de Libros con fecha de prestamo entre dos fechas*/
	public void queryObtenerLibrosPrestadosEntreDosFechas() {
		try (Session sesion = Conexion.obtenerSesion() ) {
			LocalDate fechaIni = introducirLocalDates();
			LocalDate fechaFin = introducirLocalDates();
			Query<Libro> q = sesion.createQuery("SELECT l FROM Libro l, Ejemplar e, Prestamo p "
					+ "WHERE e.libro.codLibro = l.codLibro "
					+ "AND e.idEjemplar = p.ejemplar.idEjemplar "
					+ "AND p.fechaPrestamo BETWEEN :fechaIni AND :fechaFin");
			q.setParameter("fechaIni", fechaIni);
			q.setParameter("fechaFin", fechaFin);
			q.setReadOnly(true);
			
			List<Libro> lista = q.getResultList();
			System.out.println("Resultado: ");
			for(Libro l:lista) {
				System.out.println("- " + l.getTitulo());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**Metodo para pedir por consola una fecha y convertirla a formato LocalDate*/
	public LocalDate introducirLocalDates() {
		System.out.println("Introduce la primera fecha:\r Año: ");
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
	/**Hace una consulta en la BBDD para obtener una lista de Libros escritos por el autor pasado por parametro*/
	public void queryObtenerLibrosPorNombreDeAutor(String nombreAutor) {
		try (Session sesion = Conexion.obtenerSesion() ) {
			Query<Libro> qAutor = sesion.createQuery("SELECT libros FROM Autor WHERE nombre = :nombre") ;
			qAutor.setParameter("nombre", nombreAutor);
			qAutor.setReadOnly(true);
			
			List<Libro> lista = qAutor.getResultList();
			for(Libro l:lista) {
				System.out.println("- " + l.getTitulo());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**Hace una consulta en la BBDD para obtener una lista de Libros con un precio menor que 20 o nulo*/
	public void queryObtenerLibrosPrecioMenorQue20oSinPrecio() {
		try (Session sesion = Conexion.obtenerSesion() ) {
			Query<Libro> q = sesion.createQuery("FROM Libro WHERE precio < 20 OR precio = null") ;
			q.setReadOnly(true);
			
			List<Libro> lista = q.getResultList();
			for(Libro l:lista) {
				System.out.println("- " + l.getTitulo());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
