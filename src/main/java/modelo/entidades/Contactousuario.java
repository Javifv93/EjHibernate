package modelo.entidades;
// Generated 27 ene. 2021 10:46:18 by Hibernate Tools 5.2.12.Final

/**
 * Contactousuario generated by hbm2java
 */
public class Contactousuario implements java.io.Serializable {

	private int idUsuario;
	private String email;
	private String telefonoMovil;
	private String telefonoFijo;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Contactousuario() {
	}

	public Contactousuario(int idUsuario, String email, String telefonoMovil, String telefonoFijo) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.telefonoMovil = telefonoMovil;
		this.telefonoFijo = telefonoFijo;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonoMovil() {
		return this.telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Contactousuario))
			return false;
		Contactousuario castOther = (Contactousuario) other;

		return (this.getIdUsuario() == castOther.getIdUsuario())
				&& ((this.getEmail() == castOther.getEmail()) || (this.getEmail() != null
						&& castOther.getEmail() != null && this.getEmail().equals(castOther.getEmail())))
				&& ((this.getTelefonoMovil() == castOther.getTelefonoMovil())
						|| (this.getTelefonoMovil() != null && castOther.getTelefonoMovil() != null
								&& this.getTelefonoMovil().equals(castOther.getTelefonoMovil())))
				&& ((this.getTelefonoFijo() == castOther.getTelefonoFijo())
						|| (this.getTelefonoFijo() != null && castOther.getTelefonoFijo() != null
								&& this.getTelefonoFijo().equals(castOther.getTelefonoFijo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdUsuario();
		result = 37 * result + (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37 * result + (getTelefonoMovil() == null ? 0 : this.getTelefonoMovil().hashCode());
		result = 37 * result + (getTelefonoFijo() == null ? 0 : this.getTelefonoFijo().hashCode());
		return result;
	}

}
