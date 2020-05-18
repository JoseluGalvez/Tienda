<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="edu.ucam.beans.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOGIN</title>
</head>
<body>
<center><h1>- · T I E N D A · -</h1></center><br>
<% //Si recibimos un mensaje lo ponemos encima del formulario.
if (request.getAttribute("MSG")!=null){
	out.println(request.getAttribute("MSG")+"<br>");
}
%>
<form action="<%=request.getContextPath()%>/ServletLogin" method="POST">
	<fieldset>
	<legend>Credenciales de acceso</legend>
	<p>
	<label for="nombre">Usuario</label><br>
	<input type="text" name="nombre" id="nombre" value="admin"/>
	</p>
	<p>
	<label for="contrasena">Contraseña</label><br>
	<input type="password" name="pass" id="pass" value="admin"/>
	</p>
	</fieldset>	
	<input type="submit" value="ACCEDER">
</form>
</body>
</html>