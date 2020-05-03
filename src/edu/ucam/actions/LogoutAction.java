package edu.ucam.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction extends Action {

@Override
public String execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			//Para cerrar la sesión lo único que hacemos es quitar el objeto que utilizamos para verificar que existe.
				request.getSession().removeAttribute("USER_LOGGED");
				request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
				return null;
			}
}