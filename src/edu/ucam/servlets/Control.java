package edu.ucam.servlets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.actions.Action;
import edu.ucam.actions.AddComentarioAction;
import edu.ucam.actions.AddProductoAction;
import edu.ucam.actions.AddUsuarioAction;
import edu.ucam.actions.DeleteComentarioAction;
import edu.ucam.actions.DeleteProductoAction;
import edu.ucam.actions.DeleteUsuarioAction;
import edu.ucam.actions.LogoutAction;
import edu.ucam.actions.UpdateComentarioAction;
import edu.ucam.actions.UpdateProductoAction;
import edu.ucam.actions.UpdateUsuarioAction;
import edu.ucam.actions.VotarAction;
import edu.ucam.beans.Usuario;

/**
 * Servlet implementation class Control
 */
@WebServlet(urlPatterns= {"/Control"}, name="Control")
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ACTION_ID = "ACTION_ID";
	
	//Esta tabla hash contiene los objetos de las acciones disponibles.
	private Hashtable<String, Action> actions;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Control() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override  			// Se ejecuta cuando se carga el servlet en memoria
	public void init() throws ServletException {
		super.init();
		
				if (actions == null) {
					
					//Lista de acciones
					actions = new Hashtable<String, Action>();
					actions.put("ADDPRODUCTO", new AddProductoAction());
					actions.put("DELETEPRODUCTO", new DeleteProductoAction());
					actions.put("UPDATEPRODUCTO", new UpdateProductoAction());
					actions.put("ADDUSUARIO", new AddUsuarioAction());
					actions.put("DELETEUSUARIO", new DeleteUsuarioAction());
					actions.put("UPDATEUSUARIO", new UpdateUsuarioAction());
					actions.put("ADDCOMENTARIO", new AddComentarioAction());
					actions.put("UPDATECOMENTARIO", new UpdateComentarioAction());
					actions.put("DELETECOMENTARIO", new DeleteComentarioAction());
					actions.put("VOTAR", new VotarAction());
					actions.put("EXIT", new LogoutAction());
				}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String jsp = "/secured/inicio.jsp"; //p�gina de inicio tras loguearse
		//Comprobamos si existe usuario "Logueado"
		Usuario usuario = (Usuario)request.getSession().getAttribute("USER_LOGGED");
		
		if (usuario != null) {
			//Cogemos el par�metro que llega para identificar la acci�n a realizar
			String actionId = request.getParameter("ACTION_ID");
			Action accion = this.actions.get(actionId); 
			
			//La jsp de respuesta depender� de la acci�n que se ha realizado
			jsp = accion.execute(request, response);
									
		}else { //No se est� "Logueado"
			jsp="/index.jsp";
			request.setAttribute("MSG", "Acceso restringido");
		}
		
		request.getRequestDispatcher(jsp).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
