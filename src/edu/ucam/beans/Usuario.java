package edu.ucam.beans;

public class Usuario {
	
	private String idUsu;	// ID del usuario
	private String name;	// Nombre del usuario
	private String surname;	// Apellido del usuario
	private String pass;	// Contrasenna del usuario
	boolean admin;			// Rol de usuario (normal || administrador)
	
	public Usuario(String idUsu, String name, String surname, String pass, boolean admin) {
		super();
		this.idUsu = idUsu;
		this.name = name;
		this.surname = surname;
		this.pass = pass;
		this.admin = admin;
	}
	
	public Usuario(String idUsu, String name, String pass) {
		super();
		this.idUsu = idUsu;
		this.name = name;
		this.pass = pass;
	}

	public String getIdUsu() {
		return idUsu;
	}

	public void setIdUsu(String idUsu) {
		this.idUsu = idUsu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	
}