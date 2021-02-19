package datos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.configuracion.Conexion;
import modelo.entidades.Autor;
import modelo.entidades.Libro;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;

public class PrestamoDAO {
	public void insertarPrestamo(Prestamo prestamo) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.save(prestamo);
			
			//Commit y refresh no serian 100% necesarios en este caso, pero no esta mal ponerlos por si aca
			transaccion.commit();
			sesion.refresh(prestamo);
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
	public Prestamo obtenerPrestamoPorID(int id) {
		Prestamo prestamo;
		try (Session sesion = Conexion.obtenerSesion() ) {
			prestamo = sesion.get(Prestamo.class, id);
			//Comprueba que el Prestamo con id pasada exista, y da un error en caso de que no sea así
			if(prestamo == null) {
				System.out.println("No se encuentra el Prestamo referenciado");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			prestamo = null;
		}
		return prestamo;
	}
	public void borrarPrestamo(Prestamo prestamo) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			Prestamo p = obtenerPrestamoPorID(prestamo.getIdPrestamo());
			if(p!=null) {
				//Inicias la transacción
				transaccion = sesion.beginTransaction();
				
				//borras el objeto prestamo en la BBDD
				sesion.delete(prestamo);
				
				transaccion.commit();
				sesion.refresh(prestamo);
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
	public void modificarPrestamo(Prestamo prestamo) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.update(prestamo);
		
			transaccion.commit();
			sesion.refresh(prestamo);
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
