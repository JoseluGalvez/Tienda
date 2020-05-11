package edu.ucam.actions;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.beans.Comentario;
import edu.ucam.beans.Producto;

public class AddComentarioAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp ="/secured/comentariosProducto.jsp";
		System.out.println("Acción añadir AddComentarioAction...");
		
		//Compruebo que los campos no tengan NULL
		String idComentario = (request.getParameter("idComentario")==null)?"":(request.getParameter("idComentario"));
		String textoComentario = (request.getParameter("textoComentario")==null)?"":(request.getParameter("textoComentario"));
		String idProducto = (request.getParameter("producto")==null)?"":(request.getParameter("producto"));
		
		// IdVoto = IdComentario+IdUsu
		Hashtable<String, Integer> votos = new Hashtable<String, Integer>() {
			{ put("IdVoto", 0); 
			}
		};
		
		Comentario comentario = new Comentario(idComentario, textoComentario, votos);
		
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
	    	//System.out.println("Comentario ["+idProducto+"] ya existe.");
	    	request.setAttribute("MSG", "Comentario ["+idComentario+"] ya existe, escriba otro diferente.");
	    }else {
		// Añado a la lista el comentario creado con los parámetros recibidos (atributos)
	    	comentarios.put(idComentario, comentario);
		//System.out.println("Comentario ["+idComentario+"] añadido.");
		request.setAttribute("MSG", "Comentario ["+idComentario+"] añadido al producto ["+idProducto+"].");
	    }
		
		return jsp;
	}

}
