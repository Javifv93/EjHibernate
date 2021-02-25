package datos.configuracion;

import org.hibernate.Session;

/**Clase que llama a Util Hibernate para realizar la conexion con la base de datos. Devuelve un objeto tipo Session*/
public class Conexion {
	public static Session obtenerSesion() {
		return UtilHibernate.getSessionFactory().openSession();
	}

}
