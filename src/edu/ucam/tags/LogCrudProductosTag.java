package edu.ucam.tags;
import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import edu.ucam.beans.Producto;

public class LogCrudProductosTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		
		//Declaro la lista de productos con su "casting" correspondiente.
		Hashtable <String, Producto> productos = (Hashtable <String, Producto>)this.pageContext.getServletContext().getAttribute("ATR_PRODUCTOS");
		if (productos != null) {
			try {
				this.pageContext.getOut().println("Añadidos: "+this.pageContext.getSession().getAttribute("PRODUCTOS_ADD")
						+"<br>Eliminados: "+this.pageContext.getSession().getAttribute("PRODUCTOS_DEL")
						+"<br>Total de productos: "+ productos.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.EVAL_PAGE;
	}
}