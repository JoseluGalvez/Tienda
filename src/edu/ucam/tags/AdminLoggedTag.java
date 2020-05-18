package edu.ucam.tags;

import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import edu.ucam.beans.Usuario;
import edu.ucam.servlets.ServletLogin;

public class AdminLoggedTag extends BodyTagSupport {

	@Override
	public int doStartTag() throws JspException {
		
		Hashtable <String, Usuario> usuarios = (Hashtable <String, Usuario>)this.pageContext.getServletContext().getAttribute("ATR_USUARIOS");
		Usuario userAdmin = (Usuario) this.pageContext.getSession().getAttribute(ServletLogin.USER_LOGGED);

		if (usuarios != null && userAdmin.isAdmin()) {
				return this.EVAL_BODY_INCLUDE;
			}else {
				try {
					this.pageContext.getOut().println("Acceso exclusivo a administradores. ");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return this.SKIP_BODY;
			}
	}
}