<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="edu.ucam.beans.Usuario"%>
<%@ taglib uri="tagspractica" prefix="useradmin" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MODIFICAR</title>
</head>
<body>
<%@ include file="cabecera.jsp"%>

<!-- EIQUETA, si no Admin -> SKIP_BODY -->
<useradmin:AdminLogged>

<%
//Recuperamos del contexto la lista de los usuarios
	Hashtable <String, Usuario> usuarios = (Hashtable <String, Usuario>)request.getServletContext().getAttribute("ATR_USUARIOS");
//Compruebo si existe
	boolean exist=usuarios.containsKey(request.getParameter("idUsu"));
	if(exist == false){
		out.println("El usuario que desea modificar no existe, puede que haya sido eliminado por otro administrador.");
	}else {
//recupero el usuario a modificar
	Usuario usuario = usuarios.get(request.getParameter("idUsu"));
%>
	<form action="<%=request.getContextPath()%>/Control" method="post">
<!-- hidden para que no se vea el campo que contiene la acción -->
	<input type="hidden" name="ACTION_ID" value="UPDATEUSUARIO"/>
	<input type="hidden" name="nombre" value="<%= usuario.getIdUsu() %>"/>
	<fieldset>
	<legend> MODIFICAR <%= usuario.getIdUsu() %> </legend>
	<p>
	<label for="name">Nombre a modificar:</label><br>
	<input type="text" name="name" id="name" value="<%= usuario.getName() %>"/>
	</p>
	<p>
	<label for="surname">Apellidos a modificar:</label><br>
	<input type="text" name="surname" id="surname" value="<%= usuario.getSurname() %>"/>
	</p>
	<p>
	<label for="contrasena">Contrasenna a modificar:</label><br>
	<input type="text" name="pass" id="pass" value="<%= usuario.getPass() %>"/>
	</p>
	<p>
    <input type="radio" name="tipo" value="normal"
		    <% if (usuario.isAdmin()){
		    	out.println("");
		    }else {
		    	out.println("checked");
		    	}
		    %>
	    > Normal    
    
    <input type="radio" name="tipo" value="administrador"
    		<% if (usuario.isAdmin()){
		    	out.println("checked");
		    }else {
		    	out.println("");
		    	}
		    %>
    > Administrador
  	</p>
	</fieldset>	
	<p>
	<input type="submit" value="~ MODIFICAR">
	<input type="reset" value="LIMPIAR">
	</p>
	</form>
	
<%} %>

</useradmin:AdminLogged>

<br>
<a href="<%=request.getContextPath()%>/secured/usuarios.jsp">  <button>< VOLVER</button></a>
	
</body>
</html>