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
			//Comprueba que el Usuario con id pasada exista, y da un error en caso de que no sea as�
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
	/**Borra el Usuario pasado por parametro de la BBDD*/
	public void borrarUsuario(Usuario usuario) {
		Transaction transaccion = null;
		/**Try-whit-resources: Inicia la Session y al salir del try se cierra sola*/
		try (Session sesion = Conexion.obtenerSesion() ) {
//			Usuario u = obtenerUsuarioPorID(usuario.getIdUsuario());
//			if(u!=null) {
				//Inicias la transacci�n
				transaccion = sesion.beginTransaction();
					
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
	/**Modifica el Usuario pasado por parametro en la BBDD*/
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
	/**Hace una consulta en la BBDD para obtener el Usuario con el ID obtenido por consola*/
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
	/**Hace una consulta en la BBDD para obtener un listado de Usuarios que tienen libros en prestamo*/
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
	/**Hace una consulta en la BBDD para obtener un listado de Usuarios menores de 18 a�os*/
	public void queryObtenerUsuariosMenoresDeEdad() {
		try (Session sesion = Conexion.obtenerSesion() ) {
			Query<Usuario> q = sesion.createQuery("FROM Usuario WHERE FechaNacimiento < :fechaMayorEdad");
			//Paso un LocalDate que, en el metodo .of obtiene la fecha de LocalDate.now y le resta 18 a�os
			// ******* EN LA BBDD NO HAY MAYORES DE 18 A�OS. PUEDES BAJAR LA EDAD DE LA BUSQUEDA O INTRODUCIR UN USUARIO MAYOR DE EDAD
			q.setParameter(		
					"fechaMayorEdad", LocalDate.of((
							LocalDate.now().getYear() - 18), 
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
