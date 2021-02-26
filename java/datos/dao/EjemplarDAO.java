package datos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.configuracion.Conexion;
import modelo.entidades.Autor;
import modelo.entidades.Ejemplar;
import modelo.entidades.Libro;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;

public class EjemplarDAO {

	/**Inserta un Ejemplar en base a un objeto Ejemplar*/
	public void insertarEjemplar(Ejemplar ejemplar) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.save(ejemplar);
			
			//Commit y refresh no serian 100% necesarios en este caso, pero no esta mal ponerlos por si aca
			transaccion.commit();
			sesion.refresh(ejemplar);
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
	
	/**Obtiene un objeto Ejemplar en base a un ID*/
	public Ejemplar obtenerEjemplarPorID(int id) {
		Ejemplar ejemplar;
		try (Session sesion = Conexion.obtenerSesion() ) {
			ejemplar = sesion.get(Ejemplar.class, id);
			//Comprueba que el Ejemplar con id pasada exista, y da un error en caso de que no sea así
			if(ejemplar == null) {
				System.out.println("No se encuentra el Ejemplar referenciado");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ejemplar = null;
		}
		return ejemplar;
	}
	/**Borra el Ejemplar pasado por parametro de la BBDD*/
	public void borrarEjemplar(Ejemplar ejemplar) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			Ejemplar e = obtenerEjemplarPorID(ejemplar.getIdEjemplar());
			if(e!=null) {
				//Inicias la transacción
				transaccion = sesion.beginTransaction();
				
				//borra el objeto Ejemplar en la BBDD
				sesion.delete(ejemplar);
				
				transaccion.commit();
				sesion.refresh(ejemplar);
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
	/**Modifica el Ejemplar pasado por parametro en la BBDD*/
	public void modificarEjemplar(Ejemplar ejemplar) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.update(ejemplar);
			
			transaccion.commit();
			sesion.refresh(ejemplar);
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
