<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="edu.ucam.beans.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LISTA USUARIOS</title>
</head>
<body>

	<form action="<%=request.getContextPath()%>/Control" method="POST">
<!-- hidden para que no se vea el campo que contiene la acción -->
	<input type="hidden" name="ACTION_ID" value="ADDUSUARIO"/>
	<fieldset>
		<legend> NUEVO USUARIO </legend>
		<p>
		<label for="nombre">*Nick usuario nuevo: </label>
		<input type="text" name="nombre" id="nombre" required/>
		</p>
		<p>
		<label for="contrasena">*Contrasenna usuario nuevo: </label>
		<input type="text" name="pass" id="pass" required/>
		</p>
		<p>
		<label for="name">Nombre: </label>
		<input type="text" name="name" id="name" value=""/>
		</p>
		<p>
		<label for="surname">Apellidos: </label>
		<input type="text" name="surname" id="surname" value=""/>
		</p>
		<p>
    	<input type="radio" name="tipo" value="normal" checked> Normal    
    	<input type="radio" name="tipo" value="administrador"> Administrador
  		</p>
		<h6>Los campos marcados con * son obligatorios.</h6>
		</fieldset>
		<p>
		<input type="submit" value="+ ANNADIR">
		</p>
	</form>
	<br><br>

<%
//Recuperamos del contexto todos los usuarios
	Hashtable <String, Usuario> usuarios = (Hashtable <String, Usuario>)request.getServletContext().getAttribute("ATR_USUARIOS");
	if (usuarios != null && usuarios.size() > 0){
		Usuario usuario;
		out.println("<table><tr>"
				+"<th>[Eliminar]</th>"
				+"<th>[ ID ]</th>"
				+"<th>[ Nombre ]</th>"
				+"<th>[ Apellidos ]</th>"
				+"<th>[ Tipo usuario ]</th>"
				+"<th>[Modificar]</th></tr>");
				  
		for(Enumeration e = usuarios.elements(); e.hasMoreElements();){
			usuario = (Usuario)e.nextElement();
			String tipo = (usuario.isAdmin()==true)?"Admin":"Normal";
		
			out.println("<tr>"
			+"<td align=\"CENTER\"><a href=\""+request.getContextPath()+"/Control?ACTION_ID=DELETEUSUARIO&nombre="+usuario.getIdUsu()+ "\">X </a></td>"
			+"<td align=\"CENTER\"> "+usuario.getIdUsu()+" </td>"
			+"<td align=\"CENTER\"> "+usuario.getName()+" </td>"
			+"<td align=\"CENTER\"> "+usuario.getSurname()+" </td>"
			+"<td align=\"CENTER\"> "+tipo+" </td>"
			+"<td align=\"CENTER\"><a href=\""+request.getContextPath()+"/secured/updateUsuario.jsp?nombre="+usuario.getName()+"&pass="+ usuario.getPass()+"&idUsu="+ usuario.getIdUsu()+"\"> >> </a></td>"
			+"</tr>");
		}
		out.println("</table>");
	}else{
		out.println("   - = No hay usuarios. = -");
	}
%>
<br> 
<%
out.println("<a href=\"" +request.getContextPath()+ "/secured/inicio.jsp \"> <button>< VOLVER</button></a>");
%>

</body>
</html>