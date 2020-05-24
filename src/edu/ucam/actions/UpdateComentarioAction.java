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

public class UpdateComentarioAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Acción añadir AddComentarioAction...");
		
		//Compruebo que los campos no tengan NULL
		String textoComentario = (request.getParameter("textoComentario")==null)?"":(request.getParameter("textoComentario"));
		String idProducto = (request.getParameter("idProducto")==null)?"":(request.getParameter("idProducto"));
		String idComentario = (request.getParameter("idComentario")==null)?"":(request.getParameter("idComentario"));

		//Recupero la lista de productos con su "casting" correspondiente.
		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
		// Comprobamos si existe ese Producto
	    if(productos.containsKey(idProducto)) {
	    	// Recupero lista de comentarios del producto
			Hashtable <String, Comentario> comentarios = (Hashtable <String, Comentario>) (productos.get(idProducto).getComentarios());
			// Recupero lista de votos del comentario
			Hashtable<String, Integer> votos = (Hashtable <String, Integer>) (comentarios.get(idComentario).getVotos());
			
			String autor = comentarios.get(idComentario).getAutor(); // Autor del comentario
			
			Usuario usuario = (Usuario)((HttpServletRequest) request).getSession().getAttribute(ServletLogin.USER_LOGGED);
			String usuarioReg = usuario.getIdUsu(); // ID usuario logueado que quiere modificar
			
			if(usuarioReg.equals(autor)) { // Si el usuario es el autor del comentario
				// Modifico
				Comentario comentario = new Comentario(idComentario, textoComentario, autor, votos);
				comentarios.put(idComentario, comentario);
				// Actualizo
				productos.get(idProducto).setComentarios(comentarios);
				request.setAttribute("MSG", "Comentario ["+idComentario+"] modificado.");				
			} else {
				request.setAttribute("MSG", "Debes de ser el autor del comentario ["+idComentario+"].");				
			}
	    		
	    } else {
	    	request.setAttribute("MSG", "El producto ["+idProducto+"] ya no existe.");
	    }

		return "/secured/comentariosProducto.jsp";
	}
}
