<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="edu.ucam.beans.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cabecera</title>
</head>
<body>
<center><a href="<%=request.getContextPath()%>/secured/inicio.jsp"><h1>TIENDA</h1></a></center>
<br>
<%
	//Intentamos recuperar el usuario que ha iniciado sesión.
	Usuario user = (Usuario) session.getAttribute("USER_LOGGED");
	if (user!=null){
	out.println("<h5 align=\"RIGHT\">Conectado como  "+user.getIdUsu()+".  <a href=\""+request.getContextPath()+"/Control?ACTION_ID=EXIT\"> Salir</a></h5><hr>");
	}
	//Si recibimos un mensaje lo ponemos debajo de la cabecera, encima del formulario.
	if (request.getAttribute("MSG")!=null){
		out.println(request.getAttribute("MSG")+"<br>");
	}else {
		out.println("<br>"); 	// Salto de línea para que no haya desplazamiento brusco hacia abajo 
								// cuando haya mensaje
	}
%>
</body>
</html>