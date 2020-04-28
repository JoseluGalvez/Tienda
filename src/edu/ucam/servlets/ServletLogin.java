package edu.ucam.servlets;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.beans.Usuario;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Variable para guardar el objeti user en sesión y usarlo para comprobar que está logueado
		public static final String USER_LOGGED = "USER_LOGGED";
	       
	    public ServletLogin() {
	        super();
	    }
	    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Recuperamos parámetros que contienen las credenciales para hacer login
			String idUsu = (request.getParameter("nombre")==null)?"":(request.getParameter("nombre"));
			String pass = (request.getParameter("pass")==null)?"":(request.getParameter("pass"));
					
			String jsp = "/index.jsp"; //jsp de respuesta
			
			// Si no ha introducido credenciales
					if (idUsu=="" || pass=="") {
						System.out.println("Complete los campos [Usuario] y [Contraseña]");
					}else {
						// Recupero usuarios.
					    Hashtable<String, Usuario> usuarios= (Hashtable<String, Usuario>) getServletContext().getAttribute("ATR_USUARIOS");
						// Comprobamos que existe ese ID
					    if(usuarios.containsKey(idUsu)) {
					    	if(usuarios.get(idUsu).getPass().equals(pass)) {
					    		// Usuario y pass correctos
					    		Usuario user = usuarios.get(idUsu);
					    		request.getSession().setAttribute(USER_LOGGED, user);
					    		jsp = "/secured/principal.jsp"; //jsp de respuesta Logueado
					    	}else {
					    		System.out.println("Contraseña del usuario <"+idUsu+"> incorrecta");
					    	}
					    }else {
					    	System.out.println("Usuario <"+idUsu+"> no encontrado.");
					    }
					}
				request.getRequestDispatcher(jsp).forward(request, response);		
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}


		@Override
		public void init() throws ServletException {
			//Creamos el primer usuario Administrador para poder acceder
					String idUsu = "admin";

					Usuario admin = new Usuario (idUsu, "José Luis", "Gálvez", "admin", true);
					Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>();
					
				// Lista de usuarios iniciales
				usuarios.put(idUsu, admin);
				// La agrego al contexto para tenerla accesible por todos
				getServletContext().setAttribute("ATR_USUARIOS", usuarios);	

			super.init();
		}
	}
