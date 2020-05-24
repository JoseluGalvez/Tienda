package edu.ucam.actions;

import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.beans.Comentario;
import edu.ucam.beans.Producto;

public class UpdateProductoAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp ="/secured/productos.jsp";
		//Compruebo que los campos no tengan NULL
		String idProducto = (request.getParameter("idProducto")==null)?"":(request.getParameter("idProducto"));
		String nombreProducto = (request.getParameter("nombreProducto")==null)?"":(request.getParameter("nombreProducto"));
		
		//Declaro la lista de productos con su "casting" correspondiente.
		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
		// Recupero comentarios del producto para no perderlos
		Hashtable <String, Comentario> comentarios = (Hashtable <String, Comentario>) (productos.get(idProducto).getComentarios());		
		//Si no tengo la lista de comentarios la creo
				if (comentarios == null) {
					comentarios = new Hashtable<String, Comentario>();
				}
		Producto producto = new Producto(idProducto, nombreProducto, comentarios);
		
		// Añado a la lista el producto modificado con los parámetros recibidos (atributos)
				productos.put(idProducto, producto);
				request.getServletContext().setAttribute("ATR_PRODUCTOS", productos); // Actualizo
				request.setAttribute("MSG", "Producto ["+idProducto+"] modificado.");
		return jsp;
	}
}
