package datos.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.configuracion.Conexion;
import modelo.entidades.Autor;
import modelo.entidades.Libro;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;

public class UsuarioDAO {
	
	/**Inserta un Usuario en base a un objeto Usuario*/
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
			//sesion.refresh(usuario);
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
	
	/**Obtiene un objeto Usuario en base a un ID*/
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
//			Usuario u = obtenerUsuarioPorID(usuario.getIdUsuario());
//			if(u!=null) {
				//Inicias la transacción
				transaccion = sesion.beginTransaction();
					
//				//Como Usuario tiene una lista de Prestamos, la recorro y los borro uno a uno en cascada
//				for(Prestamo prestamo:u.getPrestamos()) {				
//					sesion.delete(prestamo);
//				}
				//borra el objeto usuario en la BBDD
				sesion.delete(usuario);
					
				transaccion.commit();
//				sesion.refresh(usuario);
//			}
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
	public void queryObtenerUsuariosPorDNI() {
		try (Session sesion = Conexion.obtenerSesion() ) {
			System.out.println("Introduce el DNI del usuario");
			Scanner sc = new Scanner(System.in);
			int dni = Integer.valueOf(sc.nextInt());
			Query<Usuario> q = sesion.createQuery("FROM Usuario WHERE idUsuario like :dniUsuario");
			q.setParameter("dniUsuario", dni);
			q.setReadOnly(true);
			
			Usuario user = q.getSingleResult();
			System.out.println("Resultado: " + user.getIdUsuario());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void queryObtenerUsuariosQueTienenLibrosEnLosPrestamos() {
		try (Session sesion = Conexion.obtenerSesion() ) {
			Query<Usuario> q = sesion.createQuery("SELECT u FROM Usuario u, Prestamo p WHERE u.idUsuario = p.usuario.idUsuario");
			q.setReadOnly(true);
			
			List<Usuario> lista = q.getResultList();
			System.out.println("Resultado: ");
			for(Usuario u:lista) {
				System.out.println("- " + u.getNombre());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void queryObtenerUsuariosMenoresDeEdad() {
		try (Session sesion = Conexion.obtenerSesion() ) {
			Query<Usuario> q = sesion.createQuery("FROM Usuario WHERE FechaNacimiento < :fechaMayorEdad");
			q.setParameter(
					"fechaMayorEdad", LocalDate.of((
							LocalDate.now().getYear() - 10), 
							LocalDate.now().getMonth(),
							LocalDate.now().getDayOfMonth())
					);
			q.setReadOnly(true);
			
			List<Usuario> lista = q.getResultList();
			System.out.println("Resultado: ");
			for(Usuario u:lista) {
				System.out.println("- " + u.getNombre() + " " + u.getFechaNacimiento());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
