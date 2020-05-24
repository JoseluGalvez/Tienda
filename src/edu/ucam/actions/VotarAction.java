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

public class VotarAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
System.out.println("Acción votar...");
		
		//Compruebo que los campos no tengan NULL

		String idProducto = (request.getParameter("idProducto")==null)?"":(request.getParameter("idProducto"));
		String idComentario = (request.getParameter("idComentario")==null)?"":(request.getParameter("idComentario"));
		String estrellas = (request.getParameter("estrellas")==null)?"":(request.getParameter("estrellas"));
		

		
		//Recupero la lista de productos con su "casting" correspondiente.
		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
		// Comprobamos si existe ese Producto
	    if(productos.containsKey(idProducto)) {
	    	// Recupero lista de comentarios del producto
			Hashtable <String, Comentario> comentarios = (Hashtable <String, Comentario>) (productos.get(idProducto).getComentarios());
			if(comentarios.containsKey(idComentario)) {
				// Recupero lista de votos del comentario
				Hashtable<String, Integer> votos = (Hashtable <String, Integer>) (comentarios.get(idComentario).getVotos());
				
				if (votos == null) {
					votos = new Hashtable<String, Integer>();
				}
				
				// GENERO IdVoto = IdUsu *Un voto por mensaje y usuario.
				Usuario usuario = (Usuario)((HttpServletRequest) request).getSession().getAttribute(ServletLogin.USER_LOGGED); 
				String idVoto = usuario.getIdUsu(); // ID usuario logueado que vota
				
				//Llamo a método que comprueba si es número
				if (isNumeric(estrellas)==true) { 
					int votacion = (estrellas==null)?0:Integer.parseInt(estrellas);
					// Añado
					votos.put(idVoto, votacion);
					request.setAttribute("MSG", "Voto realizado al comnetario ["+idComentario+"].");
				} else {
			    	request.setAttribute("MSG", "Fallo en el voto");
			    }
	
			}  else {
		    	request.setAttribute("MSG", "El comentario ["+idComentario+"] ya no existe.");
		    }
					
	    } else {
	    	request.setAttribute("MSG", "El producto ["+idProducto+"] ya no existe.");
	    }

		return "/secured/comentariosProducto.jsp";
	}
// Método que comprueba si es número
	public static boolean isNumeric(String cadena) {
	   boolean resultado;
	   try {
	      Integer.parseInt(cadena);
	      resultado = true;
	   } catch (NumberFormatException excepcion) {
	       resultado = false;
	   }
	   return resultado;
	}
}
