package edu.ucam.tags;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.ucam.beans.Comentario;
import edu.ucam.beans.Producto;

public class ListarComentariosTag extends TagSupport{

	@Override
	public int doStartTag() throws JspException {
		
/*		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");

		//Recuperamos los comentarios del producto
		Hashtable <String, Comentario> comentarios = (Hashtable <String, Comentario>) (productos.get(request.getParameter("idProducto")).getComentarios());

			//Añadimos datos de prueba
			Hashtable <String, Integer> votos1 = new Hashtable <String, Integer>();
			votos1.put("VotoUsu1", 1);
			votos1.put("VotoUsu2", 5);
			votos1.put("VotoUsu3", 2);
			
			Hashtable <String, Integer> votos2 = new Hashtable <String, Integer>();
			votos2.put("VotoUsu1", 3);
			votos2.put("VotoUsu2", 3);
			votos2.put("VotoUsu3", 3);
			
			Comentario coment = new Comentario ("idComentario", "Texto del comentario", "Autor del coment", votos1);
			Comentario coment2 = new Comentario ("id2", "Mi opinion es poco valorable", "Redactor", votos2);
			comentarios.put("idComentario", coment);
			comentarios.put("id2", coment2);
			
			productos.get(request.getParameter("idProducto")).setComentarios(comentarios);
			

			if (comentarios != null && comentarios.size() > 0){
				Comentario comentario;
				out.println("<table><tr>"
						+"<th>[Eliminar]</th>"
						+"<th>[ Autor ]</th>"
						+"<th>[  Texto  ]</th>"
						+"<th>[Modificar]</th>"
						+"<th>[  Votos  ]</th></tr>");

				for(Enumeration e = comentarios.elements(); e.hasMoreElements();){
					comentario = (Comentario)e.nextElement();

					out.println("<tr>"
					+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/Control?ACTION_ID=DELETECOMENTARIO&idComentario="+ comentario.getIdComentario()+"&idProducto="+request.getParameter("idProducto")+"\">X </a></td>"
					+"<td align=\"CENTER\"> "+comentario.getAutor()+" </td>"
					+"<td align=\"CENTER\"> "+comentario.getTextoComentario()+" </td>"
					+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/secured/updateComentario.jsp?idProducto="+request.getParameter("idProducto")+"&idComentario="+comentario.getIdComentario()+"\"> >> </a></td>"
					+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/secured/votos.jsp?idComentario="+comentario.getIdComentario()+"&idProducto="+request.getParameter("idProducto")+"\">Listar Votos</a></td>"
					
							+"<td align=\"CENTER\" ><form action="+request.getContextPath()+"/Control\" method=\"POST\">"
							+"<input type=\"hidden\" name=\"ACTION_ID\" value=\"VOTAR\"/>"
							+"<input type=\"hidden\" name=\"idComentario\" value=\""+comentario.getIdComentario()+"\"/>"
							+"<p class=\"clasificacion\">"
							+"<input type=\"submit\" value=\"VOTAR\">"
							+"<input id=\"radio1"+comentario.getIdComentario()+"\" type=\"radio\" name=\"estrellas\" value=\"5\">"
							+"<label for=\"radio1"+comentario.getIdComentario()+"\">&#9733;</label>"
							+"<input id=\"radio2"+comentario.getIdComentario()+"\" type=\"radio\" name=\"estrellas\" value=\"4\">"
							+"<label for=\"radio2"+comentario.getIdComentario()+"\">&#9733;</label>"
							+"<input id=\"radio3"+comentario.getIdComentario()+"\" type=\"radio\" name=\"estrellas\" value=\"3\">"
							+"<label for=\"radio3"+comentario.getIdComentario()+"\">&#9733;</label>"
							+"<input id=\"radio4"+comentario.getIdComentario()+"\" type=\"radio\" name=\"estrellas\" value=\"2\">"
							+"<label for=\"radio4"+comentario.getIdComentario()+"\">&#9733;</label>"
							+"<input id=\"radio5"+comentario.getIdComentario()+"\" type=\"radio\" name=\"estrellas\" value=\"1\">"
							+"<label for=\"radio5"+comentario.getIdComentario()+"\">&#9733;</label>"
							+"<input id=\"radio0"+comentario.getIdComentario()+"\" type=\"radio\" name=\"estrellas\" value=\"0\" checked>"
							+"</p>"
							+"</form></td>"
					+"</tr>");	
				}
				out.println("</table>");
			}else{
				out.println("   - = No hay comentarios. = -");
			}
	*/	return this.EVAL_PAGE;
		

	}
	
	public void setNombre(String nombre) {
		//this.nombre = nombre;
		}
	
	
}