package datos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import datos.configuracion.Conexion;
import modelo.entidades.Autor;
import modelo.entidades.Libro;
import modelo.entidades.Usuario;

public class AutorDAO {
	
	/**Inserta un Autor en base a un objeto Autor*/
	public void insertarAutor(Autor autor) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.save(autor);
			//Commit y refresh no serian 100% necesarios en este caso, pero no esta mal ponerlos por si aca
			transaccion.commit();
			sesion.refresh(autor);
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
	
	/**Obtiene un objeto Autor en base a un ID*/
	public Autor obtenerAutorPorID(int id) {
		Autor autor;
		try (Session sesion = Conexion.obtenerSesion() ) {
			autor = sesion.get(Autor.class, id);
			//Comprueba que el Autor con id pasada exista, y da un error en caso de que no sea así
			if(autor == null) {
				System.out.println("No se encuentra el Autor referenciado");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			autor = null;
		}
		return autor;
	}
	/**Borra el Autor pasado por parametro de la BBDD*/
	public void borrarAutor(Autor autor) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			Autor a = obtenerAutorPorID(autor.getIdAutor());
			if(a!=null)  {
				//Inicias la transacción
				transaccion = sesion.beginTransaction();
				
				//borra el objeto autor en la BBDD
				sesion.delete(autor); 
				
				transaccion.commit();
				sesion.refresh(autor);
			}
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
	/**Modifica el Autor pasado por parametro en la BBDD*/
	public void modificarAutor(Autor autor) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//modificas el objeto autor en la BBDD
			sesion.update(autor);
			
			transaccion.commit();
			sesion.refresh(autor);
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

}
