<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="edu.ucam.beans.Producto"%>
<%@ page import="edu.ucam.beans.Usuario"%>
<%@ taglib uri="tagspractica" prefix="logcont" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PRODUCTOS</title>
</head>
<body>
<%@ include file="cabecera.jsp"%>
<form action="<%=request.getContextPath()%>/Control" method="POST">
<!-- hidden para que no se vea el campo que contiene la acci�n -->
	<input type="hidden" name="ACTION_ID" value="ADDPRODUCTO"/>
	<fieldset>
		<legend> NUEVO PRODUCTO </legend>
		<p>
		<label for="idProducto">Identificador de producto: </label>
		<input type="text" name="idProducto" id="idProducto"  required/>
		</p>
		<p>
		<label for="nombreProducto">Nombre del producto: </label>
		<input type="text" name="nombreProducto" id="nombreProducto"  required/>
		</p>
	</fieldset>
		<p>
		<input type="submit" value="+ A�ADIR">
		<input type="reset" value="LIMPIAR">
		</p>
	</form>

<%
//Recuperamos del contexto todos los productos
	Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");

	if (productos != null && productos.size() > 0){
		Producto producto;
		out.println("<table><tr>"
				+"<th>[Eliminar]</th>"
				+"<th>[  ID  ]</th>"
				+"<th>[ Nombre ]</th>"
				+"<th>[Modificar]</th>"
				+"<th>[Comentarios]</th></tr>");

		for(Enumeration e = productos.elements(); e.hasMoreElements();){
			producto = (Producto)e.nextElement();

			out.println("<tr>"
			+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/Control?ACTION_ID=DELETEPRODUCTO&idProducto="+ producto.getIdProducto()+"\">X </a></td>"
			+"<td align=\"CENTER\"> "+producto.getIdProducto()+" </td>"
			+"<td align=\"CENTER\"> "+producto.getNombreProducto()+" </td>"
			+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/secured/updateProducto.jsp?idProducto="+producto.getIdProducto()+"\"> >> </a></td>"
			+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/secured/comentariosProducto.jsp?idProducto="+producto.getIdProducto()+"\">Mostrar</a></td>"
			+"</tr>");	
		}
		out.println("</table>");
	}else{
		out.println("   - = No hay productos. = -");
	}
%>
<br>   
<!-- ETIQUETA que contabiliza las inserciones y eliminaci�n de productos en esta sesion -->

<logcont:LogProductos/>

<br> 
<a href="<%=request.getContextPath()%>/secured/inicio.jsp">  <button>< VOLVER</button></a>

</body>
</html>