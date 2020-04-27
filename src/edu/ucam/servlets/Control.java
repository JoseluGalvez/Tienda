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
import edu.ucam.actions.AddProductoAction;
import edu.ucam.actions.DeleteProductoAction;
import edu.ucam.actions.UpdateProductoAction;
import edu.ucam.beans.Usuario;

/**
 * Servlet implementation class Control
 */
@WebServlet("/Control")
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
				}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String jsp = "/principal.jsp"; //página de inicio tras loguearse
		//Comprobamos si existe usuario "Logueado"
		Usuario usuario = (Usuario)request.getSession().getAttribute("USUARIO_LOGED");
		
		if (usuario != null) {
			//Cogemos el parámetro que llega para identificar la acción a realizar
			String actionId = request.getParameter("ACTION_ID");
			Action accion = this.actions.get(actionId); 
			
			//La jsp de respuesta dependerá de la acción que se ha realizado
			jsp = accion.execute(request, response);
									
		}else { //No se está "Logueado"
			jsp="/index.jsp";
		}
		
		request.getRequestDispatcher(jsp).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
}
