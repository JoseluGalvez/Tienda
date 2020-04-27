<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="edu.ucam.beans.Producto"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PRODUCTOS</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/Control" method="POST">
<!-- hidden para que no se vea el campo que contiene la acción -->
	<input type="hidden" name="ACTION_ID" value="ADDPRODUCTO"/>
	<fieldset>
		<legend> NUEVO PRODUCTO </legend>
		<p>
		<label for="idProducto">Identificador de producto: </label>
		<input type="text" name="idProducto" id="idProducto" required/>
		</p>
		<p>
		<label for="nombreProducto">Nombre del producto: </label>
		<input type="text" name="nombreProducto" id="nombreProducto" required/>
		</p>
	</fieldset>
		<p>
		<input type="submit" value="+ AÑADIR">
		</p>
	</form>
	<br><br>

<%
//Recuperamos del contexto todos los productos
	Hashtable <String, Producto> productos = (Hashtable <String, Producto>)request.getServletContext().getAttribute("ATR_PRODUCTOS");

	if (productos != null && productos.size() > 0){
		Producto producto;
		out.println("<table><tr>"
				+"<th>[Eliminar]</th>"
				+"<th>[  ID  ]</th>"
				+"<th>[ Nombre ]</th>"
				+"<th>[Modificar]</th></tr>");

		for(Enumeration e = productos.elements(); e.hasMoreElements();){
			producto = (Producto)e.nextElement();

			out.println("<tr>"
			+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/Control?ACTION_ID=DELETEPRODUCTO&idProducto="+ producto.getIdProducto()+"\">X </a></td>"
			+"<td align=\"CENTER\"> "+producto.getIdProducto()+" </td>"
			+"<td align=\"CENTER\"> "+producto.getNombreProducto()+" </td>"
			+"<td align=\"CENTER\" ><a href=\""+request.getContextPath()+"/secured/updateProducto.jsp?nombreProducto="+producto.getNombreProducto()+"&idProducto="+producto.getIdProducto()+"\"> >> </a></td>"
			+"</tr>");	
		}
		out.println("</table>");
	}else{
		out.println("   - = No hay productos. = -");
	}
%>


</body>
</html>