<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="edu.ucam.beans.Producto"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MODIFICAR</title>
</head>
<body>

<%
//Recuperamos del contexto la lista de los productos
	Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");
//Compruebo si existe
	boolean exist=productos.containsKey(request.getParameter("idProducto"));
	if(exist == false){
		out.println("El producto que desea modificar no existe, puede que haya sido eliminado por otro usuario. <br><a href=\""+request.getContextPath()+"/principal.jsp \">  Volver</a><br>");
	}else {
//recupero el producto a modificar
	Producto producto = productos.get(request.getParameter("idProducto"));
%>
	<form action="<%=request.getContextPath()%>/Control" method="post">
<!-- hidden para que no se vea el campo que contiene la acción -->
	<input type="hidden" name="ACTION_ID" value="UPDATEPRODUCTO"/>
	<input type="hidden" name="idProducto" value="<%= producto.getIdProducto() %>"/>
	<fieldset>
	<legend> MODIFICAR PRODUCTO <%= producto.getIdProducto() %> </legend>
	<p>
	<label for="nombreProducto">Nombre a modificar:</label><br>
	<input type="text" name="nombreProducto" id="nombreProducto" value="<%= producto.getNombreProducto() %>"/>
	</p>
	</fieldset>	
	<p>
	<input type="submit" value="~ MODIFICAR">
	</p>
	</form><br><br>
<% //VOLVER
out.println("<a href=\"" +request.getContextPath()+ "/secured/principal.jsp \"><button>< VOLVER</button></a>");
}%>

</body>
</html>