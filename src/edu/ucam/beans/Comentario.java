package edu.ucam.beans;

import java.util.Hashtable;

public class Comentario {
	
	private String idComentario;
	private String textoComentario;
	private Hashtable<String, Integer> votos;
	
	
	public Comentario(String idComentario, String textoComentario, Hashtable<String, Integer> votos) {
		super();
		this.idComentario = idComentario;
		this.textoComentario = textoComentario;
		this.votos = votos;
	}
	
	

	public String getIdComentario() {
		return idComentario;
	}


	public void setIdComentario(String idComentario) {
		this.idComentario = idComentario;
	}


	public String getTextoComentario() {
		return textoComentario;
	}


	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}


	public Hashtable<String, Integer> getVotos() {
		return votos;
	}


	public void setVotos(Hashtable<String, Integer> votos) {
		this.votos = votos;
	}

}
