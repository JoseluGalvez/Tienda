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
				//Cogemos el parámetro que llega para identificar la acción a realizar
				String actionId = request.getParameter(Control.ACTION_ID);
				if (usuario != null && actionId != null) {
					
					 if(accionesAdmin.containsKey(actionId)) {
						 if(usuario.isAdmin()) {
							 chain.doFilter(request, response);
							 System.out.println("Acción para administrador, usuario administrador. Dejo pasar.");
						 }else {
							request.setAttribute("MSG", "Debe ser administrador");
							request.getRequestDispatcher("/index.jsp").forward(request, response);
						 }
					 }else { // No es acción de Administrador
						 chain.doFilter(request, response);
						 System.out.println("No es acción para administrador. Dejo pasar.");
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
