package edu.ucam.filters;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import edu.ucam.beans.Usuario;
import edu.ucam.servlets.Control;
import edu.ucam.servlets.ServletLogin;

/**
 * Servlet Filter implementation class FiltroAdmin
 */
@WebFilter(filterName = "FiltroAdmin", servletNames = {"Control"})
public class FiltroAdmin implements Filter {
	Hashtable<String, String> accionesAdmin =new Hashtable<String,String>();
	
    public FiltroAdmin() {

    	// Acciones de administrador. Zona privada
    	accionesAdmin.put("ADDUSUARIO", "Para acceder a AddUsuarioAction");
    	accionesAdmin.put("DELETEUSUARIO", "Para acceder a DeleteUsuarioAction");
    	accionesAdmin.put("UPDATEUSUARIO", "Para acceder a UpdateUsuarioAction");
    	accionesAdmin.put("DELETECOMENTARIO", "Para acceder a DeleteComentarioAction");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("FILTRO FiltroAdmin");
		//Comprobamos si existe usuario "Logueado"
				Usuario usuario = (Usuario)((HttpServletRequest) request).getSession().getAttribute(ServletLogin.USER_LOGGED);
				//Cogemos el par�metro que llega para identificar la acci�n a realizar
				String actionId = request.getParameter(Control.ACTION_ID);
				if (usuario != null && actionId != null) {
					
					 if(accionesAdmin.containsKey(actionId)) {
						 if(usuario.isAdmin()) { //Acci�n para administrador, usuario administrador. Dejo pasar.
							 chain.doFilter(request, response);
							 System.out.println("Usuario administrador, acci�n permitida.");
						 }else { //Acci�n para administrador, usuario NO administrador. NO dejo pasar.
							request.setAttribute("MSG", "Debe ser administrador");
							System.out.println("Acci�n s�lo para administradores");
							if(actionId.equals("DELETECOMENTARIO")) {
								request.getRequestDispatcher("/secured/comentariosProducto.jsp").forward(request, response);
							}else {
								request.getRequestDispatcher("/index.jsp").forward(request, response);
							}
							
						 }
					 }else { // No es acci�n para administrador. Dejo pasar.
						 chain.doFilter(request, response);
						 System.out.println("Acci�n permitida.");
					 }					
				}else {
					request.setAttribute("MSG", "Haga LOGIN para acceder");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
