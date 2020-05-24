<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="edu.ucam.beans.Producto"%>
<%@ page import="edu.ucam.beans.Comentario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MODIFICAR COMENTARIO</title>
</head>

<body>
<%@ include file="cabecera.jsp"%>

<form action="<%=request.getContextPath()%>/Control" method="POST">

	<input type="hidden" name="ACTION_ID" value="UPDATECOMENTARIO"/>
	<input type="hidden" name="idComentario" value="<%= request.getParameter("idComentario") %>"/>
	<input type="hidden" name="idProducto" value="<%= request.getParameter("idProducto") %>"/>
	<fieldset>
		<legend> Comentario a modificar</legend>

		<p>
		<label for="textoComentario">Texto: </label>
		<textarea name="textoComentario" rows="3" cols="30" maxlength="100"><%= (String) (((Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS")).get(request.getParameter("idProducto")).getComentarios()).get(request.getParameter("idComentario")).getTextoComentario() %></textarea>
		</p>
	</fieldset>
		<p>
		<input type="submit" value="~ MODIFICAR">
		<input type="reset" value="LIMPIAR">
		</p>
	</form>

	<br> 

<a href="<%=request.getContextPath()%>/secured/comentariosProducto.jsp?idProducto=<%=request.getParameter("idProducto")%>">  <button>< VOLVER</button></a>

</body>
</html>