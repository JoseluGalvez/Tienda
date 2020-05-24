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

public class DeleteComentarioAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Acción eliminar DeleteComentarioAction...");
				
		//Compruebo que los campos no tengan NULL
		String idProducto = (request.getParameter("idProducto")==null)?"":(request.getParameter("idProducto"));
		String idComentario = (request.getParameter("idComentario")==null)?"":(request.getParameter("idComentario"));

		//Recupero la lista de productos con su "casting" correspondiente.
		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
		
		if(productos.containsKey(idProducto)) { // Comprobamos si existe ese Producto
												// Recupero lista de comentarios del producto
			Hashtable <String, Comentario> comentarios = (Hashtable <String, Comentario>) (productos.get(idProducto).getComentarios());
			
				comentarios.remove(idComentario);	// Elimino
				productos.get(idProducto).setComentarios(comentarios);	// Actualizo lista comentarios
				request.getServletContext().setAttribute("ATR_PRODUCTOS", productos); // Actualizo lista productos

				request.setAttribute("MSG", "Comentario ["+idComentario+"] eliminado del producto ["+idProducto+"].");

					
		    } else {
		    	request.setAttribute("MSG", "El producto ["+idProducto+"] ya no existe.");
			}
		
		
		return "/secured/comentariosProducto.jsp";
	}

}
