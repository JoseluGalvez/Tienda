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
<title>VOTOS</title>
</head>
<body>
<%@ include file="cabecera.jsp"%>



<%
//Recuperamos del contexto todos los productos
Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
//Recuperamos los comentarios del producto
Hashtable <String, Comentario> comentarios = (Hashtable <String, Comentario>) (productos.get(request.getParameter("idProducto")).getComentarios());

Hashtable <String, Integer> votos = (Hashtable <String, Integer>) (comentarios.get(request.getParameter("idComentario")).getVotos());

	if (votos != null && votos.size() > 0){
		Integer voto;
		String votante;
		
		
		out.println("<table><tr>"
				+"<th>[Votante]</th>"
				+"<th>[VOTO]</th>"
				+"</tr>");

		for(Enumeration e = votos.keys(); e.hasMoreElements();){
			
			votante = (String)e.nextElement();
			voto = votos.get(votante);
			

			out.println("<tr>"
			+"<td align=\"CENTER\"> "+votante+" </td>"
			+"<td align=\"CENTER\"> "+voto+" </td>"
			+"</tr>");	
		}
		out.println("</table>");
	}else{
		out.println("   - = No hay votos. = -");
	}
%>
	<br> 

<a href="<%=request.getContextPath()%>/secured/comentariosProducto.jsp?idProducto=<%=request.getParameter("idProducto")%>">  <button>< VOLVER</button></a>

</body>
</html>