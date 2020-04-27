package edu.ucam.beans;

import java.util.Hashtable;

public class Producto {
	
	
	private String idProducto;
	private String nombreProducto;
	private Hashtable<String, String> comentarios;
	private int votos;
	
	public Producto(String idProducto, String nombreProducto, Hashtable<String, String> comentarios, int votos) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.comentarios = comentarios;
		this.votos = votos;
	}
	
	/**
	 * @return the idProducto
	 */
	public String getIdProducto() {
		return idProducto;
	}
	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}
	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}
	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	/**
	 * @return the comentarios
	 */
	public Hashtable<String, String> getComentarios() {
		return comentarios;
	}
	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(Hashtable<String, String> comentarios) {
		this.comentarios = comentarios;
	}
	/**
	 * @return the votos
	 */
	public int getVotos() {
		return votos;
	}
	/**
	 * @param votos the votos to set
	 */
	public void setVotos(int votos) {
		this.votos = votos;
	}

}
