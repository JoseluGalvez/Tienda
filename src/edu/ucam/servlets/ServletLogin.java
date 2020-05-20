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
						request.setAttribute("MSG", "Complete los campos [Usuario] y [Contraseña].");

					}else {
						// Recupero usuarios.
					    Hashtable<String, Usuario> usuarios= (Hashtable<String, Usuario>) getServletContext().getAttribute("ATR_USUARIOS");
						// Comprobamos que existe ese ID
					    if(usuarios.containsKey(idUsu)) {
					    	if(usuarios.get(idUsu).getPass().equals(pass)) {
					    		// Usuario y pass correctos
					    		Usuario user = usuarios.get(idUsu);
					    		request.getSession().setAttribute(USER_LOGGED, user);
					    		jsp = "/secured/inicio.jsp"; //jsp de respuesta Logueado
					    		
					    		// inicializo contadores de la etiqueta LOG que llevan el 
					    		// recuento de acciones en la sesion
					    		int contAddProducto=0, contDelProducto=0;
					    		request.getSession().setAttribute("PRODUCTOS_ADD", contAddProducto);
					    		request.getSession().setAttribute("PRODUCTOS_DEL", contDelProducto);

					    	}else {
					    		String mensaje = ("Contraseña del usuario <" + idUsu + "> incorrecta.");
					    		request.setAttribute("MSG", mensaje);
					    	}
					    }else {
					    	String mensaje = ("Usuario <" + idUsu + "> no encontrado.");
					    	request.setAttribute("MSG", mensaje);
					    }
					}
				request.getRequestDispatcher(jsp).forward(request, response);		
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
	}
