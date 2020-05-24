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
		String idProducto = request.getParameter("idProducto");
				
		//Recupero la lista de productos con su "casting" correspondiente.
		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
		
		if (productos != null) {
		
			// Comprobamos si existe ese ID
		    if(productos.containsKey(idProducto)) {
		    	//Compruebo si tiene comentarios
		    	if ((productos.get(idProducto)).getComentarios() != null && ((productos.get(idProducto)).getComentarios()).size() > 0) {
					request.setAttribute("MSG", "El producto  ["+idProducto+"] no se ha eliminado porque contiene comentarios");
				} else {
					productos.remove(idProducto); // Elimino
					request.getServletContext().setAttribute("ATR_PRODUCTOS", productos); // Actualizo
					
					// Incremento cantidad de productos eliminados por el usuario en esta sesion
					int contDelProducto = (int)request.getSession().getAttribute("PRODUCTOS_DEL");
					request.getSession().setAttribute("PRODUCTOS_DEL", ++contDelProducto);
					request.setAttribute("MSG", "Producto ["+idProducto+"] eliminado");
				}
		    }else {
			request.setAttribute("MSG", "Producto ["+idProducto+"] no encontrado");
		    }
		}
		return "/secured/productos.jsp";
	}
}
