package edu.ucam.beans;

import java.util.Hashtable;

public class Producto {
	
	
	private String idProducto;
	private String nombreProducto;
	private Hashtable<String, Comentario> comentarios;
	//private int valoracion;
	
	public Producto(String idProducto, String nombreProducto, Hashtable<String, Comentario> comentarios) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.comentarios = comentarios;
		//this.valoracion = valoracion;
	}
	

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Hashtable<String, Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Hashtable<String, Comentario> comentarios) {
		this.comentarios = comentarios;
	}

/**
*	public int getValoracion() {
*		return valoracion;
*	}
*
*	public void setValoracion(int valoracion) {
*		this.valoracion = valoracion;
*	}
*/
	
}
