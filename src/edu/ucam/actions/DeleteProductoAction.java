package edu.ucam.actions;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.beans.Producto;

public class DeleteProductoAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp ="/secured/productos.jsp";
		//System.out.println("Acción eliminar DeleteProductoAction...");
		
		String idProducto = request.getParameter("idProducto");
				
		//Recupero la lista de productos con su "casting" correspondiente.
		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
		
		if (productos != null) {
		
			// Comprobamos si existe ese ID
		    if(productos.containsKey(idProducto)) {
		    	if ((productos.get(idProducto)).getComentarios() != null) {
					//System.out.println("El producto no se ha eliminado porque contiene comentarios");	
					request.setAttribute("MSG", "El producto  ["+idProducto+"] no se ha eliminado porque contiene comentarios");
				} else {
					productos.remove(idProducto);
					//System.out.println("Producto ["+idProducto+"] eliminado.");
					request.setAttribute("MSG", "Producto ["+idProducto+"] eliminado");
				}
		    }else {
			request.setAttribute("MSG", "Producto ["+idProducto+"] no encontrado");
		    }
		}
		return jsp;
	}
}
