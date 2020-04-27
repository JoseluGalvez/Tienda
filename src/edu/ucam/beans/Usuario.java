package edu.ucam.beans;

public class Usuario {
	
	private String idUsu;
	private String name;
	private String surname;
	private String pass;
	
	public Usuario(String idUsu, String name, String surname, String pass) {
		super();
		this.idUsu = idUsu;
		this.name = name;
		this.surname = surname;
		this.pass = pass;
	}
	/**
	 * @return the idUsu
	 */
	public String getIdUsu() {
		return idUsu;
	}
	/**
	 * @param idUsu the idUsu to set
	 */
	public void setIdUsu(String idUsu) {
		this.idUsu = idUsu;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
