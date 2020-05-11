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
	<input type="hidden" name="producto" value="<%= request.getParameter("idProducto") %>"/>
	<fieldset>
		<legend> Nuevo comentario para  <%= ProductoNombre %></legend>
		<p>
		<label for="idComentario">Identificador del comentario: </label>
		<input type="text" name="idComentario" id="idComentario" required/>
		</p>
		<p>
		<label for="textoComentario">Texto del comentario: </label>
		<input type="text" name="textoComentario" id="textoComentario" required/>
		</p>
	</fieldset>
		<p>
		<input type="submit" value="+ ANNADIR">
		</p>
	</form>


<%
//out.println("<br>");
//Recuperamos del contexto todos los comentarios
	Hashtable <String, Comentario> comentarios = (Hashtable <String, Comentario>)request.getServletContext().getAttribute("ATR_COMENTARIOS");

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
			+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/secured/updateProducto.jsp?nombreProducto="+comentario.getTextoComentario()+"&idProducto="+comentario.getIdComentario()+"\"> <> </a></td>"
			+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/secured/comentariosProducto.jsp?idProducto="+comentario.getIdComentario()+"\"> Listar</a></td>"
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