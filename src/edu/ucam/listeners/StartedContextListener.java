package edu.ucam.listeners;

import java.util.Hashtable;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;

import edu.ucam.beans.Usuario;

/**
 * Application Lifecycle Listener implementation class StartedListener
 *
 */
@WebListener
public class StartedContextListener implements ServletContextListener{

    /**
     * Default constructor. 
     */
    public StartedContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)   { 
		// Inicializo contador de productos y guardo en contexto para 
		// que sean accesible desde todas las conexiones de usuarios.
		// Lo puedo utilizar para generar la ID del producto.

    	arg0.getServletContext().setAttribute("ATR_CONTPROD", 01);
		
		//int contComentarios = 01; //Inicializo el contador de comentarios para generar las ID
		arg0.getServletContext().setAttribute("ATR_CONTCOMENT", 01);

		
		//Creamos el primer usuario Administrador para poder acceder
				String idUsu = "admin";

				Usuario admin = new Usuario (idUsu, "Jose Luis", "Gálvez", "admin", true);
				Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>();
				
			// Lista de usuarios iniciales
			usuarios.put(idUsu, admin);
			// La agrego al contexto para tenerla accesible por todos
			arg0.getServletContext().setAttribute("ATR_USUARIOS", usuarios);	
			
			System.out.println("Datos cargados correctamente");
    }
	
}
