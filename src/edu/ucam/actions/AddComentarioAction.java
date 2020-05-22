package edu.ucam.actions;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.beans.Comentario;
import edu.ucam.beans.Producto;
import edu.ucam.beans.Usuario;
import edu.ucam.servlets.ServletLogin;

public class AddComentarioAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp ="/secured/comentariosProducto.jsp";
		System.out.println("Acción añadir AddComentarioAction...");
		
		//Compruebo que los campos no tengan NULL
		//String idComentario = (request.getParameter("idComentario")==null)?"":(request.getParameter("idComentario"));
		String textoComentario = (request.getParameter("textoComentario")==null)?"":(request.getParameter("textoComentario"));
		String idProducto = (request.getParameter("idProducto")==null)?"":(request.getParameter("idProducto"));
		
		// IdVoto = IdUsu *Un voto por mensaje y usuario.
		Hashtable<String, Integer> votos = new Hashtable<String, Integer>() {
			{ put("IdVoto", 0); 
			}
		};

		Usuario usuario = (Usuario)((HttpServletRequest) request).getSession().getAttribute(ServletLogin.USER_LOGGED);
		String autor = usuario.getIdUsu(); // ID usuario logueado que añade el comentario
		
		int atrContComentarios =(int)request.getServletContext().getAttribute("ATR_ CONTCOMENT");
		String idComentario = atrContComentarios +"_"+ autor; //Concateno contador de mensajes con id del usuario
		request.getServletContext().setAttribute("ATR_ CONTCOMENT", ++atrContComentarios);
		// Incremento el contador y lo guardo en el contexto actualizando ATR_CONTCOMENT
		
		Comentario comentario = new Comentario(idComentario, textoComentario, autor, votos);
		
		//Declaro la lista de comentarios con su "casting" correspondiente.
		Hashtable <String, Comentario> comentarios = (Hashtable <String, Comentario>)request.getServletContext().getAttribute("ATR_COMENTARIOS");
		
		//Si no tengo la lista de comentarios la creo y la guardo en el contexto
		//Debe ser una lista diferente por producto
		if (comentarios == null) {
			
			comentarios = new Hashtable<String, Comentario>();
// CREAR LISTA PARA CADA PRODUCTO			
			//String ATR_COMENTARIOS = "ATR_COMENTARIOS"+idProducto;
			
			request.getServletContext().setAttribute("ATR_COMENTARIOS"+idProducto, comentarios);
		}
		
		// Comprobamos si existe ese ID
	    if(comentarios.containsKey(idComentario)) {
	    	request.setAttribute("MSG", "Comentario ["+idComentario+"] ya existe, escriba otro diferente.");
	    }else {
		// Añado a la lista el comentario creado con los parámetros recibidos (atributos)
	    	comentarios.put(idComentario, comentario);
		request.setAttribute("MSG", "Comentario ["+idComentario+"] añadido al producto ["+idProducto+"].");
	    }
		
		return jsp;
	}

}
