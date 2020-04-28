package edu.ucam.actions;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.beans.Usuario;

public class AddUsuarioAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp ="/secured/usuarios.jsp";
		System.out.println("Entro en AddUsuarioAction...");
		
		//Comprobamos que los campos no tengan valor null
		String idUsu = (request.getParameter("nombre")==null)?"":(request.getParameter("nombre"));
		String name = (request.getParameter("name")==null)?"":(request.getParameter("name"));
		String surname = (request.getParameter("surname")==null)?"":(request.getParameter("surname"));
		String pass = (request.getParameter("pass")==null)?"":(request.getParameter("pass"));

		boolean admin = ((request.getParameter("tipo")).equals("administrador"))?true:(false);
		
		Usuario usuario = new Usuario(idUsu, name, surname, pass, admin);
		
		//Declaro la lista de usuarios con su "casting" correspondiente.
		Hashtable <String, Usuario> usuarios = (Hashtable <String, Usuario>)request.getServletContext().getAttribute("ATR_USUARIOS");
		
		//Si no tengo la lista de usuarios la creo y la guardo en el contexto
		if (usuarios == null) {
			usuarios = new Hashtable<String, Usuario>();
			request.getServletContext().setAttribute("ATR_USUARIOS", usuarios);
		}
		
		// Comprobamos si existe ese ID
	    if(usuarios.containsKey(idUsu)) {
	    	System.out.println("Nick en uso, escriba otro diferente.");
	    }else {
			// Añado a la lista el usuario creado con los parámetros recibidos (atributos)
			usuarios.put(idUsu, usuario);
			System.out.println("Usuario ["+idUsu+"] añadido.");
	    }
		return jsp;
	}

}
