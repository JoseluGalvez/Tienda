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
		System.out.println("Acción añadir AddComentarioAction...");
		
		//Compruebo que los campos no tengan NULL
		String textoComentario = (request.getParameter("textoComentario")==null)?"":(request.getParameter("textoComentario"));
		String idProducto = (request.getParameter("idProducto")==null)?"":(request.getParameter("idProducto"));
		
		// GENERO LISTA INICIAL DE VOTOS IdVoto = IdUsu *Un voto por mensaje y usuario.
		Hashtable<String, Integer> votos = new Hashtable<String, Integer>();

		Usuario usuario = (Usuario)((HttpServletRequest) request).getSession().getAttribute(ServletLogin.USER_LOGGED);
		String autor = usuario.getIdUsu(); // ID usuario logueado que añade el comentario
		
		// GENERO ID COMENTARIO 
		int atrContComentarios = (int) request.getServletContext().getAttribute("ATR_CONTCOMENT");
		String idComentario = "Coment_"+ atrContComentarios; //Concateno 
		request.getServletContext().setAttribute("ATR_CONTCOMENT", ++atrContComentarios); 
		// Incremento el contador y lo guardo en el contexto actualizando ATR_CONTCOMENT
		
		Comentario comentario = new Comentario(idComentario, textoComentario, autor, votos);
		
		//Recupero la lista de productos con su "casting" correspondiente.
		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
		// Comprobamos si existe ese Producto
	    if(productos.containsKey(idProducto)) {
	    	// Recupero lista de comentarios del producto
			Hashtable <String, Comentario> comentarios = (Hashtable <String, Comentario>) (productos.get(idProducto).getComentarios());
			// Añado
			comentarios.put(idComentario, comentario);
			// Actualizo
			productos.get(idProducto).setComentarios(comentarios);

			request.setAttribute("MSG", "Comentario ["+idComentario+"] añadido al producto ["+idProducto+"].");
	    		
	    } else {
	    	request.setAttribute("MSG", "El producto ["+idProducto+"] ya no existe.");
	    }

		return "/secured/comentariosProducto.jsp";
	}

}
