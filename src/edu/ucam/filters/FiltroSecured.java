package edu.ucam.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import edu.ucam.servlets.ServletLogin;

/**
 * Servlet Filter implementation class FiltroSecured
 */
@WebFilter("/secured/*")
public class FiltroSecured implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroSecured() {
        // TODO Auto-generated constructor stub
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

		if((request instanceof HttpServletRequest) && (((HttpServletRequest)request).getSession().getAttribute(ServletLogin.USER_LOGGED) == null) ) {
			request.setAttribute("MSG", "Haga LOGIN para acceder");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
