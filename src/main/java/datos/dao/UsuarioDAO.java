package datos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.configuracion.Conexion;
import modelo.entidades.Autor;
import modelo.entidades.Libro;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;

public class UsuarioDAO {

	public void insertarUsuario(Usuario usuario) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.save(usuario);
			
			//Commit y refresh no serian 100% necesarios en este caso, pero no esta mal ponerlos por si aca
			transaccion.commit();
			sesion.refresh(usuario);
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
	public Usuario obtenerUsuarioPorID(int id) {
		Usuario usuario;
		try (Session sesion = Conexion.obtenerSesion() ) {
			usuario = sesion.get(Usuario.class, id);
			//Comprueba que el Usuario con id pasada exista, y da un error en caso de que no sea así
			if(usuario == null) {
				System.out.println("No se encuentra el Usuario referenciado");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			usuario = null;
		}
		return usuario;
	}
	public void borrarUsuario(Usuario usuario) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			Usuario u = obtenerUsuarioPorID(usuario.getIdUsuario());
			if(u!=null) {
				//Inicias la transacción
				transaccion = sesion.beginTransaction();
					
				//Como Usuario tiene una lista de Prestamos, la recorro y los borro uno a uno en cascada
				for(Prestamo prestamo:u.getPrestamos()) {				
					sesion.delete(prestamo);
				}
				//borra el objeto usuario en la BBDD
				sesion.delete(usuario);
					
				transaccion.commit();
				sesion.refresh(usuario);
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
	public void modificarUsuario(Usuario usuario) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
			//Inicias la transaccion
			transaccion = sesion.beginTransaction();
			//guardas el objeto autor en la BBDD
			sesion.update(usuario);
			
			transaccion.commit();
			sesion.refresh(usuario);
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
