package datos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.id.uuid.CustomVersionOneStrategy;

import datos.configuracion.Conexion;
import modelo.entidades.Autor;
import modelo.entidades.Contactousuario;
import modelo.entidades.Libro;
import modelo.entidades.Usuario;

public class ContactousuarioDAO {

	/**Inserta un ContactoUsuario en base a un objeto ContactoUsuario*/
	public void insertarContactousuario(Contactousuario contactousuario) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.save(contactousuario);
			
			//Commit y refresh no serian 100% necesarios en este caso, pero no esta mal ponerlos por si aca
			transaccion.commit();
			sesion.refresh(contactousuario);
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
	
	/**Obtiene un objeto ContactoUsuario en base a un ID*/
	public Contactousuario obtenerContactousuarioPorID(int id) {
		Contactousuario contactousuario;
		try (Session sesion = Conexion.obtenerSesion() ) {
			contactousuario = sesion.get(Contactousuario.class, id);
			//Comprueba que el Contactousuario con id pasada exista, y da un error en caso de que no sea así
			if(contactousuario == null) {
				System.out.println("No se encuentra el Contacto de usuario referenciado");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			contactousuario = null;
		}
		return contactousuario;
	}
	/**Borra el ContactoUsuario pasado por parametro de la BBDD*/
	public void borrarContactoUsuario(Contactousuario contactousuario) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			Contactousuario cu = obtenerContactousuarioPorID(contactousuario.getIdUsuario());
			if(cu!=null) {
				//Inicias la transacción
				transaccion = sesion.beginTransaction();
			
				//borra el objeto Contactousuario en la BBDD
				sesion.delete(contactousuario);
				
				transaccion.commit();
				sesion.refresh(contactousuario);
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
	
	/**Modifica el ContactoUsuario pasado por parametro en la BBDD*/
	public void modificarContactousuario(Contactousuario contactousuario) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.update(contactousuario);
			
			transaccion.commit();
			sesion.refresh(contactousuario);
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
