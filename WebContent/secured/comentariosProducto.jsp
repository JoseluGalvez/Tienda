<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="edu.ucam.beans.Producto"%>
<%@ page import="edu.ucam.beans.Comentario"%>
<%@ page import="edu.ucam.beans.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>COMENTARIOS</title>
<link rel="stylesheet" href="css/estrellas.css">
</head>

<body>
<%@ include file="cabecera.jsp"%>
<%
//Cogemos el parámetro que llega para identificar el producto
String ProductoId = request.getParameter("idProducto");
Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
String ProductoNombre = (productos.get(request.getParameter("idProducto")).getNombreProducto());


%>
<form action="<%=request.getContextPath()%>/Control" method="POST">
<!-- hidden para que no se vea el campo que contiene la acción -->
	<input type="hidden" name="ACTION_ID" value="ADDCOMENTARIO"/>
	<input type="hidden" name="idProducto" value="<%= request.getParameter("idProducto") %>"/>
	<fieldset>
		<legend> Nuevo comentario para  <%= ProductoNombre %></legend>
		<!-- <p> /*El ID lo genero en el servlet*/
		<label for="idComentario">Identificador del comentario: </label>
		<input type="text" name="idComentario" id="idComentario" required/>
		</p>  -->
		<p>
		<label for="textoComentario">Texto: </label>
		<textarea name="texto" rows="3" cols="30" placeholder="Escriba algo en menos de 100 carácteres" maxlength="100"></textarea>
		</p>
	</fieldset>
		<p>
		<input type="submit" value="+ ANNADIR">
		<input type="reset" value="LIMPIAR">
		</p>
	</form>


<%

//Recuperamos del contexto todos los comentarios
// Debo recuperar sólo los comentarios propios del producto específico
	Hashtable <String, Comentario> comentarios = ((Hashtable <String, Comentario>)request.getServletContext().getAttribute("ATR_COMENTARIOS")==null)?(new Hashtable <String, Comentario>()):((Hashtable <String, Comentario>)request.getServletContext().getAttribute("ATR_COMENTARIOS"));

	//Añadimos datos de prueba
	Hashtable <String, Integer> votos = new Hashtable <String, Integer>();
	votos.put("VotoUsu1", 1);
	votos.put("VotoUsu2", 5);
	votos.put("VotoUsu3", 2);
	
	Hashtable <String, Integer> votos2 = new Hashtable <String, Integer>();
	votos.put("VotoUsu1", 3);
	votos.put("VotoUsu2", 3);
	votos.put("VotoUsu3", 3);
	
	Comentario coment = new Comentario ("idComentario", "Texto del comentario", "Autor del coment", votos);
	Comentario coment2 = new Comentario ("id2", "Mi opinion es poco valorable", "Redactor", votos2);
	comentarios.put("idComentario", coment);
	comentarios.put("id2", coment2);
	
	request.getServletContext().setAttribute("ATR_COMENTARIOS", comentarios);




	if (comentarios != null && comentarios.size() > 0){
		Comentario comentario;
		out.println("<table><tr>"
				+"<th>[Eliminar]</th>"
				+"<th>[  ID  ]</th>"
				+"<th>[ Texto ]</th>"
				+"<th>[Modificar]</th>"
				+"<th>[  Votos  ]</th></tr>");

		for(Enumeration e = comentarios.elements(); e.hasMoreElements();){
			comentario = (Comentario)e.nextElement();

			out.println("<tr>"
			+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/Control?ACTION_ID=DELETECOMENTARIO&idComentario="+ comentario.getIdComentario()+"\">X </a></td>"
			+"<td align=\"CENTER\"> "+comentario.getIdComentario()+" </td>"
			+"<td align=\"CENTER\"> "+comentario.getTextoComentario()+" </td>"
			+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/secured/updateComentario.jsp?textoComentario="+comentario.getTextoComentario()+"&idComentario="+comentario.getIdComentario()+"\"> >> </a></td>"
			+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/secured/comentariosProducto.jsp?idComentario="+comentario.getIdComentario()+"\">Listar Votos</a></td>"
					+"<td align=\"CENTER\" ><form>"
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
%>
	<br> 

<a href="<%=request.getContextPath()%>/secured/productos.jsp">  <button>< VOLVER</button></a>

</body>
</html>