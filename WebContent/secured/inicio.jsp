<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="edu.ucam.beans.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<title>TIENDA</title>
</head>
<body>

<%
	//Intentamos recuperar el usuario que ha iniciado sesión.
	Usuario user = (Usuario) session.getAttribute("USER_LOGGED");
	if (user!=null){
	out.println("<h5 align=\"RIGHT\">Conectado como:  "+user.getIdUsu()+" <a href=\""+request.getContextPath()+"/Control?ACTION_ID=EXIT\"> Salir</a></h5><hr>");
	}
	
%>

Bienvenido, <%= user.getIdUsu() %>!<br><br>

<% if (user.isAdmin()){ %>
Gestionar <a href="<%=request.getContextPath()%>/secured/usuarios.jsp">  USUARIOS</a><br><br>
<%} %>
Gestionar <a href="<%=request.getContextPath()%>/secured/principal.jsp">  PRODUCTOS</a><br><br>
		
</body>
</html>