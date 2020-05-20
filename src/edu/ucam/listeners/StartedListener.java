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
public class StartedListener implements ServletContextListener{

    /**
     * Default constructor. 
     */
    public StartedListener() {
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
		int contProductos = 01;
		arg0.getServletContext().setAttribute("ATR_CONTPROD", contProductos);
		
		//Creamos el primer usuario Administrador para poder acceder
				String idUsu = "admin";

				Usuario admin = new Usuario (idUsu, "Joselu", "Gálvez Morgado", "admin", true);
				Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>();
				
			// Lista de usuarios iniciales
			usuarios.put(idUsu, admin);
			// La agrego al contexto para tenerla accesible por todos
			arg0.getServletContext().setAttribute("ATR_USUARIOS", usuarios);	
			
			System.out.println("Datos cargados correctamente");
    }
	
}
