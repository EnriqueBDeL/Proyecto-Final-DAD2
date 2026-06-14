<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="edu.ucam.domain.Titulacion"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.net.URLEncoder"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Control de titulaciones</title>
</head>
<body>

<%
    String idEditar = request.getParameter("idEditar");
    boolean editando = idEditar != null;
%>

<h1><%= editando ? "Editar Titulacion" : "Añadir Titulacion" %></h1>

<form action="ControlTitulaciones" method="POST">
    <input type="hidden" name="ACTION_ID"
           value="<%= editando ? "EDITAR_TITULACION" : "INSERTAR_TITULACION" %>">
           
     <% if (editando) { %>
        <input type="hidden" name="id" value="<%= idEditar %>">
    <% } %>
	
	Nombre: <input type="text" name="nombre"
           value="<%= editando ? request.getParameter("nombre") : "" %>" required><br>
           
	Facultad: <input type="text" name="facultad"
           value="<%= editando ? request.getParameter("facultad") : "" %>" required><br>
           
	Créditos: <input type="number" name="creditos" 
			value="<%= editando ? request.getParameter("creditos") : "" %>"  required><br>
	
	<input type="submit" value="Guardar">
</form>

<br><br>
    
<h2>Lista de titulaciones:</h2>
    
<%
	@SuppressWarnings("unchecked")
    Hashtable<String, Titulacion> registro = (Hashtable<String, Titulacion>) request.getAttribute("TITULACIONES");

    if (registro != null && !registro.isEmpty()) {
        for (Titulacion t : registro.values()) {
            out.println("<br><b>" + t.getIdTitulacion() + " - " + t.getNombre() + "</b>");
            out.println(" | Facultad: " + t.getFacultad() + " | Créditos: " + t.getCreditos());
            
            %>
            <a href="ControlTitulaciones?ACTION_ID=LISTAR_TITULACIONES&idEditar=<%= t.getIdTitulacion() %>&nombre=<%= URLEncoder.encode(t.getNombre(), "UTF-8") %>&facultad=<%= URLEncoder.encode(t.getFacultad(), "UTF-8") %>&creditos=<%= URLEncoder.encode(String.valueOf(t.getCreditos()), "UTF-8") %>">[Editar]</a>
            <a href="ControlTitulaciones?ACTION_ID=BORRAR_TITULACION&id=<%= t.getIdTitulacion() %>">[Borrar]</a>  
            <%
        }
    } else {
        out.print("<br><b>No hay titulaciones registradas en la base de datos en este momento.</b>");
    }
%>

<br><br>
<a href="menuUsuario.jsp">Volver al Menú Principal</a>

</body>
</html>