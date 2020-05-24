package edu.ucam.actions;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.beans.Usuario;

public class UpdateUsuarioAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp = "/secured/usuarios.jsp";
		//System.out.println("Entro en UpdateUsuarioAction...");
		
		//Comprobamos que los campos no tengan valor null
		String idUsu = (request.getParameter("nombre")==null)?"":(request.getParameter("nombre"));
		String name = (request.getParameter("name")==null)?"":(request.getParameter("name"));
		String surname = (request.getParameter("surname")==null)?"":(request.getParameter("surname"));
		String pass = (request.getParameter("pass")==null)?"":(request.getParameter("pass"));

		boolean admin = ((request.getParameter("tipo")).equals("administrador"))?true:(false);

		
		//Declaro la lista de usuarios con su "casting" correspondiente.
		Usuario usuario = new Usuario(idUsu, name, surname, pass, admin);

		Hashtable <String, Usuario> usuarios = (Hashtable <String, Usuario>)request.getServletContext().getAttribute("ATR_USUARIOS");
		
		//Si no tengo la lista de usuarios la creo y la guardo en el contexto
		if(usuarios == null) {
			usuarios = new Hashtable<String,Usuario>();		
			request.getServletContext().setAttribute("ATR_USUARIOS", usuarios);
		}
		
		// Añado a la lista el usuario creado con los parámetros recibidos (atributos)
		usuarios.put(idUsu, usuario);
		request.getServletContext().setAttribute("ATR_USUARIOS", usuarios); // Actualizo

		request.setAttribute("MSG", "Usuario ["+idUsu+"] modificado.");
		return jsp;
	}

	}
