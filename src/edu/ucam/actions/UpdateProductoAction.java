package edu.ucam.actions;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.beans.Producto;

public class UpdateProductoAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp ="/secured/principal.jsp";
		System.out.println("Acción modificar UpdateProductoAction...");
		
		//Compruebo que los campos no tengan NULL
		String idProducto = (request.getParameter("idProducto")==null)?"":(request.getParameter("idProducto"));
		String nombreProducto = (request.getParameter("nombreProducto")==null)?"":(request.getParameter("nombreProducto"));
				
		Producto producto = new Producto(idProducto, nombreProducto, null, 0);
		
		//Declaro la lista de productos con su "casting" correspondiente.
		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
		
		//Si no tengo la lista de productos la creo y la guardo en el contexto
		if (productos == null) {
			
			productos = new Hashtable<String, Producto>();
			
			request.getServletContext().setAttribute("ATR_PRODUCTOS", productos);
		}
		
		// Añado a la lista el producto modificado con los parámetros recibidos (atributos)
				productos.put(idProducto, producto);
				System.out.println("Producto ["+idProducto+"] modificado.");
		return jsp;
	}

}
