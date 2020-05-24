package edu.ucam.actions;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.beans.Comentario;
import edu.ucam.beans.Producto;
import edu.ucam.beans.Usuario;

public class AddProductoAction extends Action {

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp ="/secured/productos.jsp";
		
		//Compruebo que los campos no tengan NULL
		String idProducto = (request.getParameter("idProducto")==null)?"":(request.getParameter("idProducto"));
		String nombreProducto = (request.getParameter("nombreProducto")==null)?"":(request.getParameter("nombreProducto"));
		Hashtable <String, Comentario> comentarios = new Hashtable <String, Comentario>();
		
		
		//Declaro la lista de productos con su "casting" correspondiente.
		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
		
		//Si no tengo la lista de productos la creo y la guardo en el contexto
		if (productos == null) {
			productos = new Hashtable<String, Producto>();
			request.getServletContext().setAttribute("ATR_PRODUCTOS", productos);
		}
		
		// Comprobamos si existe ese ID
	    if(productos.containsKey(idProducto)) {

	    	request.setAttribute("MSG", "Producto ["+idProducto+"] ya existe, escriba otro diferente.");
	    }else { // Genero el producto con los parámetros recibidos (atributos) y lo añado a la lista
	    Producto producto = new Producto(idProducto, nombreProducto, comentarios);
		productos.put(idProducto, producto);
		// Actualizo la lista en el contexto
		request.getServletContext().setAttribute("ATR_PRODUCTOS", productos);
		
		//Incremento cantidad de productos añadidos por el usuario en esta sesión
		int contAddProducto = (int)request.getSession().getAttribute("PRODUCTOS_ADD");
		request.getSession().setAttribute("PRODUCTOS_ADD", ++contAddProducto);

		request.setAttribute("MSG", "Producto ["+idProducto+"] añadido.");
	    }
		
		return jsp;
	}
}
